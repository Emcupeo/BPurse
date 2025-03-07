
import KhachHang.CBOService;
import KhachHang.KhachHangEntity;
import KhachHang.KhachHangRepositoty;
import KhachHang.LichSuEntity;
import giaodienduan.KhachHang;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionEvent;

public class KhackHang extends javax.swing.JInternalFrame {

    private DefaultTableModel dtm = new DefaultTableModel();
    private KhachHangRepositoty service = new KhachHangRepositoty();
    private List<KhachHangEntity> list = new ArrayList<>();

    //bang lich su
    private DefaultTableModel dtmls = new DefaultTableModel();
    private List<KhachHangEntity> listls = new ArrayList<>();

    private List<LichSuEntity> listlskh = new ArrayList<>();
//cbo
    private DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
    private CBOService servicecbo = new CBOService();
    private List<KhachHangEntity> listcbo = new ArrayList<>();

    public KhackHang() {
        initComponents();
        ///========================
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        txtNgaySinh.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtTenKhachHang.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtSDT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtDiaChi.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtSearch.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
//        txtGioiTinh.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
//
        list = service.getKhachHang();
        dtm = (DefaultTableModel) tblThongTinKhachHang.getModel();
        showDataTable(list);
//        ///hien bang
//        detailDongVat(1);
///Bang
//Table Lich Su
//        listlskh = service.getKhachHangLichSu();
//        dtmls = (DefaultTableModel) tblLichSuGiaoDich.getModel();
//        showDataTableLichSu(listlskh);

//        list = service.getLichSu();
//        dtm = (DefaultTableModel) tblLichSuGiaoDich.getModel();
//        showDataTableLichSu(list);
//bang 1
        for (int i = 0; i < tblThongTinKhachHang.getColumnCount(); i++) {
            tblThongTinKhachHang.getColumnModel().getColumn(i).setCellRenderer(new center());
        }

        // Set the custom header renderer
        JTableHeader header1 = tblThongTinKhachHang.getTableHeader();
        header1.setDefaultRenderer(new centerTable());

        //LichSu
        for (int i = 0; i < tblLichSuGiaoDich.getColumnCount(); i++) {
            tblLichSuGiaoDich.getColumnModel().getColumn(i).setCellRenderer(new center());
        }

        // Set the custom header renderer
        JTableHeader header2 = tblLichSuGiaoDich.getTableHeader();
        header2.setDefaultRenderer(new centerTable());

        //===========================================================
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                //System.out.println("Edit row : " + row);
                int check = JOptionPane.showConfirmDialog(KhackHang.this, "Bạn có muốn Sửa không?", "?", JOptionPane.YES_NO_CANCEL_OPTION);
                if (check == JOptionPane.YES_OPTION) {
                    //int row = tblThongTinKhachHang.getSelectedRow();
                    KhachHangEntity gv = list.get(row);
                    try {
                        service.Update(getFormData(), gv.getIDKhachHang());
                    } catch (ParseException ex) {
                        Logger.getLogger(KhackHang.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    list = service.getKhachHang();
                    showDataTable(list);
                    JOptionPane.showMessageDialog(KhackHang.this, "Sửa thành công!");
                    return;
                } else if (check == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(KhackHang.this, "Bạn đã chọn NO");
                    return;
                } else {
                    JOptionPane.showMessageDialog(KhackHang.this, "Bạn đã chọn CANCEL");
                    return;
                }
            }

            @Override
            public void onDelete(int row) {
                if (tblThongTinKhachHang.isEditing()) {
                    tblThongTinKhachHang.getCellEditor().stopCellEditing();
                }
                int check = JOptionPane.showConfirmDialog(KhackHang.this, "Bạn có muốn Xóa không?", "?", JOptionPane.YES_NO_CANCEL_OPTION);
                if (check == JOptionPane.YES_OPTION) {
                    //
//                    //int row = tblThongTinKhachHang.getSelectedRow();
//                    KhachHangEntity gv = list.get(row);
//                    service.Xoa(gv.getIDKhachHang());
//                    list = service.getKhachHang();
////            listls = service.getLichSu(gv.getIDKhachHang());
//                    showDataTable(list);
////            showDataTableLichSu(listls);
//                    dtmls = (DefaultTableModel) tblLichSuGiaoDich.getModel();
//                    dtmls.setRowCount(0);
                    //===============================================
                    //int row = tblThongTinKhachHang.getSelectedRow();
                    //////////////
                    if (row == -1) {
                        JOptionPane.showMessageDialog(KhackHang.this, "Chọn dòng muốn xóa!");
                        return;
                    } else {
                        ///////////////
                        KhachHangEntity sv = list.get(row);
                        service.Xoa(sv.getIDKhachHang());
                        list = service.getKhachHang();
                        showDataTable(list);
                    }
                    //
                    JOptionPane.showMessageDialog(KhackHang.this, "Xóa thành công!");
                    return;

                }
                if (check == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(KhackHang.this, "Bạn đã chọn NO");
                    return;
                } else {
                    JOptionPane.showMessageDialog(KhackHang.this, "Bạn đã chọn CANCEL");
                    return;
                }
//                DefaultTableModel model = (DefaultTableModel) tblThongTinKhachHang.getModel();
//                model.removeRow(row);
            }

            @Override
            public void onView(int row) {
                System.out.println("View row : " + row);
            }
        };
        tblThongTinKhachHang.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRender());
        tblThongTinKhachHang.getColumnModel().getColumn(6).setCellEditor(new TableActionCellEditor(event));
//        tblThongTinKhachHang.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
//            @Override
//            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
//                setHorizontalAlignment(SwingConstants.RIGHT);
//                return super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
//            }
//        });
        ///table
        setBackground(new Color(0, 0, 0, 0));
        tblThongTinKhachHang.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblThongTinKhachHang.getTableHeader().setOpaque(false);
        tblThongTinKhachHang.getTableHeader().setBackground(new Color(32, 136, 203));
        tblThongTinKhachHang.getTableHeader().setBackground(new Color(225, 225, 225));
        tblThongTinKhachHang.setRowHeight(35);

        /////////////////////////////////////////////
        tblThongTinKhachHang.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            private int previousSelectedRow = -1;

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Đầu tiên, đặt lại màu và font cho tất cả các dòng
                    for (int i = 0; i < tblThongTinKhachHang.getRowCount(); i++) {
                        for (int j = 0; j < tblThongTinKhachHang.getColumnCount(); j++) {
                            tblThongTinKhachHang.setValueAt(tblThongTinKhachHang.getValueAt(i, j), i, j);
                        }
                    }

                    // Sau đó, in đậm dòng được chọn
                    int selectedRow = tblThongTinKhachHang.getSelectedRow();
                    if (selectedRow != -1) {
                        for (int i = 0; i < tblThongTinKhachHang.getColumnCount(); i++) {
                            tblThongTinKhachHang.setValueAt("<html><b>" + tblThongTinKhachHang.getValueAt(selectedRow, i) + "</b></html>", selectedRow, i);
                        }

                        // Đặt lại dòng trước đó về trạng thái bình thường nếu có
                        if (previousSelectedRow != -1 && previousSelectedRow != selectedRow) {
                            for (int j = 0; j < tblThongTinKhachHang.getColumnCount(); j++) {
                                tblThongTinKhachHang.setValueAt(tblThongTinKhachHang.getValueAt(previousSelectedRow, j), previousSelectedRow, j);
                            }
                        }

                        previousSelectedRow = selectedRow;
                    }
                }
            }
        });
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                service.Search(txtSearch.getText() + "");
                list = service.Search(txtSearch.getText() + "");
                showDataTable(list);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                service.Search(txtSearch.getText() + "");
                list = service.Search(txtSearch.getText() + "");
                showDataTable(list);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                service.Search(txtSearch.getText() + "");
                list = service.Search(txtSearch.getText() + "");
                showDataTable(list
                );
            }

        });

//load du lieu len cbo
        listcbo = servicecbo.getCBO();

        setCombobox();

        listcbo = servicecbo.getCBO();
        dcbm = (DefaultComboBoxModel) CboGioiTinh.getModel();

    }

    private void setCombobox() {
        cboGioiTinh1.removeAllItems();
        cboGioiTinh1.addItem("Tất Cả");
        cboGioiTinh1.addItem("Nam");
        cboGioiTinh1.addItem("Nữ");

    }
///lich su

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

    ///
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

//        public void showDataTable(List<KhachHangEntity> list) {
//            dtm.setRowCount(0); 
//            int stt = 0;
//            for (KhachHangEntity gv : list) {
//                //String gioiTinhStr = gv.isGioiTinh() ? "Nam" : "Nữ";
//                stt++;
//                dtm.addRow(new Object[]{
//                    stt, gv.getTenKhachHang(), gv.getSoDienThoai(), gv.getGioiTinh(), gv.getNgaySinh(), gv.getDiaChi()
//                });
//            }
//        }
    public void showDataTable(List<KhachHangEntity> list) {
        dtm.setRowCount(0);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tblThongTinKhachHang.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // Set renderer for the first column (STT)

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
            dtm.addRow(new Object[]{
                stt, gv.getTenKhachHang(), gv.getSoDienThoai(), gioiTinh,//gv.getGioiTinh(),
                gv.getNgaySinh(), gv.getDiaChi()
            });
        }
    }

    public void showDataTableLichSu(List<LichSuEntity> list) {
        dtmls.setRowCount(0);
        int stt = 0;
        for (LichSuEntity gvv : list) {
            //String gioiTinhStr = gv.isGioiTinh() ? "Nam" : "Nữ";
            stt++;
            dtmls.addRow(new Object[]{
                stt, gvv.getTenKhachHang(), gvv.getMaHD(),gvv.getCreateBy(), gvv.getUpdateAt(), gvv.getHanhDong()//, gvv.getDiaChi()
            });
        }
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
//        if (diaChi.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống!");
//            return null;
//        }
if (diaChi.length() > 100) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được nhập quá 100 ký tự!");
            return null;
        } else if (diaChi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống!");
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate ngay = null;
        try {
            LocalDate ngaySinh = LocalDate.parse(ngaySinhText, formatter);

            LocalDate currentDate = LocalDate.now();

            if (ngaySinh.isAfter(currentDate)) {
                JOptionPane.showMessageDialog(null, "Ngày sinh không được lớn hơn ngày hiện tại!");
                return null;
            } else if (ngaySinh.getYear() < 1900) {
                JOptionPane.showMessageDialog(null, "Năm sinh phải từ năm 1900 đến năm hiện tại!");
                return null;
            }

            ngay = ngaySinh;
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Ngày sinh không hợp lệ!");
            return null;
        }

// code chuyển sang kiểu Date
        Date ngayDate = Date.from(ngay.atStartOfDay(ZoneId.systemDefault()).toInstant());
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//        LocalDate ngay = null;
//        try {
//            LocalDate ngaySinh = LocalDate.parse(ngaySinhText, formatter);
//
//            int currentYear = LocalDate.now().getYear();
//            int minYear = currentYear - 200;
//
//            if (ngaySinh.getYear() < 1900 || ngaySinh.getYear() > currentYear) {
//                JOptionPane.showMessageDialog(null, "Năm sinh phải từ năm 1900 đến năm hiện tại!");
////                JOptionPane.showMessageDialog(null, "Thêm thất bại!");
//                return null;
//            }
//
//            ngay = ngaySinh;
//        } catch (DateTimeParseException e) {
//            JOptionPane.showMessageDialog(null, "Ngày sinh không hợp lệ!");
////            JOptionPane.showMessageDialog(null, "Thêm thất bại!");
//            return null;
//        }
//
//        ///code sang kieu Date
//        Date ngayDate = Date.from(ngay.atStartOfDay(ZoneId.systemDefault()).toInstant());

        int gioiTinhIndex = CboGioiTinh.getSelectedIndex();
        String gioiTinh = "";
        if (gioiTinhIndex == 0) {
            gioiTinh = "0";
        } else if (gioiTinhIndex == 1) {
            gioiTinh = "1";
        }
        KhachHangEntity gv = new KhachHangEntity(ten, so, gioiTinh, ngayDate, diaChi);
        return gv;
        //public KhachHangEntity getFormData() throws ParseException {
        //        String ten = txtTenKhachHang.getText().trim();
        //        String so = txtSDT.getText().trim();
        //        String ngaySinhText = txtNgaySinh.getText().trim();
        //        String diachi = txtDiaChi.getText().trim();
        //
        //        // Kiểm tra dữ liệu trống và hiển thị thông báo nếu cần
        //        if (ten.isEmpty()) {
        //            JOptionPane.showMessageDialog(this, "Tên KH không được để trống!");
        //            return null;
        //        }
        //        if (ten.isEmpty() || !ten.matches("^[\\p{L}\\s]+$")) {
        //            JOptionPane.showMessageDialog(this, "Tên KH chỉ được chứa chữ cái và không được để trống!");
        //            return null;
        //        }
        //
        //        if (so.isEmpty() || !so.matches("^0\\d*$")) {
        //            JOptionPane.showMessageDialog(this, "Số Điện Thoại không hợp lệ! Vui lòng nhập lại.");
        //            return null;
        //        }
        //        if (ngaySinhText.isEmpty()) {
        //            JOptionPane.showMessageDialog(this, "Ngày sinh không được để trống!");
        //            return null;
        //        }
        //        if (diachi.isEmpty()) {
        //            JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống!");
        //            return null;
        //        }
        //
        //        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        //        Date ngay;
        //        try {
        //            ngay = sdf.parse(ngaySinhText);
        //        } catch (ParseException e) {
        //            JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ!");
        //            return null;
        //        }
        //
        //        int gioiTinhIndex = CboGioiTinh.getSelectedIndex();
        //        String gioiTinh = "";
        //        if (gioiTinhIndex == 0) {
        //            gioiTinh = "0";
        //        } else if (gioiTinhIndex == 1) {
        //            gioiTinh = "1";
        //        }
        //
        //        KhachHangEntity gv = new KhachHangEntity(ten, so, gioiTinh, ngay, diachi);
        //        return gv;
        //    }
        //check trung so dien thoia
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        dateChooser1 = new com.raven.datechooser.DateChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        botronPanel1 = new BotronPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        txtTenKhachHang = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        roundedButton6 = new RoundedButton();
        roundedButton8 = new RoundedButton();
        btnClear = new RoundedButton();
        roundedButton10 = new RoundedButton();
        jLabel8 = new javax.swing.JLabel();
        roundedButton7 = new RoundedButton();
        txtSearch = new javax.swing.JTextField();
        cboGioiTinh1 = new combobox.Combobox();
        CboGioiTinh = new combobox.Combobox();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThongTinKhachHang = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLichSuGiaoDich = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        dateChooser1.setTextRefernce(txtNgaySinh);

        setPreferredSize(new java.awt.Dimension(1230, 710));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("UVN Chim Bien", 1, 18)); // NOI18N
        jLabel1.setText("Khách Hàng");

        botronPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel3.setText("Ngày Sinh:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel4.setText("Họ Và Tên:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel5.setText("Giới Tính:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel6.setText("SĐT:");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel7.setText("Địa Chỉ:");

        txtNgaySinh.setBackground(new java.awt.Color(204, 204, 204));
        txtNgaySinh.setOpaque(false);
        txtNgaySinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgaySinhActionPerformed(evt);
            }
        });

        txtTenKhachHang.setBackground(new java.awt.Color(204, 204, 204));
        txtTenKhachHang.setOpaque(false);
        txtTenKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKhachHangActionPerformed(evt);
            }
        });

        txtSDT.setBackground(new java.awt.Color(204, 204, 204));
        txtSDT.setOpaque(false);
        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });

        txtDiaChi.setBackground(new java.awt.Color(204, 204, 204));
        txtDiaChi.setOpaque(false);
        txtDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiActionPerformed(evt);
            }
        });

        roundedButton6.setBackground(new java.awt.Color(0, 0, 0));
        roundedButton6.setForeground(new java.awt.Color(255, 255, 255));
        roundedButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/daucong.png"))); // NOI18N
        roundedButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roundedButton6MouseClicked(evt);
            }
        });
        roundedButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedButton6ActionPerformed(evt);
            }
        });

        roundedButton8.setBackground(new java.awt.Color(0, 0, 0));
        roundedButton8.setForeground(new java.awt.Color(255, 255, 255));
        roundedButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Xoa.png"))); // NOI18N
        roundedButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roundedButton8MouseClicked(evt);
            }
        });
        roundedButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedButton8ActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(0, 0, 0));
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/LamMoii.png"))); // NOI18N
        btnClear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnClearMouseClicked(evt);
            }
        });
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        roundedButton10.setBackground(new java.awt.Color(0, 0, 0));
        roundedButton10.setForeground(new java.awt.Color(255, 255, 255));
        roundedButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-pencil-32.png"))); // NOI18N
        roundedButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roundedButton10MouseClicked(evt);
            }
        });
        roundedButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedButton10ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel8.setText("Tìm theo tên mã địa chỉ sđt");

        roundedButton7.setBackground(new java.awt.Color(0, 0, 0));
        roundedButton7.setForeground(new java.awt.Color(255, 255, 255));
        roundedButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Search.png"))); // NOI18N
        roundedButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roundedButton7MouseClicked(evt);
            }
        });
        roundedButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedButton7ActionPerformed(evt);
            }
        });

        txtSearch.setBackground(new java.awt.Color(204, 204, 204));
        txtSearch.setOpaque(false);
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        cboGioiTinh1.setBackground(new java.awt.Color(204, 204, 204));
        cboGioiTinh1.setLabeText("");
        cboGioiTinh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboGioiTinh1ActionPerformed(evt);
            }
        });

        CboGioiTinh.setBackground(new java.awt.Color(204, 204, 204));
        CboGioiTinh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));
        CboGioiTinh.setLabeText("");
        CboGioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboGioiTinhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout botronPanel1Layout = new javax.swing.GroupLayout(botronPanel1);
        botronPanel1.setLayout(botronPanel1Layout);
        botronPanel1Layout.setHorizontalGroup(
            botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botronPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CboGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(botronPanel1Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addGap(105, 105, 105))
            .addGroup(botronPanel1Layout.createSequentialGroup()
                .addGroup(botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(botronPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel8)
                        .addGap(62, 62, 62)
                        .addComponent(roundedButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(237, 237, 237)
                        .addComponent(cboGioiTinh1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(botronPanel1Layout.createSequentialGroup()
                        .addGap(411, 411, 411)
                        .addComponent(roundedButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(roundedButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(roundedButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(botronPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(295, Short.MAX_VALUE))
        );
        botronPanel1Layout.setVerticalGroup(
            botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botronPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(CboGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roundedButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundedButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundedButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboGioiTinh1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(roundedButton7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel2.setText("Thiếp Lập Thông Tin Khách Hàng");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel10.setText("Thiếp Lập Thông Tin Khách Hàng");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jTabbedPane1.setBackground(new java.awt.Color(0, 255, 255));

        tblThongTinKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên KH", "SĐT", "Giới Tính", "Ngày Sinh", "Địa Chỉ", "Hành Động"
            }
        ));
        tblThongTinKhachHang.setFocusable(false);
        tblThongTinKhachHang.setGridColor(new java.awt.Color(0, 153, 153));
        tblThongTinKhachHang.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblThongTinKhachHang.setRowHeight(25);
        tblThongTinKhachHang.setSelectionBackground(new java.awt.Color(0, 153, 153));
        tblThongTinKhachHang.setShowGrid(false);
        tblThongTinKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThongTinKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblThongTinKhachHang);

        tblLichSuGiaoDich.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên KH", "Mã hoá đơn", "Mã NV", "Ngày cập nhật", "Hành động"
            }
        ));
        tblLichSuGiaoDich.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLichSuGiaoDichMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblLichSuGiaoDich);

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel12.setText("Thông Tin Khách Hàng");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel13.setText("Lịch Sử Giao Dịch");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(677, 677, 677)
                                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(444, 444, 444)
                            .addComponent(jLabel1))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addComponent(jLabel10))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(29, 29, 29)
                            .addComponent(jLabel2))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel9)
                            .addGap(48, 48, 48)
                            .addComponent(jLabel11))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addComponent(botronPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 227, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botronPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(175, 175, 175)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNgaySinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgaySinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgaySinhActionPerformed

    private void txtTenKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKhachHangActionPerformed

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

    private void txtDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiActionPerformed

    private void roundedButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roundedButton6MouseClicked
        //        service.Them(getFormData());
        //        list = service.getALLL();
        //        showDataTable(list);
    }//GEN-LAST:event_roundedButton6MouseClicked

    private void roundedButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedButton6ActionPerformed
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
        }
//int check = JOptionPane.showConfirmDialog(this, "Bạn có muốn Thêm không?", "?", JOptionPane.YES_NO_CANCEL_OPTION);
//        if (check == JOptionPane.YES_OPTION) {
//            try {
//                KhachHangEntity gv = getFormData();
//                if (gv != null) {
//                    service.Them(gv);
//                    list = service.getKhachHang();
//                    showDataTable(list);
//                    JOptionPane.showMessageDialog(this, "Thêm thành công!");
//                } 
////else {
////                    JOptionPane.showMessageDialog(this, "Dữ liệu nhập không hợp lệ!");
////                }
//            } catch (ParseException ex) {
//                Logger.getLogger(KhachHang.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            return;
//        } else if (check == JOptionPane.NO_OPTION) {
//            JOptionPane.showMessageDialog(this, "Bạn đã chọn NO");
//            return;
//        } else {
//            JOptionPane.showMessageDialog(this, "Bạn đã chọn CANCEL");
//            return;
//        }
    }//GEN-LAST:event_roundedButton6ActionPerformed

    private void roundedButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roundedButton7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_roundedButton7MouseClicked

    private void roundedButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedButton7ActionPerformed
//        String searchType = txtSearch.getText().trim(); // Get the search criteria from the text field
//        if (!searchType.isEmpty()) {
//            // Perform the search and update the table
//            list = service.Search(searchType);
//            showDataTable(list);
//        } else {
//            // If the search criteria is empty, show a message
//            JOptionPane.showMessageDialog(this, "Vui lòng nhập Tên Khach Hang để tìm kiếm.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//        }

    }//GEN-LAST:event_roundedButton7ActionPerformed

    private void roundedButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roundedButton8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_roundedButton8MouseClicked

    private void roundedButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedButton8ActionPerformed
        int check = JOptionPane.showConfirmDialog(this, "Bạn có muốn Xóa không?", "?", JOptionPane.YES_NO_CANCEL_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            //
            int row = tblThongTinKhachHang.getSelectedRow();
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
//            int row = tblThongTinKhachHang.getSelectedRow();
//            KhachHangEntity gv = list.get(row);
//            service.Xoa(gv.getIDKhachHang());
//            list = service.getKhachHang();
////            listls = service.getLichSu(gv.getIDKhachHang());
//            showDataTable(list);
////            showDataTableLichSu(listls);
//            dtmls = (DefaultTableModel) tblLichSuGiaoDich.getModel();
//            dtmls.setRowCount(0);
            //
            JOptionPane.showMessageDialog(this, "Xóa thành công!");
            return;

        }
        if (check == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Bạn đã chọn NO");
            return;
        } else {
            JOptionPane.showMessageDialog(this, "Bạn đã chọn CANCEL");
            return;
        }
/////////////////////
//        int check = JOptionPane.showConfirmDialog(this, "Bạn có muốn Xóa không?", "?", JOptionPane.YES_NO_CANCEL_OPTION);
//
//        if (check == JOptionPane.YES_OPTION) {
//            int row = tblStudent.getSelectedRow();
//            if (row < 0) {
//                JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để xóa.");
//                return;
//            }
//
//            KhachHangEntity gv = list.get(row);
//            boolean deleteSuccess = service.Xoa(gv.getIDKhachHang());
//
//            if (deleteSuccess) {
//                list = service.getKhachHang();
//                showDataTable(list);
//                JOptionPane.showMessageDialog(this, "Xóa thành công!");
//            } else {
//                JOptionPane.showMessageDialog(this, "Xóa thất bại!");
//            }
//        } else if (check == JOptionPane.NO_OPTION) {
//            JOptionPane.showMessageDialog(this, "Bạn đã chọn NO");
//        } else {
//            JOptionPane.showMessageDialog(this, "Bạn đã chọn CANCEL");
//        }

    }//GEN-LAST:event_roundedButton8ActionPerformed

    private void btnClearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClearMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClearMouseClicked

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        txtDiaChi.setText("");
        txtNgaySinh.setText("");
        txtSDT.setText("");
        txtTenKhachHang.setText("");
//        txtGioiTinh.setText("");
//        rdoNam.setSelected(true);
//        rdoNu.setSelected(true);
//        cbo.setSelectedItem(cboBac.getItemAt(0));
//
        CboGioiTinh.setSelectedItem(CboGioiTinh.getItemAt(0));
    }//GEN-LAST:event_btnClearActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void roundedButton10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roundedButton10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_roundedButton10MouseClicked

    private void roundedButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedButton10ActionPerformed
        int check = JOptionPane.showConfirmDialog(this, "Bạn có muốn Sửa không?", "?", JOptionPane.YES_NO_CANCEL_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            //
            int row = tblThongTinKhachHang.getSelectedRow();
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

    }//GEN-LAST:event_roundedButton10ActionPerformed

    private void tblThongTinKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThongTinKhachHangMouseClicked
        int row = tblThongTinKhachHang.getSelectedRow();
        detail(row);

//        KhachHangEntity ls = list.get(row);
//        dtmls = (DefaultTableModel) tblLichSuGiaoDich.getModel();
//        listls = service.getLichSu(ls.getIDKhachHang());
//        showDataTableLichSu(listls);
        KhachHangEntity ls = list.get(row);
        dtmls = (DefaultTableModel) tblLichSuGiaoDich.getModel();
        listlskh = service.getKhachHangLichSu(ls.getIDKhachHang());
        showDataTableLichSu(listlskh);

    }

    private void detail(int index) {
        KhachHangEntity gv = list.get(index);
        txtTenKhachHang.setText(gv.getTenKhachHang());
        txtSDT.setText(gv.getSoDienThoai());
//    txtGioiTinh.setText(gv.getGioiTinh());

        String gioiTinh = gv.getGioiTinh();
        if (gioiTinh.equals("0")) {
            CboGioiTinh.setSelectedItem("Nam");
        } else if (gioiTinh.equals("1")) {
            CboGioiTinh.setSelectedItem("Nữ");
        }

        txtNgaySinh.setText(String.valueOf(gv.getNgaySinh()));
        txtDiaChi.setText(gv.getDiaChi());


    }//GEN-LAST:event_tblThongTinKhachHangMouseClicked

    private void cboGioiTinh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboGioiTinh1ActionPerformed
        String chon = cboGioiTinh1.getSelectedItem().toString();

        if (chon.equals("Tất Cả")) {
            showDataTable(service.getKhachHang());
        } else if (chon.equals("Nam")) {
            showDataTable(service.getCBO("0"));
        } else if (chon.equals("Nữ")) {
            showDataTable(service.getCBO("1"));
        }
    }//GEN-LAST:event_cboGioiTinh1ActionPerformed

    private void tblLichSuGiaoDichMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLichSuGiaoDichMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblLichSuGiaoDichMouseClicked

    private void CboGioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboGioiTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CboGioiTinhActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private combobox.Combobox CboGioiTinh;
    private BotronPanel botronPanel1;
    private RoundedButton btnClear;
    private javax.swing.ButtonGroup buttonGroup1;
    private combobox.Combobox cboGioiTinh1;
    private com.raven.datechooser.DateChooser dateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private RoundedButton roundedButton10;
    private RoundedButton roundedButton6;
    private RoundedButton roundedButton7;
    private RoundedButton roundedButton8;
    private javax.swing.JTable tblLichSuGiaoDich;
    private javax.swing.JTable tblThongTinKhachHang;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTenKhachHang;
    // End of variables declaration//GEN-END:variables

}
