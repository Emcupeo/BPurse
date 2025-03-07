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
public class HinhThucThanhToan {
    private String ID;
    private String IDHoaDonChiTiet;
    private String IDPhuongThucThanhToan;
    private float tienMat;
    private boolean deleted;
    private String createdBy;
    private Date createAt;
    private String updatedBy;
    private Date updatedAt;

    public HinhThucThanhToan(String ID, String IDHoaDonChiTiet, String IDPhuongThucThanhToan, float tienMat, boolean deleted, String createdBy, Date createAt, String updatedBy, Date updatedAt) {
        this.ID = ID;
        this.IDHoaDonChiTiet = IDHoaDonChiTiet;
        this.IDPhuongThucThanhToan = IDPhuongThucThanhToan;
        this.tienMat = tienMat;
        this.deleted = deleted;
        this.createdBy = createdBy;
        this.createAt = createAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
    }

    public HinhThucThanhToan() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getIDHoaDonChiTiet() {
        return IDHoaDonChiTiet;
    }

    public void setIDHoaDonChiTiet(String IDHoaDonChiTiet) {
        this.IDHoaDonChiTiet = IDHoaDonChiTiet;
    }

    public String getIDPhuongThucThanhToan() {
        return IDPhuongThucThanhToan;
    }

    public void setIDPhuongThucThanhToan(String IDPhuongThucThanhToan) {
        this.IDPhuongThucThanhToan = IDPhuongThucThanhToan;
    }

    public float getTienMat() {
        return tienMat;
    }

    public void setTienMat(float tienMat) {
        this.tienMat = tienMat;
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
