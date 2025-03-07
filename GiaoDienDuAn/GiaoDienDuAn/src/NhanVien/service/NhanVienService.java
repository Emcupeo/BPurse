/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NhanVien.service;

import NhanVien.entity.NhanVien;
import NhanVien.repository.NhanVienRepository;
import NhanVien.viewmodel.NhanVienViewModel;
import java.util.List;

/**
 *
 * @author NguyenVanKien
 */
public class NhanVienService {
    private NhanVienRepository repo=new NhanVienRepository();
    
    public List<NhanVienViewModel> getAll(){
        return repo.getAll();
    }
    
     public List<NhanVienViewModel> getAllChucVu(String id) {
         return repo.getAllChucVu(id);
     }
    
    public String add(NhanVien nv){
        if(nv==null){
            return "Thêm thất bại";
            
        }else{
           repo.add(nv); 
           return "Thêm thành công";
        }
        
    }
    
    
    
    public boolean delete(String id,int trangThai){
        return repo.delete(id,trangThai);
    }
    
    public String update(NhanVien nv,String oldID){
        if(nv==null){
            return "Sửa thất bại";
        }else{
            repo.update(nv, oldID);
            return "Sửa thành công";
        }
        
    }
    
    public String gioiTinh(boolean gt){
        if(gt==true){
            return "1";
        }else{
            return "0";
        }
    }
    
    public String trangThai(boolean tt){
        if(tt==true){
            return "Đang làm";
    }else{
            return "Đã nghỉ";
        }
    }
    
    public List<NhanVienViewModel> getAllTrangThai(int tt) {
        return repo.getAllTrangThai(tt);
    }
    
    public List<NhanVienViewModel> getAllGioiTinh(String gt) {
        return repo.getAllGioiTinh(gt);
    }
    
    public List<NhanVienViewModel> search(String search) {
        return repo.search(search);
    }
    
    
    public List<NhanVienViewModel> getAllCCCD(String cccd) {
        return repo.getAllCCCD(cccd);
    }
    
//    public boolean xuatNhanVien() {
//       return repo.xuatNhanVien();
//    }
}
