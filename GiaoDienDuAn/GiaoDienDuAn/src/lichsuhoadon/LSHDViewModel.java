/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lichsuhoadon;

import com.sun.jna.platform.win32.OaIdl;
import java.util.Date;

/**
 *
 * @author My PC
 */
public class LSHDViewModel {
    private String nhanVien;
    private String idHoaDon;
    private Date ngay;
    private int gio;
    private String hanhDong;

    public LSHDViewModel(String nhanVien,String idHoaDon, Date ngay, int gio, String hanhDong) {
        this.nhanVien = nhanVien;
        this.idHoaDon = idHoaDon;
        this.ngay = ngay;
        this.gio = gio;
        this.hanhDong = hanhDong;
    }

    
    public LSHDViewModel() {
    }

    public String getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(String idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(String nhanVien) {
        this.nhanVien = nhanVien;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public int getGio() {
        return gio;
    }

    public void setGio(int gio) {
        this.gio = gio;
    }

    public String getHanhDong() {
        return hanhDong;
    }

    public void setHanhDong(String hanhDong) {
        this.hanhDong = hanhDong;
    }
    
}
