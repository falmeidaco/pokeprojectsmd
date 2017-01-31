/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaProject;

import java.io.IOException;
import java.sql.SQLException;
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
public class TrainerService extends HttpServlet {

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
        response.sendRedirect("Trainers");
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
        
        TrainerDAO trainer_dao = new TrainerDAO();
        String action = request.getParameter("action");
        
        if (action.equals("add")) {
            Trainer trainer = new Trainer();
            trainer.setName(request.getParameter("name"));
            trainer.setUsername(request.getParameter("username"));
            trainer.setCity_name(request.getParameter("city_name"));
            trainer.setEmail(request.getParameter("email"));
            trainer.setPassword(request.getParameter("password"));
            trainer.setMoney(Double.parseDouble(request.getParameter("money")));
            try {
                trainer_dao.add(trainer);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect("Trainers?msg=error_on_add");
            }
            response.sendRedirect("Trainers?msg=trainer_added");
        } else if (action.equals("update")) {
            if (request.getParameter("id") != null) {
                try {
                    Trainer trainer = trainer_dao.get(Integer.parseInt(request.getParameter("id")));
                    if (trainer != null) {
                        trainer.setName(request.getParameter("name"));
                        trainer.setCity_name(request.getParameter("city_name"));
                        trainer.setUsername(request.getParameter("username"));
                        trainer.setEmail(request.getParameter("email"));
                        trainer.setPassword(request.getParameter("password"));
                        trainer.setMoney(Double.parseDouble(request.getParameter("money")));
                        trainer_dao.update(trainer);
                        response.sendRedirect("Trainer?id=" + request.getParameter("id"));
                    } else {
                        response.sendRedirect("Trainers?msg=trainer_not_found");
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                    response.sendRedirect("Trainer?id=" + request.getParameter("id") + "&msg=error_on_update");
                }
            } else {
                response.sendRedirect("Trainers?msg=erro_on_update_data_not_set");
            }
        } else if (action.equals("delete")) {
            if (request.getParameter("id") != null && request.getParameter("confirm_delete") != null) {
                try {
                    trainer_dao.delete(Integer.parseInt(request.getParameter("id")));
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                    response.sendRedirect("Trainer?id=" + request.getParameter("id") + "&msg=error_on_delete");
                }
                response.sendRedirect("Trainers?msg=user_deleted");
            } else {
                response.sendRedirect("Trainer?id=" + request.getParameter("id") + "&msg=delete_not_confirmed");
            }
        } else if(action.equals("add_pokemon")) {
            if (request.getParameter("trainer_id") != null && request.getParameter("pokemon_id") != null) {
                try {
                    trainer_dao.addPokemon(Integer.parseInt(request.getParameter("trainer_id")), Integer.parseInt(request.getParameter("pokemon_id")), Integer.parseInt(request.getParameter("exp")));
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(TrainerService.class.getName()).log(Level.SEVERE, null, ex);   
                    response.sendRedirect("Trainers?"+ request.getParameter("trainer_id") + "&msg=error_on_delete_pokemon");
                }
                response.sendRedirect("Trainer?id="+ request.getParameter("trainer_id") + "&msg=pokemon_added");
            } else {
                response.sendRedirect("Trainers?msg=error_onadd_pokemon_data_not_set");
            }
        } else if(action.equals("delete_pokemon")) {
            if (request.getParameter("trainer_id") != null && request.getParameter("pokemon_id") != null) {
                try {
                    trainer_dao.deletePokemon(Integer.parseInt(request.getParameter("pokemon_id")));
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(TrainerService.class.getName()).log(Level.SEVERE, null, ex);   
                    response.sendRedirect("Trainers?"+ request.getParameter("trainer_id") + "&msg=error_on_delete_pokemon");
                }
                response.sendRedirect("Trainer?id="+ request.getParameter("trainer_id") + "&msg=pokemon_deleted");
            } else {
                response.sendRedirect("Trainers?msg=error_on_delete_pokemon_data_not_set");
            }
        } else {
            response.sendRedirect("Trainers?msg=action_not_set");
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
