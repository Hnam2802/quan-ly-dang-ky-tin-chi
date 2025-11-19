package view;

import controller.MonHocController;
import model.MonHoc;

import javax.swing.*;
import java.awt.*;

/**
 * Panel đơn giản để thêm/xóa/view Môn học
 */
public class PanelMonHoc extends JPanel {
    private final MonHocController controller;
    private final DefaultListModel<MonHoc> listModel = new DefaultListModel<>();
    private final JList<MonHoc> jList = new JList<>(listModel);

    public PanelMonHoc(MonHocController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(8,8));

        // Form thêm
        JPanel form = new JPanel(new GridLayout(5,2,5,5));
        JTextField tfMa = new JTextField();
        JTextField tfTen = new JTextField();
        JTextField tfTiet = new JTextField();
        JTextField tfLoai = new JTextField();
        form.add(new JLabel("Mã môn (3 chữ số):")); form.add(tfMa);
        form.add(new JLabel("Tên môn:")); form.add(tfTen);
        form.add(new JLabel("Tổng số tiết:")); form.add(tfTiet);
        form.add(new JLabel("Loại môn:")); form.add(tfLoai);

        JButton btnAdd = new JButton("Thêm");
        JButton btnDelete = new JButton("Xóa");
        JButton btnRefresh = new JButton("Làm mới");
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnAdd); btnPanel.add(btnDelete); btnPanel.add(btnRefresh);

        add(form, BorderLayout.NORTH);
        add(new JScrollPane(jList), BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        // Event: Thêm
        btnAdd.addActionListener(e -> {
            String ma = tfMa.getText().trim();
            String ten = tfTen.getText().trim();
            String sTiet = tfTiet.getText().trim();
            String loai = tfLoai.getText().trim();
            if (ma.isEmpty() || ten.isEmpty() || sTiet.isEmpty() || loai.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Điền đủ thông tin");
                return;
            }
            try {
                int tiet = Integer.parseInt(sTiet);
                // Kiểm tra mã 3 chữ số
                if (ma.length() != 3) { JOptionPane.showMessageDialog(this, "Mã môn phải 3 ký tự"); return; }
                if (controller.findByMa(ma) != null) { JOptionPane.showMessageDialog(this, "Mã môn đã tồn tại"); return; }
                controller.addMonHoc(new MonHoc(ma, ten, tiet, loai));
                refresh();
                tfMa.setText(""); tfTen.setText(""); tfTiet.setText(""); tfLoai.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Tổng số tiết phải là số nguyên");
            }
        });

        // Xóa
        btnDelete.addActionListener(e -> {
            MonHoc sel = jList.getSelectedValue();
            if (sel == null) { JOptionPane.showMessageDialog(this, "Chọn môn để xóa"); return; }
            int confirm = JOptionPane.showConfirmDialog(this, "Xóa môn " + sel.getMaMon() + "?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                controller.deleteMonHoc(sel.getMaMon());
                refresh();
            }
        });

        btnRefresh.addActionListener(e -> refresh());

        refresh();
    }

    public void refresh() {
        listModel.clear();
        for (MonHoc m : controller.getAll()) listModel.addElement(m);
    }
}
