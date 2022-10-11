package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<User> getAllUsers() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUser(int id) {
        return em.find(User.class, id);
    }

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> q = (em.createQuery("select u from User u join fetch u.roleSet where u.username =:username"
                , User.class));
        q.setParameter("username", username);
        return q.getResultList().stream().findFirst().orElse(null);

    }

    @Override
    public void updateUser(int id, User updatedUser) {
        em.merge(updatedUser);
    }

    @Override
    public void deleteUser(int id) {
        User user = em.find(User.class, id);
        em.remove(user);
    }
}
