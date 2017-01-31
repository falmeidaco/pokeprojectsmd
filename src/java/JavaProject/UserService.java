/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaProject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Almeida
 */
public class UserService extends HttpServlet {

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
        response.sendRedirect("Users");
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
        
        request.setCharacterEncoding("UTF-8");
        
        UserDAO user_dao = new UserDAO();
        
        String action = request.getParameter("action");
        
        if (action.equals("add")) {
            User user = new User();
            user.setName(request.getParameter("name"));
            user.setUsername(request.getParameter("username"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("password"));
            user.setRole(request.getParameter("role"));
            try {
                user_dao.add(user);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect("Users?msg=error_on_add");
            }
            response.sendRedirect("Users?msg=user_added");
        } else if (action.equals("update")) {
            if (request.getParameter("id") != null) {
                try {
                    User user = user_dao.get(Integer.parseInt(request.getParameter("id")));
                    if (!user.equals(null)) {
                        user.setName(request.getParameter("name"));
                        user.setUsername(request.getParameter("username"));
                        user.setEmail(request.getParameter("email"));
                        user.setPassword(request.getParameter("password"));
                        user.setRole(request.getParameter("role"));
                        user_dao.update(user);
                    } else {
                        response.sendRedirect("Users?msg=error_on_update_user_not_found");
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                    response.sendRedirect("User?id=" + request.getParameter("id") + "&msg=error_on_update");
                }
                response.sendRedirect("User?id=" + request.getParameter("id") + "&msg=user_updated");
            } else {
                response.sendRedirect("Users?msg=error_on_update_data_not_set");
            }
        } else if (action.equals("delete")) {
            if (request.getParameter("id") != null && request.getParameter("confirm_delete") != null) {
                try {
                    user_dao.delete(Integer.parseInt(request.getParameter("id")));
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                    response.sendRedirect("Users?id=" + request.getParameter("id") + "&msg=error_on_delete");
                }
                response.sendRedirect("Users?msg=user_deleted");
            } else {
                response.sendRedirect("User?id=" + request.getParameter("id") + "&msg=error_on_delete_confirmation_not_set");
            }
        } else {
            response.sendRedirect("Users?msg=action_not_set");
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
