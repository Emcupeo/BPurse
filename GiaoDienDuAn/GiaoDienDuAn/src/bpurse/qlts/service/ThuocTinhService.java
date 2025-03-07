/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bpurse.qlts.service;

import bpurse.qlts.entity.ChatLieu;
import bpurse.qlts.entity.KichCo;
import bpurse.qlts.entity.LoaiQuai;
import bpurse.qlts.entity.MauSac;
import bpurse.qlts.entity.NhaSanXuat;
import bpurse.qlts.entity.PhanLoai;
import bpurse.qlts.entity.PhuKien;
import bpurse.qlts.entity.ThuongHieu;
import bpurse.qlts.entity.TinhTrang;
import bpurse.qlts.repo.ThuocTinhRepo;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ThuocTinhService {

    private ThuocTinhRepo repo = new ThuocTinhRepo();

    //
    public List<NhaSanXuat> getNSX() {
        return repo.getNSX();
    }

    public List<ChatLieu> getCL() {
        return repo.getCL();
    }

    public List<MauSac> getMS() {
        return repo.getMS();
    }

    public List<PhuKien> getPK() {
        return repo.getPK();
    }

    public List<LoaiQuai> getLQ() {
        return repo.getLQ();
    }

    public List<ThuongHieu> getTH() {
        return repo.getTH();
    }

    public List<PhanLoai> getPL() {
        return repo.getPL();
    }

    public List<KichCo> getKC() {
        return repo.getKC();
    }

    public List<TinhTrang> getTT() {
        return repo.getTT();
    }

    ////add
    public boolean addNSX(NhaSanXuat nsx) {
        return repo.addNSX(nsx);
    }

    public boolean addCL(ChatLieu cl) {
        return repo.addCL(cl);
    }

    public boolean addMS(MauSac ms) {
        return repo.addMS(ms);
    }

    public boolean addPK(PhuKien pk) {
        return repo.addPK(pk);
    }

    public boolean addLQ(LoaiQuai lq) {
        return repo.addLQ(lq);
    }

    public boolean addTH(ThuongHieu th) {
        return repo.addTH(th);
    }

    public boolean addPL(PhanLoai pl) {
        return repo.addPL(pl);
    }

    public boolean addKC(KichCo kc) {
        return repo.addKC(kc);
    }

    public boolean addTT(TinhTrang tt) {
        return repo.addTT(tt);
    }

    //////update
    public boolean updateMS(MauSac ms, String id) {
        return repo.updateMS(ms, id);
    }

    public boolean updateNSX(NhaSanXuat nsx, String id) {
        return repo.updateNSX(nsx, id);
    }

    public boolean updateCL(ChatLieu cl, String id) {
        return repo.updateCL(cl, id);
    }

    public boolean updatePK(PhuKien pk, String id) {
        return repo.updatePK(pk, id);
    }

    public boolean updateLQ(LoaiQuai lq, String id) {
        return repo.updateLQ(lq, id);
    }

    public boolean updateTH(ThuongHieu th, String id) {
        return repo.updateTH(th, id);
    }

    public boolean updatePL(PhanLoai pl, String id) {
        return repo.updatePL(pl, id);
    }

    public boolean updateKC(KichCo kc, String id) {
        return repo.updateKC(kc, id);
    }

    public boolean updateTT(TinhTrang tt, String id) {
        return repo.updateTT(tt, id);
    }

    ////delete
    public boolean removeNSX(String id) {
        return repo.removeNSX(id);
    }

    public boolean removeCL(String id) {
        return repo.removeCL(id);
    }

    public boolean removeMS(String id) {
        return repo.removeMS(id);
    }

    public boolean removeLQ(String id) {
        return repo.removeLQ(id);
    }

    public boolean removeTH(String id) {
        return repo.removeTH(id);
    }

    public boolean removePL(String id) {
        return repo.removePL(id);
    }

    public boolean removeKC(String id) {
        return repo.removeKC(id);
    }

    public boolean removeTT(String id) {
        return repo.removeTT(id);
    }

    public boolean removePK(String id) {
        return repo.removePK(id);
    }

}
