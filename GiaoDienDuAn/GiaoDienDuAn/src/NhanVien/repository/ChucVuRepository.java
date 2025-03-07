/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NhanVien.repository;

import NhanVien.entity.ChucVu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NguyenVanKien
 */
public class ChucVuRepository {
    public List<ChucVu> getAll(){
        List<ChucVu> listCV=new ArrayList<>();
        String sql="""
                   SELECT [ID]
                         ,[TenChucVu]
                         
                         ,[MaChucVu]
                     FROM [dbo].[ChucVu]
                   where Deleted=1
                   """;
        
        try (Connection con=DBConnect.getConnection();PreparedStatement ps=con.prepareStatement(sql)){
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                ChucVu cv=new ChucVu();
                cv.setID(rs.getString(1));
                cv.setTenChucVu(rs.getString(2));
                cv.setMaChucVu(rs.getString(3));
                listCV.add(cv);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCV;
    }
    
    public ChucVu getID(String ma){
        
        String sql="""
                   SELECT [ID]
                         ,[TenChucVu]
                         
                         ,[MaChucVu]
                     FROM [dbo].[ChucVu]
                   where [TenChucVu] like ?
                   """;
        
        try (Connection con=DBConnect.getConnection();PreparedStatement ps=con.prepareStatement(sql)){
            ps.setObject(1, ma);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                ChucVu cv=new ChucVu();
                cv.setID(rs.getString(1));
                cv.setTenChucVu(rs.getString(2));
                cv.setMaChucVu(rs.getString(3));
                
                return cv;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
