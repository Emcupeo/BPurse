/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HoaDonChiTiet;

import HoaDonChiTiet.HoaDonChiTietViewModel;
import bpurse.qlts.repo.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author My PC
 */
public class HoaDonChiTietRepository {
     public List<HoaDonChiTietViewModel> getAll(String idHoaDon){
        List<HoaDonChiTietViewModel> ListKH = new ArrayList<>();

        String sql = """
                   SELECT dbo.ChiTietSanPham.ID
                     , dbo.SanPham.TenSanPham
                     , dbo.HoaDonChiTiet.SoLuong
                     , dbo.HoaDonChiTiet.DonGia
                     , dbo.HoaDon.TongTien
                     , dbo.HoaDon.ID
                     , dbo.HoaDonChiTiet.MaHoaDonChiTiet
                     , dbo.HoaDonChiTiet.ID
                     , dbo.ChatLieu.TenChatLieu
                     , dbo.MauSac.TenMauSac
                     , dbo.PhuKien.LoaiPhuKien
                     , dbo.ThuongHieu.ThuongHieu
                     , dbo.ChiTietSanPham.MaCTSP
                FROM     dbo.ChiTietSanPham INNER JOIN
                                  dbo.HoaDonChiTiet ON dbo.ChiTietSanPham.ID = dbo.HoaDonChiTiet.idChiTietSP INNER JOIN
                                  dbo.HoaDon ON dbo.HoaDonChiTiet.idHoaDon= dbo.HoaDon.ID INNER JOIN
                                  dbo.SanPham ON dbo.ChiTietSanPham.MaSanPham = dbo.SanPham.ID INNER JOIN
                                 dbo.ChatLieu ON dbo.ChiTietSanPham.idChatLieu = dbo.ChatLieu.ID INNER JOIN
                                 dbo.MauSac ON dbo.ChiTietSanPham.idMauSac = dbo.MauSac.ID INNER JOIN
                                 dbo.PhuKien ON dbo.ChiTietSanPham.idPhuKien = dbo.PhuKien.ID INNER JOIN
                                 dbo.ThuongHieu ON dbo.ChiTietSanPham.idThuongHieu = dbo.ThuongHieu.ID
                where idHoaDon like ?
                   """;
        try (Connection con = new DBConnect().getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, idHoaDon);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTietViewModel kh = new HoaDonChiTietViewModel();
                kh.setMaSanPham(rs.getString(1));
                kh.setTenSP(rs.getString(2));
                kh.setSoLuong(rs.getInt(3));
                kh.setDonGia(rs.getFloat(4));
                kh.setThanhTien(rs.getFloat(5));
                kh.setIdHoaDon(rs.getString(6));
                kh.setMaHDCT(rs.getString(7));
                kh.setIdHDCT(rs.getString(8));
                kh.setTenCL(rs.getString(9));
                kh.setTenMS(rs.getString(10));
                kh.setTenPK(rs.getString(11));
                kh.setTenTH(rs.getString(12));
                kh.setIDSP(rs.getString(13));
                
                
                ListKH.add(kh);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ListKH;
    }
}
