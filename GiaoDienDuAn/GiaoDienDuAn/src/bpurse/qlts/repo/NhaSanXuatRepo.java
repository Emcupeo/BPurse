/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bpurse.qlts.repo;

import bpurse.qlts.entity.NhaSanXuat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class NhaSanXuatRepo {

    public List<NhaSanXuat> getTenNSX() {
        List<NhaSanXuat> lists = new ArrayList<>();
        String sql = """
                     SELECT [ID]
                           ,[TenNhaSanXuat]
                           ,[MaNhaSanXuat]
                       FROM [dbo].[NhaSanXuat]
                     where Deleted = 1
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhaSanXuat th = new NhaSanXuat();
                th.setID(rs.getString(1));
                th.setTenNhaSanXuat(rs.getString(2));
                th.setMaNSX(rs.getString(3));
                lists.add(th);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }
}
