package model.interfaces;

import model.DangKy;
import java.util.List;

/**
 * Sắp xếp danh sách đăng ký theo các tiêu chí đơn giản
 */
public interface ISortDangKy {
    List<DangKy> sortBySinhVien(List<DangKy> list);
    List<DangKy> sortByMonHoc(List<DangKy> list);
}
