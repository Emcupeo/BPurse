/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bpurse.qlts.repo;

import bpurse.qlts.entity.PhanLoai;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class PhanLoaiRepo {
     public List<PhanLoai> getTenPL() {
        List<PhanLoai> lists = new ArrayList<>();
        String sql = """
                     SELECT [ID]
                           ,[PhanLoai]
                           ,[MaPhanLoai]
                       FROM [dbo].[PhanLoai]
                     where Deleted = 1
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PhanLoai pl = new PhanLoai();
                pl.setID(rs.getString(1));
                pl.setTenPhanLoai(rs.getString(2));
                pl.setMaPhanLoai(rs.getString(3));
                lists.add(pl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }
}
