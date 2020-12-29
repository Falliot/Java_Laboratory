package pl.polsl.anton.pustovidko.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.polsl.anton.pustovidko.model.Model;
import pl.polsl.anton.pustovidko.model.BrailleInputException;

/**
 * MainServlet class
 *
 * @author Anton Pustovidko
 * @version 1.0
 */
public class MainServlet extends HttpServlet {

    /**
     * Model object that performs conversions from plainTest to Braille notation and vise versa
     */
    Model model = new Model();

    /**
     * Initializer for servlet context 
     * @param config servlet configuration
     * @throws ServletException in case of invalid data
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        List<String> historyList = new ArrayList<>();
        getServletContext().setAttribute("historyList", historyList);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext servletContext = getServletContext();
        PrintWriter out = response.getWriter();

        String action = request.getParameter("data");

        String output = "";
        String result = "";
        List<String> historyList = (List<String>) servletContext.getAttribute("historyList");

        if (action.equals("1")) {
            String userInputEnglish = request.getParameter("plainText").toLowerCase();

            if (userInputEnglish.length() == 0) {
                getServletContext().getRequestDispatcher("/Error").forward(request, response);
            } else {
                try {
                    output = model.convertToBraille(userInputEnglish);
                    result = userInputEnglish + " in Braille is " + output;
                    historyList.add(result);
                    servletContext.setAttribute("historyList", historyList);
                    out.println(result);
                } catch (BrailleInputException ex) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input " + userInputEnglish);
                }
            }

        } else {
            String userInputBraille = request.getParameter("brailleCharacters");

            if (userInputBraille.length() == 0) {
                getServletContext().getRequestDispatcher("/Error").forward(request, response);
            } else {
                try {
                    output = model.convertBrailleNumbers(userInputBraille);
                    result = userInputBraille + " from Braille to english is " + output;
                    historyList.add(result);
                    servletContext.setAttribute("historyList", historyList);
                    out.println(result);
                } catch (BrailleInputException ex) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input " + userInputBraille);
                }

            }
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
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
}
