package view;

import controller.*;
import javax.swing.*;
import java.awt.*;

/**
 * MainFrame: chứa JTabbedPane với các panel chức năng.
 */
public class MainFrame extends JFrame {
    public MainFrame(MonHocController monHocController, SinhVienController sinhVienController,
                     LopHocController lopHocController, DangKyController dangKyController,
                     SortController sortController) {
        setTitle("Quản lý đăng ký học - Swing (Đơn giản)");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Môn học", new PanelMonHoc(monHocController));
        tabs.addTab("Sinh viên", new PanelSinhVien(sinhVienController));
        tabs.addTab("Lớp học", new PanelLopHoc(lopHocController));
        tabs.addTab("Đăng ký", new PanelDangKy(dangKyController, sinhVienController, monHocController));
        tabs.addTab("Sắp xếp ĐK", new PanelSapXep(sortController, dangKyController));

        getContentPane().add(tabs, BorderLayout.CENTER);
    }
}
