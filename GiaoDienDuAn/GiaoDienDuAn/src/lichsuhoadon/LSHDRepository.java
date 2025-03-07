/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lichsuhoadon;

import lichsuhoadon.LSHDViewModel;

import bpurse.qlts.repo.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author My PC
 */
public class LSHDRepository {
     public List<LSHDViewModel> getAll(String idHoaDon){
        List<LSHDViewModel> ListKH = new ArrayList<>();

        String sql = """
          
                  	SELECT 
                     dbo.LichSuHoaDon.CreatedBy
                     , dbo.LichSuHoaDon.idHoaDon
                     , DATEPART(hour, dbo.LichSuHoaDon.CreatedAt)
                     , dbo.LichSuHoaDon.UpdatedAt
                     , dbo.LichSuHoaDon.HanhDong
                   	FROM     dbo.HoaDon
                   	INNER JOIN
                   
                   					  dbo.LichSuHoaDon ON dbo.HoaDon.ID = dbo.LichSuHoaDon.idHoaDon
                   					 where idHoaDon = ?
                   						  """;
        try (Connection con = new DBConnect().getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, idHoaDon);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LSHDViewModel kh = new LSHDViewModel();
                kh.setNhanVien(rs.getString(1));
                kh.setIdHoaDon(rs.getString(2));
                kh.setGio(rs.getInt(3));
                kh.setNgay(rs.getDate(4));
                kh.setHanhDong(rs.getString(5));
                
                ListKH.add(kh);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ListKH;
    }
}
