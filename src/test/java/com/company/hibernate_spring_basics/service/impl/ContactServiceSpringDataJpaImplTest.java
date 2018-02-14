package com.company.hibernate_spring_basics.service.impl;

import com.company.hibernate_spring_basics.entity.Contact;
import com.company.hibernate_spring_basics.service.ContactService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.regex.Matcher;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ContactServiceSpringDataJpaImplTest {

    private ContactService contactService;

    @Before
    public void setUp() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context-jpa.xml");
        this.contactService =
                ctx.getBean("contactServiceSpringDataJpa", ContactServiceSpringDataJpaImpl.class);
    }

    @Test
    public void findAll() {
        List<Contact> contacts = contactService.findAll();
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    @Test
    public void findById() {
        System.out.println(contactService.findById(1L));
    }

    @Test
    public void findAllWithDetail() {
    }

    @Test
    public void save() {
        Contact contact = contactService.findById(1L);
        contact.setFirstName("Peter");
        contact.setLastName("Jackson");
        contactService.save(contact);
        System.out.println("Saved contact: " + contactService.findById(1L));
    }

    @Test
    public void delete() {
    }

    @Test
    public void findAllByNativeQuery() {
    }

    @Test
    public void findByCriteriaQuery() {
    }

    @Test
    public void countAll() {
        Long contactCount = contactService.countAll();
        System.out.println("Contact count: " + contactCount);
        assertThat(contactCount, is(3L));
    }
}