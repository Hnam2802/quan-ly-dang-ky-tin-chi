package model;

/**
 * Lớp đại diện cho một Lớp học trong hệ thống
 */
public class LopHoc {
    private String maLop;   // Mã lớp (chuỗi)
    private String tenLop;  // Tên lớp

    public LopHoc(String maLop, String tenLop) {
        this.maLop = maLop;
        this.tenLop = tenLop;
    }

    public String getMaLop() {
        return maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    @Override
    public String toString() {
        return maLop + " - " + tenLop;
    }
}
