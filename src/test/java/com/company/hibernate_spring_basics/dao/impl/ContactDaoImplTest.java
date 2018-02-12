package com.company.hibernate_spring_basics.dao.impl;

import com.company.hibernate_spring_basics.dao.ContactDao;
import com.company.hibernate_spring_basics.entity.Contact;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.function.Consumer;

import static org.junit.Assert.*;

public class ContactDaoImplTest {

    private ApplicationContext ctx;

    @Before
    public void setUp() throws Exception {
        this.ctx = new ClassPathXmlApplicationContext("app-context-annotation.xml");
    }

    @Test
    public void findAll() {
        ContactDao contactDao = ctx.getBean("contactDao", ContactDaoImpl.class);
        List<Contact> contacts = contactDao.findAll();
        System.out.println("Listing contacts without details:");
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    @Test
    public void findAllWithDetail() {
    }

    @Test
    public void findByid() {
    }

    @Test
    public void save() {
    }

    @Test
    public void delete() {
    }
}