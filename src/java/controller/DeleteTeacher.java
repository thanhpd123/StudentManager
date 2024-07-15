package controller;

import dal.GiaoVienDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteTeacher extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        if (request.getSession().getAttribute("role")==null) {
            response.sendRedirect("jsp/error.jsp");
            return;
        }
        try {
            int MaGV = Integer.parseInt(request.getParameter("MaGV"));
            GiaoVienDAO gvdao = new GiaoVienDAO();
            gvdao.delete(MaGV);
            response.sendRedirect("ViewTeachers");
        } catch (Exception e) {
            Logger.getLogger(DeleteStudent.class.getName()).log(Level.SEVERE, null, e);
        }
    } 
}