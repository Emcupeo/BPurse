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
public class KichCo {
    private String ID;
    private String maKichCo;
    private String tenKichCo;
    private boolean deleted;
    private Date createAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;

    public KichCo() {
    }

    public KichCo(String ID, String maKichCo, String tenKichCo, boolean deleted, Date createAt, String createdBy, Date updatedAt, String updatedBy) {
        this.ID = ID;
        this.maKichCo = maKichCo;
        this.tenKichCo = tenKichCo;
        this.deleted = deleted;
        this.createAt = createAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
    }

    public KichCo(String maKichCo, String tenKichCo) {
        this.maKichCo = maKichCo;
        this.tenKichCo = tenKichCo;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getMaKichCo() {
        return maKichCo;
    }

    public void setMaKichCo(String maKichCo) {
        this.maKichCo = maKichCo;
    }

    public String getTenKichCo() {
        return tenKichCo;
    }

    public void setTenKichCo(String tenKichCo) {
        this.tenKichCo = tenKichCo;
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
