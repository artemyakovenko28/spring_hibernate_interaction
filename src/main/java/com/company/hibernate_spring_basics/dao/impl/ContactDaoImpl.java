package com.company.hibernate_spring_basics.dao.impl;

import com.company.hibernate_spring_basics.dao.ContactDao;
import com.company.hibernate_spring_basics.entity.Contact;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Repository("contactDao")
public class ContactDaoImpl implements ContactDao {
    private static final Logger logger = Logger.getLogger(ContactDaoImpl.class);

    private SessionFactory sessionFactory;

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Contact> findAll() {
        return (List<Contact>) sessionFactory.getCurrentSession().createQuery("from Contact c").list();
    }

    public List<Contact> findAllWithDetail() {
        return null;
    }

    public Contact findByid(Long id) {
        return null;
    }

    public Contact save(Contact contact) {
        return null;
    }

    public void delete(Contact contact) {

    }
}
