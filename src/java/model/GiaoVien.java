package model;

public class GiaoVien {
    private int MaGV;
    private String TenGV;
    private String NgaySinh;
    private boolean GioiTinh;
    private MonHoc monHoc;

    public GiaoVien() {
    }

    public GiaoVien(int MaGV, String TenGV, String NgaySinh, boolean GioiTinh, MonHoc monHoc) {
        this.MaGV = MaGV;
        this.TenGV = TenGV;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.monHoc = monHoc;
    }

    public int getMaGV() {
        return MaGV;
    }

    public void setMaGV(int MaGV) {
        this.MaGV = MaGV;
    }

    public String getTenGV() {
        return TenGV;
    }

    public void setTenGV(String TenGV) {
        this.TenGV = TenGV;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public boolean isGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public MonHoc getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(MonHoc monHoc) {
        this.monHoc = monHoc;
    }
}
