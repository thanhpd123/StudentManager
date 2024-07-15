package model;

public class HocKi {
    private int MaHocKi;
    private int TenHocKi;

    public HocKi() {
    }

    public HocKi(int MaHocKi, int TenHocKi) {
        this.MaHocKi = MaHocKi;
        this.TenHocKi = TenHocKi;
    }

    public int getMaHocKi() {
        return MaHocKi;
    }

    public void setMaHocKi(int MaHocKi) {
        this.MaHocKi = MaHocKi;
    }

    public int getTenHocKi() {
        return TenHocKi;
    }

    public void setTenHocKi(int TenHocKi) {
        this.TenHocKi = TenHocKi;
    }
    
}
