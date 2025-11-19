package controller;

import model.LopHoc;
import model.manager.LopHocManager;

import java.util.List;

public class LopHocController {
    private final LopHocManager manager;
    public LopHocController(LopHocManager manager) { this.manager = manager; }

    public void addLop(String ma, String ten) { manager.addLop(ma, ten); }
    public void deleteLop(String ma) { manager.deleteLop(ma); }
    public List<LopHoc> getAll() { return manager.getAll(); }
    public LopHoc findByMa(String ma) { return manager.findByMa(ma); }
}
