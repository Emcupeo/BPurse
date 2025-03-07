/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HoaDonChiTiet;

import HoaDonChiTiet.HoaDonChiTietViewModel;

import HoaDonChiTiet.HoaDonChiTietRepository;
import HoaDon.HoaDonRepository;
import HoaDon.HoaDonViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author My PC
 */
public class HoaDonChiTietService {
    List<HoaDonChiTietViewModel> ListSV = new ArrayList<>();
    private HoaDonChiTietRepository repo = new HoaDonChiTietRepository();

    public List<HoaDonChiTietViewModel> getAll(String idHoaDon) {
        return repo.getAll(idHoaDon);
    }
}
