package org.example.es342;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;


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
		String valueName;
		HttpSession session = request.getSession( false );	//impone che il get venga invocato dopo il post e non prima
		Enumeration<String> valueNames;
		if ( session != null )
			valueNames = session.getAttributeNames();
		else
			valueNames = null;

		response.setContentType( "text/html" );
		output = response.getWriter();
		output.println( "<HTML><HEAD><TITLE>" );
		output.println( "Recommendation" );
		output.println( "</TITLE></HEAD><BODY>" );
		if ( valueNames != null && valueNames.hasMoreElements()  ) {
			output.println( "<H1>Recommendations</H1>" );

			while (valueNames.hasMoreElements()) {
				valueName = valueNames.nextElement();
				String value = (String) session.getAttribute( valueName);
				output.println( valueName + " How to Program. " +"ISBN#: " + value + "<BR>" );
			}
		} else {
			output.println( "<H1>No Recommendations</H1>" );
			output.println( "You did not select a language or" );
			output.println( "the cookies have expired." );
		}
		output.println( "</BODY></HTML>" );
		output.close();    // close stream
	}	PrintWriter output;


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter output;
		String language = request.getParameter( "lang" );
		HttpSession session = request.getSession( true ); //se l'obj sessione non esiste lo crea
	    session.setAttribute ( language, getISBN( language ) );
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
