package model;

/**
 * Bản ghi đăng ký: liên kết SinhVien - MonHoc theo học kỳ & năm học.
 * Chỉ chứa các trường theo yêu cầu.
 */
public class DangKy {
    private SinhVien sinhVien;
    private MonHoc monHoc;
    private int hocKy;
    private int namHoc;

    public DangKy(SinhVien sinhVien, MonHoc monHoc, int hocKy, int namHoc) {
        this.sinhVien = sinhVien;
        this.monHoc = monHoc;
        this.hocKy = hocKy;
        this.namHoc = namHoc;
    }

    public SinhVien getSinhVien() { return sinhVien; }
    public MonHoc getMonHoc() { return monHoc; }
    public int getHocKy() { return hocKy; }
    public int getNamHoc() { return namHoc; }

    @Override
    public String toString() {
        return sinhVien.getMaSV() + " | " + monHoc.getMaMon() + " | HK" + hocKy + "-" + namHoc;
    }
}
