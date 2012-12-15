/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.web;

import com.linuxstore.ejb.entity.LinuxStoreUser;
import com.linuxstore.ejb.remote.LinuxStoreAdminFacadeRemote;
import com.linuxstore.ejb.remote.LinuxStoreUserFacadeRemote;
import com.linuxstore.web.utils.URLHelper;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Clement WIRTH
 */
@WebServlet(name = "Inscription", urlPatterns = {"/inscription"})
public class Inscription extends HttpServlet {

    @EJB
    private LinuxStoreUserFacadeRemote utilisateurs;
    @EJB
    private LinuxStoreAdminFacadeRemote admins;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LinuxStoreUser user = null;

        String userName = request.getParameter("user");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");


        if (userName == null || password == null || password2 == null) {
            URLHelper.redirectTo(URLHelper.Page.inscription, request, response);
        } else {
            if (!userName.matches("^[\\w_.~-]+@[\\w][\\w.\\-]*[\\w]\\.[\\w][\\w.]*[a-zA-Z]$")) {
                request.setAttribute("errorMessage", "error_bad_login");
                URLHelper.redirectTo(URLHelper.Page.inscription, request, response);
            } else {
                if (password.length() < 6) {
                    request.setAttribute("errorMessage", "error_invalid_password");
                    URLHelper.redirectTo(URLHelper.Page.inscription, request, response);
                } else {
                    if (!password.equals(password2)) {
                        request.setAttribute("errorMessage", "error_password_retype");
                        URLHelper.redirectTo(URLHelper.Page.inscription, request, response);
                    } else {
                        user = admins.findByLogin(userName);
                        if (user == null) {
                            user = utilisateurs.findByLogin(userName);
                        }
                        if (user != null) {
                            request.setAttribute("errorMessage", "error_login_used");
                            URLHelper.redirectTo(URLHelper.Page.inscription, request, response);
                        } else {
                            user = new LinuxStoreUser();
                            user.setLoginMail(userName);
                            user.setPassword(password);
                            utilisateurs.create(user);
                            request.setAttribute("errorMessage", "inscription_success");
                            URLHelper.redirectTo(URLHelper.Page.connection, request, response);
                        }
                    }
                }
            }

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
