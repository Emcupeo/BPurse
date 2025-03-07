/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bpurse.qlts.repo;

import bpurse.qlts.entity.MauSac;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class MauSacRepo {
    public List<MauSac> getTenMS() {
        List<MauSac> lists = new ArrayList<>();
        String sql = """
                     SELECT [ID]
                           ,[TenMauSac]
                           ,[MaMauSac]
                       FROM [dbo].[MauSac]
                     where Deleted = 1
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MauSac ms = new MauSac();
                ms.setID(rs.getString(1));
                ms.setTenMauSac(rs.getString(2));
                ms.setMaMauSac(rs.getString(3));
                lists.add(ms);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }
}
