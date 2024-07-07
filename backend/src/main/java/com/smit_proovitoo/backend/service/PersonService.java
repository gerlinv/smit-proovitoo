package com.smit_proovitoo.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smit_proovitoo.backend.model.Person;
import com.smit_proovitoo.backend.repository.PersonRepository;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Retrieves an existing person by identity code.
     *
     * @param identityCode, the identity code of the person.
     * @return the person if found, null otherwise.
     */
    public Person getExistingPerson(Long identityCode) {
        return personRepository.findByIdentityCode(identityCode);
    }

    /**
     * Saves a person entity into the database.
     *
     * @param person, the person object to be saved.
     * @return the saved person object.
     */
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

}
