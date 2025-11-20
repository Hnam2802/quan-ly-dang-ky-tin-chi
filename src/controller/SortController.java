package controller;

import model.DangKy;
import model.manager.DangKyManager;
import model.manager.SortDangKyManager;

import java.util.List;

/**
 * Controller cho chức năng sắp xếp
 */
public class SortController {
    private final SortDangKyManager sortService;
    
    public SortController(DangKyManager dkManager) {
        this.sortService = new SortDangKyManager(dkManager);
    }
    
    public List<DangKy> sortBySV() {
        return sortService.sortBySinhVien(null);
    }
    
    public List<DangKy> sortByTG() {
        return sortService.sortByThoiGian(null);
    }
}
