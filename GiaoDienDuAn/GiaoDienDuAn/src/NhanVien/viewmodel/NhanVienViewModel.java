/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NhanVien.viewmodel;

import java.util.Date;

/**
 *
 * @author NguyenVanKien
 */
public class NhanVienViewModel {
    private String idNhanVien;
    private String maNhanVien;
    private String tenNhanVien;
    private String sDt;
    private String email;
    private String diaChi;
    private String gioiTinh;
    private Date ngaySinh;
    private String chucVu;
    private boolean trangThai;
private String maChucVu;
    private String cCCD;

    public NhanVienViewModel() {
    }

    public NhanVienViewModel(String idNhanVien, String maNhanVien, String tenNhanVien, String sDt, String email, String diaChi, String gioiTinh, Date ngaySinh, String chucVu, boolean trangThai, String maChucVu, String cCCD) {
        this.idNhanVien = idNhanVien;
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.sDt = sDt;
        this.email = email;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.chucVu = chucVu;
        this.trangThai = trangThai;
        this.maChucVu = maChucVu;
        this.cCCD = cCCD;
    }

    public String getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(String idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    @Override
    public String toString() {
        return "NhanVienViewModel{" + "idNhanVien=" + idNhanVien + ", maNhanVien=" + maNhanVien + ", tenNhanVien=" + tenNhanVien + ", sDt=" + sDt + ", email=" + email + ", diaChi=" + diaChi + ", gioiTinh=" + gioiTinh + ", ngaySinh=" + ngaySinh + ", chucVu=" + chucVu + ", trangThai=" + trangThai + ", maChucVu=" + maChucVu + ", cCCD=" + cCCD + '}';
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getsDt() {
        return sDt;
    }

    public void setsDt(String sDt) {
        this.sDt = sDt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaChucVu() {
        return maChucVu;
    }

    public void setMaChucVu(String maChucVu) {
        this.maChucVu = maChucVu;
    }

    public String getcCCD() {
        return cCCD;
    }

    public void setcCCD(String cCCD) {
        this.cCCD = cCCD;
    }

    

    
    
    
    
    
}
