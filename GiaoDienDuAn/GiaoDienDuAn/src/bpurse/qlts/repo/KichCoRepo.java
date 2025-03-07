/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bpurse.qlts.repo;

import bpurse.qlts.entity.KichCo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class KichCoRepo {
    
    public List<KichCo> getTenKC() {
        List<KichCo> lists = new ArrayList<>();
        String sql = """
                     SELECT [ID]
                           ,[TenKichCo]
                           ,[MaKichCo]
                       FROM [dbo].[KichCo]
                     where Deleted = 1
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KichCo kc = new KichCo();
                kc.setID(rs.getString(1));
                kc.setTenKichCo(rs.getString(2));
                kc.setMaKichCo(rs.getString(3));
                lists.add(kc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }
}
