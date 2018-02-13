package com.company.hibernate_spring_basics.dao.impl;

import com.company.hibernate_spring_basics.dao.ContactDao;
import com.company.hibernate_spring_basics.entity.Contact;
import com.company.hibernate_spring_basics.entity.ContactTelDetail;
import com.company.hibernate_spring_basics.entity.Hobby;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;
import java.util.function.Consumer;

import static org.junit.Assert.*;

public class ContactDaoImplTest {

    private ContactDao contactDao;

    private void listContactsWithDetail(List<Contact> contacts) {
        for (Contact contact : contacts) {
            System.out.println(contact);
            if (contact.getContactTelDetails() != null) {
                for (ContactTelDetail contactTelDetail : contact.getContactTelDetails()) {
                    System.out.println(contactTelDetail);
                }
            }
            if (contact.getHobbies() != null) {
                for (Hobby hobby : contact.getHobbies()) {
                    System.out.println(hobby);
                }
            }
            System.out.println();
        }
    }

    @Before
    public void setUp() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context-annotation.xml");
        this.contactDao = ctx.getBean("contactDao", ContactDaoImpl.class);
    }

    @Test
    public void findAll() {
        List<Contact> contacts = contactDao.findAll();
        System.out.println("Listing contacts without details:");
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    @Test
    public void findAllWithDetail() {
        List<Contact> contacts = contactDao.findAllWithDetail();
        listContactsWithDetail(contacts);
    }

    @Test
    public void findById() {
        Contact contact = contactDao.findById(1L);
        System.out.println("Contact with id 1: ");
        System.out.println(contact);
    }

    @Test
    public void save() {
        Contact contact = new Contact();
        contact.setFirstName("Michael");
        contact.setLastName("Jackson");
        contact.setBirthDate(new Date());

        Set<ContactTelDetail> contactTelDetails = new HashSet<>();
        contactTelDetails.add(new ContactTelDetail("Home", "11111111"));
        contactTelDetails.add(new ContactTelDetail("Mobile", "22222222"));

        contact.setContactTelDetails(contactTelDetails);

        contactDao.save(contact);

        listContactsWithDetail(contactDao.findAllWithDetail());
    }

    @Test
    public void delete() {
        Contact contact = contactDao.findById(1L);
        contactDao.delete(contact);
        listContactsWithDetail(contactDao.findAllWithDetail());
    }
}