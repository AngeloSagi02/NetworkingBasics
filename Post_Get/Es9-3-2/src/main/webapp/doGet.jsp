<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
Recommendation

<%
    String valueName;
    Enumeration<String> valueNames;
    if ( session != null )
        valueNames = session.getAttributeNames();
    else
        valueNames = null;

    if ( valueNames != null && valueNames.hasMoreElements()  ) {
        out.println( "Recommendations\n");

        while (valueNames.hasMoreElements()) {
            valueName = valueNames.nextElement();
            String value = (String) session.getAttribute( valueName);
            out.println( valueName + " How to Program. " +"ISBN#: " + value + "\n" );
        }
    } else {
        out.println( "No Recommendations\n" );
        out.println( "You did not select a language or\n" );
        out.println( "the cookies have expired.\n" );
    }
%>
</body>
</html>