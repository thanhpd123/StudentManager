package controller;

import dal.DangKyThiDAO;
import dal.HocKiDAO;
import dal.HocSinhDAO;
import dal.MonHocDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import model.DangKyThi;

public class ViewCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String MaMonHoc = request.getParameter("MaMonHoc");
        String MaHK = request.getParameter("MaHK");
        String MaMH_HK = null;
        if (MaMonHoc != null) {
            MaMH_HK = MaMonHoc + MaHK;
        }

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
        request.getRequestDispatcher("jsp/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        if (role.equals("student")) {
            String maHS_raw = (String) session.getAttribute("studentID");
            int ma_HS = Integer.parseInt(maHS_raw);

            Vector<String> toRemove = new Vector<>();
            java.util.Enumeration em = session.getAttributeNames();
            while (em.hasMoreElements()) {
                String id = em.nextElement().toString();
                if (!id.equals("studentID") && !id.equals("role") && !id.equals("account")) {
                    DangKyThiDAO dktdao = new DangKyThiDAO();
                    DangKyThi dkt = new DangKyThi(ma_HS, id, Integer.parseInt(session.getAttribute(id).toString()));
                    dktdao.addThongTin(dkt);
                    toRemove.add(id);
                }
            }
            for (String string : toRemove) {
                session.removeAttribute(string);
            }
            response.sendRedirect("ViewStudents");
        } else {
            response.sendRedirect("jsp/error.jsp");
        }
    }
}
