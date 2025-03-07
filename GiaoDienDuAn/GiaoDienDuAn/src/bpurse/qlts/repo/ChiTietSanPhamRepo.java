/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bpurse.qlts.repo;

import bpurse.qlts.entity.ChiTietSanPham;
import bpurse.qlts.viewmodel.CTSPViewmodel;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.apache.poi.common.usermodel.HyperlinkType;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ASUS
 */
public class ChiTietSanPhamRepo {

    public List<ChiTietSanPham> getID() {
        List<ChiTietSanPham> lists = new ArrayList<>();
        String sql = """    
                    select SanPham.ID from SanPham
                    inner join ChiTietSanPham on SanPham.ID = ChiTietSanPham.MaSanPham
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham ctsp = new ChiTietSanPham();
                ctsp.setmaSP(rs.getString(1));

                lists.add(ctsp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }

    public List<ChiTietSanPham> getMaCTSP() {
        List<ChiTietSanPham> lists = new ArrayList<>();
        String sql = """    
                    select ID from ChiTietSanPham
                         where idChatLieu is null                
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham ctsp = new ChiTietSanPham();
                ctsp.setID(rs.getString(1));

                lists.add(ctsp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }

    public boolean addCTSP(ChiTietSanPham sv, String oldMa) {
        int check = 0;
        List<ChiTietSanPham> lists = new ArrayList<>();
        String sql = """    
                    UPDATE [dbo].[ChiTietSanPham]
                       SET [idNhaSanXuat] = (Select top 1 [ID] from [dbo].[NhaSanXuat] where [TenNhaSanXuat] = ?)
                          ,[idChatLieu] = (Select top 1 [ID] from [dbo].[ChatLieu] where [TenChatLieu] = ?)
                          ,[idMauSac] = (Select top 1 [ID] from [dbo].[MauSac] where [TenMauSac] = ?)
                          ,[idPhuKien] = (Select top 1 [ID] from [dbo].[PhuKien] where [LoaiPhuKien] = ?)
                          ,[idLoaiQuai] = (Select top 1 [ID] from [dbo].[LoaiQuai] where [TenLoaiQuai] = ?)
                          ,[idThuongHieu] = (Select top 1 [ID] from [dbo].[ThuongHieu] where [ThuongHieu] = ?)
                          ,[idPhanLoai] = (Select top 1 [ID] from [dbo].[PhanLoai] where [PhanLoai] = ?)
                          ,[idKichCo] =  (Select top 1 [ID] from [dbo].[KichCo] where [TenKichCo] = ?)
                          ,[idTinhTrang] = (Select top 1 [ID] from [dbo].[TinhTrang] where [TinhTrang] = ?)
                          ,[SoLuong] = ?
                          ,[MoTa] = ?
                          ,[GiaNhap] = ?
                          ,[GiaBan] = ?
                          ,[CreatedAt] = getDate()
                     
                     WHERE ID = (Select top (1) [dbo].[ChiTietSanPham].[ID] from [dbo].[ChiTietSanPham] Inner join 
                     SanPham on ChiTietSanPham.MaSanPham = SanPham.ID
                     where dbo.SanPham.TenSanPham = ?)
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, sv.getIDNhaSanXuat());
            ps.setObject(2, sv.getIDChatLieu());
            ps.setObject(3, sv.getIDMauSac());
            ps.setObject(4, sv.getIDPhuKien());
            ps.setObject(5, sv.getIDLoaiQuai());
            ps.setObject(6, sv.getIDThuongHieu());
            ps.setObject(7, sv.getIDPhanLoai());
            ps.setObject(8, sv.getIDKichCo());
            ps.setObject(9, sv.getIDTinhTrang());

            ps.setObject(10, sv.getSoLuong());
            ps.setObject(11, sv.getMoTa());
            ps.setObject(12, sv.getGiaNhap());
            ps.setObject(13, sv.getGiaBan());
            ps.setObject(14, oldMa);
            check = ps.executeUpdate();
            lists.add(sv);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    //Xuất excel
//Repository


    public boolean updateCTSP(ChiTietSanPham sv, String oldMa) {
        int check = 0;
        List<ChiTietSanPham> lists = new ArrayList<>();
        String sql = """    
                    UPDATE [dbo].[ChiTietSanPham]
                       SET [idNhaSanXuat] = (Select top 1 [ID] from [dbo].[NhaSanXuat] where [TenNhaSanXuat] = ?)
                          ,[idChatLieu] = (Select top 1 [ID] from [dbo].[ChatLieu] where [TenChatLieu] = ?)
                          ,[idMauSac] = (Select top 1 [ID] from [dbo].[MauSac] where [TenMauSac] = ?)
                          ,[idPhuKien] = (Select top 1 [ID] from [dbo].[PhuKien] where [LoaiPhuKien] = ?)
                          ,[idLoaiQuai] = (Select top 1 [ID] from [dbo].[LoaiQuai] where [TenLoaiQuai] = ?)
                          ,[idThuongHieu] = (Select top 1 [ID] from [dbo].[ThuongHieu] where [ThuongHieu] = ?)
                          ,[idPhanLoai] = (Select top 1 [ID] from [dbo].[PhanLoai] where [PhanLoai] = ?)
                          ,[idKichCo] =  (Select top 1 [ID] from [dbo].[KichCo] where [TenKichCo] = ?)
                          ,[idTinhTrang] = (Select top 1 [ID] from [dbo].[TinhTrang] where [TinhTrang] = ?)
                          ,[SoLuong] = ?
                          ,[MoTa] = ?
                          ,[GiaNhap] = ?
                          ,[GiaBan] = ?
                          ,[UpdatedAt] = getDate()
                    
                     WHERE [dbo].[ChiTietSanPham].[ID]  = ?
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, sv.getIDNhaSanXuat());
            ps.setObject(2, sv.getIDChatLieu());
            ps.setObject(3, sv.getIDMauSac());
            ps.setObject(4, sv.getIDPhuKien());
            ps.setObject(5, sv.getIDLoaiQuai());
            ps.setObject(6, sv.getIDThuongHieu());
            ps.setObject(7, sv.getIDPhanLoai());
            ps.setObject(8, sv.getIDKichCo());
            ps.setObject(9, sv.getIDTinhTrang());

            ps.setObject(10, sv.getSoLuong());
            ps.setObject(11, sv.getMoTa());
            ps.setObject(12, sv.getGiaNhap());
            ps.setObject(13, sv.getGiaBan());
            ps.setObject(14, oldMa);
            check = ps.executeUpdate();
            lists.add(sv);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean xoaCTSP(String ma) {
        int check = 0;

        String sql = """    
                    UPDATE [dbo].[ChiTietSanPham]
                       SET [Deleted] = 0

                    
                     WHERE [dbo].[ChiTietSanPham].[ID]  = ?
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, ma);
            check = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
    
    public boolean xuatHoaDon() {
        try ( Connection connection = DBConnect.getConnection()) {
            String query = """
                    SELECT        
                        dbo.ChiTietSanPham.ID, 
                        dbo.SanPham.MaSanPham,  
                        dbo.ChiTietSanPham.MaCTSP, 
                        dbo.ChiTietSanPham.MoTa, 
                            dbo.ChiTietSanPham.SoLuong, 
                            dbo.ChiTietSanPham.GiaNhap, 
                            dbo.ChiTietSanPham.GiaBan, 
                            dbo.ChatLieu.TenChatLieu,
                            dbo.MauSac.TenMauSac, 
                            dbo.PhuKien.LoaiPhuKien, 
                            dbo.LoaiQuai.TenLoaiQuai, 
                            dbo.ThuongHieu.ThuongHieu, 
                            dbo.PhanLoai.PhanLoai,
                            dbo.KichCo.TenKichCo,
                            dbo.TinhTrang.TinhTrang,
                            dbo.ChiTietSanPham.trangThai,
                            dbo.SanPham.TenSanPham,
                            dbo.NhaSanXuat.TenNhaSanXuat
                           FROM            
                        dbo.ChiTietSanPham INNER JOIN
                        
                        dbo.ChatLieu ON dbo.ChiTietSanPham.idChatLieu = dbo.ChatLieu.ID INNER JOIN
                        dbo.MauSac ON dbo.ChiTietSanPham.idMauSac = dbo.MauSac.ID INNER JOIN
                        dbo.PhuKien ON dbo.ChiTietSanPham.idPhuKien = dbo.PhuKien.ID INNER JOIN
                        dbo.LoaiQuai ON dbo.ChiTietSanPham.idLoaiQuai = dbo.LoaiQuai.ID INNER JOIN
                        dbo.ThuongHieu ON dbo.ChiTietSanPham.idThuongHieu = dbo.ThuongHieu.ID INNER JOIN
                        dbo.PhanLoai ON dbo.ChiTietSanPham.idPhanLoai = dbo.PhanLoai.ID INNER JOIN
                        dbo.KichCo ON dbo.ChiTietSanPham.idKichCo = dbo.KichCo.ID INNER JOIN
                        dbo.SanPham ON dbo.ChiTietSanPham.MaSanPham = dbo.SanPham.ID INNER JOIN
                        dbo.TinhTrang ON dbo.ChiTietSanPham.idTinhTrang = dbo.TinhTrang.ID INNER JOIN
                        dbo.NhaSanXuat ON dbo.ChiTietSanPham.idNhaSanXuat = dbo.NhaSanXuat.ID
                        where ChiTietSanPham.Deleted = 1			
                        GROUP BY dbo.ChiTietSanPham.ID, dbo.ChiTietSanPham.MaSanPham, dbo.ChiTietSanPham.MaCTSP, dbo.SanPham.TenSanPham, dbo.ChiTietSanPham.MoTa, dbo.ChiTietSanPham.SoLuong, dbo.ChiTietSanPham.GiaNhap, dbo.ChiTietSanPham.GiaBan, dbo.ChiTietSanPham.CreatedAt
                             ,dbo.ChatLieu.TenChatLieu, dbo.MauSac.TenMauSac, dbo.PhuKien.LoaiPhuKien,
                            dbo.LoaiQuai.TenLoaiQuai, dbo.ThuongHieu.ThuongHieu, dbo.PhanLoai.PhanLoai,
                            dbo.KichCo.TenKichCo,dbo.TinhTrang.TinhTrang,dbo.ChiTietSanPham.trangThai,
                           dbo.NhaSanXuat.TenNhaSanXuat,dbo.SanPham.MaSanPham

                            order by dbo.ChiTietSanPham.CreatedAt desc              
                    
                    """;

            try ( PreparedStatement statement = connection.prepareStatement(query);  ResultSet resultSet = statement.executeQuery()) {
                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("Danh sách CTSP");

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
                    hyperlink.setAddress("'" + resultSet.getString("MaSanPham") + "'!A1");
                    Cell idCell = row.createCell(1);
                    idCell.setCellValue(resultSet.getString("MaSanPham"));
                    idCell.setHyperlink(hyperlink);
                    idCell.setCellStyle(styleData); // Sử dụng phông in đậm cho dữ liệu

                    // Điền dữ liệu vào các cột còn lại
                    for (int i = 2; i <= columnCount; i++) {
                        Cell dataCell = row.createCell(i - 1);
                        dataCell.setCellValue(resultSet.getString(i));
                        dataCell.setCellStyle(styleData); // Sử dụng phông in đậm cho dữ liệu
                    }

                    // Tiếp tục xử lý các chi tiết hóa đơn...
                    row.createCell(2).setCellValue(resultSet.getString("MaCTSP"));
                    row.createCell(3).setCellValue(resultSet.getString("MoTa"));
                    row.createCell(4).setCellValue(resultSet.getString("SoLuong"));
                    row.createCell(5).setCellValue(resultSet.getString("GiaNhap"));
                    row.createCell(6).setCellValue(resultSet.getString("GiaBan"));
                    row.createCell(7).setCellValue(resultSet.getString("TenChatLieu"));
                    row.createCell(8).setCellValue(resultSet.getString("TenMauSac"));
                    row.createCell(9).setCellValue(resultSet.getString("LoaiPhuKien"));
                    row.createCell(10).setCellValue(resultSet.getString("TenLoaiQuai"));
                    row.createCell(11).setCellValue(resultSet.getString("ThuongHieu"));
                    row.createCell(12).setCellValue(resultSet.getString("PhanLoai"));
                    row.createCell(13).setCellValue(resultSet.getString("TenKichCo"));
                    row.createCell(14).setCellValue(resultSet.getString("TinhTrang"));
                    row.createCell(15).setCellValue(resultSet.getString("trangThai"));
                    row.createCell(16).setCellValue(resultSet.getString("TenNhaSanXuat"));
//                    row.createCell(17).setCellValue(resultSet.getString("TenNhaSanXuat"));

////                    row.createCell(5).setCellValue(resultSet.getString("TenKieuThanhToan"));
//                    row.createCell(7).setCellValue(resultSet.getString("TongTien"));
                    // Lấy ID sp để lấy thông tin chi tiết sp
               
                    String maCTSP = resultSet.getString("MaSanPham");
                    SanPhamRepo2 repo = new SanPhamRepo2();
                    List<CTSPViewmodel> ctsplists = repo.getAll3(maCTSP);
                    // Tạo sheet mới cho chi tiết hóa đơn
                    Sheet chiTietSheet = workbook.createSheet(maCTSP);
                    Row headerChiTietRow = chiTietSheet.createRow(0);
                    String[] chiTietHeaders = {
                        "Mã CTSP", "Tên sản phẩm", "Số lượng", "Giá bán","Chất liệu",
                        "Kích cỡ","Loại quai","Màu sắc","Nhà sản xuất","Phân loại",
                        "Phụ kiện","Thương hiệu","Mô tả"
                    
                    };
                    for (int i = 0; i < chiTietHeaders.length; i++) {
                        Cell chiTietCell = headerChiTietRow.createCell(i);
                        chiTietCell.setCellValue(chiTietHeaders[i]);
                        chiTietCell.setCellStyle(styleData);
                    }

                    // Đổ dữ liệu chi tiết hóa đơn vào sheet mới
                    int chiTietRowIndex = 1;
                    for (CTSPViewmodel ctsp : ctsplists) {

                        Row chiTietRow = chiTietSheet.createRow(chiTietRowIndex++);
                        chiTietRow.createCell(0).setCellValue(ctsp.getMaSP2());
                        chiTietRow.createCell(1).setCellValue(ctsp.getTenSP2());
                        chiTietRow.createCell(2).setCellValue(ctsp.getSoLuong2());
                        chiTietRow.createCell(3).setCellValue(ctsp.getGiaBan());
                        //
                        chiTietRow.createCell(4).setCellValue(ctsp.getChatLieu());
                        chiTietRow.createCell(5).setCellValue(ctsp.getKichCo());
                        chiTietRow.createCell(6).setCellValue(ctsp.getLoaiQuai());
                        chiTietRow.createCell(7).setCellValue(ctsp.getMauSac());
                        ///
                        chiTietRow.createCell(8).setCellValue(ctsp.getNhaSanXuat());
                        chiTietRow.createCell(9).setCellValue(ctsp.getPhanLoai());
                        chiTietRow.createCell(10).setCellValue(ctsp.getPhuKien());
                        chiTietRow.createCell(11).setCellValue(ctsp.getThuongHieu());
                        chiTietRow.createCell(12).setCellValue(ctsp.getMoTa2());
                    }
                }
                String fileName = ".\\excelCTSP\\CTSP_Excel" + System.currentTimeMillis() + ".xlsx";
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

}
