package model.manager;

import java.time.LocalDateTime;
import model.DangKy;
import model.interfaces.IDangKy;
import model.MonHoc;
import model.SinhVien;

import java.util.ArrayList;
import java.util.List;

/**
 * Manager chứa logic đăng ký cơ bản:
 * - Không có kiểm tra tiền đề do đề không yêu cầu.
 * - Kiểm tra tránh trùng đăng ký cùng sinh viên với cùng môn cùng học kỳ & năm.
 * - Trả về thông báo lỗi nếu trùng; null nếu đăng ký thành công.
 */
public class DangKyManager implements IDangKy {
    private final List<DangKy> data = new ArrayList<>();

    private final SinhVienManager svManager;
    private final MonHocManager mhManager;

    public DangKyManager(SinhVienManager svManager, MonHocManager mhManager) {
        this.svManager = svManager;
        this.mhManager = mhManager;
    }

    @Override
    public String register(DangKy dk) {
        // Validate tồn tại SV & MH
        SinhVien sv = svManager.findByMa(dk.getSinhVien().getMaSV());
        if (sv == null) return "Sinh viên không tồn tại";

        MonHoc mh = mhManager.findByMa(dk.getMonHoc().getMaMon());
        if (mh == null) return "Môn học không tồn tại";

        // Kiểm tra trùng: cùng SV, cùng MH, cùng HK & năm
        boolean trùng = data.stream().anyMatch(r ->
                r.getSinhVien().getMaSV().equals(dk.getSinhVien().getMaSV()) &&
                r.getMonHoc().getMaMon().equals(dk.getMonHoc().getMaMon()) &&
                r.getHocKy() == dk.getHocKy() &&
                r.getNamHoc() == dk.getNamHoc()
        );
        if (trùng) return "Đã đăng ký môn này cho học kỳ/năm tương ứng";

        // Thêm
        dk.setThoiGianDK(LocalDateTime.now());
        data.add(dk);
        return null;
    }

    @Override
    public boolean cancel(DangKy dk) {
        return data.removeIf(r ->
                r.getSinhVien().getMaSV().equals(dk.getSinhVien().getMaSV()) &&
                r.getMonHoc().getMaMon().equals(dk.getMonHoc().getMaMon()) &&
                r.getHocKy() == dk.getHocKy() &&
                r.getNamHoc() == dk.getNamHoc()
        );
    }

    @Override
    public List<DangKy> getAll() {
        return new ArrayList<>(data);
    }

    public SinhVienManager getSvManager() {
        return svManager;
    }
    
    public List<DangKy> findBySinhVien(String maSV) {
        List<DangKy> out = new ArrayList<>();
        for (DangKy d : data) if (d.getSinhVien().getMaSV().equals(maSV)) out.add(d);
        return out;
    }

    public List<DangKy> findByMon(String maMon) {
        List<DangKy> out = new ArrayList<>();
        for (DangKy d : data) if (d.getMonHoc().getMaMon().equals(maMon)) out.add(d);
        return out;
    }
}
