package view;

import controller.DangKyController;
import controller.MonHocController;
import controller.SinhVienController;
import model.DangKy;
import model.MonHoc;
import model.SinhVien;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Panel đăng ký: chọn sinh viên, chọn môn, nhập học kỳ & năm -> đăng ký
 */
public class PanelDangKy extends JPanel {
    private final DangKyController dkController;
    private final SinhVienController svController;
    private final MonHocController mhController;

    private final JComboBox<String> cbSV = new JComboBox<>();
    private final JComboBox<String> cbMH = new JComboBox<>();
    private final JTextField tfHocKy = new JTextField();
    private final JTextField tfNam = new JTextField();
    private final DefaultListModel<DangKy> model = new DefaultListModel<>();
    private final JList<DangKy> list = new JList<>(model);

    public PanelDangKy(DangKyController dkController, SinhVienController svController, MonHocController mhController) {
        this.dkController = dkController;
        this.svController = svController;
        this.mhController = mhController;
        setLayout(new BorderLayout(8,8));

        JPanel top = new JPanel(new GridLayout(4,2,5,5));
        top.add(new JLabel("Sinh viên (Mã):")); top.add(cbSV);
        top.add(new JLabel("Môn học (Mã):")); top.add(cbMH);
        top.add(new JLabel("Học kỳ (số):")); top.add(tfHocKy);
        top.add(new JLabel("Năm học (ví dụ 2025):")); top.add(tfNam);

        JPanel btnPanel = new JPanel();
        JButton btnDK = new JButton("Đăng ký");
        JButton btnHuy = new JButton("Hủy đăng ký (chọn dòng)");
        JButton btnRefresh = new JButton("Làm mới");
        btnPanel.add(btnDK); btnPanel.add(btnHuy); btnPanel.add(btnRefresh);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(list), BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        btnDK.addActionListener(e -> doRegister());
        btnHuy.addActionListener(e -> doCancel());
        btnRefresh.addActionListener(e -> refresh());

        refresh();
    }

    private void refreshCombos() {
        cbSV.removeAllItems();
        for (model.SinhVien s : svController.getAll()) cbSV.addItem(s.getMaSV());
        cbMH.removeAllItems();
        for (MonHoc m : mhController.getAll()) cbMH.addItem(m.getMaMon());
    }

    private void refreshList() {
        model.clear();
        List<DangKy> all = dkController.getAll();
        for (DangKy d : all) model.addElement(d);
    }

    public void refresh() {
        refreshCombos();
        refreshList();
    }

    private void doRegister() {
        String maSV = (String) cbSV.getSelectedItem();
        String maMH = (String) cbMH.getSelectedItem();
        if (maSV == null || maMH == null) { JOptionPane.showMessageDialog(this,"Chưa chọn SV hoặc MH"); return; }
        try {
            int hk = Integer.parseInt(tfHocKy.getText().trim());
            int nam = Integer.parseInt(tfNam.getText().trim());
            SinhVien sv = svController.findByMa(maSV);
            MonHoc mh = mhController.findByMa(maMH);
            DangKy dk = new DangKy(sv, mh, hk, nam);
            String err = dkController.register(dk);
            if (err != null) JOptionPane.showMessageDialog(this, "Lỗi: " + err);
            else { JOptionPane.showMessageDialog(this, "Đăng ký thành công"); refresh(); }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Học kỳ và Năm học phải là số");
        }
    }

    private void doCancel() {
        DangKy sel = list.getSelectedValue();
        if (sel == null) { JOptionPane.showMessageDialog(this,"Chọn đăng ký để hủy"); return; }
        int c = JOptionPane.showConfirmDialog(this, "Bạn có muốn hủy đăng ký này?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (c == JOptionPane.YES_OPTION) {
            boolean ok = dkController.cancel(sel);
            if (ok) { JOptionPane.showMessageDialog(this, "Hủy thành công"); refresh(); }
            else JOptionPane.showMessageDialog(this, "Hủy thất bại");
        }
    }
}
