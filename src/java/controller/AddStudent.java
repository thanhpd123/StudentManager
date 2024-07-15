package controller;

import dal.HocSinhDAO;
import dal.LopDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.Lop;

public class AddStudent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        LopDAO ldao = new LopDAO();
        Vector<Lop> dsLop = ldao.getData("SELECT * FROM Lop");
        request.setAttribute("dsLop", dsLop);
        request.getRequestDispatcher("jsp/addStudent.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String HoDem = request.getParameter("HoDem");
        String Ten = request.getParameter("Ten");
        String NgaySinh = request.getParameter("NgaySinh");
        boolean GioiTinh = Boolean.parseBoolean(request.getParameter("GioiTinh"));
        int MaLop = Integer.parseInt(request.getParameter("MaLop"));
        HocSinhDAO hsdao = new HocSinhDAO();
        hsdao.add(HoDem, Ten, NgaySinh, GioiTinh, MaLop);
        response.sendRedirect("ViewStudents");
    }
}