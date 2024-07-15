
package controller;

import dal.GiaoVienDAO;
import dal.MonHocDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.GiaoVien;
import model.MonHoc;

public class ViewTeachers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        GiaoVienDAO gvdao = new GiaoVienDAO();
        MonHocDAO mhdao = new MonHocDAO();
        Vector<GiaoVien> dsGiaoVien = gvdao.getAll();
        Vector<MonHoc> dsMonHoc = mhdao.getData("SELECT * FROM MonHoc");
        request.setAttribute("dsGiaoVien",dsGiaoVien);
        request.setAttribute("dsMonHoc",dsMonHoc);
        request.getRequestDispatcher("jsp/teacherList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String giaoVienCanTim = request.getParameter("giaoVienCanTim");
        String monHocCanTim = request.getParameter("monHocCanTim");
        GiaoVienDAO gvdao = new GiaoVienDAO();
        MonHocDAO mhdao = new MonHocDAO();
        String sql = "select * from GiaoVien g join MonHoc m on g.MaMH = m.MaMH ";
        Vector<MonHoc> dsMonHoc = mhdao.getData("SELECT * FROM MonHoc");
        Vector<String> conditions = new Vector<>();
        if (!"".equals(giaoVienCanTim)) {
            conditions.add("g.TenGV like N'%" + giaoVienCanTim + "%'");
        }
        if (!"all".equals(monHocCanTim)) {
            conditions.add("m.MaMH = N'" + monHocCanTim + "'");
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
        Vector<GiaoVien> dsGiaoVien = gvdao.getGiaoVienByCondition(sql);
        request.setAttribute("dsGiaoVien", dsGiaoVien);
        request.setAttribute("dsMonHoc", dsMonHoc);
        request.setAttribute("giaoVienCanTim", giaoVienCanTim);
        request.setAttribute("monHocCanTim", monHocCanTim);
        request.getRequestDispatcher("jsp/teacherList.jsp").forward(request, response);
    }
}