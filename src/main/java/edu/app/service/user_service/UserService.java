package edu.app.service.user_service;

import edu.app.model.user.User;
import edu.app.repository.user_repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service ("userservice")
@Transactional
public class UserService implements IUserService <User> {

    private UserRepository userRepository;

    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(long id) {
        User user = userRepository.findById(id).orElse(null);
        return user;
    }

    @Override
    public List<User> findAll() {
        List <User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User save(User user) {
        User userSaveToDB = userRepository.save(user);
        return userSaveToDB;
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean findUserByUsername(String username) {
        Optional<User> userName = userRepository.findByUserName(username);
        return userName.isPresent();
    }
}
