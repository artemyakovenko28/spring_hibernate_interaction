package com.company.hibernate_spring_basics.service.impl;

import com.company.hibernate_spring_basics.ContactRepository;
import com.company.hibernate_spring_basics.entity.Contact;
import com.company.hibernate_spring_basics.service.ContactService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("contactServiceSpringDataJpa")
@Repository
@Transactional
public class ContactServiceSpringDataJpaImpl implements ContactService {

    private ContactRepository contactRepository;

    @Autowired
    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Contact> findAll() {
        return Lists.newArrayList(contactRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public Contact findById(Long id) {
        return contactRepository.findById(id).get();
    }

    @Override
    public List<Contact> findAllWithDetail() {
        return null;
    }

    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public void delete(Contact contact) {
        contactRepository.delete(contact);
    }

    @Override
    public List<Contact> findAllByNativeQuery() {
        return null;
    }

    @Override
    public List<Contact> findByCriteriaQuery(String firstName, String lastName) {
        return null;
    }

    @Transactional(propagation = Propagation.NEVER)
    @Override
    public Long countAll() {
        return contactRepository.countAllContacts();
    }
}
