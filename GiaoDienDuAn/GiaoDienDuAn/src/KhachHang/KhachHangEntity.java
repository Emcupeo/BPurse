/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KhachHang;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class KhachHangEntity {

    private int stt;
    private String IDKhachHang;
    private String MaKhachHang;
    private String TenKhachHang;
    private String SoDienThoai;
    private String GioiTinh;
    private Date NgaySinh;
    private String DiaChi; 
    private boolean Delete;
    private Date CreatedAt;
    private String CreateBy;
    private Date UpdateAt;
    private String UpdateBy;

    public KhachHangEntity() {
    }

    public KhachHangEntity(String TenKhachHang, String SoDienThoai, String GioiTinh, Date NgaySinh, String DiaChi) {
        this.TenKhachHang = TenKhachHang;
        this.SoDienThoai = SoDienThoai;
        this.GioiTinh = GioiTinh;
        this.NgaySinh = NgaySinh;
        this.DiaChi = DiaChi;
    }

    
    
    public KhachHangEntity(int stt, String IDKhachHang, String TenKhachHang, String SoDienThoai, String GioiTinh, Date NgaySinh, String DiaChi, boolean Delete, Date  CreatedAt, String CreateBy, Date  UpdateAt, String UpdateBy) {
        this.stt = stt;
        this.IDKhachHang = IDKhachHang;
        this.TenKhachHang = TenKhachHang;
        this.SoDienThoai = SoDienThoai;
        this.GioiTinh = GioiTinh;
        this.NgaySinh = NgaySinh;
        this.DiaChi = DiaChi;
        this.Delete = Delete;
        this.CreatedAt = CreatedAt;
        this.CreateBy = CreateBy;
        this.UpdateAt = UpdateAt;
        this.UpdateBy = UpdateBy;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getMaKhachHang() {
        return MaKhachHang;
    }

    public void setMaKhachHang(String MaKhachHang) {
        this.MaKhachHang = MaKhachHang;
    }

    public String getIDKhachHang() {
        return IDKhachHang;
    }

    public void setIDKhachHang(String IDKhachHang) {
        this.IDKhachHang = IDKhachHang;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public void setTenKhachHang(String TenKhachHang) {
        this.TenKhachHang = TenKhachHang;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public boolean isDelete() {
        return Delete;
    }

    public void setDelete(boolean Delete) {
        this.Delete = Delete;
    }

    public Date   getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Date CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    public String getCreateBy() {
        return CreateBy;
    }

    public void setCreateBy(String CreateBy) {
        this.CreateBy = CreateBy;
    }

    public Date getUpdateAt() {
        return UpdateAt;
    }

    public void setUpdateAt(Date UpdateAt) {
        this.UpdateAt = UpdateAt;
    }

    public String getUpdateBy() {
        return UpdateBy;
    }

    public void setUpdateBy(String UpdateBy) {
        this.UpdateBy = UpdateBy;
    }

}
