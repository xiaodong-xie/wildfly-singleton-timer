package com.xxd.wildfly.singleton.timer;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
@LocalBean
public class SessionBean {

    @PersistenceContext(unitName = "ejbPU")
    protected EntityManager em;

    public void create(User user) {
        em.persist(user);
    }

    public User edit(User user) {
        return em.merge(user);
    }

    public User findById(long id) {
        return em.find(User.class, id);
    }

    public List<User> findAll() {
        TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
        return query.getResultList();
    }

    public EntityManager getEntityManager() {
        return em;
    }

}
