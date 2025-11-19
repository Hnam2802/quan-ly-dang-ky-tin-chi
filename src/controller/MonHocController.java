package controller;

import model.MonHoc;
import model.manager.MonHocManager;

import java.util.List;

/**
 * Controller trung gian giữa view và MonHocManager
 */
public class MonHocController {
    private final MonHocManager manager;

    public MonHocController(MonHocManager manager) { this.manager = manager; }

    public void addMonHoc(MonHoc m) { manager.add(m); }
    public void deleteMonHoc(String ma) { manager.delete(ma); }
    public List<MonHoc> getAll() { return manager.getAll(); }
    public MonHoc findByMa(String ma) { return manager.findByMa(ma); }
}
