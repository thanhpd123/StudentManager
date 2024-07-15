package dal;

import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.GiaoVien;
import model.MonHoc;
public class GiaoVienDAO extends DBContext{
    public Vector<GiaoVien> getAll(){
        String sql = "select * from GiaoVien g join MonHoc m on g.MaMH = m.MaMH";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Vector<GiaoVien> vector = new Vector<>();
            while (rs.next()) {
                MonHoc m = new MonHoc(rs.getString(6), rs.getNString(7));
                vector.add(new GiaoVien(rs.getInt(1), rs.getNString(2),rs.getString(3),rs.getBoolean(4), m));
            }
            return vector;
        } catch (SQLException ex) {
            Logger.getLogger(HocSinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public GiaoVien getGiaoVienByMaGV(int MaGV){
        String sql = "select * from GiaoVien g join MonHoc m on g.MaMH = m.MaMH where g.MaGV = " + MaGV;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                MonHoc m = new MonHoc(rs.getString(6), rs.getNString(7));
                return new GiaoVien(rs.getInt(1), rs.getNString(2),rs.getString(3),rs.getBoolean(4), m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HocSinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Vector<GiaoVien> getGiaoVienByCondition(String sql){
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Vector<GiaoVien> vector = new Vector<>();
            while (rs.next()) {
                MonHoc m = new MonHoc(rs.getString(6), rs.getNString(7));
                vector.add(new GiaoVien(rs.getInt(1), rs.getNString(2),rs.getString(3),rs.getBoolean(4), m));
            }
            return vector;
        } catch (SQLException ex) {
            Logger.getLogger(HocSinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void add(String TenGV,String NgaySinh,boolean GioiTinh, String MaMonHoc){
        String sql = "INSERT INTO GiaoVien values(?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setNString(1, TenGV);
            ps.setString(2, NgaySinh);
            ps.setBoolean(3, GioiTinh);
            ps.setString(4, MaMonHoc);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HocSinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(GiaoVien gv){
        String sql = "UPDATE GiaoVien SET TenGV = ?,NgaySinh = ?, GioiTinh = ?, MaMH = ? WHERE MaGV = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setNString(1, gv.getTenGV());
            ps.setString(2, gv.getNgaySinh());
            ps.setBoolean(3, gv.isGioiTinh());
            ps.setString(4, gv.getMonHoc().getMaMonHoc());
            ps.setInt(5, gv.getMaGV());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HocSinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(int id){
        String sql = "DELETE FROM GiaoVien where MaGV = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HocSinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}