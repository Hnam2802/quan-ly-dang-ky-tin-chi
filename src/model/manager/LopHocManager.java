package model.manager;

import model.LopHoc;
import model.interfaces.ILopHoc;

import java.util.ArrayList;
import java.util.List;

/**
 * Manager cho LopHoc (rất tối giản)
 */
public class LopHocManager implements ILopHoc {
    private final List<LopHoc> data = new ArrayList<>();

    @Override
    public void addLop(String maLop, String tenLop) {
        data.add(new LopHoc(maLop, tenLop));
    }

    @Override
    public void deleteLop(String maLop) {
        data.removeIf(l -> l.getMaLop().equals(maLop));
    }

    public List<LopHoc> getAll() {
        return new ArrayList<>(data);
    }

    public LopHoc findByMa(String ma) {
        return data.stream().filter(l -> l.getMaLop().equals(ma)).findFirst().orElse(null);
    }
}
