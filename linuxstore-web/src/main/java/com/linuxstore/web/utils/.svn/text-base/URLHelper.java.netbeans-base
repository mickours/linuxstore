/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.web.utils;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Michael Mercier <michael_mercier@orange.fr>
 */
public class URLHelper {

    /**
     * enumerate the page with associated urls
     */
    public enum Page {
        about,
        applications,
        cart,
        confirmation,
        connection,
        index,
        inscription,
        my_applications,
        post_application,
        post_update,
        infoApp
    };

    private static final String viewsPath = "/WEB-INF/view/";

    public static void redirectTo(Page page, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("selectedPage", page.toString());
        String[] pagesInMenu = {
        Page.index.name(),
        Page.applications.name(),
        Page.my_applications.name(),
        Page.post_application.name(),
        Page.about.name()
        };
        request.setAttribute("pages", pagesInMenu);
        request.getRequestDispatcher(viewsPath+page+".jsp").forward(request, response);
    }
}
