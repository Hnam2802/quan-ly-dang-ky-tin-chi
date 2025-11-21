package model.manager;

import model.SinhVien;
import model.interfaces.IManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Manager cho SinhVien
 */
public class SinhVienManager implements IManager<SinhVien> {
    private final List<SinhVien> data = new ArrayList<>();

    @Override
    public void add(SinhVien t) {
        data.add(t);
    }

    @Override
    public void delete(String id) {
        data.removeIf(s -> s.getMaSV().equals(id));
    }

    @Override
    public List<SinhVien> getAll() {
        return new ArrayList<>(data);
    }

    public SinhVien findByMa(String ma) {
        return data.stream().filter(s -> s.getMaSV().equals(ma)).findFirst().orElse(null);
    }
}
