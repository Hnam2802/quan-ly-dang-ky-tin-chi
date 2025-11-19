package controller;

import model.SinhVien;
import model.manager.SinhVienManager;

import java.util.List;

public class SinhVienController {
    private final SinhVienManager manager;
    public SinhVienController(SinhVienManager manager) { this.manager = manager; }

    public void addSV(SinhVien s) { manager.add(s); }
    public void deleteSV(String ma) { manager.delete(ma); }
    public List<SinhVien> getAll() { return manager.getAll(); }
    public SinhVien findByMa(String ma) { return manager.findByMa(ma); }
}
