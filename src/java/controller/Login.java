package controller;

import dal.GiaoVienDAO;
import dal.HocSinhDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.GiaoVien;
import model.HocSinh;

public class Login extends HttpServlet {

    //logout
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("account") != null) {
            session.invalidate();
        }
        request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("user");
        try {
            if (user.substring(0, 2).equalsIgnoreCase("HS")) {
                int studentUserInt = Integer.parseInt(user.substring(2));
                HocSinhDAO hsdao = new HocSinhDAO();
                HocSinh hs = hsdao.getHocSinhByMaHS(studentUserInt);
                if (hs != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("account", hs.getHoDem() + " " + hs.getTen());
                    session.setAttribute("studentID", String.valueOf(hs.getMaHS()));
                    session.setAttribute("role", "student");
                    response.sendRedirect("ViewStudents");
                } else {
                    request.setAttribute("error", "Không tìm thấy. Vui lòng thử lại");
                    response.sendRedirect("ViewStudents");
                }
            } else if (user.equals("admin")) {
                HttpSession session = request.getSession();
                session.setAttribute("account", "admin");
                session.setAttribute("role", "admin");
                response.sendRedirect("ViewStudents");
            } else if (user.substring(0, 2).equalsIgnoreCase("GV")){
                int teacherUserInt = Integer.parseInt(user.substring(2));
                GiaoVienDAO gvdao = new GiaoVienDAO();
                GiaoVien gv = gvdao.getGiaoVienByMaGV(teacherUserInt);
                if (gv != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("account", gv.getTenGV());
                    session.setAttribute("role", "teacher");
                    response.sendRedirect("ViewStudents");
                } else {
                    request.setAttribute("error", "Không tìm thấy. Vui lòng thử lại");
                    request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
                }
            } else{
                throw new Exception();
            }
        } catch (Exception e) {
            request.setAttribute("error", "SAI CÚ PHÁP. VUI LÒNG THỬ LẠI!");
            request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
        }
    }
}