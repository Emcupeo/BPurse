/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KhachHang;

import java.util.List;

/**
 *
 * @author ADMIN
 */
public class KhachHangService {

    private KhachHangRepositoty repo = new KhachHangRepositoty();

    public List<KhachHangEntity> getKhachHang() {
        return repo.getKhachHang();
    }
//
    public List<LichSuEntity> getKhachHangLichSu(String idKH) {
        return repo.getKhachHangLichSu(idKH);
    }
public List<KhachHangEntity> getCheckSDT() {
        return repo.getCheckSDT();
    }

//    public List<KhachHangEntity> getKhachHangLichSuGiaoDich() {
//        return repo.getKhachHangLichSuGiaoDich();
//    }

//    public List<KhachHangEntity> getCBO(String tt) {
//        return repo.getLichSu(tt);
//    }
//    public boolean Them(KhachHangEntity gv) {
//        return repo.Them(gv);
//    }

//    public String Them(KhachHangEntity nv) {
//        boolean chk = repo.Them(nv);
//        if (chk) {
//            return "Thêm thành công";
//        } else {
//            return "Thêm thất bại";
//        }
//    }
public String Them(KhachHangEntity nv) {
    // Kiểm tra xem dữ liệu từ form có tồn tại không trước khi thêm vào cơ sở dữ liệu
    if (nv == null) {
        return "Dữ liệu nhập từ form không hợp lệ";
    }

    // Kiểm tra các trường thông tin
    if (nv.getTenKhachHang().isEmpty() || nv.getSoDienThoai().isEmpty() || nv.getDiaChi().isEmpty()) {
        return "Thêm thất bại";
    }

    boolean chk = repo.Them(nv);
    if (chk) {
        return "Thêm thành công";
    } else {
        return "Thêm thất bại";
    }
}
    public String Update(KhachHangEntity nv, String oldMa) {
        boolean chk = repo.Update(nv, oldMa);
        if (chk) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }
//

    public String Xoa(String id) {
        boolean chk = repo.Xoa(id);
        if (chk) {
            return "Xoá thành công";
        } else {
            return "Xoá thất bại";
        }
    }
//    public boolean Xoa(int id) {
//        return repo.Xoa(id);
//    }
//    public boolean Update(KhachHangEntity gv, int oldMa) {
//        return repo.Update(gv, oldMa);
//    }
//

    public List<KhachHangEntity> Search(String type) {
        return repo.Search(type);
    }
}
