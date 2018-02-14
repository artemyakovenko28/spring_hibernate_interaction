package com.company.hibernate_spring_basics.service.impl;

import com.company.hibernate_spring_basics.entity.Contact;
import com.company.hibernate_spring_basics.entity.ContactTelDetail;
import com.company.hibernate_spring_basics.entity.Hobby;
import com.company.hibernate_spring_basics.service.ContactService;
import com.company.hibernate_spring_basics.service.impl.ContactServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactServiceImplTest {

    private ContactService contactService;

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
        ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context-jpa.xml");
        this.contactService = ctx.getBean("jpaContactService", ContactServiceImpl.class);
    }

    @Test
    public void findAll() {
        List<Contact> contacts = contactService.findAll();
        System.out.println("Listing contacts without details:");
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    @Test
    public void findAllWithDetail() {
        List<Contact> contacts = contactService.findAllWithDetail();
        listContactsWithDetail(contacts);
    }

    @Test
    public void findById() {
        Contact contact = contactService.findById(1L);
        System.out.println("Contact with id 1: ");
        System.out.println(contact);
    }

    @Test
    public void save() {
        Contact contact = new Contact();
        contact. setFirstName ( "Michael");
        contact.setLastName("Jackson");
        contact.setBirthDate(new Date());

        ContactTelDetail contactTelDetail = new ContactTelDetail("Home", "1111111111");
        contact.addContactTelDetail(contactTelDetail);
        contactTelDetail = new ContactTelDetail("Mobile", "2222222222");
        contact.addContactTelDetail(contactTelDetail);

        contactService.save(contact);
        listContactsWithDetail(contactService.findAllWithDetail());
    }

    @Test
    public void delete() {
        Contact contact = contactService.findById(1L);
        contactService.delete(contact);
        listContactsWithDetail(contactService.findAllWithDetail());
    }
}