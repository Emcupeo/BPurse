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
public class PhuKien {
    private String ID;
    private String maPhuKien;
    private String tenPhuKien;
    private boolean deleted;
    private Date createAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;

    public PhuKien() {
    }

    public PhuKien(String maPhuKien, String tenPhuKien) {
        this.maPhuKien = maPhuKien;
        this.tenPhuKien = tenPhuKien;
    }
    
    
    
    

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getMaPhuKien() {
        return maPhuKien;
    }

    public void setMaPhuKien(String maPhuKien) {
        this.maPhuKien = maPhuKien;
    }

    public String getTenPhuKien() {
        return tenPhuKien;
    }

    public void setTenPhuKien(String tenPhuKien) {
        this.tenPhuKien = tenPhuKien;
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
