package controller;

import dal.DiemDAO;
import dal.HocKiDAO;
import dal.HocSinhDAO;
import dal.LopDAO;
import dal.MonHocDAO;
import java.sql.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import model.HocKi;
import model.HocSinh;
import model.Lop;
import model.MonHoc;

public class ViewMarkList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        if (role == null) {
            response.sendRedirect("jsp/error.jsp");
        } else if (!role.equals("student")) {
            MonHocDAO mhdao = new MonHocDAO();
            LopDAO ldao = new LopDAO();
            HocKiDAO hkdao = new HocKiDAO();
            Vector<Lop> dsLop = ldao.getData("SELECT * FROM Lop");
            Vector<MonHoc> dsMonHoc = mhdao.getData("SELECT * FROM MonHoc");
            Vector<HocKi> dsHocKi = hkdao.getAll();
            request.setAttribute("dsLop", dsLop);
            request.setAttribute("dsMonHoc", dsMonHoc);
            request.setAttribute("dsHocKi", dsHocKi);
            String lopCanTim_raw = request.getParameter("lopCanTim");
            String monHocCanTim = request.getParameter("monHocCanTim");
            String hocKiCanTim_raw = request.getParameter("hocKiCanTim");
            if (lopCanTim_raw != null && monHocCanTim != null && hocKiCanTim_raw != null) {
                int lopCanTim = Integer.parseInt(lopCanTim_raw);
                int hocKiCanTim = Integer.parseInt(hocKiCanTim_raw);
                DiemDAO ddao = new DiemDAO();
                ResultSet rs = ddao.layDiemChoGV(monHocCanTim, hocKiCanTim, lopCanTim);
                request.setAttribute("dsDiem", rs);
                request.setAttribute("lopCanTim", lopCanTim);
                request.setAttribute("monHocCanTim", monHocCanTim);
                request.setAttribute("hocKiCanTim", hocKiCanTim);
            }
            if (role.equals("teacher")) {
                request.getRequestDispatcher("jsp/viewMarkForTeacher.jsp").forward(request, response);
            } else if (role.equals("admin")) {
                request.getRequestDispatcher("jsp/viewMarkForAdmin.jsp").forward(request, response);
            }
        } else {
            String maHS_raw = (String) session.getAttribute("studentID");
            int ma_HS = Integer.parseInt(maHS_raw);
            HocKiDAO hkdao = new HocKiDAO();
            Vector<HocKi> dsHocKi = hkdao.getAll();
            MonHocDAO mhdao = new MonHocDAO();
            Vector<MonHoc> dsMonHoc = mhdao.getData("SELECT * FROM MonHoc");
            request.setAttribute("dsMonHoc", dsMonHoc);
            request.setAttribute("dsHocKi", dsHocKi);
            String hocKiCanTim_raw = request.getParameter("hocKiCanTim");
            if (hocKiCanTim_raw != null) {
                int hocKiCanTim = Integer.parseInt(hocKiCanTim_raw);
                DiemDAO ddao = new DiemDAO();
                ResultSet rs = ddao.layDiemChoHS(ma_HS, hocKiCanTim);
                request.setAttribute("dsDiem", rs);
                request.setAttribute("hocKiCanTim", hocKiCanTim);
            }
            request.getRequestDispatcher("jsp/viewMarkForStudent.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String lopCanTim = request.getParameter("lopCanTim");
        String monHoc_HocKi = request.getParameter("monHocCanTim") + request.getParameter("hocKiCanTim");
        HocSinhDAO hsdao = new HocSinhDAO();
        Vector<HocSinh> dsHocSinh = hsdao.getHocSinhByMaLop(Integer.parseInt(lopCanTim));
        Vector<Float> dsDiem = new Vector<>();
        try {
            for (HocSinh hs : dsHocSinh) {
                dsDiem.add(Float.parseFloat(request.getParameter("" + hs.getMaHS())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DiemDAO ddao = new DiemDAO();
        ddao.update(dsHocSinh, monHoc_HocKi, dsDiem);
        doGet(request, response);
    }
}
