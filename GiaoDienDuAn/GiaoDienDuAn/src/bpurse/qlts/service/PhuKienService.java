/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bpurse.qlts.service;

import bpurse.qlts.entity.PhuKien;
import bpurse.qlts.repo.PhuKienRepo;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class PhuKienService {
    private PhuKienRepo repo = new PhuKienRepo();
      public List<PhuKien> getTenPK() {
          return repo.getTenPK();
      }
}
