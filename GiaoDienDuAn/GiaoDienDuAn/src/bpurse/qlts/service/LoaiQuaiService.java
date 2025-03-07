/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bpurse.qlts.service;

import bpurse.qlts.entity.LoaiQuai;
import bpurse.qlts.repo.LoaiQuaiRepo;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class LoaiQuaiService {
       private LoaiQuaiRepo repo = new LoaiQuaiRepo();
      public List<LoaiQuai> getTenLQ() {
          return repo.getTenLQ();
      }
}
