/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import HoaDonChiTiet.HoaDonChiTiet;
import bpurse.qlts.entity.ChiTietSanPham;
import java.util.ArrayList;
import java.util.List;
import viewmodel.BanHangViewModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author tung pc
 */
public class BanHangDao {

    public List<BanHangViewModel> getAll() {

        List<BanHangViewModel> listBH = new ArrayList<>();

        String sql = """
                        SELECT dbo.ChiTietSanPham.MaCTSP
                         , dbo.SanPham.TenSanPham
                         , dbo.MauSac.TenMauSac
                         , dbo.ChatLieu.TenChatLieu
                         ,dbo.KichCo.TenKichCo
                         , dbo.NhaSanXuat.TenNhaSanXuat
                         , dbo.ThuongHieu.ThuongHieu
                         ,dbo.ChiTietSanPham.GiaBan
                         , dbo.ChiTietSanPham.trangThai
                         , dbo.ChiTietSanPham.SoLuong
                         , dbo.ChiTietSanPham.ID

                        FROM   dbo.ChiTietSanPham INNER JOIN
                                     dbo.ChatLieu ON dbo.ChiTietSanPham.idChatLieu = dbo.ChatLieu.ID AND dbo.ChatLieu.Deleted = 1 INNER JOIN
                                     dbo.NhaSanXuat ON dbo.ChiTietSanPham.idNhaSanXuat = dbo.NhaSanXuat.ID AND dbo.NhaSanXuat.Deleted = 1 INNER JOIN
                                     dbo.SanPham ON dbo.ChiTietSanPham.MaSanPham = dbo.SanPham.ID AND dbo.SanPham.Deleted = 1 INNER JOIN
                                     dbo.ThuongHieu ON dbo.ChiTietSanPham.idThuongHieu = dbo.ThuongHieu.ID AND dbo.ThuongHieu.Deleted = 1 INNER JOIN
                                     dbo.MauSac ON dbo.ChiTietSanPham.idMauSac = dbo.MauSac.ID AND dbo.MauSac.Deleted = 1 INNER JOIN
                                     dbo.KichCo ON dbo.ChiTietSanPham.idKichCo = dbo.KichCo.ID AND dbo.KichCo.Deleted = 1
                        WHERE dbo.ChiTietSanPham.Deleted = 1
                     """;

        try ( Connection con = DBconnect.getConnection();  PreparedStatement ps = con.prepareCall(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BanHangViewModel bh = new BanHangViewModel();
                bh.setMa(rs.getString(1));
                bh.setTenSP(rs.getString(2));
                bh.setTenMS(rs.getString(3));
                bh.setTenCL(rs.getString(4));
                bh.setKichCo(rs.getString(5));
                bh.setTenNSX(rs.getString(6));
                bh.setTenTH(rs.getString(7));
                bh.setGiaBan(rs.getDouble(8));
                bh.setTrangThai(rs.getString(9));
                bh.setSoLuong(rs.getInt(10));
                bh.setId(rs.getString(11));
                listBH.add(bh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listBH;
    }
    
    public List<BanHangViewModel> getAll1(String idSP) {

        List<BanHangViewModel> listBH = new ArrayList<>();

        String sql = """
                        SELECT dbo.ChiTietSanPham.MaCTSP
                         , dbo.SanPham.TenSanPham
                         , dbo.MauSac.TenMauSac
                         , dbo.ChatLieu.TenChatLieu
                         ,dbo.KichCo.TenKichCo
                         , dbo.NhaSanXuat.TenNhaSanXuat
                         , dbo.ThuongHieu.ThuongHieu
                         ,dbo.ChiTietSanPham.GiaBan
                         , dbo.ChiTietSanPham.trangThai
                         , dbo.ChiTietSanPham.SoLuong
                         , dbo.ChiTietSanPham.ID

                        FROM   dbo.ChiTietSanPham INNER JOIN
                                     dbo.ChatLieu ON dbo.ChiTietSanPham.idChatLieu = dbo.ChatLieu.ID AND dbo.ChatLieu.Deleted = 1 INNER JOIN
                                     dbo.NhaSanXuat ON dbo.ChiTietSanPham.idNhaSanXuat = dbo.NhaSanXuat.ID AND dbo.NhaSanXuat.Deleted = 1 INNER JOIN
                                     dbo.SanPham ON dbo.ChiTietSanPham.MaSanPham = dbo.SanPham.ID AND dbo.SanPham.Deleted = 1 INNER JOIN
                                     dbo.ThuongHieu ON dbo.ChiTietSanPham.idThuongHieu = dbo.ThuongHieu.ID AND dbo.ThuongHieu.Deleted = 1 INNER JOIN
                                     dbo.MauSac ON dbo.ChiTietSanPham.idMauSac = dbo.MauSac.ID AND dbo.MauSac.Deleted = 1 INNER JOIN
                                     dbo.KichCo ON dbo.ChiTietSanPham.idKichCo = dbo.KichCo.ID AND dbo.KichCo.Deleted = 1
                        WHERE dbo.ChiTietSanPham.Deleted = 1 and ChiTietSanPham.ID = ?
                     """;

        try ( Connection con = DBconnect.getConnection();  PreparedStatement ps = con.prepareCall(sql)) {
            ps.setObject(1, idSP);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BanHangViewModel bh = new BanHangViewModel();
                bh.setMa(rs.getString(1));
                bh.setTenSP(rs.getString(2));
                bh.setTenMS(rs.getString(3));
                bh.setTenCL(rs.getString(4));
                bh.setKichCo(rs.getString(5));
                bh.setTenNSX(rs.getString(6));
                bh.setTenTH(rs.getString(7));
                bh.setGiaBan(rs.getDouble(8));
                bh.setTrangThai(rs.getString(9));
                bh.setSoLuong(rs.getInt(10));
                bh.setId(rs.getString(11));
                listBH.add(bh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listBH;
    }

    public List<BanHangViewModel> tim(String search) {

        List<BanHangViewModel> listBH = new ArrayList<>();
        search = search.trim();

        String sql = """
                    SELECT dbo.ChiTietSanPham.MaCTSP
                                         , dbo.SanPham.TenSanPham
                                         , dbo.MauSac.TenMauSac
                                         , dbo.ChatLieu.TenChatLieu
                                         ,dbo.KichCo.TenKichCo
                                         , dbo.NhaSanXuat.TenNhaSanXuat
                                         , dbo.ThuongHieu.ThuongHieu
                                         ,dbo.ChiTietSanPham.GiaBan
                                         , dbo.ChiTietSanPham.trangThai
                                         , dbo.ChiTietSanPham.SoLuong
                                         , dbo.ChiTietSanPham.ID
                     FROM   dbo.ChiTietSanPham INNER JOIN
                                 dbo.ChatLieu ON dbo.ChiTietSanPham.idChatLieu = dbo.ChatLieu.ID AND dbo.ChatLieu.Deleted = 1 INNER JOIN
                                 dbo.NhaSanXuat ON dbo.ChiTietSanPham.idNhaSanXuat = dbo.NhaSanXuat.ID AND dbo.NhaSanXuat.Deleted = 1 INNER JOIN
                                 dbo.SanPham ON dbo.ChiTietSanPham.MaSanPham = dbo.SanPham.ID AND dbo.SanPham.Deleted = 1 INNER JOIN
                                 dbo.ThuongHieu ON dbo.ChiTietSanPham.idThuongHieu = dbo.ThuongHieu.ID AND dbo.ThuongHieu.Deleted = 1 INNER JOIN
                                 dbo.MauSac ON dbo.ChiTietSanPham.idMauSac = dbo.MauSac.ID AND dbo.MauSac.Deleted = 1 INNER JOIN
                                 dbo.KichCo ON dbo.ChiTietSanPham.idKichCo = dbo.KichCo.ID AND dbo.KichCo.Deleted = 1
                    WHERE dbo.ChiTietSanPham.Deleted = 1 AND (LOWER(dbo.ThuongHieu.ThuongHieu) COLLATE SQL_Latin1_General_CP1_CI_AI LIKE ? OR LOWER(dbo.MauSac.TenMauSac) COLLATE SQL_Latin1_General_CP1_CI_AI LIKE ? OR LOWER(dbo.KichCo.TenKichCo) COLLATE SQL_Latin1_General_CP1_CI_AI LIKE ? OR LOWER(dbo.ChatLieu.TenChatLieu) COLLATE SQL_Latin1_General_CP1_CI_AI LIKE ?)
                     """;

        try ( Connection con = DBconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, "%" + search.toLowerCase() + "%");
            ps.setObject(2, "%" + search.toLowerCase() + "%");
            ps.setObject(3, "%" + search.toLowerCase() + "%");
            ps.setObject(4, "%" + search.toLowerCase() + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                BanHangViewModel bh = new BanHangViewModel();
                bh.setMa(rs.getString(1));
                bh.setTenSP(rs.getString(2));
                bh.setTenMS(rs.getString(3));
                bh.setTenCL(rs.getString(4));
                bh.setKichCo(rs.getString(5));
                bh.setTenNSX(rs.getString(6));
                bh.setTenTH(rs.getString(7));
                bh.setGiaBan(rs.getDouble(8));
                bh.setTrangThai(rs.getString(9));
                bh.setSoLuong(rs.getInt(10));
                bh.setId(rs.getString(11));
                listBH.add(bh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listBH;
    }

    public List<BanHangViewModel> timT(String search) {

        List<BanHangViewModel> listBH = new ArrayList<>();
        search = search.trim().toLowerCase();

        String sql = """
                    SELECT dbo.ChiTietSanPham.MaCTSP
                           
                                         , dbo.SanPham.TenSanPham
                                         , dbo.MauSac.TenMauSac
                                         , dbo.ChatLieu.TenChatLieu
                                         , dbo.KichCo.TenKichCo
                                         , dbo.NhaSanXuat.TenNhaSanXuat
                                         , dbo.ThuongHieu.ThuongHieu
                                         , dbo.ChiTietSanPham.GiaBan
                                         , dbo.ChiTietSanPham.trangThai
                                         , dbo.ChiTietSanPham.SoLuong
                                         , dbo.ChiTietSanPham.ID
                     FROM   dbo.ChiTietSanPham INNER JOIN
                                 dbo.ChatLieu ON dbo.ChiTietSanPham.idChatLieu = dbo.ChatLieu.ID AND dbo.ChatLieu.Deleted = 1 INNER JOIN
                                 dbo.NhaSanXuat ON dbo.ChiTietSanPham.idNhaSanXuat = dbo.NhaSanXuat.ID AND dbo.NhaSanXuat.Deleted = 1 INNER JOIN
                                 dbo.SanPham ON dbo.ChiTietSanPham.MaSanPham = dbo.SanPham.ID AND dbo.SanPham.Deleted = 1 INNER JOIN
                                 dbo.ThuongHieu ON dbo.ChiTietSanPham.idThuongHieu = dbo.ThuongHieu.ID AND dbo.ThuongHieu.Deleted = 1 INNER JOIN
                                 dbo.MauSac ON dbo.ChiTietSanPham.idMauSac = dbo.MauSac.ID AND dbo.MauSac.Deleted = 1 INNER JOIN
                                 dbo.KichCo ON dbo.ChiTietSanPham.idKichCo = dbo.KichCo.ID AND dbo.KichCo.Deleted = 1
                    WHERE dbo.ChiTietSanPham.Deleted = 1 AND (LOWER(dbo.ChiTietSanPham.MaCTSP) COLLATE SQL_Latin1_General_CP1_CI_AI LIKE ? OR LOWER(dbo.SanPham.TenSanPham) COLLATE SQL_Latin1_General_CP1_CI_AI LIKE ? OR LOWER(dbo.ChiTietSanPham.trangThai) COLLATE SQL_Latin1_General_CP1_CI_AI LIKE ?)
                     """;

        try ( Connection con = DBconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, "%" + search + "%");
            ps.setObject(2, "%" + search + "%");
            ps.setObject(3, "%" + search + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                BanHangViewModel bh = new BanHangViewModel();
                bh.setMa(rs.getString(1));
                bh.setTenSP(rs.getString(2));
                bh.setTenMS(rs.getString(3));
                bh.setTenCL(rs.getString(4));
                bh.setKichCo(rs.getString(5));
                bh.setTenNSX(rs.getString(6));
                bh.setTenTH(rs.getString(7));
                bh.setGiaBan(rs.getDouble(8));
                bh.setTrangThai(rs.getString(9));
                bh.setSoLuong(rs.getInt(10));
                bh.setId(rs.getString(11));
                listBH.add(bh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listBH;
    }

    public List<BanHangViewModel> timKetHop(String m, String nxs, String cl, String kc) {
        List<BanHangViewModel> listBH = new ArrayList<>();
        String sql = """
                SELECT dbo.ChiTietSanPham.MaCTSP, dbo.SanPham.TenSanPham, dbo.MauSac.TenMauSac, dbo.ChatLieu.TenChatLieu, dbo.KichCo.TenKichCo, dbo.NhaSanXuat.TenNhaSanXuat, dbo.ThuongHieu.ThuongHieu, dbo.ChiTietSanPham.GiaBan, dbo.ChiTietSanPham.trangThai
                FROM   dbo.ChiTietSanPham INNER JOIN
                             dbo.ChatLieu ON dbo.ChiTietSanPham.idChatLieu = dbo.ChatLieu.ID AND dbo.ChatLieu.Deleted = 1 INNER JOIN
                             dbo.NhaSanXuat ON dbo.ChiTietSanPham.idNhaSanXuat = dbo.NhaSanXuat.ID AND dbo.NhaSanXuat.Deleted = 1 INNER JOIN
                             dbo.SanPham ON dbo.ChiTietSanPham.MaSanPham = dbo.SanPham.ID AND dbo.SanPham.Deleted = 1 INNER JOIN
                             dbo.ThuongHieu ON dbo.ChiTietSanPham.idThuongHieu = dbo.ThuongHieu.ID AND dbo.ThuongHieu.Deleted = 1 INNER JOIN
                             dbo.MauSac ON dbo.ChiTietSanPham.idMauSac = dbo.MauSac.ID AND dbo.MauSac.Deleted = 1 INNER JOIN
                             dbo.KichCo ON dbo.ChiTietSanPham.idKichCo = dbo.KichCo.ID AND dbo.KichCo.Deleted = 1
                WHERE dbo.ChiTietSanPham.Deleted = 1 AND (LOWER(dbo.ThuongHieu.ThuongHieu) COLLATE SQL_Latin1_General_CP1_CI_AI LIKE ? OR LOWER(dbo.MauSac.TenMauSac) COLLATE SQL_Latin1_General_CP1_CI_AI LIKE ? OR LOWER(dbo.KichCo.TenKichCo) COLLATE SQL_Latin1_General_CP1_CI_AI LIKE ? OR LOWER(dbo.ChatLieu.TenChatLieu) COLLATE SQL_Latin1_General_CP1_CI_AI LIKE ?)
                 """;
        try ( Connection con = DBconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, "%" + m.toLowerCase() + "%");
            ps.setObject(2, "%" + nxs.toLowerCase() + "%");
            ps.setObject(3, "%" + cl.toLowerCase() + "%");
            ps.setObject(4, "%" + kc.toLowerCase() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BanHangViewModel bh = new BanHangViewModel();
                bh.setMa(rs.getString(1));
                bh.setTenSP(rs.getString(2));
                bh.setTenMS(rs.getString(3));
                bh.setTenCL(rs.getString(4));
                bh.setKichCo(rs.getString(5));
                bh.setTenNSX(rs.getString(6));
                bh.setTenTH(rs.getString(7));
                bh.setGiaBan(rs.getDouble(8));
                bh.setTrangThai(rs.getString(9));
                listBH.add(bh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listBH;
    }

    public boolean huyHD(List<ChiTietSanPham> spctList, String idHD, int SLSP) {
        int check = 0;
        String sqlUpdateHD = """
                UPDATE [dbo].[HoaDon]
                        SET [Deleted] = 0
                           ,[idKhachHang] = null
                           ,[TenKhachHang] = null
                           ,[SoDienThoai] = null
                           ,[DiachiKhachHang] = null
                           ,[TongTien] = 0
                      WHERE ID = ?    
                             
                             
                INSERT INTO [dbo].[LichSuHoaDon]
                                        ([idHoaDon]
                                        ,[idNhanVien]
                                        ,[HanhDong]
                                        )
                                  VALUES
                                        (?, '750C2AEF-90DD-46E5-8580-E279B96D9609', N'Huỷ hoá đơn')             
                             """;
        String sqlInsertHoaDonChiTiet = """
                                        
             UPDATE [dbo].[ChiTietSanPham]
            SET [SoLuong] = ?

          WHERE [ID]= CONVERT(uniqueidentifier, ?)

              UPDATE [dbo].[ChiTietSanPham]
                 SET 
                    [trangThai] = N'Còn hàng'

               WHERE SoLuong >0

               UPDATE [dbo].[ChiTietSanPham]
                 SET 
                    [trangThai] = N'Hết hàng'

               WHERE SoLuong = 0
                                        
                          
                                                                             
             """;

        try ( Connection cnt = DBconnect.getConnection();  PreparedStatement psUpdate = cnt.prepareStatement(sqlUpdateHD);  PreparedStatement psInsert = cnt.prepareStatement(sqlInsertHoaDonChiTiet)) {

            cnt.setAutoCommit(false); // Bắt đầu một giao dịch
            psUpdate.setObject(1, idHD);
            psUpdate.setObject(2, idHD);
            psUpdate.addBatch();

            // Thực hiện cập nhật danh sách chi tiết hóa đơn
            for (ChiTietSanPham spct : spctList) {
                // Tách giá trị idimel và idCTSP\
                String[] idCTSPList = spct.getID().split("\n");

                // Thêm từng cặp idimel và idCTSP vào cơ sở dữ liệu
                for (int i = 0; i < idCTSPList.length; i++) {
                    psInsert.setObject(1, SLSP);
                    psInsert.setObject(2, idCTSPList[i]);

                    psInsert.addBatch(); // Thêm câu lệnh insert vào batch
                }
            }

            // Thực thi batch cho cả update và insert
            // Thực thi batch cho cả update và insert
            int[] updateCounts = psUpdate.executeBatch();
            int[] insertCounts = psInsert.executeBatch();

            // Kiểm tra kết quả của cả hai batch
            for (int updateCount : updateCounts) {
                if (updateCount == PreparedStatement.EXECUTE_FAILED) {
                    throw new Exception("Update hoa don failed");
                }
                check += updateCount;
            }

            // Kiểm tra kết quả của cả hai batch
            for (int insertCount : insertCounts) {
                if (insertCount == PreparedStatement.EXECUTE_FAILED) {
                    throw new Exception("Xoa hoa don chi tiet failed");
                }
                check += insertCount;
            }

            cnt.commit(); // Commit giao dịch
        } catch (Exception e) {
            e.printStackTrace();
            check = 0;
        }

        return check > 0;
    }

    public boolean AddSPGH(HoaDonChiTiet hdct1, ChiTietSanPham ctsp1, String idHD, String idSP, int SLSP, int SLHDCT) {

        int check = 0;
        int check1 = 0;
        String sqlUpdateSoLuongCTSP = """
              UPDATE [dbo].[ChiTietSanPham]
                                    SET [SoLuong] = ?
                                 
                                  WHERE [ID]= CONVERT(uniqueidentifier, ?)
                                      
                                      
                                      UPDATE [dbo].[ChiTietSanPham]
                                         SET 
                                            [trangThai] = N'Còn hàng'
                                           
                                       WHERE SoLuong >0
                                      
                                       UPDATE [dbo].[ChiTietSanPham]
                                         SET 
                                            [trangThai] = N'Hết hàng'
                                           
                                       WHERE SoLuong = 0
             """;

        String sqlInsertHoaDonChiTiet = """
            INSERT INTO [dbo].[HoaDonChiTiet]
                            (   [idChiTietSP],
                                [idHoaDon],
                                [DonGia],
                                [SoLuong]
                            )
                            VALUES
                            (CONVERT(uniqueidentifier, ?)
                            ,CONVERT(uniqueidentifier, ?)
                            ,(SELECT GiaBan FROM ChiTietSanPham WHERE ID = CONVERT(uniqueidentifier, ?))
                            ,?)
                          
             """;

        try ( Connection cnt = DBconnect.getConnection();  PreparedStatement psUpdate = cnt.prepareStatement(sqlUpdateSoLuongCTSP);  PreparedStatement psInsert = cnt.prepareStatement(sqlInsertHoaDonChiTiet)) {

            psUpdate.setObject(1, SLSP);
            psUpdate.setObject(2, idSP);
            check1 = psUpdate.executeUpdate(); // Thêm câu     lệnh update vào batch

            // Thêm từng cặp idimel và idCTSP vào cơ sở dữ liệu
            psInsert.setObject(1, idSP);
            psInsert.setObject(2, idHD);
            psInsert.setObject(3, idSP);
            psInsert.setObject(4, SLHDCT);
            check = psInsert.executeUpdate();
// Thêm câu lệnh insert vào batch

            // Thực thi batch cho cả update và insert
        } catch (Exception e) {
            e.printStackTrace();

        }

        return check > 0 && check1 > 0;
    }

    public boolean UpdateTrungSP(String idHD, String idSP, int SLSP, int SLHDCT) {

        int check = 0;
        int check1 = 0;
        String sqlUpdateSoLuongCTSP = """
              UPDATE [dbo].[ChiTietSanPham]
                                    SET [SoLuong] = ?
                                 
                                  WHERE [ID]= CONVERT(uniqueidentifier, ?)
            
                                      
              UPDATE [dbo].[ChiTietSanPham]
                SET 
                   [trangThai] = N'Còn hàng'
                  
              WHERE SoLuong >0
             
              UPDATE [dbo].[ChiTietSanPham]
                SET 
                   [trangThai] = N'Hết hàng'
                  
              WHERE SoLuong = 0
                                      
                                      """;

        String sqlInsertHoaDonChiTiet = """
            Update   [dbo].[HoaDonChiTiet]
                                        SET [SoLuong] = ?
                                                    
                                        WHERE [idChiTietSP]= CONVERT(uniqueidentifier, ?) 
            				and   [idHoaDon]= CONVERT(uniqueidentifier, ?) 
                          
             """;

        try ( Connection cnt = DBconnect.getConnection();  PreparedStatement psUpdate = cnt.prepareStatement(sqlUpdateSoLuongCTSP);  PreparedStatement psInsert = cnt.prepareStatement(sqlInsertHoaDonChiTiet)) {

            psUpdate.setObject(1, SLSP);
            psUpdate.setObject(2, idSP);
            check1 = psUpdate.executeUpdate(); // Thêm câu     lệnh update vào batch

            // Thêm từng cặp idimel và idCTSP vào cơ sở dữ liệu
            psInsert.setObject(1, SLHDCT);
            psInsert.setObject(2, idSP);
            psInsert.setObject(3, idHD);
            check = psInsert.executeUpdate();
// Thêm câu lệnh insert vào batch

            // Thực thi batch cho cả update và insert
        } catch (Exception e) {
            e.printStackTrace();

        }

        return check > 0 && check1 > 0;
    }

    public boolean RemoveGH(String idHD, String idSP, int SLSP) {

        int check = 0;
        int check1 = 0;
        String sqlUpdateSoLuongCTSP = """
              UPDATE [dbo].[ChiTietSanPham]
                                    SET [SoLuong] = ?
                                 
                                  WHERE [ID]= CONVERT(uniqueidentifier, ?)
                                      
              UPDATE [dbo].[ChiTietSanPham]
                                         SET 
                                            [trangThai] = N'Còn hàng'
                                           
                                       WHERE SoLuong >0
                                      
                                       UPDATE [dbo].[ChiTietSanPham]
                                         SET 
                                            [trangThai] = N'Hết hàng'
                                           
                                       WHERE SoLuong = 0                        
             """;

        String sqlInsertHoaDonChiTiet = """
           Delete  From [dbo].[HoaDonChiTiet]                                                   
                                        WHERE [idChiTietSP]= CONVERT(uniqueidentifier, ?) 
            				and   [idHoaDon]= CONVERT(uniqueidentifier, ?) 
                                        
                                        
                          
             """;

        try ( Connection cnt = DBconnect.getConnection();  PreparedStatement psUpdate = cnt.prepareStatement(sqlUpdateSoLuongCTSP);  PreparedStatement psInsert = cnt.prepareStatement(sqlInsertHoaDonChiTiet)) {

            psUpdate.setObject(1, SLSP);
            psUpdate.setObject(2, idSP);
            check1 = psUpdate.executeUpdate(); // Thêm câu     lệnh update vào batch

            // Thêm từng cặp idimel và idCTSP vào cơ sở dữ liệu
            psInsert.setObject(1, idSP);
            psInsert.setObject(2, idHD);
            check = psInsert.executeUpdate();
// Thêm câu lệnh insert vào batch

            // Thực thi batch cho cả update và insert
        } catch (Exception e) {
            e.printStackTrace();

        }

        return check > 0 && check1 > 0;
    }

    public boolean thanhToan(String idHD, String idHTTT, float tienMat, float tienCK, String idKH, String idMaGG) {

        int check1 = 0;
        String sqlUpdateSoLuongCTSP = """
              UPDATE [dbo].[HoaDon]
                                    SET [TrangThai] = N'Đã thanh toán'
                                        ,[idKhachHang] = ?
                                        ,[idMaGiamGia] = ?
                                        ,[NgayThanhToan] = getDate()
                                  WHERE [ID]= CONVERT(uniqueidentifier, ?)
                                      
                                      
            INSERT INTO [dbo].[LichSuHoaDon]
                        ([idHoaDon]
                        ,[idNhanVien]
                        ,[HanhDong]
                        )
                  VALUES
                        (?, '750C2AEF-90DD-46E5-8580-E279B96D9609', N'Thanh toán')  
                                      
            INSERT INTO [dbo].[HinhThucThanhToan]
                        ([idHoaDon]
                        ,[idPhuongThucThanhToan]
                        ,[TienChuyenKHoan]
                        ,[TienMat]
                               )
                  VALUES
                        (?,?,?,?)                           
             """;

        try ( Connection cnt = DBconnect.getConnection();  PreparedStatement psUpdate = cnt.prepareStatement(sqlUpdateSoLuongCTSP);) {
            psUpdate.setObject(1, idKH);
            psUpdate.setObject(2, idMaGG);
            psUpdate.setObject(3, idHD);
            psUpdate.setObject(4, idHD);
            psUpdate.setObject(5, idHD);
            psUpdate.setObject(6, idHTTT);
            psUpdate.setObject(7, tienCK);
            psUpdate.setObject(8, tienMat);
            
            check1 = psUpdate.executeUpdate(); // Thêm câu     lệnh update vào batch

            // Thêm từng cặp idimel và idCTSP vào cơ sở dữ liệu
// Thêm câu lệnh insert vào batch
            // Thực thi batch cho cả update và insert
        } catch (Exception e) {
            e.printStackTrace();

        }

        return check1 > 0;
    }
    
    public boolean thanhToan1(String idHD, String idHTTT, float tienMat, float tienCK, String idKH, String idMaGG, int soLuong) {

        int check1 = 0;
        String sqlUpdateSoLuongCTSP = """
              UPDATE [dbo].[HoaDon]
                                    SET [TrangThai] = N'Đã thanh toán'
                                        ,[idKhachHang] = ?
                                        ,[idMaGiamGia] = ?
                                        ,[NgayThanhToan] = getDate()
                                  WHERE [ID]= CONVERT(uniqueidentifier, ?)
                                      
                                      
            INSERT INTO [dbo].[LichSuHoaDon]
                        ([idHoaDon]
                        ,[idNhanVien]
                        ,[HanhDong]
                        )
                  VALUES
                        (?, '750C2AEF-90DD-46E5-8580-E279B96D9609', N'Thanh toán')  
                                      
            INSERT INTO [dbo].[HinhThucThanhToan]
                        ([idHoaDon]
                        ,[idPhuongThucThanhToan]
                        ,[TienChuyenKHoan]
                        ,[TienMat]
                               )
                  VALUES
                        (?,?,?,?)         
                                      
           UPDATE [dbo].[PhieuGiamGia]
               SET SoLuongPhieu = ?
                                       
             WHERE [ID] = ?   
                                      
           UPDATE [dbo].[PhieuGiamGia]
                           SET TrangThai = N'Đã kết thúc'          
                         WHERE SoLuongPhieu = 0  
             """;

        try ( Connection cnt = DBconnect.getConnection();  PreparedStatement psUpdate = cnt.prepareStatement(sqlUpdateSoLuongCTSP);) {
            psUpdate.setObject(1, idKH);
            psUpdate.setObject(2, idMaGG);
            psUpdate.setObject(3, idHD);
            psUpdate.setObject(4, idHD);
            psUpdate.setObject(5, idHD);
            psUpdate.setObject(6, idHTTT);
            psUpdate.setObject(7, tienCK);
            psUpdate.setObject(8, tienMat);
            psUpdate.setObject(9, soLuong);
            psUpdate.setObject(10, idMaGG);
            
            check1 = psUpdate.executeUpdate(); // Thêm câu     lệnh update vào batch

            // Thêm từng cặp idimel và idCTSP vào cơ sở dữ liệu
// Thêm câu lệnh insert vào batch
            // Thực thi batch cho cả update và insert
        } catch (Exception e) {
            e.printStackTrace();

        }

        return check1 > 0;
    }

    public static void main(String[] args) {
        List<BanHangViewModel> list = new BanHangDao().getAll();
        for (BanHangViewModel banHangViewModel : list) {
            System.out.println(banHangViewModel.toString());
        }

    }
}
