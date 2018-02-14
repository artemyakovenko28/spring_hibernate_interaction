package com.company.hibernate_spring_basics.service.impl;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("contactSummaryServiceUntyped")
@Repository
@Transactional
public class ContactSummaryServiceUntypedImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public void displayAllContactSummary() {
        List result = entityManager
                .createQuery("select c.firstName, c.lastName, t.telNumber " +
                "from Contact c left join c.contactTelDetails t " +
                "where t.telType='Home'").getResultList();
        int count = 0;
        for (Object row : result) {
            Object[] values = (Object[]) row;
            System.out.println(++count + ": " + values[0] + ", " + values[1] + ", " + values[2]);
        }
    }
}
