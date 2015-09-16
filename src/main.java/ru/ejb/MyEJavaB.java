package ru.ejb;

import javax.ejb.Stateful;

/**
 * Created by Business_Book on 11.09.2015.
 */

@Stateful
public class MyEJavaB  {
    public MyEJavaB() {
    }

    public String firstMethod() {
        return "My First Method";
    }
}
