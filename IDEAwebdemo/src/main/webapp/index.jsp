<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello, I am a Java web app!</title>
</head>
<body>
<%
    String error = (String) request.getSession().getAttribute("error");
    if (error != null) {
        response.getWriter().println("<h2>Login fail</h2>");
        request.getSession().invalidate();
    }
%>
<h1>Simple Java Web App Demo</h1>
<p>Java demo servlet <a href="demo-servlet">here</a></p>
<p>HelloServet With Annotation: <a href="hello-anou">click here</a></p>
<p>HelloServletXML: <a href="say-hello">click here</a></p>
<p>Input form: <a href="form_input.html">click here</a></p>
<p>Login form: <a href="login_form.html">click here</a></p>
<p>session test: <a href="session-test">click here</a></p>
<p>filter test: <a href="filter">click here</a></p>
</body>
</html>