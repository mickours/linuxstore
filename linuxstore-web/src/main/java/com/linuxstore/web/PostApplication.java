/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.web;

import com.linuxstore.ejb.entity.Application;
import com.linuxstore.ejb.entity.Application.Category;
import com.linuxstore.ejb.entity.LinuxStoreUser;
import com.linuxstore.web.utils.URLHelper;
import com.linuxstore.web.utils.URLHelper.Page;
import com.linuxstore.web.utils.UploadFileHelper;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileUpload;

/**
 *
 * @author Michael Mercier <michael_mercier@orange.fr>
 */
@WebServlet(name = "PostApplication", urlPatterns = {"/post_application"})
public class PostApplication extends HttpServlet {

    @Resource(mappedName = "jms/NewMessageFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/NewMessage")
    private Queue queue;

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
        //not connected
        if (request.getSession().getAttribute("user") == null){
            request.getSession().setAttribute("backTo","post_application");
            request.setAttribute("errorMessage", "error_not_logged_applications");
            URLHelper.redirectTo(Page.connection, request, response);
        }
        //form is ok
        else if (FileUpload.isMultipartContent(request)) {

            String msg = "application_posted";
            Connection connection = null;
            MessageProducer messageProducer = null;
            try {
                connection = connectionFactory.createConnection();
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                messageProducer = session.createProducer(queue);

                ObjectMessage message = session.createObjectMessage();
                // here we create NewsEntity, that will be sent in JMS message
                Application e = new Application();
                //upload File and Icon on the server
                UploadFileHelper.uploadApplication(request, e, getServletContext().getRealPath("/"));
                //link app to User
                LinuxStoreUser user = (LinuxStoreUser) request.getSession().getAttribute("user");
                e.setOwner(user);
                List<Application> applist = new LinkedList<Application>();
                applist.add(e);
                user.addToMyApplications(applist);

                message.setObject(e);
                messageProducer.send(message);

            } catch (Exception ex) {
                msg=ex.getMessage();
//                msg = "application_not_posted";
            }
            finally{
                try {
                    messageProducer.close();
                    connection.close();
                } catch (JMSException ex) {
                    //do nothing
                }
            }
            if (msg.equals("application_posted")){
                request.setAttribute("confirmationMessage", msg);
                URLHelper.redirectTo(Page.confirmation, request, response);
            }
            else {
                request.setAttribute("errorMessage", msg);
                request.setAttribute("categories", Category.values());
                URLHelper.redirectTo(Page.post_application, request, response);
            }
        }
        //access to form
        else {
            request.setAttribute("fileTypes", UploadFileHelper.listOfAppExtensions);
            request.setAttribute("imgTypes", UploadFileHelper.listOfImgExtensions);
            request.setAttribute("categories", Category.values());
            URLHelper.redirectTo(Page.post_application, request, response);
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
