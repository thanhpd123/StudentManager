package controller;

import dal.GiaoVienDAO;
import dal.MonHocDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.MonHoc;

public class AddTeacher extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        MonHocDAO mhdao = new MonHocDAO();
        Vector<MonHoc> dsMonHoc = mhdao.getData("SELECT * FROM MonHoc");
        request.setAttribute("dsMonHoc", dsMonHoc);
        request.getRequestDispatcher("jsp/addTeacher.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String TenGV = request.getParameter("TenGV");
        String NgaySinh = request.getParameter("NgaySinh");
        boolean GioiTinh = Boolean.parseBoolean(request.getParameter("GioiTinh"));
        String MaMonHoc = request.getParameter("MaMonHoc");
        GiaoVienDAO gvdao = new GiaoVienDAO();
        gvdao.add(TenGV,NgaySinh,GioiTinh, MaMonHoc);
        response.sendRedirect("ViewTeachers");
    }
}