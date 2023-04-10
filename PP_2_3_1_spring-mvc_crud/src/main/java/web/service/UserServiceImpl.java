package web.service;

import org.springframework.stereotype.Service;
import web.model.User;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Override
    public void updateUser(User updateUser) {

    }

    @Override
    public void removeUserById(int id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }
}
