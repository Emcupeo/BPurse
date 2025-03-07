/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bpurse.qlts.viewmodel;

/**
 *
 * @author ASUS
 */
public class SanPhamViewmodel1 {
    private String idSP;
    private String maSP;
    private String tenSP;
    private String moTa;
    private int soLuong;
    private String trangThai;

    public SanPhamViewmodel1() {
    }

    public String getIdSP() {
        return idSP;
    }

    public void setIdSP(String idSP) {
        this.idSP = idSP;
    }
    
    public SanPhamViewmodel1(String idSP, String maSP, String tenSP, String moTa, int soLuong, String trangThai) {
        this.idSP = idSP;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.moTa = moTa;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }

    public SanPhamViewmodel1(String maSP, String tenSP, String moTa, int soLuong, String trangThai) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.moTa = moTa;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
    
    
}
