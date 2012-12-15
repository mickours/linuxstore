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
    public static List<String> listOfAppExtensions = Arrays.asList("zip", "tar.gz", "tar", "7z");
    public static List<String> listOfImgExtensions = Arrays.asList("jpg", "jpeg", "png", "gif");

    public static void uploadApplication(HttpServletRequest request, Application app) throws Exception {
        // on prépare pour l'envoie par la mise en oeuvre en mémoire
        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
        ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
        List items = uploadHandler.parseRequest(request);

        new File(applications).mkdirs();
        new File(image).mkdirs();
        
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
                if (listOfAppExtensions.contains(fileExtensionName)) {
                    base = applications;
                    file = new File(base, fileItem.getName());
                    app.setFile(file.getPath());
                } else if (listOfImgExtensions.contains(fileExtensionName)) {
                    base = image;
                    file = new File(base, fileItem.getName());
                    app.setImagePath(file.getPath());
                }
                if (base == null) {
                    throw new Exception("Unsuported files");
                }
                item.write(file);
            }
        }
    }
}
