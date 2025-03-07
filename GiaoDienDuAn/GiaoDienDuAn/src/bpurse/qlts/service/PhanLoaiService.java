/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bpurse.qlts.service;

import bpurse.qlts.entity.PhanLoai;
import bpurse.qlts.repo.PhanLoaiRepo;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class PhanLoaiService {

    private PhanLoaiRepo repo = new PhanLoaiRepo();

    public List<PhanLoai> getTenPL() {
        return repo.getTenPL();
    }
}
