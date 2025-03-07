/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NhanVien.service;

import NhanVien.entity.ChucVu;
import NhanVien.repository.ChucVuRepository;
import java.util.List;

/**
 *
 * @author NguyenVanKien
 */
public class ChucVuService {
    private ChucVuRepository repo=new ChucVuRepository();
    
    public List<ChucVu> getAll(){
        return repo.getAll();
    }
    public ChucVu getID(String ma){
        return repo.getID(ma);
    }
}
