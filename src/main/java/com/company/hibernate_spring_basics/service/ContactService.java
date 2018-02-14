package com.company.hibernate_spring_basics.service;

import com.company.hibernate_spring_basics.entity.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> findAll();

    List<Contact> findAllWithDetail();

    Contact findById(Long id);

    Contact save(Contact contact);

    void delete(Contact contact);

    List<Contact> findAllByNativeQuery();

    List<Contact> findByCriteriaQuery(String firstName, String lastName);
}
