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
public class SanPham {
    private String ID;
    private String maSP;
    private String tenSanPham;
    private boolean deleted;
    private Date createAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;

    public SanPham() {
    }

    public SanPham(String ID, String maSP, String tenSanPham, boolean deleted, Date createAt, String createdBy, Date updatedAt, String updatedBy) {
        this.ID = ID;
        this.maSP = maSP;
        this.tenSanPham = tenSanPham;
        this.deleted = deleted;
        this.createAt = createAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }
    
    public SanPham(String ID, String tenSanPham, boolean deleted, Date createAt, String createdBy, Date updatedAt, String updatedBy) {
        this.ID = ID;
      
        this.tenSanPham = tenSanPham;
     
        this.deleted = deleted;
        this.createAt = createAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
    }

    public SanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

   

   
    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
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

    
    
}
