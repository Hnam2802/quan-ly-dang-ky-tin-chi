package view;

import controller.DangKyController;
import controller.SortController;
import model.DangKy;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.List;

/**
 * Panel đơn giản để sắp xếp danh sách đăng ký theo Sinh viên hoặc Môn học
 */
public class PanelSapXep extends JPanel {
    private final SortController sortController;
    private final DangKyController dkController;
    private final DefaultListModel<DangKy> model = new DefaultListModel<>();
    private final JList<DangKy> list = new JList<>(model);

    public PanelSapXep(SortController sortController, DangKyController dkController) {
        this.sortController = sortController;
        this.dkController = dkController;
        setLayout(new BorderLayout(8,8));

        JPanel top = new JPanel();
        JButton btnBySV = new JButton("Sắp xếp theo Mã SV");
        JButton btnByMH = new JButton("Sắp xếp theo thời gian ĐK");
        JButton btnRefresh = new JButton("Làm mới");
        top.add(btnBySV); top.add(btnByMH); top.add(btnRefresh);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(list), BorderLayout.CENTER);

        btnBySV.addActionListener(e -> sortBySV());
        btnByMH.addActionListener(e -> sortByTG());
        btnRefresh.addActionListener(e -> refresh());

        refresh();
    }

    private void refresh() {
        model.clear();
        List<DangKy> all = dkController.getAll();
        for (DangKy d : all) model.addElement(d);
    }

    private void sortBySV() {
        model.clear();
        List<DangKy> sortedList = sortController.sortBySV();
        for (DangKy d : sortedList) model.addElement(d);
    }

    private void sortByTG() {
        model.clear();
        List<DangKy> sortedList = sortController.sortByTG();
        for (DangKy d : sortedList) model.addElement(d);
    }
}
