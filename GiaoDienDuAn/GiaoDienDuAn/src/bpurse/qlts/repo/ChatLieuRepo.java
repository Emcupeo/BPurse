/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bpurse.qlts.repo;

import bpurse.qlts.entity.ChatLieu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ChatLieuRepo {
    public List<ChatLieu> getTenCL() {
        List<ChatLieu> lists = new ArrayList<>();
        String sql = """
                     SELECT [ID]
                           ,[TenChatLieu]
                           ,[MaChatLieu]
                       FROM [dbo].[ChatLieu]
                     where Deleted = 1
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChatLieu cl = new ChatLieu();
                cl.setID(rs.getString(1));
                cl.setTenChatLieu(rs.getString(2));
                cl.setMaCL(rs.getString(3));
                lists.add(cl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }
}
