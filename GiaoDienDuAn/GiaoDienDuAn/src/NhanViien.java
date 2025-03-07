
import NhanVien.QRCode.WebCam;
import NhanVien.QRCode.WebCam.IDListener;
import NhanVien.entity.ChucVu;
import NhanVien.entity.NhanVien;
import NhanVien.service.ChucVuService;
import NhanVien.service.NhanVienService;
import NhanVien.viewmodel.NhanVienViewModel;
import com.raven.datechooser.DateChooser;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
/**
 *
 * @author ADMIN
 */
public class NhanViien extends javax.swing.JInternalFrame {

    private DefaultTableModel dtm = new DefaultTableModel();
    private NhanVienService service = new NhanVienService();
    public List<NhanVienViewModel> listNV = new ArrayList<>();

    private DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
    private DefaultComboBoxModel dcbm2 = new DefaultComboBoxModel();
    private ChucVuService service2 = new ChucVuService();
    private List<ChucVu> listCV = new ArrayList<>();

    private String qrData;

    /**
     * Creates new form Menu1
     */
    public NhanViien() {

        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        txtDiaChi.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtHoTen.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        dateNgaySinh.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtCCCD.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtEmail.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

        txtnhap.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtsdt.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

        for (int i = 0; i < tblNhanVien.getColumnCount(); i++) {
            tblNhanVien.getColumnModel().getColumn(i).setCellRenderer(new center());

        }

        

        dcbm = (DefaultComboBoxModel) cbbChucVu.getModel();
        dcbm2 = (DefaultComboBoxModel) cbbTimKiemChucVu.getModel();
        listCV = service2.getAll();
        showDataComboBox();
        
        dtm = (DefaultTableModel) tblNhanVien.getModel();
        listNV = service.getAll();
        showDataTable(listNV);

        btnQuetQR.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WebCam webCam = new WebCam();
                webCam.addIDListener(new IDListener() {
                    @Override
                    public void onIDReceived(String qrCodeString) {
                        try {
                            // Xử lý ID ở đây
                            resetForm();
                            NhanVien nv = parseCCCDInfo(qrCodeString);
                            txtHoTen.setText(nv.getTenNhanVien());
                            txtDiaChi.setText(nv.getDiaChi());
                            dateNgaySinh.setDate(nv.getNgaySinh());
                            txtCCCD.setText(nv.getcCCD());
                            if (nv.getGioiTinh().equals("Nam")) {
                                rdoNam.setSelected(true);
                            } else {
                                rdoNu.setSelected(true);
                            }
                            listNV = service.getAll();
                            for (NhanVienViewModel nvvm : listNV) {
                                if (nvvm.getcCCD().equals(txtCCCD.getText())) {
                                    listNV = service.getAllCCCD(nv.getcCCD());
                                    showDataTable(listNV);
                                    JOptionPane.showMessageDialog(NhanViien.this, "Nhân viên đã tồn tại");
                                }
                            }
                        } catch (ParseException ex) {
                            Logger.getLogger(NhanViien.class.getName()).log(Level.SEVERE, null, ex);
                        }

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
        // Set the custom header renderer
        JTableHeader header1 = tblNhanVien.getTableHeader();
        header1.setDefaultRenderer(new centerTablee());
    }

    private static NhanVien parseCCCDInfo(String qrCodeString) throws ParseException {
        // Tách thông tin từ chuỗi mã QR

// Tìm vị trí của dấu phân tách "||"
        int firstSeparatorIndex = qrCodeString.indexOf("||");

// Tách chuỗi thành hai phần: CCCD và phần còn lại chứa các thông tin khác
        String cccd = qrCodeString.substring(0, firstSeparatorIndex);
        String otherInfo = qrCodeString.substring(firstSeparatorIndex + 2);

// Tách thông tin từ phần còn lại sử dụng dấu "|"
        String[] qrInfo = otherInfo.split("\\|");

// Lấy thông tin từng phần trong mã QR
        String hoTen = qrInfo[0];
        String ngaySinh = qrInfo[1];
        String gioiTinh = qrInfo[2];
        String diaChi = qrInfo[3];

        DateFormat formatter = new SimpleDateFormat("ddMMyyyy");

        // Ép kiểu thành đối tượng Date
        Date date = formatter.parse(ngaySinh);

        // Tạo một đối tượng NhanVien và trả về
        NhanVien nv = new NhanVien(hoTen, date, diaChi, gioiTinh, cccd);
        return nv;
    }

    public void showDataTable(List<NhanVienViewModel> list) {
        dtm.setRowCount(0);

        int stt = 1;
        for (NhanVienViewModel nv : list) {
            String gioiTinh = "";
            if (nv.getGioiTinh().equals("1")) {
                gioiTinh = "Nam";
            } else if (nv.getGioiTinh().equals("0")) {
                gioiTinh = "Nữ";
            } else {
                gioiTinh = "Không xác định";
            }
            dtm.addRow(new Object[]{stt++, nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getsDt(), nv.getEmail(), nv.getDiaChi(), gioiTinh, nv.getNgaySinh(), nv.getChucVu(), service.trangThai(nv.isTrangThai())});
        }
    }

    public void showDataComboBox() {
        dcbm.removeAllElements();
dcbm2.removeAllElements();
        for (ChucVu cv : listCV) {
            dcbm.addElement(cv.getTenChucVu());
            dcbm2.addElement(cv.getTenChucVu());
        }
    }

    private String password; // Biến thành viên để lưu trữ password

    public String generatePassword() {
        int length = 10; // Độ dài chuỗi mong muốn
        this.password = generateRandomString(length);
        return password;
    }

    public NhanVien getFormData() {
        NhanVien nv = new NhanVien();
        String hoTen = txtHoTen.getText().trim();
        String sDT = txtsdt.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        String cCCD = txtCCCD.getText().trim();
        //String maNV=txtmaNV.getText().trim();
        String chucVu = (String) cbbChucVu.getSelectedItem();
        String email = txtEmail.getText().trim();
        Date ngaySinh = dateNgaySinh.getDate();
        ChucVu cv = service2.getID(chucVu);
        boolean nam = rdoNam.isSelected();

        Random maNV = new Random();

        // Now you can use the 'date' object as needed
        Date ngayHienTai = new Date();

        if (hoTen.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Họ tên không được trống");
            return null;
        }
        if (hoTen.matches(".*[^\\p{L}\\s].*")) {
            JOptionPane.showMessageDialog(this, "Họ tên không được chứa số và ký tự đặc biệt");
            return null;
        }
        if (hoTen.length() > 100) {
            JOptionPane.showMessageDialog(this, "Họ tên vượt quá số kí tự cho phép");
            return null;
        }
        if (!rdoNam.isSelected() && !rdoNu.isSelected()) {
            JOptionPane.showMessageDialog(this, "Chưa chọn giới tính");
            return null;
        }
        if (sDT.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được trống");
            return null;
        }
        if (sDT.matches("[0-9]+") == false) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được phép chứa chữ cái và ký tự đặc biệt");
            return null;
        }
        if (sDT.length() > 11) {
            JOptionPane.showMessageDialog(this, "Số điện thoại chỉ có tối đa 11 chữ số");
            return null;
        }
        if (diaChi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được trống");
            return null;
        }
        if (cCCD.isEmpty()) {
            JOptionPane.showMessageDialog(this, "CCCD không được trống");
            return null;
        }
        if (cCCD.matches("[0-9]+") == false) {
            JOptionPane.showMessageDialog(this, "CCCD không được phép chứa chữ cái và ký tự đặc biệt");
            return null;
        }
        if (cCCD.length() != 12) {
            JOptionPane.showMessageDialog(this, "CCCD phải có 12 chữ số");
            return null;
        }
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email không được trống");
            return null;
        }
        
        if (ngaySinh==null) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không được trống");
            return null;
        }
        if (ngaySinh.after(ngayHienTai)) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ");
            return null;
        }

//        Calendar cal = Calendar.getInstance();
//        cal.setTime(ngaySinh);
//        cal.add(Calendar.YEAR, 18);
//        Date eighteenYearsAgo = cal.getTime();
//
//// So sánh với ngày hiện tại
//        Date currentDate = new Date();
//        if (currentDate.before(eighteenYearsAgo)) { 
//            JOptionPane.showMessageDialog(this, "Người này chưa đủ 18 tuổi");
//            return null;
//        }
        nv.setMaNhanVien("NV" + String.valueOf(10000 + maNV.nextInt(90000)));
        nv.setTenNhanVien(hoTen);
        nv.setSoDienThoai(sDT);
        nv.setEmail(email);
        nv.setDiaChi(diaChi);
        nv.setNgaySinh(ngaySinh);
        nv.setIdChucVu(cv.getID());
        nv.setTenNhanVien(hoTen);
        nv.setGioiTinh(service.gioiTinh(nam));
        nv.setcCCD(cCCD);
        nv.setMatKhau(generatePassword());

        return nv;
    }

    public void resetForm() {
        txtDiaChi.setText("");
        txtHoTen.setText("");
        txtEmail.setText("");
        txtnhap.setText("");
        txtsdt.setText("");
        cbbChucVu.setSelectedIndex(0);
        buttonGroup1.clearSelection();
        dateNgaySinh.setDate(null);
        txtCCCD.setText("");
        lbMaNV.setText("");
    }

    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }

        return randomString.toString();
    }

// LichSu
    private static class center extends DefaultTableCellRenderer {

        public center() {
            setHorizontalAlignment(SwingConstants.CENTER);
        }
    }

    private static class centerTablee extends DefaultTableCellRenderer {

        public centerTablee() {
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        botronPanel1 = new BotronPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnQuetQR = new RoundedButton();
        roundedButton2 = new RoundedButton();
        roundedButton3 = new RoundedButton();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        roundedButton4 = new RoundedButton();
        cbbChucVu = new combobox.Combobox();
        txtsdt = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtCCCD = new javax.swing.JTextField();
        btnThayDoiTrangThai = new RoundedButton();
        roundedButton6 = new RoundedButton();
        roundedButton7 = new RoundedButton();
        jLabel17 = new javax.swing.JLabel();
        lbMaNV = new javax.swing.JLabel();
        dateNgaySinh = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        botronPanel2 = new BotronPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        rdoTatCa = new javax.swing.JRadioButton();
        rdoDaLam = new javax.swing.JRadioButton();
        rdoDaNghi = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        txtnhap = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel16 = new javax.swing.JLabel();
        cbbTimKiemChucVu = new combobox.Combobox();
        jLabel19 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1230, 710));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("UVN Chim Bien", 1, 18)); // NOI18N
        jLabel1.setText("Nhân Viên");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel3.setText("Thiết Lập Thông Tin Nhân Viên");

        botronPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Họ Tên");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Giới Tính");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("SĐT");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Địa Chỉ");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("CCCD");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Chức Vụ");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Ngày Sinh");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Email");

        btnQuetQR.setBackground(new java.awt.Color(0, 0, 0));
        btnQuetQR.setForeground(new java.awt.Color(255, 255, 255));
        btnQuetQR.setText("Quét QR CCCD");

        roundedButton2.setBackground(new java.awt.Color(0, 0, 0));
        roundedButton2.setForeground(new java.awt.Color(255, 255, 255));
        roundedButton2.setText("Sửa");
        roundedButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedButton2ActionPerformed(evt);
            }
        });

        roundedButton3.setBackground(new java.awt.Color(0, 0, 0));
        roundedButton3.setForeground(new java.awt.Color(255, 255, 255));
        roundedButton3.setText("Mới");
        roundedButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedButton3ActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        roundedButton4.setBackground(new java.awt.Color(0, 0, 0));
        roundedButton4.setForeground(new java.awt.Color(255, 255, 255));
        roundedButton4.setText("Thêm");
        roundedButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedButton4ActionPerformed(evt);
            }
        });

        cbbChucVu.setBackground(new java.awt.Color(204, 204, 204));
        cbbChucVu.setLabeText("");
        cbbChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbChucVuActionPerformed(evt);
            }
        });

        txtsdt.setBackground(new java.awt.Color(204, 204, 204));

        txtEmail.setBackground(new java.awt.Color(204, 204, 204));

        txtHoTen.setBackground(new java.awt.Color(204, 204, 204));

        txtDiaChi.setBackground(new java.awt.Color(204, 204, 204));

        txtCCCD.setBackground(new java.awt.Color(204, 204, 204));

        btnThayDoiTrangThai.setBackground(new java.awt.Color(0, 0, 0));
        btnThayDoiTrangThai.setForeground(new java.awt.Color(255, 255, 255));
        btnThayDoiTrangThai.setText("Nghỉ việc");
        btnThayDoiTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThayDoiTrangThaiActionPerformed(evt);
            }
        });

        roundedButton6.setBackground(new java.awt.Color(0, 0, 0));
        roundedButton6.setForeground(new java.awt.Color(255, 255, 255));
        roundedButton6.setText("Nhập excel");
        roundedButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedButton6ActionPerformed(evt);
            }
        });

        roundedButton7.setBackground(new java.awt.Color(0, 0, 0));
        roundedButton7.setForeground(new java.awt.Color(255, 255, 255));
        roundedButton7.setText("Xuất excel");
        roundedButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedButton7ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Mã nhân viên");

        dateNgaySinh.setBackground(new java.awt.Color(204, 204, 204));
        dateNgaySinh.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout botronPanel1Layout = new javax.swing.GroupLayout(botronPanel1);
        botronPanel1.setLayout(botronPanel1Layout);
        botronPanel1Layout.setHorizontalGroup(
            botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botronPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(botronPanel1Layout.createSequentialGroup()
                        .addGroup(botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5))
                        .addGap(33, 33, 33)
                        .addGroup(botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(botronPanel1Layout.createSequentialGroup()
                                .addComponent(rdoNam)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoNu))
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(botronPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel13))
                    .addComponent(jLabel12)
                    .addComponent(jLabel17))
                .addGroup(botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(botronPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(lbMaNV))
                    .addGroup(botronPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtEmail)
                            .addComponent(cbbChucVu, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                            .addComponent(dateNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(155, 155, 155)
                        .addGroup(botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnQuetQR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(roundedButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(roundedButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(274, 274, 274))
            .addGroup(botronPanel1Layout.createSequentialGroup()
                .addGap(390, 390, 390)
                .addComponent(roundedButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(roundedButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(roundedButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnThayDoiTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        botronPanel1Layout.setVerticalGroup(
            botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botronPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(botronPanel1Layout.createSequentialGroup()
                        .addGroup(botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(lbMaNV))
                        .addGap(27, 27, 27)
                        .addGroup(botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(botronPanel1Layout.createSequentialGroup()
                                .addComponent(cbbChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addGroup(botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(dateNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(botronPanel1Layout.createSequentialGroup()
                        .addGroup(botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(rdoNam)
                            .addComponent(rdoNu))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(roundedButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(roundedButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnThayDoiTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(roundedButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
            .addGroup(botronPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(btnQuetQR, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundedButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundedButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel4.setText("Danh Sách Nhân Viên");

        botronPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Nhập MãNV,Tên, Email, SĐT, Địa Chỉ, để tìm kiếm.");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Trạng Thái");

        buttonGroup2.add(rdoTatCa);
        rdoTatCa.setText("Tất Cả");
        rdoTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTatCaActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoDaLam);
        rdoDaLam.setText("Đã làm");
        rdoDaLam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDaLamActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoDaNghi);
        rdoDaNghi.setText("Đã Nghỉ");
        rdoDaNghi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDaNghiActionPerformed(evt);
            }
        });

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã NV", "Tên NV", "SĐT", "Email", "Địa Chỉ", "Giới Tính", "Ngày Sinh", "Chức Vụ", "Trạng Thái"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        txtnhap.setBackground(new java.awt.Color(204, 204, 204));
        txtnhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnhapActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton1);
        jRadioButton1.setText("Nam");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton2);
        jRadioButton2.setText("Nữ");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Giới tính");

        cbbTimKiemChucVu.setBackground(new java.awt.Color(204, 204, 204));
        cbbTimKiemChucVu.setLabeText("");
        cbbTimKiemChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTimKiemChucVuActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Tất cả");

        javax.swing.GroupLayout botronPanel2Layout = new javax.swing.GroupLayout(botronPanel2);
        botronPanel2.setLayout(botronPanel2Layout);
        botronPanel2Layout.setHorizontalGroup(
            botronPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botronPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(botronPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(txtnhap, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(botronPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19)
                    .addComponent(rdoTatCa))
                .addGroup(botronPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(botronPanel2Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(rdoDaLam))
                    .addGroup(botronPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdoDaNghi)
                .addGap(52, 52, 52)
                .addGroup(botronPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(botronPanel2Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton2))
                    .addComponent(jLabel16))
                .addGap(42, 42, 42)
                .addComponent(cbbTimKiemChucVu, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                .addGap(296, 296, 296))
            .addGroup(botronPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(botronPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1164, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        botronPanel2Layout.setVerticalGroup(
            botronPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botronPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(botronPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(botronPanel2Layout.createSequentialGroup()
                        .addGroup(botronPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(botronPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14)
                                .addComponent(jLabel19)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(botronPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoDaLam)
                            .addComponent(rdoDaNghi)
                            .addComponent(txtnhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoTatCa)))
                    .addGroup(botronPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(botronPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2)
                            .addComponent(cbbTimKiemChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(botronPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel16)
                            .addGap(30, 30, 30))))
                .addContainerGap(184, Short.MAX_VALUE))
            .addGroup(botronPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(botronPanel2Layout.createSequentialGroup()
                    .addGap(73, 73, 73)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(28, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(452, 452, 452)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(botronPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botronPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(botronPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botronPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void roundedButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedButton2ActionPerformed
        // TODO add your handling code here:
        int row = tblNhanVien.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn nhân viên");
            return;
        } else {
            if (getFormData() != null) {
                NhanVienViewModel nv = listNV.get(row);
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không");
                if (confirm == JOptionPane.YES_OPTION) {

                    service.update(getFormData(), nv.getIdNhanVien());
                    listNV = service.getAll();
                    showDataTable(listNV);
                    JOptionPane.showMessageDialog(this, "Sửa thành công");
                }
            }
        }
    }//GEN-LAST:event_roundedButton2ActionPerformed

    private void roundedButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedButton3ActionPerformed
        // TODO add your handling code he
        listNV = service.getAll();
        showDataTable(listNV);
        resetForm();
    }//GEN-LAST:event_roundedButton3ActionPerformed

    private void roundedButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedButton4ActionPerformed
        // TODO add your handling code here:
        if (getFormData() != null) {
            listNV = service.getAll();
            for (NhanVienViewModel nvvm : listNV) {
                if (nvvm.getcCCD().equals(txtCCCD.getText())) {
                    JOptionPane.showMessageDialog(this, "CCCD đã tồn tại");
                    return;
                }
                if (nvvm.getsDt().equals(txtsdt.getText())) {
                    JOptionPane.showMessageDialog(this, "SDT đã tồn tại");
                    return;
                }
                if (nvvm.getEmail().equals(txtEmail.getText())) {
                    JOptionPane.showMessageDialog(this, "Email đã tồn tại");
                    return;
                }
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không");
            if (confirm == JOptionPane.YES_OPTION) {
                service.add(getFormData());
                listNV = service.getAll();
                showDataTable(listNV);
                JOptionPane.showMessageDialog(this, "Thêm thành công");

                String toEmail = txtEmail.getText();
//                String fromEmail = "huytath01523@fpt.edu.vn";
//                String fromEmailPassword = "o t l w w t g h q l u p b m z p";//mat khau ung dung tai khoan google(FromEmail) cua ban
                String subject = "Thông báo nhận hồ sơ ứng tuyển vị trí Bán hàng B-Purse";

                
                String fromEmail = "kiennguyenvann1@gmail.com";
                String fromEmailPassword = "fnod yqew bmcd yuxl";
                
                
                Properties properties = new Properties();
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.port", "587");

                Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromEmail, fromEmailPassword);
                    }
                });
                try {
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(fromEmail));
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
                    message.setSubject(subject);
                    message.setText("Xin chào,\n"
                            + "\n"
                            + "Chúng tôi xin thông báo rằng bạn đã được ứng tuyển vào vị trí "+cbbChucVu.getSelectedItem()+".Dưới đây là thông tin đăng nhập cho phần mềm quản lý bán hàng B-Purse:\n"
                            + "\n"
                            + "Tên đăng nhập: " + toEmail + "\n"
                            + "Mật khẩu: " + password + "\n"
                            + "\n"
                            + "Chúng tôi sẽ liên hệ với bạn trong thời gian sớm nhất.\n"
                            + "\n"
                            + "Trân trọng!\n"
                    );
//                    
                    Transport.send(message);
                } catch (Exception ex) {
                    System.out.println("" + ex);
                }
            }
        }
    }//GEN-LAST:event_roundedButton4ActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        // TODO add your handling code here:
        int row = tblNhanVien.getSelectedRow();
        NhanVienViewModel nv = listNV.get(row);
        txtDiaChi.setText(nv.getDiaChi());
        txtHoTen.setText(nv.getTenNhanVien());
        txtEmail.setText(nv.getEmail());
        txtsdt.setText(nv.getsDt());
        dateNgaySinh.setDate(nv.getNgaySinh());
        cbbChucVu.setSelectedItem(nv.getChucVu());
        if (nv.getGioiTinh().equals("1")) {
            rdoNam.setSelected(true);
        } else if (nv.getGioiTinh().equals("0")) {
            rdoNu.setSelected(true);
        }

        txtCCCD.setText(nv.getcCCD());
        lbMaNV.setText(nv.getMaNhanVien());

        if (nv.isTrangThai() == true) {
            btnThayDoiTrangThai.setText("Nghỉ việc");
        } else {
            btnThayDoiTrangThai.setText("Đi làm lại");
        }
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void cbbChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbChucVuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbChucVuActionPerformed

    private void rdoTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTatCaActionPerformed
        // TODO add your handling code here:
        listNV = service.getAll();
        showDataTable(listNV);

    }//GEN-LAST:event_rdoTatCaActionPerformed

    private void rdoDaLamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDaLamActionPerformed
        // TODO add your handling code here:
        listNV = service.getAllTrangThai(1);
        showDataTable(listNV);

    }//GEN-LAST:event_rdoDaLamActionPerformed

    private void rdoDaNghiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDaNghiActionPerformed
        // TODO add your handling code here:
        listNV = service.getAllTrangThai(0);
        showDataTable(listNV);

    }//GEN-LAST:event_rdoDaNghiActionPerformed

    private void txtnhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnhapActionPerformed
        // TODO add your handling code here:
        listNV = service.search(txtnhap.getText());
        showDataTable(listNV);
    }//GEN-LAST:event_txtnhapActionPerformed

    private void btnThayDoiTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThayDoiTrangThaiActionPerformed
        // TODO add your handling code here:
        int row = tblNhanVien.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn nhân viên");
        } else {
            NhanVienViewModel nv = listNV.get(row);
            int trangThai;
            String thongBao = nv.isTrangThai() == true ? "Bạn có muốn nhân viên nghỉ việc không" : "Bạn có muốn nhân viên đi làm lại không";
            int confirm = JOptionPane.showConfirmDialog(this, thongBao);
            if (nv.isTrangThai() == true) {
                trangThai = 0;
            } else {
                trangThai = 1;
            }

            if (confirm == JOptionPane.YES_OPTION) {
                service.delete(nv.getIdNhanVien(), trangThai);
                listNV = service.getAll();
                showDataTable(listNV);
                JOptionPane.showMessageDialog(this, "Thay đổi trạng thái thành công");
            }
        }
    }//GEN-LAST:event_btnThayDoiTrangThaiActionPerformed

    private void roundedButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedButton6ActionPerformed
        // TODO add your handling code here:
        File excelFile;
        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;
        XSSFWorkbook excelJTableImport = null;
        int rowCount = dtm.getRowCount() + 1;

        String defaultCurrentDirectoryPath = ".\\GiaoDienDuAn\\excelNV";
        JFileChooser excelFileChooser = new JFileChooser(defaultCurrentDirectoryPath);
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
        excelFileChooser.setFileFilter(fnef);

        excelFileChooser.setDialogTitle("Select Excel File");
        int excelChooser = excelFileChooser.showOpenDialog(null);

        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {
                excelFile = excelFileChooser.getSelectedFile();

                excelFIS = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(excelFIS);
                excelJTableImport = new XSSFWorkbook(excelBIS);
                XSSFSheet excelSheet = excelJTableImport.getSheetAt(0);
                for (int row = 1; row <= excelSheet.getLastRowNum(); row++) {
                    XSSFRow excelRow = excelSheet.getRow(row);

                    XSSFCell excelMaNV = excelRow.getCell(1);
                    XSSFCell excelTenNV = excelRow.getCell(2);
                    XSSFCell excelSDT = excelRow.getCell(3);
                    XSSFCell excelEmail = excelRow.getCell(4);
                    XSSFCell excelDiaChi = excelRow.getCell(5);
                    XSSFCell excelGioiTinh = excelRow.getCell(6);
                    XSSFCell excelNgaySinh = excelRow.getCell(7);
                    XSSFCell excelChucVu = excelRow.getCell(8);
                    XSSFCell excelTrangThai = excelRow.getCell(9);
                    XSSFCell excelCCCD = excelRow.getCell(10);
                    Date date = null;
                    try {
                        // Định dạng ngày tháng trong ô (ví dụ: dd/MM/yyyy)
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                        // Lấy giá trị của ô dưới dạng chuỗi
                        String cellValue = excelNgaySinh.getStringCellValue();

                        // Chuyển đổi chuỗi thành kiểu Date
                        date = sdf.parse(cellValue);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    String gt = "";
                    if (excelGioiTinh.getStringCellValue().equals("Nam")) {
                        gt = "1";
                    } else if (excelGioiTinh.getStringCellValue().equals("Nữ")) {
                        gt = "0";
                    } else {
                        gt = "Không xác định";
                    }

                    boolean trangThai;
                    if (excelTrangThai.getStringCellValue().equals("Đang làm")) {
                        trangThai = true;
                    } else {
                        trangThai = false;
                    }

                    NhanVienViewModel nv = new NhanVienViewModel();
                    nv.setMaNhanVien(String.valueOf(excelMaNV));
                    nv.setTenNhanVien(String.valueOf(excelTenNV));
                    nv.setsDt(String.valueOf(excelSDT));
                    nv.setEmail(String.valueOf(excelEmail));
                    nv.setDiaChi(String.valueOf(excelDiaChi));
                    nv.setGioiTinh(gt);
                    nv.setNgaySinh(date);
                    nv.setChucVu(String.valueOf(excelChucVu));
                    nv.setTrangThai(trangThai);
                    nv.setcCCD(String.valueOf(excelCCCD));
                    listNV.add(nv);
                    //System.out.println(excelName);
                    showDataTable(listNV);
//                    for (int column = 0; column < excelRow.getLastCellNum(); column++) {
//                        XSSFCell excelCell=excelRow.getCell(column);
//                        
//                        System.out.println(excelCell.getStringCellValue());
//                    }
                }
                JOptionPane.showMessageDialog(null, "Nhập excel Thành công");
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } finally {
                try {
                    if (excelFIS != null) {
                        excelFIS.close();
                    }
                    if (excelBIS != null) {
                        excelBIS.close();
                    }
                    if (excelJTableImport != null) {
                        excelJTableImport.close();
                    }
                } catch (IOException iOException) {
                    JOptionPane.showMessageDialog(null, iOException.getMessage());
                }
            }
        }
    }//GEN-LAST:event_roundedButton6ActionPerformed

    private void roundedButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedButton7ActionPerformed
        // TODO add your handling code here:

        FileOutputStream excelFOU = null;
        BufferedOutputStream excelBOU = null;
        XSSFWorkbook excelJTableExporter = null;

        JFileChooser excelFileChooser = new JFileChooser(".\\GiaoDienDuAn\\excelNV\\");
        excelFileChooser.setDialogTitle("Save as");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
        excelFileChooser.setFileFilter(fnef);

        int excelChooser = excelFileChooser.showSaveDialog(null);

        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {
                excelJTableExporter = new XSSFWorkbook();
                XSSFSheet excelSheet = excelJTableExporter.createSheet("JTable Sheet");

                // Thêm cột mới vào hàng tiêu đề
                XSSFRow headerRow = excelSheet.createRow(0);
                for (int j = 0; j < dtm.getColumnCount(); j++) {
                    XSSFCell cell = headerRow.createCell(j);
                    cell.setCellValue(dtm.getColumnName(j));
                }

                // Thêm cột CCCD vào vị trí thứ 2
                XSSFCell cccdCell = headerRow.createCell(dtm.getColumnCount());
                cccdCell.setCellValue("CCCD");

                // Tạo dữ liệu cho các cột
                for (int i = 0; i < dtm.getRowCount(); i++) {
                    XSSFRow excelRow = excelSheet.createRow(i + 1);
                    for (int j = 0; j < dtm.getColumnCount(); j++) {
                        XSSFCell excelCell = excelRow.createCell(j);
                        excelCell.setCellValue(dtm.getValueAt(i, j).toString());
                    }
                    // Thêm dữ liệu cho cột CCCD
                    XSSFCell cccdExcelCell = excelRow.createCell(dtm.getColumnCount());
                    NhanVienViewModel nv = listNV.get(i); // Lấy phần tử tương ứng từ danh sách
                    cccdExcelCell.setCellValue(nv.getcCCD());
                }

                // Lưu tệp Excel
                excelFOU = new FileOutputStream(excelFileChooser.getSelectedFile() + ".xlsx");
                excelBOU = new BufferedOutputStream(excelFOU);
                excelJTableExporter.write(excelBOU);

                JOptionPane.showMessageDialog(null, "Xuất excel thành công");

            } catch (FileNotFoundException ex) {
                Logger.getLogger(NhanViien.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(NhanViien.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    // Đóng luồng
                    if (excelBOU != null) {
                        excelBOU.close();
                    }
                    if (excelFOU != null) {
                        excelFOU.close();
                    }
                    if (excelJTableExporter != null) {
                        excelJTableExporter.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
//        
    }//GEN-LAST:event_roundedButton7ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        listNV = service.getAllGioiTinh("1");
        showDataTable(listNV);

    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        listNV = service.getAllGioiTinh("0");
        showDataTable(listNV);
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void cbbTimKiemChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTimKiemChucVuActionPerformed
        // TODO add your handling code here:
        ChucVu cv=service2.getID((String) cbbTimKiemChucVu.getSelectedItem());
        
        listNV=service.getAllChucVu(cv.getID());
        showDataTable(listNV);
    }//GEN-LAST:event_cbbTimKiemChucVuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private BotronPanel botronPanel1;
    private BotronPanel botronPanel2;
    private RoundedButton btnQuetQR;
    private RoundedButton btnThayDoiTrangThai;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private combobox.Combobox cbbChucVu;
    private combobox.Combobox cbbTimKiemChucVu;
    private com.toedter.calendar.JDateChooser dateNgaySinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbMaNV;
    private javax.swing.JRadioButton rdoDaLam;
    private javax.swing.JRadioButton rdoDaNghi;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JRadioButton rdoTatCa;
    private RoundedButton roundedButton2;
    private RoundedButton roundedButton3;
    private RoundedButton roundedButton4;
    private RoundedButton roundedButton6;
    private RoundedButton roundedButton7;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtnhap;
    private javax.swing.JTextField txtsdt;
    // End of variables declaration//GEN-END:variables
}
