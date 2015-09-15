package controllers;

import ru.ejb.MyEJavaBRemote;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Business_Book on 11.09.2015.
 */

@WebServlet(name = "greet", urlPatterns = "/")
//@WebServlet("/TestServlet")
public class MyServlet extends HttpServlet {
    public MyServlet() {
        super();
    }

    public static String getEARName() {
        String url = MyServlet.class.getProtectionDomain().getCodeSource().getLocation().toString();
        // cause doesn't work for non standard class loader
        if (!url.contains(".ear")) {
            return "untitled";
        }
        url = url.substring(0, url.indexOf(".ear"));
        url = url.substring(url.lastIndexOf("/") + 1);
        return url;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.write("MyServlet greets you!!!!11");
        try {
            InitialContext ctx = new InitialContext();
            Object obj = ctx.lookup("global/untitled_ear_exploded/web/MyEJavaB!ru.ejb.MyEJavaBRemote");
            out.print(obj);
            MyEJavaBRemote remote = (MyEJavaBRemote) obj;
            String result = remote.firstMethod();
            out.print(result);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
