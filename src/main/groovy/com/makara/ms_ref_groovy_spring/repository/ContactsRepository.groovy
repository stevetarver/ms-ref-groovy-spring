package com.makara.ms_ref_groovy_spring.repository

import org.springframework.data.mongodb.repository.MongoRepository

/**
 * I handle MongoDb datastore operations
 *
 * TODO: :( no ping command to tell if I can reach the db for readiness probe
 * TODO: use MongoTemplate to provide proper patch semantics and ping
 * see https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongo-template.save-update-remove
 */
interface ContactsRepository extends MongoRepository<com.makara.ms_ref_groovy_spring.model.Contact, String> {

    // get a contact by _id
    com.makara.ms_ref_groovy_spring.model.Contact findById(String id)

    // get all contacts
    List<com.makara.ms_ref_groovy_spring.model.Contact> findAll()

    // get all contacts using query by example and include sorting and paging
    //    List<Contact> findAll(Contact c)

    // create a contact
    com.makara.ms_ref_groovy_spring.model.Contact insert(com.makara.ms_ref_groovy_spring.model.Contact c)

    // delete a contact - return count of items deleted
    Long removeDistinctById(String id)
    //    Long removeContactById(String id)

    // replace a contact doc
    com.makara.ms_ref_groovy_spring.model.Contact save(com.makara.ms_ref_groovy_spring.model.Contact c)
}
