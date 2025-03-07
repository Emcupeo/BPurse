/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ThongKe;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class SoHoaDonEntity {
    private int stt;
    private String MaHoaDon;
    private Date NgayTao;
    private BigDecimal tongTien;

    public SoHoaDonEntity() {
    }

    public SoHoaDonEntity(int stt, String MaHoaDon, Date NgayTao, BigDecimal tongTien) {
        this.stt = stt;
        this.MaHoaDon = MaHoaDon;
        this.NgayTao = NgayTao;
        this.tongTien = tongTien;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(String MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }
    

}