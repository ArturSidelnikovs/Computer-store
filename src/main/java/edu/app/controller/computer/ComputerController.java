package edu.app.controller.computer;

import edu.app.config.security.SecurityUser;
import edu.app.model.computer.Computer;
import edu.app.model.computer.OperatingSystem;
import edu.app.model.computer.ScreenTechnology;
import edu.app.model.user.User;
import edu.app.service.IService;
import edu.app.service.userService.IUserService;
import edu.app.util.computerUtils.ComputerUtils;
import edu.app.util.userUtils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/computers")
public class ComputerController {

    private final IService<Computer> computerIService;
    private final IUserService<User> userService;
    private final UserUtils userUtils;
    private final ComputerUtils computerUtils;

    private static final Logger logger = LoggerFactory.getLogger(ComputerController.class);

    public ComputerController(IService<Computer> computerIService, IUserService<User> userService, UserUtils userUtils, ComputerUtils computerUtils) {
        this.computerIService = computerIService;
        this.userService = userService;
        this.userUtils = userUtils;
        this.computerUtils = computerUtils;
    }

    @GetMapping()
    public String computerList(Model model,
                               @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Computer> computers = computerIService.findAllWithPagination(pageable);
        int[] body = pagination(computers);
        model.addAttribute("computers", computers);
        model.addAttribute("body", body);
        model.addAttribute("amountOfElements", new int[]{5, 10, 20, 50});
        model.addAttribute("userUtils", userUtils);


        return "computers/computerCatalog";
    }

    @GetMapping("/{id}")
    public String computerPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("computer", computerIService.findById(id));
        model.addAttribute("imageLink", "");
        model.addAttribute("userUtils", userUtils);

        return "computers/showComputer";
    }

    @GetMapping("/new")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public String addNewComputer (Model model) {
        model.addAttribute("newComputer", new Computer());
        model.addAttribute("os", OperatingSystem.values());
        model.addAttribute("screenTech", ScreenTechnology.values());
        return "computers/newComputer";
    }

    @PostMapping
    public String create(@ModelAttribute("newComputer") @Valid Computer computer,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("os", OperatingSystem.values());
            model.addAttribute("screenTech", ScreenTechnology.values());
            return "computers/newComputer";
        }
        computerIService.save(computer);
        return "redirect:/computers";
    }

    @PostMapping("/{id}")
    public String addImage(@PathVariable long id, @ModelAttribute(value = "imageLink") String imageLink) {
        Computer computer = computerIService.findById(id);
        computerUtils.addImage(computer, imageLink);
        computerIService.save(computer);
        return "redirect:/computers/" + computer.getId();
    }


    @GetMapping("/{id}/updateComputer")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("computer", computerIService.findById(id));
        model.addAttribute("os", OperatingSystem.values());
        model.addAttribute("screenTech", ScreenTechnology.values());
        return "computers/updateComputer";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id,
                         @ModelAttribute("computer") @Valid Computer computer,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("os", OperatingSystem.values());
            model.addAttribute("screenTech", ScreenTechnology.values());
            return "computers/updateComputer";
        }
        computerIService.save(computer);
        return "redirect:/computers";
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public ResponseEntity<Computer> delete(@PathVariable long id) {
        computerUtils.findUsersAndOrders(id);
        computerIService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/bookmark/{id}")
    public String addToBookmark(@PathVariable long id, Authentication authentication) {

        Computer computer = computerIService.findById(id);
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        List<Computer> bookmarks = user.getBookmarks();
        userUtils.addToBookMarks(bookmarks, computer);
        userService.save(user);
        if (user.isBookmarksEmpty()) {
            User userFromDb = userService.findById(user.getId());
            SecurityUser securityUser1 = new SecurityUser(userFromDb);
            Collection<? extends GrantedAuthority> authorities = securityUser1.getAuthorities();
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(securityUser1, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(token);
            userFromDb.setBookmarksEmpty(false);
        }
        return "redirect:/computers/" + id;
    }

    @DeleteMapping(value = "/bookmark/{id}")
    public String deleteFromBookmark(@PathVariable long id,
                                     @RequestParam(value = "from", required = false) String from,
                                     Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        userUtils.deleteFromBookmarks(user.getBookmarks(), id);
        userService.save(user);
        if (from.equals("bookmarks")) {
            return "redirect:/profile/" + id + "/bookmarks";
        }
        return "redirect:/computers/" + id;
    }


    private int[] pagination(Page<Computer> computers) {
        int[] body;
        int maxComputerPages = 7;
        int headMaxPage = 4;
        int bodyBeforeMaxPage = 4;
        int bodyAfterMaxPages = 2;
        int bodyCenterMaxPage = 3;


        if (computers.getTotalPages() > maxComputerPages) {
            int totalPages = computers.getTotalPages();
            int pageNumber = computers.getNumber() + 1; //Отображаемый индекс страницы на единицу больше,чем тот,что мы имеем в коде.

            /*If current page greater than headMaxPage ,than we display page one and minus one,else we display pages one,two three.*/
            int[] head = (pageNumber > headMaxPage) ? new int[]{1, -1} : new int[]{1, 2, 3};
            /*If current page greater than bodyBeforeMaxPage and pageNumber less than totalPAges minus one,than we display pageNumber minus two,
             and pageNumber minus one,else we display nothing.*/
            int[] bodyBefore = (pageNumber > bodyBeforeMaxPage && pageNumber < totalPages - 1) ? new int[]{pageNumber - 2, pageNumber - 1} : new int[]{};
             /*If current page greater than bodyAfterMaxPages and pageNumber less than totalPAges minus three,than we display pageNumber plus one,
             and pageNumber plus two,else we display nothing.*/
            int[] bodyAfter = (pageNumber > bodyAfterMaxPages && pageNumber < totalPages - 3) ? new int[]{pageNumber + 1, pageNumber + 2} : new int[]{};
            /*If current page greater than bodyCenterMaxPage and pageNumber less than totalPAges minus two,than we display pageNumber,else we display nothing.*/
            int[] bodyCenter = (pageNumber > bodyCenterMaxPage && pageNumber < totalPages - 2) ? new int[]{pageNumber} : new int[]{};
            /*If current page less than totalPages minus three,than we display minus one and totalPages,else we display totalPages-2,totalPages-1 and totalPages.*/
            int[] tail = (pageNumber < totalPages - 3) ? new int[]{-1, totalPages} : new int[]{totalPages - 2, totalPages - 1, totalPages};

            body = merge(head, bodyBefore, bodyCenter, bodyAfter, tail);
        } else {
            body = new int[computers.getTotalPages()];
            for (int i = 0; i < computers.getTotalPages(); i++) {
                body[i] = i + 1;
            }
        }
        return body;
    }

    private int[] merge(int[]... intArrays) {
        return Arrays.stream(intArrays).flatMapToInt(Arrays::stream)
                .toArray();
    }
}