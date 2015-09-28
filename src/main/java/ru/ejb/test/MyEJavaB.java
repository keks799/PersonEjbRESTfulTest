package ru.ejb.test;

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
            System.out.println("new person with id " + person.getId() + " has been added");
        } else {
            for(int i = 0; i < getPersons().size(); i++) {
                if(getPersons().get(i).getId().compareTo(person.getId()) == 0) {
                    getPersons().remove(i);
                    getPersons().add(i, person);
                    System.out.println("person with id " + person.getId() + " has been edited");
                    return person;
                }
            }
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
    public boolean dropPerson(Long id) {
        if (id != null){
            for(Person person : persons) {
                if(person.getId().compareTo(id) == 0) {
                    persons.remove(person);
                    System.out.println("removed person with id " + person.getId());
                    return true;
                }
            }
        }
        return false;
    }

    public List<Person> getPersons() {
        if (persons == null){
            persons = new ArrayList<Person>();
        }
        return persons;
    }
}
