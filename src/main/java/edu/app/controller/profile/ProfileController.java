package edu.app.controller.profile;

import edu.app.config.security.SecurityUser;
import edu.app.model.Order.Order;
import edu.app.model.Order.OrderState;
import edu.app.model.phone.Computer;
import edu.app.model.user.User;
import edu.app.service.IService;
import edu.app.util.orderUtils.OrderUtils;
import edu.app.util.userUtils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;


    @Controller
    @RequestMapping("/profile")
    public class ProfileController {
        private final IService<Computer> computerIService;
        private final IService<User> userService;
        private final IService<Order> orderService;
        private final UserUtils userUtils;
        private final OrderUtils orderUtils;

        private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);


        public ProfileController(IService<Computer> computerIService,
                                 IService<User> userService,
                                 IService<Order> orderService,
                                 @Qualifier(value = "userUtils") UserUtils userUtils,
                                 @Qualifier(value = "orderUtils") OrderUtils orderUtils) {
            this.computerIService = computerIService;
            this.userService = userService;
            this.orderService = orderService;
            this.userUtils = userUtils;
            this.orderUtils = orderUtils;
        }

        @GetMapping("/{id}")
        public String userProfile(@PathVariable Long id, Model model,
                                  Authentication authentication) {
            SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
            User user = securityUser.getUser();
            model.addAttribute("user", user);
            return "profile/profile";
        }

        @GetMapping("/{id}/bookmarks")
        public String bookmarks(@PathVariable long id, Authentication authentication,
                                Model model) {
            SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
            User user = securityUser.getUser();
            List<Computer> bookmarks = user.getBookmarks();
            model.addAttribute("userUtils", userUtils);
            model.addAttribute("bookmarks", bookmarks);
            return "profile/bookmarks";
        }

        @GetMapping("/{id}/purchasesList")
        public String purchasesList(@PathVariable Long id, Model model, Authentication authentication) {
            SecurityUser principal = (SecurityUser) authentication.getPrincipal();
            User user = principal.getUser();
            Order order = userUtils.getPreparatoryOrder(user.getOrders());
            List<Computer> userComputers = new ArrayList<>();
            if (order != null) {
                userComputers = order.getComputers();
            }
            Map<String, Map<Computer, Integer>> phones = orderUtils.convertListOfPhonesIntoMap(userComputers);
            model.addAttribute("purchaseList", phones);
            model.addAttribute("userUtils", userUtils);
            return "profile/purchasesList";
        }

        @GetMapping("/{id}/orders")
        public String ordersList(@PathVariable Long id, Model model, Authentication authentication) {
            SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
            User user = securityUser.getUser();

            List<Order> orders = userUtils.getOrdersWithoutPreparatory(user.getOrders());
            model.addAttribute("orders", orders);
            model.addAttribute("orderUtils", orderUtils);


            return "profile/orders";
        }

        @GetMapping("/{id}/successOrder")
        public String successOrder(@PathVariable Long id) {
            return "fragments/successOrder";
        }

        @PostMapping(value = "/{id}")
        public String addPurchaseInPhoneCatalog(@PathVariable Long id, Authentication authentication,
                                                @RequestParam(value = "requestFrom", required = false) String requestFromParam) {
            SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
            User user = securityUser.getUser();
            Order order = userUtils.getPreparatoryOrder(user.getOrders());
            if (order == null) {
                order = new Order();
                order.setState(OrderState.PREPARATORY);
                order.setUser(user);
                userUtils.addOrder(user.getOrders(), order);
            }
            Computer computerById = computerIService.findById(id);
            orderUtils.addPhone(order.getComputers(), computerById);


            orderService.save(order);
            if (requestFromParam.equals("showPhone")) {
                return "redirect:/phones/" + id;
            } else if (requestFromParam.equals("bookmarks")) {
                return "redirect:/profile/" + user.getId() + "/bookmarks";
            }
            return "redirect:/computers";
        }

        @PostMapping(value = "/{id}/plusOp")
        public String addPhoneInPlusOperation(@PathVariable Long id, Authentication authentication) {
            SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
            User user = securityUser.getUser();
            Order order = userUtils.getPreparatoryOrder(user.getOrders());
            Computer computerById = computerIService.findById(id);
            orderUtils.addPhone(order.getComputers(), computerById);

            orderService.save(order);
            return "redirect:/profile/" + user.getId() + "/purchasesList";
        }

        @PostMapping(value = "/{id}/order")
        public String makeAnOrder(@PathVariable Long id, Authentication authentication) {
            SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
            User user = securityUser.getUser();
            Order order = userUtils.getPreparatoryOrder(user.getOrders());
            order.setState(OrderState.ACTIVE);
            orderService.save(order);
            return "redirect:/profile/" + user.getId() + "/successOrder";
        }

        @PutMapping(value = "/{id}/orders")
        public String cancelTheOrder(@PathVariable long id, Authentication authentication) {
            SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
            User user = securityUser.getUser();
            Order order = userUtils.findOrder(user.getOrders(), id);
            if (!order.getState().toString().equals("CANCELED")) {
                order.setState(OrderState.CANCELED);
            } else {
                order.setState(OrderState.ACTIVE);
            }
            orderService.save(order);
            return "redirect:/profile/" + user.getId() + "/orders";
        }

        @PutMapping(value = "/{id}")
        public String updateAvatar(@PathVariable long id, Authentication authentication,
                                   @RequestParam("file") MultipartFile file) throws IOException {
            SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
            User user = securityUser.getUser();
            if (file.isEmpty()) {
                return "redirect:/profile/" + user.getId() + "?noFileChosen=true";
            }
            String fileType = file.getOriginalFilename().split("\\.")[1].toLowerCase(Locale.ROOT);
            if (fileType.equals("png") || fileType.equals("jpg") || fileType.equals("jpeg")) {
                user.setAvatar(file.getBytes());
                userService.save(user);
                return "redirect:/profile/" + user.getId();
            }
            return "redirect:/profile/" + user.getId() + "?fileTypeError=true";
        }

        @PutMapping(value = "/{id}/editUser")
        public String editUser(@PathVariable long id, Authentication authentication,
                               @ModelAttribute("user") @Valid User user,
                               BindingResult bindingResult) {
            if (bindingResult.hasErrors()) {
                return "profile/profile";
            }
            SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
            User loginUser = securityUser.getUser();
            loginUser.setAddress(user.getAddress());
            loginUser.setEmail(user.getEmail());
            loginUser.setFirstName(user.getFirstName());
            loginUser.setLastName(user.getLastName());
            loginUser.setUserName(user.getUserName());
            userService.save(loginUser);
            return "redirect:/profile/" + user.getId();
        }


        @DeleteMapping(value = "/{id}/minusOperator")
        public String deleteFromMinusOperator(@PathVariable Long id, Authentication authentication) {
            SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
            User user = securityUser.getUser();
            Order order = userUtils.getPreparatoryOrder(user.getOrders());
            orderUtils.deletePhone(order.getComputers(), id);

            orderService.save(order);
            return "redirect:/profile/" + securityUser.getUser().getId() + "/purchasesList";
        }

        @DeleteMapping(value = "/{id}/allPhones")
        public String removeAllPhonesInOneOrderFromPurchase(@PathVariable Long id, Authentication authentication) {
            SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
            User user = securityUser.getUser();
            Order order = userUtils.getPreparatoryOrder(user.getOrders());
            orderUtils.deleteAllPhones(order.getComputers(), id);
            if (order.getComputers().isEmpty()) {
                user.getOrders().remove(order);
                orderService.deleteById(order.getId());

                return "redirect:/profile/{id}/purchasesList";
            }
            orderService.save(order);
            return "redirect:/profile/{id}/purchasesList";
        }


        @DeleteMapping(value = "/{id}/order")
        public String deleteOrder(@PathVariable Long id, Authentication authentication) {
            SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
            User user = securityUser.getUser();
            Order order = userUtils.findOrder(user.getOrders(), id);
            if (order.getState().toString().equals("CANCELED")) {
                orderService.deleteById(id);
                userUtils.deleteOrder(user.getOrders(), id);
                return "redirect:/profile/" + user.getId() + "/orders";
            }
            order.setState(OrderState.DELETED);
            orderService.save(order);
            return "redirect:/profile/" + user.getId() + "/orders";
        }
    }
