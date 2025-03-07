/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bpurse.qlts.repo;

import bpurse.qlts.viewmodel.CTSPViewmodel;
import bpurse.qlts.viewmodel.SanPhamViewmodel1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class SanPhamRepo2 {

    public List<CTSPViewmodel> getAll() {
        List<CTSPViewmodel> lists = new ArrayList<>();
        String sql = """
             SELECT        
            dbo.ChiTietSanPham.ID,
            dbo.ChiTietSanPham.MaCTSP, 
            dbo.ChiTietSanPham.MoTa, 
            dbo.ChiTietSanPham.SoLuong, 
            dbo.ChiTietSanPham.GiaNhap, 
            dbo.ChiTietSanPham.GiaBan, 
            dbo.ChatLieu.TenChatLieu,
            dbo.MauSac.TenMauSac, 
            dbo.PhuKien.LoaiPhuKien, 
            dbo.LoaiQuai.TenLoaiQuai, 
            dbo.ThuongHieu.ThuongHieu, 
            dbo.PhanLoai.PhanLoai,
            dbo.KichCo.TenKichCo,
            dbo.TinhTrang.TinhTrang,
             dbo.ChiTietSanPham.trangThai,
             dbo.SanPham.TenSanPham,
             dbo.NhaSanXuat.TenNhaSanXuat
               FROM            
            dbo.ChiTietSanPham INNER JOIN
            dbo.ChatLieu ON dbo.ChiTietSanPham.idChatLieu = dbo.ChatLieu.ID INNER JOIN
            dbo.MauSac ON dbo.ChiTietSanPham.idMauSac = dbo.MauSac.ID INNER JOIN
            dbo.PhuKien ON dbo.ChiTietSanPham.idPhuKien = dbo.PhuKien.ID INNER JOIN
            dbo.LoaiQuai ON dbo.ChiTietSanPham.idLoaiQuai = dbo.LoaiQuai.ID INNER JOIN
            dbo.ThuongHieu ON dbo.ChiTietSanPham.idThuongHieu = dbo.ThuongHieu.ID INNER JOIN
            dbo.PhanLoai ON dbo.ChiTietSanPham.idPhanLoai = dbo.PhanLoai.ID INNER JOIN
            dbo.KichCo ON dbo.ChiTietSanPham.idKichCo = dbo.KichCo.ID INNER JOIN
            dbo.SanPham ON dbo.ChiTietSanPham.MaSanPham = dbo.SanPham.ID INNER JOIN
            dbo.TinhTrang ON dbo.ChiTietSanPham.idTinhTrang = dbo.TinhTrang.ID INNER JOIN
            dbo.NhaSanXuat ON dbo.ChiTietSanPham.idNhaSanXuat = dbo.NhaSanXuat.ID
            where ChiTietSanPham.Deleted = 1			
            GROUP BY dbo.ChiTietSanPham.ID, dbo.ChiTietSanPham.MaCTSP, dbo.SanPham.TenSanPham, dbo.ChiTietSanPham.MoTa, dbo.ChiTietSanPham.SoLuong, dbo.ChiTietSanPham.GiaNhap, dbo.ChiTietSanPham.GiaBan, dbo.ChiTietSanPham.CreatedAt
                 ,dbo.ChatLieu.TenChatLieu, dbo.MauSac.TenMauSac, dbo.PhuKien.LoaiPhuKien, dbo.LoaiQuai.TenLoaiQuai, dbo.ThuongHieu.ThuongHieu, dbo.PhanLoai.PhanLoai, dbo.KichCo.TenKichCo,dbo.TinhTrang.TinhTrang,dbo.ChiTietSanPham.trangThai,dbo.NhaSanXuat.TenNhaSanXuat

                order by dbo.ChiTietSanPham.CreatedAt desc              
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CTSPViewmodel sp2 = new CTSPViewmodel();
                sp2.setIdCTSP(rs.getString(1));
                sp2.setMaSP2(rs.getString(2));
                sp2.setMoTa2(rs.getString(3));
                sp2.setSoLuong2(rs.getInt(4));
                sp2.setGiaNhap(rs.getFloat(5));
                sp2.setGiaBan(rs.getFloat(6));
                sp2.setChatLieu(rs.getString(7));
                sp2.setMauSac(rs.getString(8));
                sp2.setPhuKien(rs.getString(9));
                sp2.setLoaiQuai(rs.getString(10));
                sp2.setThuongHieu(rs.getString(11));
                sp2.setPhanLoai(rs.getString(12));
                sp2.setKichCo(rs.getString(13));
                sp2.setTinhTrang(rs.getString(14));
                sp2.setTrangThai(rs.getString(15));
                sp2.setTenSP2(rs.getString(16));
                sp2.setNhaSanXuat(rs.getString(17));
                lists.add(sp2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }
     public List<CTSPViewmodel> getAll2(String id) {
        List<CTSPViewmodel> lists = new ArrayList<>();
        String sql = """
             SELECT        
            dbo.ChiTietSanPham.ID,
            dbo.ChiTietSanPham.MaCTSP, 
            dbo.ChiTietSanPham.MoTa, 
            dbo.ChiTietSanPham.SoLuong, 
            dbo.ChiTietSanPham.GiaNhap, 
            dbo.ChiTietSanPham.GiaBan, 
            dbo.ChatLieu.TenChatLieu,
            dbo.MauSac.TenMauSac, 
            dbo.PhuKien.LoaiPhuKien, 
            dbo.LoaiQuai.TenLoaiQuai, 
            dbo.ThuongHieu.ThuongHieu, 
            dbo.PhanLoai.PhanLoai,
            dbo.KichCo.TenKichCo,
            dbo.TinhTrang.TinhTrang,
             dbo.ChiTietSanPham.trangThai,
             dbo.SanPham.TenSanPham,
             dbo.NhaSanXuat.TenNhaSanXuat
               FROM            
            dbo.ChiTietSanPham INNER JOIN
            dbo.ChatLieu ON dbo.ChiTietSanPham.idChatLieu = dbo.ChatLieu.ID INNER JOIN
            dbo.MauSac ON dbo.ChiTietSanPham.idMauSac = dbo.MauSac.ID INNER JOIN
            dbo.PhuKien ON dbo.ChiTietSanPham.idPhuKien = dbo.PhuKien.ID INNER JOIN
            dbo.LoaiQuai ON dbo.ChiTietSanPham.idLoaiQuai = dbo.LoaiQuai.ID INNER JOIN
            dbo.ThuongHieu ON dbo.ChiTietSanPham.idThuongHieu = dbo.ThuongHieu.ID INNER JOIN
            dbo.PhanLoai ON dbo.ChiTietSanPham.idPhanLoai = dbo.PhanLoai.ID INNER JOIN
            dbo.KichCo ON dbo.ChiTietSanPham.idKichCo = dbo.KichCo.ID INNER JOIN
            dbo.SanPham ON dbo.ChiTietSanPham.MaSanPham = dbo.SanPham.ID INNER JOIN
            dbo.TinhTrang ON dbo.ChiTietSanPham.idTinhTrang = dbo.TinhTrang.ID INNER JOIN
            dbo.NhaSanXuat ON dbo.ChiTietSanPham.idNhaSanXuat = dbo.NhaSanXuat.ID
            where ChiTietSanPham.Deleted = 1 and ChiTietSanPham.ID = ?	
            GROUP BY dbo.ChiTietSanPham.ID, dbo.ChiTietSanPham.MaCTSP, dbo.SanPham.TenSanPham, dbo.ChiTietSanPham.MoTa, dbo.ChiTietSanPham.SoLuong, dbo.ChiTietSanPham.GiaNhap, dbo.ChiTietSanPham.GiaBan, dbo.ChiTietSanPham.CreatedAt
                 ,dbo.ChatLieu.TenChatLieu, dbo.MauSac.TenMauSac, dbo.PhuKien.LoaiPhuKien, dbo.LoaiQuai.TenLoaiQuai, dbo.ThuongHieu.ThuongHieu, dbo.PhanLoai.PhanLoai, dbo.KichCo.TenKichCo,dbo.TinhTrang.TinhTrang,dbo.ChiTietSanPham.trangThai,dbo.NhaSanXuat.TenNhaSanXuat

                order by dbo.ChiTietSanPham.CreatedAt desc              
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CTSPViewmodel sp2 = new CTSPViewmodel();
                sp2.setIdCTSP(rs.getString(1));
                sp2.setMaSP2(rs.getString(2));
                sp2.setMoTa2(rs.getString(3));
                sp2.setSoLuong2(rs.getInt(4));
                sp2.setGiaNhap(rs.getFloat(5));
                sp2.setGiaBan(rs.getFloat(6));
                sp2.setChatLieu(rs.getString(7));
                sp2.setMauSac(rs.getString(8));
                sp2.setPhuKien(rs.getString(9));
                sp2.setLoaiQuai(rs.getString(10));
                sp2.setThuongHieu(rs.getString(11));
                sp2.setPhanLoai(rs.getString(12));
                sp2.setKichCo(rs.getString(13));
                sp2.setTinhTrang(rs.getString(14));
                sp2.setTrangThai(rs.getString(15));
                sp2.setTenSP2(rs.getString(16));
                sp2.setNhaSanXuat(rs.getString(17));
                lists.add(sp2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }
     
     public List<CTSPViewmodel> locCTSP(String tenSP, String Nsx, String chatLieu, String LoaiQuai, String PhuKien) {
        List<CTSPViewmodel> lists = new ArrayList<>();
        String sql = """
             SELECT        
            dbo.ChiTietSanPham.ID,
            dbo.ChiTietSanPham.MaCTSP, 
            dbo.ChiTietSanPham.MoTa, 
            dbo.ChiTietSanPham.SoLuong, 
            dbo.ChiTietSanPham.GiaNhap, 
            dbo.ChiTietSanPham.GiaBan, 
            dbo.ChatLieu.TenChatLieu,
            dbo.MauSac.TenMauSac, 
            dbo.PhuKien.LoaiPhuKien, 
            dbo.LoaiQuai.TenLoaiQuai, 
            dbo.ThuongHieu.ThuongHieu, 
            dbo.PhanLoai.PhanLoai,
            dbo.KichCo.TenKichCo,
            dbo.TinhTrang.TinhTrang,
             dbo.ChiTietSanPham.trangThai,
             dbo.SanPham.TenSanPham,
             dbo.NhaSanXuat.TenNhaSanXuat
               FROM            
            dbo.ChiTietSanPham INNER JOIN
            dbo.ChatLieu ON dbo.ChiTietSanPham.idChatLieu = dbo.ChatLieu.ID INNER JOIN
            dbo.MauSac ON dbo.ChiTietSanPham.idMauSac = dbo.MauSac.ID INNER JOIN
            dbo.PhuKien ON dbo.ChiTietSanPham.idPhuKien = dbo.PhuKien.ID INNER JOIN
            dbo.LoaiQuai ON dbo.ChiTietSanPham.idLoaiQuai = dbo.LoaiQuai.ID INNER JOIN
            dbo.ThuongHieu ON dbo.ChiTietSanPham.idThuongHieu = dbo.ThuongHieu.ID INNER JOIN
            dbo.PhanLoai ON dbo.ChiTietSanPham.idPhanLoai = dbo.PhanLoai.ID INNER JOIN
            dbo.KichCo ON dbo.ChiTietSanPham.idKichCo = dbo.KichCo.ID INNER JOIN
            dbo.SanPham ON dbo.ChiTietSanPham.MaSanPham = dbo.SanPham.ID INNER JOIN
            dbo.TinhTrang ON dbo.ChiTietSanPham.idTinhTrang = dbo.TinhTrang.ID INNER JOIN
            dbo.NhaSanXuat ON dbo.ChiTietSanPham.idNhaSanXuat = dbo.NhaSanXuat.ID
            where ChiTietSanPham.Deleted = 1
                     and dbo.SanPham.TenSanPham like ?
                     or dbo.NhaSanXuat.TenNhaSanXuat like ?
                     or dbo.ChatLieu.TenChatLieu like ?
                     or dbo.LoaiQuai.TenLoaiQuai like ?
                     or dbo.PhuKien.LoaiPhuKien like ?
            GROUP BY dbo.ChiTietSanPham.ID,
                     dbo.ChiTietSanPham.MaCTSP,
                     dbo.SanPham.TenSanPham,
                     dbo.ChiTietSanPham.MoTa, 
                     dbo.ChiTietSanPham.SoLuong, 
                     dbo.ChiTietSanPham.GiaNhap, 
                     dbo.ChiTietSanPham.GiaBan,
                     dbo.ChiTietSanPham.CreatedAt
                 ,dbo.ChatLieu.TenChatLieu, 
                     dbo.MauSac.TenMauSac, 
                     dbo.ThuongHieu.ThuongHieu, 
                     dbo.PhanLoai.PhanLoai, 
                     dbo.KichCo.TenKichCo,
                     dbo.TinhTrang.TinhTrang,
                     dbo.ChiTietSanPham.trangThai,
                     dbo.NhaSanXuat.TenNhaSanXuat
                order by dbo.ChiTietSanPham.CreatedAt desc              
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, tenSP);
            ps.setObject(2, Nsx);
            ps.setObject(3, chatLieu);
            ps.setObject(4, LoaiQuai);
            ps.setObject(5, PhuKien);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CTSPViewmodel sp2 = new CTSPViewmodel();
                sp2.setIdCTSP(rs.getString(1));
                sp2.setMaSP2(rs.getString(2));
                sp2.setMoTa2(rs.getString(3));
                sp2.setSoLuong2(rs.getInt(4));
                sp2.setGiaNhap(rs.getFloat(5));
                sp2.setGiaBan(rs.getFloat(6));
                sp2.setChatLieu(rs.getString(7));
                sp2.setMauSac(rs.getString(8));
                sp2.setPhuKien(rs.getString(9));
                sp2.setLoaiQuai(rs.getString(10));
                sp2.setThuongHieu(rs.getString(11));
                sp2.setPhanLoai(rs.getString(12));
                sp2.setKichCo(rs.getString(13));
                sp2.setTinhTrang(rs.getString(14));
                sp2.setTrangThai(rs.getString(15));
                sp2.setTenSP2(rs.getString(16));
                sp2.setNhaSanXuat(rs.getString(17));
                lists.add(sp2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }
     
    
    
    public List<CTSPViewmodel> getTenSP() {
        List<CTSPViewmodel> lists = new ArrayList<>();
        String sql = """
                       SELECT dbo.SanPham.TenSanPham
                         FROM dbo.SanPham INNER JOIN
                              dbo.ChiTietSanPham ON dbo.SanPham.ID = dbo.ChiTietSanPham.MaSanPham 
                              where ChiTietSanPham.idChatLieu is null and SanPham.Deleted = 1 order by  dbo.SanPham.CreatedAt desc            
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CTSPViewmodel sp2 = new CTSPViewmodel();
                sp2.setTenSP2(rs.getString(1));            
                lists.add(sp2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }
    public List<CTSPViewmodel> search2(String search) {
        List<CTSPViewmodel> lists = new ArrayList<>();
        String sql ="""
                    SELECT        
                                    dbo.ChiTietSanPham.ID,
                                    dbo.ChiTietSanPham.MaCTSP, 
                                    dbo.ChiTietSanPham.MoTa, 
                                    dbo.ChiTietSanPham.SoLuong, 
                                    dbo.ChiTietSanPham.GiaNhap, 
                                    dbo.ChiTietSanPham.GiaBan,
                                    dbo.ChatLieu.TenChatLieu,
                                    dbo.MauSac.TenMauSac, 
                                    dbo.PhuKien.LoaiPhuKien, 
                                    dbo.LoaiQuai.TenLoaiQuai, 
                                    dbo.ThuongHieu.ThuongHieu, 
                                    dbo.PhanLoai.PhanLoai,
                                    dbo.KichCo.TenKichCo,
                                    dbo.TinhTrang.TinhTrang,
                                     dbo.ChiTietSanPham.trangThai,
                                     dbo.SanPham.TenSanPham,
                                     dbo.NhaSanXuat.TenNhaSanXuat
                                       FROM            
                                    dbo.ChiTietSanPham INNER JOIN
                                    dbo.ChatLieu ON dbo.ChiTietSanPham.idChatLieu = dbo.ChatLieu.ID INNER JOIN
                                    dbo.MauSac ON dbo.ChiTietSanPham.idMauSac = dbo.MauSac.ID INNER JOIN
                                    dbo.PhuKien ON dbo.ChiTietSanPham.idPhuKien = dbo.PhuKien.ID INNER JOIN
                                    dbo.LoaiQuai ON dbo.ChiTietSanPham.idLoaiQuai = dbo.LoaiQuai.ID INNER JOIN
                                    dbo.ThuongHieu ON dbo.ChiTietSanPham.idThuongHieu = dbo.ThuongHieu.ID INNER JOIN
                                    dbo.PhanLoai ON dbo.ChiTietSanPham.idPhanLoai = dbo.PhanLoai.ID INNER JOIN
                                    dbo.KichCo ON dbo.ChiTietSanPham.idKichCo = dbo.KichCo.ID INNER JOIN
                                    dbo.SanPham ON dbo.ChiTietSanPham.MaSanPham = dbo.SanPham.ID INNER JOIN
                                    dbo.TinhTrang ON dbo.ChiTietSanPham.idTinhTrang = dbo.TinhTrang.ID INNER JOIN
                                    dbo.NhaSanXuat ON dbo.ChiTietSanPham.idNhaSanXuat = dbo.NhaSanXuat.ID
                                    where ChiTietSanPham.Deleted = 1 and SanPham.Deleted = 1 
                                                
                                                GROUP BY dbo.ChiTietSanPham.ID,
                                        dbo.ChiTietSanPham.MaSanPham, 
                                        dbo.ChiTietSanPham.MaCTSP,
                                        dbo.SanPham.TenSanPham, 
                                        dbo.ChiTietSanPham.MoTa,
                                        dbo.ChiTietSanPham.SoLuong,
                                        dbo.ChiTietSanPham.GiaNhap,
                                        dbo.ChiTietSanPham.GiaBan, 
                                        dbo.ChiTietSanPham.CreatedAt,
                                        dbo.ChatLieu.TenChatLieu, 
                                        dbo.MauSac.TenMauSac,
                                        dbo.PhuKien.LoaiPhuKien,
                                        dbo.LoaiQuai.TenLoaiQuai, 
                                        dbo.ThuongHieu.ThuongHieu,
                                        dbo.PhanLoai.PhanLoai, 
                                        dbo.KichCo.TenKichCo,
                                        dbo.TinhTrang.TinhTrang,
                                        dbo.ChiTietSanPham.trangThai,
                                        dbo.NhaSanXuat.TenNhaSanXuat
                    				Having  ChatLieu.TenChatLieu like ?
                    				            or MauSac.TenMauSac like ?
                                                or PhuKien.LoaiPhuKien like ?
                                                or LoaiQuai.TenLoaiQuai like ?
                                                or ThuongHieu.ThuongHieu like ?
                                                or PhanLoai.PhanLoai like ?
                                                or KichCo.TenKichCo like ?
                                                or SanPham.TenSanPham like ?
                                                or TinhTrang.TinhTrang like ?
                                                or NhaSanXuat.TenNhaSanXuat like ?
                                                or SoLuong like ?
                                                or GiaBan like ?
                                                or GiaNhap like ?
                    							order by dbo.ChiTietSanPham.CreatedAt desc    
                    """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareCall(sql)) {
            ps.setObject(1, '%' + search + '%');
            ps.setObject(2, '%' + search + '%');
            ps.setObject(3, '%' + search + '%');
            ps.setObject(4, '%' + search + '%');
            ps.setObject(5, '%' + search + '%');
            ps.setObject(6, '%' + search + '%');
            ps.setObject(7, '%' + search + '%');
            ps.setObject(8, '%' + search + '%');
            ps.setObject(9, '%' + search + '%');
            ps.setObject(10, '%' + search + '%');
            ps.setObject(11, '%' + search + '%');
            ps.setObject(12, '%' + search + '%');
            ps.setObject(13, '%' + search + '%');
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                CTSPViewmodel ctsp = new CTSPViewmodel();
                ctsp.setIdCTSP(rs.getString(1));
                ctsp.setMaSP2(rs.getString(2));
                ctsp.setMoTa2(rs.getString(3));
                ctsp.setSoLuong2(rs.getInt(4));
                ctsp.setGiaNhap(rs.getFloat(5));
                ctsp.setGiaBan(rs.getFloat(6));
                ctsp.setChatLieu(rs.getString(7));
                ctsp.setMauSac(rs.getString(8));
                ctsp.setPhuKien(rs.getString(9));
                ctsp.setLoaiQuai(rs.getString(10));
                ctsp.setThuongHieu(rs.getString(11));
                ctsp.setPhanLoai(rs.getString(12));
                ctsp.setKichCo(rs.getString(13));
                ctsp.setTinhTrang(rs.getString(14));
                ctsp.setTrangThai(rs.getString(15));
                ctsp.setTenSP2(rs.getString(16));
                ctsp.setNhaSanXuat(rs.getString(17));
                lists.add(ctsp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }
    public List<CTSPViewmodel> getAll3(String id) {
        List<CTSPViewmodel> lists = new ArrayList<>();
        String sql = """
             SELECT        
            dbo.ChiTietSanPham.ID,
            dbo.ChiTietSanPham.MaCTSP, 
            dbo.ChiTietSanPham.MoTa, 
            dbo.ChiTietSanPham.SoLuong, 
            dbo.ChiTietSanPham.GiaNhap, 
            dbo.ChiTietSanPham.GiaBan, 
            dbo.ChatLieu.TenChatLieu,
            dbo.MauSac.TenMauSac, 
            dbo.PhuKien.LoaiPhuKien, 
            dbo.LoaiQuai.TenLoaiQuai, 
            dbo.ThuongHieu.ThuongHieu, 
            dbo.PhanLoai.PhanLoai,
            dbo.KichCo.TenKichCo,
            dbo.TinhTrang.TinhTrang,
             dbo.ChiTietSanPham.trangThai,
             dbo.SanPham.TenSanPham,
             dbo.NhaSanXuat.TenNhaSanXuat
               FROM            
            dbo.ChiTietSanPham INNER JOIN
            dbo.ChatLieu ON dbo.ChiTietSanPham.idChatLieu = dbo.ChatLieu.ID INNER JOIN
            dbo.MauSac ON dbo.ChiTietSanPham.idMauSac = dbo.MauSac.ID INNER JOIN
            dbo.PhuKien ON dbo.ChiTietSanPham.idPhuKien = dbo.PhuKien.ID INNER JOIN
            dbo.LoaiQuai ON dbo.ChiTietSanPham.idLoaiQuai = dbo.LoaiQuai.ID INNER JOIN
            dbo.ThuongHieu ON dbo.ChiTietSanPham.idThuongHieu = dbo.ThuongHieu.ID INNER JOIN
            dbo.PhanLoai ON dbo.ChiTietSanPham.idPhanLoai = dbo.PhanLoai.ID INNER JOIN
            dbo.KichCo ON dbo.ChiTietSanPham.idKichCo = dbo.KichCo.ID INNER JOIN
            dbo.SanPham ON dbo.ChiTietSanPham.MaSanPham = dbo.SanPham.ID INNER JOIN
            dbo.TinhTrang ON dbo.ChiTietSanPham.idTinhTrang = dbo.TinhTrang.ID INNER JOIN
            dbo.NhaSanXuat ON dbo.ChiTietSanPham.idNhaSanXuat = dbo.NhaSanXuat.ID
            where ChiTietSanPham.Deleted = 1 and ChiTietSanPham.MaSanPham = ?	
            GROUP BY dbo.ChiTietSanPham.ID, dbo.ChiTietSanPham.MaCTSP, dbo.SanPham.TenSanPham, dbo.ChiTietSanPham.MoTa, dbo.ChiTietSanPham.SoLuong, dbo.ChiTietSanPham.GiaNhap, dbo.ChiTietSanPham.GiaBan, dbo.ChiTietSanPham.CreatedAt
                 ,dbo.ChatLieu.TenChatLieu, dbo.MauSac.TenMauSac, dbo.PhuKien.LoaiPhuKien, dbo.LoaiQuai.TenLoaiQuai, dbo.ThuongHieu.ThuongHieu, dbo.PhanLoai.PhanLoai, dbo.KichCo.TenKichCo,dbo.TinhTrang.TinhTrang,dbo.ChiTietSanPham.trangThai,dbo.NhaSanXuat.TenNhaSanXuat

                order by dbo.ChiTietSanPham.CreatedAt desc              
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CTSPViewmodel sp2 = new CTSPViewmodel();
                sp2.setIdCTSP(rs.getString(1));
                sp2.setMaSP2(rs.getString(2));
                sp2.setMoTa2(rs.getString(3));
                sp2.setSoLuong2(rs.getInt(4));
                sp2.setGiaNhap(rs.getFloat(5));
                sp2.setGiaBan(rs.getFloat(6));
                sp2.setChatLieu(rs.getString(7));
                sp2.setMauSac(rs.getString(8));
                sp2.setPhuKien(rs.getString(9));
                sp2.setLoaiQuai(rs.getString(10));
                sp2.setThuongHieu(rs.getString(11));
                sp2.setPhanLoai(rs.getString(12));
                sp2.setKichCo(rs.getString(13));
                sp2.setTinhTrang(rs.getString(14));
                sp2.setTrangThai(rs.getString(15));
                sp2.setTenSP2(rs.getString(16));
                sp2.setNhaSanXuat(rs.getString(17));
                lists.add(sp2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }

    
}
