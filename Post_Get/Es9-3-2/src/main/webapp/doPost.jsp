
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
List of books<BR>
<P>Welcome to Cookies!<BR></P>
<%
out.println(request.getParameter( "lang" ));
%>
is a great language.
</body>
</html>
