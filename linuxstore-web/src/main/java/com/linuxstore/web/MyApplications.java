/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.web;

import com.linuxstore.ejb.entity.Application;
import com.linuxstore.ejb.entity.LinuxStoreUser;
import com.linuxstore.ejb.remote.ApplicationFacadeRemote;
import com.linuxstore.web.utils.URLHelper;
import com.linuxstore.web.utils.URLHelper.Page;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Michael Mercier <michael_mercier@orange.fr>
 */
@WebServlet(name = "MyApplications", urlPatterns = {"/my_applications"})
public class MyApplications extends HttpServlet {

    @EJB
    private ApplicationFacadeRemote applicationFacade;

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
        LinuxStoreUser user = (LinuxStoreUser) session.getAttribute("user");
        if (user == null) {
            session.setAttribute("backTo", "my_applications");
            request.setAttribute("errorMessage", "error_not_logged_applications");
            request.getRequestDispatcher("connection").forward(request, response);
        } else {
            List<Application> applicationsBuyedByUser = new ArrayList<Application>();
            List<Application> applicationsOwnedByUser = new ArrayList<Application>();

            for (Application application : user.getMyApplications()) {
                Application app = applicationFacade.find(application.getId());
                if (app.getOwner().equals(user)){
                    applicationsOwnedByUser.add(app);
                } else {
                    applicationsBuyedByUser.add(app);
                }
            }
//            for (int i = 0 ; i < applicationsBuyedByUser.size();i++) {
//                if (user.equals(applicationsBuyedByUser.get(i).getOwner())) {
//                    applicationsOwnedByUser.add(applicationsBuyedByUser.get(i));
//                    request.setAttribute("debug",applicationsBuyedByUser.get(i));
//                    applicationsBuyedByUser.remove(i);
//                    i--;
//                }
//            }

            request.setAttribute("applicationsBuyed", applicationsBuyedByUser);
            request.setAttribute("applicationsOwned", applicationsOwnedByUser);

            URLHelper.redirectTo(Page.my_applications, request, response);
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
