package com.company.hibernate_spring_basics.dao;

import com.company.hibernate_spring_basics.entity.Contact;

import java.util.List;

public interface ContactDao {
    List<Contact> findAll();

    List<Contact> findAllWithDetail();

    Contact findByid(Long id);

    Contact save(Contact contact);

    void delete(Contact contact);
}
