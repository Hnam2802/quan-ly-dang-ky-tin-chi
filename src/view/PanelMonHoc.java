package view;

import controller.MonHocController;
import model.MonHoc;

import javax.swing.*;
import java.awt.*;

/**
 * Panel thêm/xóa/xem Môn học với nút chọn loại môn
 */
public class PanelMonHoc extends JPanel {
    private final MonHocController controller;
    private final DefaultListModel<MonHoc> listModel = new DefaultListModel<>();
    private final JList<MonHoc> jList = new JList<>(listModel);

    // Radio buttons cho loại môn
    private final JRadioButton rbDC = new JRadioButton("Đại cương");
    private final JRadioButton rbCSN = new JRadioButton("Cơ sở ngành");
    private final JRadioButton rbCNBB = new JRadioButton("Chuyên ngành bắt buộc");
    private final JRadioButton rbCNTC = new JRadioButton("Chuyên ngành tự chọn");

    public PanelMonHoc(MonHocController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(8,8));

        // Form thêm
        JPanel form = new JPanel(new GridLayout(5,2,5,5));
        JTextField tfMa = new JTextField();
        JTextField tfTen = new JTextField();
        JTextField tfTiet = new JTextField();

        form.add(new JLabel("Mã môn (3 chữ số):")); form.add(tfMa);
        form.add(new JLabel("Tên môn:")); form.add(tfTen);
        form.add(new JLabel("Tổng số tiết:")); form.add(tfTiet);

        // Nhóm radio button
        ButtonGroup group = new ButtonGroup();
        group.add(rbDC); group.add(rbCSN); group.add(rbCNBB); group.add(rbCNTC);
        JPanel panelLoai = new JPanel(new GridLayout(2,2));
        panelLoai.add(rbDC); panelLoai.add(rbCSN);
        panelLoai.add(rbCNBB); panelLoai.add(rbCNTC);

        form.add(new JLabel("Loại môn:")); form.add(panelLoai);

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

            if (ma.isEmpty() || ten.isEmpty() || sTiet.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Điền đủ thông tin");
                return;
            }
            try {
                int tiet = Integer.parseInt(sTiet);
                if (ma.length() != 3) {
                    JOptionPane.showMessageDialog(this, "Mã môn phải 3 ký tự");
                    return;
                }
                if (controller.findByMa(ma) != null) {
                    JOptionPane.showMessageDialog(this, "Mã môn đã tồn tại");
                    return;
                }

                // Lấy loại môn từ radio button
                String loai = null;
                if (rbDC.isSelected()) loai = "Đại cương";
                else if (rbCSN.isSelected()) loai = "Cơ sở ngành";
                else if (rbCNBB.isSelected()) loai = "Chuyên ngành bắt buộc";
                else if (rbCNTC.isSelected()) loai = "Chuyên ngành tự chọn";

                if (loai == null) {
                    JOptionPane.showMessageDialog(this, "Chọn loại môn");
                    return;
                }

                controller.addMonHoc(new MonHoc(ma, ten, tiet, loai));
                refresh();
                tfMa.setText(""); tfTen.setText(""); tfTiet.setText("");
                group.clearSelection();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Tổng số tiết phải là số nguyên");
            }
        });

        // Xóa
        btnDelete.addActionListener(e -> {
            MonHoc sel = jList.getSelectedValue();
            if (sel == null) {
                JOptionPane.showMessageDialog(this, "Chọn môn để xóa");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Xóa môn " + sel.getMaMon() + "?",
                    "Confirm", JOptionPane.YES_NO_OPTION);
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
