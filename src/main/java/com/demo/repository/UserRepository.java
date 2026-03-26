package com.demo.repository;

import com.demo.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UserRepository {
    
    @PersistenceContext(unitName = "userPU")
    private EntityManager entityManager;
    
    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }
    
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }
    
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }
    
    @Transactional
    public void update(User user) {
        entityManager.merge(user);
    }
    
    @Transactional
    public void delete(Long id) {
        User user = findById(id);
        if (user != null) {
            entityManager.remove(user);
        }
    }
}

// Made with Bob
