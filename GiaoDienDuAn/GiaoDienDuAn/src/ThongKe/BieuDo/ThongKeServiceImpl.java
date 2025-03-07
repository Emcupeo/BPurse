/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ThongKe.BieuDo;

import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ThongKeServiceImpl implements ThongKeService {

    private ThongKeDao thongKeDao = null;

    public ThongKeServiceImpl() {
        thongKeDao = new ThongKeDaoImpl();
    }

    @Override
    public List<LopHocBean> getListByLopHoc() {
        return thongKeDao.getListByLopHoc();
    }

}
