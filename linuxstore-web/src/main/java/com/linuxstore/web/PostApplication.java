/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.web;

import com.linuxstore.ejb.entity.Application;
import com.linuxstore.ejb.entity.Application.Category;
import com.linuxstore.web.utils.URLHelper;
import com.linuxstore.web.utils.URLHelper.Page;
import com.linuxstore.web.utils.UploadFileHelper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
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

        if (FileUpload.isMultipartContent(request)) {
            // Wrap the form data
//        String name = request.getParameter("name");
//        String description = request.getParameter("description");
//        String category = request.getParameter("category");
//        String price = request.getParameter("price");

            //if ((name != null) && (description != null)) {
            String msg = "application_posted";
            request.setAttribute("confirmationMessage", msg);
            URLHelper.redirectTo(Page.confirmation, request, response);
            try {
                Connection connection = connectionFactory.createConnection();
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                MessageProducer messageProducer = session.createProducer(queue);

                ObjectMessage message = session.createObjectMessage();
                // here we create NewsEntity, that will be sent in JMS message
                Application e = new Application();

                //upload File and Icon on the server
                UploadFileHelper.uploadApplication(request, e, getServletContext().getRealPath("/"));

                message.setObject(e);
                messageProducer.send(message);
                messageProducer.close();
                connection.close();

            } catch (Exception ex) {
                Logger.getLogger(PostApplication.class.getName()).log(Level.SEVERE, null, ex);
                msg = "application_not_posted";
            }
//            }finally{
//                request.setAttribute("confirmationMessage", msg);
//                URLHelper.redirectTo(Page.confirmation, request, response);
//            }
        } else {
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
