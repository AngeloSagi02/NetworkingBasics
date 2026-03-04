package org.angelo.es931;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        request.getRequestDispatcher("doGet.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String language = request.getParameter( "lang" );
        Cookie c = new Cookie( language, getISBN( language ) );
        c.setMaxAge( 120 );
        response.addCookie( c );

        request.getRequestDispatcher("doPost.jsp").forward(request, response);
    }


    private String getISBN( String lang ) {
        for ( int i = 0; i < names.length; ++i )
            if ( lang.equals( names[ i ] ) )
                return isbn[ i ];
        return "";  // no matching string found
    }
}
