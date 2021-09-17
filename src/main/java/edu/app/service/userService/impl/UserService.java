package edu.app.service.userService.impl;

import edu.app.model.user.User;
import edu.app.repository.userRepository.UserRepository;
import edu.app.service.userService.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("userService")
@Transactional
public class UserService implements IUserService<User> {

    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)

    public User findById(long id) {
        User user = userRepository.findById(id).orElse(null);
        logger.info("User {} was successfully found", user.getUserName());
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        logger.info("Users was successfully found");
        return users;
    }

    @Override
    public User save(User user) {
        User userToDb = userRepository.save(user);
        logger.info("User {} successfully added to db", user.getId());
        return userToDb;
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
        logger.info("User with id {} was successfully deleted", id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> findAllWithPagination(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        logger.info("All users in db was successfully found");
        return users;
    }

    @Override
    public boolean findUserByUsername(String username) {
        Optional<User> userName = userRepository.findByUserName(username);
        return userName.isPresent();
    }

    @Override
    public boolean findUserByEmail(String email) {
        Optional<User> userEmail = userRepository.findByEmail(email);
        return userEmail.isPresent();
    }
}