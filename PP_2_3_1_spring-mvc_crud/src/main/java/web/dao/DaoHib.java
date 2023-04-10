package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class DaoHib {
    @PersistenceContext()
    private EntityManager entityManager;


    @Transactional
    public void saveUser(User user) {
        entityManager.persist(user);
        entityManager.close();
    }


    @Transactional
    public void updateUser(User updateUser) {
        entityManager.merge(updateUser);
    }


    @Transactional
    public void removeUserById(int id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT user FROM User user", User.class).getResultList();
    }

    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }
}
