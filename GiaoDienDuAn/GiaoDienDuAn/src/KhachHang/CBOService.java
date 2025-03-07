/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KhachHang;

import java.util.List;

/**
 *
 * @author ADMIN
 */
public class CBOService {

    CBORepositoty repo = new CBORepositoty();

    public List<KhachHangEntity> getCBO() {
        return repo.getCBO();
    }
}
