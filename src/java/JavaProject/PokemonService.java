/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaProject;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 *
 * @author Felipe Almeida
 */
public class PokemonService extends HttpServlet {

    FileItem file_item;
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
        response.sendRedirect("Pokemons");
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
        PokemonDAO pokemon_dao = new PokemonDAO();
        String action = request.getParameter("action");
        
        if (ServletFileUpload.isMultipartContent(request)) {
            getParameterValues(request);
            action = request.getAttribute("action").toString();
        }
        
        if (action.equals("add")) {
            Pokemon pokemon = new Pokemon();
            pokemon.setName(request.getAttribute("name").toString());
            pokemon.setDescription(request.getAttribute("description").toString());
            pokemon.setDefault_exp(Integer.parseInt(request.getAttribute("default_exp").toString()));
            try {
                pokemon.setImage_url(imageUpload(file_item));
            } catch (Exception ex) {
                Logger.getLogger(PokemonService.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect("Pokemons?msg=error_on_upload");
            }
            try {
                pokemon_dao.add(pokemon);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect("Pokemons?msg=error_on_add");
            }
            response.sendRedirect("Pokemons?msg=pokemon_added");
        } else if (action.equals("update")) {
            String pokemon_id = request.getAttribute("id").toString();
            if (pokemon_id != null) {
                try {
                    Pokemon pokemon = pokemon_dao.get(Integer.parseInt(pokemon_id));
                    if (pokemon != null) {
                        pokemon.setName(request.getAttribute("name").toString());
                        pokemon.setDescription(request.getAttribute("description").toString());
                        if (file_item.getSize() > 0) {
                            pokemon.setImage_url(imageUpload(file_item));
                        }
                        pokemon.setDefault_exp(Integer.parseInt(request.getAttribute("default_exp").toString()));
                        pokemon_dao.update(pokemon);
                        response.sendRedirect("Pokemon?id=" + pokemon_id);
                    } else {
                        response.sendRedirect("Pokemons?msg=pokemon_not_found");
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                    response.sendRedirect("Pokemon?id=" + pokemon_id + "&msg=erro_on_update");
                } catch (Exception ex) {
                    Logger.getLogger(PokemonService.class.getName()).log(Level.SEVERE, null, ex);
                    response.sendRedirect("Pokemon?id=" + pokemon_id + "&msg=erro_on_upload");
                }
            } else {
                response.sendRedirect("Pokemons?msg=error_on_update_data_not_set");
            }
        } else if (action.equals("delete")) {
            if (request.getParameter("id") != null && request.getParameter("confirm_delete") != null) {
                try {
                    pokemon_dao.delete(Integer.parseInt(request.getParameter("id")));
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                    response.sendRedirect("Pokemon?id=" + request.getParameter("id") + "&msg=error_on_delete");
                }
                response.sendRedirect("Pokemons?msg=pokemon_deleted");
            } else {
                response.sendRedirect("Pokemon?id=" + request.getParameter("id") + "&msg=confirmation_not_set");
            }
        } else {
            response.sendRedirect("Pokemons?msg=action_not_set");
        }
    }
    
    public void getParameterValues(HttpServletRequest request) {
        try {
            List<FileItem> formInputs = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            System.out.println(formInputs.size());
            for (FileItem item : formInputs) {
                if (item.isFormField()) {
                    request.setAttribute(item.getFieldName(), item.getString());
                } else {
                    file_item = item;
                }
            }
        } catch (FileUploadException ex) {
            Logger.getLogger(PokemonService.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public String imageUpload(FileItem item) throws Exception {
        String final_name = "";
        ServletContext servletContext = this.getServletConfig().getServletContext();
        String real_path = servletContext.getRealPath("/static/uploads");
        try {
            String name = new File(item.getName()).getName();
            item.write(new File(real_path + "\\" + File.separator + name));
            final_name = name;
        } catch (Exception ex) {
            Logger.getLogger(PokemonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return final_name;
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
