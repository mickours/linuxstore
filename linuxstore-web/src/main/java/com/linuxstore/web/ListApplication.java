package com.linuxstore.web;

import com.linuxstore.ejb.entity.Application.Category;
import com.linuxstore.ejb.remote.ApplicationFacadeRemote;
import com.linuxstore.ejb.session.ApplicationFacade.AppException;
import com.linuxstore.web.utils.CategoriesView;
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

/**
 *
 * @author Michael Mercier <michael_mercier@orange.fr>
 */
@WebServlet(name = "ListApplication", urlPatterns = {"/applications"})
public class ListApplication extends HttpServlet {

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

        //finding application list
        List appList;
        String patern = request.getParameter("search");
        String selectedCategory = request.getParameter("category");
        String sortby = request.getParameter("sortby");
        try{
            //handle research
            if (patern != null){
                patern = patern.trim();
                appList = applicationFacade.search(patern);
                request.setAttribute("patern",patern);
            }
            else {
                appList = applicationFacade.findAll();
            }
            //filter by category
            if (selectedCategory != null && Category.valueOf(selectedCategory)!=null){
                appList = applicationFacade.filter(appList,Category.valueOf(selectedCategory));
                request.setAttribute("selectedCategory",selectedCategory);
            }
        } catch(AppException ex){
                request.setAttribute("errorMessage",ex.getMessage());
                appList = applicationFacade.findAll();
        }
        if (sortby != null){
             request.setAttribute("sortby",sortby);
            if (sortby.equals("price")){
                appList = applicationFacade.sortByPrice(appList);              
            }
            else if(sortby.equals("name")){
                appList = applicationFacade.sortByName(appList);
            }
        }
        request.setAttribute("appList",appList);

        List<CategoriesView> cats = new ArrayList<CategoriesView>(Category.values().length);
        for (Category c : Category.values()){
            cats.add(new CategoriesView(c.toString(),applicationFacade.categoryCount(patern,c)));
        }
        request.setAttribute("categories",cats);

        URLHelper.redirectTo(Page.applications, request, response);
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
