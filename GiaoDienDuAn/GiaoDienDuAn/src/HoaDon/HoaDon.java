/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HoaDon;

import java.util.Date;

/**
 *
 * @author My PC
 */
public class HoaDon {
    private String ID;
    private String IDChiTietSP;
    private String IDHoaDonChiTiet;  
    private float donGia;
    private boolean deleted;
    private String createdBy;
    private Date createAt;
    private String updatedBy;
    private Date updatedAt;
    private Date ngayThanhToan;

    public HoaDon(String ID, String IDChiTietSP, String IDHoaDonChiTiet, float donGia, boolean deleted, String createdBy, Date createAt, String updatedBy, Date updatedAt) {
        this.ID = ID;
        this.IDChiTietSP = IDChiTietSP;
        this.IDHoaDonChiTiet = IDHoaDonChiTiet;
        this.donGia = donGia;
        this.deleted = deleted;
        this.createdBy = createdBy;
        this.createAt = createAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
    }

    public HoaDon() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getIDChiTietSP() {
        return IDChiTietSP;
    }

    public void setIDChiTietSP(String IDChiTietSP) {
        this.IDChiTietSP = IDChiTietSP;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public String getIDHoaDonChiTiet() {
        return IDHoaDonChiTiet;
    }

    public void setIDHoaDonChiTiet(String IDHoaDonChiTiet) {
        this.IDHoaDonChiTiet = IDHoaDonChiTiet;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
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
