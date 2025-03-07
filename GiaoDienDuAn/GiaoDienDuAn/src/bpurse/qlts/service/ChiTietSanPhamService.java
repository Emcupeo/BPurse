/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bpurse.qlts.service;

import bpurse.qlts.entity.ChiTietSanPham;
import bpurse.qlts.repo.ChiTietSanPhamRepo;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ChiTietSanPhamService {

    private ChiTietSanPhamRepo repo = new ChiTietSanPhamRepo();

    public List<ChiTietSanPham> getID() {
        return repo.getID();
    }

    public List<ChiTietSanPham> getMaCTSP() {
        return repo.getMaCTSP();
    }

    public boolean addCTSP(ChiTietSanPham sv, String oldMa) {
        return repo.addCTSP(sv, oldMa);
    }

    public boolean updateCTSP(ChiTietSanPham sv, String oldMa) {
        return repo.updateCTSP(sv, oldMa);
    }

    public boolean xoaCTSP(String ma) {
        return repo.xoaCTSP(ma);
    }

    public boolean xuatHoaDon() {
        return repo.xuatHoaDon();
    }
}
