/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloWorld;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ritchiefitzgerald
 */
@WebServlet(name = "ViewPosts", urlPatterns = {"/ViewPosts"})
public class LoadPosts extends HttpServlet {

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
        
        List<Post> posts = getPosts();

        request.setAttribute("posts", posts);

        request.getRequestDispatcher("Posts.jsp").forward(request,response);
    }
    
    public List<Post> getPosts() {
        List<Post> posts = new ArrayList<Post>();
        List<Post> reversedPosts = new ArrayList<Post>();
        String openshiftPath = System.getenv("OPENSHIFT_DATA_DIR");
        String postsFilePath = null;
        
        if (openshiftPath != null) {
            postsFilePath = openshiftPath + "posts.csv";
        } else {
            postsFilePath = getServletContext().getRealPath("/") + "posts.csv";
        }
        
        try {
            CSVReader reader = new CSVReader(new FileReader(postsFilePath));
            String [] line;
            while ((line = reader.readNext()) != null) {
                // line[] is an array of values from the line
                posts.add(new Post(line[0], line[1], line[2]));
            }
            
            for (int i = posts.size()-1; i >= 0; i--) {
                reversedPosts.add(posts.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return reversedPosts;
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
