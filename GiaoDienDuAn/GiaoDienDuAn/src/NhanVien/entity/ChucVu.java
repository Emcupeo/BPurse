/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NhanVien.entity;

import java.util.Date;

/**
 *
 * @author NguyenVanKien
 */
public class ChucVu {
    private String ID;
    private String tenChucVu;
    private String deleted;
    private Date createdAt;
    private String createdBy;
    private Date updatedAt;
    private String updateBy;
    private String maChucVu;

    public ChucVu() {
    }

    public ChucVu(String ID, String tenChucVu, String deleted, Date createdAt, String createdBy, Date updatedAt, String updateBy, String maChucVu) {
        this.ID = ID;
        this.tenChucVu = tenChucVu;
        this.deleted = deleted;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updateBy = updateBy;
        this.maChucVu = maChucVu;
    }

    public String getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "ChucVu{" + "ID=" + ID + ", tenChucVu=" + tenChucVu + ", deleted=" + deleted + ", createdAt=" + createdAt + ", createdBy=" + createdBy + ", updatedAt=" + updatedAt + ", updateBy=" + updateBy + ", maChucVu=" + maChucVu + '}';
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTenChucVu() {
        return tenChucVu;
    }

    public void setTenChucVu(String tenChucVu) {
        this.tenChucVu = tenChucVu;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getMaChucVu() {
        return maChucVu;
    }

    public void setMaChucVu(String maChucVu) {
        this.maChucVu = maChucVu;
    }

    
    
}
