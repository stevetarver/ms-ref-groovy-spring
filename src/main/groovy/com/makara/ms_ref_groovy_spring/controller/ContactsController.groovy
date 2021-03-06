package com.makara.ms_ref_groovy_spring.controller

import com.makara.ms_ref_groovy_spring.common.aspect.LoggedApi
import com.makara.ms_ref_groovy_spring.exception.ResourceNotFoundInCollectionException
import com.makara.ms_ref_groovy_spring.model.Contact
import com.makara.ms_ref_groovy_spring.service.ContactsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@LoggedApi
@RestController
@RequestMapping(
        value = "/reference/groovy/contacts",
        produces = MediaType.APPLICATION_JSON_VALUE)
class ContactsController {

    private final ContactsService contactsService

    /**
     * The new, Spring preferred way of CDI: Component Scan with @Autowired constructor injection
     *
     * Note: @Autowired is not required iff only 1 ctor. That seems ripe for maintenance injection errors
     *       so I use it always.
     * https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-spring-beans-and-dependency-injection.html
     */
    @Autowired
    ContactsController(ContactsService contactsService) {
        this.contactsService = contactsService
    }

    /**
     * Create a new contact in our contacts collection
     *
     * json:api requires we return the new contact and 201
     */
    @PostMapping
    ResponseEntity createContact(@Validated @RequestBody Contact contact) {
        // Ensure user does not specify an ID let mongo provide a proper ObjectId
        contact.setId(null)
        return new ResponseEntity<>(contactsService.createContact(contact), HttpStatus.CREATED)
    }

    /**
     * Delete the specified contact, return doc deleted count
     *
     * json:api says:
     * - return 404 if contact does not exist
     * - return 204 if we are not returning the deleted contact
     */
    @DeleteMapping(value="/{id}")
    ResponseEntity deleteContact(@PathVariable String id) {
        if(0 == contactsService.deleteContact(id)) {
            throw new ResourceNotFoundInCollectionException("contacts", id)
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT)
    }

    /**
     * Find contacts
     * TODO: update to include search, sort, and paging
     *
     * json:api says: the result of an empty search is OK, not NOT_FOUND
     */
    @GetMapping
    ResponseEntity findContacts() {
        return new ResponseEntity<>(contactsService.getContacts(), HttpStatus.OK)
    }

    /**
     * Get the specified contact
     */
    @GetMapping(value="/{id}")
    ResponseEntity getContact(@PathVariable String id) {
        Contact result = contactsService.getContact(id)
        if(null == result) {
            throw new ResourceNotFoundInCollectionException("contacts", id)
        }
        return new ResponseEntity<>(result, HttpStatus.OK)
    }

    /**
     * Update our version of the contact with the changes specified in the
     * request body.
     *
     * NOTE: json:api has a special form for a patch : http://jsonapi.org/format/#crud-updating
     *       We don't do related data, omitted or null fields are ignored.
     */
    @PatchMapping(value="/{id}")
    ResponseEntity patchContact(@PathVariable String id, @RequestBody Contact contact) {
        // ensure the contact has the proper id set
        contact.setId(id)
        return new ResponseEntity<>(contactsService.patchContact(contact), HttpStatus.OK)
    }

    /**
     * Replace our version of the contact with the provided version.
     */
    @PutMapping(value="/{id}")
    ResponseEntity replaceContact(@PathVariable String id, @RequestBody Contact contact) {
        // ensure the contact has the proper id set
        contact.setId(id)
        return new ResponseEntity<>(contactsService.replaceContact(contact), HttpStatus.OK)
    }
}
