package ru.ejb;

import models.Person;

import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Business_Book on 11.09.2015.
 */

@Stateful
public class MyEJavaB implements MyEJavaBRemote {

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
    public void createNewPerson(Person person) {
        if(person.getId() == null){
            person.setId((long) (persons.size()));
        }
        persons.add(person);
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
    public Person saveExistedPerson(Person existedPerson) {
        if(existedPerson != null) {
            persons.add(existedPerson.getId().intValue(), existedPerson);
        }
        return existedPerson;
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

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
