/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ThongKe;

import java.util.Date;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class SoHoaDonService {

    private SoHoaDonRepositoty repo = new SoHoaDonRepositoty();

    public List<SoHoaDonEntity> getSoHoaDon() {
        return repo.getSoHoaDon();
    }
  public List<SoHoaDonEntity> search(String type) {
        return repo.search(type);
    }

    public List<SoHoaDonEntity> getCBO(String month) {
        return repo.getCBO(month);
    }

    public List<SoHoaDonEntity> getCBONam(String year) {
        return repo.getCBONam(year);
    }
}
