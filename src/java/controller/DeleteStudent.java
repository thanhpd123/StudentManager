package controller;

import dal.HocSinhDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteStudent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            int MaHS = Integer.parseInt(request.getParameter("MaHS"));
            HocSinhDAO sdao = new HocSinhDAO();
            sdao.delete(MaHS);
            response.sendRedirect("ViewStudents");
        } catch (Exception e) {
            Logger.getLogger(DeleteStudent.class.getName()).log(Level.SEVERE, null, e);
        }
    } 
}