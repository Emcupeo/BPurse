/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lichsuhoadon;

import lichsuhoadon.*;
import lichsuhoadon.LSHDViewModel;

import lichsuhoadon.LSHDRepository;
import HoaDon.HoaDonRepository;
import HoaDon.HoaDonViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author My PC
 */
public class LSHDService {
    
    List<LSHDViewModel> ListSV = new ArrayList<>();
    private LSHDRepository repo = new LSHDRepository();

    public List<LSHDViewModel> getAll(String idHoaDon) {
        return repo.getAll(idHoaDon);
    }
}
