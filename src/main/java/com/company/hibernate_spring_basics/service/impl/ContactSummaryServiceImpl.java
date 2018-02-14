package com.company.hibernate_spring_basics.service.impl;

import com.company.hibernate_spring_basics.entity.ContactSummary;
import com.company.hibernate_spring_basics.service.ContactSummaryService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("contactSummaryService")
@Repository
@Transactional
public class ContactSummaryServiceImpl implements ContactSummaryService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public List<ContactSummary> findAll() {
        List<ContactSummary> result = entityManager.createQuery(
                "select new com.company.hibernate_spring_basics.entity.ContactSummary(" +
                        "c.firstName, c.lastName, t.telNumber) " +
                        "from Contact c left join c.contactTelDetails t " +
                        "where t.telType='Home'", ContactSummary.class).getResultList();
        return result;
    }
}
