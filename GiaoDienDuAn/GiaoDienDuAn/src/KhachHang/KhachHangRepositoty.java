/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KhachHang;

import bpurse.qlts.repo.DBConnect;
import java.sql.Connection;//phai code tay
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class KhachHangRepositoty {

    public List<KhachHangEntity> getKhachHang() {
        List<KhachHangEntity> listGV = new ArrayList<>();
        String sql = """
        SELECT [ID]
              ,[TenKhachHang]
              ,[SoDienThoai]
              ,[gioiTinh]
              ,[NgaySinh]
              ,[DiaChi]
              ,[Deleted]
              ,[MaKhachHang]       
              ,[CreatedAt]
              ,[CreatedBy]
              ,[UpdatedAt]
              ,[UpdatedBy]
                   
        FROM [dbo].[KhachHang]
                    where Deleted = 1
        ORDER BY [CreatedAt] DESC;
    """;

        try (java.sql.Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHangEntity kh = new KhachHangEntity();
                kh.setIDKhachHang(rs.getString(1));
                kh.setTenKhachHang(rs.getString(2));
                kh.setSoDienThoai(rs.getString(3));
                kh.setGioiTinh(rs.getString(4));
                kh.setNgaySinh(rs.getDate(5));
                kh.setDiaChi(rs.getString(6));
                kh.setDelete(rs.getBoolean(7));
                kh.setMaKhachHang(rs.getString(8));
                listGV.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listGV;
    }

    //===============
public List<KhachHangEntity> getCheckSDT() {
        List<KhachHangEntity> listGV = new ArrayList<>();
        String sql = "SELECT SoDienThoai FROM dbo.KhachHang";

        try (java.sql.Connection con = DBConnectKhachHang.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHangEntity kh = new KhachHangEntity();
                kh.setSoDienThoai(rs.getString("SoDienThoai"));
                listGV.add(kh); // Thêm số điện thoại vào danh sách
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listGV;
    }

    //////VIEW MODEL
    public List<LichSuEntity> getKhachHangLichSu(String idKH) {
        List<LichSuEntity> listGV = new ArrayList<>();
        String sql = """
SELECT dbo.KhachHang.ID,dbo.KhachHang.TenKhachHang, dbo.KhachHang.CreatedBy, dbo.LichSuHoaDon.UpdatedAt, dbo.LichSuHoaDon.HanhDong, dbo.HoaDon.MaHoaDon
        FROM     dbo.KhachHang INNER JOIN
                          dbo.HoaDon ON dbo.KhachHang.ID = dbo.HoaDon.idKhachHang INNER JOIN
                          dbo.LichSuHoaDon ON dbo.HoaDon.ID = dbo.LichSuHoaDon.idHoaDon
                     where dbo.KhachHang.ID = ?

    """;

        try (java.sql.Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, idKH);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LichSuEntity ls = new LichSuEntity();
                ls.setIdKhachHang(rs.getString(1));
                ls.setTenKhachHang(rs.getString(2));
                ls.setCreateBy(rs.getString(3));
                ls.setUpdateAt(rs.getDate(4));
                ls.setHanhDong(rs.getString(5));
                ls.setMaHD(rs.getString(6));
//                ls.setDiaChi(rs.getString(6));
//                ls.setDelete(rs.getBoolean(7));
                listGV.add(ls);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listGV;
    }
//    public List<KhachHangEntity> getKhachHangLichSuGiaoDich() {
//    List<KhachHangEntity> listKH = new ArrayList<>();
//    String sql = """
//                 SELECT [ID]
//                       ,[TenKhachHang]
//                       ,[SoDienThoai]
//                       ,[gioiTinh]
//                       ,[NgaySinh]
//                       ,[DiaChi]
//                       ,[Deleted]
//                       ,[CreatedAt]
//                       ,[CreatedBy]
//                       ,[UpdatedAt]
//                       ,[UpdatedBy]
//                   FROM [dbo].[KhachHang]
//                 """;
//
//    try (java.sql.Connection con = DBConnectKhachHang.getConnection(); 
//         PreparedStatement ps = con.prepareStatement(sql); 
//         ResultSet rs = ps.executeQuery()) {
//        
//        while (rs.next()) {
//            KhachHangEntity kh = new KhachHangEntity();
//            kh.setIDKhachHang(rs.getString(1));
//            kh.setTenKhachHang(rs.getString(2));
//            kh.setSoDienThoai(rs.getString(3));
//            kh.setGioiTinh(rs.getString(4));
//            kh.setNgaySinh(rs.getDate(5));
//            kh.setDiaChi(rs.getString(6));
////            kh.setDelete(rs.getBoolean("Deleted"));
////            kh.setCreatedAt(rs.getTimestamp("CreatedAt"));
////            kh.setCreatedAt(rs.getString("CreatedBy"));
////            kh.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
////            kh.setUpdatedBy(rs.getString("UpdatedBy"));
//            listKH.add(kh);
//        }
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//
//    return listKH;
//}

    public static void main(String[] args) {//psvm
        List<KhachHangEntity> list = new KhachHangRepositoty().getKhachHang();
        for (KhachHangEntity kh : list) {
            System.out.println(kh.toString());
        }
    }
//LichSuGiaoDich
    public List<KhachHangEntity> getCBO(String tt) {
        List<KhachHangEntity> listGV = new ArrayList<>();
        String sql = """
        SELECT [ID]
              ,[TenKhachHang]
              ,[SoDienThoai]
              ,[gioiTinh]
              ,[NgaySinh]
              ,[DiaChi]
              ,[MaKhachHang]
        FROM [dbo].[KhachHang]
                     
        where gioiTinh= ?
    """;
//              ,[Deleted]
//              ,[CreatedAt]
//              ,[CreatedBy]
//              ,[UpdatedAt]
//              ,[UpdatedBy]
        try (java.sql.Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, tt);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHangEntity kh = new KhachHangEntity();
                kh.setIDKhachHang(rs.getString(1));
                kh.setTenKhachHang(rs.getString(2));
                kh.setSoDienThoai(rs.getString(3));
                kh.setGioiTinh(rs.getString(4));
                kh.setNgaySinh(rs.getDate(5));
                kh.setDiaChi(rs.getString(6));
                kh.setMaKhachHang(rs.getString(7));
//                kh.setDelete(rs.getBoolean(7));
                listGV.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listGV;
    }
    //============================
//    public boolean Them(KhachHangEntity gv) {
//        int check = 0;
//        String sql = """
//INSERT INTO [dbo].[KhachHang]
//           (
//           [TenKhachHang]
//           ,[SoDienThoai]
//           ,[gioiTinh]
//           ,[NgaySinh]
//           ,[DiaChi]
//)
//     VALUES(?,?,?,?,?)
//                   """;
//        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
//
//            ps.setObject(1, gv.getTenKhachHang());
//            ps.setObject(2, gv.getSoDienThoai());
//            ps.setObject(3, gv.getGioiTinh());
//            ps.setObject(4, gv.getNgaySinh());
//            ps.setObject(5, gv.getDiaChi());
//            check = ps.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return check > 0;
//    }
public boolean Them(KhachHangEntity gv) {
    if (gv == null) {
        System.out.println("Đối tượng KhachHangEntity truyền vào là null.");
        return false;
    }
    
    int check = 0;
    String sql = """
        INSERT INTO [dbo].[KhachHang]
                   (
                   [TenKhachHang]
                   ,[SoDienThoai]
                   ,[gioiTinh]
                   ,[NgaySinh]
                   ,[DiaChi]
        )
        VALUES(?,?,?,?,?)
    """;
    try (Connection con = DBConnectKhachHang.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setObject(1, gv.getTenKhachHang());
        ps.setObject(2, gv.getSoDienThoai());
        ps.setObject(3, gv.getGioiTinh());
        ps.setObject(4, gv.getNgaySinh());
        ps.setObject(5, gv.getDiaChi());
        check = ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return check > 0;
}
    //===================
    public boolean Xoa(String id) {
        int check = 0;

        String sql = """
                   UPDATE [dbo].[KhachHang]
                     SET [Deleted] = 0
                         WHERE ID = ?;
                   """;
//        UPDATE [dbo].[KhachHangg]
//                                       SET [Deleted] = 0
//                                     WHERE  IDKhachHang = ?;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    ///
    public boolean Update(KhachHangEntity gv, String oldMa) {
        int check = 0;
        // B1: Write SQL
        String sql = """
UPDATE [dbo].[KhachHang]
                    SET 
                       [TenKhachHang] = ?
                       ,[SoDienThoai] = ?
                       ,[gioiTinh] = ?
                       ,[NgaySinh] = ?
                       ,[DiaChi] = ?

                  WHERE  ID = ?;
                 """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, gv.getTenKhachHang());
            ps.setObject(2, gv.getSoDienThoai());
            ps.setObject(3, gv.getGioiTinh());
            ps.setObject(4, gv.getNgaySinh());
            ps.setObject(5, gv.getDiaChi());
            ps.setObject(6, oldMa);

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();  //
        }
        return check > 0;
    }

    ////
    public List<KhachHangEntity> Search(String type) {
        List<KhachHangEntity> listKhachHang = new ArrayList<>();
        // Bước 1: Tạo SQL
        String sql = """
SELECT 
                          [TenKhachHang]
                          ,[SoDienThoai]
                          ,[gioiTinh]
                          ,[NgaySinh]
                          ,[DiaChi]
                          ,[MaKhachHang]

                      FROM [dbo].[KhachHang]
                    WHERE TenKhachHang LIKE ? OR
                          SoDienThoai LIKE ? OR
                          gioiTinh LIKE ? OR
                          NgaySinh LIKE ? OR
                          DiaChi LIKE ?;
                """;
        try (java.sql.Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            // Đặt tham số cho câu SQL
            String keywordLike = "%" + type + "%";
            for (int i = 1; i <= 5; i++) {
                ps.setString(i, keywordLike);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    KhachHangEntity khachHang = new KhachHangEntity();

                    khachHang.setTenKhachHang(rs.getString(1));
                    khachHang.setSoDienThoai(rs.getString(2));
                    khachHang.setGioiTinh(rs.getString(3));
                    khachHang.setNgaySinh(rs.getDate(4));
                    khachHang.setDiaChi(rs.getString(5));
                    khachHang.setMaKhachHang(rs.getString(6));

                    // Bước 6: Thêm vào danh sách
                    listKhachHang.add(khachHang);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Xử lý khi có lỗi
        }
        return listKhachHang;
    }

}
