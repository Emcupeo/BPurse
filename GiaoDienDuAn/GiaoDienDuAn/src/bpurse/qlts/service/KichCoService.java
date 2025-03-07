/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bpurse.qlts.service;

import bpurse.qlts.entity.KichCo;
import bpurse.qlts.repo.KichCoRepo;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class KichCoService {

    private KichCoRepo repo = new KichCoRepo();

    public List<KichCo> getTenKC() {
        return repo.getTenKC();
    }
}
