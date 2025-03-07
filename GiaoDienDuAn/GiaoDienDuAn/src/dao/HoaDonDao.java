/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import viewmodel.HoaDonViewModel;

/**
 *
 * @author tung pc
 */
public class HoaDonDao {

    public List<HoaDonViewModel> getHD() {

        List<HoaDonViewModel> listHD = new ArrayList<>();

        String sql = """
                   
                   SELECT 
                    HoaDon.ID, 
                    HoaDon.MaHoaDon,
                    HoaDon.NgayTao,
                    HoaDon.CreatedBy, 
                    Sum(HoaDonChiTiet.SoLuong) AS TongSoSanPham,
                    HoaDon.TrangThai,
                    KhachHang.TenKhachHang,
                    KhachHang.MaKhachHang,
                    HoaDon.idKhachHang
                FROM   
                    dbo.HoaDon 

                LEFT JOIN
                    dbo.HinhThucThanhToan ON HoaDon.ID = HinhThucThanhToan.IDHoaDon 
                LEFT JOIN
                    dbo.PhuongThucThanhToan ON HinhThucThanhToan.IDPhuongThucThanhToan = PhuongThucThanhToan.ID
                LEFT JOIN
                    dbo.HoaDonChiTiet ON HoaDon.ID = HoaDonChiTiet.IDHoaDon
                LEFT JOIN 
                    dbo.KhachHang on HoaDon.IDKhachHang = KhachHang.ID     
                                            Where HoaDon.Deleted = 1 and TrangThai = N'Chờ thanh toán'
                GROUP BY
                    HoaDon.ID, 
                    HoaDon.MaHoaDon,
                    HoaDon.NgayTao,
                    HoaDon.CreatedBy, 
                    HoaDon.TrangThai,
                    KhachHang.TenKhachHang,
                    KhachHang.MaKhachHang,
                     HoaDon.idKhachHang
                ORDER BY 
                    MAX(HoaDon.NgayTao) DESC
                     """;

        try ( Connection con = DBconnect.getConnection();  PreparedStatement ps = con.prepareCall(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonViewModel hd = new HoaDonViewModel();
                hd.setId(rs.getString(1));
                hd.setMa(rs.getString(2));
                hd.setNgayTao(rs.getDate(3));
                hd.setnV(rs.getString(4));
                hd.setTongSP(rs.getInt(5));
                hd.settT(rs.getString(6));
                hd.setTenKH(rs.getString(7));
                hd.setMaKH(rs.getString(8));
                hd.setIdKH(rs.getString(9));
                listHD.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHD;
    }

    public boolean themHoaDon(HoaDonViewModel hdMoi) {
        String sql = """
                  INSERT INTO [dbo].[HoaDon]
                             ([idNhanVien]
                             ,[TrangThai]
                             )
                       VALUES
                             ('750C2AEF-90DD-46E5-8580-E279B96D9609',N'Chờ thanh toán');
                     
                     INSERT INTO [dbo].[LichSuHoaDon]
                                           ([idHoaDon]
                                           ,[idNhanVien]
                                           ,[HanhDong]
                                           )
                                     VALUES
                                           ((Select top 1 [ID] from [dbo].[HoaDon] order by [dbo].[HoaDon].CreatedAt desc)
                                            ,'750C2AEF-90DD-46E5-8580-E279B96D9609',N'Tạo hoá đơn')
                 """;

        try ( Connection con = DBconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<HoaDonViewModel> getIDHD(String idHD) {

        List<HoaDonViewModel> listHD = new ArrayList<>();

        String sql = """
                   
                   SELECT 
                    HoaDon.ID, 
                    HoaDon.MaHoaDon,
                    HoaDon.NgayTao,
                    HoaDon.CreatedBy, 
                    Sum(HoaDonChiTiet.SoLuong) AS TongSoSanPham,
                    HoaDon.TrangThai,
                    KhachHang.TenKhachHang,
                    KhachHang.MaKhachHang,
                    HoaDon.idKhachHang
                FROM   
                    dbo.HoaDon 
                     
                LEFT JOIN
                    dbo.HinhThucThanhToan ON HoaDon.ID = HinhThucThanhToan.IDHoaDon 
                LEFT JOIN
                    dbo.PhuongThucThanhToan ON HinhThucThanhToan.IDPhuongThucThanhToan = PhuongThucThanhToan.ID
                LEFT JOIN
                    dbo.HoaDonChiTiet ON HoaDon.ID = HoaDonChiTiet.IDHoaDon
                LEFT JOIN 
                    dbo.KhachHang on HoaDon.IDKhachHang = KhachHang.ID     
                                            Where HoaDon.ID = ?
                GROUP BY
                    HoaDon.ID, 
                    HoaDon.MaHoaDon,
                    HoaDon.NgayTao,
                    HoaDon.CreatedBy, 
                    HoaDon.TrangThai,
                    KhachHang.TenKhachHang,
                    KhachHang.MaKhachHang,
                     HoaDon.idKhachHang
                ORDER BY 
                    MAX(HoaDon.NgayTao) DESC
                     """;

        try ( Connection con = DBconnect.getConnection();  PreparedStatement ps = con.prepareCall(sql)) {
            ps.setObject(1,idHD );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonViewModel hd = new HoaDonViewModel();
                hd.setId(rs.getString(1));
                hd.setMa(rs.getString(2));
                hd.setNgayTao(rs.getDate(3));
                hd.setnV(rs.getString(4));
                hd.setTongSP(rs.getInt(5));
                hd.settT(rs.getString(6));
                hd.setTenKH(rs.getString(7));
                hd.setMaKH(rs.getString(8));
                hd.setIdKH(rs.getString(9));
                listHD.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHD;
    }
    

    public static void main(String[] args) {
        List<HoaDonViewModel> list = new HoaDonDao().getHD();
        for (HoaDonViewModel hoaDonViewModel : list) {
            System.out.println(hoaDonViewModel.toString());
        }
    }
}
