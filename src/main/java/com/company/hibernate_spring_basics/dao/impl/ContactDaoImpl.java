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
        return sessionFactory.getCurrentSession().createQuery("from Contact c").list();
    }

    @Transactional(readOnly = true)
    public List<Contact> findAllWithDetail() {
//        use NamedQuery
        return sessionFactory.getCurrentSession().createNamedQuery("Contact.findAllWithDetail").list();
//        use Query
//        return sessionFactory
//                .getCurrentSession()
//                .createQuery("select distinct c " +
//                        "from Contact c left join fetch c.contactTelDetails t left join fetch c.hobbies h").list();
    }

    public Contact findById(Long id) {
        return (Contact) sessionFactory.getCurrentSession()
                .createNamedQuery("Contact.findById")
                .setParameter("id", id)
                .uniqueResult();
    }

    public Contact save(Contact contact) {
        sessionFactory.getCurrentSession().saveOrUpdate(contact);
        logger.info("Contact saved with id: " + contact.getId());
        return contact;
    }

    public void delete(Contact contact) {
        sessionFactory.getCurrentSession().delete(contact);
        logger.info("Contact deleted with id: " + contact.getId());
    }
}
