/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bpurse.qlts.repo;

import bpurse.qlts.entity.TinhTrang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author My PC
 */
public class TinhTrangRepo {
    
    public List<TinhTrang> getTenTT() {
        List<TinhTrang> lists = new ArrayList<>();
        String sql = """
                     SELECT [ID]
                           ,[TinhTrang]
                           ,[MaTinhTrang]
                       FROM [dbo].[TinhTrang]
                     where Deleted = 1
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TinhTrang th = new TinhTrang();
                th.setID(rs.getString(1));
                th.setTenTinhTrang(rs.getString(2));
                th.setMaTinhTrang(rs.getString(3));
                lists.add(th);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }
    
}
