/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.util.Date;

/**
 *
 * @author tung pc
 */
public class GiamGiaViewModel {

    private String maPhieuGiamGia;
    private String tenKhuyenMai;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private int soLuongPhieu;
    private double hoaDonToiThieu;
    private double phanTramGiam;
    private double soTienGiamToiDa;
    private Date ngayTao;
    private String nguoiTao;
    private String trangThai;
    private String idPhieuGiamGia;

    public GiamGiaViewModel() {
    }

    public GiamGiaViewModel(String maPhieuGiamGia, String tenKhuyenMai, Date ngayBatDau, Date ngayKetThuc, int soLuongPhieu, double hoaDonToiThieu, double phanTramGiam, double soTienGiamToiDa, Date ngayTao, String nguoiTao, String trangThai) {
        this.maPhieuGiamGia = maPhieuGiamGia;
        this.tenKhuyenMai = tenKhuyenMai;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.soLuongPhieu = soLuongPhieu;
        this.hoaDonToiThieu = hoaDonToiThieu;
        this.phanTramGiam = phanTramGiam;
        this.soTienGiamToiDa = soTienGiamToiDa;
        this.ngayTao = ngayTao;
        this.nguoiTao = nguoiTao;
        this.trangThai = trangThai;
    }

    public String getIdPhieuGiamGia() {
        return idPhieuGiamGia;
    }

    public void setIdPhieuGiamGia(String idPhieuGiamGia) {
        this.idPhieuGiamGia = idPhieuGiamGia;
    }

    public String getMaPhieuGiamGia() {
        return maPhieuGiamGia;
    }

    public void setMaPhieuGiamGia(String maPhieuGiamGia) {
        this.maPhieuGiamGia = maPhieuGiamGia;
    }

    public String getTenKhuyenMai() {
        return tenKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        this.tenKhuyenMai = tenKhuyenMai;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getSoLuongPhieu() {
        return soLuongPhieu;
    }

    public void setSoLuongPhieu(int soLuongPhieu) {
        this.soLuongPhieu = soLuongPhieu;
    }

    public double getHoaDonToiThieu() {
        return hoaDonToiThieu;
    }

    public void setHoaDonToiThieu(double hoaDonToiThieu) {
        this.hoaDonToiThieu = hoaDonToiThieu;
    }

    public double getPhanTramGiam() {
        return phanTramGiam;
    }

    public void setPhanTramGiam(double phanTramGiam) {
        this.phanTramGiam = phanTramGiam;
    }

    public double getSoTienGiamToiDa() {
        return soTienGiamToiDa;
    }

    public void setSoTienGiamToiDa(double soTienGiamToiDa) {
        this.soTienGiamToiDa = soTienGiamToiDa;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "GiamGiaViewModel{" + "maPhieuGiamGia=" + maPhieuGiamGia + ", tenKhuyenMai=" + tenKhuyenMai + ", ngayBatDau=" + ngayBatDau + ", ngayKetThuc=" + ngayKetThuc + ", soLuongPhieu=" + soLuongPhieu + ", hoaDonToiThieu=" + hoaDonToiThieu + ", phanTramGiam=" + phanTramGiam + ", soTienGiamToiDa=" + soTienGiamToiDa + ", ngayTao=" + ngayTao + ", nguoiTao=" + nguoiTao + ", trangThai=" + trangThai + '}';
    }

}
