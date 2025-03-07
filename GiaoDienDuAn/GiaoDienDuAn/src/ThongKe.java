
import ThongKe.BieuDo.QuanLyThongKeController;
import ThongKe.SoHoaDonEntity;
import ThongKe.SoHoaDonRepositoty;
import ThongKe.ThongKeEntity;
import ThongKe.ThongKeRepositoty;
import java.awt.Color;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ThongKe extends javax.swing.JInternalFrame {

    private DefaultTableModel dtm = new DefaultTableModel();
    private ThongKeRepositoty service = new ThongKeRepositoty();
    private List<ThongKeEntity> list = new ArrayList<>();

    private DefaultTableModel dtmhd = new DefaultTableModel();
    private SoHoaDonRepositoty servicehd = new SoHoaDonRepositoty();
    private List<SoHoaDonEntity> listhd = new ArrayList<>();
    private DecimalFormat format = new DecimalFormat("");
    private DefaultComboBoxModel dcbm = new DefaultComboBoxModel();

    public ThongKe() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        //code 1 dong
        txtSearch.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
//Student

        for (int i = 0; i < tblDoanhThuTheoThoiGian.getColumnCount(); i++) {
            tblDoanhThuTheoThoiGian.getColumnModel().getColumn(i).setCellRenderer(new center());
        }

        // Set the custom header renderer
        JTableHeader header1 = tblDoanhThuTheoThoiGian.getTableHeader();
        header1.setDefaultRenderer(new centerTable());
        tinhTongSoHoaDonDaThanhToan();

        tinhTongSoKhachHang();
        tinhTongSoHoaDonChuaThanhToan();
        hienThiTongTien();

        listhd = servicehd.getSoHoaDon();
        dtmhd = (DefaultTableModel) tblDoanhThuTheoThoiGian.getModel();
        showDataTableLichSu(listhd);

        QuanLyThongKeController controller = new QuanLyThongKeController();
        controller.setDateToChart1(jpnView1);
        /////Search
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String keyword = txtSearch.getText();
                listhd = servicehd.search(keyword);
                showDataTableLichSu(listhd);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String keyword = txtSearch.getText();
                listhd = servicehd.search(keyword);
                showDataTableLichSu(listhd);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Not used for plain text components
            }
        });
//===================CBO
        setCombobox();
        LocTheoNamm();
        dcbm = (DefaultComboBoxModel) LocTheoNam.getModel();
        //listhd = servicehd.getCBO();
        dcbm = (DefaultComboBoxModel) cboThang.getModel();

    }

    private void setCombobox() {
        cboThang.removeAllItems();
        cboThang.addItem("Tất cả");
        cboThang.addItem("01");
        cboThang.addItem("02");
        cboThang.addItem("03");
        cboThang.addItem("04");
        cboThang.addItem("05");
        cboThang.addItem("06");
        cboThang.addItem("07");
        cboThang.addItem("08");
        cboThang.addItem("09");
        cboThang.addItem("10");
        cboThang.addItem("11");
        cboThang.addItem("12");

    }

    private void LocTheoNamm() {
        LocTheoNam.removeAllItems();
        LocTheoNam.addItem("Tất cả");
        LocTheoNam.addItem("2024");
        LocTheoNam.addItem("2023");
        LocTheoNam.addItem("2022");
        LocTheoNam.addItem("2021");
        LocTheoNam.addItem("2020");
        LocTheoNam.addItem("2019");
        LocTheoNam.addItem("2018");
        LocTheoNam.addItem("2017");
        LocTheoNam.addItem("2016");
        LocTheoNam.addItem("2015");
        LocTheoNam.addItem("2014");
        LocTheoNam.addItem("2013");

    }

    public void showDataTableLichSu(List<SoHoaDonEntity> list) {
        dtmhd.setRowCount(0);
        int stt = 0;
        for (SoHoaDonEntity gvv : list) {
            //String gioiTinhStr = gv.isGioiTinh() ? "Nam" : "Nữ";
            stt++;
            dtmhd.addRow(new Object[]{
                stt, gvv.getMaHoaDon(), gvv.getNgayTao(), gvv.getTongTien()
            });
        }
    }

    private BigDecimal tinhTongTien() {
        BigDecimal tongTien = BigDecimal.ZERO;
        list = service.getThongKe();
        for (ThongKeEntity entity : list) {
            BigDecimal tien = entity.getTongTien();
            tongTien = tongTien.add(tien);
        }

        return tongTien;
    }

    private void hienThiTongTien() {
        BigDecimal tongTien = tinhTongTien();
        DecimalFormat df = new DecimalFormat("#,###,###");
        TongTien.setText(df.format(tongTien) + "VND");
    }
///======================================

    private void tinhTongSoHoaDonDaThanhToan() {
        List<ThongKeEntity> list = service.getThongKe(); // Sử dụng phương thức getThongKe() đã được sửa đổi
        int soHoaDonDaThanhToan = 0; // Biến đếm số hóa đơn đã thanh toán
        // Duyệt qua từng hóa đơn trong danh sách
        for (ThongKeEntity bill : list) {
            // Kiểm tra nếu trạng thái của hóa đơn là "Đã thanh toán"
            if (bill.getTrangThai().equals("Đã thanh toán")) {
                // Tăng biến đếm lên 1
                soHoaDonDaThanhToan++;
            }
        }
        SoHoaDonDaThanhToan1.setText(String.valueOf(soHoaDonDaThanhToan) + " Hóa Đơn");
    }

    // Các phần code khác ở đây...
    //=========================================================
    private void tinhTongSoHoaDonChuaThanhToan() {
        // Lấy danh sách hóa đơn từ cơ sở dữ liệu
        List<ThongKeEntity> list = service.getHoaDon(); // Sử dụng phương thức getThongKe() đã được sửa đổi

        int soHoaDonChuaThanhToan = 0; // Biến đếm số hóa đơn chờ thanh toán

        // Duyệt qua từng hóa đơn trong danh sách
        for (ThongKeEntity bill : list) {
            // Kiểm tra nếu trạng thái của hóa đơn là "Chờ thanh toán"
            if (bill.getTrangThai().equals("Chờ thanh toán")) {
                // Tăng biến đếm lên 1
                soHoaDonChuaThanhToan++;

            }
        }

        // Hiển thị tổng số hóa đơn chờ thanh toán lên giao diện
        HoaDonChuaThanhToan.setText(String.valueOf(soHoaDonChuaThanhToan) + " Hóa Đơn");
    }

//========================================================
    private void tinhTongSoKhachHang() {
        // Lấy danh sách hóa đơn từ cơ sở dữ liệu
        List<ThongKeEntity> list = service.getKhachHang(); // Sử dụng phương thức getThongKe() đã được sửa đổi

        // Khởi tạo một Map để lưu trữ số lần xuất hiện của mỗi tên khách hàng
        Map<String, Integer> customerCountMap = new HashMap<>();

        // Duyệt qua từng hóa đơn trong danh sách để đếm số lần xuất hiện của mỗi tên khách hàng
        for (ThongKeEntity bill : list) {
            String customerName = bill.getTenKhachHang();
            customerCountMap.put(customerName, customerCountMap.getOrDefault(customerName, 0) + 1);
        }

        // Tính tổng số khách hàng
        int totalCustomers = 0;
        for (int count : customerCountMap.values()) {
            totalCustomers += count;
        }

        // Hiển thị tổng số khách hàng lên giao diện
        SoKhachHang.setText(String.valueOf(totalCustomers) + "  Khách Hàng");
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        TongTien = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        SoHoaDonDaThanhToan1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        HoaDonChuaThanhToan = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        roundedPanel2 = new RoundedPanel();
        jLabel8 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new RoundedButton();
        roundedPanel3 = new RoundedPanel();
        cboThang = new combobox.Combobox();
        roundedPanel1 = new RoundedPanel();
        LocTheoNam = new combobox.Combobox();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jpnRoot = new javax.swing.JPanel();
        jpnView1 = new javax.swing.JPanel();
        jpnView2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDoanhThuTheoThoiGian = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        SoKhachHang = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));

        TongTien.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TongTien.setForeground(new java.awt.Color(255, 0, 0));
        TongTien.setText("jLabel2");

        jLabel10.setFont(new java.awt.Font("UVN Thay Giao", 1, 16)); // NOI18N
        jLabel10.setText("Doanh Thu");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(TongTien)))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TongTien)
                .addGap(35, 35, 35))
        );

        jPanel12.setBackground(new java.awt.Color(0, 102, 102));

        SoHoaDonDaThanhToan1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        SoHoaDonDaThanhToan1.setForeground(new java.awt.Color(255, 0, 0));
        SoHoaDonDaThanhToan1.setText("jLabel3");

        jLabel3.setFont(new java.awt.Font("UVN Thay Giao", 1, 16)); // NOI18N
        jLabel3.setText("Chưa Thanh Toán");

        jLabel11.setFont(new java.awt.Font("UVN Thay Giao", 1, 16)); // NOI18N
        jLabel11.setText("Số Hóa Đơn ");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(107, 107, 107))))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(SoHoaDonDaThanhToan1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(4, 4, 4)
                .addComponent(jLabel3)
                .addGap(30, 30, 30)
                .addComponent(SoHoaDonDaThanhToan1)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(0, 102, 102));

        HoaDonChuaThanhToan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        HoaDonChuaThanhToan.setForeground(new java.awt.Color(255, 51, 0));
        HoaDonChuaThanhToan.setText("jLabel4");

        jLabel2.setFont(new java.awt.Font("UVN Thay Giao", 1, 16)); // NOI18N
        jLabel2.setText("Đã Thanh Toán");

        jLabel12.setFont(new java.awt.Font("UVN Thay Giao", 1, 16)); // NOI18N
        jLabel12.setText("Số Hóa Đơn ");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(86, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(83, 83, 83))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(HoaDonChuaThanhToan)
                        .addGap(101, 101, 101))))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(27, 27, 27)
                .addComponent(HoaDonChuaThanhToan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roundedPanel2.setBackground(new java.awt.Color(0, 102, 102));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel8.setText("Thời Gian:");

        txtSearch.setBackground(new java.awt.Color(0, 102, 102));

        btnSearch.setBackground(new java.awt.Color(0, 0, 0));
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Search.png"))); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundedPanel2Layout = new javax.swing.GroupLayout(roundedPanel2);
        roundedPanel2.setLayout(roundedPanel2Layout);
        roundedPanel2Layout.setHorizontalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        roundedPanel2Layout.setVerticalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundedPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        roundedPanel3.setBackground(new java.awt.Color(0, 102, 102));

        cboThang.setBackground(new java.awt.Color(0, 102, 102));
        cboThang.setLabeText("");
        cboThang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboThangMouseClicked(evt);
            }
        });
        cboThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboThangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundedPanel3Layout = new javax.swing.GroupLayout(roundedPanel3);
        roundedPanel3.setLayout(roundedPanel3Layout);
        roundedPanel3Layout.setHorizontalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(cboThang, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        roundedPanel3Layout.setVerticalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roundedPanel1.setBackground(new java.awt.Color(0, 102, 102));

        LocTheoNam.setBackground(new java.awt.Color(0, 102, 102));
        LocTheoNam.setLabeText("");
        LocTheoNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LocTheoNamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(LocTheoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LocTheoNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel6.setText("Lọc theo năm");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel14.setText("Lọc Theo Tháng");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel7.setText("Lọc Theo Khoảng Thời Gian");

        jLabel1.setFont(new java.awt.Font("UVN Chim Bien", 1, 18)); // NOI18N
        jLabel1.setText("Thống Kê");

        jTabbedPane1.setBackground(new java.awt.Color(204, 255, 255));

        jpnRoot.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpnView1Layout = new javax.swing.GroupLayout(jpnView1);
        jpnView1.setLayout(jpnView1Layout);
        jpnView1Layout.setHorizontalGroup(
            jpnView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1062, Short.MAX_VALUE)
        );
        jpnView1Layout.setVerticalGroup(
            jpnView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 166, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpnView2Layout = new javax.swing.GroupLayout(jpnView2);
        jpnView2.setLayout(jpnView2Layout);
        jpnView2Layout.setHorizontalGroup(
            jpnView2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpnView2Layout.setVerticalGroup(
            jpnView2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpnRootLayout = new javax.swing.GroupLayout(jpnRoot);
        jpnRoot.setLayout(jpnRootLayout);
        jpnRootLayout.setHorizontalGroup(
            jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnRootLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnView1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnView2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpnRootLayout.setVerticalGroup(
            jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnRootLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnView1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jpnView2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnRoot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnRoot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Biểu Đồ", jPanel3);

        jPanel4.setBackground(new java.awt.Color(0, 102, 102));

        tblDoanhThuTheoThoiGian.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã Hóa Đơn", "Ngày Tạo Hóa Đơn", "Tổng Tiền Hóa Đơn"
            }
        ));
        jScrollPane1.setViewportView(tblDoanhThuTheoThoiGian);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1082, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Doanh Thu Theo Thời Gian", jPanel4);

        jPanel14.setBackground(new java.awt.Color(0, 102, 102));

        SoKhachHang.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        SoKhachHang.setForeground(new java.awt.Color(255, 0, 0));
        SoKhachHang.setText("jLabel5");

        jLabel13.setFont(new java.awt.Font("UVN Thay Giao", 1, 16)); // NOI18N
        jLabel13.setText("Số Khách Hàng");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(94, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(71, 71, 71))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(SoKhachHang)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(SoKhachHang)
                .addGap(43, 43, 43))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(91, 91, 91)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(roundedPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(roundedPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(441, 441, 441)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1087, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(roundedPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(roundedPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(927, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 334, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LocTheoNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LocTheoNamActionPerformed
                String selectedYear = LocTheoNam.getSelectedItem().toString();

        if (!selectedYear.equals("Tất cả")) {
            // Lọc theo năm được chọn
            showDataTableLichSu(servicehd.getCBONam(selectedYear));
        } else {
            // Hiển thị tất cả dữ liệu
            showDataTableLichSu(servicehd.getSoHoaDon());
        }
    }//GEN-LAST:event_LocTheoNamActionPerformed

    private void cboThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboThangActionPerformed
       String chon = cboThang.getSelectedItem().toString();

        if (chon.equals("Tất cả")) {
            showDataTableLichSu(servicehd.getSoHoaDon());
        } else {
            showDataTableLichSu(servicehd.getCBO(chon));
        }
    }//GEN-LAST:event_cboThangActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchActionPerformed

    private void cboThangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboThangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboThangMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel HoaDonChuaThanhToan;
    private combobox.Combobox LocTheoNam;
    private javax.swing.JLabel SoHoaDonDaThanhToan1;
    private javax.swing.JLabel SoKhachHang;
    private javax.swing.JLabel TongTien;
    private RoundedButton btnSearch;
    private combobox.Combobox cboThang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jpnRoot;
    private javax.swing.JPanel jpnView1;
    private javax.swing.JPanel jpnView2;
    private RoundedPanel roundedPanel1;
    private RoundedPanel roundedPanel2;
    private RoundedPanel roundedPanel3;
    private javax.swing.JTable tblDoanhThuTheoThoiGian;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
