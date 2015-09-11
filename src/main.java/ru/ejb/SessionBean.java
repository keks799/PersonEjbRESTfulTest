package ru.ejb;

import javax.ejb.Stateless;

/**
 * Created by Business_Book on 11.09.2015.
 */
@Stateless(name = "SessionEJB")
public class SessionBean implements SessionBeanRemote {
    public SessionBean() {
    }

    @Override
    public String firstMethod() {
        return "My First Method";
    }
}
