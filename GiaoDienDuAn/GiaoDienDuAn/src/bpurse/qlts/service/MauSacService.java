/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bpurse.qlts.service;

import bpurse.qlts.entity.MauSac;
import bpurse.qlts.repo.MauSacRepo;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class MauSacService {

    private MauSacRepo repo = new MauSacRepo();

    public List<MauSac> getTenMS() {
        return repo.getTenMS();
    }
}
