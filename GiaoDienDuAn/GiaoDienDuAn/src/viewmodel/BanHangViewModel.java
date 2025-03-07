/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

/**
 *
 * @author tung pc
 */
public class BanHangViewModel {
    private String id; 
    private String ma;
    private String tenSP;
    private String tenMS;
    private String tenCL;
    private String kichCo;
    private String tenNSX;
    private String tenTH;
    private double giaBan;
    private String trangThai;
    private int soLuong;

    public BanHangViewModel() {
    }

    public BanHangViewModel(String ma, String tenSP, String tenMS, String tenCL, String kichCo, String tenNSX, String tenTH, double giaBan, String trangThai) {
        this.ma = ma;
        this.tenSP = tenSP;
        this.tenMS = tenMS;
        this.tenCL = tenCL;
        this.kichCo = kichCo;
        this.tenNSX = tenNSX;
        this.tenTH = tenTH;
        this.giaBan = giaBan;
        this.trangThai = trangThai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    
    public String getMa() {
        return ma;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getTenMS() {
        return tenMS;
    }

    public void setTenMS(String tenMS) {
        this.tenMS = tenMS;
    }

    public String getTenCL() {
        return tenCL;
    }

    public void setTenCL(String tenCL) {
        this.tenCL = tenCL;
    }

    public String getKichCo() {
        return kichCo;
    }

    public void setKichCo(String kichCo) {
        this.kichCo = kichCo;
    }

    public String getTenNSX() {
        return tenNSX;
    }

    public void setTenNSX(String tenNSX) {
        this.tenNSX = tenNSX;
    }

    public String getTenTH() {
        return tenTH;
    }

    public void setTenTH(String tenTH) {
        this.tenTH = tenTH;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "BanHangViewModel{" + "ma=" + ma + ", tenSP=" + tenSP + ", tenMS=" + tenMS + ", tenCL=" + tenCL + ", kichCo=" + kichCo + ", tenNSX=" + tenNSX + ", tenTH=" + tenTH + ", giaBan=" + giaBan + ", trangThai=" + trangThai + '}';
    }
    
}
