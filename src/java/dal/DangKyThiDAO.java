package dal;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DangKyThi;

public class DangKyThiDAO extends DBContext {

    public void addThongTin(DangKyThi dkt) {
        String sql = "INSERT INTO DangKyThi VALUES (?,?,?)";
        int SoLuong = getSoLuong(dkt.getMaHS(), dkt.getMaMH_HK());
        if (SoLuong > 0) {
            updateSoLuong(dkt.getSoLuong(), dkt.getMaHS(), dkt.getMaMH_HK());
            return;
        }
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, dkt.getMaHS());
            ps.setString(2, dkt.getMaMH_HK());
            ps.setInt(3, dkt.getSoLuong() + getSoLuong(dkt.getMaHS(), dkt.getMaMH_HK()));
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DangKyThiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getSoLuong(int MaHS, String MaMH_HK) {
        String sql = "select SoLuong from DangKyThi where MaHS = ? and MaMH_HK = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, MaHS);
            ps.setString(2, MaMH_HK);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DangKyThiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void updateSoLuong(int SoLuong, int MaHS, String MaMH_HK) {
        String sql = "UPDATE DangKyThi SET SoLuong = ? WHERE MaHS = ? AND MaMH_HK = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, SoLuong + getSoLuong(MaHS, MaMH_HK));
            ps.setInt(2, MaHS);
            ps.setString(3, MaMH_HK);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DangKyThiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean daDangKy(int MaHS, String MaMH_HK){
        String sql = "select MaHS,MaMH_HK from DangKyThi where MaHS = ? and MaMH_HK = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, MaHS);
            ps.setString(2, MaMH_HK);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DangKyThiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
