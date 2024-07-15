package dal;

import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Lop;

public class LopDAO extends DBContext{
    public Vector<Lop> getData(String sql){
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Vector<Lop> vector = new Vector<>();
            while (rs.next()) {
                vector.add(new Lop(rs.getInt(1), rs.getString(2)));
            }
            return vector;
        } catch (SQLException ex) {
            Logger.getLogger(LopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Lop getLopByMaLop(int MaLop){
        String sql = "SELECT * FROM Lop WHERE MaLop = " + MaLop;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Lop(rs.getInt(1),rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String getTenLopByMaHS(String MaHS){
        String sql = "SELECT TenLop FROM Lop WHERE MaLop = (select MaLop FROM HocSinh WHERE MaHS = ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, MaHS);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void add(Lop l){
        String sql = "INSERT INTO Lop VALUES ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, l.getTenLop());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(Lop l){
        String sql = "UPDATE Lop SET TenLop = ? WHERE MaLop = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, l.getTenLop());
            ps.setInt(2, l.getMaLop());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(int id){
        String sql = "DELETE FROM Lop WHERE MaLop = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
