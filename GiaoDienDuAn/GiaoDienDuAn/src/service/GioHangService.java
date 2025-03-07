/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import HoaDonChiTiet.HoaDonChiTietViewModel;
import dao.GioHangDao;
import java.util.List;
import viewmodel.GioHangViewModel;

/**
 *
 * @author tung pc
 */
public class GioHangService {

    private GioHangDao dao = new GioHangDao();

//    public List<GioHangViewModel> getHD() {
//        return dao.getHD();
//    }
//
//    public List<GioHangViewModel> getGH(String ID) {
//        return dao.getGH(ID);
//    }
    public List<GioHangViewModel> getGioHang(String idHoaDon){
        return dao.getGioHang(idHoaDon);
    }
    public List<GioHangViewModel> getGioHangQR(String idHoaDon){
        return dao.getGioHangQR(idHoaDon);
    }
}
