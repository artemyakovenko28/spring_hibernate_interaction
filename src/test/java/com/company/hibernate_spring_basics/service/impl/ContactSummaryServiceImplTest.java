package com.company.hibernate_spring_basics.service.impl;

import com.company.hibernate_spring_basics.entity.ContactSummary;
import com.company.hibernate_spring_basics.service.ContactSummaryService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ContactSummaryServiceImplTest {

    private ContactSummaryService contactSummaryService;

    @Before
    public void setUp() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context-jpa.xml");
        this.contactSummaryService =
                ctx.getBean("contactSummaryService", ContactSummaryServiceImpl.class);
    }

    @Test
    public void findAll() {
        List<ContactSummary> contacts = contactSummaryService.findAll();
        for (ContactSummary contactSummary : contacts) {
            System.out.println(contactSummary);
        }
    }
}