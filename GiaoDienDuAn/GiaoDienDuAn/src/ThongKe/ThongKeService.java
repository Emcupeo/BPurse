/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ThongKe;

import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ThongKeService {

    private ThongKeRepositoty repo = new ThongKeRepositoty();

    public List<ThongKeEntity> getThongKe() {
        return repo.getThongKe();
    }
    
    public List<ThongKeEntity> getHoaDon() {
        return repo.getHoaDon();
    }
        public List<ThongKeEntity> getKhachHang() {
            return repo.getKhachHang();
        }
}
