package com.company.hibernate_spring_basics.service.impl;

import com.company.hibernate_spring_basics.entity.Contact;
import com.company.hibernate_spring_basics.service.ContactService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("jpaContactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {

    private Logger logger = Logger.getLogger(ContactServiceImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public List<Contact> findAll() {
        return entityManager.createNamedQuery("Contact.findAll", Contact.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Contact> findAllWithDetail() {
        return entityManager
                .createNamedQuery("Contact.findAllWithDetail", Contact.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Contact findById(Long id) {
        return entityManager
                .createNamedQuery("Contact.findById", Contact.class)
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public Contact save(Contact contact) {
        if (contact.getId() == null) {
            logger.info("Inserting new contact");
            entityManager.persist(contact);
        } else {
            entityManager.merge(contact);
            logger.info("Updating existing contact");
        }
        logger.info("Contact save with id: " + contact.getId());
        return contact;
    }

    @Override
    public void delete(Contact contact) {
        Contact contactToDelete = entityManager.find(Contact.class, contact.getId());
        if (contactToDelete != null) {
            entityManager.remove(contactToDelete);
        }
        logger.info("Contact with id: " + contact.getId() + "deleted successfully");
    }
}
