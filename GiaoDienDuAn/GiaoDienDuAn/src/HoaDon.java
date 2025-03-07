
import HoaDon.HoaDonService;
import HoaDon.HoaDonViewModel;
import HoaDon.WebCam;
import HoaDonChiTiet.HoaDonChiTietService;
import HoaDonChiTiet.HoaDonChiTietViewModel;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import lichsuhoadon.LSHDService;
import lichsuhoadon.LSHDViewModel;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import print.ReportManager;

import print.model.FieldReportPayment;
import print.model.ParameterReportPayment;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
/**
 *
 * @author ADMIN
 */
public class HoaDon extends javax.swing.JInternalFrame {

    DefaultTableModel dtm = new DefaultTableModel();
    private HoaDonService sr = new HoaDonService();
    List<HoaDonViewModel> lists = new ArrayList<>();

    DefaultTableModel dtm1 = new DefaultTableModel();
    private HoaDonChiTietService srCT = new HoaDonChiTietService();
    List<HoaDonChiTietViewModel> listsCT = new ArrayList<>();

    DefaultTableModel dtm2 = new DefaultTableModel();
    private LSHDService srLSHD = new LSHDService();
    List<LSHDViewModel> listsLSHD = new ArrayList<>();

    private DecimalFormat format = new DecimalFormat("");
    Color DefauColor, ClickColor;

    /**
     * Creates new form Menu1
     */
    public HoaDon() {
        initComponents();
        lists = sr.getAll();

        dtm = (DefaultTableModel) tblHoaDon.getModel();
        dtm1 = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        dtm2 = (DefaultTableModel) tblLichSuThanhToan.getModel();

        showDataTable(lists);
        showDataTableCT(listsCT);
        showDataTableLSHD(listsLSHD);

        DefauColor = new Color(102, 102, 102);
        ClickColor = new Color(204, 204, 204);

        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        //=========
        searchText1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtMax.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtMin.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

        for (int i = 0; i < tblHoaDon.getColumnCount(); i++) {
            tblHoaDon.getColumnModel().getColumn(i).setCellRenderer(new center());
        }

        // Set the custom header renderer
        JTableHeader header1 = tblHoaDon.getTableHeader();
        header1.setDefaultRenderer(new centerTable());

        // LichSu
        for (int i = 0; i < tblHoaDonChiTiet.getColumnCount(); i++) {
            tblHoaDonChiTiet.getColumnModel().getColumn(i).setCellRenderer(new centerr());
        }

        // Set the custom header renderer
        JTableHeader header2 = tblHoaDonChiTiet.getTableHeader();
        header2.setDefaultRenderer(new centerTablee());
        //tblstudent3
        for (int i = 0; i < tblLichSuThanhToan.getColumnCount(); i++) {
            tblLichSuThanhToan.getColumnModel().getColumn(i).setCellRenderer(new centerr());
        }

        // Set the custom header renderer
        JTableHeader header3 = tblLichSuThanhToan.getTableHeader();
        header3.setDefaultRenderer(new centerTablee());

        txtMin.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchHD();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchHD();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchHD();
            }
        });
        txtMin.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchHD();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchHD();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchHD();
            }
        });

        txtMax.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchHD();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchHD();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchHD();
            }
        });

        searchText1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                sr.search(searchText1.getText() + "");
                lists = sr.search(searchText1.getText() + "");
                showDataTable(lists);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                sr.search(searchText1.getText() + "");
                lists = sr.search(searchText1.getText() + "");
                showDataTable(lists);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                sr.search(searchText1.getText() + "");
                lists = sr.search(searchText1.getText() + "");
                showDataTable(lists);
            }

        });

        btnQuetQR.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WebCam webCam = new WebCam();
                webCam.addIDListener(new WebCam.IDListener() {
                    @Override
                    public void onIDReceived(String idHD) {
                        // Xử lý ID ở đây
                        lists = sr.getIDHD(idHD);
                        showDataTable(lists);
                    }
                });
                webCam.setVisible(true); // Hiển thị JFrame 2 khi nút được nhấn

                webCam.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        webCam.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Ẩn JFrame 2 khi nút đóng được nhấn
                        webCam.onWindowClosing();
                    }
                });
            }
        });

    }
//     private void printHD() {
//        try {
//            int rowIndex = tblHoaDon.getSelectedRow();
//            if (rowIndex >= 0) {
//                HoaDonViewModel hd = lists.get(rowIndex);
//                dtm1 = (DefaultTableModel) tblHoaDonChiTiet.getModel();
//                listsCT = srCT.getAll(hd.getMaHoaDon());
//                showDataTableCT(listsCT);
//                List<FieldReportPayment> fields = new ArrayList<>();
//
//                // Lặp qua các hàng trong bảng tblHoaDonChiTiet để lấy thông tin chi tiết
//                for (int i = 0; i < tblHoaDonChiTiet.getRowCount(); i++) {
//                    // Lấy dữ liệu từ mỗi hàng
//                    String tenSP = tblHoaDonChiTiet.getValueAt(i, 2).toString();
//                    
//                    // Kiểm tra và chuyển đổi số lượng
//                    int soLuong = 0;
//                    try {
//                        soLuong = Integer.parseInt(tblHoaDonChiTiet.getValueAt(i, 3).toString());
//                    } catch (NumberFormatException e) {
//                        e.printStackTrace();
//                        // Xử lý nếu giá trị không hợp lệ
//                    }
//                    
//                    // Kiểm tra và chuyển đổi giá bán
//                    double giaBan = 0.0;
//                    try {
//                        String giaBanStr = tblHoaDonChiTiet.getValueAt(i, 4).toString().replace(" VND", "");
//                        giaBan = Double.parseDouble(giaBanStr);
//                    } catch (NumberFormatException e) {
//                        e.printStackTrace();
//                        // Xử lý nếu giá trị không hợp lệ
//                    }
//                    double tongTien = 0.0;
//                    try {
//                        String tongTienStr = tblHoaDonChiTiet.getValueAt(i, 5).toString().replace(" VND", "");
//                        tongTien = Double.parseDouble(tongTienStr);
//                    } catch (NumberFormatException e) {
//                        e.printStackTrace();
//                        // Xử lý nếu giá trị không hợp lệ
//                    }
//                    // In dữ liệu ra console
//                    System.out.println("Tên sản phẩm: " + tenSP);
//                    System.out.println("Số lượng: " + soLuong);
//                    System.out.println("Đơn giá: " + giaBan);
//                    System.out.println("Tổng tiền: " + tongTien);
//
//                    // Thêm thông tin vào danh sách fields để tạo báo cáo
//                    fields.add(new FieldReportPayment(tenSP,  giaBan, soLuong, tongTien));
//                }
//                // Kiểm tra nếu có dữ liệu để tạo báo cáo
//                if (!fields.isEmpty()) {
//                    // Tạo mã QR Code
//                        // Tạo tham số để in báo cáo
//                        ParameterReportPayment dataPrint = new ParameterReportPayment(hd.getMaHoaDon(), hd.getNgayTao()+"", hd.getTenKhachHang(), fields);                         
//
//                        // Trước khi gọi printReportPayment
//                        ReportManager.getInstance().checkJRXMLPath();
//                        ReportManager.getInstance().checkCompilation();
//                    ReportManager.getInstance().checkReportParameters(dataPrint);
//
//                        // Gọi phương thức in báo cáo
//                        ReportManager.getInstance().printReportPayment(dataPrint);
//                    }
//            }             
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//}

    private void printHD() {
        try {
            int rowIndex = tblHoaDon.getSelectedRow();
            if (rowIndex >= 0) {
                HoaDonViewModel hd = lists.get(rowIndex);
                dtm1 = (DefaultTableModel) tblHoaDonChiTiet.getModel();
                listsCT = srCT.getAll(hd.getMaHoaDon());
                showDataTableCT(listsCT);
                List<FieldReportPayment> fields = new ArrayList<>();

                // Lặp qua các hàng trong bảng tblHoaDonChiTiet để lấy thông tin chi tiết
                for (int i = 0; i < tblHoaDonChiTiet.getRowCount(); i++) {
                    // Lấy dữ liệu từ mỗi hàng
                    String tenSP = tblHoaDonChiTiet.getValueAt(i, 2).toString();

                    // Kiểm tra và chuyển đổi số lượng
                    int soLuong = 0;
                    try {
                        soLuong = Integer.parseInt(tblHoaDonChiTiet.getValueAt(i, 3).toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        // Xử lý nếu giá trị không hợp lệ
                    }

                    // Kiểm tra và chuyển đổi giá bán
                    double giaBan = 0.0;
                    String giaBanStr = tblHoaDonChiTiet.getValueAt(i, 4).toString().replaceAll("[^\\d.]", "");
                    if (!giaBanStr.isEmpty()) {
                        try {
                            giaBan = Double.parseDouble(giaBanStr);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                            // Xử lý nếu giá trị không hợp lệ
                        }
                    }

                    double tongTien = soLuong * giaBan;

                    // In dữ liệu ra console
                    System.out.println("Tên sản phẩm: " + tenSP);
                    System.out.println("Số lượng: " + soLuong);
                    System.out.println("Đơn giá: " + giaBan);
                    System.out.println("Tổng tiền: " + tongTien);

                    // Thêm thông tin vào danh sách fields để tạo báo cáo
                    fields.add(new FieldReportPayment(tenSP, String.valueOf(giaBan), String.valueOf(soLuong), String.valueOf(tongTien)));
                }
                // Kiểm tra nếu có dữ liệu để tạo báo cáo
                if (!fields.isEmpty()) {
                    // Tạo mã QR Code

                    // Tạo tham số để in báo cáo
                    ParameterReportPayment dataPrint = new ParameterReportPayment(
                            hd.getMaHoaDon(), String.valueOf(hd.getNgayTao()), hd.getTenKhachHang(), hd.getSdt(), hd.getDiaChi(), String.valueOf(hd.getThanhTien()).replaceAll("[^\\d.]", ""), fields);
// Trước khi gọi printReportPayment
                    ReportManager.getInstance().checkJRXMLPath();
                    ReportManager.getInstance().checkCompilation();
                    ReportManager.getInstance().checkReportParameters(dataPrint);

                    // Gọi phương thức in báo cáo
                    ReportManager.getInstance().printReportPayment(dataPrint);

                } else {
                    JOptionPane.showMessageDialog(this, "Không có dữ liệu để tạo báo cáo.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn hóa đơn để in ra.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void searchHD() {
        try {
            String minText = txtMin.getText().trim();
            String maxText = txtMax.getText().trim();

            // Kiểm tra xem minText và maxText có phải là số hợp lệ hay không
            if (!minText.isEmpty() && !maxText.isEmpty()) {
                float min = Float.parseFloat(minText);
                float max = Float.parseFloat(maxText);

                // Đảm bảo rằng phương thức searchGiaTienHD đã triển khai đúng
                lists = sr.searchGiaTien(min, max);
                showDataTable(lists);
            }
        } catch (NumberFormatException e) {
            // Xử lý ngoại lệ, ví dụ: hiển thị thông báo lỗi cho người dùng
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá trị số hợp lệ.");
        }
    }

    public void showDataTable(List<HoaDonViewModel> listsKH) {
        dtm.setRowCount(0);
        int i = 0;
        for (HoaDonViewModel kh : listsKH) {
            i++;
            dtm.addRow(new Object[]{
                i, kh.getID(), kh.getIdNV(), kh.getTenKhachHang(), kh.getSdt(), kh.getDiaChi(), format.format(kh.getThanhTien()), kh.getNgayTao(), kh.getNgayThanhToan(),
                 kh.getTrangThai()
            });
        }

    }

    public void showDataTableCT(List<HoaDonChiTietViewModel> listHDCT) {
        dtm1.setRowCount(0);
        int i = 0;
        for (HoaDonChiTietViewModel kh : listHDCT) {
            i++;
            dtm1.addRow(new Object[]{
                i, kh.getIDSP(), kh.getTenSP(), kh.getTenCL(), kh.getTenMS(), kh.getTenPK(), kh.getTenTH(), kh.getSoLuong(), format.format(kh.getDonGia()), format.format(kh.getSoLuong() * kh.getDonGia())
            });
        }

    }

    public void showDataTableLSHD(List<LSHDViewModel> listLSHD) {
        dtm2.setRowCount(0);
        int i = 0;
        for (LSHDViewModel kh : listLSHD) {
            i++;
            dtm2.addRow(new Object[]{
                i, kh.getNhanVien(), kh.getGio(), kh.getNgay(), kh.getHanhDong()
            });
        }

    }

    //3
    private static class centerrr extends DefaultTableCellRenderer {

        public centerrr() {
            setHorizontalAlignment(SwingConstants.CENTER);
        }
    }

    private static class centerTableee extends DefaultTableCellRenderer {

        public centerTableee() {
            setHorizontalAlignment(SwingConstants.CENTER);
        }
    }

// 2
    private static class centerr extends DefaultTableCellRenderer {

        public centerr() {
            setHorizontalAlignment(SwingConstants.CENTER);
        }
    }

    private static class centerTablee extends DefaultTableCellRenderer {

        public centerTablee() {
            setHorizontalAlignment(SwingConstants.CENTER);
        }
    }

    private static class center extends DefaultTableCellRenderer {

        public center() {
            setHorizontalAlignment(SwingConstants.CENTER);
        }
    }

    private static class centerTable extends DefaultTableCellRenderer {

        public centerTable() {
            setHorizontalAlignment(SwingConstants.CENTER);
        }
    }

    private void createPDF(String maHD) throws BadElementException, IOException, DocumentException {
        int selectedRowIndex = tblHoaDon.getSelectedRow();
        try {
            Document document = new Document(PageSize.A4, 30, 30, 30, 30); // Giảm lề

            PdfWriter.getInstance(document, new FileOutputStream(".\\pdf\\HD-PDF_" + tblHoaDon.getValueAt(selectedRowIndex, 1).toString() + ".pdf"));
            document.open();

            // Tiêu đề hóa đơn
            Font titleFont = new Font(20, Font.BOLD);
            Paragraph title = new Paragraph("Thong tin hoa don - " + tblHoaDon.getValueAt(selectedRowIndex, 1).toString(), titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(16f);

            // Thông tin hóa đơn
            Font contentFont = new Font(16);
            Paragraph content = new Paragraph();
            content.setFont(contentFont);
            content.setLeading(24f); // Đặt khoảng cách giữa các dòng
            content.add("Ma HD: " + tblHoaDon.getValueAt(selectedRowIndex, 1).toString() + "\n");
            content.add("Ten Khach Hang: " + tblHoaDon.getValueAt(selectedRowIndex, 5) + "\n");
            content.add("Ngay tao: " + tblHoaDon.getValueAt(selectedRowIndex, 2).toString() + "\n");
            content.add("Dia chi: " + tblHoaDon.getValueAt(selectedRowIndex, 6).toString() + "\n");
            content.add("So dien thoai: " + tblHoaDon.getValueAt(selectedRowIndex, 7).toString() + "\n");
            content.add("Tong tien: " + tblHoaDon.getValueAt(selectedRowIndex, 4).toString() + "VND" + "\n");
            content.add("\n");
            content.add("\n");
            content.add("\n");
            content.add("\n");
            content.add("\n");
            content.add("\n");
            content.add("\n");
            content.add("\n");
            content.add("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");

            document.add(title);
            document.add(content);

            // Thêm mã QR dưới cùng và ở giữa
            // Thêm mã QR dưới cùng và ở giữa
            String qrFilePath = ".\\qrcode\\" + maHD + ".png";
            Image qrImage = Image.getInstance(qrFilePath);
            qrImage.setAlignment(Element.ALIGN_CENTER);
            qrImage.scaleToFit(300, 300); // Giảm kích thước ảnh QR
            qrImage.setAbsolutePosition((PageSize.A4.getWidth() - qrImage.getScaledWidth()) / 2, 50); // Đặt vị trí ảnh QR ở giữa và dưới cùng
            document.add(qrImage);

            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        roundedPanel1 = new RoundedPanel();
        jLabel3 = new javax.swing.JLabel();
        searchText1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnQuetQR = new RoundedButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtMax = new javax.swing.JTextField();
        btnHoaDonMoi = new RoundedButton();
        cbbTrangThaiHD = new combobox.Combobox();
        btnInHoaDon = new RoundedButton();
        btnXuat = new RoundedButton();
        txtMin = new javax.swing.JTextField();
        btnLamMoi = new RoundedButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        roundedPanel2 = new RoundedPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDonChiTiet = new javax.swing.JTable();
        roundedPanel3 = new RoundedPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblLichSuThanhToan = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1230, 710));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("UVN Chim Bien", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Hóa Đơn");

        roundedPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel3.setText("Tìm Kiếm Hóa Đơn");

        searchText1.setBackground(new java.awt.Color(204, 204, 204));
        searchText1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchText1ActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/timkiem.png"))); // NOI18N

        btnQuetQR.setBackground(new java.awt.Color(0, 0, 0));
        btnQuetQR.setForeground(new java.awt.Color(255, 255, 255));
        btnQuetQR.setText("Quét QR");
        btnQuetQR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuetQRActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel5.setText("Trạng Thái Hóa Đơn");

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel7.setText("Giá Từ");

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel8.setText("Đến");

        txtMax.setBackground(new java.awt.Color(204, 204, 204));

        btnHoaDonMoi.setBackground(new java.awt.Color(0, 0, 0));
        btnHoaDonMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnHoaDonMoi.setText("Thanh Hóa Đơn Mới");
        btnHoaDonMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoaDonMoiActionPerformed(evt);
            }
        });

        cbbTrangThaiHD.setBackground(new java.awt.Color(204, 204, 204));
        cbbTrangThaiHD.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Chờ thanh toán", "Đã thanh toán", " " }));
        cbbTrangThaiHD.setLabeText("");
        cbbTrangThaiHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTrangThaiHDActionPerformed(evt);
            }
        });

        btnInHoaDon.setBackground(new java.awt.Color(0, 0, 0));
        btnInHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnInHoaDon.setText("In Hóa Đơn");
        btnInHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHoaDonActionPerformed(evt);
            }
        });

        btnXuat.setBackground(new java.awt.Color(0, 0, 0));
        btnXuat.setForeground(new java.awt.Color(255, 255, 255));
        btnXuat.setText("Xuất Excel");
        btnXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatActionPerformed(evt);
            }
        });

        txtMin.setBackground(new java.awt.Color(204, 204, 204));

        btnLamMoi.setBackground(new java.awt.Color(0, 0, 0));
        btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Hóa Đơn", "Mã nhân viên", "Tên Khách Hàng", "Số điện thoại", "Địa chỉ", "Tổng Tiền", "Ngày Tạo", "Ngày Thanh Toán", "Trạng thái"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnInHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112))
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(roundedPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(24, 24, 24)
                                .addComponent(searchText1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(btnQuetQR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnHoaDonMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(roundedPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(40, 40, 40)
                                .addComponent(cbbTrangThaiHD, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 305, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addGap(40, 40, 40)
                                .addComponent(txtMin, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addComponent(jLabel8)
                                .addGap(34, 34, 34)
                                .addComponent(txtMax, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(118, 118, 118))))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnQuetQR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHoaDonMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbTrangThaiHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8)
                            .addComponent(txtMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(searchText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        roundedPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel9.setText("Hóa Đơn Chi Tiết");

        tblHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SPCT", "Tên Sản Phẩm", "Chất liệu", "Màu sắc", "Phụ kiện", "Thương hiệu", "Số Lượng", "Đơn giá", "Tổng giá"
            }
        ));
        jScrollPane2.setViewportView(tblHoaDonChiTiet);

        javax.swing.GroupLayout roundedPanel2Layout = new javax.swing.GroupLayout(roundedPanel2);
        roundedPanel2.setLayout(roundedPanel2Layout);
        roundedPanel2Layout.setHorizontalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        roundedPanel2Layout.setVerticalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addContainerGap())
        );

        roundedPanel3.setBackground(new java.awt.Color(204, 204, 204));
        roundedPanel3.setPreferredSize(new java.awt.Dimension(592, 254));

        tblLichSuThanhToan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Người Tác Động", "Giờ", "Ngày Cập Nhật", "Hành Động"
            }
        ));
        jScrollPane3.setViewportView(tblLichSuThanhToan);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel10.setText("Lịch Sử Hóa Đơn");

        javax.swing.GroupLayout roundedPanel3Layout = new javax.swing.GroupLayout(roundedPanel3);
        roundedPanel3.setLayout(roundedPanel3Layout);
        roundedPanel3Layout.setHorizontalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundedPanel3Layout.setVerticalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(417, 417, 417)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(roundedPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(roundedPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundedPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundedPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(76, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 92, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatActionPerformed
        sr.xuatHoaDon();
    }//GEN-LAST:event_btnXuatActionPerformed

    private void btnInHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHoaDonActionPerformed

        // TODO add your handling code here:
        int selectedRowIndex = tblHoaDon.getSelectedRow();
        if (selectedRowIndex == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn để tạo mã QR!");
            return;
        }
        
        HoaDonViewModel hd = lists.get(selectedRowIndex);
        String maHD = hd.getMaHoaDon();
       
        try {
            ByteArrayOutputStream out = QRCode.from(maHD)
                    .to(ImageType.PNG).stream();

            String fileName = ".\\qrcode\\" + maHD + ".png"; // Tên file ảnh QRCode

            // Ghi ảnh QR vào file
            FileOutputStream fout = new FileOutputStream(new File(fileName));
            fout.write(out.toByteArray());
            fout.flush();
            createPDF(maHD);
        } catch (BadElementException ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(this, "Xuất hóa đơn thành công");

    }//GEN-LAST:event_btnInHoaDonActionPerformed

    private void cbbTrangThaiHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTrangThaiHDActionPerformed
        if ((cbbTrangThaiHD.getSelectedItem() + "").equalsIgnoreCase("Đã thanh toán")) {
            showDataTable(sr.trangThai("Đã thanh toán"));
            lists = sr.trangThai("Đã thanh toán");

        } else if ((cbbTrangThaiHD.getSelectedItem() + "").equalsIgnoreCase("Chờ thanh toán")) {
            showDataTable(sr.trangThai("Chờ thanh toán"));
            lists = sr.trangThai("Chờ thanh toán");
        }
    }//GEN-LAST:event_cbbTrangThaiHDActionPerformed

    private void btnHoaDonMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoaDonMoiActionPerformed

        BanHang menu1 = new BanHang();
        jPanel1.removeAll();
        jPanel1.add(menu1).setVisible(true);


    }//GEN-LAST:event_btnHoaDonMoiActionPerformed

    private void btnQuetQRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuetQRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnQuetQRActionPerformed

    private void searchText1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchText1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchText1ActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed

        lists = sr.getAll();
        showDataTable(lists);

        dtm1 = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        listsCT = srCT.getAll(null);
        showDataTableCT(listsCT);

        dtm2 = (DefaultTableModel) tblLichSuThanhToan.getModel();
        listsLSHD = srLSHD.getAll(null);
        showDataTableLSHD(listsLSHD);
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        int rowIndex = tblHoaDon.getSelectedRow();
        HoaDonViewModel hd = lists.get(rowIndex);

        dtm1 = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        listsCT = srCT.getAll(hd.getMaHoaDon());
        showDataTableCT(listsCT);

        dtm2 = (DefaultTableModel) tblLichSuThanhToan.getModel();
        listsLSHD = srLSHD.getAll(hd.getMaHoaDon());
        showDataTableLSHD(listsLSHD);
    }//GEN-LAST:event_tblHoaDonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RoundedButton btnHoaDonMoi;
    private RoundedButton btnInHoaDon;
    private RoundedButton btnLamMoi;
    private RoundedButton btnQuetQR;
    private RoundedButton btnXuat;
    private combobox.Combobox cbbTrangThaiHD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private RoundedPanel roundedPanel1;
    private RoundedPanel roundedPanel2;
    private RoundedPanel roundedPanel3;
    private javax.swing.JTextField searchText1;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblHoaDonChiTiet;
    private javax.swing.JTable tblLichSuThanhToan;
    private javax.swing.JTextField txtMax;
    private javax.swing.JTextField txtMin;
    // End of variables declaration//GEN-END:variables
}
