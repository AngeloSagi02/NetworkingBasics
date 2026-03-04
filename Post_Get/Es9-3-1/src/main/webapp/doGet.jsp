<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>

<H1>Recommendations</H1><br>

<%
    Cookie cookies[];
    cookies = request.getCookies();
    if ( request.getCookies() != null ) {

        for ( int i = 0; i < cookies.length; i++ )
        out.println( cookies[ i ].getName() + " How to Program. " +"ISBN#: " + cookies[ i ].getValue() + "<BR>" );
    }
    else {
        out.println( "<H1>No Recommendations</H1>" );
        out.println( "You did not select a language or" );
        out.println( "the cookies have expired." );
    }
%>
</body>
</html>