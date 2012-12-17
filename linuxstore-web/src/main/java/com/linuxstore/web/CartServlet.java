/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.web;

import com.linuxstore.ejb.entity.Application;
import com.linuxstore.ejb.remote.ApplicationFacadeRemote;
import com.linuxstore.web.utils.AppCart;
import com.linuxstore.web.utils.URLHelper;
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
 * @author Michael Mercier <michael_mercier@orange.fr>
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/addToCart", "/viewCart", "/removeFromCart"})
public class CartServlet extends HttpServlet {
@EJB
    private ApplicationFacadeRemote applicationFacade;
    

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userPath = request.getServletPath();
        HttpSession session = request.getSession();
        AppCart cart = (AppCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new AppCart();
            session.setAttribute("cart", cart);
        }

        if (userPath.equals("/viewCart")) {
            //do nothing
        } else if (userPath.equals("/addToCart")) {
            String appToAddId = request.getParameter("appToAddId");
            Application appToAdd = applicationFacade.find(Long.parseLong(appToAddId));
            cart.addApp(appToAdd);
        } else if (userPath.equals("/removeFromCart")) {
            String appToRemoveId = request.getParameter("appToRemoveId");
            Application toRemove = applicationFacade.find(Long.parseLong(appToRemoveId));
            cart.removeApp(toRemove);
        }
        URLHelper.redirectTo(URLHelper.Page.cart,request, response);
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
