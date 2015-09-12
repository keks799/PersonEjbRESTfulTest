package ru.ejb;

import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import java.util.concurrent.TimeUnit;

/**
 * Created by Business_Book on 12.09.2015.
 */
@Stateful(name = "SessionEJB")
@StatefulTimeout( unit = TimeUnit.MINUTES, value = 30)

public class SessionBean {
    public SessionBean() {
    }

    public String firstMethod() {
        return "My First Method";
    }
}
