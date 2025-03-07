/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ThongKe.BieuDo;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class LopHocBean {

    private Date NgayTao;
//    private int SoLuong;
    private BigDecimal tongTien;

    public LopHocBean() {
    }

    public LopHocBean(Date NgayTao, BigDecimal tongTien) {
        this.NgayTao = NgayTao;
        this.tongTien = tongTien;
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
