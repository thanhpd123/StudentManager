package controller;

import dal.HocSinhDAO;
import dal.LopDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.HocSinh;
import model.Lop;

public class UpdateStudent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int MaHS = Integer.parseInt(request.getParameter("MaHS"));
            HocSinhDAO hsdao = new HocSinhDAO();
            LopDAO ldao = new LopDAO();
            HocSinh hs = hsdao.getHocSinhByMaHS(MaHS);
            Vector<Lop> dsLop = ldao.getData("SELECT * FROM Lop");
            request.setAttribute("hocSinhCanTim", hs);
            request.setAttribute("lopCanTim", hs.getLop().getMaLop());
            request.setAttribute("dsLop", dsLop);
        } catch (Exception e) {
            Logger.getLogger(UpdateStudent.class.getName()).log(Level.SEVERE, null, e);
        }
        request.getRequestDispatcher("jsp/updateStudent.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int MaHS = Integer.parseInt(request.getParameter("MaHS"));
        String HoDem = request.getParameter("HoDem");
        String Ten = request.getParameter("Ten");
        String NgaySinh = request.getParameter("NgaySinh");
        boolean GioiTinh = Boolean.parseBoolean(request.getParameter("GioiTinh"));
        String MaLop = request.getParameter("MaLop");
        LopDAO ldao = new LopDAO();
        Lop l = ldao.getLopByMaLop(Integer.parseInt(MaLop));
        HocSinh hs = new HocSinh(MaHS, HoDem, Ten, NgaySinh, GioiTinh, l);
        HocSinhDAO hsdao = new HocSinhDAO();
        hsdao.update(hs);
        response.sendRedirect("ViewStudents");
    }
}
