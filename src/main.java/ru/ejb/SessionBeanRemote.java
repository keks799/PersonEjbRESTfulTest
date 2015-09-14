package ru.ejb;

import javax.ejb.Remote;

/**
 * Created by Business_Book on 11.09.2015.
 */

@Remote
public interface SessionBeanRemote {

    public String firstMethod();
}
