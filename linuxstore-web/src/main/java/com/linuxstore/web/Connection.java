/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.web;

import com.linuxstore.ejb.entity.LinuxStoreUser;
import com.linuxstore.ejb.remote.LinuxStoreAdminFacadeRemote;
import com.linuxstore.ejb.remote.LinuxStoreUserFacadeRemote;
import com.linuxstore.web.utils.URLHelper;
import com.linuxstore.web.utils.URLHelper.Page;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Clement WIRTH
 */
@WebServlet(name = "Connection", urlPatterns = {"/connection"})
public class Connection extends HttpServlet {

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

        HttpSession session = request.getSession();
        LinuxStoreUser user;

        String userName = request.getParameter("user");
        String password = request.getParameter("password");

        if (userName != null && password != null) {
            user = admins.findByLogin(userName);
            if (user == null) {
                user = utilisateurs.findByLogin(userName);
            }
            if (user == null) {
                request.setAttribute("errorMessage", "error_login");
            } else {
                if (user.getPassword().equals(password)) {
                    session.setAttribute("user", user);
                } else {
                    request.setAttribute("errorMessage", "error_password");
                }
            }

        }
        if (session.getAttribute("user") != null) {
            String to = (String) session.getAttribute("backTo");
            session.removeAttribute("backTo");
            if(to==null){
                to="/my_applications";
            }
            request.getRequestDispatcher(to).forward(request, response);
        }
        else{
            URLHelper.redirectTo(Page.connection, request, response);
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
