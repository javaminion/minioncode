package com.example.demo.service;

import com.example.demo.entity.Person;
import com.example.demo.event.PersonAboveRetiredAgeEvent;
import com.example.demo.repository.PersonRepository;

import jakarta.transaction.Transactional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final ApplicationEventPublisher eventPublisher;

    public PersonService(PersonRepository personRepository, ApplicationEventPublisher eventPublisher) {
        this.personRepository = personRepository;
        this.eventPublisher = eventPublisher;
    }

    @Cacheable("persons")
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }
    
    public void save(Person person) {
    	if(person.getAge() > 55) {
    		eventPublisher.publishEvent(new PersonAboveRetiredAgeEvent(person));    		
    	}
    	personRepository.save(person);
    	
    }
    
    public Person findById(Long id) {
    	Person person = personRepository.findById(id).orElseThrow(()
    			  -> new IllegalArgumentException("Invalid person Id:" + id));
    	return person;
    }
    
    public void deleteById(Long id) {
    	personRepository.deleteById(id);
    }
    
    @Transactional
    public void saveAll(List<Person> records) {
    	personRepository.saveAll(records);
    }
}
