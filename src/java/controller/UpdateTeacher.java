package controller;

import dal.GiaoVienDAO;
import dal.MonHocDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.GiaoVien;
import model.MonHoc;

public class UpdateTeacher extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int MaGV = Integer.parseInt(request.getParameter("MaGV"));
            GiaoVienDAO gvdao = new GiaoVienDAO();
            MonHocDAO mhdao = new MonHocDAO();
            GiaoVien gv = gvdao.getGiaoVienByMaGV(MaGV);
            Vector<MonHoc> dsMonHoc = mhdao.getData("SELECT * FROM MonHoc");
            request.setAttribute("giaoVienCanTim", gv);
            request.setAttribute("monHocCanTim", gv.getMonHoc().getMaMonHoc());
            request.setAttribute("dsMonHoc", dsMonHoc);
        } catch (Exception e) {
            Logger.getLogger(UpdateStudent.class.getName()).log(Level.SEVERE, null, e);
        }
        request.getRequestDispatcher("jsp/updateTeacher.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int MaGV = Integer.parseInt(request.getParameter("MaGV"));
        String TenGV = request.getParameter("TenGV");
        String NgaySinh = request.getParameter("NgaySinh");
        boolean GioiTinh = Boolean.parseBoolean(request.getParameter("GioiTinh"));
        String MaMonHoc = request.getParameter("MaMonHoc");
        MonHocDAO mhdao = new MonHocDAO();
        MonHoc m = mhdao.getMonHocByMaMonHoc(MaMonHoc);
        GiaoVien gv = new GiaoVien(MaGV, TenGV,NgaySinh,GioiTinh, m);
        GiaoVienDAO gvdao = new GiaoVienDAO();
        gvdao.update(gv);
        response.sendRedirect("ViewTeachers");
    }
}