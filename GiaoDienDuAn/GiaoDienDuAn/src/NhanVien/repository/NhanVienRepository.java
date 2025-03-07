/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NhanVien.repository;

import NhanVien.entity.NhanVien;
import NhanVien.viewmodel.NhanVienViewModel;
//import java.io.FileOutputStream;
//import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import org.apache.poi.common.usermodel.HyperlinkType;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.CreationHelper;
//import org.apache.poi.ss.usermodel.Font;
//import org.apache.poi.ss.usermodel.Hyperlink;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author NguyenVanKien
 */
public class NhanVienRepository {

    public List<NhanVienViewModel> getAll() {
        List<NhanVienViewModel> listNV = new ArrayList<>();
        String sql = """
                   						SELECT dbo.NhanVien.ID, dbo.NhanVien.MaNhanVien, dbo.NhanVien.TenNhanVien, dbo.NhanVien.SoDienThoai, dbo.NhanVien.Email, dbo.NhanVien.DiaChi, dbo.NhanVien.GioiTinh, dbo.NhanVien.NgaySinh, dbo.ChucVu.TenChucVu, 
                                                                       dbo.ChucVu.maChucVu,dbo.NhanVien.Deleted,dbo.NhanVien.CCCD
                                                       FROM     dbo.ChucVu INNER JOIN
                                                                       dbo.NhanVien ON dbo.ChucVu.ID = dbo.NhanVien.idchucVu
                   
                                      									order by dbo.NhanVien.CreatedAt desc 
                   """;

        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVienViewModel nv = new NhanVienViewModel();
                nv.setIdNhanVien(rs.getString(1));
                nv.setMaNhanVien(rs.getString(2));
                nv.setTenNhanVien(rs.getString(3));
                nv.setsDt(rs.getString(4));
                nv.setEmail(rs.getString(5));
                nv.setDiaChi(rs.getString(6));
                nv.setGioiTinh(rs.getString(7));
                nv.setNgaySinh(rs.getDate(8));
                nv.setChucVu(rs.getString(9));

                nv.setMaChucVu(rs.getString(10));
                nv.setTrangThai(rs.getBoolean(11));
                nv.setcCCD(rs.getString(12));
                listNV.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNV;
    }
    
    public List<NhanVienViewModel> getAllChucVu(String id) {
        List<NhanVienViewModel> listNV = new ArrayList<>();
        String sql = """
                   						SELECT dbo.NhanVien.ID, dbo.NhanVien.MaNhanVien, dbo.NhanVien.TenNhanVien, dbo.NhanVien.SoDienThoai, dbo.NhanVien.Email, dbo.NhanVien.DiaChi, dbo.NhanVien.GioiTinh, dbo.NhanVien.NgaySinh, dbo.ChucVu.TenChucVu, 
                                                                                                                                       dbo.ChucVu.maChucVu,dbo.NhanVien.Deleted,dbo.NhanVien.CCCD
                                                                                                                       FROM     dbo.ChucVu INNER JOIN
                                                                                                                                       dbo.NhanVien ON dbo.ChucVu.ID = dbo.NhanVien.idchucVu
                                                                                   where dbo.ChucVu.ID like ?
                                                                                                      									order by dbo.NhanVien.CreatedAt desc 
                   """;

        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVienViewModel nv = new NhanVienViewModel();
                nv.setIdNhanVien(rs.getString(1));
                nv.setMaNhanVien(rs.getString(2));
                nv.setTenNhanVien(rs.getString(3));
                nv.setsDt(rs.getString(4));
                nv.setEmail(rs.getString(5));
                nv.setDiaChi(rs.getString(6));
                nv.setGioiTinh(rs.getString(7));
                nv.setNgaySinh(rs.getDate(8));
                nv.setChucVu(rs.getString(9));

                nv.setMaChucVu(rs.getString(10));
                nv.setTrangThai(rs.getBoolean(11));
                nv.setcCCD(rs.getString(12));
                listNV.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNV;
    }
    
        

    public List<NhanVienViewModel> getAllTrangThai(int tt) {
        List<NhanVienViewModel> listNV = new ArrayList<>();
        String sql = """
                SELECT dbo.NhanVien.ID
                     , dbo.NhanVien.MaNhanVien
                     , dbo.NhanVien.TenNhanVien
                     , dbo.NhanVien.SoDienThoai
                     , dbo.NhanVien.Email
                     , dbo.NhanVien.DiaChi
                     , dbo.NhanVien.GioiTinh
                     , dbo.NhanVien.NgaySinh
                     , dbo.ChucVu.TenChucVu
                     , dbo.ChucVu.maChucVu
                     , dbo.NhanVien.Deleted
                      ,dbo.NhanVien.CCCD
                                       FROM     dbo.ChucVu INNER JOIN
                                                       dbo.NhanVien ON dbo.ChucVu.ID = dbo.NhanVien.idchucVu
   WHERE dbo.NhanVien.Deleted =?
                                                                                        order by dbo.NhanVien.CreatedAt desc 
   """;

        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, tt);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVienViewModel nv = new NhanVienViewModel();
                nv.setIdNhanVien(rs.getString(1));
                nv.setMaNhanVien(rs.getString(2));
                nv.setTenNhanVien(rs.getString(3));
                nv.setsDt(rs.getString(4));
                nv.setEmail(rs.getString(5));
                nv.setDiaChi(rs.getString(6));
                nv.setGioiTinh(rs.getString(7));
                nv.setNgaySinh(rs.getDate(8));
                nv.setChucVu(rs.getString(9));
                nv.setMaChucVu(rs.getString(10));
                nv.setTrangThai(rs.getBoolean(11));
                nv.setcCCD(rs.getString(12));
                listNV.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNV;
    }
    
    
    public List<NhanVienViewModel> getAllGioiTinh(String gt) {
        List<NhanVienViewModel> listNV = new ArrayList<>();
        String sql = """
                SELECT dbo.NhanVien.ID
                     , dbo.NhanVien.MaNhanVien
                     , dbo.NhanVien.TenNhanVien
                     , dbo.NhanVien.SoDienThoai
                     , dbo.NhanVien.Email
                     , dbo.NhanVien.DiaChi
                     , dbo.NhanVien.GioiTinh
                     , dbo.NhanVien.NgaySinh
                     , dbo.ChucVu.TenChucVu
                     , dbo.ChucVu.maChucVu
                     , dbo.NhanVien.Deleted
                      ,dbo.NhanVien.CCCD
                                       FROM     dbo.ChucVu INNER JOIN
                                                       dbo.NhanVien ON dbo.ChucVu.ID = dbo.NhanVien.idchucVu
   WHERE dbo.NhanVien.GioiTinh like ?
                                                                                        order by dbo.NhanVien.CreatedAt desc 
   """;

        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, gt);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVienViewModel nv = new NhanVienViewModel();
                nv.setIdNhanVien(rs.getString(1));
                nv.setMaNhanVien(rs.getString(2));
                nv.setTenNhanVien(rs.getString(3));
                nv.setsDt(rs.getString(4));
                nv.setEmail(rs.getString(5));
                nv.setDiaChi(rs.getString(6));
                nv.setGioiTinh(rs.getString(7));
                nv.setNgaySinh(rs.getDate(8));
                nv.setChucVu(rs.getString(9));
                nv.setMaChucVu(rs.getString(10));
                nv.setTrangThai(rs.getBoolean(11));
                nv.setcCCD(rs.getString(12));
                listNV.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNV;
    }
    

    public List<NhanVienViewModel> getAllCCCD(String cccd) {
        List<NhanVienViewModel> listNV = new ArrayList<>();
        String sql = """
                   						SELECT dbo.NhanVien.ID, dbo.NhanVien.MaNhanVien, dbo.NhanVien.TenNhanVien, dbo.NhanVien.SoDienThoai, dbo.NhanVien.Email, dbo.NhanVien.DiaChi, dbo.NhanVien.GioiTinh, dbo.NhanVien.NgaySinh, dbo.ChucVu.TenChucVu, 
                                                                       dbo.ChucVu.maChucVu,dbo.NhanVien.Deleted,dbo.NhanVien.CCCD
                                                       FROM     dbo.ChucVu INNER JOIN
                                                                       dbo.NhanVien ON dbo.ChucVu.ID = dbo.NhanVien.idchucVu
                   where dbo.NhanVien.CCCD like ?
                                      									order by dbo.NhanVien.CreatedAt desc 
                   """;

        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, cccd);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVienViewModel nv = new NhanVienViewModel();
                nv.setIdNhanVien(rs.getString(1));
                nv.setMaNhanVien(rs.getString(2));
                nv.setTenNhanVien(rs.getString(3));
                nv.setsDt(rs.getString(4));
                nv.setEmail(rs.getString(5));
                nv.setDiaChi(rs.getString(6));
                nv.setGioiTinh(rs.getString(7));
                nv.setNgaySinh(rs.getDate(8));
                nv.setChucVu(rs.getString(9));
                nv.setMaChucVu(rs.getString(10));
                nv.setTrangThai(rs.getBoolean(11));
                nv.setcCCD(rs.getString(12));
                listNV.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNV;
    }

    public List<NhanVienViewModel> search(String search) {
        List<NhanVienViewModel> listNV = new ArrayList<>();
        String sql = """
                 SELECT dbo.NhanVien.ID, dbo.NhanVien.MaNhanVien, dbo.NhanVien.TenNhanVien, dbo.NhanVien.SoDienThoai, dbo.NhanVien.Email, dbo.NhanVien.DiaChi, dbo.NhanVien.GioiTinh, dbo.NhanVien.NgaySinh, dbo.ChucVu.TenChucVu, 
                    dbo.ChucVu.maChucVu,dbo.NhanVien.Deleted
                     FROM     dbo.ChucVu INNER JOIN
                   dbo.NhanVien ON dbo.ChucVu.ID = dbo.NhanVien.idchucVu
                 WHERE (dbo.NhanVien.MaNhanVien like ? or dbo.NhanVien.TenNhanVien like ? or dbo.NhanVien.Email like ? or dbo.NhanVien.SoDienThoai like ? or dbo.NhanVien.DiaChi like ?)
                                                                                   									order by dbo.NhanVien.CreatedAt desc 
                   """;

        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, "%" + search + "%");
            ps.setObject(2, "%" + search + "%");
            ps.setObject(3, "%" + search + "%");
            ps.setObject(4, "%" + search + "%");
            ps.setObject(5, "%" + search + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVienViewModel nv = new NhanVienViewModel();
                nv.setIdNhanVien(rs.getString(1));
                nv.setMaNhanVien(rs.getString(2));
                nv.setTenNhanVien(rs.getString(3));
                nv.setsDt(rs.getString(4));
                nv.setEmail(rs.getString(5));
                nv.setDiaChi(rs.getString(6));
                nv.setGioiTinh(rs.getString(7));
                nv.setNgaySinh(rs.getDate(8));
                nv.setChucVu(rs.getString(9));

                nv.setMaChucVu(rs.getString(10));
                nv.setTrangThai(rs.getBoolean(11));
                listNV.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNV;
    }

    public boolean add(NhanVien nv) {
        int check = 0;
        String sql = """
                  INSERT INTO [dbo].[NhanVien]
                              (
                              [idchucVu]
                              ,[TenNhanVien]
                              ,[NgaySinh]
                              ,[DiaChi]
                              ,[SoDienThoai]
                              ,[Email]
                              ,[GioiTinh]
                              ,[MatKhau]
                              
                              ,[MaNhanVien],
                                CCCD
                              )
                        VALUES(?,?,?,?,?,?,?,?,?,?)
                   """;

        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, nv.getIdChucVu());
            ps.setObject(2, nv.getTenNhanVien());
            ps.setObject(3, nv.getNgaySinh());
            ps.setObject(4, nv.getDiaChi());
            ps.setObject(5, nv.getSoDienThoai());
            ps.setObject(6, nv.getEmail());
            ps.setObject(7, nv.getGioiTinh());
            ps.setObject(8, nv.getMatKhau());
            ps.setObject(9, nv.getMaNhanVien());
            ps.setObject(10, nv.getcCCD());
            check = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean delete(String id,int trangThai) {
        int check = 0;
        String sql = """
                   UPDATE [dbo].[NhanVien]
                      SET 
                         [Deleted] = ?
                         
                    
                         WHERE ID like ?
                   """;

        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
ps.setObject(1, trangThai);
            ps.setObject(2, id);

            check = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean update(NhanVien nv, String oldID) {
        int check = 0;
        String sql = """
                  UPDATE [dbo].[NhanVien]
                     SET 
                        [idchucVu] = ?
                        ,[TenNhanVien] = ?
                        ,[NgaySinh] = ?
                        ,[DiaChi] = ?
                        ,[SoDienThoai] = ?
                        ,[Email] = ?
                        ,[GioiTinh] = ?
                        ,
                        CCCD=?
                   WHERE ID like ?
                   """;

        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, nv.getIdChucVu());
            ps.setObject(2, nv.getTenNhanVien());
            ps.setObject(3, nv.getNgaySinh());
            ps.setObject(4, nv.getDiaChi());
            ps.setObject(5, nv.getSoDienThoai());
            ps.setObject(6, nv.getEmail());
            ps.setObject(7, nv.getGioiTinh());
            
            ps.setObject(8, nv.getcCCD());
            ps.setObject(9, oldID);
            check = ps.executeUpdate();

            

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    //Xuất excel
//Repository
//    public boolean xuatNhanVien() {
//        try ( Connection connection = DBConnect.getConnection()) {
//            String query = """
//                           SELECT [MaNhanVien]
//                                 ,[idchucVu]
//                                 ,[TenNhanVien]
//                                 ,[NgaySinh]
//                                 ,[DiaChi]
//                                 ,[SoDienThoai]
//                                 ,[Email]
//                                 ,[GioiTinh]
//                                 ,[MatKhau]
//                                 
//                                 
//                                 ,[CCCD]
//                             FROM [dbo].[NhanVien]
//                    
//                    """;
//
//            try ( PreparedStatement statement = connection.prepareStatement(query);  ResultSet resultSet = statement.executeQuery()) {
//                Workbook workbook = new XSSFWorkbook();
//                Sheet sheet = workbook.createSheet("Danh sách nhân viên");
//
//                // Tạo phông in đậm cho header
//                Font fontHeader = workbook.createFont();
//                fontHeader.setBold(true);
//                CellStyle styleHeader = workbook.createCellStyle();
//                styleHeader.setFont(fontHeader);
//
//                // Tạo phông in đậm cho dữ liệu
//                Font fontData = workbook.createFont();
//                CellStyle styleData = workbook.createCellStyle();
//                styleData.setFont(fontData);
//
//                ResultSetMetaData metaData = resultSet.getMetaData();
//                int columnCount = metaData.getColumnCount();
//                Row headerRow = sheet.createRow(0);
//
//                // Tạo header cho danh sách hóa đơn
//                for (int i = 1; i <= columnCount; i++) {
//                    String columnName = metaData.getColumnName(i);
//                    Cell cell = headerRow.createCell(i - 1);
//                    cell.setCellValue(columnName);
//                    cell.setCellStyle(styleHeader);
//                }
//
//                int rowIndex = 1;
//                while (resultSet.next()) {
//                    Row row = sheet.createRow(rowIndex++);
//
//                    // Tạo hyperlink cho ID
//                   
//
//                    // Điền dữ liệu vào các cột còn lại
//                    for (int i = 2; i <= columnCount; i++) {
//                        Cell dataCell = row.createCell(i - 1);
//                        dataCell.setCellValue(resultSet.getString(i));
//                        dataCell.setCellStyle(styleData); // Sử dụng phông in đậm cho dữ liệu
//                    }
//
//                    // Tiếp tục xử lý các chi tiết hóa đơn...
//                    row.createCell(0).setCellValue(resultSet.getString("MaNhanVien"));
//                    row.createCell(1).setCellValue(resultSet.getString("idChucVu"));
//                    row.createCell(2).setCellValue(resultSet.getString("TenNhanVien"));
//                    row.createCell(3).setCellValue(resultSet.getTimestamp("NgaySinh").toString());
//                    row.createCell(4).setCellValue(resultSet.getString("DiaChi").toString());
//                    row.createCell(5).setCellValue(resultSet.getString("SoDienThoai"));
//                    row.createCell(6).setCellValue(resultSet.getString("Email"));
//
////                    row.createCell(5).setCellValue(resultSet.getString("TenKieuThanhToan"));
//                    String gioiTinh="";
//                    if("1".equals(resultSet.getString("GioiTinh"))){
//                        gioiTinh="Nam";
//                    
//                    }else if("0".equals(resultSet.getString("GioiTinh"))){
//                        gioiTinh="Nữ";
//                    }
//                    
//                    row.createCell(7).setCellValue(gioiTinh);
//                    row.createCell(8).setCellValue(resultSet.getString("MatKhau"));
//                    row.createCell(9).setCellValue(resultSet.getString("CCCD"));
//
//                    // Lấy ID hóa đơn để lấy thông tin chi tiết hóa đơn
////                    String idNhanVien = resultSet.getString("ID");
////                    ChucVuRepository repo = new ChucVuRepository();
////                    List<HoaDonChiTietViewModel> hoaDonChiTietList = repo.getAll(idHoaDon);
////
////                    // Tạo sheet mới cho chi tiết hóa đơn
////                    Sheet chiTietSheet = workbook.createSheet(idHoaDon);
////                    Row headerChiTietRow = chiTietSheet.createRow(0);
////                    String[] chiTietHeaders = {"ID hóa đơn", "Tên sản phẩm", "Số lượng", "Giá bán", "Tổng tiền"};
////                    for (int i = 0; i < chiTietHeaders.length; i++) {
////                        Cell chiTietCell = headerChiTietRow.createCell(i);
////                        chiTietCell.setCellValue(chiTietHeaders[i]);
////                        chiTietCell.setCellStyle(styleData);
////                    }
//                    // Đổ dữ liệu chi tiết hóa đơn vào sheet mới
////                    int chiTietRowIndex = 1;
////                    for (HoaDonChiTietViewModel hoaDonChiTiet : hoaDonChiTietList ) {
////                        
////                        Row chiTietRow = chiTietSheet.createRow(chiTietRowIndex++);
////                        chiTietRow.createCell(0).setCellValue(hoaDonChiTiet.getIdHoaDon());
////                        chiTietRow.createCell(1).setCellValue(hoaDonChiTiet.getTenSP());
////                        chiTietRow.createCell(2).setCellValue(hoaDonChiTiet.getSoLuong());
////                        chiTietRow.createCell(3).setCellValue(hoaDonChiTiet.getDonGia());
//////                        chiTietRow.createCell(4).setCellValue(hoaDonChiTiet.getGiamGia());
////                        chiTietRow.createCell(4).setCellValue(hoaDonChiTiet.getDonGia()*hoaDonChiTiet.getSoLuong());
////
////                    }
//                }
//
//                String fileName = ".\\excelNV\\NV-EXCEL_" + System.currentTimeMillis() + ".xlsx";
//                try ( FileOutputStream fileOut = new FileOutputStream(fileName)) {
//                    workbook.write(fileOut);
//                }
//                System.out.println("Đã xuất file Excel: " + fileName);
//                return true;
//            }
//        } catch (SQLException | IOException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
}
