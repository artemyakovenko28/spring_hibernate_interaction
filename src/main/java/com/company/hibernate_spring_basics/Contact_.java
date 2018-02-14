package com.company.hibernate_spring_basics;

import com.company.hibernate_spring_basics.entity.Contact;
import com.company.hibernate_spring_basics.entity.ContactTelDetail;
import com.company.hibernate_spring_basics.entity.Hobby;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@StaticMetamodel(Contact.class)
public class Contact_ {
    public static volatile SingularAttribute<Contact, Long> id;
    public static volatile SetAttribute<Contact, ContactTelDetail> contactTelDetails;
    public static volatile SingularAttribute<Contact, String> lastName;
    public static volatile SingularAttribute<Contact, Date> birthDate;
    public static volatile SetAttribute<Contact, Hobby> hobbies;
    public static volatile SingularAttribute<Contact, String> firstName;
    public static volatile SingularAttribute<Contact, Integer> version;
}
