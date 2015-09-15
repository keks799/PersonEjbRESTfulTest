package controllers;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Created by Business_Book on 14.09.2015.
 */
public class RemoteManager {
    public Object getRemote(){
        Object obj = null;
        try {
            InitialContext ctx = new InitialContext();
            obj = ctx.lookup("global/untitled_ear_exploded/web/MyEJavaB!ru.ejb.MyEJavaBRemote");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
