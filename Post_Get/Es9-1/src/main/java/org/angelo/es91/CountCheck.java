package org.angelo.es91;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/Count")
public class CountCheck extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(true);
        Integer count = (Integer) session.getAttribute("count");

        if(count == null) {
            count = 1;
        }
        else{
            count++;
        }
        session.setAttribute("count", count);

        response.setContentType("text/plain");
        response.getWriter().println(count);

    }

    public void destroy() {
    }
}