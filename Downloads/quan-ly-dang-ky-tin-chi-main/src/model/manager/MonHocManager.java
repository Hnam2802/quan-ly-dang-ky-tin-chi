package model.manager;

import model.MonHoc;
import model.interfaces.IManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Manager cho MonHoc (in-memory)
 */
public class MonHocManager implements IManager<MonHoc> {
    private final List<MonHoc> data = new ArrayList<>();

    @Override
    public void add(MonHoc t) {
        // không kiểm tra trùng để đơn giản; controller có thể kiểm tra
        data.add(t);
    }

    @Override
    public void delete(String id) {
        data.removeIf(m -> m.getMaMon().equals(id));
    }

    @Override
    public List<MonHoc> getAll() {
        return new ArrayList<>(data);
    }

    public MonHoc findByMa(String ma) {
        return data.stream().filter(m -> m.getMaMon().equals(ma)).findFirst().orElse(null);
    }
}

