package com.example.demo.event;

import com.example.demo.entity.Person;

public class PersonAboveRetiredAgeEvent {
	
	private final Person person;

	public Person getPerson() {
		return person;
	}

	public PersonAboveRetiredAgeEvent(Person person) {
		super();
		this.person = person;
	}

}
