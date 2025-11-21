package view;

import controller.LopHocController;
import model.LopHoc;

import javax.swing.*;
import java.awt.*;

/**
 * Panel lớp học: thêm/xóa/làm mới
 */
public class PanelLopHoc extends JPanel {
    private final LopHocController controller;
    private final DefaultListModel<LopHoc> model = new DefaultListModel<>();
    private final JList<LopHoc> list = new JList<>(model);

    public PanelLopHoc(LopHocController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(8,8));

        JPanel form = new JPanel(new GridLayout(3,2,5,5));
        JTextField tfMa = new JTextField();
        JTextField tfTen = new JTextField();
        form.add(new JLabel("Mã lớp:")); form.add(tfMa);
        form.add(new JLabel("Tên lớp:")); form.add(tfTen);

        JButton btnAdd = new JButton("Thêm lớp");
        JButton btnDelete = new JButton("Xóa lớp");
        JButton btnRefresh = new JButton("Làm mới");
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnAdd); btnPanel.add(btnDelete); btnPanel.add(btnRefresh);

        add(form, BorderLayout.NORTH);
        add(new JScrollPane(list), BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> {
            String ma = tfMa.getText().trim();
            String ten = tfTen.getText().trim();
            if (ma.isEmpty() || ten.isEmpty()) { JOptionPane.showMessageDialog(this,"Điền đủ thông tin"); return; }
            if (controller.findByMa(ma) != null) { JOptionPane.showMessageDialog(this,"Mã lớp đã tồn tại"); return; }
            controller.addLop(ma, ten);
            refresh();
            tfMa.setText(""); tfTen.setText("");
        });

        btnDelete.addActionListener(e -> {
            LopHoc sel = list.getSelectedValue();
            if (sel == null) { JOptionPane.showMessageDialog(this,"Chọn lớp để xóa"); return; }
            int c = JOptionPane.showConfirmDialog(this, "Xóa lớp " + sel.getMaLop() + "?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (c == JOptionPane.YES_OPTION) {
                controller.deleteLop(sel.getMaLop());
                refresh();
            }
        });

        btnRefresh.addActionListener(e -> refresh());
        refresh();
    }

    public void refresh() {
        model.clear();
        for (LopHoc l : controller.getAll()) model.addElement(l);
    }
}
