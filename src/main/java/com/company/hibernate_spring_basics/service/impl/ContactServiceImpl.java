package com.company.hibernate_spring_basics.service.impl;

import com.company.hibernate_spring_basics.Contact_;
import com.company.hibernate_spring_basics.entity.Contact;
import com.company.hibernate_spring_basics.service.ContactService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

@Service("jpaContactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {

    private Logger logger = Logger.getLogger(ContactServiceImpl.class);

    private static final String SELECT_ALL_CONTACTS_NATIVE_QUERY =
            "SELECT id, first_name, last_name, birth_date, version FROM contact";

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

    @Transactional(readOnly = true)
    @Override
    public List<Contact> findAllByNativeQuery() {
//        return entityManager
//                .createNativeQuery(SELECT_ALL_CONTACTS_NATIVE_QUERY, Contact.class)
//                .getResultList();
//        using resultSetMapping
        return entityManager
                .createNativeQuery(SELECT_ALL_CONTACTS_NATIVE_QUERY, "contactResult")
                .getResultList();
    }

    @Override
    public List<Contact> findByCriteriaQuery(String firstName, String lastName) {
        logger.info("Finding contact for firstName: " + firstName + " and lastName : " + lastName);

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Contact> criteriaQuery = cb.createQuery(Contact.class);
        Root<Contact> contactRoot = criteriaQuery.from(Contact.class);

        contactRoot.fetch(Contact_.contactTelDetails, JoinType.LEFT);
        contactRoot.fetch(Contact_.hobbies, JoinType.LEFT);
        criteriaQuery.select(contactRoot).distinct(true);

        Predicate criteria = cb.conjunction();
        if (firstName != null) {
            Predicate p = cb.equal(contactRoot.get(Contact_.firstName), firstName);
            criteria = cb.and(criteria, p);
        }
        if (lastName != null) {
            Predicate p = cb.equal(contactRoot.get(Contact_.lastName), lastName);
            criteria = cb.and(criteria, p);
        }

        criteriaQuery.where(criteria);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Long countAll() {
        return null;
    }
}
