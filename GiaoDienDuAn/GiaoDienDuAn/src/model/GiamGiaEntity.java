/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author tung pc
 */
public class GiamGiaEntity {

    private String id;
    private String maPhieuGiamGia;
    private String tenKhuyenMai;
    private int soLuongPhieu;
    private double phanTramGiam;
    private double hoaDonToiThieu;
    private double soTienGiamToiDa;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private String trangThai;
    private String idNhanVien;
    private boolean deleted;
    private Date createdAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;

    public GiamGiaEntity() {
    }

    public GiamGiaEntity(String id, String maPhieuGiamGia, String tenKhuyenMai, int soLuongPhieu, double phanTramGiam, double hoaDonToiThieu, double soTienGiamToiDa, Date ngayBatDau, Date ngayKetThuc, String trangThai, String idNhanVien, boolean deleted, Date createdAt, String createdBy, Date updatedAt, String updatedBy) {
        this.id = id;
        this.maPhieuGiamGia = maPhieuGiamGia;
        this.tenKhuyenMai = tenKhuyenMai;
        this.soLuongPhieu = soLuongPhieu;
        this.phanTramGiam = phanTramGiam;
        this.hoaDonToiThieu = hoaDonToiThieu;
        this.soTienGiamToiDa = soTienGiamToiDa;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
        this.idNhanVien = idNhanVien;
        this.deleted = deleted;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
    }    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaPhieuGiamGia() {
        return maPhieuGiamGia;
    }

    public void setMaPhieuGiamGia(String maPhieuGiamGia) {
        this.maPhieuGiamGia = maPhieuGiamGia;
    }

    public String getTenKhuyenMai() {
        return tenKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        this.tenKhuyenMai = tenKhuyenMai;
    }

    public int getSoLuongPhieu() {
        return soLuongPhieu;
    }

    public void setSoLuongPhieu(int soLuongPhieu) {
        this.soLuongPhieu = soLuongPhieu;
    }

    public double getPhanTramGiam() {
        return phanTramGiam;
    }

    public void setPhanTramGiam(double phanTramGiam) {
        this.phanTramGiam = phanTramGiam;
    }

    public double getHoaDonToiThieu() {
        return hoaDonToiThieu;
    }

    public void setHoaDonToiThieu(double hoaDonToiThieu) {
        this.hoaDonToiThieu = hoaDonToiThieu;
    }

    public double getSoTienGiamToiDa() {
        return soTienGiamToiDa;
    }

    public void setSoTienGiamToiDa(double soTienGiamToiDa) {
        this.soTienGiamToiDa = soTienGiamToiDa;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(String idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

}
