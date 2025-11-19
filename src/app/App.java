package app;

import controller.*;
import view.MainFrame;
import model.manager.MonHocManager;
import model.MonHoc;

import model.manager.SinhVienManager;
import model.SinhVien;

import model.manager.DangKyManager;
import model.DangKy;

import model.manager.LopHocManager;
import model.LopHoc;



import javax.swing.*;

/**
 * Entry point: khởi tạo manager, controller, sample data và hiển thị UI.
 */
public class App {
    public static void main(String[] args) {
        // Khởi tạo managers (in-memory)
        MonHocManager mhManager = new MonHocManager();
        SinhVienManager svManager = new SinhVienManager();
        LopHocManager lopManager = new LopHocManager();
        DangKyManager dkManager = new DangKyManager(svManager, mhManager);

        // Controllers
        MonHocController mhCtrl = new MonHocController(mhManager);
        SinhVienController svCtrl = new SinhVienController(svManager);
        LopHocController lopCtrl = new LopHocController(lopManager);
        DangKyController dkCtrl = new DangKyController(dkManager);

        // Sample dữ liệu
        mhManager.add(new MonHoc("101","Toán rời rạc",30,"CO_SO_NGANH"));
        mhManager.add(new MonHoc("102","Lập trình Cơ bản",45,"DAI_CUONG"));
        svManager.add(new SinhVien("SV001","Nguyễn Văn A","CNTT-21"));
        svManager.add(new SinhVien("SV002","Trần Thị B","CNTT-20"));
        lopManager.addLop("L01","Nhóm sáng");
        lopManager.addLop("L02","Nhóm chiều");

        SwingUtilities.invokeLater(() -> {
            MainFrame mf = new MainFrame(mhCtrl, svCtrl, lopCtrl, dkCtrl);
            mf.setVisible(true);
        });
    }
}
