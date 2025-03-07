/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bpurse.qlts.service;

import bpurse.qlts.entity.TinhTrang;
import bpurse.qlts.repo.TinhTrangRepo;
import java.util.List;

/**
 *
 * @author My PC
 */
public class TinhTrangService {
    private TinhTrangRepo repo = new TinhTrangRepo();
          public List<TinhTrang> getTenTT() {
              return repo.getTenTT();
          }
}
