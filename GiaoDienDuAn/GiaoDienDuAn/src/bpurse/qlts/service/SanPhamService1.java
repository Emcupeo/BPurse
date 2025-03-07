/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bpurse.qlts.service;

import bpurse.qlts.entity.SanPham;
import bpurse.qlts.repo.SanPhamRepo1;
import bpurse.qlts.viewmodel.SanPhamViewmodel1;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class SanPhamService1 {

    private SanPhamRepo1 repo = new SanPhamRepo1();

    public List<SanPhamViewmodel1> getAll() {
        return repo.getAll();
    }

    public boolean add(SanPham spv) {
        return repo.add(spv);
    }

    public boolean xoaSP(String ma) {
        return repo.xoaSP(ma);
    }

    public List<SanPhamViewmodel1> search(String search) {
        return repo.search(search);
    }

    public boolean update(SanPham spv, String oldID) {
        return repo.update(spv, oldID);
    }

}
