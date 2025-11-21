package view;

import controller.SinhVienController;
import model.SinhVien;

import javax.swing.*;
import java.awt.*;

/**
 * Panel quản lý sinh viên: thêm/xóa/làm mới
 */
public class PanelSinhVien extends JPanel {
    private final SinhVienController controller;
    private final DefaultListModel<SinhVien> model = new DefaultListModel<>();
    private final JList<SinhVien> list = new JList<>(model);

    public PanelSinhVien(SinhVienController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(8,8));

        JPanel form = new JPanel(new GridLayout(4,2,5,5));
        JTextField tfMa = new JTextField();
        JTextField tfTen = new JTextField();
        JTextField tfLop = new JTextField();
        form.add(new JLabel("Mã SV:")); form.add(tfMa);
        form.add(new JLabel("Họ tên:")); form.add(tfTen);
        form.add(new JLabel("Lớp:")); form.add(tfLop);

        JButton btnAdd = new JButton("Thêm");
        JButton btnDelete = new JButton("Xóa");
        JButton btnRefresh = new JButton("Làm mới");
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnAdd); btnPanel.add(btnDelete); btnPanel.add(btnRefresh);

        add(form, BorderLayout.NORTH);
        add(new JScrollPane(list), BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> {
            String ma = tfMa.getText().trim();
            String ten = tfTen.getText().trim();
            String lop = tfLop.getText().trim();
            if (ma.isEmpty() || ten.isEmpty() || lop.isEmpty()) { JOptionPane.showMessageDialog(this,"Điền đủ thông tin"); return; }
            if (controller.findByMa(ma) != null) { JOptionPane.showMessageDialog(this,"Mã SV đã tồn tại"); return; }
            controller.addSV(new SinhVien(ma, ten, lop));
            refresh();
            tfMa.setText(""); tfTen.setText(""); tfLop.setText("");
        });

        btnDelete.addActionListener(e -> {
            SinhVien sel = list.getSelectedValue();
            if (sel == null) { JOptionPane.showMessageDialog(this,"Chọn SV để xóa"); return; }
            int c = JOptionPane.showConfirmDialog(this, "Xóa SV " + sel.getMaSV() + "?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (c == JOptionPane.YES_OPTION) {
                controller.deleteSV(sel.getMaSV());
                refresh();
            }
        });

        btnRefresh.addActionListener(e -> refresh());

        refresh();
    }

    public void refresh() {
        model.clear();
        for (SinhVien s : controller.getAll()) model.addElement(s);
    }
}
