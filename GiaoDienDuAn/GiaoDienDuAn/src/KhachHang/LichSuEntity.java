/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KhachHang;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class LichSuEntity {

    private String IdKhachHang;
    private String tenKhachHang;
    private String CreateBy;
    private Date UpdateAt;
    private String HanhDong;
    private String maHD;

    public String getIdKhachHang() {
        return IdKhachHang;
    }

    public void setIdKhachHang(String IdKhachHang) {
        this.IdKhachHang = IdKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getCreateBy() {
        return CreateBy;
    }

    public void setCreateBy(String CreateBy) {
        this.CreateBy = CreateBy;
    }

    public Date getUpdateAt() {
        return UpdateAt;
    }

    public void setUpdateAt(Date UpdateAt) {
        this.UpdateAt = UpdateAt;
    }

    public String getHanhDong() {
        return HanhDong;
    }

    public void setHanhDong(String HanhDong) {
        this.HanhDong = HanhDong;
    }

    public LichSuEntity(String IdKhachHang, String tenKhachHang, String CreateBy, Date UpdateAt, String HanhDong) {
        this.IdKhachHang = IdKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.CreateBy = CreateBy;
        this.UpdateAt = UpdateAt;
        this.HanhDong = HanhDong;
    }

    public LichSuEntity() {
    }

}
