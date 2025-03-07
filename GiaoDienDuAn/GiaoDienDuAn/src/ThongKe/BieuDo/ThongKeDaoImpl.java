package ThongKe.BieuDo;
import KhachHang.DBConnectKhachHang;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ThongKeDaoImpl implements ThongKeDao {

@Override
public List<LopHocBean> getListByLopHoc() {
    try {
        Connection cons = DBConnectKhachHang.getConnection();

        String sql = "SELECT NgayTao, SUM(dbo.HoaDonChiTiet.SoLuong * dbo.HoaDonChiTiet.DonGia) as 'TongTien' FROM HoaDon Inner join\n" +
"                                   dbo.HoaDonChiTiet ON dbo.HoaDon.ID = dbo.HoaDonChiTiet.idHoaDon  Inner join\n" +
"                                   dbo.KhachHang ON dbo.HoaDon.idKhachHang = dbo.KhachHang.ID  Inner join\n" +
"                                   dbo.NhanVien ON dbo.HoaDon.idNhanVien = dbo.NhanVien.ID where HoaDon.Deleted = 'True'\n" +
"\n" +
"\n" +
"                    group by dbo.HoaDon.ID, dbo.HoaDon.CreatedAt, dbo.HoaDon.UpdatedAt, dbo.KhachHang.TenKhachHang,dbo.HoaDon.TrangThai,\n" +
"                    HoaDon.NgayTao, dbo.KhachHang.DiaChi,    dbo.KhachHang.SoDienThoai, dbo.HoaDon.MaHoaDon, dbo.NhanVien.MaNhanVien  ";

        List<LopHocBean> list = new ArrayList<>();
        PreparedStatement ps = cons.prepareStatement(sql);   
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            LopHocBean bd = new LopHocBean();
            bd.setNgayTao(rs.getDate("NgayTao")); // Corrected column name
            bd.setTongTien(rs.getBigDecimal("TongTien")); // Corrected column name
            list.add(bd);
        }
        return list;
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return null;
}
///=====================================================



}








