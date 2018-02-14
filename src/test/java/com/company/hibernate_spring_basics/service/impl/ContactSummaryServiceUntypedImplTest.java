package com.company.hibernate_spring_basics.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContactSummaryServiceUntypedImplTest {

    private ContactSummaryServiceUntypedImpl contactSummaryServiceUntyped;

    @Before
    public void setUp() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context-jpa.xml");
        this.contactSummaryServiceUntyped =
                ctx.getBean("contactSummaryServiceUntype", ContactSummaryServiceUntypedImpl.class);
    }

    @Test
    public void displayAllContactSummary() {
        contactSummaryServiceUntyped.displayAllContactSummary();
    }
}