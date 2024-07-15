package controller;

import dal.HocSinhDAO;
import dal.LopDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.*;
import model.HocSinh;
import model.Lop;

public class ViewStudents extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HocSinhDAO hsdao = new HocSinhDAO();
        Vector<HocSinh> dsHocSinh = new Vector<>();
        HttpSession session = request.getSession();
        if (session.getAttribute("role") != null) {
            String role = (String) session.getAttribute("role");
            if (role.equals("student")) {
                String studentID = (String) session.getAttribute("studentID");
                dsHocSinh = hsdao.getHocSinhByCondition("select * from HocSinh h join Lop l on h.MaLop = l.MaLop WHERE h.MaLop = (\n"
                        + "select MaLop FROM HocSinh WHERE MaHS = " + studentID + ")");
                LopDAO ldao = new LopDAO();
                request.setAttribute("lopCanTim", ldao.getTenLopByMaHS(studentID));
                request.setAttribute("dsHocSinh", dsHocSinh);
                request.getRequestDispatcher("jsp/studentListOnly.jsp").forward(request, response);
            } else {
                dsHocSinh = hsdao.getAll();
                LopDAO ldao = new LopDAO();
                Vector<Lop> dsLop = ldao.getData("SELECT * FROM Lop");
                request.setAttribute("dsLop", dsLop);
                request.setAttribute("dsHocSinh", dsHocSinh);
                request.getRequestDispatcher("jsp/studentListAll.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("jsp/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String hocSinhCanTim = request.getParameter("hocSinhCanTim");
        String lopCanTim = request.getParameter("lopCanTim");
        String sapXep = request.getParameter("sapXep");
        HocSinhDAO hsdao = new HocSinhDAO();
        LopDAO ldao = new LopDAO();
        String sql = "select * from HocSinh h join Lop l on h.MaLop = l.MaLop ";
        Vector<Lop> dsLop = ldao.getData("SELECT * FROM Lop");
        Vector<String> conditions = new Vector<>();
        if (!"".equals(hocSinhCanTim)) {
            conditions.add("h.HoDem + ' ' + h.Ten like N'%" + hocSinhCanTim + "%'");
        }
        if (!"all".equals(lopCanTim)) {
            conditions.add("h.MaLop = N'" + lopCanTim + "'");
        }
        if (!conditions.isEmpty()) {
            sql += "WHERE ";
            for (int i = 0; i < conditions.size(); i++) {
                if (i == 0) {
                    sql += conditions.get(i);
                } else {
                    sql += " AND " + conditions.get(i);
                }
            }
        }
        Vector<HocSinh> dsHocSinh = hsdao.getHocSinhByCondition(sql);
        if (sapXep != null) {
            Collections.sort(dsHocSinh, new Comparator<HocSinh>() {
                @Override
                public int compare(HocSinh hs1, HocSinh hs2) {
                    return hs1.getTen().compareTo(hs2.getTen());
                }
            });
        }
        request.setAttribute("dsHocSinh", dsHocSinh);
        request.setAttribute("dsLop", dsLop);
        request.setAttribute("hocSinhCanTim", hocSinhCanTim);
        request.setAttribute("lopCanTim", lopCanTim);
        request.setAttribute("sapXep", sapXep);
        request.getRequestDispatcher("jsp/studentListAll.jsp").forward(request, response);
    }
}
