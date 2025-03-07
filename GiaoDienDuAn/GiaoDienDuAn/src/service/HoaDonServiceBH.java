/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.HoaDonDao;
import java.util.List;
import viewmodel.GioHangViewModel;
import viewmodel.HoaDonViewModel;

/**
 *
 * @author tung pc
 */
public class HoaDonServiceBH {

    private HoaDonDao dao = new HoaDonDao();

    public List<HoaDonViewModel> getHD() {
        return dao.getHD();
    }

    public boolean themHoaDon(HoaDonViewModel hdMoi) {
        return dao.themHoaDon(hdMoi);
    }
public List<HoaDonViewModel> getIDHD(String idHD) {
    return dao.getIDHD(idHD);
}
//    public List<GioHangViewModel> getGioHang(HoaDonViewModel hd) {
//        return dao.getGioHang(hd);
//    }
    
}
