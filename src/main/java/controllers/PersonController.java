package controllers;

import models.Person;

import javax.ejb.Stateful;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Business_Book on 11.09.2015.
 */

@Stateful
public class PersonController implements Serializable {

    private List<Person> persons = new ArrayList<Person>();

    private Long newId = 0L;    
    
    public List<Person> listOfPersons() {
        return persons;
    }
    
    public Person savePerson(Person person) {
        if(person.getId() == null){
            person.setId(newId);
            persons.add(person);
            newId = person.getId() + 1;
            System.out.println("new person with id " + person.getId() + " has been added");
        } else {
            for(int i = 0; i < persons.size(); i++) {
                if(persons.get(i).getId().compareTo(person.getId()) == 0) {
                    persons.remove(i);
                    persons.add(i, person);
                    System.out.println("person with id " + person.getId() + " has been edited");
                    return person;
                }
            }
        }
        return person;
    }
    
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
}
