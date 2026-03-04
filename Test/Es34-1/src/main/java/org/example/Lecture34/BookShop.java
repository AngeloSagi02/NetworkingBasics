package org.example.Lecture34;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class BookShop
 */
@WebServlet("/BookShop")
public class BookShop extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String names[] = { "C", "C++", "Java","Visual Basic" };
    private String isbn[] = { "0-13-226119-7", "0-13-528910-6", "0-13-012507-5", "0-13-528910-6" };

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookShop() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        PrintWriter output;
        Cookie cookies[];
        cookies = request.getCookies(); // get client's cookies
        response.setContentType( "text/html" );
        output = response.getWriter();
        output.println( "<HTML><HEAD><TITLE>" );
        output.println( "Recommendations" );
        output.println( "</TITLE></HEAD><BODY>" );
        if ( cookies != null ) {
            output.println( "<H1>Recommendations</H1>" );
            // get the name of each cookie
            for ( int i = 0; i < cookies.length; i++ )
                output.println( cookies[ i ].getName() + " How to Program. " +"ISBN#: " + cookies[ i ].getValue() + "<BR>" );
        } else {
            output.println( "<H1>No Recommendations</H1>" );
            output.println( "You did not select a language or" );
            output.println( "the cookies have expired." );
        }
        output.println( "</BODY></HTML>" );
        output.close();    // close stream
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        PrintWriter output;
        String language = request.getParameter( "lang" );
        Cookie c = new Cookie( language, getISBN( language ) );
        c.setMaxAge( 120 );
        response.addCookie( c );
        response.setContentType( "text/html" );
        output = response.getWriter();
        output.println( "<HTML><HEAD><TITLE>" );
        output.println( "List of books" );
        output.println( "</TITLE></HEAD><BODY>" );
        output.println( "<P>Welcome to Cookies!<BR>" );
        output.println( "</P>" );
        output.println( language );
        output.println( " is a great language." );
        output.println( "</BODY></HTML>" );
        output.close();    // close stream
    }


    private String getISBN( String lang ) {
        for ( int i = 0; i < names.length; ++i )
            if ( lang.equals( names[ i ] ) )
                return isbn[ i ];
        return "";  // no matching string found
    }
}
