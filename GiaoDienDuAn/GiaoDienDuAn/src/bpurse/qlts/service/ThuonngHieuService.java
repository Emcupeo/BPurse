/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bpurse.qlts.service;

import bpurse.qlts.entity.ThuongHieu;
import bpurse.qlts.repo.ThuongHieuRepo;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ThuonngHieuService {
    private ThuongHieuRepo repo = new ThuongHieuRepo();
          public List<ThuongHieu> getTenTH() {
              return repo.getTenTH();
          }
}
