
import KhachHang.CBOService;
import KhachHang.KhachHangEntity;
import KhachHang.KhachHangRepositoty;
import KhachHang.KhachHangService;
import KhachHang.LichSuEntity;
import com.raven.datechooser.DateChooser;
import giaodienduan.KhachHang;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author My PC
 */
public class KhachHangThongTin extends javax.swing.JFrame {

    List<KhachHangEntity> list = new ArrayList<>();
    DefaultTableModel tableModel = new DefaultTableModel();
    KhachHangService srKH = new KhachHangService();
    private DefaultTableModel dtm = new DefaultTableModel();
    private KhachHangRepositoty service = new KhachHangRepositoty();

    //bang lich su

    private DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
    private CBOService servicecbo = new CBOService();
    private List<KhachHangEntity> listcbo = new ArrayList<>();

    private BanHang viewBanHang;
    private DateChooser datechooser1 = new DateChooser();

    /**
     * Creates new form KhachHangThongTin
     */
    public KhachHangThongTin(BanHang viewBanHang) {

        this.viewBanHang = viewBanHang;
        initComponents();
        list = srKH.getKhachHang();
        tableModel = (DefaultTableModel) tblHienThi.getModel();
        tblHienThi.setDefaultEditor(Object.class, null);
        datechooser1.setTextRefernce(txtNgaySinh);
        tblHienThi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int index = tblHienThi.getSelectedRow();
                if (index >= 0 && evt.getClickCount() == 2) {
                    String TenKh = (String) tblHienThi.getValueAt(index, 2);
                    String maKH = (String) tblHienThi.getValueAt(index, 3);
                    String idKh = (String) tblHienThi.getValueAt(index, 1);
                    viewBanHang.getThongTinKH(TenKh, maKH, idKh);
                    setVisible(false);
                }
            }
        });
        showDataTable(list);
    }

     public boolean checkSDT() {
        if (txtSDT.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Số Điện Thoại Không Được Để Trống!");
            return false;
        }
        List<KhachHangEntity> tenDsp = service.getCheckSDT();
// Chuẩn hóa tên sản phẩm mới để so sánh
        String SDT = txtSDT.getText().trim().toLowerCase();
//        for (KhachHangEntity dsp : tenDsp) {
//            String sd = dsp.getCheckSDT().toLowerCase();
//            if (sd.equals(SDT)) {
//                JOptionPane.showMessageDialog(this, "Số Điện Thoại đã tồn tại trong cơ sở dữ liệu!");
//                return false;
//            }
//        }
        for (KhachHangEntity kh : tenDsp) {
            String sdt = kh.getSoDienThoai().trim(); // Lấy số điện thoại từ danh sách
            if (sdt.equals(SDT)) {
                JOptionPane.showMessageDialog(this, "Số Điện Thoại đã tồn tại trong cơ sở dữ liệu!");
                return false;
            }
        }

        return true;
    }
     
     private void detail(int index) {
        KhachHangEntity gv = list.get(index);
        txtTenKhachHang.setText(gv.getTenKhachHang());
        txtSDT.setText(gv.getSoDienThoai());
//    txtGioiTinh.setText(gv.getGioiTinh());
        txtMaKhachHang.setText(gv.getMaKhachHang());
        String gioiTinh = gv.getGioiTinh();
        if (gioiTinh.equals("0")) {
            cboGioiTinh1.setSelectedItem("Nam");
        } else if (gioiTinh.equals("1")) {
            cboGioiTinh1.setSelectedItem("Nữ");
        }

        txtNgaySinh.setText(String.valueOf(gv.getNgaySinh()));
        txtDiaChi.setText(gv.getDiaChi());
     }
    
    public KhachHangEntity getFormData() throws ParseException {
        String ten = txtTenKhachHang.getText().trim();
        String so = txtSDT.getText().trim();
        String ngaySinhText = txtNgaySinh.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        //check trùng

        ///
        if (ten.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên KH không được để trống!");
            return null;
        }
        if (ten.isEmpty() || !ten.matches("^[\\p{L}\\s]+$")) {
            JOptionPane.showMessageDialog(this, "Tên KH chỉ được chứa chữ cái và không được để trống!");
            return null;
        }

        if (so.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số Điện Thoại không được để trống!");
            return null;
        }
//        if (so.isEmpty() || !so.matches("^0\\d*$")) {
//            JOptionPane.showMessageDialog(this, "Số Điện Thoại không hợp lệ! Vui lòng nhập lại.");
//            return null;
//        }
        if (so.isEmpty() || !so.matches("^0\\d*$") || so.length() != 10) {
            JOptionPane.showMessageDialog(this, "Số Điện Thoại không hợp lệ! Vui lòng nhập lại.");
            return null;
        }
        if (ngaySinhText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không được để trống!");
            return null;
        }
        if (diaChi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống!");
            return null;
        }

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date ngay = null;
//        try {
//            sdf.setLenient(false);
//            ngay = sdf.parse(ngaySinhText);
//        } catch (ParseException e) {
//            JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ!");
//            return null;
//        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate ngay = null;
        try {
            LocalDate ngaySinh = LocalDate.parse(ngaySinhText, formatter);

            int currentYear = LocalDate.now().getYear();
            int minYear = currentYear - 200;

            if (ngaySinh.getYear() < 1900 || ngaySinh.getYear() > currentYear) {
                JOptionPane.showMessageDialog(null, "Năm sinh phải từ năm 1900 đến năm hiện tại!");
//                JOptionPane.showMessageDialog(null, "Thêm thất bại!");
                return null;
            }

            ngay = ngaySinh;
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Ngày sinh không hợp lệ!");
//            JOptionPane.showMessageDialog(null, "Thêm thất bại!");
            return null;
        }

        ///code sang kieu Date
        Date ngayDate = Date.from(ngay.atStartOfDay(ZoneId.systemDefault()).toInstant());

        int gioiTinhIndex = cboGioiTinh1.getSelectedIndex();
        String gioiTinh = "";
        if (gioiTinhIndex == 0) {
            gioiTinh = "0";
        } else if (gioiTinhIndex == 1) {
            gioiTinh = "1";
        }
        KhachHangEntity gv = new KhachHangEntity(ten, so, gioiTinh, ngayDate, diaChi);
        return gv;
    }

    public void showDataTable(List<KhachHangEntity> list) {
        tableModel.setRowCount(0);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tblHienThi.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // Set renderer for the first column (STT)

        int stt = 0;
        for (KhachHangEntity gv : list) {
            String gioiTinh = "";
            if (gv.getGioiTinh().equals("0")) {
                gioiTinh = "Nam";
            } else if (gv.getGioiTinh().equals("1")) {
                gioiTinh = "Nữ";
            } else {
                gioiTinh = "Không xác định";
            }
            stt++;
            tableModel.addRow(new Object[]{
                stt, gv.getIDKhachHang(), gv.getTenKhachHang(), gv.getMaKhachHang(), gv.getSoDienThoai(), gv.getDiaChi(), gioiTinh,//gv.getGioiTinh(),
                gv.getNgaySinh(),});
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHienThi = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        txtTenKhachHang = new javax.swing.JTextField();
        txtMaKhachHang = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cboGioiTinh1 = new combobox.Combobox();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chọn khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblHienThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "ID KH", "Tên", "Mã KH", "SDT", "Địa chỉ", "Giới tính", "Ngày sinh"
            }
        ));
        tblHienThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHienThiMouseClicked(evt);
            }
        });
        tblHienThi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblHienThiKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblHienThi);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel9.setText("Họ và tên");

        jLabel10.setText("Mã khách hàng");

        jLabel11.setText("Số điện thoại");

        jLabel12.setText("Địa chỉ");

        txtNgaySinh.setBackground(new java.awt.Color(204, 204, 204));
        txtNgaySinh.setOpaque(false);
        txtNgaySinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgaySinhActionPerformed(evt);
            }
        });

        jLabel13.setText("Ngày sinh");

        cboGioiTinh1.setBackground(new java.awt.Color(204, 204, 204));
        cboGioiTinh1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));
        cboGioiTinh1.setLabeText("");
        cboGioiTinh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboGioiTinh1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Giới tính");

        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Sửa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Xoá");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Làm mới");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboGioiTinh1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                            .addComponent(txtNgaySinh)
                            .addComponent(txtDiaChi)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addGap(0, 2, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(14, 14, 14)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboGioiTinh1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNgaySinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgaySinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgaySinhActionPerformed

    private void cboGioiTinh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboGioiTinh1ActionPerformed
  
    }//GEN-LAST:event_cboGioiTinh1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 int check = JOptionPane.showConfirmDialog(this, "Bạn có muốn Thêm không?", "?", JOptionPane.YES_NO_CANCEL_OPTION);

        if (check == JOptionPane.YES_OPTION) {
            try {
                // Kiểm tra trùng số điện thoại trước khi thêm
                if (!checkSDT()) {
                    // Số điện thoại đã tồn tại trong cơ sở dữ liệu, không thêm được
                    return;
                }

                // Nếu số điện thoại không trùng, tiến hành thêm khách hàng mới
                KhachHangEntity formData = getFormData();
                if (formData != null) {
                    service.Them(formData);
                    list = service.getKhachHang();
                    showDataTable(list);
                    JOptionPane.showMessageDialog(this, "Thêm thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm thất bại!");
                }
            } catch (ParseException ex) {
                Logger.getLogger(KhachHang.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }
        if (check == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Bạn đã chọn NO");
            return;
        } else {
            JOptionPane.showMessageDialog(this, "Bạn đã chọn CANCEL");
            return;
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
int check = JOptionPane.showConfirmDialog(this, "Bạn có muốn Sửa không?", "?", JOptionPane.YES_NO_CANCEL_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            //
            int row = tblHienThi.getSelectedRow();
            KhachHangEntity gv = list.get(row);
            try {
                service.Update(getFormData(), gv.getIDKhachHang());
            } catch (ParseException ex) {
                Logger.getLogger(KhackHang.class.getName()).log(Level.SEVERE, null, ex);
            }
            list = service.getKhachHang();
            showDataTable(list);
            //
            JOptionPane.showMessageDialog(this, "Sửa thành công!");
            return;

        }
        if (check == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Bạn đã chọn NO");
            return;
        } else {
            JOptionPane.showMessageDialog(this, "Bạn đã chọn CANCEL");
            return;
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
 int check = JOptionPane.showConfirmDialog(this, "Bạn có muốn Xóa không?", "?", JOptionPane.YES_NO_CANCEL_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            //
            int row = tblHienThi.getSelectedRow();
            //////////////
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Chọn dòng muốn xóa!");
                return;
            } else {
                ///////////////
                KhachHangEntity sv = list.get(row);
                service.Xoa(sv.getIDKhachHang());
                list = service.getKhachHang();
                showDataTable(list);
            }

            JOptionPane.showMessageDialog(this, "Xóa thành công!");
            return;

        }
        if (check == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Bạn đã chọn NO");
            return;
        } else {
            JOptionPane.showMessageDialog(this, "Bạn đã chọn CANCEL");
            return;
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
            txtDiaChi.setText("");
        txtNgaySinh.setText("");
        txtSDT.setText("");
        txtTenKhachHang.setText("");
//        txtGioiTinh.setText("");
//        rdoNam.setSelected(true);
//        rdoNu.setSelected(true);
//        cbo.setSelectedItem(cboBac.getItemAt(0));
//
        cboGioiTinh1.setSelectedItem(cboGioiTinh1.getItemAt(0));
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tblHienThiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblHienThiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblHienThiKeyPressed

    private void tblHienThiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHienThiMouseClicked
        int row = tblHienThi.getSelectedRow();
        detail(row);

//        KhachHangEntity ls = list.get(row);
//        dtmls = (DefaultTableModel) tblLichSuGiaoDich.getModel();
//        listls = service.getLichSu(ls.getIDKhachHang());
//        showDataTableLichSu(listls);
  
    }//GEN-LAST:event_tblHienThiMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(KhachHangThongTin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KhachHangThongTin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KhachHangThongTin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KhachHangThongTin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private combobox.Combobox cboGioiTinh1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblHienThi;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMaKhachHang;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKhachHang;
    // End of variables declaration//GEN-END:variables
}
