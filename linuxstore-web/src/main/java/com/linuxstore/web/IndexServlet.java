/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.web;

import com.linuxstore.ejb.entity.Application;
import com.linuxstore.ejb.remote.ApplicationFacadeRemote;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.linuxstore.web.utils.CategoriesView;
import com.linuxstore.web.utils.URLHelper;
import com.linuxstore.web.utils.URLHelper.Page;

/**
 *
 * @author mickours
 */
@WebServlet(name = "IndexServlet", urlPatterns = {"/index"})
public class IndexServlet extends HttpServlet {

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

        List<CategoriesView> cats = new ArrayList<CategoriesView>(Application.Category.values().length);
        for (Application.Category c : Application.Category.values()) {
            cats.add(new CategoriesView(c.toString(), applicationFacade.categoryCount(null, c)));
        }
        List<Application> app=applicationFacade.findAll();
        try{
            Application topGame = applicationFacade.filter(app, Application.Category.Jeux).get(0);
            Application topTheme = applicationFacade.filter(app, Application.Category.Themes).get(0);
            Application topAccesoire = applicationFacade.filter(app, Application.Category.Accessoires).get(0);
            Application topMultimedia = applicationFacade.filter(app, Application.Category.Multimedia).get(0);
            request.setAttribute("categories", cats);
            request.setAttribute("appTopGame", topGame);
            request.setAttribute("appTopTheme", topTheme);
            request.setAttribute("appTopAccessoire", topAccesoire);
            request.setAttribute("appTopMulti", topMultimedia);
        }catch(IndexOutOfBoundsException ex){
            //do nothing : missing apps
        }
        
        URLHelper.redirectTo(Page.index, request, response);
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
