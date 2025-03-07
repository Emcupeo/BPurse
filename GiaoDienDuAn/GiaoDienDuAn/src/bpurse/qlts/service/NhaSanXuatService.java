/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bpurse.qlts.service;

import bpurse.qlts.entity.NhaSanXuat;
import bpurse.qlts.repo.NhaSanXuatRepo;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class NhaSanXuatService {
    private NhaSanXuatRepo repo = new NhaSanXuatRepo();
          public List<NhaSanXuat> getTenNSX() {
              return repo.getTenNSX();
          }
}
