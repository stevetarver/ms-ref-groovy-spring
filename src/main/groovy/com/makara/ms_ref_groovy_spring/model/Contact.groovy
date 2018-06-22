package com.makara.ms_ref_groovy_spring.model

import org.hibernate.validator.constraints.NotEmpty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


/**
 * Model for Contact documents
 */
@Document(collection = "contacts")
class Contact {
    @Id
    String id;
    @NotEmpty
    String firstName;
    @NotEmpty
    String lastName;
    String companyName;
    String address;
    String city;
    String county;
    String state;
    @NotEmpty
    String zip;
    String phone1;
    String phone2;
    @NotEmpty
    String email;
    String website;
}
