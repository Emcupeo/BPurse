
import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.GiamGiaEntity;
import service.GiamGiaService;
import viewmodel.GiamGiaViewModel;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.*;
import javax.swing.table.*;

public class PhieuGiamGia extends javax.swing.JInternalFrame {

    private List<GiamGiaViewModel> lists = new ArrayList<>();
    private DefaultTableModel dtm = new DefaultTableModel();
    private GiamGiaService ggs = new GiamGiaService();
    private DecimalFormat format = new DecimalFormat("0");

    public PhieuGiamGia() {
        initComponents();

        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);

//        //code 1 dong
        txtHDTT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtMa.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtTen.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtPTG.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtSTGTD.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtSL.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtTimKiem.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtNBD.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtNKT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

        for (int i = 0; i < tbl.getColumnCount(); i++) {
            tbl.getColumnModel().getColumn(i).setCellRenderer(new center());
        }

        JTableHeader header1 = tbl.getTableHeader();
        header1.setDefaultRenderer(new centerTable());

        initComponents();
        lists = ggs.getAll();
        dtm = (DefaultTableModel) tbl.getModel();
        showData(lists);
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

    public void showData(List<GiamGiaViewModel> list) {
        dtm.setRowCount(0);
        int stt = 1;
        NumberFormat format = NumberFormat.getInstance(new Locale("vi", "VN"));
        for (GiamGiaViewModel giamGiaViewModel : list) {
            dtm.addRow(new Object[]{
                stt++,
                giamGiaViewModel.getMaPhieuGiamGia(),
                giamGiaViewModel.getTenKhuyenMai(),
                giamGiaViewModel.getNgayBatDau(),
                giamGiaViewModel.getNgayKetThuc(),
                giamGiaViewModel.getSoLuongPhieu(),
                format.format(giamGiaViewModel.getHoaDonToiThieu()) + " VND",
                format.format(giamGiaViewModel.getPhanTramGiam()) + " %",
                format.format(giamGiaViewModel.getSoTienGiamToiDa()) + " VND",
                giamGiaViewModel.getTrangThai()
            });
        }

        // Set custom renderer for the status column
        tbl.getColumnModel().getColumn(9).setCellRenderer(new StatusCellRenderer());
    }

    class StatusCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            String status = (String) value;
            switch (status) {
                case "Sắp diễn ra":
                    setForeground(new Color(204, 204, 0));
                    break;
                case "Đang diễn ra":
                    setForeground(new Color(0, 100, 0));
                    break;
                case "Ðã kết thúc":
                    setForeground(Color.RED);
                    break;
                default:
                    setForeground(Color.RED);
            }
            return this;
        }
    }

    public GiamGiaEntity getData() {

        GiamGiaEntity gge = new GiamGiaEntity();

        String ma = ggs.taoMoiMaPhieu();
        String ten = txtTen.getText().trim();
        String soLuongPhieu = txtSL.getText().trim();
        String phanTramGiam = txtPTG.getText().trim().replaceAll("\\.0$", "");
        String hoaDonToiThieu = txtHDTT.getText().trim().replaceAll("\\.0$", "");
        String soTienGiamToiDa = txtSTGTD.getText().trim().replaceAll("\\.0$", "");
        String ngayBatDau = txtNBD.getText().trim();
        String ngayKetThuc = txtNKT.getText().trim();

        if (ma.isEmpty() || ten.isEmpty() || soLuongPhieu.isEmpty() || phanTramGiam.isEmpty() || hoaDonToiThieu.isEmpty() || soTienGiamToiDa.isEmpty() || ngayBatDau.isEmpty() || ngayKetThuc.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không được để trống dữ liệu");
            return null;
        }

        if (phanTramGiam.matches("[0-9]+") == false || Integer.parseInt(phanTramGiam) < 0 || Integer.parseInt(phanTramGiam) > 100) {
            JOptionPane.showMessageDialog(null, "Phần trăm giảm chỉ cần ghi số %, phải lớn hơn 0 và không quá 100");
            return null;
        }

        if (soLuongPhieu.matches("[0-9]+") == false || Integer.parseInt(soLuongPhieu) < 0) {
            JOptionPane.showMessageDialog(null, "Số lượng phiếu phải lớn hơn không");
            return null;
        }

        if (soTienGiamToiDa.matches("[0-9]+") == false || Integer.parseInt(soTienGiamToiDa) < 0) {
            JOptionPane.showMessageDialog(null, "Số tiền giảm tối đa phải lớn hơn không");
            return null;
        }

        if (hoaDonToiThieu.matches("[0-9]+") == false || Integer.parseInt(hoaDonToiThieu) < 0) {
            JOptionPane.showMessageDialog(null, "Hóa đơn tối thiểu phải lớn hơn 0");
            return null;
        }

        int soLuongPhieuInt = Integer.parseInt(soLuongPhieu);
        double phanTramGiamDouble = Double.parseDouble(phanTramGiam);
        double hoaDonToiThieuDouble = Double.parseDouble(hoaDonToiThieu);
        double soTienGiamToiDaDouble = Double.parseDouble(soTienGiamToiDa);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date ngayBatDauDate = null;
        Date ngayKetThucDate = null;

        try {
            ngayBatDauDate = sdf.parse(ngayBatDau);
            ngayKetThucDate = sdf.parse(ngayKetThuc);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String trangThai = cboTT.getSelectedItem().toString();

        gge.setMaPhieuGiamGia(ma);
        gge.setTenKhuyenMai(ten);
        gge.setSoLuongPhieu(soLuongPhieuInt);
        gge.setPhanTramGiam(phanTramGiamDouble);
        gge.setHoaDonToiThieu(hoaDonToiThieuDouble);
        gge.setSoTienGiamToiDa(soTienGiamToiDaDouble);
        gge.setNgayBatDau(ngayBatDauDate);
        gge.setNgayKetThuc(ngayKetThucDate);
        gge.setTrangThai(trangThai);

        gge.setCreatedAt(new Date());
        gge.setCreatedBy("983b9d9d-cd28-4109-8d5b-214771e4ba63");

        return gge;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        dateChooser1 = new com.raven.datechooser.DateChooser();
        dateChooser2 = new com.raven.datechooser.DateChooser();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        materialTabbed1 = new tabbed.MaterialTabbed();
        jPanel2 = new javax.swing.JPanel();
        cboTT = new combobox.Combobox();
        txtMa = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        txtNBD = new javax.swing.JTextField();
        txtNKT = new javax.swing.JTextField();
        txtHDTT = new javax.swing.JTextField();
        txtSL = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtSTGTD = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtPTG = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnThem = new RoundedButton();
        btnSua = new RoundedButton();
        btnXoa = new RoundedButton();
        btnLM = new RoundedButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cboTT1 = new combobox.Combobox();
        txtTimKiem = new javax.swing.JTextField();
        btnTim = new RoundedButton();

        dateChooser1.setTextRefernce(txtNBD);

        dateChooser2.setToolTipText("");
        dateChooser2.setTextRefernce(txtNKT);

        setPreferredSize(new java.awt.Dimension(1230, 710));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1230, 710));

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã", "Tên", "Ngày BĐ", "Ngày KT", "Số Lượng", "HĐ tối thiểu", "% giảm", "Giảm tối đa", "Trạng thái"
            }
        ));
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        cboTT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Sắp diễn ra", "Đang diễn ra", "Ðã kết thúc" }));
        cboTT.setLabeText("Trạng Thái");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Mã Phiếu");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Số Lượng Được Phép Sử Dụng");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Hóa Đơn Tối Thiểu");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Ngày Kết Thúc");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Số Tiền Giảm Tối Đa");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Phần Trăm Giảm");

        jLabel1.setFont(new java.awt.Font("UVN Chim Bien", 1, 24)); // NOI18N
        jLabel1.setText("Phiếu Giảm Giá");

        btnThem.setBackground(new java.awt.Color(0, 0, 0));
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(0, 0, 0));
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(0, 0, 0));
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLM.setBackground(new java.awt.Color(0, 0, 0));
        btnLM.setForeground(new java.awt.Color(255, 255, 255));
        btnLM.setText("Làm Mới");
        btnLM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLMActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Tên Phiếu");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Ngày Bắt Đầu");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtMa)
                        .addComponent(txtTen)
                        .addComponent(txtNBD, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                        .addComponent(jLabel9))
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addGap(328, 328, 328)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHDTT, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel10)
                    .addComponent(txtNKT, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(271, 271, 271)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8)
                    .addComponent(txtPTG, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSTGTD, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(cboTT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(497, 497, 497))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(482, 482, 482))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHDTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNBD))))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtPTG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtSTGTD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(71, 71, 71))
                        .addComponent(cboTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLM, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        materialTabbed1.addTab("Cập nhật", jPanel2);

        cboTT1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất cả", "Sắp diễn ra", "Đang diễn ra", "Ðã kết thúc" }));
        cboTT1.setLabeText("");
        cboTT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTT1ActionPerformed(evt);
            }
        });

        btnTim.setBackground(new java.awt.Color(0, 0, 0));
        btnTim.setForeground(new java.awt.Color(255, 255, 255));
        btnTim.setText("Tìm kiếm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(materialTabbed1, javax.swing.GroupLayout.PREFERRED_SIZE, 1181, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboTT1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1191, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(materialTabbed1, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cboTT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMouseClicked
        int row = tbl.getSelectedRow();
        GiamGiaViewModel ggvm = lists.get(row);

        txtHDTT.setText(String.valueOf(ggvm.getHoaDonToiThieu()));
        txtMa.setText(ggvm.getMaPhieuGiamGia());
        txtTen.setText(ggvm.getTenKhuyenMai());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        txtNBD.setText(sdf.format(ggvm.getNgayBatDau()));
        txtNKT.setText(sdf.format(ggvm.getNgayKetThuc()));

        txtPTG.setText(String.valueOf(ggvm.getPhanTramGiam()));
        txtSL.setText(String.valueOf(ggvm.getSoLuongPhieu()));
        txtSTGTD.setText(String.valueOf(ggvm.getSoTienGiamToiDa()));

        cboTT.setSelectedItem(ggvm.getTrangThai());
    }//GEN-LAST:event_tblMouseClicked

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        lists = ggs.timTheoTen(txtTimKiem.getText());
        showData(lists);
    }//GEN-LAST:event_btnTimActionPerformed

    private void cboTT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTT1ActionPerformed
        lists = ggs.timTheoTT((String) cboTT1.getSelectedItem());
        showData(lists);
    }//GEN-LAST:event_cboTT1ActionPerformed

    private void btnLMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLMActionPerformed
        txtHDTT.setText("");
        txtMa.setText("");
        txtNBD.setText("");
        txtNKT.setText("");
        txtPTG.setText("");
        txtSL.setText("");
        txtSTGTD.setText("");
        txtTen.setText("");

        lists = ggs.getAll();
        showData(lists);
    }//GEN-LAST:event_btnLMActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int row = tbl.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Không được để trống dữ liệu");
            return;
        }
        GiamGiaViewModel ggvm = lists.get(row);
        int xn = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn xóa không?");
        if (xn == JOptionPane.YES_OPTION) {
            ggs.xoa(ggvm.getMaPhieuGiamGia());
            JOptionPane.showMessageDialog(null, "Xóa thành công!");
            lists = ggs.getAll();
            showData(lists);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int row = tbl.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để sửa phiếu giảm giá");
            return;
        }

        GiamGiaViewModel ggvm = lists.get(row);
        int xn = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn sửa phiếu không?");
        if (xn == JOptionPane.YES_OPTION) {
            GiamGiaEntity gg = getData();

            if (gg.getNgayBatDau().after(gg.getNgayKetThuc())) {
                JOptionPane.showMessageDialog(null, "Không thể sửa ngày bắt đầu vượt quá ngày kết thúc!");
                return;
            }

            ggs.sua(gg, ggvm.getMaPhieuGiamGia());
            JOptionPane.showMessageDialog(null, "Sửa thành công!");
            lists = ggs.getAll();
            showData(lists);
        } else {
            JOptionPane.showMessageDialog(null, "Sửa phiếu giảm giá thất bại!");
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        int xn = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn thêm phiếu không?");
        if (xn == JOptionPane.YES_OPTION) {
            GiamGiaEntity gg = getData();
            if (gg != null) {
                java.util.Date date = new java.util.Date();
                java.sql.Date ngayHienTai = new java.sql.Date(date.getTime());

                if (gg.getNgayBatDau().before(ngayHienTai)) {
                    JOptionPane.showMessageDialog(null, "Ngày bắt đầu không thể trước ngày tạo!");
                    return;
                }

                if (gg.getNgayBatDau().after(gg.getNgayKetThuc())) {
                    JOptionPane.showMessageDialog(null, "Ngày bắt đầu không thể sau ngày kết thúc!");
                    return;
                }

                if (!"Sắp diễn ra".equals(gg.getTrangThai())) {
                    JOptionPane.showMessageDialog(null, "Trạng thái phải là 'Sắp diễn ra'!");
                    return;
                }
                ggs.them(gg);
                JOptionPane.showMessageDialog(null, "Thêm thành công!");
                lists = ggs.getAll();
                showData(lists);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Thêm phiếu giảm giá thất bại!");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RoundedButton btnLM;
    private RoundedButton btnSua;
    private RoundedButton btnThem;
    private RoundedButton btnTim;
    private RoundedButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private combobox.Combobox cboTT;
    private combobox.Combobox cboTT1;
    private com.raven.datechooser.DateChooser dateChooser1;
    private com.raven.datechooser.DateChooser dateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private tabbed.MaterialTabbed materialTabbed1;
    private javax.swing.JTable tbl;
    private javax.swing.JTextField txtHDTT;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtNBD;
    private javax.swing.JTextField txtNKT;
    private javax.swing.JTextField txtPTG;
    private javax.swing.JTextField txtSL;
    private javax.swing.JTextField txtSTGTD;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
