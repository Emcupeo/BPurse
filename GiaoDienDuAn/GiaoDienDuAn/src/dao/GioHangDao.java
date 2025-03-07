/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import HoaDonChiTiet.HoaDonChiTietViewModel;
import bpurse.qlts.repo.DBConnect;
import java.util.ArrayList;
import java.util.List;
import viewmodel.GioHangViewModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import viewmodel.BanHangViewModel;

/**
 *
 * @author tung pc
 */
public class GioHangDao {

//    public List<GioHangViewModel> getHD() {
//
//        List<GioHangViewModel> listGH = new ArrayList<>();
//
//        String sql = """
//            SELECT dbo.ChiTietSanPham.MaCTSP, dbo.SanPham.TenSanPham, dbo.MauSac.TenMauSac, dbo.ChatLieu.TenChatLieu, dbo.KichCo.TenKichCo, dbo.ChiTietSanPham.GiaBan
//              FROM   dbo.ChatLieu INNER JOIN
//                           dbo.ChiTietSanPham ON dbo.ChatLieu.ID = dbo.ChiTietSanPham.idChatLieu INNER JOIN
//                           dbo.KichCo ON dbo.ChiTietSanPham.idKichCo = dbo.KichCo.ID INNER JOIN
//                           dbo.MauSac ON dbo.ChiTietSanPham.idMauSac = dbo.MauSac.ID INNER JOIN
//                           dbo.SanPham ON dbo.ChiTietSanPham.MaSanPham = dbo.SanPham.ID
//                     """;
//
//        try ( Connection con = DBconnect.getConnection();  PreparedStatement ps = con.prepareCall(sql)) {
//
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                GioHangViewModel gh = new GioHangViewModel();
//                gh.setMa(rs.getString(1));
//                gh.setTen(rs.getString(2));
//                gh.setMau(rs.getString(3));
//                gh.setChatLieu(rs.getString(4));
//                gh.setKichCo(rs.getString(5));
//                gh.setGia(rs.getDouble(6));
//                listGH.add(gh);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return listGH;
//    }
//
//    public List<GioHangViewModel> getGH(String ID) {
//        List<GioHangViewModel> listGH = new ArrayList<>();
//
//        String sql = """
//        SELECT dbo.ChiTietSanPham.MaCTSP, dbo.SanPham.TenSanPham, dbo.MauSac.TenMauSac, dbo.ChatLieu.TenChatLieu, dbo.KichCo.TenKichCo, dbo.ChiTietSanPham.GiaBan
//        FROM   dbo.ChatLieu INNER JOIN
//                dbo.ChiTietSanPham ON dbo.ChatLieu.ID = dbo.ChiTietSanPham.idChatLieu INNER JOIN
//                dbo.HoaDon ON dbo.ChatLieu.ID = dbo.HoaDon.ID INNER JOIN
//                dbo.KichCo ON dbo.ChiTietSanPham.idKichCo = dbo.KichCo.ID INNER JOIN
//                dbo.MauSac ON dbo.ChiTietSanPham.idMauSac = dbo.MauSac.ID INNER JOIN
//                dbo.SanPham ON dbo.ChiTietSanPham.MaSanPham = dbo.SanPham.ID
//        		WHERE dbo.HoaDon.ID = ?
//                 """;
//
//        try ( Connection con = DBconnect.getConnection();  PreparedStatement ps = con.prepareCall(sql)) {
//            ps.setString(1, ID);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                GioHangViewModel gh = new GioHangViewModel();
//                gh.setMa(rs.getString(1));
//                gh.setTen(rs.getString(2));
//                gh.setMau(rs.getString(3));
//                gh.setChatLieu(rs.getString(4));
//                gh.setKichCo(rs.getString(5));
//                gh.setGia(rs.getDouble(6));
//                listGH.add(gh);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return listGH;
//    }

    public List<GioHangViewModel> getGioHang(String idHoaDon){
        List<GioHangViewModel> ListKH = new ArrayList<>();

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
                     , dbo.ChiTietSanPham.SoLuong
                FROM     dbo.ChiTietSanPham INNER JOIN
                                  dbo.HoaDonChiTiet ON dbo.ChiTietSanPham.ID = dbo.HoaDonChiTiet.idChiTietSP INNER JOIN
                                  dbo.HoaDon ON dbo.HoaDonChiTiet.idHoaDon= dbo.HoaDon.ID INNER JOIN
                                  dbo.SanPham ON dbo.ChiTietSanPham.MaSanPham = dbo.SanPham.ID INNER JOIN
                                 dbo.ChatLieu ON dbo.ChiTietSanPham.idChatLieu = dbo.ChatLieu.ID INNER JOIN
                                 dbo.MauSac ON dbo.ChiTietSanPham.idMauSac = dbo.MauSac.ID INNER JOIN
                                 dbo.PhuKien ON dbo.ChiTietSanPham.idPhuKien = dbo.PhuKien.ID INNER JOIN
                                 dbo.ThuongHieu ON dbo.ChiTietSanPham.idThuongHieu = dbo.ThuongHieu.ID
                where ChiTietSanPham.Deleted = 1 and HoaDon.Deleted = 1  and idHoaDon like ?
                   """;
        try (Connection con = new DBConnect().getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, idHoaDon);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GioHangViewModel kh = new GioHangViewModel();
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
                kh.setSoLuongSP(rs.getInt(13));
                
                
                ListKH.add(kh);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ListKH;
    }
    
    public List<GioHangViewModel> getGioHangQR(String idHoaDon){
        List<GioHangViewModel> ListKH = new ArrayList<>();

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
                     , dbo.ChiTietSanPham.SoLuong
                FROM     dbo.ChiTietSanPham INNER JOIN
                                  dbo.HoaDonChiTiet ON dbo.ChiTietSanPham.ID = dbo.HoaDonChiTiet.idChiTietSP INNER JOIN
                                  dbo.HoaDon ON dbo.HoaDonChiTiet.idHoaDon= dbo.HoaDon.ID INNER JOIN
                                  dbo.SanPham ON dbo.ChiTietSanPham.MaSanPham = dbo.SanPham.ID INNER JOIN
                                 dbo.ChatLieu ON dbo.ChiTietSanPham.idChatLieu = dbo.ChatLieu.ID INNER JOIN
                                 dbo.MauSac ON dbo.ChiTietSanPham.idMauSac = dbo.MauSac.ID INNER JOIN
                                 dbo.PhuKien ON dbo.ChiTietSanPham.idPhuKien = dbo.PhuKien.ID INNER JOIN
                                 dbo.ThuongHieu ON dbo.ChiTietSanPham.idThuongHieu = dbo.ThuongHieu.ID
                where ChiTietSanPham.Deleted = 1 and HoaDon.Deleted = 1 and HoaDon.MaHoaDon like ?
                   """;
        try (Connection con = new DBConnect().getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, idHoaDon);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GioHangViewModel kh = new GioHangViewModel();
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
                kh.setSoLuongSP(rs.getInt(13));
                
                
                ListKH.add(kh);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ListKH;
    }
    
//    public static void main(String[] args) {
//        List<GioHangViewModel> list = new GioHangDao().getGH("381E50D1-0269-4EFF-A5C4-1980FF6E4036");
//        for (GioHangViewModel gioHangViewModel : list) {
//            System.out.println(gioHangViewModel.toString());
//        }
//    }
}
