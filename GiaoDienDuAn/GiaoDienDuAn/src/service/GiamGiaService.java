/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.GiamGiaDao;
import model.GiamGiaEntity;
import java.util.List;
import viewmodel.GiamGiaViewModel;

/**
 *
 * @author tung pc
 */
public class GiamGiaService {

    private GiamGiaDao dao = new GiamGiaDao();

    public List<GiamGiaViewModel> timTheoTT(String tim) {
        return dao.timTheoTT(tim);
    }

    public List<GiamGiaViewModel> getAll() {
        return dao.getAll();
    }
    public List<GiamGiaViewModel> getAll1() {
        return dao.getAll1();
    }

    public List<GiamGiaViewModel> timTheoTen(String search) {
        return dao.timTheoTen(search);
    }

    public boolean them(GiamGiaEntity gg) {
        return dao.them(gg);
    }

    public boolean sua(GiamGiaEntity gg, String oldId) {
        return dao.sua(gg, oldId);
    }

    public boolean xoa(String maPhieuGiamGia) {
        return dao.xoa(maPhieuGiamGia);
    }

    public boolean ketThuc(String maPhieuGiamGia) {
        return dao.ketThuc(maPhieuGiamGia);
    }

    public String taoMoiMaPhieu() {
        return dao.taoMoiMaPhieu();
    }
}
