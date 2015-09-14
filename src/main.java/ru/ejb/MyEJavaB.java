package ru.ejb;

import javax.ejb.Stateless;

/**
 * Created by Business_Book on 11.09.2015.
 */
@Stateless (mappedName = "MyEJavaBEJB/Remote")
public class MyEJavaB implements MyEJavaBRemote {
    public MyEJavaB() {
    }

    @Override
    public String firstMethod() {
        return "My First Method";
    }
}
