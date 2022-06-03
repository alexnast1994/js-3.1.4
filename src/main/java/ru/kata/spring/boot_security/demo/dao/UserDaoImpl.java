package ru.kata.spring.boot_security.demo.dao;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        encodePassword(user);
        entityManager.persist(user);
    }

    @Override
    public void removeUserById(Long userId) {
        entityManager.createQuery("DELETE FROM User u WHERE u.id=:userId").setParameter("userId", userId).executeUpdate();
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }


    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }


    @Override
    public void updateUser(User user) {
        encodePassword(user);
        entityManager.merge(user);
    }

    @Override
    public User getUserByUserName(String userName) {
        return entityManager.createQuery("FROM User u WHERE u.userName =:userName", User.class).setParameter("userName", userName).getSingleResult();
    }

    @Override
    public void encodePassword(User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    }
}
