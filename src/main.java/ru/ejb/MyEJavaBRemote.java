package ru.ejb;

import models.Person;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by Business_Book on 11.09.2015.
 */

@Remote
public interface MyEJavaBRemote {

    String firstMethod();

    List<Person> listOfPersons();

    Person savePerson(Person person);

    Person pickPerson(Long id);

    boolean dropPerson(Long id);
}