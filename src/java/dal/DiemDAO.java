package dal;

import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Diem;
import model.HocSinh;

public class DiemDAO extends DBContext {

    public Diem layDiemChoHS(int MaHS, String MaMH_HK) {
        String sql = "SELECT * FROM Diem WHERE MaHS = ? AND MaMH_HK = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, MaHS);
            ps.setString(2, MaMH_HK);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Diem(MaHS, MaMH_HK, rs.getFloat(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DiemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ResultSet layDiemChoGV(String MaMonHoc, int MaHocKi, int MaLop) {
        String sql = "SELECT h.MaHS, h.HoDem, h.Ten, h.NgaySinh, iif(d.Diem is null,-1.0,d.Diem) as Diem FROM Diem d\n"
                + "JOIN HocSinh h ON d.MaHS = h.MaHS\n"
                + "JOIN MonHoc_HocKi mhhk ON d.MaMH_HK = mhhk.MaMH_HK\n"
                + "JOIN Lop l ON h.MaLop = l.MaLop\n"
                + "where mhhk.MaMH_HK = (select MaMH_HK from MonHoc_HocKi where MaMH_HK = \n"
                + "CONCAT(?,?))\n"
                + "and l.MaLop = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, MaMonHoc);
            ps.setInt(2, MaHocKi);
            ps.setInt(3, MaLop);
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(DiemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ResultSet layDiemChoHS(int MaHS, int MaHocKi) {
        String sql = "select MaHS,MaMH_HK,iif(Diem is null,-1.0,Diem) as DiemHocKy from Diem WHERE MaHS = " + MaHS + " AND MaMH_HK like '_" + MaHocKi + "'";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(DiemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void deleteByHocSinh(int MaHS) {
        String sql = "DELETE Diem WHERE MaHS = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, MaHS);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DiemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Vector<HocSinh> dsHocSinh, String MonHoc_HocKi, Vector<Float> dsDiem) {
        String sql = "UPDATE Diem SET Diem = CASE MaHS";
        for (int i = 0; i < dsDiem.size(); i++) {
            sql += " WHEN " + dsHocSinh.get(i).getMaHS() + " THEN " + dsDiem.get(i);
        }
        sql += " END WHERE MaMH_HK = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, MonHoc_HocKi);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DiemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
