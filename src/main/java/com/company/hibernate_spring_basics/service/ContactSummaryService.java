package com.company.hibernate_spring_basics.service;

import com.company.hibernate_spring_basics.entity.ContactSummary;

import java.util.List;

public interface ContactSummaryService {

    List<ContactSummary> findAll();
}
