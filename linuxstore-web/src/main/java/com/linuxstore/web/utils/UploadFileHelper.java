package com.linuxstore.web.utils;

import com.linuxstore.ejb.entity.Application;
import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Michael Mercier <michael_mercier@orange.fr>
 */
public class UploadFileHelper {

    public final static String applications = "uploads/";
    public final static String image = applications + "img/";
    public static List<String> listOfAppExtensions = Arrays.asList("zip", "gz", "tar", "7z");
    public static List<String> listOfImgExtensions = Arrays.asList("jpg", "jpeg", "png", "gif");

    public static void uploadApplication(HttpServletRequest request, Application app,String servletPath) throws Exception {
        // on prépare pour l'envoie par la mise en oeuvre en mémoire
        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
        ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
        List items = uploadHandler.parseRequest(request);
        String error = "";

        new File(servletPath+applications).mkdirs();
        new File(servletPath+image).mkdirs();

        Iterator itr = items.iterator();
        while (itr.hasNext()) {
            DiskFileItem item = (DiskFileItem) itr.next();
            if (item.getFieldName().equals("name")) {
                app.setName(item.getString());
            } else if (item.getFieldName().equals("description")) {
                app.setDescription(item.getString());
            } else if (item.getFieldName().equals("price")) {
                app.setPrice(Float.parseFloat(item.getString()));
            } else if (item.getFieldName().equals("category")) {
                app.setCategory(Application.Category.valueOf(item.getString()));
            } else if (item instanceof FileItem) {
                FileItem fileItem = (FileItem)item;
                String fileExtensionName = fileItem.getName();
                fileExtensionName = FilenameUtils.getExtension(fileExtensionName);

                String base = null;
                File file = null;

                if (item.getFieldName().equals("file") && FilenameUtils.isExtension(fileItem.getName(), listOfAppExtensions)){
                    base = servletPath+applications;
                    File tmp = new File(fileItem.getName());
                    file = new File(base, tmp.getName());
                    app.setFile(applications+file.getName());
                } if (item.getFieldName().equals("icon") && FilenameUtils.isExtension(fileItem.getName(), listOfImgExtensions)) {
                    base = servletPath+image;
                    File tmp = new File(fileItem.getName());
                    file = new File(base, tmp.getName());
                    app.setImagePath(image+file.getName());
                }
                if (base == null) {
                    error += "the "+fileExtensionName+" file type is not supported";
                    if (item.getFieldName().equals("file")){
                        error += " for the application file<br/>";
                    }
                    else if (item.getFieldName().equals("icon")){
                        error += " for the icon file<br/>";
                    }
                }
                else{
                    item.write(file);
                }
            }
        }
        if (!error.isEmpty()){
            throw new Exception(error);
        }
    }
}
