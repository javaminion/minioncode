package com.example.demo.controller;

import com.example.demo.entity.Person;
import com.example.demo.event.PersonAboveRetiredAgeEvent;
import com.example.demo.service.EDGARService;
import com.example.demo.service.PersonService;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

import com.github.javafaker.*;

@Controller
@RequestMapping("/person")
public class PersonController {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
	
    private final PersonService personService;
    
    private final EDGARService edgarService;
    
    private final SimpMessagingTemplate messagingTemplate;

    public PersonController(PersonService personService, SimpMessagingTemplate messagingTemplate, EDGARService edgarService) {
        this.personService = personService;
        this.messagingTemplate = messagingTemplate;
        this.edgarService = edgarService;
    }
    

    //@GetMapping("/api/persons")
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }
    
    public List<Person> loadFakeData(){
    	logger.info("Building your Mock Data...");
    	List<Person> mockData = new ArrayList<Person>();
    	for(int i=0; i < 100; i++) {
    		Faker faker = new Faker();
    		Person obj = new Person();
    		int minAge = 21;
    		int computedAge = (faker.number().randomDigit()) * (faker.number().randomDigit());
    		int age = (computedAge < 21) ? minAge + computedAge : computedAge;
    		//obj.setId(Long.valueOf(i+1));
    		obj.setAge(age);
    		obj.setName(faker.funnyName().name());
    		obj.setAddress(faker.address().fullAddress());
    		obj.setVersion(0);
    		mockData.add(obj);
    	}
    	personService.saveAll(mockData);
    	logger.info("Added 100 records successfully !!");
    	return mockData;
    }
    
    @GetMapping("/mock")
    public String mockPersons(Model model) {
        List<Person> persons = loadFakeData();
        model.addAttribute("persons", persons);
        model.addAttribute("person", new Person()); // For the form
        return "redirect:/person/list";
    }
    
    @GetMapping("/list")
    public String listPersons(Model model) {
        List<Person> persons = getAllPersons();
        model.addAttribute("persons", persons);
        model.addAttribute("person", new Person()); // For the form
        return "chatgptgenperson";
    }
    
    @GetMapping("/retired") public String retiredPersons() {
	  return "retiredpersons"; 
	 }

	
	  @PostMapping("/save") public String savePerson(@ModelAttribute Person person)
	  { 
		  personService.save(person); 
		  
		  return "redirect:/person/list"; 
	  }
	  
	  @EventListener
	    public void handlePersonAgeRetired(PersonAboveRetiredAgeEvent event) {
	        Person person = event.getPerson();
            logger.info(person.getName() + " is above configured retirement age");
            String message = "Person " + person.getName() + ", age: "+ person.getAge() + " years old residing at " + person.getAddress() + " is above retirement age"; 

            // Publish the message to the front-end topic
            messagingTemplate.convertAndSend("/topic/retiredpersons", message);
	        
	    }
	  
	  @GetMapping("/edit/{id}") public String editPerson(@PathVariable Long id,
	  Model model) 
	  { 
	  
		  model.addAttribute("person", personService.findById(id)); model.addAttribute("persons",
			  getAllPersons()); return "chatgptgenperson"; 
	  }
	  
	  @GetMapping("/delete/{id}") public String deletePerson(@PathVariable Long id, Model model)
	  {  
		
		  personService.deleteById(id);
		  model.addAttribute("persons",
				  getAllPersons());
		  return "redirect:/person/list"; 
		  
	  }
	  
	  @GetMapping("/edgar/fetchdata") public String fetchDataFromEdgarApi(@RequestParam String cik) {
		  return edgarService.fetchPublicApiData(cik);		  
	  }
	  
	 
}
