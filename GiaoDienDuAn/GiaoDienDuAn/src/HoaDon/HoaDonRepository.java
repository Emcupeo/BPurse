/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HoaDon;

import HoaDon.HoaDonViewModel;
import HoaDonChiTiet.HoaDonChiTietRepository;
import HoaDonChiTiet.HoaDonChiTietViewModel;

import bpurse.qlts.entity.ChiTietSanPham;
import bpurse.qlts.repo.DBConnect;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.zxing.BarcodeFormat;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;

import com.google.zxing.Result;
import com.google.zxing.WriterException;
//import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;

import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import java.sql.ResultSetMetaData;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.Hyperlink;

/**
 *
 * @author My PC
 */
public class HoaDonRepository {

    public List<HoaDonViewModel> getAll() {
        List<HoaDonViewModel> ListKH = new ArrayList<>();

        String sql = """
          
                     SELECT dbo.HoaDon.ID,
                     dbo.HoaDon.CreatedAt,
                     dbo.HoaDon.NgayThanhToan,
                     SUM(HoaDonChiTiet.SoLuong* HoaDonChiTiet.DonGia) as 'Tong tien', 
                     dbo.KhachHang.TenKhachHang,
                     dbo.KhachHang.DiaChi,
                     dbo.KhachHang.SoDienThoai,
                     dbo.HoaDon.TrangThai,
                     dbo.HoaDon.MaHoaDon,
                     dbo.NhanVien.MaNhanVien
                     FROM     dbo.HoaDon INNER JOIN
                                       dbo.HoaDonChiTiet ON dbo.HoaDon.ID = dbo.HoaDonChiTiet.idHoaDon Inner join
                                       dbo.KhachHang ON dbo.HoaDon.idKhachHang = dbo.KhachHang.ID  Inner join
                                       dbo.NhanVien ON dbo.HoaDon.idNhanVien = dbo.NhanVien.ID
                     where HoaDon.Deleted = 'True'
                                        				  group by dbo.HoaDon.ID, dbo.HoaDon.CreatedAt, dbo.HoaDon.NgayThanhToan, dbo.KhachHang.TenKhachHang,dbo.HoaDon.TrangThai,
                                                                     HoaDon.NgayThanhToan, dbo.KhachHang.DiaChi,    dbo.KhachHang.SoDienThoai, dbo.HoaDon.MaHoaDon, dbo.NhanVien.MaNhanVien  
                   				order by HoaDon.NgayThanhToan desc
                     """;
        try ( Connection con = new DBConnect().getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonViewModel kh = new HoaDonViewModel();
                kh.setMaHoaDon(rs.getString(1));
                kh.setNgayTao(rs.getDate(2));
                kh.setNgayThanhToan(rs.getDate(3));
                kh.setThanhTien(rs.getFloat(4));
                kh.setTenKhachHang(rs.getString(5));
                kh.setDiaChi(rs.getString(6));
                kh.setSdt(rs.getString(7));
                kh.setTrangThai(rs.getString(8));
                kh.setID(rs.getString(9));
                kh.setIdNV(rs.getString(10));
                ListKH.add(kh);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ListKH;
    }

    public List<HoaDonViewModel> searchGiaTien(float min, float max) {
        List<HoaDonViewModel> listHD = new ArrayList<>();
        String sql = """                    
                    SELECT dbo.HoaDon.ID,
                    dbo.HoaDon.CreatedAt,
                    dbo.HoaDon.NgayThanhToan,
                    SUM(dbo.HoaDonChiTiet.SoLuong * dbo.HoaDonChiTiet.DonGia),
                    dbo.KhachHang.TenKhachHang,
                    dbo.KhachHang.DiaChi,
                    dbo.KhachHang.SoDienThoai,
                    dbo.HoaDon.TrangThai,
                    dbo.HoaDon.MaHoaDon,
                    dbo.NhanVien.MaNhanVien
                    FROM  dbo.HoaDon 
                                   Inner join
                                   dbo.HoaDonChiTiet ON dbo.HoaDon.ID = dbo.HoaDonChiTiet.idHoaDon Inner join
                                   dbo.KhachHang ON dbo.HoaDon.idKhachHang = dbo.KhachHang.ID  Inner join
                                   dbo.NhanVien ON dbo.HoaDon.idNhanVien = dbo.NhanVien.ID

                       where HoaDon.Deleted = 'True'


                    group by dbo.HoaDon.ID, dbo.HoaDon.CreatedAt, dbo.HoaDon.NgayThanhToan, dbo.KhachHang.TenKhachHang,dbo.HoaDon.TrangThai,
                    HoaDon.NgayThanhToan, dbo.KhachHang.DiaChi,    dbo.KhachHang.SoDienThoai, dbo.HoaDon.MaHoaDon, dbo.NhanVien.MaNhanVien  

                       HAVING 
                       SUM(dbo.HoaDonChiTiet.SoLuong * dbo.HoaDonChiTiet.DonGia) BETWEEN ? AND ?
                                                                                       order by HoaDon.NgayThanhToan desc
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareCall(sql)) {
            ps.setObject(1, min);
            ps.setObject(2, max);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonViewModel hd = new HoaDonViewModel();
                HoaDonViewModel kh = new HoaDonViewModel();
                kh.setMaHoaDon(rs.getString(1));
                kh.setNgayTao(rs.getDate(2));
                kh.setNgayThanhToan(rs.getDate(3));
                kh.setThanhTien(rs.getFloat(4));
                kh.setTenKhachHang(rs.getString(5));
                kh.setDiaChi(rs.getString(6));
                kh.setSdt(rs.getString(7));
                kh.setTrangThai(rs.getString(8));
                kh.setID(rs.getString(9));
                kh.setIdNV(rs.getString(10));
                listHD.add(kh);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHD;
    }

    public List<HoaDonViewModel> search(String timKiem) {
        List<HoaDonViewModel> listHD = new ArrayList<>();
        String sql = """                    
                     SELECT dbo.HoaDon.ID,
                    dbo.HoaDon.CreatedAt,
                    dbo.HoaDon.NgayThanhToan,
                    SUM(HoaDonChiTiet.SoLuong* HoaDonChiTiet.DonGia) as 'Tong tien', 
                    dbo.KhachHang.TenKhachHang,
                    dbo.KhachHang.DiaChi,
                    dbo.KhachHang.SoDienThoai,
                    dbo.HoaDon.TrangThai,
                     dbo.HoaDon.MaHoaDon,
                     dbo.NhanVien.MaNhanVien
                         FROM  dbo.HoaDon
                                    Inner join
                   dbo.HoaDonChiTiet ON dbo.HoaDon.ID = dbo.HoaDonChiTiet.idHoaDon Inner join
                   dbo.KhachHang ON dbo.HoaDon.idKhachHang = dbo.KhachHang.ID  Inner join
                   dbo.NhanVien ON dbo.HoaDon.idNhanVien = dbo.NhanVien.ID
                            where HoaDon.Deleted = 'True' 

                      group by dbo.HoaDon.ID, dbo.HoaDon.CreatedAt, dbo.HoaDon.NgayThanhToan, dbo.KhachHang.TenKhachHang,dbo.HoaDon.TrangThai,
                      HoaDon.NgayThanhToan, dbo.KhachHang.DiaChi,    dbo.KhachHang.SoDienThoai, dbo.HoaDon.MaHoaDon, dbo.NhanVien.MaNhanVien  
                      Having SUM(HoaDonChiTiet.SoLuong* HoaDonChiTiet.DonGia) like ?  or
                     dbo.NhanVien.MaNhanVien like ? or
                        dbo.KhachHang.TenKhachHang like ? or
                        dbo.KhachHang.DiaChi like ? or
                        dbo.KhachHang.SoDienThoai like ? or
                        dbo.HoaDon.TrangThai like ? or 
                         dbo.HoaDon.MaHoaDon like ? 
                       order by HoaDon.NgayThanhToan desc

                 """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareCall(sql)) {
            ps.setObject(1, '%' + timKiem + '%');
            ps.setObject(2, '%' + timKiem + '%');
            ps.setObject(3, '%' + timKiem + '%');
            ps.setObject(4, '%' + timKiem + '%');
            ps.setObject(5, '%' + timKiem + '%');
            ps.setObject(6, '%' + timKiem + '%');
            ps.setObject(7, '%' + timKiem + '%');
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                HoaDonViewModel kh = new HoaDonViewModel();
                kh.setMaHoaDon(rs.getString(1));
                kh.setNgayTao(rs.getDate(2));
                kh.setNgayThanhToan(rs.getDate(3));
                kh.setThanhTien(rs.getFloat(4));
                kh.setTenKhachHang(rs.getString(5));
                kh.setDiaChi(rs.getString(6));
                kh.setSdt(rs.getString(7));
                kh.setTrangThai(rs.getString(8));
                kh.setID(rs.getString(9));
                kh.setIdNV(rs.getString(10));
                listHD.add(kh);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHD;
    }

    public List<HoaDonViewModel> trangThai(String trangThai) {
        List<HoaDonViewModel> ListKH = new ArrayList<>();

        String sql = """
          
                     SELECT dbo.HoaDon.ID,
                     dbo.HoaDon.CreatedAt,
                     dbo.HoaDon.NgayThanhToan,
                     SUM(HoaDonChiTiet.SoLuong* HoaDonChiTiet.DonGia) as 'Tong tien', 
                     dbo.KhachHang.TenKhachHang,
                     dbo.KhachHang.DiaChi,
                     dbo.KhachHang.SoDienThoai,
                     dbo.HoaDon.TrangThai,
                     dbo.HoaDon.MaHoaDon,
                     dbo.NhanVien.MaNhanVien
                     FROM     dbo.HoaDon INNER JOIN
                                    dbo.HoaDonChiTiet ON dbo.HoaDon.ID = dbo.HoaDonChiTiet.idHoaDon Inner join
                                    dbo.KhachHang ON dbo.HoaDon.idKhachHang = dbo.KhachHang.ID  Inner join
                                    dbo.NhanVien ON dbo.HoaDon.idNhanVien = dbo.NhanVien.ID
                    where HoaDon.Deleted = 'True' and HoaDon.TrangThai like ?
                                                                     				  group by dbo.HoaDon.ID, dbo.HoaDon.CreatedAt, dbo.HoaDon.NgayThanhToan, dbo.KhachHang.TenKhachHang,dbo.HoaDon.TrangThai,
                                                                                                  HoaDon.NgayThanhToan, dbo.KhachHang.DiaChi,    dbo.KhachHang.SoDienThoai, dbo.HoaDon.MaHoaDon, dbo.NhanVien.MaNhanVien  
                                                				order by HoaDon.NgayThanhToan desc       
                   """;
        try ( Connection con = new DBConnect().getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, trangThai);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonViewModel kh = new HoaDonViewModel();
                kh.setMaHoaDon(rs.getString(1));
                kh.setNgayTao(rs.getDate(2));
                kh.setNgayThanhToan(rs.getDate(3));
                kh.setThanhTien(rs.getFloat(4));
                kh.setTenKhachHang(rs.getString(5));
                kh.setDiaChi(rs.getString(6));
                kh.setSdt(rs.getString(7));
                kh.setTrangThai(rs.getString(8));
                kh.setID(rs.getString(9));
                kh.setIdNV(rs.getString(10));
                ListKH.add(kh);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ListKH;
    }

    public boolean xuatHoaDon() {

        try ( Connection connection = DBConnect.getConnection()) {
            String query = """
                       SELECT [MaHoaDon]
                                                            ,[idNhanVien]
                                                            ,KhachHang.[TenKhachHang] as 'TenKhachHang'
                                                            ,[NgayTao]
                                                            ,HoaDon.[NgayThanhToan] as 'NgayThanhToan'
                                                            ,KhachHang.[SoDienThoai] as 'SoDienThoai'
                                                            ,KhachHang.[Diachi] as 'DiaChiKhachHang'
                                                            ,SUM(HoaDonChiTiet.SoLuong* HoaDonChiTiet.DonGia) as 'TongTien'
                                                            ,[TrangThai]
                                                            ,dbo.HoaDon.[ID]
                                                        FROM [BPURSE_SOF102_SD1702].[dbo].[HoaDon]
                           							 Inner join HoaDonChiTiet on HoaDon.ID = HoaDonChiTiet.idHoaDon
                           							 Inner join KhachHang on HoaDon.idKhachHang =KhachHang.ID
                           							 where dbo.HoaDon.Deleted = 1
                           							 group by [MaHoaDon]
                                                            ,[idNhanVien]
                                                            ,KhachHang.[TenKhachHang] 
                                                            ,[NgayTao]
                                                            ,HoaDon.[NgayThanhToan]
                                                            ,KhachHang.[SoDienThoai] 
                                                            ,KhachHang.[Diachi]
                                                             
                                                            ,[TrangThai]
                                                            ,dbo.HoaDon.[ID]
                    
                    """;

            try ( PreparedStatement statement = connection.prepareStatement(query);  ResultSet resultSet = statement.executeQuery()) {
                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("Danh sách hóa đơn");

                // Tạo phông in đậm cho header
                Font fontHeader = workbook.createFont();
                fontHeader.setBold(true);
                CellStyle styleHeader = workbook.createCellStyle();
                styleHeader.setFont(fontHeader);

                // Tạo phông in đậm cho dữ liệu
                Font fontData = workbook.createFont();
                CellStyle styleData = workbook.createCellStyle();
                styleData.setFont(fontData);

                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                Row headerRow = sheet.createRow(0);

                // Tạo header cho danh sách hóa đơn
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Cell cell = headerRow.createCell(i - 1);
                    cell.setCellValue(columnName);
                    cell.setCellStyle(styleHeader);
                }

                int rowIndex = 1;
                while (resultSet.next()) {
                    Row row = sheet.createRow(rowIndex++);

                    // Tạo hyperlink cho ID
                    CreationHelper createHelper = workbook.getCreationHelper();
                    Hyperlink hyperlink = createHelper.createHyperlink(HyperlinkType.DOCUMENT);
                    hyperlink.setAddress("'" + resultSet.getString("MaHoaDon") + "'!A1");
                    Cell idCell = row.createCell(0);
                    idCell.setCellValue(resultSet.getString("MaHoaDon"));
                    idCell.setHyperlink(hyperlink);
                    idCell.setCellStyle(styleData); // Sử dụng phông in đậm cho dữ liệu

                    // Điền dữ liệu vào các cột còn lại
                    for (int i = 2; i <= columnCount; i++) {
                        Cell dataCell = row.createCell(i - 1);
                        dataCell.setCellValue(resultSet.getString(i));
                        dataCell.setCellStyle(styleData); // Sử dụng phông in đậm cho dữ liệu
                    }

                    // Tiếp tục xử lý các chi tiết hóa đơn...
                    row.createCell(1).setCellValue(resultSet.getString("IDNhanVien"));
                    row.createCell(2).setCellValue(resultSet.getString("TenKhachHang"));
                    row.createCell(3).setCellValue(resultSet.getTimestamp("NgayTao").toString());
                    row.createCell(4).setCellValue(resultSet.getTimestamp("NgayThanhToan").toString());
                    row.createCell(5).setCellValue(resultSet.getString("SoDienThoai"));
                    row.createCell(6).setCellValue(resultSet.getString("DiachiKhachHang"));

//                    row.createCell(5).setCellValue(resultSet.getString("TenKieuThanhToan"));
                    row.createCell(7).setCellValue(resultSet.getString("TongTien"));

                    // Lấy ID hóa đơn để lấy thông tin chi tiết hóa đơn
                    String idHoaDon = resultSet.getString("ID");
                    String maHoaDon = resultSet.getString("MaHoaDon");
                    HoaDonChiTietRepository repo = new HoaDonChiTietRepository();
                    List<HoaDonChiTietViewModel> hoaDonChiTietList = repo.getAll(idHoaDon);

                    // Tạo sheet mới cho chi tiết hóa đơn
                    Sheet chiTietSheet = workbook.createSheet(maHoaDon);
                    Row headerChiTietRow = chiTietSheet.createRow(0);
                    String[] chiTietHeaders = {"Mã hoá đơn chi tiết", "Tên sản phẩm", "Chất liệu", "Màu sắc", "Phụ kiện", "Thương hiệu", "Số lượng", "Giá bán", "Tổng tiền"};
                    for (int i = 0; i < chiTietHeaders.length; i++) {
                        Cell chiTietCell = headerChiTietRow.createCell(i);
                        chiTietCell.setCellValue(chiTietHeaders[i]);
                        chiTietCell.setCellStyle(styleData);
                    }

                    // Đổ dữ liệu chi tiết hóa đơn vào sheet mới
                    int chiTietRowIndex = 1;
                    for (HoaDonChiTietViewModel hoaDonChiTiet : hoaDonChiTietList) {

                        Row chiTietRow = chiTietSheet.createRow(chiTietRowIndex++);
                        chiTietRow.createCell(0).setCellValue(hoaDonChiTiet.getMaHDCT());
                        chiTietRow.createCell(1).setCellValue(hoaDonChiTiet.getTenSP());
                        chiTietRow.createCell(2).setCellValue(hoaDonChiTiet.getTenCL());
                        chiTietRow.createCell(3).setCellValue(hoaDonChiTiet.getTenMS());
                        chiTietRow.createCell(4).setCellValue(hoaDonChiTiet.getTenPK());
                        chiTietRow.createCell(5).setCellValue(hoaDonChiTiet.getTenTH());
                        chiTietRow.createCell(6).setCellValue(hoaDonChiTiet.getSoLuong());
                        chiTietRow.createCell(7).setCellValue(hoaDonChiTiet.getDonGia());
//                        chiTietRow.createCell(4).setCellValue(hoaDonChiTiet.getGiamGia());
                        chiTietRow.createCell(8).setCellValue(hoaDonChiTiet.getDonGia() * hoaDonChiTiet.getSoLuong());

                    }
                }

                String fileName = ".\\excel\\HD-Excel" + System.currentTimeMillis() + ".xlsx";
                try ( FileOutputStream fileOut = new FileOutputStream(fileName)) {
                    workbook.write(fileOut);
                }
                System.out.println("Đã xuất file Excel: " + fileName);
                return true;
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<HoaDonViewModel> getIDHD(String idHD) {
        List<HoaDonViewModel> ListKH = new ArrayList<>();

        String sql = """
          
                     SELECT dbo.HoaDon.ID,
                                          dbo.HoaDon.CreatedAt,
                                          dbo.HoaDon.NgayThanhToan,
                                          SUM(HoaDonChiTiet.SoLuong* HoaDonChiTiet.DonGia) as 'Tong tien', 
                                          dbo.KhachHang.TenKhachHang,
                                          dbo.KhachHang.DiaChi,
                                          dbo.KhachHang.SoDienThoai,
                                          dbo.HoaDon.TrangThai,
                                          dbo.HoaDon.MaHoaDon,
                                          dbo.NhanVien.MaNhanVien
                                          FROM     dbo.HoaDon INNER JOIN
                                                            dbo.HoaDonChiTiet ON dbo.HoaDon.ID = dbo.HoaDonChiTiet.idHoaDon Inner join
                                                            dbo.KhachHang ON dbo.HoaDon.idKhachHang = dbo.KhachHang.ID Inner join
                     									   dbo.NhanVien on dbo.HoaDon.idNhanVien = dbo.NhanVien.ID
                                        where HoaDon.Deleted = 'True' and HoaDon.ID = ?
                                                                                				  group by dbo.HoaDon.ID, dbo.HoaDon.CreatedAt, dbo.HoaDon.NgayThanhToan, dbo.KhachHang.TenKhachHang,dbo.HoaDon.TrangThai,
                                                                                                             HoaDon.NgayThanhToan, dbo.KhachHang.DiaChi,    dbo.KhachHang.SoDienThoai, dbo.HoaDon.MaHoaDon, dbo.NhanVien.MaNhanVien  
                                                           				order by HoaDon.NgayThanhToan desc    """;
        try ( Connection con = new DBConnect().getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, idHD);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonViewModel kh = new HoaDonViewModel();
                kh.setMaHoaDon(rs.getString(1));
                kh.setNgayTao(rs.getDate(2));
                kh.setNgayThanhToan(rs.getDate(3));
                kh.setThanhTien(rs.getFloat(4));
                kh.setTenKhachHang(rs.getString(5));
                kh.setDiaChi(rs.getString(6));
                kh.setSdt(rs.getString(7));
                kh.setTrangThai(rs.getString(8));
                kh.setID(rs.getString(9));
                kh.setIdNV(rs.getString(10));
                ListKH.add(kh);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ListKH;
    }
}
