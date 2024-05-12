package controller;

import database.ParentCategoryDao;
import model.ParentCategory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
@MultipartConfig
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private ParentCategoryDao parentCateDao = new ParentCategoryDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action.equals("addParentCategory")){
            addParentCategory(req, resp);
        };

    }
    private void addParentCategory (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String name = req.getParameter("namePC");
        System.out.println(name);
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setRepository(new File("D:/FutureOFMe/Project_Web_FreshFood/src/main/webapp"));
        ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
        String url = "";
        try {
            List<FileItem> fileItems = fileUpload.parseRequest(req);
            for(FileItem f : fileItems){
                if(!f.isFormField()){
                    if(f.getFieldName().equals("imgCate")){
                        File file = new File("D:/FutureOFMe/Project_Web_FreshFood/src/main/webapp/assets/images/categories/" + f.getName());
                        f.write(file);
                        url = "/assets/images/categories/" + f.getName();
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        parentCateDao.insert(new ParentCategory(name, url));
        req.getRequestDispatcher("/admin/danhMucCha.jsp").forward(req, resp);
    }

}
