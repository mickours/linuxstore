/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.web;

import com.linuxstore.ejb.entity.Application;
import com.linuxstore.ejb.entity.LinuxStoreUser;
import com.linuxstore.ejb.remote.LinuxStoreUserFacadeRemote;
import com.linuxstore.web.utils.AppCart;
import com.linuxstore.web.utils.URLHelper;
import java.io.IOException;
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
 * @author mickours
 */
@WebServlet(name = "PurchaseServlet", urlPatterns = {"/purchase"})
public class PurchaseServlet extends HttpServlet {

    @EJB
    private LinuxStoreUserFacadeRemote userFacade;

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
            session.setAttribute("backTo","purchase");
            request.setAttribute("errorMessage", "error_not_logged_cart");
            request.getRequestDispatcher("connection").forward(request, response);
        } else {
            //TODO check this values
            String secCode = request.getParameter("securityCode");
            String month = request.getParameter("month");
            String year = request.getParameter("year");
            String isOk = request.getParameter("ok");
            AppCart cart = (AppCart)session.getAttribute("cart");
            if (isOk != null || cart.getTotalPrice()==0){
                //associate applications with the user
                List<Application> appToAdd = cart.getAppList();
                user.addToMyApplications(appToAdd);
                userFacade.edit(user);
                session.removeAttribute("cart");
                request.setAttribute("confirmationMessage", "purchase_finished");
                URLHelper.redirectTo(URLHelper.Page.confirmation, request, response);
            }
            else{
                URLHelper.redirectTo(URLHelper.Page.purchase, request, response);
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
