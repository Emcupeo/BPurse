/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bpurse.qlts.entity;

import java.util.Date;

/**
 *
 * @author My PC
 */
public class ChiTietSanPham {
    private String ID;
    private String IDNhaSanXuat;
    private String IDChatLieu;
    private String IDMauSac;
    private String IDPhuKien;
    private String IDLoaiQuai;
    private String IDThuongHieu;
    private String IDPhanLoai;
    private String IDKichCo;
    private String IDTinhTrang;
    private int soLuong   ;
    private String moTa;
    private float giaNhap   ;
    private float giaBan   ;
    private boolean deleted;
    private Date createAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;
    private String maSP;
    private String trangThai;

    public ChiTietSanPham() {
    }

    public ChiTietSanPham(String ID, int soLuong) {
        this.ID = ID;
        this.soLuong = soLuong;
    }

    
    public ChiTietSanPham(String IDNhaSanXuat, String IDChatLieu, String IDMauSac, String IDPhuKien, String IDLoaiQuai, String IDThuongHieu, String IDPhanLoai, String IDKichCo, String IDTinhTrang, int soLuong, String moTa, float giaNhap, float giaBan) {
        this.IDNhaSanXuat = IDNhaSanXuat;
        this.IDChatLieu = IDChatLieu;
        this.IDMauSac = IDMauSac;
        this.IDPhuKien = IDPhuKien;
        this.IDLoaiQuai = IDLoaiQuai;
        this.IDThuongHieu = IDThuongHieu;
        this.IDPhanLoai = IDPhanLoai;
        this.IDKichCo = IDKichCo;
        this.IDTinhTrang = IDTinhTrang;
        this.soLuong = soLuong;
        this.moTa = moTa;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
    }

    public ChiTietSanPham(String ID, String IDNhaSanXuat, String IDChatLieu, String IDMauSac, String IDPhuKien, String IDLoaiQuai, String IDThuongHieu, String IDPhanLoai, String IDKichCo, String IDTinhTrang, int soLuong, String moTa, float giaNhap, float giaBan, boolean deleted, Date createAt, String createdBy, Date updatedAt, String updatedBy, String maSP) {
        this.ID = ID;
        this.IDNhaSanXuat = IDNhaSanXuat;
        this.IDChatLieu = IDChatLieu;
        this.IDMauSac = IDMauSac;
        this.IDPhuKien = IDPhuKien;
        this.IDLoaiQuai = IDLoaiQuai;
        this.IDThuongHieu = IDThuongHieu;
        this.IDPhanLoai = IDPhanLoai;
        this.IDKichCo = IDKichCo;
        this.IDTinhTrang = IDTinhTrang;
        this.soLuong = soLuong;
        this.moTa = moTa;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.deleted = deleted;
        this.createAt = createAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.maSP = maSP;
    }

    public ChiTietSanPham(String ID, String IDNhaSanXuat, String IDChatLieu, String IDMauSac, String IDPhuKien, String IDLoaiQuai, String IDThuongHieu, String IDPhanLoai, String IDKichCo, String IDTinhTrang, int soLuong, String moTa, float giaNhap, float giaBan, boolean deleted, Date createAt, String createdBy, Date updatedAt, String updatedBy, String maSP, String trangThai) {
        this.ID = ID;
        this.IDNhaSanXuat = IDNhaSanXuat;
        this.IDChatLieu = IDChatLieu;
        this.IDMauSac = IDMauSac;
        this.IDPhuKien = IDPhuKien;
        this.IDLoaiQuai = IDLoaiQuai;
        this.IDThuongHieu = IDThuongHieu;
        this.IDPhanLoai = IDPhanLoai;
        this.IDKichCo = IDKichCo;
        this.IDTinhTrang = IDTinhTrang;
        this.soLuong = soLuong;
        this.moTa = moTa;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.deleted = deleted;
        this.createAt = createAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.maSP = maSP;
        this.trangThai = trangThai;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public ChiTietSanPham(int soLuong) {
        this.soLuong = soLuong;
    }


     

    public String getIDNhaSanXuat() {
        return IDNhaSanXuat;
    }

    public void setIDNhaSanXuat(String IDNhaSanXuat) {
        this.IDNhaSanXuat = IDNhaSanXuat;
    }

    public String getIDChatLieu() {
        return IDChatLieu;
    }

    public void setIDChatLieu(String IDChatLieu) {
        this.IDChatLieu = IDChatLieu;
    }

    public String getIDMauSac() {
        return IDMauSac;
    }

    public void setIDMauSac(String IDMauSac) {
        this.IDMauSac = IDMauSac;
    }

    public String getIDPhuKien() {
        return IDPhuKien;
    }

    public void setIDPhuKien(String IDPhuKien) {
        this.IDPhuKien = IDPhuKien;
    }

    public String getIDLoaiQuai() {
        return IDLoaiQuai;
    }

    public void setIDLoaiQuai(String IDLoaiQuai) {
        this.IDLoaiQuai = IDLoaiQuai;
    }

    public String getIDThuongHieu() {
        return IDThuongHieu;
    }

    public void setIDThuongHieu(String IDThuongHieu) {
        this.IDThuongHieu = IDThuongHieu;
    }

    public String getIDPhanLoai() {
        return IDPhanLoai;
    }

    public void setIDPhanLoai(String IDPhanLoai) {
        this.IDPhanLoai = IDPhanLoai;
    }

    public String getIDKichCo() {
        return IDKichCo;
    }

    public void setIDKichCo(String IDKichCo) {
        this.IDKichCo = IDKichCo;
    }

    public String getIDTinhTrang() {
        return IDTinhTrang;
    }

    public void setIDTinhTrang(String IDTinhTrang) {
        this.IDTinhTrang = IDTinhTrang;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public float getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(float giaNhap) {
        this.giaNhap = giaNhap;
    }

    public float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(float giaBan) {
        this.giaBan = giaBan;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
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

    public String getmaSP() {
        return maSP;
    }

    public void setmaSP(String maSP) {
        this.maSP = maSP;
    }

    
    
}
