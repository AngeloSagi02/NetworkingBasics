package org.angelo.es92;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/Register")
public class Register extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        File f = new File("C:\\Users\\angel\\OneDrive\\Desktop\\PSR\\Progetti\\Es8\\registrations.txt");
        FileOutputStream fos = new FileOutputStream(f, true);
        PrintStream ps = new PrintStream(fos);

        ps.println(request.getParameter("firstname"));
        ps.println(request.getParameter("lastname"));
        ps.println(request.getParameter("email"));
        ps.println(request.getParameter("pw"));
        ps.println("-");

        request.setAttribute("firstname", request.getParameter("firstname"));

        try {
            request.getRequestDispatcher("index.jsp").forward(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }


    }
}