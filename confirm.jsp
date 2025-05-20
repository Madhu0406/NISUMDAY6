<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%
    HttpSession session = request.getSession(false);
    if (session == null) {
        out.println("No session found.");
        return;
    }
%>
<h2>Confirmation Page</h2>
<p><strong>Name:</strong> <%= session.getAttribute("name") %></p>
<p><strong>Email:</strong> <%= session.getAttribute("email") %></p>
<p><strong>Qualification:</strong> <%= session.getAttribute("qualification") %></p>
<p><strong>University:</strong> <%= session.getAttribute("university") %></p>
