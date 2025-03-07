/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KhachHang;
import java.sql.Connection;//phai code tay
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class CBORepositoty {
        public List<KhachHangEntity> getCBO() {
        List<KhachHangEntity> list = new ArrayList<>();
        String sql = """
        SELECT [gioiTinh]

        FROM [dbo].[KhachHang]
                    """;
        try (Connection con = DBConnectKhachHang.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHangEntity svl = new KhachHangEntity();
                svl.setGioiTinh(rs.getString(1));

                list.add(svl);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
