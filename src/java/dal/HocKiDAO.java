package dal;

import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.HocKi;

public class HocKiDAO extends DBContext{
    public Vector<HocKi> getAll(){
        String sql = "SELECT * FROM HocKi";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Vector<HocKi> vector = new Vector<>();
            while (rs.next()) {
                vector.add(new HocKi(rs.getInt(1), rs.getInt(2)));
            }
            return vector;
        } catch (SQLException ex) {
            Logger.getLogger(HocKiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
