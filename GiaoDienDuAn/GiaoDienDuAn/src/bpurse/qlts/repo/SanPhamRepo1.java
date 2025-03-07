/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bpurse.qlts.repo;

import bpurse.qlts.entity.ChiTietSanPham;
import bpurse.qlts.entity.SanPham;
import bpurse.qlts.viewmodel.SanPhamViewmodel1;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class SanPhamRepo1 {
    public List<ChiTietSanPham> getTT(){
        List<ChiTietSanPham> lists = new ArrayList<>();
        String sql ="""
                    SELECT TOP (1000) [ID]
                          ,[trangThai]
                      FROM [BPURSE_SOF102_SD1702].[dbo].[ChiTietSanPham]
                    where Deleted = 1
                    """;
        try(Connection con =DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                ChiTietSanPham ctsp = new ChiTietSanPham();
                ctsp.setTrangThai(rs.getString(1));
                lists.add(ctsp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }

    public List<SanPhamViewmodel1> getAll() {
        List<SanPhamViewmodel1> lists = new ArrayList<>();
        String sql = """
                    SELECT        dbo.SanPham.MaSanPham, dbo.SanPham.TenSanPham, dbo.ChiTietSanPham.MoTa, dbo.ChiTietSanPham.SoLuong,dbo.SanPham.ID
                    FROM            dbo.SanPham INNER JOIN
                                             dbo.ChiTietSanPham ON dbo.SanPham.ID = dbo.ChiTietSanPham.MaSanPham
                     where SanPham.Deleted = 1
                     order by dbo.SanPham.CreatedAt desc
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamViewmodel1 sp1 = new SanPhamViewmodel1();
                sp1.setMaSP(rs.getString(1));
                sp1.setTenSP(rs.getString(2));
                sp1.setMoTa(rs.getString(3));
                sp1.setSoLuong(rs.getInt(4));
                sp1.setIdSP(rs.getString(5));

                lists.add(sp1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }

    public boolean add(SanPham spv) {
        int check = 0;
        String sql = """
                   INSERT INTO [dbo].[SanPham]
                                                     ([TenSanPham])
                                               VALUES
                                                     (?)
                     
                     
                     INSERT INTO [dbo].[ChiTietSanPham]
                                                     (MaSanPham)
                                               VALUES
                                                     ((Select top 1 [ID] from [dbo].[SanPham] order by [dbo].[SanPham].CreatedAt desc))
                     
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, spv.getTenSanPham());
            check = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;

    }
     
      public boolean update(SanPham spv, String oldID) {
        int check = 0;
        String sql = """
                     UPDATE [dbo].[SanPham]
                        SET [TenSanPham] = ?
                      WHERE [ID] like ?
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, spv.getTenSanPham());
            ps.setObject(2, oldID);
            
            check = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;

    }

    public boolean xoaSP(String ma) {
        int check = 0;

        String sql = """    
                    UPDATE [dbo].[SanPham]
                       SET [Deleted] = 0

                    
                     WHERE [dbo].[SanPham].[MaSanPham]  = ?
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, ma);
            check = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public List<SanPhamViewmodel1> search(String search) {
        List<SanPhamViewmodel1> lists = new ArrayList<>();
        String sql = """
                  SELECT       
                                      dbo.SanPham.MaSanPham, 
                                      dbo.SanPham.TenSanPham, 
                                      dbo.ChiTietSanPham.SoLuong
                                      FROM            
                                      dbo.SanPham INNER JOIN
                                      dbo.ChiTietSanPham ON dbo.SanPham.ID = dbo.ChiTietSanPham.MaSanPham
                                      where SanPham.Deleted = 1 and ChiTietSanPham.Deleted = 1 and SanPham.TenSanPham like ?
                                    or ChiTietSanPham.SoLuong like ?
                   """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareCall(sql)) {
            ps.setObject(1, '%' + search + '%');
            ps.setObject(2, '%' + search + '%');
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                SanPhamViewmodel1 sp = new SanPhamViewmodel1();
                sp.setMaSP(rs.getString(1));
                sp.setTenSP(rs.getString(2));
                sp.setSoLuong(rs.getInt(3));
                lists.add(sp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }

}
