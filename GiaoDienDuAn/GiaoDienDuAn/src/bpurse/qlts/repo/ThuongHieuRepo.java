/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bpurse.qlts.repo;

import bpurse.qlts.entity.ThuongHieu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ThuongHieuRepo {

    public List<ThuongHieu> getTenTH() {
        List<ThuongHieu> lists = new ArrayList<>();
        String sql = """
                     SELECT [ID]
                           ,[ThuongHieu]
                           ,[MaThuongHieu]
                       FROM [dbo].[ThuongHieu]
                     where Deleted = 1
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ThuongHieu th = new ThuongHieu();
                th.setID(rs.getString(1));
                th.setTenThuongHieu(rs.getString(2));
                th.setMaThuongHieu(rs.getString(3));
                lists.add(th);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }
}
