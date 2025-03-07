package ThongKe;

import java.math.BigDecimal;

public class ThongKeEntity {

    private String TenKhachHang;
    private BigDecimal tongTien;
    private String TrangThai;

    public ThongKeEntity() {
    }

    public ThongKeEntity(String TenKhachHang, BigDecimal tongTien, String TrangThai) {
        this.TenKhachHang = TenKhachHang;
        this.tongTien = tongTien;
        this.TrangThai = TrangThai;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public void setTenKhachHang(String TenKhachHang) {
        this.TenKhachHang = TenKhachHang;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

}
