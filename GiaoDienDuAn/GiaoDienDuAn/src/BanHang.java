
import HoaDonChiTiet.HoaDonChiTiet;
import HoaDonChiTiet.HoaDonChiTietViewModel;
import bpurse.qlts.entity.ChiTietSanPham;
import com.lowagie.text.Element;
import com.lowagie.text.Row;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.GiamGiaEntity;
import org.apache.poi.ss.formula.ptg.TblPtg;
import service.BanHangService;
import service.GiamGiaService;
import service.GioHangService;
import service.HoaDonServiceBH;
import viewmodel.BanHangViewModel;
import viewmodel.GiamGiaViewModel;
import viewmodel.GioHangViewModel;
import viewmodel.HoaDonViewModel;

public class BanHang extends javax.swing.JInternalFrame {

    private int constHeight = 0;

    private List<GiamGiaViewModel> listGG = new ArrayList<>();
    GiamGiaService ggs = new GiamGiaService();

    private List<BanHangViewModel> lists = new ArrayList<>();
    private BanHangService bhs = new BanHangService();
    private DefaultTableModel dtm = new DefaultTableModel();
    private DecimalFormat format = new DecimalFormat("");

    private List<HoaDonViewModel> listHD = new ArrayList<>();
    private HoaDonServiceBH hds = new HoaDonServiceBH();
    private DefaultTableModel dtmHD = new DefaultTableModel();

    private List<GioHangViewModel> listGH = new ArrayList<>();
    private GioHangService ghs = new GioHangService();
    private DefaultTableModel dtmGH = new DefaultTableModel();

    public BanHang() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        //code 1 dong
        txtTim.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtMKH.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtTKH.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtMHD.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtCK.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtMNV.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtTenKH.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtTienTL.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

        txtTM.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

        txtNT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

        txtTToan.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

        for (int i = 0; i < tblHoaDon.getColumnCount(); i++) {
            tblHoaDon.getColumnModel().getColumn(i).setCellRenderer(new center());
        }

        setDataCboHTTT();
        listGG = ggs.getAll1();
        createComboBox(listGG);

        JTableHeader header1 = tblHoaDon.getTableHeader();
        header1.setDefaultRenderer(new centerTable());

        for (int i = 0; i < tblGioHang.getColumnCount(); i++) {
            tblGioHang.getColumnModel().getColumn(i).setCellRenderer(new centerr());
        }

        JTableHeader header2 = tblGioHang.getTableHeader();
        header2.setDefaultRenderer(new centerTablee());

        for (int i = 0; i < tblSanPham.getColumnCount(); i++) {
            tblSanPham.getColumnModel().getColumn(i).setCellRenderer(new centerr());
        }

        btnQuetQR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WebCam webCam = new WebCam();
                webCam.addIDListener(new WebCam.IDListener() {
                    @Override
                    public void onIDReceived(String idSP) {
                        // Xử lý ID ở đây
                        lists = bhs.getAll1(idSP);
                        for (BanHangViewModel sp : lists) {
                            JOptionPane.showMessageDialog(rootPane, "Bạn đã chọn " + sp.getTenSP() + " giá " + format.format(sp.getGiaBan()));
                           
                            
                            if (txtIDHD.getText().isEmpty()) {
                                JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn 1 hoá dơn");
                                return;
                            }

                            int soLuong = nhapSoLuong();
                            if (soLuong <= 0) {
                                JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn số lớn hơn 0 hoặc chọn OK");
                                return;

                            }
                            if (sp.getSoLuong() - soLuong < 0) {
                                JOptionPane.showMessageDialog(rootPane, "Quá giới hạn số lượng hàng");
                                return;
                            }

                            for (GioHangViewModel gioHangSP : listGH) {
                                if (gioHangSP.getTenSP().equals(sp.getTenSP())) {
                                    // Sản phẩm đã tồn tại trong giỏ hàng, giảm số lượng đi 1
                                    bhs.UpdateTrungSP(txtIDHD.getText(), sp.getId(), sp.getSoLuong() - soLuong, soLuong + gioHangSP.getSoLuong());
                                    // Giảm đi 1 số lượng
                                    lists = bhs.getAll();
                                    dtm = (DefaultTableModel) tblSanPham.getModel();
                                    showData(lists);

                                    listHD = hds.getHD();
                                    dtmHD = (DefaultTableModel) tblHoaDon.getModel();
                                    showDataHD(listHD);

                                    dtmGH = (DefaultTableModel) tblGioHang.getModel();
                                    listGH = ghs.getGioHang(txtIDHD.getText());
                                    showDataTableGH(listGH);
                                    return;

                                }
                                // Nếu sản phẩm không tồn tại trong giỏ hàng, không cần thực hiện gì cả
                            }

                            bhs.AddSPGH(getFormHDCT(sp.getId(), txtIDHD.getText(), soLuong, sp.getGiaBan()),
                                    getFormCTSP(sp.getSoLuong() - soLuong), txtIDHD.getText(), sp.getId(), sp.getSoLuong() - soLuong, soLuong);

                            lists = bhs.getAll();
                            dtm = (DefaultTableModel) tblSanPham.getModel();
                            showData(lists);

                            listHD = hds.getHD();
                            dtmHD = (DefaultTableModel) tblHoaDon.getModel();
                            showDataHD(listHD);

                            dtmGH = (DefaultTableModel) tblGioHang.getModel();
                            listGH = ghs.getGioHang(txtIDHD.getText());
                            showDataTableGH(listGH);
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
        

        txtCK.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                BigDecimal totalAmount = calculateTotalAmountFromGioHang();
                BigDecimal tienTM = BigDecimal.ZERO;
                BigDecimal tienCK = BigDecimal.ZERO;

                BigDecimal tongTien = BigDecimal.ZERO;
                if (txtCK.getText().trim().isEmpty() && txtTM.getText().trim().isEmpty()) {
                    tienCK = BigDecimal.ZERO;
                    tienTM = BigDecimal.ZERO;

                    lblTongTien.setText(totalAmount + "");
                    if (tongTien.compareTo(BigDecimal.valueOf(0)) < 0) {
                        txtTienTL.setText(format.format(totalAmount.multiply(BigDecimal.valueOf(-1))));
                    } else {
                        txtTienTL.setText(0 + "");
                    }

                    return;
                }

                if (txtTM.getText().trim().isEmpty()) {
                    try {
                        tienTM = BigDecimal.ZERO;
                        tienCK = BigDecimal.valueOf(Long.parseLong(txtCK.getText().trim()));

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập số");
                        return;
                    }
                    tongTien = totalAmount.subtract(tienCK);
                    lblTongTien.setText(tongTien + "");
                    if (tongTien.compareTo(BigDecimal.valueOf(0)) < 0) {
                        txtTienTL.setText(format.format(tongTien.multiply(BigDecimal.valueOf(-1))));
                    } else {
                        txtTienTL.setText(0 + "");
                    }

                }
                if (txtCK.getText().trim().isEmpty()) {
                    try {
                        tienCK = BigDecimal.ZERO;
                        tienTM = BigDecimal.valueOf(Long.parseLong(txtTM.getText().trim()));

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập số");
                        return;
                    }
                    tongTien = totalAmount.subtract(tienTM);
                    lblTongTien.setText(tongTien + "");

                    if (tongTien.compareTo(BigDecimal.valueOf(0)) < 0) {
                        txtTienTL.setText(format.format(tongTien.multiply(BigDecimal.valueOf(-1))));
                    } else {
                        txtTienTL.setText(0 + "");
                    }

                } else if (!txtCK.getText().trim().isEmpty() && !txtTM.getText().trim().isEmpty()) {
                    try {
                        tienCK = BigDecimal.valueOf(Long.parseLong(txtCK.getText().trim()));
                        tienTM = BigDecimal.valueOf(Long.parseLong(txtTM.getText().trim()));

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập số");
                        return;

                    }
                    BigDecimal tienTra;
                    tienTra = tienCK.add(tienTM);

                    tongTien = totalAmount.subtract(tienTra);

                    lblTongTien.setText(tongTien + "");
                    if (tongTien.compareTo(BigDecimal.valueOf(0)) < 0) {
                        txtTienTL.setText(format.format(tongTien.multiply(BigDecimal.valueOf(-1))));
                    } else {
                        txtTienTL.setText(0 + "");
                    }

                }

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                BigDecimal totalAmount = calculateTotalAmountFromGioHang();
                BigDecimal tienTM = BigDecimal.ZERO;
                BigDecimal tienCK = BigDecimal.ZERO;

                BigDecimal tongTien = BigDecimal.ZERO;
                if (txtCK.getText().trim().isEmpty() && txtTM.getText().trim().isEmpty()) {
                    tienCK = BigDecimal.ZERO;
                    tienTM = BigDecimal.ZERO;

                    lblTongTien.setText(totalAmount + "");
                    if (tongTien.compareTo(BigDecimal.valueOf(0)) < 0) {
                        txtTienTL.setText(format.format(totalAmount.multiply(BigDecimal.valueOf(-1))));
                    } else {
                        txtTienTL.setText(0 + "");
                    }

                    return;
                }

                if (txtTM.getText().trim().isEmpty()) {
                    try {
                        tienTM = BigDecimal.ZERO;
                        tienCK = BigDecimal.valueOf(Long.parseLong(txtCK.getText().trim()));

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập số");
                        return;
                    }
                    tongTien = totalAmount.subtract(tienCK);
                    lblTongTien.setText(tongTien + "");
                    if (tongTien.compareTo(BigDecimal.valueOf(0)) < 0) {
                        txtTienTL.setText(format.format(tongTien.multiply(BigDecimal.valueOf(-1))));
                    } else {
                        txtTienTL.setText(0 + "");
                    }

                }
                if (txtCK.getText().trim().isEmpty()) {
                    try {
                        tienCK = BigDecimal.ZERO;
                        tienTM = BigDecimal.valueOf(Long.parseLong(txtTM.getText().trim()));

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập số");
                        return;
                    }
                    tongTien = totalAmount.subtract(tienTM);
                    lblTongTien.setText(tongTien + "");

                    if (tongTien.compareTo(BigDecimal.valueOf(0)) < 0) {
                        txtTienTL.setText(format.format(tongTien.multiply(BigDecimal.valueOf(-1))));
                    } else {
                        txtTienTL.setText(0 + "");
                    }

                } else if (!txtCK.getText().trim().isEmpty() && !txtTM.getText().trim().isEmpty()) {
                    try {
                        tienCK = BigDecimal.valueOf(Long.parseLong(txtCK.getText().trim()));
                        tienTM = BigDecimal.valueOf(Long.parseLong(txtTM.getText().trim()));

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập số");
                        return;

                    }
                    BigDecimal tienTra;
                    tienTra = tienCK.add(tienTM);

                    tongTien = totalAmount.subtract(tienTra);

                    lblTongTien.setText(tongTien + "");
                    if (tongTien.compareTo(BigDecimal.valueOf(0)) < 0) {
                        txtTienTL.setText(format.format(tongTien.multiply(BigDecimal.valueOf(-1))));
                    } else {
                        txtTienTL.setText(0 + "");
                    }

                }

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                BigDecimal totalAmount = calculateTotalAmountFromGioHang();
                BigDecimal tienTM = BigDecimal.ZERO;
                BigDecimal tienCK = BigDecimal.ZERO;

                BigDecimal tongTien = BigDecimal.ZERO;
                if (txtCK.getText().trim().isEmpty() && txtTM.getText().trim().isEmpty()) {
                    tienCK = BigDecimal.ZERO;
                    tienTM = BigDecimal.ZERO;

                    lblTongTien.setText(totalAmount + "");
                    if (tongTien.compareTo(BigDecimal.valueOf(0)) < 0) {
                        txtTienTL.setText(format.format(totalAmount.multiply(BigDecimal.valueOf(-1))));
                    } else {
                        txtTienTL.setText(0 + "");
                    }

                    return;
                }

                if (txtTM.getText().trim().isEmpty()) {
                    try {
                        tienTM = BigDecimal.ZERO;
                        tienCK = BigDecimal.valueOf(Long.parseLong(txtCK.getText().trim()));

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập số");
                        return;
                    }
                    tongTien = totalAmount.subtract(tienCK);
                    lblTongTien.setText(tongTien + "");
                    if (tongTien.compareTo(BigDecimal.valueOf(0)) < 0) {
                        txtTienTL.setText(format.format(tongTien.multiply(BigDecimal.valueOf(-1))));
                    } else {
                        txtTienTL.setText(0 + "");
                    }

                }
                if (txtCK.getText().trim().isEmpty()) {
                    try {
                        tienCK = BigDecimal.ZERO;
                        tienTM = BigDecimal.valueOf(Long.parseLong(txtTM.getText().trim()));

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập số");
                        return;
                    }
                    tongTien = totalAmount.subtract(tienTM);
                    lblTongTien.setText(tongTien + "");

                    if (tongTien.compareTo(BigDecimal.valueOf(0)) < 0) {
                        txtTienTL.setText(format.format(tongTien.multiply(BigDecimal.valueOf(-1))));
                    } else {
                        txtTienTL.setText(0 + "");
                    }

                } else if (!txtCK.getText().trim().isEmpty() && !txtTM.getText().trim().isEmpty()) {
                    try {
                        tienCK = BigDecimal.valueOf(Long.parseLong(txtCK.getText().trim()));
                        tienTM = BigDecimal.valueOf(Long.parseLong(txtTM.getText().trim()));

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập số");
                        return;

                    }
                    BigDecimal tienTra;
                    tienTra = tienCK.add(tienTM);

                    tongTien = totalAmount.subtract(tienTra);

                    lblTongTien.setText(tongTien + "");
                    if (tongTien.compareTo(BigDecimal.valueOf(0)) < 0) {
                        txtTienTL.setText(format.format(tongTien.multiply(BigDecimal.valueOf(-1))));
                    } else {
                        txtTienTL.setText(0 + "");
                    }

                }
            }

        });

        txtTM.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                BigDecimal totalAmount = calculateTotalAmountFromGioHang();
                BigDecimal tienTM = BigDecimal.ZERO;
                BigDecimal tienCK = BigDecimal.ZERO;

                BigDecimal tongTien = BigDecimal.ZERO;
                if (txtCK.getText().trim().isEmpty() && txtTM.getText().trim().isEmpty()) {
                    tienCK = BigDecimal.ZERO;
                    tienTM = BigDecimal.ZERO;

                    lblTongTien.setText(totalAmount + "");
                    if (tongTien.compareTo(BigDecimal.valueOf(0)) < 0) {
                        txtTienTL.setText(format.format(totalAmount.multiply(BigDecimal.valueOf(-1))));
                    } else {
                        txtTienTL.setText(0 + "");
                    }

                    return;
                }

                if (txtTM.getText().trim().isEmpty()) {
                    try {
                        tienTM = BigDecimal.ZERO;
                        tienCK = BigDecimal.valueOf(Long.parseLong(txtCK.getText().trim()));

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập số");
                        return;
                    }
                    tongTien = totalAmount.subtract(tienCK);
                    lblTongTien.setText(tongTien + "");
                    if (tongTien.compareTo(BigDecimal.valueOf(0)) < 0) {
                        txtTienTL.setText(format.format(tongTien.multiply(BigDecimal.valueOf(-1))));
                    } else {
                        txtTienTL.setText(0 + "");
                    }

                }
                if (txtCK.getText().trim().isEmpty()) {
                    try {
                        tienCK = BigDecimal.ZERO;
                        tienTM = BigDecimal.valueOf(Long.parseLong(txtTM.getText().trim()));

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập số");
                        return;
                    }
                    tongTien = totalAmount.subtract(tienTM);
                    lblTongTien.setText(tongTien + "");

                    if (tongTien.compareTo(BigDecimal.valueOf(0)) < 0) {
                        txtTienTL.setText(format.format(tongTien.multiply(BigDecimal.valueOf(-1))));
                    } else {
                        txtTienTL.setText(0 + "");
                    }

                } else if (!txtCK.getText().trim().isEmpty() && !txtTM.getText().trim().isEmpty()) {
                    try {
                        tienCK = BigDecimal.valueOf(Long.parseLong(txtCK.getText().trim()));
                        tienTM = BigDecimal.valueOf(Long.parseLong(txtTM.getText().trim()));

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập số");
                        return;

                    }
                    BigDecimal tienTra;
                    tienTra = tienCK.add(tienTM);

                    tongTien = totalAmount.subtract(tienTra);

                    lblTongTien.setText(tongTien + "");
                    if (tongTien.compareTo(BigDecimal.valueOf(0)) < 0) {
                        txtTienTL.setText(format.format(tongTien.multiply(BigDecimal.valueOf(-1))));
                    } else {
                        txtTienTL.setText(0 + "");
                    }

                }

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                BigDecimal totalAmount = calculateTotalAmountFromGioHang();
                BigDecimal tienTM = BigDecimal.ZERO;
                BigDecimal tienCK = BigDecimal.ZERO;

                BigDecimal tongTien = BigDecimal.ZERO;
                if (txtCK.getText().trim().isEmpty() && txtTM.getText().trim().isEmpty()) {
                    tienCK = BigDecimal.ZERO;
                    tienTM = BigDecimal.ZERO;

                    lblTongTien.setText(totalAmount + "");
                    if (tongTien.compareTo(BigDecimal.valueOf(0)) < 0) {
                        txtTienTL.setText(format.format(totalAmount.multiply(BigDecimal.valueOf(-1))));
                    } else {
                        txtTienTL.setText(0 + "");
                    }

                    return;
                }

                if (txtTM.getText().trim().isEmpty()) {
                    try {
                        tienTM = BigDecimal.ZERO;
                        tienCK = BigDecimal.valueOf(Long.parseLong(txtCK.getText().trim()));

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập số");
                        return;
                    }
                    tongTien = totalAmount.subtract(tienCK);
                    lblTongTien.setText(tongTien + "");
                    if (tongTien.compareTo(BigDecimal.valueOf(0)) < 0) {
                        txtTienTL.setText(format.format(tongTien.multiply(BigDecimal.valueOf(-1))));
                    } else {
                        txtTienTL.setText(0 + "");
                    }

                }
                if (txtCK.getText().trim().isEmpty()) {
                    try {
                        tienCK = BigDecimal.ZERO;
                        tienTM = BigDecimal.valueOf(Long.parseLong(txtTM.getText().trim()));

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập số");
                        return;
                    }
                    tongTien = totalAmount.subtract(tienTM);
                    lblTongTien.setText(tongTien + "");

                    if (tongTien.compareTo(BigDecimal.valueOf(0)) < 0) {
                        txtTienTL.setText(format.format(tongTien.multiply(BigDecimal.valueOf(-1))));
                    } else {
                        txtTienTL.setText(0 + "");
                    }

                } else if (!txtCK.getText().trim().isEmpty() && !txtTM.getText().trim().isEmpty()) {
                    try {
                        tienCK = BigDecimal.valueOf(Long.parseLong(txtCK.getText().trim()));
                        tienTM = BigDecimal.valueOf(Long.parseLong(txtTM.getText().trim()));

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập số");
                        return;

                    }
                    BigDecimal tienTra;
                    tienTra = tienCK.add(tienTM);

                    tongTien = totalAmount.subtract(tienTra);

                    lblTongTien.setText(tongTien + "");
                    if (tongTien.compareTo(BigDecimal.valueOf(0)) < 0) {
                        txtTienTL.setText(format.format(tongTien.multiply(BigDecimal.valueOf(-1))));
                    } else {
                        txtTienTL.setText(0 + "");
                    }

                }

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                BigDecimal totalAmount = calculateTotalAmountFromGioHang();
                BigDecimal tienTM = BigDecimal.ZERO;
                BigDecimal tienCK = BigDecimal.ZERO;

                BigDecimal tongTien = BigDecimal.ZERO;
                if (txtCK.getText().trim().isEmpty() && txtTM.getText().trim().isEmpty()) {
                    tienCK = BigDecimal.ZERO;
                    tienTM = BigDecimal.ZERO;

                    lblTongTien.setText(totalAmount + "");
                    if (tongTien.compareTo(BigDecimal.valueOf(0)) < 0) {
                        txtTienTL.setText(format.format(totalAmount.multiply(BigDecimal.valueOf(-1))));
                    } else {
                        txtTienTL.setText(0 + "");
                    }

                    return;
                }

                if (txtTM.getText().trim().isEmpty()) {
                    try {
                        tienTM = BigDecimal.ZERO;
                        tienCK = BigDecimal.valueOf(Long.parseLong(txtCK.getText().trim()));

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập số");
                        return;
                    }
                    tongTien = totalAmount.subtract(tienCK);
                    lblTongTien.setText(tongTien + "");
                    if (tongTien.compareTo(BigDecimal.valueOf(0)) < 0) {
                        txtTienTL.setText(format.format(tongTien.multiply(BigDecimal.valueOf(-1))));
                    } else {
                        txtTienTL.setText(0 + "");
                    }

                }
                if (txtCK.getText().trim().isEmpty()) {
                    try {
                        tienCK = BigDecimal.ZERO;
                        tienTM = BigDecimal.valueOf(Long.parseLong(txtTM.getText().trim()));

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập số");
                        return;
                    }
                    tongTien = totalAmount.subtract(tienTM);
                    lblTongTien.setText(tongTien + "");

                    if (tongTien.compareTo(BigDecimal.valueOf(0)) < 0) {
                        txtTienTL.setText(format.format(tongTien.multiply(BigDecimal.valueOf(-1))));
                    } else {
                        txtTienTL.setText(0 + "");
                    }

                } else if (!txtCK.getText().trim().isEmpty() && !txtTM.getText().trim().isEmpty()) {
                    try {
                        tienCK = BigDecimal.valueOf(Long.parseLong(txtCK.getText().trim()));
                        tienTM = BigDecimal.valueOf(Long.parseLong(txtTM.getText().trim()));

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập số");
                        return;

                    }
                    BigDecimal tienTra;
                    tienTra = tienCK.add(tienTM);

                    tongTien = totalAmount.subtract(tienTra);

                    lblTongTien.setText(tongTien + "");
                    if (tongTien.compareTo(BigDecimal.valueOf(0)) < 0) {
                        txtTienTL.setText(format.format(tongTien.multiply(BigDecimal.valueOf(-1))));
                    } else {
                        txtTienTL.setText(0 + "");
                    }

                }

            }
        });
//          ListSelectionListener listener = new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                
//                JTable table = (tblHoaDon); e.getSource();
//                if (!e.getValueIsAdjusting()) {
//                    if (table.getSelectedRowCount() > 0) {
//                        if (!tblSanPham.contains(p)) {
//                            tblSanPham.add(table);
//                        }
//                    } else {
//                        tblSanPham.remove(table);
//                    }
//                }
//            }
//        };
//        t.getSelectionModel().addListSelectionListener(listener);
//        table2.getSelectionModel().addListSelectionListener(listener);
        JTableHeader header3 = tblSanPham.getTableHeader();
        header3.setDefaultRenderer(new centerTablee());

        lists = bhs.getAll();
        dtm = (DefaultTableModel) tblSanPham.getModel();
        showData(lists);

        listHD = hds.getHD();
        dtmHD = (DefaultTableModel) tblHoaDon.getModel();
        showDataHD(listHD);

        tblSanPham.setDefaultEditor(Object.class, null);
        tblGioHang.setDefaultEditor(Object.class, null);
        tblHoaDon.setDefaultEditor(Object.class, null);
        txtCK.setEditable(false);
        txtTM.setEditable(false);

    }

    private void setDataCboHTTT() {
        cbbPTTT.removeAllItems();
        String[] paymentMethods = {"", "Tiền mặt", "Chuyển khoản", "Cả 2 hình thức"};
        for (String method : paymentMethods) {
            cbbPTTT.addItem(method);
        }
        cbbPTTT.setSelectedIndex(0);
    }

    private BigDecimal calculateTotalAmountFromGioHang() {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
            Object amountObject = tblGioHang.getValueAt(i, 9);
            if (amountObject != null) {
                String amountString = amountObject.toString();
                if (!amountString.isEmpty()) {
                    BigDecimal amount = new BigDecimal(amountString.replace(",", ""));
                    totalAmount = totalAmount.add(amount);
                }
            }
        }
        return totalAmount;
    }

    private void setTxtFieldsFromSelectedHoaDon(HoaDonViewModel selectedHoaDon) {
        Date createAt = selectedHoaDon.getNgayTao();
        String createBy = selectedHoaDon.getnV();
        String maHD = selectedHoaDon.getMa();
        String tenKH = selectedHoaDon.getTenKH();
        String maKH = selectedHoaDon.getMaKH();
        String idKH = selectedHoaDon.getIdKH();

        int i = 0;
        i = tblHoaDon.getSelectedRow();
        Object amountObject = tblHoaDon.getValueAt(i, 5);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String dateString = formatter.format(createAt);

        txtNT.setText(dateString);
        txtMNV.setText(createBy);
        txtMHD.setText(maHD);
        txtTKH.setText(tenKH);
        txtTenKH.setText(tenKH);
        txtIDKH.setText(idKH);
        txtTToan.setText(amountObject + "");
        txtMKH.setText(maKH);
        txtIDHD.setText(selectedHoaDon.getId());

        // Tính tổng số tiền từ bảng tblGioHang
        BigDecimal totalAmount = calculateTotalAmountFromGioHang();
        // Hiển thị tổng số tiền tính được vào txtTongTien
        lblTongTien.setText(format.format(totalAmount));
        lblTongTien2.setText(format.format(totalAmount));
    }

    public void getThongTinKH(String ten, String ma, String id) {
        txtTenKH.setText(ten);
        txtMKH.setText(ma);
        txtTKH.setText(ten);
        txtIDKH.setText(id);
    }

    private int nhapSoLuong() {
        String input = JOptionPane.showInputDialog("Nhập số lượng để thêm vào giỏ hàng:");

        if (input != null && !input.isEmpty()) {
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập số nguyên dương.");

            }
        }
        return 0; // Trả về 0 nếu không nhập hoặc nhập không hợp lệ
    }

    private int nhapSoLuong1() {
        String input = JOptionPane.showInputDialog("Nhập số lượng để xoá khỏi giỏ hàng:");

        if (input != null && !input.isEmpty()) {
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập số nguyên dương.");

            }
        }
        return 0; // Trả về 0 nếu không nhập hoặc nhập không hợp lệ
    }

    public void showDataHD(List<HoaDonViewModel> listHD) {
        dtmHD.setRowCount(0);
        int stt = 1;
        for (HoaDonViewModel hoaDonViewModel : listHD) {
            dtmHD.addRow(new Object[]{
                stt++,
                hoaDonViewModel.getMa(),
                hoaDonViewModel.getNgayTao(),
                hoaDonViewModel.getnV(),
                hoaDonViewModel.getTongSP(),
                hoaDonViewModel.gettT()
            });
        }
    }

    public void showDataTableGH(List<GioHangViewModel> listHDCT) {
        dtmGH.setRowCount(0);
        int i = 0;
        for (GioHangViewModel kh : listGH) {
            i++;
            dtmGH.addRow(new Object[]{
                i, kh.getMaHDCT(), kh.getTenSP(),
                kh.getTenMS(), kh.getTenCL(),
                kh.getTenPK(), kh.getTenTH(),
                format.format(kh.getDonGia()), kh.getSoLuong(), format.format(kh.getSoLuong() * kh.getDonGia())
            });
        }

    }

    public void showData(List<BanHangViewModel> listBH) {
        dtm.setRowCount(0);
        int stt = 1;
        for (BanHangViewModel banHangViewModel : listBH) {
            dtm.addRow(new Object[]{
                stt++,
                banHangViewModel.getMa(),
                banHangViewModel.getTenSP(),
                banHangViewModel.getTenMS(),
                banHangViewModel.getTenCL(),
                banHangViewModel.getKichCo(),
                banHangViewModel.getTenNSX(),
                banHangViewModel.getTenTH(),
                banHangViewModel.getSoLuong(),
                format.format(banHangViewModel.getGiaBan()) + " " + "VND",
                banHangViewModel.getTrangThai()
            });
        }
    }

    public void createComboBox(List<GiamGiaViewModel> listLKH) {
        cbbPGG.removeAllItems();
        cbbPGG.addItem("");
        for (GiamGiaViewModel lkhs : listLKH) {
            cbbPGG.addItem("Giảm giá " + format.format(lkhs.getSoTienGiamToiDa()) + "VND");
        }

    }

//    public void showDataGH(List<GioHangViewModel> listGH) {
//        if (listGH == null) {
//            listGH = new ArrayList<>();
//        }
//        dtmGH.setRowCount(0);
//        int stt = 1;
//        for (GioHangViewModel gioHangViewModel : listGH) {
//            double thanhTien = gioHangViewModel.getGia() * gioHangViewModel.getSoLuong();
//            dtmGH.addRow(new Object[]{
//                stt++,
//                gioHangViewModel.getMa(),
//                gioHangViewModel.getTen(),
//                gioHangViewModel.getMau(),
//                gioHangViewModel.getChatLieu(),
//                gioHangViewModel.getKichCo(),
//                format.format(gioHangViewModel.getGia()) + " VND",
//                gioHangViewModel.getSoLuong(),
//                format.format(thanhTien) + " VND"
//            });
//        }
//    }
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

    private void updateData(String search) {
        lists = bhs.tim(search);
        showData(lists);
    }

    //có thêm các trường khác để kết nối với cách model khác\
    private HoaDonViewModel getDataHD() {
        HoaDonViewModel hd = new HoaDonViewModel();
        // Tạo
        String maHD = "HD" + String.format("%05d", listHD.size() + 1);

        hd.setMa(maHD);
        hd.setNgayTao(new java.sql.Date(System.currentTimeMillis()));
        hd.setnV("NV001");
        hd.setTongSP(0);
        hd.settT("Chờ thanh toán");
        return hd;
    }

    public HoaDonChiTiet getFormHDCT(String idSP, String idHD, int soLuong, double tongTien) {

        HoaDonChiTiet hdct = new HoaDonChiTiet(idSP, idHD, soLuong, tongTien);
        return hdct;
    }

    public ChiTietSanPham getFormCTSP(int soLuong) {

        ChiTietSanPham ctsp = new ChiTietSanPham(soLuong);
        return ctsp;
    }

//    public List<ChiTietSanPham> getFormCTSP1(int soLuong) {
//
//        ChiTietSanPham ctsp = new ChiTietSanPham(soLuong);
//        return ctsp;
//    }
    public List<ChiTietSanPham> getFormCTSP1(String idCTSP, int soLuong) {
        int index = tblGioHang.getRowCount();

        List<ChiTietSanPham> hdctList = new ArrayList<>();

        ChiTietSanPham hdct = new ChiTietSanPham(idCTSP, soLuong);
        hdctList.add(hdct);

        return hdctList;
    }

    private void addToCart(String maSP, String tenSanPham, String hang, String mau, String size, String chatlieu, String day, int soLuong, int giaBan) {
        boolean productExists = false;

        for (int i = 0; i < dtmGH.getRowCount(); i++) {
            if (dtmGH.getValueAt(i, 1).equals(maSP)) {
                int oldQuantity = (int) dtmGH.getValueAt(i, 8);
                dtmGH.setValueAt(oldQuantity + soLuong, i, 8);
                productExists = true;
                break;
            }
        }
    }
//    public SinhVien getFormData(){
//        String tenSV = txtTen.getText().trim();
//        String maSV = txtMSV.getText().trim();
//        String SDT = txtSDT.getText().trim();
//        String CMND = txtCMND.getText().trim();
//        String email = txtEmail.getText().trim();
//        
//        String tenLop = cboTenLop.getSelectedItem()+"";
//        MaLop lop = lsr.timLop(tenLop);
//        int gioiTinh = 4;
//        if(rdoNam.isSelected()){
//            gioiTinh = 1;
//        }else if(rdoNu.isSelected()){
//            gioiTinh = 0;   
//        }
//        
//        SinhVien sv = new SinhVien(maSV, tenSV, CMND, SDT, email, lop.getId(), gioiTinh);
//                return sv;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        roundedPanel1 = new RoundedPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        btnLMHD = new RoundedButton();
        btnTaoHD = new RoundedButton();
        btnQuetQR = new RoundedButton();
        jLabel3 = new javax.swing.JLabel();
        roundedPanel2 = new RoundedPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        botronPanel1 = new BotronPanel();
        btnTim = new RoundedButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        txtTim = new javax.swing.JTextField();
        cboNXS = new combobox.Combobox();
        cboCL = new combobox.Combobox();
        cboM = new combobox.Combobox();
        cboKC = new combobox.Combobox();
        roundedPanel3 = new RoundedPanel();
        jLabel6 = new javax.swing.JLabel();
        roundedPanel4 = new RoundedPanel();
        txtMKH = new javax.swing.JTextField();
        txtTKH = new javax.swing.JTextField();
        roundedButton5 = new RoundedButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtTToan = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        roundedPanel5 = new RoundedPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtMHD = new javax.swing.JTextField();
        txtMNV = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        txtCK = new javax.swing.JTextField();
        txtNT = new javax.swing.JTextField();
        btnHuyHD = new RoundedButton();
        btnThanhToan = new RoundedButton();
        cbbPGG = new javax.swing.JComboBox<>();
        txtTM = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        cbbPTTT = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        lblTongTien1 = new javax.swing.JLabel();
        lblTongTien2 = new javax.swing.JLabel();
        txtTienTL = new javax.swing.JTextField();
        txtIDCTSP = new javax.swing.JTextField();
        txtIDHD = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        txtDonGia = new javax.swing.JTextField();
        txtIDKH = new javax.swing.JTextField();
        lblTongTien = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1230, 710));
        setRequestFocusEnabled(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1131, 728));

        jLabel1.setFont(new java.awt.Font("UVN Chim Bien", 1, 18)); // NOI18N
        jLabel1.setText("BÁN HÀNG");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Hóa Đơn");

        roundedPanel1.setBackground(new java.awt.Color(204, 204, 204));

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "MãHĐ", "Ngày tạo", "Nhân viên", "Tổng SP", "Trạng Thái"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);
        if (tblHoaDon.getColumnModel().getColumnCount() > 0) {
            tblHoaDon.getColumnModel().getColumn(4).setResizable(false);
        }

        btnLMHD.setBackground(new java.awt.Color(0, 0, 0));
        btnLMHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-refresh-32 (1).png"))); // NOI18N
        btnLMHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLMHDActionPerformed(evt);
            }
        });

        btnTaoHD.setBackground(new java.awt.Color(0, 0, 0));
        btnTaoHD.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoHD.setText("Tạo Hóa Đơn");
        btnTaoHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHDActionPerformed(evt);
            }
        });

        btnQuetQR.setBackground(new java.awt.Color(0, 0, 0));
        btnQuetQR.setForeground(new java.awt.Color(255, 255, 255));
        btnQuetQR.setText("Quét Mã QR");
        btnQuetQR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuetQRActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(btnQuetQR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTaoHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLMHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnLMHD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTaoHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnQuetQR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Giỏ Hàng");

        roundedPanel2.setBackground(new java.awt.Color(204, 204, 204));

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã-CTSP", "Tên SP", "Màu", "Chất liệu", "Phụ kiện", "Thương hiệu", "Giá", "Số lượng", "Thành tiền"
            }
        ));
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGioHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblGioHang);
        if (tblGioHang.getColumnModel().getColumnCount() > 0) {
            tblGioHang.getColumnModel().getColumn(3).setHeaderValue("Màu");
            tblGioHang.getColumnModel().getColumn(4).setHeaderValue("Chất liệu");
            tblGioHang.getColumnModel().getColumn(5).setHeaderValue("Phụ kiện");
            tblGioHang.getColumnModel().getColumn(6).setHeaderValue("Thương hiệu");
        }

        javax.swing.GroupLayout roundedPanel2Layout = new javax.swing.GroupLayout(roundedPanel2);
        roundedPanel2.setLayout(roundedPanel2Layout);
        roundedPanel2Layout.setHorizontalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        roundedPanel2Layout.setVerticalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel2Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Danh Sách Các Sản Phẩm");

        botronPanel1.setBackground(new java.awt.Color(204, 204, 204));

        btnTim.setBackground(new java.awt.Color(0, 0, 0));
        btnTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-search-24 (1).png"))); // NOI18N
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Ma-CTSP", "Tên", "Màu", "Chất liệu", "Kích cỡ", "Xuất xứ", "NSX", "Số lượng", "Giá", "Trạng Thái"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSanPham);

        txtTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimActionPerformed(evt);
            }
        });

        cboNXS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Louis Vuitton", "Dior", "YSL", "Lacos", "Dolce & Gabbana" }));
        cboNXS.setLabeText("NXS");
        cboNXS.setLightWeightPopupEnabled(false);
        cboNXS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNXSActionPerformed(evt);
            }
        });

        cboCL.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Vải", "Da bò", "Giả da" }));
        cboCL.setLabeText("Chất liệu");
        cboCL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCLActionPerformed(evt);
            }
        });

        cboM.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Màu đen", "Màu nâu ", "Màu đỏ", "Màu nude" }));
        cboM.setLabeText("Màu");
        cboM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMActionPerformed(evt);
            }
        });

        cboKC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Lớn", "Vừa", "Nhỏ" }));
        cboKC.setLabeText("Kích cỡ");
        cboKC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboKCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout botronPanel1Layout = new javax.swing.GroupLayout(botronPanel1);
        botronPanel1.setLayout(botronPanel1Layout);
        botronPanel1Layout.setHorizontalGroup(
            botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botronPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(botronPanel1Layout.createSequentialGroup()
                        .addComponent(txtTim)
                        .addGap(18, 18, 18)
                        .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cboNXS, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboCL, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboM, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboKC, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        botronPanel1Layout.setVerticalGroup(
            botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botronPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(botronPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboKC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboNXS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboCL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtTim)
                    .addComponent(btnTim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        roundedPanel3.setBackground(new java.awt.Color(204, 204, 204));
        roundedPanel3.setMaximumSize(new java.awt.Dimension(259, 621));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel6.setText("Thông Tin Khách Hàng");

        roundedPanel4.setBackground(new java.awt.Color(204, 204, 204));

        txtMKH.setBackground(new java.awt.Color(204, 204, 204));
        txtMKH.setOpaque(false);

        txtTKH.setBackground(new java.awt.Color(204, 204, 204));
        txtTKH.setOpaque(false);

        roundedButton5.setBackground(new java.awt.Color(0, 0, 0));
        roundedButton5.setForeground(new java.awt.Color(255, 255, 255));
        roundedButton5.setText("Chọn");
        roundedButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedButton5ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Tên khách hàng");

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(102, 102, 102));
        jLabel21.setText("Mã khách hàng");

        txtTToan.setBackground(new java.awt.Color(204, 204, 204));
        txtTToan.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtTToan.setOpaque(false);

        javax.swing.GroupLayout roundedPanel4Layout = new javax.swing.GroupLayout(roundedPanel4);
        roundedPanel4.setLayout(roundedPanel4Layout);
        roundedPanel4Layout.setHorizontalGroup(
            roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel4Layout.createSequentialGroup()
                        .addGroup(roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel21))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTKH, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMKH, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel4Layout.createSequentialGroup()
                        .addComponent(txtTToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(roundedButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        roundedPanel4Layout.setVerticalGroup(
            roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtMKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addGap(3, 3, 3)
                .addComponent(txtTKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roundedButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTToan, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel9.setText("Thông Tin Hóa Đơn");

        roundedPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Mã Hóa Đơn:");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Ngày Tạo:");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Mã Nhân Viên:");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Tên Khách Hàng:");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Tiền mặt");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("Tiên trả lại");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 0, 51));
        jLabel17.setText("Tổng:");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("Phương thức TT");

        txtMHD.setBackground(new java.awt.Color(204, 204, 204));
        txtMHD.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtMHD.setOpaque(false);

        txtMNV.setBackground(new java.awt.Color(204, 204, 204));
        txtMNV.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtMNV.setOpaque(false);

        txtTenKH.setBackground(new java.awt.Color(204, 204, 204));
        txtTenKH.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtTenKH.setOpaque(false);

        txtCK.setBackground(new java.awt.Color(204, 204, 204));
        txtCK.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtCK.setOpaque(false);

        txtNT.setBackground(new java.awt.Color(204, 204, 204));
        txtNT.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtNT.setOpaque(false);
        txtNT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNTActionPerformed(evt);
            }
        });

        btnHuyHD.setBackground(new java.awt.Color(0, 0, 0));
        btnHuyHD.setForeground(new java.awt.Color(255, 255, 255));
        btnHuyHD.setText("Hủy HĐ");
        btnHuyHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyHDActionPerformed(evt);
            }
        });

        btnThanhToan.setBackground(new java.awt.Color(0, 0, 0));
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        cbbPGG.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbPGG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbPGGActionPerformed(evt);
            }
        });

        txtTM.setBackground(new java.awt.Color(204, 204, 204));
        txtTM.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtTM.setOpaque(false);

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 102));
        jLabel19.setText("Chuyển khoản");

        cbbPTTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbPTTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbPTTTActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));
        jLabel20.setText("Giảm giá");

        lblTongTien1.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblTongTien1.setForeground(new java.awt.Color(255, 0, 0));
        lblTongTien1.setText("VND");

        lblTongTien2.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblTongTien2.setForeground(new java.awt.Color(255, 0, 0));
        lblTongTien2.setText("0 ");

        txtTienTL.setBackground(new java.awt.Color(204, 204, 204));
        txtTienTL.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtTienTL.setOpaque(false);

        javax.swing.GroupLayout roundedPanel5Layout = new javax.swing.GroupLayout(roundedPanel5);
        roundedPanel5.setLayout(roundedPanel5Layout);
        roundedPanel5Layout.setHorizontalGroup(
            roundedPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel5Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTongTien2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTongTien1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(roundedPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel5Layout.createSequentialGroup()
                        .addGroup(roundedPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel15)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(roundedPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel5Layout.createSequentialGroup()
                                .addComponent(txtTM, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4))
                            .addComponent(txtNT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMHD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCK, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(roundedPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbbPGG, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundedPanel5Layout.createSequentialGroup()
                        .addGroup(roundedPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(roundedPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMNV, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(roundedPanel5Layout.createSequentialGroup()
                                .addComponent(btnHuyHD, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(roundedPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, roundedPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel18)
                                    .addGap(18, 18, 18)
                                    .addComponent(cbbPTTT, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(roundedPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel14)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(roundedPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTienTL, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundedPanel5Layout.setVerticalGroup(
            roundedPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtMHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(roundedPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtNT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(roundedPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtMNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(roundedPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(roundedPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbPTTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(roundedPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(15, 15, 15)
                .addGroup(roundedPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(18, 18, 18)
                .addGroup(roundedPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addComponent(txtTienTL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundedPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(cbbPGG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(roundedPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(lblTongTien1)
                    .addComponent(lblTongTien2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuyHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        txtIDCTSP.setBackground(new java.awt.Color(204, 204, 204));
        txtIDCTSP.setAutoscrolls(false);
        txtIDCTSP.setEnabled(false);
        txtIDCTSP.setFocusable(false);
        txtIDCTSP.setOpaque(false);
        txtIDCTSP.setRequestFocusEnabled(false);

        txtIDHD.setBackground(new java.awt.Color(204, 204, 204));
        txtIDHD.setAutoscrolls(false);
        txtIDHD.setEnabled(false);
        txtIDHD.setFocusable(false);
        txtIDHD.setOpaque(false);
        txtIDHD.setRequestFocusEnabled(false);
        txtIDHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDHDActionPerformed(evt);
            }
        });

        txtSoLuong.setBackground(new java.awt.Color(204, 204, 204));
        txtSoLuong.setAutoscrolls(false);
        txtSoLuong.setEnabled(false);
        txtSoLuong.setFocusable(false);
        txtSoLuong.setOpaque(false);
        txtSoLuong.setRequestFocusEnabled(false);
        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });

        txtDonGia.setBackground(new java.awt.Color(204, 204, 204));
        txtDonGia.setAutoscrolls(false);
        txtDonGia.setEnabled(false);
        txtDonGia.setFocusable(false);
        txtDonGia.setOpaque(false);
        txtDonGia.setRequestFocusEnabled(false);

        txtIDKH.setBackground(new java.awt.Color(204, 204, 204));
        txtIDKH.setAutoscrolls(false);
        txtIDKH.setEnabled(false);
        txtIDKH.setFocusable(false);
        txtIDKH.setOpaque(false);
        txtIDKH.setRequestFocusEnabled(false);
        txtIDKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDKHActionPerformed(evt);
            }
        });

        lblTongTien.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(255, 0, 0));
        lblTongTien.setText("0 ");
        lblTongTien.setEnabled(false);
        lblTongTien.setFocusable(false);
        lblTongTien.setInheritsPopupMenu(false);
        lblTongTien.setRequestFocusEnabled(false);

        javax.swing.GroupLayout roundedPanel3Layout = new javax.swing.GroupLayout(roundedPanel3);
        roundedPanel3.setLayout(roundedPanel3Layout);
        roundedPanel3Layout.setHorizontalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel3Layout.createSequentialGroup()
                .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(roundedPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(roundedPanel3Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(txtIDHD, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(txtIDCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundedPanel3Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(txtIDKH, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundedPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundedPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(91, 91, 91)
                                .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(roundedPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundedPanel3Layout.setVerticalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(11, 11, 11)
                .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDHD, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIDKH, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(roundedPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundedPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addGap(350, 350, 350)
                .addComponent(jLabel1)
                .addContainerGap(696, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(roundedPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(botronPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundedPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(roundedPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(roundedPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botronPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(roundedPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(153, Short.MAX_VALUE))
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
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        lists = bhs.timT(txtTim.getText());
        showData(lists);
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnHuyHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyHDActionPerformed
        int index = tblGioHang.getRowCount();

        String[] idCtsp = txtIDCTSP.getText().split("\n");
        for (int i = 0; i <= index; i++) {

            for (GioHangViewModel bh : listGH) {
                bhs.huyHD(getFormCTSP1(bh.getMaSanPham(), bh.getSoLuong() + bh.getSoLuongSP()), bh.getIdHoaDon(), bh.getSoLuong() + bh.getSoLuongSP());
            }

        }
        lists = bhs.getAll();
        dtm = (DefaultTableModel) tblSanPham.getModel();
        showData(lists);

        listHD = hds.getHD();
        dtmHD = (DefaultTableModel) tblHoaDon.getModel();
        showDataHD(listHD);

        dtmGH = (DefaultTableModel) tblGioHang.getModel();
        listGH = ghs.getGioHang(null);
        showDataTableGH(listGH);

    }//GEN-LAST:event_btnHuyHDActionPerformed

    private void cboMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMActionPerformed
        updateData((String) cboM.getSelectedItem());
    }//GEN-LAST:event_cboMActionPerformed

    private void cboNXSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNXSActionPerformed
        updateData((String) cboNXS.getSelectedItem());
    }//GEN-LAST:event_cboNXSActionPerformed

    private void cboCLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCLActionPerformed
        updateData((String) cboCL.getSelectedItem());
    }//GEN-LAST:event_cboCLActionPerformed

    private void cboKCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboKCActionPerformed
        updateData((String) cboKC.getSelectedItem());
    }//GEN-LAST:event_cboKCActionPerformed

    private void btnLMHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLMHDActionPerformed
        listHD = hds.getHD();
        showDataHD(listHD);

        listGH = ghs.getGioHang(null);
        showDataTableGH(listGH);

        lists = bhs.getAll();
        showData(lists);
    }//GEN-LAST:event_btnLMHDActionPerformed

    private void btnTaoHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHDActionPerformed
        HoaDonViewModel hdMoi = getDataHD();

        int i = tblHoaDon.getRowCount();
        if (i >= 10) {
            JOptionPane.showMessageDialog(this, "Không được tạo quá 10 hoá đơn");
            return;
        }
        boolean tc = hds.themHoaDon(hdMoi);

        if (tc) {
            HoaDonViewModel hd = listHD.get(0);
            listHD.add(hdMoi);
            listHD = hds.getHD();
            showDataHD(listHD);

            listGH = ghs.getGioHang(hd.getId());
            showDataTableGH(listGH);
            

            cbbPGG.setSelectedIndex(0);
            cbbPTTT.setSelectedIndex(0);
            txtTM.setText("");
            txtCK.setText("");

            tblHoaDon.setRowSelectionInterval(0, 0);
            setTxtFieldsFromSelectedHoaDon(hd);
        } else {
            JOptionPane.showMessageDialog(this, "Tạo thất bại");    
            return;
        }
       
    }//GEN-LAST:event_btnTaoHDActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
//        tblHoaDon.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                int row = tblHoaDon.getSelectedRow();
//                String ID = String.valueOf(tblHoaDon.getValueAt(row, 0));
//                listGH = ghs.getGH(ID);
//                showDataGH(listGH);
//            }
//        });
        int rowIndex = tblHoaDon.getSelectedRow();
        HoaDonViewModel hd = listHD.get(rowIndex);

        cbbPGG.setSelectedIndex(0);
        cbbPTTT.setSelectedIndex(0);
        txtTM.setText("");
        txtCK.setText("");

        dtmGH = (DefaultTableModel) tblGioHang.getModel();
        listGH = ghs.getGioHang(hd.getId());
        showDataTableGH(listGH);

        setTxtFieldsFromSelectedHoaDon(hd);

    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked

        int rowSP = tblSanPham.getSelectedRow();
        BanHangViewModel sp = lists.get(rowSP);

        txtIDCTSP.setText(sp.getId());

        String idCTSP = txtIDCTSP.getText();
        txtDonGia.setText(sp.getGiaBan() + "");
        double giaBan = Double.parseDouble(txtDonGia.getText());
        txtSoLuong.setText(sp.getSoLuong() + "");
        int soLuong1 = Integer.parseInt(txtSoLuong.getText());

        if (evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1) { // Kiểm tra xem đã click 2 lần chưa
            System.out.println(sp.getId());
            System.out.println("Đã click 2 lần");
            if (txtIDHD.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 hoá dơn");
                return;
            }

            int soLuong = nhapSoLuong();
            if (soLuong <= 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn số lớn hơn 0 hoặc chọn OK");
                return;

            }
            if (sp.getSoLuong() - soLuong < 0) {
                JOptionPane.showMessageDialog(this, "Quá giới hạn số lượng hàng");
                return;
            }

            for (GioHangViewModel gioHangSP : listGH) {
                if (gioHangSP.getTenSP().equals(sp.getTenSP())) {
                    // Sản phẩm đã tồn tại trong giỏ hàng, giảm số lượng đi 1
                    bhs.UpdateTrungSP(txtIDHD.getText(), idCTSP, sp.getSoLuong() - soLuong, soLuong + gioHangSP.getSoLuong());
                    // Giảm đi 1 số lượng
                    lists = bhs.getAll();
                    dtm = (DefaultTableModel) tblSanPham.getModel();
                    showData(lists);

                    listHD = hds.getHD();
                    dtmHD = (DefaultTableModel) tblHoaDon.getModel();
                    showDataHD(listHD);

                    dtmGH = (DefaultTableModel) tblGioHang.getModel();
                    listGH = ghs.getGioHang(txtIDHD.getText());
                    showDataTableGH(listGH);
                    return;

                }
                // Nếu sản phẩm không tồn tại trong giỏ hàng, không cần thực hiện gì cả
            }

            bhs.AddSPGH(getFormHDCT(txtIDCTSP.getText(), txtIDHD.getText(), soLuong, giaBan),
                    getFormCTSP(soLuong1 - soLuong), txtIDHD.getText(), txtIDCTSP.getText(), sp.getSoLuong() - soLuong, soLuong);

            lists = bhs.getAll();
            dtm = (DefaultTableModel) tblSanPham.getModel();
            showData(lists);

            listHD = hds.getHD();
            dtmHD = (DefaultTableModel) tblHoaDon.getModel();
            showDataHD(listHD);

            dtmGH = (DefaultTableModel) tblGioHang.getModel();
            listGH = ghs.getGioHang(txtIDHD.getText());
            showDataTableGH(listGH);
        }


    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void txtNTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNTActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed

        String idPGG = null;
        String idHTTT = null;
        int soPGG = 0; 
        
        if (txtIDHD.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 hoá đơn");
            return;
        }

        if (txtTToan.getText().equalsIgnoreCase("Đã thanh toán")) {
            JOptionPane.showMessageDialog(this, "Hoá đơn đã được thanh toán rồi");
            return;
        }
        if (cbbPTTT.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phương thức thanh toán và nhập số tiền");
            return;
        }

        if (cbbPTTT.getSelectedIndex() == 1) {
            idHTTT = "48DA4E80-2212-47FE-A758-A4C5DE9B8727";
            System.out.println(idHTTT);
        }
        if (cbbPTTT.getSelectedIndex() == 2) {
            idHTTT = "58AC69D4-BE5C-4547-8419-3A3695317C25";
            System.out.println(idHTTT);
        }
        if (cbbPTTT.getSelectedIndex() == 3) {
            idHTTT = "96A88802-E9BD-4A7F-912D-B23A1A1DBFDA";
            System.out.println(idHTTT);
        }

        if (Float.parseFloat(lblTongTien.getText()) > 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ số tiền");
            return;
        } else {

            if (cbbPGG.getSelectedIndex() == 0) {
                idPGG = null;
                float tienTraLai;
            tienTraLai = Float.parseFloat(lblTongTien.getText());
            float tienCK = 0;
            float tienTM = 0;

            if (txtCK.isEditable() && !txtTM.isEditable()) {
                tienTM = 0;
                tienCK = Float.parseFloat(txtCK.getText().trim());
                bhs.thanhToan(txtIDHD.getText(), idHTTT, tienTM, tienCK, txtIDKH.getText().trim(), null);
                JOptionPane.showMessageDialog(this, "Thanh toán hoá đơn thành công" + "\n" + "Đã trả lại: " + format.format(Double.valueOf(tienTraLai + "") * -1) + " VND");
                lists = bhs.getAll();
                dtm = (DefaultTableModel) tblSanPham.getModel();
                showData(lists);

                listHD = hds.getHD();
                dtmHD = (DefaultTableModel) tblHoaDon.getModel();
                showDataHD(listHD);

                dtmGH = (DefaultTableModel) tblGioHang.getModel();
                listGH = ghs.getGioHang(null);
                showDataTableGH(listGH);
                return;
            }
            if (txtTM.isEditable() && !txtCK.isEditable()) {
                tienCK = 0;
                tienTM = Float.parseFloat(txtTM.getText().trim());
                bhs.thanhToan(txtIDHD.getText(), idHTTT, tienTM, tienCK, txtIDKH.getText().trim(), null);
                JOptionPane.showMessageDialog(this, "Thanh toán hoá đơn thành công" + "\n" + "Đã trả lại: " + format.format(Double.valueOf(tienTraLai + "") * -1) + " VND");
                lists = bhs.getAll();
                dtm = (DefaultTableModel) tblSanPham.getModel();
                showData(lists);

                listHD = hds.getHD();
                dtmHD = (DefaultTableModel) tblHoaDon.getModel();
                showDataHD(listHD);

                dtmGH = (DefaultTableModel) tblGioHang.getModel();
                listGH = ghs.getGioHang(null);
                showDataTableGH(listGH);
                return;
            } else {
                tienCK = Float.parseFloat(txtCK.getText().trim());
                tienTM = Float.parseFloat(txtTM.getText().trim());
                bhs.thanhToan(txtIDHD.getText(), idHTTT, tienTM, tienCK, txtIDKH.getText().trim(), null);
                JOptionPane.showMessageDialog(this, "Thanh toán hoá đơn thành công" + "\n" + "Đã trả lại: " + format.format(Double.valueOf(tienTraLai + "") * -1) + " VND");
                lists = bhs.getAll();
                dtm = (DefaultTableModel) tblSanPham.getModel();
                showData(lists);

                listHD = hds.getHD();
                dtmHD = (DefaultTableModel) tblHoaDon.getModel();
                showDataHD(listHD);

                dtmGH = (DefaultTableModel) tblGioHang.getModel();
                listGH = ghs.getGioHang(null);
                showDataTableGH(listGH);
                return;
            }
            } else {
                GiamGiaViewModel sp = listGG.get(cbbPGG.getSelectedIndex() - 1);
                idPGG = sp.getIdPhieuGiamGia();
                soPGG = sp.getSoLuongPhieu();
                float tienTraLai;
            tienTraLai = Float.parseFloat(lblTongTien.getText());
            float tienCK = 0;
            float tienTM = 0;

            if (txtCK.isEditable() && !txtTM.isEditable()) {
                tienTM = 0;
                tienCK = Float.parseFloat(txtCK.getText().trim());
                bhs.thanhToan1(txtIDHD.getText(), idHTTT, tienTM, tienCK, txtIDKH.getText().trim(), idPGG, soPGG -1);
                JOptionPane.showMessageDialog(this, "Thanh toán hoá đơn thành công" + "\n" + "Đã trả lại: " + format.format(Double.valueOf(tienTraLai + "") * -1) + " VND");
                lists = bhs.getAll();
                dtm = (DefaultTableModel) tblSanPham.getModel();
                showData(lists);

                listHD = hds.getHD();
                dtmHD = (DefaultTableModel) tblHoaDon.getModel();
                showDataHD(listHD);

                dtmGH = (DefaultTableModel) tblGioHang.getModel();
                listGH = ghs.getGioHang(null);
                showDataTableGH(listGH);
                return;
            }
            if (txtTM.isEditable() && !txtCK.isEditable()) {
                tienCK = 0;
                tienTM = Float.parseFloat(txtTM.getText().trim());
                bhs.thanhToan1(txtIDHD.getText(), idHTTT, tienTM, tienCK, txtIDKH.getText().trim(), idPGG, soPGG-1);
                JOptionPane.showMessageDialog(this, "Thanh toán hoá đơn thành công" + "\n" + "Đã trả lại: " + format.format(Double.valueOf(tienTraLai + "") * -1) + " VND");
                lists = bhs.getAll();
                dtm = (DefaultTableModel) tblSanPham.getModel();
                showData(lists);

                listHD = hds.getHD();
                dtmHD = (DefaultTableModel) tblHoaDon.getModel();
                showDataHD(listHD);

                dtmGH = (DefaultTableModel) tblGioHang.getModel();
                listGH = ghs.getGioHang(null);
                showDataTableGH(listGH);
                return;
            } else {
                tienCK = Float.parseFloat(txtCK.getText().trim());
                tienTM = Float.parseFloat(txtTM.getText().trim());
                bhs.thanhToan1(txtIDHD.getText(), idHTTT, tienTM, tienCK, txtIDKH.getText().trim(), idPGG, soPGG-1);
                JOptionPane.showMessageDialog(this, "Thanh toán hoá đơn thành công" + "\n" + "Đã trả lại: " + format.format(Double.valueOf(tienTraLai + "") * -1) + " VND");
                lists = bhs.getAll();
                dtm = (DefaultTableModel) tblSanPham.getModel();
                showData(lists);

                listHD = hds.getHD();
                dtmHD = (DefaultTableModel) tblHoaDon.getModel();
                showDataHD(listHD);

                dtmGH = (DefaultTableModel) tblGioHang.getModel();
                listGH = ghs.getGioHang(null);
                showDataTableGH(listGH);
                return;
            }
            }
            
        }


    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void cbbPGGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbPGGActionPerformed
        double tienGG = 0;
        BigDecimal totalAmount = calculateTotalAmountFromGioHang();
        BigDecimal tienCK = BigDecimal.ZERO;
        BigDecimal tienTM = BigDecimal.ZERO;
        BigDecimal tongTien = BigDecimal.ZERO;
        BigDecimal tongTienFinal = BigDecimal.ZERO;
        if (cbbPGG.getSelectedIndex() <= 0) {
            tienGG = 0;

        } else if (cbbPGG.getSelectedIndex() > 0) {
            try {
                int chon = cbbPGG.getSelectedIndex();
                GiamGiaViewModel sp = listGG.get(chon - 1);

            } catch (Exception e) {
                System.out.println("Chưa chọn");
                return;
            }
            int chon = cbbPGG.getSelectedIndex();
            GiamGiaViewModel sp = listGG.get(chon - 1);
            tienGG = sp.getSoTienGiamToiDa();
//            if (txtCK.getText().trim().isEmpty() && txtTM.getText().trim().isEmpty()) {
//                    tienCK = BigDecimal.ZERO;
//                    tienTM = BigDecimal.ZERO;
//
//                    lblTongTien.setText(totalAmount + "");
//                    return;
//                }
            if (txtCK.getText().trim().isEmpty() && txtTM.getText().trim().isEmpty()) {

                tienCK = BigDecimal.ZERO;
                tienTM = BigDecimal.ZERO;

                System.out.println(tienGG);

                tongTienFinal = totalAmount.subtract(BigDecimal.valueOf(tienGG));
                lblTongTien.setText(tongTienFinal + "");
                lblTongTien2.setText(format.format(totalAmount.subtract(BigDecimal.valueOf(tienGG))));
                if (tongTienFinal.compareTo(BigDecimal.valueOf(0)) < 0) {
                    txtTienTL.setText(format.format(tongTienFinal.multiply(BigDecimal.valueOf(-1))));
                } else {
                    txtTienTL.setText(0 + "");
                }
                return;
            }

            if (txtTM.getText().trim().isEmpty()) {
                try {

                    tienCK = BigDecimal.valueOf(Float.parseFloat(txtCK.getText().trim()));
                    tienTM = BigDecimal.ZERO;
                    BigDecimal tienTra;
                    tienTra = tienCK;

                    tongTien = totalAmount.subtract(tienTra);
                    tongTienFinal = tongTien.subtract(BigDecimal.valueOf(tienGG));
                    lblTongTien.setText(tongTienFinal + "");
                    lblTongTien2.setText(format.format(totalAmount.subtract(BigDecimal.valueOf(tienGG))));
                    if (tongTienFinal.compareTo(BigDecimal.valueOf(0)) < 0) {
                        txtTienTL.setText(format.format(tongTienFinal.multiply(BigDecimal.valueOf(-1))));
                    } else {
                        txtTienTL.setText(0 + "");
                    }
                    return;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập số");
                    return;
                }

            }
            if (txtCK.getText().trim().isEmpty()) {
                try {

                    tienTM = BigDecimal.valueOf(Float.parseFloat(txtTM.getText().trim()));
                    tienCK = BigDecimal.ZERO;
                    BigDecimal tienTra;
                    tienTra = tienTM;

                    tongTien = totalAmount.subtract(tienTra);
                    tongTienFinal = tongTien.subtract(BigDecimal.valueOf(tienGG));
                    lblTongTien.setText(tongTienFinal + "");
                    lblTongTien2.setText(format.format(totalAmount.subtract(BigDecimal.valueOf(tienGG))));
                    if (tongTienFinal.compareTo(BigDecimal.valueOf(0)) < 0) {
                        txtTienTL.setText(format.format(tongTienFinal.multiply(BigDecimal.valueOf(-1))));
                    } else {
                        txtTienTL.setText(0 + "");
                    }
                    return;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập số");
                    return;
                }

            } else if (!txtCK.getText().trim().isEmpty() && !txtTM.getText().trim().isEmpty()) {

                tienCK = BigDecimal.valueOf(Float.parseFloat(txtCK.getText().trim()));
                tienTM = BigDecimal.valueOf(Float.parseFloat(txtTM.getText().trim()));
                BigDecimal tienTra;
                tienTra = tienCK.add(tienTM);

                tongTien = totalAmount.subtract(tienTra);
                tongTienFinal = tongTien.subtract(BigDecimal.valueOf(tienGG));
                lblTongTien.setText(tongTienFinal + "");
                lblTongTien2.setText(format.format(totalAmount.subtract(BigDecimal.valueOf(tienGG))));
                if (tongTienFinal.compareTo(BigDecimal.valueOf(0)) < 0) {
                    txtTienTL.setText(format.format(tongTienFinal.multiply(BigDecimal.valueOf(-1))));
                } else {
                    txtTienTL.setText(0 + "");
                }
                return;
            }

        }
    }//GEN-LAST:event_cbbPGGActionPerformed

    private void cbbPTTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbPTTTActionPerformed
        String idHTTT = null;
        if (cbbPTTT.getSelectedIndex() == 0) {
            txtTM.setText("");
            txtCK.setText("");
            txtTM.setEditable(false);
            txtCK.setEditable(false);
        }

        if (cbbPTTT.getSelectedIndex() == 1) {
            txtCK.setText("");
            txtTM.setEditable(true);
            txtCK.setEditable(false);
            return;
        }
        if (cbbPTTT.getSelectedIndex() == 2) {
            txtTM.setText("");
            txtTM.setEditable(false);
            txtCK.setEditable(true);
        }
        if (cbbPTTT.getSelectedIndex() == 3) {
            txtTM.setEditable(true);
            txtCK.setEditable(true);
        }

    }//GEN-LAST:event_cbbPTTTActionPerformed

    private void roundedButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedButton5ActionPerformed
        KhachHangThongTin kh = new KhachHangThongTin(this);
        
        kh.setVisible(true);
        kh.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Ẩn JFrame 2 khi nút đóng được nhấn
                        

    }//GEN-LAST:event_roundedButton5ActionPerformed

    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked
        int rowSP = tblGioHang.getSelectedRow();
        GioHangViewModel sp = listGH.get(rowSP);

        if (evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1) { // Kiểm tra xem đã click 2 lần chưa
            System.out.println(sp.getIdHDCT());
            System.out.println("Đã click 2 lần");

            int soLuong = nhapSoLuong1();
            if (soLuong <= 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn số lớn hơn 0");
                return;

            }
            if (sp.getSoLuong() - soLuong < 0) {
                JOptionPane.showMessageDialog(this, "Quá giới hạn số lượng hàng");
                return;
            }

            if (sp.getSoLuong() - soLuong <= 0) {
                bhs.RemoveGH(txtIDHD.getText(), sp.getMaSanPham(), sp.getSoLuongSP() + soLuong);
                lists = bhs.getAll();
                dtm = (DefaultTableModel) tblSanPham.getModel();
                showData(lists);

                listHD = hds.getHD();
                dtmHD = (DefaultTableModel) tblHoaDon.getModel();
                showDataHD(listHD);

                dtmGH = (DefaultTableModel) tblGioHang.getModel();
                listGH = ghs.getGioHang(txtIDHD.getText());
                showDataTableGH(listGH);
                return;
            } else {
                // Sản phẩm đã tồn tại trong giỏ hàng, giảm số lượng đi 1
                bhs.UpdateTrungSP(txtIDHD.getText(), sp.getMaSanPham(), sp.getSoLuongSP() + soLuong, sp.getSoLuong() - soLuong);
                // Giảm đi 1 số lượng

            }
            lists = bhs.getAll();
            dtm = (DefaultTableModel) tblSanPham.getModel();
            showData(lists);

            listHD = hds.getHD();
            dtmHD = (DefaultTableModel) tblHoaDon.getModel();
            showDataHD(listHD);

            dtmGH = (DefaultTableModel) tblGioHang.getModel();
            listGH = ghs.getGioHang(txtIDHD.getText());
            showDataTableGH(listGH);
            return;

        }
    }//GEN-LAST:event_tblGioHangMouseClicked

    private void txtTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimActionPerformed

    private void txtIDKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDKHActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void txtIDHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDHDActionPerformed

    private void btnQuetQRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuetQRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnQuetQRActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private BotronPanel botronPanel1;
    private RoundedButton btnHuyHD;
    private RoundedButton btnLMHD;
    private RoundedButton btnQuetQR;
    private RoundedButton btnTaoHD;
    private RoundedButton btnThanhToan;
    private RoundedButton btnTim;
    private javax.swing.JComboBox<String> cbbPGG;
    private javax.swing.JComboBox<String> cbbPTTT;
    private combobox.Combobox cboCL;
    private combobox.Combobox cboKC;
    private combobox.Combobox cboM;
    private combobox.Combobox cboNXS;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblTongTien1;
    private javax.swing.JLabel lblTongTien2;
    private RoundedButton roundedButton5;
    private RoundedPanel roundedPanel1;
    private RoundedPanel roundedPanel2;
    private RoundedPanel roundedPanel3;
    private RoundedPanel roundedPanel4;
    private RoundedPanel roundedPanel5;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtCK;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtIDCTSP;
    private javax.swing.JTextField txtIDHD;
    private javax.swing.JTextField txtIDKH;
    private javax.swing.JTextField txtMHD;
    private javax.swing.JTextField txtMKH;
    private javax.swing.JTextField txtMNV;
    private javax.swing.JTextField txtNT;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTKH;
    private javax.swing.JTextField txtTM;
    private javax.swing.JTextField txtTToan;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTienTL;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
