package model.manager;

import model.DangKy;
import model.interfaces.IDangKy;
import model.interfaces.ISortDangKy;
import model.MonHoc;
import model.SinhVien;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Manager chứa logic sắp xếp cơ bản:
 * - Sắp xếp theo tên SV, nếu cùng tên sắp xếp theo mã SV.
 * - Sắp xếp theo thời gian đăng ký.
 */
public class SortDangKyManager implements ISortDangKy{
    private final DangKyManager dkManager;

    public SortDangKyManager(DangKyManager dkManager) {
        this.dkManager = dkManager;
    }
    private Map<String, SinhVien> getSinhVienMap(){
        SinhVienManager svManager = dkManager.getSvManager();
        List<SinhVien> danhSachSV = svManager.getAll();
        return danhSachSV.stream()
                .collect(Collectors.toMap(SinhVien::getMaSV, sv -> sv, (existing, replacement) -> existing));
    }
    
    @Override
    public List<DangKy> sortBySinhVien(List<DangKy> list) {
        List<DangKy> danhSachGoc = dkManager.getAll();
        
        if(danhSachGoc.isEmpty()) {
            return new ArrayList<>();
        }
        
        List<DangKy> dsSapXep = new ArrayList<>(danhSachGoc);
        Map<String, SinhVien> mapSinhVien = getSinhVienMap();
        
        dsSapXep.sort(new Comparator<DangKy>(){
            @Override
            public int compare(DangKy dk1, DangKy dk2){
                String tenSV1 = mapSinhVien.getOrDefault(dk1.getSinhVien().getMaSV(), new SinhVien("","ZZZ Khac")).getHoTen();
                String tenSV2 = mapSinhVien.getOrDefault(dk2.getSinhVien().getMaSV(), new SinhVien("","ZZZ Khac")).getHoTen();
                
                int result = tenSV1.compareToIgnoreCase(tenSV2);
                if(result == 0){
                    return dk1.getSinhVien().getMaSV().compareTo(dk2.getSinhVien().getMaSV());
                }
                return result; 
            }
        });
        return dsSapXep;
    }

    @Override
    public List<DangKy> sortByThoiGian(List<DangKy> list) {
        List<DangKy> danhSachGoc = dkManager.getAll();
        
        if(danhSachGoc.isEmpty()) {
            return new ArrayList<>();
        }
        
        List<DangKy> dsSapXep = new ArrayList<>(danhSachGoc);
        
        dsSapXep.sort(Comparator.comparing(DangKy::getThoiGianDK));
        return dsSapXep;
    } 
}
