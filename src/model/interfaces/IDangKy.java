package model.interfaces;

import model.DangKy;
import java.util.List;

/**
 * Interface hành vi đăng ký
 */
public interface IDangKy {
    /**
     * Thực hiện đăng ký; trả về null nếu thành công, hoặc chuỗi mô tả lỗi nếu thất bại.
     */
    String register(DangKy dk);

    /**
     * Hủy đăng ký (theo điều kiện): trả về true nếu hủy thành công.
     */
    boolean cancel(DangKy dk);

    List<DangKy> getAll();
}
