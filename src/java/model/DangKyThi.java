package model;

public class DangKyThi {
    private int MaHS;
    private String MaMH_HK;
    private int SoLuong;

    public DangKyThi() {
    }

    public DangKyThi(int MaHS, String MaMH_HK, int SoLuong) {
        this.MaHS = MaHS;
        this.MaMH_HK = MaMH_HK;
        this.SoLuong = SoLuong;
    }

    public int getMaHS() {
        return MaHS;
    }

    public void setMaHS(int MaHS) {
        this.MaHS = MaHS;
    }

    public String getMaMH_HK() {
        return MaMH_HK;
    }

    public void setMaMH_HK(String MaMH_HK) {
        this.MaMH_HK = MaMH_HK;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    
}
