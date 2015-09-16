package ru.ejb;

import models.Person;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by Business_Book on 11.09.2015.
 */

@Remote
public interface MyEJavaBRemote {

    public String firstMethod();

    public List<Person> listOfPersons();

    public void createNewPerson(Person person);

    public Person pickPerson(Long id);

    public Person saveExistedPerson(Person existedPerson);

    public void dropPerson(Long id);
}