package dal;

import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.HocSinh;
import model.Lop;
public class HocSinhDAO extends DBContext{
    public Vector<HocSinh> getAll(){
        String sql = "select * from HocSinh h join Lop l on h.MaLop = l.MaLop";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Vector<HocSinh> vector = new Vector<>();
            while (rs.next()) {
                Lop l = new Lop(rs.getInt(7), rs.getString(8));
                vector.add(new HocSinh(rs.getInt("MaHS"), rs.getNString("HoDem"), rs.getNString("Ten"), rs.getString("NgaySinh"), rs.getBoolean("GioiTinh"), l));
            }
            return vector;
        } catch (SQLException ex) {
            Logger.getLogger(HocSinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public HocSinh getHocSinhByMaHS(int MaHS){
        String sql = "select * from HocSinh h join Lop l on h.MaLop = l.MaLop where MaHS = " + MaHS;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Lop l = new Lop(rs.getInt(7), rs.getString(8));
                return new HocSinh(rs.getInt("MaHS"), rs.getNString("HoDem"), rs.getNString("Ten"), rs.getString("NgaySinh"), rs.getBoolean("GioiTinh"), l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HocSinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Vector<HocSinh> getHocSinhByMaLop(int MaLop){
        String sql = "select * from HocSinh h join Lop l on h.MaLop = l.MaLop where h.MaLop = " + MaLop;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Vector<HocSinh> vector = new Vector<>();
            while (rs.next()) {
                Lop l = new Lop(rs.getInt(7), rs.getString(8));
                vector.add(new HocSinh(rs.getInt("MaHS"), rs.getNString("HoDem"), rs.getNString("Ten"), rs.getString("NgaySinh"), rs.getBoolean("GioiTinh"), l));
            }
            return vector;
        } catch (SQLException ex) {
            Logger.getLogger(HocSinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Vector<HocSinh> getHocSinhByCondition(String sql){
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Vector<HocSinh> vector = new Vector<>();
            while (rs.next()) {
                Lop l = new Lop(rs.getInt(7), rs.getString(8));
                vector.add(new HocSinh(rs.getInt("MaHS"), rs.getNString("HoDem"), rs.getNString("Ten"), rs.getString("NgaySinh"), rs.getBoolean("GioiTinh"), l));
            }
            return vector;
        } catch (SQLException ex) {
            Logger.getLogger(HocSinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void add(String HoDem, String Ten, String NgaySinh, boolean GioiTinh, int MaLop){
        String sql = "INSERT INTO HocSinh values(?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setNString(1, HoDem);
            ps.setNString(2, Ten);
            ps.setString(3, NgaySinh);
            ps.setBoolean(4, GioiTinh);
            ps.setInt(5, MaLop);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HocSinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(HocSinh hs){
        String sql = "UPDATE HocSinh SET HoDem = ?,Ten = ?, NgaySinh = ?, GioiTinh = ?, MaLop = ? "
                + "where MaHS = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setNString(1, hs.getHoDem());
            ps.setNString(2, hs.getTen());
            ps.setString(3, hs.getNgaySinh());
            ps.setBoolean(4, hs.isGioiTinh());
            ps.setInt(5, hs.getLop().getMaLop());
            ps.setInt(6, hs.getMaHS());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HocSinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(int id){
        //xoá điểm trước
        DiemDAO ddao = new DiemDAO();
        ddao.deleteByHocSinh(id);
        //rồi mới xoá hs
        String sql = "DELETE FROM HocSinh where MaHS = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HocSinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}