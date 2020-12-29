package pl.polsl.anton.pustovidko.servlets;

import java.io.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import pl.polsl.anton.pustovidko.model.Manager;

/**
 * HistoryAccessServlet class stores history and uses cookies
 *
 * @author Anton Pustovidko
 * @version 1.0
 */
public class HistoryAccessServlet extends HttpServlet {

    /**
     * Counter for storing the number of times a user enters the page
     */
    int counter = 0;

    
    Manager storageClass = new Manager();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {

            /* TODO output your page here. You may use following sample code. */
            Cookie[] cookies = request.getCookies();
            String lastVisit = "never";
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("lastVisit")) {
                        lastVisit = cookie.getValue();
                        break;
                    }
                }
            }

            EntityManager em  = (EntityManager) servletContext.getAttribute("entityManager");
            
            List<String> historyList = storageClass.getResults(em);

            out.println("History\n");

            if (historyList == null) {
                out.println("You did not performed any operations");
            } else {
                for (int item = 0; item < historyList.size(); item++) {
                    out.println((item + 1) + ". " + historyList.get(item) + "\n");
                }
            }

            out.println("Your last visit was " + lastVisit);

            counter++;
            Cookie cookie = new Cookie("lastVisit", new java.util.Date().toString());
            response.addCookie(cookie);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
