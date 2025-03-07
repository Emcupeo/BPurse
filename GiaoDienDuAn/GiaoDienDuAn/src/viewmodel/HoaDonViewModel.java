/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.util.Date;
import java.util.List;

/**
 *
 * @author tung pc
 */
public class HoaDonViewModel {
    private String id;
    private String ma;
    private Date ngayTao;
    private String nV;
    private int TongSP;
    private String tT;
    private String tenKH;
    private String maKH;
    private String idKH;
    private List<GioHangViewModel> gioHang;

    public HoaDonViewModel() {
    }

    public HoaDonViewModel(String ma, Date ngayTao, String nV, int TongSP, String tT) {
        this.ma = ma;
        this.ngayTao = ngayTao;
        this.nV = nV;
        this.TongSP = TongSP;
        this.tT = tT;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getIdKH() {
        return idKH;
    }

    public void setIdKH(String idKH) {
        this.idKH = idKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getnV() {
        return nV;
    }

    public void setnV(String nV) {
        this.nV = nV;
    }

    public int getTongSP() {
        return TongSP;
    }

    public void setTongSP(int TongSP) {
        this.TongSP = TongSP;
    }

    public String gettT() {
        return tT;
    }

    public void settT(String tT) {
        this.tT = tT;
    }

    @Override
    public String toString() {
        return "HoaDonViewModel{" + "ma=" + ma + ", ngayTao=" + ngayTao + ", nV=" + nV + ", TongSP=" + TongSP + ", tT=" + tT + '}';
    }

    public List<GioHangViewModel> getGioHang() {
        return gioHang;
    }

    public void setGioHang(List<GioHangViewModel> gioHang) {
        this.gioHang = gioHang;
    }
}
