package view;

import controller.DangKyController;
import model.DangKy;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.List;

/**
 * Panel đơn giản để sắp xếp danh sách đăng ký theo Sinh viên hoặc Môn học
 */
public class PanelSapXep extends JPanel {
    private final DangKyController controller;
    private final DefaultListModel<DangKy> model = new DefaultListModel<>();
    private final JList<DangKy> list = new JList<>(model);

    public PanelSapXep(DangKyController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(8,8));

        JPanel top = new JPanel();
        JButton btnBySV = new JButton("Sắp xếp theo Mã SV");
        JButton btnByMH = new JButton("Sắp xếp theo Mã Môn");
        JButton btnRefresh = new JButton("Làm mới");
        top.add(btnBySV); top.add(btnByMH); top.add(btnRefresh);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(list), BorderLayout.CENTER);

        btnBySV.addActionListener(e -> sortBySV());
        btnByMH.addActionListener(e -> sortByMH());
        btnRefresh.addActionListener(e -> refresh());

        refresh();
    }

    private void refresh() {
        model.clear();
        List<DangKy> all = controller.getAll();
        for (DangKy d : all) model.addElement(d);
    }

    private void sortBySV() {
        model.clear();
        List<DangKy> all = controller.getAll();
        all.sort(Comparator.comparing(d -> d.getSinhVien().getMaSV()));
        for (DangKy d : all) model.addElement(d);
    }

    private void sortByMH() {
        model.clear();
        List<DangKy> all = controller.getAll();
        all.sort(Comparator.comparing(d -> d.getMonHoc().getMaMon()));
        for (DangKy d : all) model.addElement(d);
    }
}
