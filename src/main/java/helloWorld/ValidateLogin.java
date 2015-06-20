/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloWorld;

import com.opencsv.CSVReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ritchiefitzgerald
 */
@WebServlet(name = "ValidateLogin", urlPatterns = {"/ValidateLogin"})
public class ValidateLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
//      Get Credentials
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
//        request.setAttribute("username", username);
        request.getSession().setAttribute("username", username);

//      Direct user to the correct page based on the validity of their credentials.
        if (isLoginValid(username, password, response)) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            request.setAttribute("date", format.format(new Date()));
            
            request.getRequestDispatcher("NewPost.jsp").forward(request,response);
        } else {
            request.getRequestDispatcher("LoginFailed.jsp").forward(request,response);
        }
    }
    
    /**
     * Validates the user credentials.
     * 
     * @param  username the username the user entered.
     * @param  password the password the user entered.
     * @return          whether the credentials match a saved user.
     */
    protected Boolean isLoginValid(String username, String password, HttpServletResponse response) {
        
        Boolean validLogin = false;
        
        String openshiftPath = System.getenv("OPENSHIFT_DATA_DIR");
        String userFilePath = null;
        
        if (openshiftPath != null) {
            userFilePath = openshiftPath + "users.csv";
        } else {
            userFilePath = getServletContext().getRealPath("/") + "users.csv";
        }

        try {
//            PrintWriter out = response.getWriter();
            
            CSVReader reader = new CSVReader(new FileReader(userFilePath));
            String [] line;
            while ((line = reader.readNext()) != null) {
                // line[] is an array of values from the line
                
//                out.println("Username: " + line[0]);
                if (username.equals(line[0])  && password.equals(line[1])) {
                    validLogin = true;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return validLogin;
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
