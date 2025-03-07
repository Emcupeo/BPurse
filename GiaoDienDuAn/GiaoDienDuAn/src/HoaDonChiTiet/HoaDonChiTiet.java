/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HoaDonChiTiet;

import java.util.Date;

/**
 *
 * @author My PC
 */
public class HoaDonChiTiet {
    
    private String ID;
    private String IDCTSP;
    private String IDNhanVien;
    private String IDKhachHang;  
    private String IDHoaDon;
    private Date ngayTao;
    private Date ngayThanhToan;
    private String tenKhachHang;
    private String sdt; 
    private String diaChi;
    private int soLuong;
    private double tongTien;
    private boolean deleted;
    private String createdBy;
    private Date createAt;
    private String updatedBy;
    private Date updatedAt;

    public HoaDonChiTiet() {
    }



    public HoaDonChiTiet(String IDCTSP, String IDHoaDon, int soLuong, double tongTien) {
        this.IDCTSP = IDCTSP;   
        this.IDHoaDon = IDHoaDon;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
    }

    
    
    public HoaDonChiTiet(String ID, String IDNhanVien, String IDKhachHang, Date ngayTao, Date ngayThanhToan, String tenKhachHang, String sdt, String diaChi, double tongTien, boolean deleted, String createdBy, Date createAt, String updatedBy, Date updatedAt) {
        this.ID = ID;
        this.IDNhanVien = IDNhanVien;
        this.IDKhachHang = IDKhachHang;
        this.ngayTao = ngayTao;
        this.ngayThanhToan = ngayThanhToan;
        this.tenKhachHang = tenKhachHang;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.tongTien = tongTien;
        this.deleted = deleted;
        this.createdBy = createdBy;
        this.createAt = createAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
    }

    public String getIDHoaDon() {
        return IDHoaDon;
    }

    public void setIDHoaDon(String IDHoaDon) {
        this.IDHoaDon = IDHoaDon;
    }

    public String getIDCTSP() {
        return IDCTSP;
    }

    public void setIDCTSP(String IDCTSP) {
        this.IDCTSP = IDCTSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getIDNhanVien() {
        return IDNhanVien;
    }

    public void setIDNhanVien(String IDNhanVien) {
        this.IDNhanVien = IDNhanVien;
    }

    public String getIDKhachHang() {
        return IDKhachHang;
    }

    public void setIDKhachHang(String IDKhachHang) {
        this.IDKhachHang = IDKhachHang;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}