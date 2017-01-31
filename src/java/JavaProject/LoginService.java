/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaProject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Felipe Almeida
 */
public class LoginService extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

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
        if (request.getParameter("action").equals("logout")) {
            HttpSession session = request.getSession();
            session.invalidate();
        }
        response.sendRedirect("LoginPage");
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
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (username == null || password == null) {
            response.sendRedirect("LoginPage?msg=unset_data");
        } else {
            UserDAO user_dao = new UserDAO();
            try {
                User user = user_dao.validate(username, password);
                if (user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("logged", "1");
                    session.setAttribute("logged_user_id", user.getId());
                    session.setAttribute("logged_user_name", user.getName());
                    session.setAttribute("logged_user_username", user.getUsername());
                    session.setAttribute("logged_user_email", user.getEmail());
                    session.setAttribute("logged_user_role", user.getRole());
                    response.sendRedirect("Users");
                } else {
                    response.sendRedirect("LoginPage?msg=invalid_username_or_password");
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(LoginService.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect("LoginPage?msg=erro_on_auth");
            }
        }
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
