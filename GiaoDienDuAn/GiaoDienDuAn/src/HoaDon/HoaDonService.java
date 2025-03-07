/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HoaDon;

import HoaDon.HoaDonRepository;
import HoaDon.HoaDonViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author My PC
 */
public class HoaDonService {
    List<HoaDonViewModel> ListSV = new ArrayList<>();
    private HoaDonRepository repo = new HoaDonRepository();

    public List<HoaDonViewModel> getAll() {
        return repo.getAll();
    }
    
    public List<HoaDonViewModel> searchGiaTien(float min, float max) {
        return repo.searchGiaTien(min, max);
    }
    public List<HoaDonViewModel> search(String timKiem) {
        return repo.search(timKiem);
    }
    public List<HoaDonViewModel> trangThai(String trangThai){
        return repo.trangThai(trangThai);
    }

    public boolean xuatHoaDon() {
       return repo.xuatHoaDon();
    }
    
    public List<HoaDonViewModel> getIDHD(String idHD) {
        return repo.getIDHD(idHD);
    }
    
}
