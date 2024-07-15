package controller;

import dal.DangKyThiDAO;
import dal.HocKiDAO;
import dal.MonHocDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import model.HocKi;
import model.MonHoc;

public class RegisterCourse extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        //Mã học kì
        int MaHK;
        try {
            MaHK = Integer.parseInt(request.getParameter("MaHK"));
        } catch (Exception e) {
            MaHK = 1;
        }
        request.setAttribute("MaHK", MaHK);
        //Danh sách học kì
        HocKiDAO hkdao = new HocKiDAO();
        Vector<HocKi> dsHocKi = hkdao.getAll();
        request.setAttribute("dsHocKi", dsHocKi);

        //Danh sách môn học
        MonHocDAO mhdao = new MonHocDAO();
        Vector<MonHoc> dsMonHoc = mhdao.getData("SELECT * FROM MonHoc");
        request.setAttribute("dsMonHoc", dsMonHoc);

        //Mã môn thêm vào session
        String MaMonHoc = request.getParameter("MaMonHoc");
        String MaMH_HK = null;
        if (MaMonHoc != null) {
            MaMH_HK = MaMonHoc + MaHK;
        }

        //Check xem đã đăng ký chưa
        int MaHS = Integer.parseInt(session.getAttribute("studentID").toString());
        DangKyThiDAO dktdao = new DangKyThiDAO();
        for (MonHoc mh : dsMonHoc) {
            if (dktdao.daDangKy(MaHS, mh.getMaMonHoc() + MaHK)) {
                request.setAttribute("thongbao"+mh.getMaMonHoc(), "Đã đăng ký");
            }
        }

        //Thêm hay xoá
        String dichVu = request.getParameter("dichVu");
        if (MaMH_HK != null && dichVu != null) {
            if (dichVu.equals("them")) {
                if (session.getAttribute(MaMH_HK) != null) {
                    int SoLuong = Integer.parseInt(session.getAttribute(MaMH_HK).toString()) + 1;
                    session.setAttribute(MaMH_HK, SoLuong);
                } else {
                    session.setAttribute(MaMH_HK, 1);
                }
                request.setAttribute("message", "<p style='color:green'>Đã thêm môn học vào kỳ</p>");
            } else if (dichVu.equals("xoa")) {
                if (session.getAttribute(MaMH_HK) != null) {
                    int SoLuong = Integer.parseInt(session.getAttribute(MaMH_HK).toString()) - 1;
                    if (SoLuong > 0) {
                        session.setAttribute(MaMH_HK, SoLuong);
                    } else {
                        session.removeAttribute(MaMH_HK);
                    }
                    request.setAttribute("message", "<p style='color:orange'>Đã xoá môn học vào kỳ</p>");
                } else {
                    request.setAttribute("message", "<p style='color:red'>Bạn chưa thêm môn học vào kỳ</p>");
                }
            }
        }
        request.getRequestDispatcher("jsp/registerCourse.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
