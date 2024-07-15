package model;

public class Diem {
    private int MaHS;
    private String MaMH_HK;
    private Float Diem;

    public Diem() {
    }

    public Diem(int MaHS, String MaMH_HK, Float Diem) {
        this.MaHS = MaHS;
        this.MaMH_HK = MaMH_HK;
        this.Diem = Diem;
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

    public Float getDiem() {
        return Diem;
    }

    public void setDiem(Float Diem) {
        this.Diem = Diem;
    }
}