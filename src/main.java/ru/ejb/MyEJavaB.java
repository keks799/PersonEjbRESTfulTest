package ru.ejb;

import models.Person;

import javax.ejb.Stateful;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Business_Book on 11.09.2015.
 */

@Stateful
public class MyEJavaB implements MyEJavaBRemote, Serializable {

    private List<Person> persons;

    @Override
    public String firstMethod() {
        return "My First Method";
    }

    @Override
    public List<Person> listOfPersons() {
        return getPersons();
    }

    @Override
    public Person savePerson(Person person) {
        if(person.getId() == null){
            person.setId((long) (getPersons().size()));
            getPersons().add(person);
        } else {
            getPersons().add(person.getId().intValue(), person);
        }
        return person;
    }

    @Override
    public Person pickPerson(Long id) {
        Person person = null;
        if(persons != null) {
            try {
                person = persons.get(id.intValue());
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return person;
    }

    @Override
    public void dropPerson(Long id) {
        if (id != null){
            persons.remove(id.intValue());
        }
    }

    public List<Person> getPersons() {
        if (persons == null){
            persons = new ArrayList<Person>();
        }
        return persons;
    }
}
