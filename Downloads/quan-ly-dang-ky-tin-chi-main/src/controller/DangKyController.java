package controller;

import model.DangKy;
import model.manager.DangKyManager;

import java.util.List;

/**
 * Controller cho chức năng đăng ký
 */
public class DangKyController {
    private final DangKyManager manager;
    public DangKyController(DangKyManager manager) { this.manager = manager; }

    /**
     * Thực hiện đăng ký; trả về null nếu thành công, hoặc chuỗi mô tả lỗi nếu thất bại.
     */
    public String register(DangKy dk) { return manager.register(dk); }

    public boolean cancel(DangKy dk) { return manager.cancel(dk); }

    public List<DangKy> getAll() { return manager.getAll(); }
}
