package dal;

import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.MonHoc;

public class MonHocDAO extends DBContext{
    public Vector<MonHoc> getData(String sql){
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Vector<MonHoc> vector = new Vector<>();
            while (rs.next()) {
                vector.add(new MonHoc(rs.getString(1), rs.getNString(2)));
            }
            return vector;
        } catch (SQLException ex) {
            Logger.getLogger(LopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public MonHoc getMonHocByMaMonHoc(String MaMonHoc){
        String sql = "SELECT * FROM MonHoc WHERE MaMH = '" + MaMonHoc + "'";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new MonHoc(rs.getString(1),rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void add(MonHoc m){
        String sql = "INSERT INTO MonHoc VALUES (?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, m.getTenMonHoc());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(MonHoc l){
        String sql = "UPDATE MonHoc SET TenMH = ? WHERE MaMH = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setNString(1, l.getTenMonHoc());
            ps.setString(2, l.getMaMonHoc());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(int id){
        String sql = "DELETE FROM MonHoc WHERE MaMH = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}