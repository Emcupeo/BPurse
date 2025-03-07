/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bpurse.qlts.service;

import bpurse.qlts.repo.SanPhamRepo2;
import bpurse.qlts.viewmodel.CTSPViewmodel;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class SanPhamService2 {

    private SanPhamRepo2 repo = new SanPhamRepo2();

    public List<CTSPViewmodel> getAll() {
        return repo.getAll();
    }

    public List<CTSPViewmodel> getTenSP() {
        return repo.getTenSP();
    }

    public List<CTSPViewmodel> search2(String search) {
        return repo.search2(search);
    }
    
    public List<CTSPViewmodel> getAll2(String id) {
        return repo.getAll2(id);
    }
    public List<CTSPViewmodel> getAll3(String id) {
        return repo.getAll3(id);
    }
}
