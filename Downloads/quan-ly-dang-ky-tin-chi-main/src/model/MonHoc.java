package model;

/**
 * Môn học: chỉ chứa các thuộc tính theo yêu cầu
 * - maMon: mã môn (3 chữ số)
 * - tenMon: tên môn
 * - tongSoTiet: tổng số tiết
 * - loaiMon: loại môn (DAI_CUONG, CO_SO_NGANH, CN_BAT_BUOC, CN_TU_CHON)
 */
public class MonHoc {
    private String maMon;
    private String tenMon;
    private int tongSoTiet;
    private String loaiMon;

    public MonHoc(String maMon, String tenMon, int tongSoTiet, String loaiMon) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.tongSoTiet = tongSoTiet;
        this.loaiMon = loaiMon;
    }

    // Getters
    public String getMaMon() { return maMon; }
    public String getTenMon() { return tenMon; }
    public int getTongSoTiet() { return tongSoTiet; }
    public String getLoaiMon() { return loaiMon; }

    @Override
    public String toString() {
        return maMon + " - " + tenMon + " (" + tongSoTiet + " tiết)";
    }
}
