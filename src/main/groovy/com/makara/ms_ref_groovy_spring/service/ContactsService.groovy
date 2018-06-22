package com.makara.ms_ref_groovy_spring.service

import com.makara.ms_ref_groovy_spring.exception.ResourceNotFoundInCollectionException
import com.makara.ms_ref_groovy_spring.model.Contact
import com.makara.ms_ref_groovy_spring.repository.ContactsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


/**
 * I perform orchestration on contact related ReST calls.
 */
@Service
class ContactsService {

    private final ContactsRepository contactsRepository

    /**
     * The new, Spring preferred way of CDI: Component Scan with @Autowired constructor injection
     *
     * Note: @Autowired is not required iff only 1 ctor. That seems ripe for maintenance injection errors
     *       so I use it always.
     * https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-spring-beans-and-dependency-injection.html
     */
    @Autowired
    ContactsService(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository
    }

    Contact createContact(Contact contact) {
        return contactsRepository.insert(contact)
    }

    Long deleteContact(String id) {
        return contactsRepository.removeDistinctById(id)
    }

    Contact getContact(String id) {
        return contactsRepository.findById(id)
    }

    List<Contact> getContacts() {
        return contactsRepository.findAll()
    }

    Contact patchContact(Contact contact) {
        Contact orig = contactsRepository.findById(contact.getId())
        if(null == orig) {
            throw new ResourceNotFoundInCollectionException("contacts", contact.getId())
        }

        // TODO: really ugly, please provide a simpler PATCH
        contact.firstName = contact.firstName ?: orig.firstName
        contact.lastName = contact.lastName ?: orig.lastName
        contact.companyName = contact.companyName ?: orig.companyName
        contact.address = contact.address ?: orig.address
        contact.city = contact.city ?: orig.city
        contact.county = contact.county ?: orig.county
        contact.state = contact.state ?: orig.state
        contact.zip = contact.zip ?: orig.zip
        contact.phone1 = contact.phone1 ?: orig.phone1
        contact.phone2 = contact.phone2 ?: orig.phone2
        contact.email = contact.email ?: orig.email
        contact.website = contact.website ?: orig.website

        return contactsRepository.save(contact)
    }

    Contact replaceContact(Contact contact) {
        return contactsRepository.save(contact)
    }
}
