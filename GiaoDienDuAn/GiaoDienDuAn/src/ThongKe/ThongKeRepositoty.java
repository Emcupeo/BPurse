/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ThongKe;

import KhachHang.DBConnectKhachHang;
import java.sql.Connection;//phai code tay
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ThongKeRepositoty {

    public List<ThongKeEntity> getThongKe() {
        List<ThongKeEntity> listGV = new ArrayList<>();
        String sql = """
            SELECT
                    dbo.KhachHang.TenKhachHang,
                    SUM(dbo.HoaDonChiTiet.SoLuong * dbo.HoaDonChiTiet.DonGia),
                    dbo.HoaDon.TrangThai

                    FROM  dbo.HoaDon 
                                   Inner join
                                   dbo.HoaDonChiTiet ON dbo.HoaDon.ID = dbo.HoaDonChiTiet.idHoaDon Inner join
                                   dbo.KhachHang ON dbo.HoaDon.idKhachHang = dbo.KhachHang.ID  Inner join
                                   dbo.NhanVien ON dbo.HoaDon.idNhanVien = dbo.NhanVien.ID
                     
                       where HoaDon.Deleted = 'True'


                    group by dbo.HoaDon.ID, dbo.HoaDon.CreatedAt, dbo.HoaDon.UpdatedAt, dbo.KhachHang.TenKhachHang,dbo.HoaDon.TrangThai,
                    HoaDon.NgayThanhToan, dbo.KhachHang.DiaChi,    dbo.KhachHang.SoDienThoai, dbo.HoaDon.MaHoaDon, dbo.NhanVien.MaNhanVien  

                    
    """;

        try (java.sql.Connection con = DBConnectKhachHang.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ThongKeEntity kh = new ThongKeEntity();
                kh.setTenKhachHang(rs.getString(1));
                kh.setTongTien(rs.getBigDecimal(2));
                kh.setTrangThai(rs.getString(3));
                listGV.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listGV;
    }
    
    public List<ThongKeEntity> getHoaDon() {
        List<ThongKeEntity> listGV = new ArrayList<>();
        String sql = """
            SELECT
                    dbo.KhachHang.TenKhachHang,
                    
                    dbo.HoaDon.TrangThai

                    FROM  dbo.HoaDon 
                                   Inner join
                                   dbo.KhachHang ON dbo.HoaDon.idKhachHang = dbo.KhachHang.ID  Inner join
                                   dbo.NhanVien ON dbo.HoaDon.idNhanVien = dbo.NhanVien.ID
                     
                       where HoaDon.Deleted = 'True' and HoaDon.TrangThai like N'Chờ thanh toán'


                    group by dbo.HoaDon.ID, dbo.HoaDon.CreatedAt, dbo.HoaDon.UpdatedAt, dbo.KhachHang.TenKhachHang,dbo.HoaDon.TrangThai,
                    HoaDon.NgayThanhToan, dbo.KhachHang.DiaChi,    dbo.KhachHang.SoDienThoai, dbo.HoaDon.MaHoaDon, dbo.NhanVien.MaNhanVien  
                    
    """;

        try (java.sql.Connection con = DBConnectKhachHang.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ThongKeEntity kh = new ThongKeEntity();
                kh.setTenKhachHang(rs.getString(1));
               
                kh.setTrangThai(rs.getString(2));
                listGV.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listGV;
    }
    
    public List<ThongKeEntity> getKhachHang() {
        List<ThongKeEntity> listGV = new ArrayList<>();
        String sql = """
            SELECT
                    dbo.KhachHang.TenKhachHang
                    
                    

                    FROM  dbo.KhachHang 
                                  
                     
                       where Deleted = 'True' 
                    
    """;

        try (java.sql.Connection con = DBConnectKhachHang.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ThongKeEntity kh = new ThongKeEntity();
                kh.setTenKhachHang(rs.getString(1));
        
                listGV.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listGV;
    }
}
