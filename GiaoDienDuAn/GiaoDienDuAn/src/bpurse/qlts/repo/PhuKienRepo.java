/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bpurse.qlts.repo;

import bpurse.qlts.entity.PhuKien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class PhuKienRepo {
        public List<PhuKien> getTenPK() {
        List<PhuKien> lists = new ArrayList<>();
        String sql = """
                     SELECT [ID]
                           ,[LoaiPhuKien]
                           ,[MaPhuKien]
                       FROM [dbo].[PhuKien]
                     where Deleted = 1
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PhuKien pk = new PhuKien();
                pk.setID(rs.getString(1));
                pk.setTenPhuKien(rs.getString(2));
                pk.setMaPhuKien(rs.getString(3));
                lists.add(pk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }
}
