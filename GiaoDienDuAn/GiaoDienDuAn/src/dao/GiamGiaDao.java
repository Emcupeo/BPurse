/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.GiamGiaEntity;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import viewmodel.GiamGiaViewModel;

/**
 *
 * @author tung pc
 */
public class GiamGiaDao {

    public List<GiamGiaViewModel> getAll() {
        List<GiamGiaViewModel> listgg = new ArrayList<>();

        String sql = """
            SELECT dbo.PhieuGiamGia.MaPhieuGiamGia, dbo.PhieuGiamGia.TenKhuyenMai, dbo.PhieuGiamGia.NgayBatDau, dbo.PhieuGiamGia.NgayKetThuc, dbo.PhieuGiamGia.SoLuongPhieu, dbo.PhieuGiamGia.HoaDonToiThieu, dbo.PhieuGiamGia.PhanTramGiam, 
                    dbo.PhieuGiamGia.SoTienGiamToiDa, dbo.PhieuGiamGia.CreatedAt, dbo.NhanVien.TenNhanVien, dbo.PhieuGiamGia.TrangThai, dbo.PhieuGiamGia.ID
            FROM   dbo.NhanVien INNER JOIN
                    dbo.PhieuGiamGia ON dbo.NhanVien.ID = dbo.PhieuGiamGia.idNhanVien
                    WHERE dbo.PhieuGiamGia.Deleted = 1
                    ORDER BY dbo.PhieuGiamGia.CreatedAt DESC
                    """;
        try ( Connection con = DBconnect.getConnection();  PreparedStatement ps = con.prepareCall(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GiamGiaViewModel ggvm = new GiamGiaViewModel();
                ggvm.setMaPhieuGiamGia(rs.getString(1));
                ggvm.setTenKhuyenMai(rs.getString(2));
                ggvm.setNgayBatDau(rs.getDate(3));
                ggvm.setNgayKetThuc(rs.getDate(4));
                ggvm.setSoLuongPhieu(rs.getInt(5));
                ggvm.setHoaDonToiThieu(rs.getDouble(6));
                ggvm.setPhanTramGiam(rs.getDouble(7));
                ggvm.setSoTienGiamToiDa(rs.getDouble(8));
                ggvm.setNgayTao(rs.getDate(9));
                ggvm.setNguoiTao(rs.getString(10));
                ggvm.setTrangThai(rs.getString(11));
                ggvm.setIdPhieuGiamGia(rs.getString(12));
                listgg.add(ggvm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listgg;
    }

    public List<GiamGiaViewModel> timTheoTen(String search) {
        List<GiamGiaViewModel> listgg = new ArrayList<>();

        search = search.trim();

        String sql = """
        SELECT dbo.PhieuGiamGia.MaPhieuGiamGia, dbo.PhieuGiamGia.TenKhuyenMai, dbo.PhieuGiamGia.NgayBatDau, dbo.PhieuGiamGia.NgayKetThuc, dbo.PhieuGiamGia.SoLuongPhieu, dbo.PhieuGiamGia.HoaDonToiThieu, dbo.PhieuGiamGia.PhanTramGiam, 
                     dbo.PhieuGiamGia.SoTienGiamToiDa, dbo.PhieuGiamGia.CreatedAt, dbo.NhanVien.TenNhanVien, dbo.PhieuGiamGia.TrangThai, dbo.PhieuGiamGia.ID
        FROM   dbo.NhanVien INNER JOIN
                     dbo.PhieuGiamGia ON dbo.NhanVien.ID = dbo.PhieuGiamGia.idNhanVien
                     WHERE (LOWER(dbo.PhieuGiamGia.MaPhieuGiamGia) COLLATE SQL_Latin1_General_CP1_CI_AI LIKE ? OR LOWER(dbo.PhieuGiamGia.TenKhuyenMai) COLLATE SQL_Latin1_General_CP1_CI_AI LIKE ? OR LOWER(dbo.PhieuGiamGia.TrangThai) COLLATE SQL_Latin1_General_CP1_CI_AI LIKE ?) AND dbo.PhieuGiamGia.Deleted = 1
        """;
        try ( Connection con = DBconnect.getConnection();  PreparedStatement ps = con.prepareCall(sql)) {

            ps.setObject(1, "%" + search.toLowerCase() + "%");
            ps.setObject(2, "%" + search.toLowerCase() + "%");
            ps.setObject(3, "%" + search.toLowerCase() + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GiamGiaViewModel ggvm = new GiamGiaViewModel();
                ggvm.setMaPhieuGiamGia(rs.getString(1));
                ggvm.setTenKhuyenMai(rs.getString(2));
                ggvm.setNgayBatDau(rs.getDate(3));
                ggvm.setNgayKetThuc(rs.getDate(4));
                ggvm.setSoLuongPhieu(rs.getInt(5));
                ggvm.setHoaDonToiThieu(rs.getDouble(6));
                ggvm.setPhanTramGiam(rs.getDouble(7));
                ggvm.setSoTienGiamToiDa(rs.getDouble(8));
                ggvm.setNgayTao(rs.getDate(9));
                ggvm.setNguoiTao(rs.getString(10));
                ggvm.setTrangThai(rs.getString(11));
                ggvm.setIdPhieuGiamGia(rs.getString(12));
                listgg.add(ggvm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listgg;
    }

    public List<GiamGiaViewModel> timTheoTT(String tim) {
        List<GiamGiaViewModel> listgg = new ArrayList<>();

        String sql = """
            SELECT dbo.PhieuGiamGia.MaPhieuGiamGia, dbo.PhieuGiamGia.TenKhuyenMai, dbo.PhieuGiamGia.NgayBatDau, dbo.PhieuGiamGia.NgayKetThuc, dbo.PhieuGiamGia.SoLuongPhieu, dbo.PhieuGiamGia.HoaDonToiThieu, dbo.PhieuGiamGia.PhanTramGiam, 
                         dbo.PhieuGiamGia.SoTienGiamToiDa, dbo.PhieuGiamGia.CreatedAt, dbo.NhanVien.TenNhanVien, dbo.PhieuGiamGia.TrangThai, dbo.PhieuGiamGia.ID
            FROM   dbo.NhanVien INNER JOIN
                         dbo.PhieuGiamGia ON dbo.NhanVien.ID = dbo.PhieuGiamGia.idNhanVien
            """ + (tim.equals("Tất cả") ? "WHERE dbo.PhieuGiamGia.Deleted = 1" : "WHERE TrangThai LIKE ? AND dbo.PhieuGiamGia.Deleted = 1");
        try ( Connection con = DBconnect.getConnection();  PreparedStatement ps = con.prepareCall(sql)) {

            if (!tim.equals("Tất cả")) {
                ps.setObject(1, tim);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GiamGiaViewModel ggvm = new GiamGiaViewModel();
                ggvm.setMaPhieuGiamGia(rs.getString(1));
                ggvm.setTenKhuyenMai(rs.getString(2));
                ggvm.setNgayBatDau(rs.getDate(3));
                ggvm.setNgayKetThuc(rs.getDate(4));
                ggvm.setSoLuongPhieu(rs.getInt(5));
                ggvm.setHoaDonToiThieu(rs.getDouble(6));
                ggvm.setPhanTramGiam(rs.getDouble(7));
                ggvm.setSoTienGiamToiDa(rs.getDouble(8));
                ggvm.setNgayTao(rs.getDate(9));
                ggvm.setNguoiTao(rs.getString(10));
                ggvm.setTrangThai(rs.getString(11));
                ggvm.setIdPhieuGiamGia(rs.getString(12));
                listgg.add(ggvm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listgg;
    }

    public String taoMoiMaPhieu() {
        String lastMaPhieu = null;
        String sql = "SELECT TOP 1 MaPhieuGiamGia FROM [dbo].[PhieuGiamGia] ORDER BY MaPhieuGiamGia DESC";
        try ( Connection con = DBconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                lastMaPhieu = rs.getString("MaPhieuGiamGia");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (lastMaPhieu == null) {
            return "PGG01";
        } else {
            int lastNumber = Integer.parseInt(lastMaPhieu.substring(3)); // lấy mã
            String newMaPhieu = "PGG" + String.format("%02d", lastNumber + 1); // tạo mã mới
            return newMaPhieu;
        }
    }

    public boolean them(GiamGiaEntity gg) {
        int check = 0;
        String sql = """
            INSERT INTO [dbo].[PhieuGiamGia]
                       ([MaPhieuGiamGia]
                       ,[TenKhuyenMai]
                       ,[SoLuongPhieu]
                       ,[PhanTramGiam]
                       ,[HoaDonToiThieu]
                       ,[SoTienGiamToiDa]
                       ,[NgayBatDau]
                       ,[NgayKetThuc]
                       ,[TrangThai]
                       ,[CreatedAt]
                       ,[idNhanVien]
                       ,[Deleted] 
                        )
                 VALUES
                       (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'BDAA3374-AD27-4138-A426-B8F64490B717','1')
            """;
        try ( Connection con = DBconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, gg.getMaPhieuGiamGia());
            ps.setObject(2, gg.getTenKhuyenMai());
            ps.setObject(3, gg.getSoLuongPhieu());
            ps.setObject(4, gg.getPhanTramGiam());
            ps.setObject(5, gg.getHoaDonToiThieu());
            ps.setObject(6, gg.getSoTienGiamToiDa());
            ps.setObject(7, new java.sql.Date(gg.getNgayBatDau().getTime()));
            ps.setObject(8, new java.sql.Date(gg.getNgayKetThuc().getTime()));
            ps.setObject(9, gg.getTrangThai());
            ps.setObject(10, new java.sql.Timestamp(System.currentTimeMillis()));

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean sua(GiamGiaEntity gg, String maPhieuGiamGia) {
        int check = 0;
        String sql = """
            UPDATE [dbo].[PhieuGiamGia]
               SET [TenKhuyenMai] = ?
                  ,[SoLuongPhieu] = ?
                  ,[PhanTramGiam] = ?
                  ,[HoaDonToiThieu] = ?
                  ,[SoTienGiamToiDa] = ?
                  ,[NgayBatDau] = ?
                  ,[NgayKetThuc] = ?
                  ,[TrangThai] = ?
             WHERE MaPhieuGiamGia = ?
        """;
        try ( Connection con = DBconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, gg.getTenKhuyenMai());
            ps.setObject(2, gg.getSoLuongPhieu());
            ps.setObject(3, gg.getPhanTramGiam());
            ps.setObject(4, gg.getHoaDonToiThieu());
            ps.setObject(5, gg.getSoTienGiamToiDa());
            ps.setObject(6, new java.sql.Date(gg.getNgayBatDau().getTime()));
            ps.setObject(7, new java.sql.Date(gg.getNgayKetThuc().getTime()));
            ps.setObject(8, gg.getTrangThai());
            ps.setObject(9, maPhieuGiamGia);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean xoa(String maPhieuGiamGia) {
        int check = 0;
        String sql = """
        UPDATE [dbo].[PhieuGiamGia] SET [Deleted] = 0
             WHERE MaPhieuGiamGia = ?
             """;
        try ( Connection con = DBconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, maPhieuGiamGia);

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean ketThuc(String maPhieuGiamGia) {
        int check = 0;
        String sql = """
        UPDATE [dbo].[PhieuGiamGia]
           SET [TrangThai] = N'Đã kết thúc'
         WHERE MaPhieuGiamGia = ?
                     """;
        try ( Connection con = DBconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, maPhieuGiamGia);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
    
     public List<GiamGiaViewModel> getAll1() {
        List<GiamGiaViewModel> listgg = new ArrayList<>();

        String sql = """
            SELECT dbo.PhieuGiamGia.MaPhieuGiamGia, dbo.PhieuGiamGia.TenKhuyenMai, dbo.PhieuGiamGia.NgayBatDau, dbo.PhieuGiamGia.NgayKetThuc, dbo.PhieuGiamGia.SoLuongPhieu, dbo.PhieuGiamGia.HoaDonToiThieu, dbo.PhieuGiamGia.PhanTramGiam, 
                    dbo.PhieuGiamGia.SoTienGiamToiDa, dbo.PhieuGiamGia.CreatedAt, dbo.NhanVien.TenNhanVien, dbo.PhieuGiamGia.TrangThai, dbo.PhieuGiamGia.ID
            FROM   dbo.NhanVien INNER JOIN
                    dbo.PhieuGiamGia ON dbo.NhanVien.ID = dbo.PhieuGiamGia.idNhanVien
                    WHERE dbo.PhieuGiamGia.Deleted = 1 and PhieuGiamGia.TrangThai like N'Đang diễn ra'
                    ORDER BY dbo.PhieuGiamGia.CreatedAt DESC
                    """;
        try ( Connection con = DBconnect.getConnection();  PreparedStatement ps = con.prepareCall(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GiamGiaViewModel ggvm = new GiamGiaViewModel();
                ggvm.setMaPhieuGiamGia(rs.getString(1));
                ggvm.setTenKhuyenMai(rs.getString(2));
                ggvm.setNgayBatDau(rs.getDate(3));
                ggvm.setNgayKetThuc(rs.getDate(4));
                ggvm.setSoLuongPhieu(rs.getInt(5));
                ggvm.setHoaDonToiThieu(rs.getDouble(6));
                ggvm.setPhanTramGiam(rs.getDouble(7));
                ggvm.setSoTienGiamToiDa(rs.getDouble(8));
                ggvm.setNgayTao(rs.getDate(9));
                ggvm.setNguoiTao(rs.getString(10));
                ggvm.setTrangThai(rs.getString(11));
                ggvm.setIdPhieuGiamGia(rs.getString(12));
                listgg.add(ggvm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listgg;
    }

    public static void main(String[] args) {
        List<GiamGiaViewModel> lists = new GiamGiaDao().getAll();
        for (GiamGiaViewModel list : lists) {
            System.out.println(list.toString());
        }
    }
}
