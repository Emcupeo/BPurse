/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import HoaDonChiTiet.HoaDonChiTiet;
import bpurse.qlts.entity.ChiTietSanPham;
import dao.BanHangDao;
import java.util.List;
import viewmodel.BanHangViewModel;
import viewmodel.HoaDonViewModel;

/**
 *
 * @author tung pc
 */
public class BanHangService {

    private BanHangDao dao = new BanHangDao();

    public List<BanHangViewModel> getAll() {
        return dao.getAll();
    }

    public List<BanHangViewModel> tim(String search) {
        return dao.tim(search);
    }

    public List<BanHangViewModel> timT(String search) {
        return dao.timT(search);
    }

    public boolean AddSPGH(HoaDonChiTiet hdct1, ChiTietSanPham ctsp1, String idHD, String idSP, int SLSP, int SLHDCT) {
        return dao.AddSPGH(hdct1, ctsp1, idHD, idSP, SLSP, SLHDCT);
    }
    
    public boolean UpdateTrungSP( String idHD, String idSP, int SLSP, int SLHDCT) {
        return dao.UpdateTrungSP(idHD, idSP, SLSP, SLHDCT);
    }
    
    public boolean RemoveGH( String idHD, String idSP, int SLSP) {
        return dao.RemoveGH(idHD, idSP, SLSP);
    }
    
    public boolean huyHD( List<ChiTietSanPham> spctList, String idHD, int SLSP) {
        return dao.huyHD(spctList, idHD, SLSP);
    }
    
    public boolean thanhToan(String idHD, String idHTTT, float tienMat, float tienCK, String idKH, String idMaGG) {
        return dao.thanhToan(idHD, idHTTT, tienMat, tienCK, idKH, idMaGG);
    }
    
    public boolean thanhToan1(String idHD, String idHTTT, float tienMat, float tienCK, String idKH, String idMaGG, int soLuong) {
        return dao.thanhToan1(idHD, idHTTT, tienMat, tienCK, idKH, idMaGG, soLuong);
    }
    public List<BanHangViewModel> getAll1(String idSP) {
        return dao.getAll1(idSP);
    }
//    public List<BanHangViewModel> timKetHop(String m, String nxs, String cl, String kc) {
//        return dao.timKetHop(m, nxs, cl, kc);
//    }
}
