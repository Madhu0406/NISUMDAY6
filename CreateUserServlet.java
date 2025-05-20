//package com.nisum;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
//public class CreateUserServlet extends HttpServlet {
//     private Map<String, User> users = new HashMap<>();
//
//     String username;
//     String password;
//     String url;
//
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        System.out.println("the init method");
//        this.username=config.getInitParameter("username");
//        this.password=config.getInitParameter("password");
//    }
//
////    @Override
////    public void init() throws ServletException {
////        ServletContext servletContext=getServletContext();
////        username=(String)servletContext.getAttribute("username");
////    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//
//        ServletContext servletContext=getServletContext();
//        username=(String)servletContext.getAttribute("username");
//        String username = req.getParameter("username");
//        String email = req.getParameter("email");
//
////        Connection connection = DriverManager.getConnection(url,username,password);
////
////       Statement statement = connection.createStatement();
//
//
//
//        resp.setContentType("text/html");
//        resp.getWriter().write("<h2>User Created Successfully</h2>");
//        resp.getWriter().write("<p>Username: " + username + "</p>");
//        resp.getWriter().write("<p>Email: " + email + "</p>");
//        // users.put(email, new User(username, email));
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String pathInfo =   req.getPathInfo();
//
//        resp.setContentType("application/json");
//
//        String email = pathInfo.substring(1);
//
////        String email = req.getParameter("email");
//
//        User user = users.get(email);
////        resp.getWriter().write("<h1>")
//
//
//        resp.getWriter().write(users.get(email).toString());
////        resp.getWriter().write(users.get(username).toString());
//    }
//
//
//
//}

package com.nisum;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CreateUserServlet extends HttpServlet {

    private final Map<String, User> users = new HashMap<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String email = req.getParameter("email");

        if (username == null || email == null || username.isEmpty() || email.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Both username and email are required.");
            return;
        }

        users.put(email, new User(username, email));

        resp.setContentType("text/html");
        resp.getWriter().write("<h2>User Created Successfully</h2>");
        resp.getWriter().write("<p>Username: " + username + "</p>");
        resp.getWriter().write("<p>Email: " + email + "</p>");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String pathInfo = req.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email must be provided in the path.");
            return;
        }

        String email = pathInfo.substring(1); // remove leading slash
        User user = users.get(email);

        if (user == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found.");
            return;
        }

        resp.setContentType("application/json");
        resp.getWriter().write(user.toString());
    }
}
