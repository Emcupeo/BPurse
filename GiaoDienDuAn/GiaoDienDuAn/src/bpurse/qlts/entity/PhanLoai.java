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
public class PhanLoai {
    private String ID;
    private String maPhanLoai;
    private String tenPhanLoai;
    private boolean deleted;
    private Date createAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;

    public PhanLoai() {
    }

    public PhanLoai(String ID, String maPhanLoai, String tenPhanLoai, boolean deleted, Date createAt, String createdBy, Date updatedAt, String updatedBy) {
        this.ID = ID;
        this.maPhanLoai = maPhanLoai;
        this.tenPhanLoai = tenPhanLoai;
        this.deleted = deleted;
        this.createAt = createAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
    }

    public PhanLoai(String maPhanLoai, String tenPhanLoai) {
        this.maPhanLoai = maPhanLoai;
        this.tenPhanLoai = tenPhanLoai;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getMaPhanLoai() {
        return maPhanLoai;
    }

    public void setMaPhanLoai(String maPhanLoai) {
        this.maPhanLoai = maPhanLoai;
    }

    public String getTenPhanLoai() {
        return tenPhanLoai;
    }

    public void setTenPhanLoai(String tenPhanLoai) {
        this.tenPhanLoai = tenPhanLoai;
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
