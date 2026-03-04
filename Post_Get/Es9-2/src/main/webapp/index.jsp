<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body style="background-color: black; color: white" >

  <h2 >
    <% out.println(request.getParameter("firstname")); %>  LA TUA REGISTRAZIONE E' AVVENUTA CON SUCCESSO <br>
    <%= request.getParameter("firstname") %> LA TUA REGISTRAZIONE E' AVVENUTA CON SUCCESSO<br>
    ${param.firstname} LA TUA REGISTRAZIONE E' AVVENUTA CON SUCCESSO<br>
    ${firstname} LA TUA REGISTRAZIONE E' AVVENUTA CON SUCCESSO<br>
  </h2>
  <br>

</body>
</html>