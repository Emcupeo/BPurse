/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bpurse.qlts.repo;

import bpurse.qlts.entity.LoaiQuai;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class LoaiQuaiRepo {

    public List<LoaiQuai> getTenLQ() {
        List<LoaiQuai> lists = new ArrayList<>();
        String sql = """
                     SELECT [ID]
                           ,[TenLoaiQuai]
                           ,[MaLoaiQuai]
                       FROM [dbo].[LoaiQuai]
                     where Deleted = 1
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LoaiQuai lq = new LoaiQuai();
                lq.setID(rs.getString(1));
                lq.setTenLoaiQuai(rs.getString(2));
                lq.setMaLoaiQuai(rs.getString(3));
                lists.add(lq);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }
}
