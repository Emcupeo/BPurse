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
import java.util.Date;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class SoHoaDonRepositoty {

    public List<SoHoaDonEntity> getSoHoaDon() {
        List<SoHoaDonEntity> listGV = new ArrayList<>();
        String sql = """
SELECT [NgayTao], SUM(dbo.HoaDonChiTiet.SoLuong * dbo.HoaDonChiTiet.DonGia) as 'TongTien', [MaHoaDon]
        FROM [dbo].[HoaDon]
                     Inner join
                    dbo.HoaDonChiTiet ON dbo.HoaDon.ID = dbo.HoaDonChiTiet.idHoaDon Inner join
                    dbo.KhachHang ON dbo.HoaDon.idKhachHang = dbo.KhachHang.ID  Inner join
                    dbo.NhanVien ON dbo.HoaDon.idNhanVien = dbo.NhanVien.ID
                     
                   
                                            where HoaDon.Deleted = 'True'
                     
                     
                                         group by dbo.HoaDon.ID, dbo.HoaDon.CreatedAt, dbo.HoaDon.UpdatedAt, dbo.KhachHang.TenKhachHang,dbo.HoaDon.TrangThai,
                                         HoaDon.NgayTao, dbo.KhachHang.DiaChi, HoaDon.NgayThanhToan,   dbo.KhachHang.SoDienThoai, dbo.HoaDon.MaHoaDon, dbo.NhanVien.MaNhanVien  
                     
    """;

        try (java.sql.Connection con = DBConnectKhachHang.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SoHoaDonEntity kh = new SoHoaDonEntity();
                kh.setNgayTao(rs.getDate(1));
                kh.setTongTien(rs.getBigDecimal(2));
                kh.setMaHoaDon(rs.getString(3));
                listGV.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listGV;
    }

//    public List<SoHoaDonEntity> getCBO() {
//        List<SoHoaDonEntity> list = new ArrayList<>();
//        String sql = """
//SELECT [NgayTao]
//
//          FROM [dbo].[HoaDon]
//                    """;
//        try (Connection con = DBConnectKhachHang.getConnection();
//                PreparedStatement ps = con.prepareStatement(sql)) {
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                SoHoaDonEntity kh = new SoHoaDonEntity();
//                kh.setNgayTao(rs.getDate(1));
//                list.add(kh);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }

    
        public List<SoHoaDonEntity> getCBO(String month) {
        List<SoHoaDonEntity> list = new ArrayList<>();
        String sql = """
            SELECT [NgayTao], SUM(dbo.HoaDonChiTiet.SoLuong * dbo.HoaDonChiTiet.DonGia) as 'TongTien', [MaHoaDon]
                    FROM [dbo].[HoaDon]
                                 Inner join
                                dbo.HoaDonChiTiet ON dbo.HoaDon.ID = dbo.HoaDonChiTiet.idHoaDon Inner join
                                dbo.KhachHang ON dbo.HoaDon.idKhachHang = dbo.KhachHang.ID  Inner join
                                dbo.NhanVien ON dbo.HoaDon.idNhanVien = dbo.NhanVien.ID
                                 
                               
                                                        where HoaDon.Deleted = 'True' and MONTH([NgayTao]) = ?
                                 
                                 
                                                     group by dbo.HoaDon.ID, dbo.HoaDon.CreatedAt, dbo.HoaDon.UpdatedAt, dbo.KhachHang.TenKhachHang,dbo.HoaDon.TrangThai,
                                                     HoaDon.NgayTao, dbo.KhachHang.DiaChi,HoaDon.NgayThanhToan,    dbo.KhachHang.SoDienThoai, dbo.HoaDon.MaHoaDon, dbo.NhanVien.MaNhanVien  
                                 
           
        """;
        try (Connection con = DBConnectKhachHang.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, month);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SoHoaDonEntity hd = new SoHoaDonEntity();
                hd.setNgayTao(rs.getDate("NgayTao"));
                hd.setTongTien(rs.getBigDecimal("TongTien"));
                hd.setMaHoaDon(rs.getString("MaHoaDon"));
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    
    public List<SoHoaDonEntity> getCBONam(String year) {
    List<SoHoaDonEntity> list = new ArrayList<>();
    String sql = """
       SELECT [NgayTao], SUM(dbo.HoaDonChiTiet.SoLuong * dbo.HoaDonChiTiet.DonGia) as 'TongTien', [MaHoaDon]
                            FROM [dbo].[HoaDon]
                                         Inner join
                                        dbo.HoaDonChiTiet ON dbo.HoaDon.ID = dbo.HoaDonChiTiet.idHoaDon Inner join
                                        dbo.KhachHang ON dbo.HoaDon.idKhachHang = dbo.KhachHang.ID  Inner join
                                        dbo.NhanVien ON dbo.HoaDon.idNhanVien = dbo.NhanVien.ID
                                         
                                       
                                                                where HoaDon.Deleted = 'True' and YEAR([NgayTao]) = ?
                                         
                                         
                                                             group by dbo.HoaDon.ID, dbo.HoaDon.CreatedAt, dbo.HoaDon.UpdatedAt, dbo.KhachHang.TenKhachHang,dbo.HoaDon.TrangThai,
                                                             HoaDon.NgayTao, dbo.KhachHang.DiaChi, HoaDon.NgayThanhToan,   dbo.KhachHang.SoDienThoai, dbo.HoaDon.MaHoaDon, dbo.NhanVien.MaNhanVien  
                                 
       
    """;
    try (Connection con = DBConnectKhachHang.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, year);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            SoHoaDonEntity hd = new SoHoaDonEntity();
            hd.setNgayTao(rs.getDate("NgayTao"));
            hd.setTongTien(rs.getBigDecimal("TongTien"));
            hd.setMaHoaDon(rs.getString("MaHoaDon"));
            list.add(hd);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

    
    
    
    

    
    public List<SoHoaDonEntity> search(String keyword) {
        List<SoHoaDonEntity> resultList = new ArrayList<>();
        String sql = """
        SELECT [NgayTao], SUM(dbo.HoaDonChiTiet.SoLuong * dbo.HoaDonChiTiet.DonGia) as 'TongTien', [MaHoaDon]
        FROM [dbo].[HoaDon]
                     Inner join
                    dbo.HoaDonChiTiet ON dbo.HoaDon.ID = dbo.HoaDonChiTiet.idHoaDon Inner join
                    dbo.KhachHang ON dbo.HoaDon.idKhachHang = dbo.KhachHang.ID  Inner join
                    dbo.NhanVien ON dbo.HoaDon.idNhanVien = dbo.NhanVien.ID
                     
                   
                                            where HoaDon.Deleted = 'True'
                     
                     
                                         group by dbo.HoaDon.ID, dbo.HoaDon.CreatedAt, dbo.HoaDon.UpdatedAt, dbo.KhachHang.TenKhachHang,dbo.HoaDon.TrangThai,
                                         HoaDon.NgayTao, dbo.KhachHang.DiaChi, HoaDon.NgayThanhToan,    dbo.KhachHang.SoDienThoai, dbo.HoaDon.MaHoaDon, dbo.NhanVien.MaNhanVien  
                     
                                            HAVING 
                                            SUM(dbo.HoaDonChiTiet.SoLuong * dbo.HoaDonChiTiet.DonGia) like ? or [NgayTao] LIKE ? or [MaHoaDon] LIKE ?
                                                                                                            order by HoaDon.NgayThanhToan desc
                                     
    """;
        try (Connection con = DBConnectKhachHang.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            // Đặt các tham số cho các trường cần tìm kiếm
            String likeKeyword = "%" + keyword + "%";
            ps.setString(1, likeKeyword);
            ps.setString(2, likeKeyword);
            ps.setString(3, likeKeyword);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SoHoaDonEntity entity = new SoHoaDonEntity();
                    entity.setNgayTao(rs.getDate("NgayTao"));
                    entity.setTongTien(rs.getBigDecimal("TongTien"));
                    entity.setMaHoaDon(rs.getString("MaHoaDon"));

                    // Thêm entity vào danh sách kết quả
                    resultList.add(entity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

}
