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
public class PhieuGiamGia {

    private String ID;
    private String IDTenPhieuGiamGia;
    private String LoaiPhieuGiamGia;  
    private float giaTriGiamGia;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private String dieuKienApDung;
    private String trangThai; 
    private String moTa;
    private boolean deleted;
    private String createdBy;
    private Date createAt;
    private String updatedBy;
    private Date updatedAt;

    public PhieuGiamGia(String ID, String IDTenPhieuGiamGia, String LoaiPhieuGiamGia, float giaTriGiamGia, Date ngayBatDau, Date ngayKetThuc, String dieuKienApDung, String trangThai, String moTa, boolean deleted, String createdBy, Date createAt, String updatedBy, Date updatedAt) {
        this.ID = ID;
        this.IDTenPhieuGiamGia = IDTenPhieuGiamGia;
        this.LoaiPhieuGiamGia = LoaiPhieuGiamGia;
        this.giaTriGiamGia = giaTriGiamGia;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.dieuKienApDung = dieuKienApDung;
        this.trangThai = trangThai;
        this.moTa = moTa;
        this.deleted = deleted;
        this.createdBy = createdBy;
        this.createAt = createAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
    }

    public PhieuGiamGia() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getIDTenPhieuGiamGia() {
        return IDTenPhieuGiamGia;
    }

    public void setIDTenPhieuGiamGia(String IDTenPhieuGiamGia) {
        this.IDTenPhieuGiamGia = IDTenPhieuGiamGia;
    }

    public String getLoaiPhieuGiamGia() {
        return LoaiPhieuGiamGia;
    }

    public void setLoaiPhieuGiamGia(String LoaiPhieuGiamGia) {
        this.LoaiPhieuGiamGia = LoaiPhieuGiamGia;
    }

    public float getGiaTriGiamGia() {
        return giaTriGiamGia;
    }

    public void setGiaTriGiamGia(float giaTriGiamGia) {
        this.giaTriGiamGia = giaTriGiamGia;
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

    public String getDieuKienApDung() {
        return dieuKienApDung;
    }

    public void setDieuKienApDung(String dieuKienApDung) {
        this.dieuKienApDung = dieuKienApDung;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
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
