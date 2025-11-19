package model;

/**
 * Sinh viên: giữ đúng thuộc tính yêu cầu
 * - maSV, hoTen, lop
 */
public class SinhVien {
    private String maSV;
    private String hoTen;
    private String lop;

    public SinhVien(String maSV, String hoTen, String lop) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.lop = lop;
    }

    public String getMaSV() { return maSV; }
    public String getHoTen() { return hoTen; }
    public String getLop() { return lop; }

    @Override
    public String toString() {
        return maSV + " - " + hoTen;
    }
}
