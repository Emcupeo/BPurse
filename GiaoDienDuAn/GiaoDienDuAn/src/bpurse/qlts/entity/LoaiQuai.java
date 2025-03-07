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
public class LoaiQuai {
    private String ID;
    private String maLoaiQuai;
    private String tenLoaiQuai;
    private boolean deleted;
    private Date createAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;

    public LoaiQuai() {
    }

    public LoaiQuai(String maLoaiQuai, String tenLoaiQuai) {
        this.maLoaiQuai = maLoaiQuai;
        this.tenLoaiQuai = tenLoaiQuai;
    }
    

    public LoaiQuai(String ID, String maLoaiQuai, String tenLoaiQuai, boolean deleted, Date createAt, String createdBy, Date updatedAt, String updatedBy) {
        this.ID = ID;
        this.maLoaiQuai = maLoaiQuai;
        this.tenLoaiQuai = tenLoaiQuai;
        this.deleted = deleted;
        this.createAt = createAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getMaLoaiQuai() {
        return maLoaiQuai;
    }

    public void setMaLoaiQuai(String maLoaiQuai) {
        this.maLoaiQuai = maLoaiQuai;
    }

    public String getTenLoaiQuai() {
        return tenLoaiQuai;
    }

    public void setTenLoaiQuai(String tenLoaiQuai) {
        this.tenLoaiQuai = tenLoaiQuai;
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
