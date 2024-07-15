package model;

public class MonHoc {
    private String MaMonHoc,TenMonHoc;

    public MonHoc() {
    }

    public MonHoc(String MaMonHoc, String TenMonHoc) {
        this.MaMonHoc = MaMonHoc;
        this.TenMonHoc = TenMonHoc;
    }

    public String getMaMonHoc() {
        return MaMonHoc;
    }

    public void setMaMonHoc(String MaMonHoc) {
        this.MaMonHoc = MaMonHoc;
    }

    public String getTenMonHoc() {
        return TenMonHoc;
    }

    public void setTenMonHoc(String TenMonHoc) {
        this.TenMonHoc = TenMonHoc;
    }
}
