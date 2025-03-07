/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

import bpurse.qlts.entity.ChatLieu;
import bpurse.qlts.entity.ChiTietSanPham;
import bpurse.qlts.entity.KichCo;
import bpurse.qlts.entity.LoaiQuai;
import bpurse.qlts.entity.MauSac;
import bpurse.qlts.entity.NhaSanXuat;
import bpurse.qlts.entity.PhanLoai;
import bpurse.qlts.entity.PhuKien;
import bpurse.qlts.entity.ThuongHieu;
import bpurse.qlts.entity.TinhTrang;
import bpurse.qlts.service.ChatLieuService;
import bpurse.qlts.service.ChiTietSanPhamService;
import bpurse.qlts.service.KichCoService;
import bpurse.qlts.service.LoaiQuaiService;
import bpurse.qlts.service.MauSacService;
import bpurse.qlts.service.NhaSanXuatService;
import bpurse.qlts.service.PhanLoaiService;
import bpurse.qlts.service.PhuKienService;
import bpurse.qlts.service.SanPhamService1;
import bpurse.qlts.service.SanPhamService2;
import bpurse.qlts.service.ThuonngHieuService;
import bpurse.qlts.service.TinhTrangService;
import bpurse.qlts.viewmodel.SanPhamViewmodel1;
import bpurse.qlts.viewmodel.CTSPViewmodel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author ASUS
 */
public class ThemCTSP extends javax.swing.JPanel {

    private List<SanPhamViewmodel1> list = new ArrayList<>();
    private List<CTSPViewmodel> list2 = new ArrayList<>();
    private List<CTSPViewmodel> listTenSP = new ArrayList<>();
    private List<ChiTietSanPham> listctsp = new ArrayList<>();
    private List<ChiTietSanPham> listMaCTSP = new ArrayList<>();

    private DefaultTableModel dtm1 = new DefaultTableModel();
    private DefaultTableModel dtm2 = new DefaultTableModel();
    private SanPhamService1 service = new SanPhamService1();
    private SanPhamService2 service2 = new SanPhamService2();
    private ChiTietSanPhamService servicectsp = new ChiTietSanPhamService();

    private List<ChatLieu> listcl = new ArrayList<>();
    private List<MauSac> listms = new ArrayList<>();
    private List<PhuKien> listpk = new ArrayList<>();
    private List<LoaiQuai> listlq = new ArrayList<>();
    private List<ThuongHieu> listth = new ArrayList<>();
    private List<PhanLoai> listpl = new ArrayList<>();
    private List<KichCo> listkc = new ArrayList<>();
    private List<TinhTrang> listtt = new ArrayList<>();
    private List<NhaSanXuat> listnsx = new ArrayList<>();

    private DefaultComboBoxModel dcbm1 = new DefaultComboBoxModel();
    private DefaultComboBoxModel dcbmMS = new DefaultComboBoxModel();
    private DefaultComboBoxModel dcbmPK = new DefaultComboBoxModel();
    private DefaultComboBoxModel dcbmLQ = new DefaultComboBoxModel();
    private DefaultComboBoxModel dcbmTH = new DefaultComboBoxModel();
    private DefaultComboBoxModel dcbmPL = new DefaultComboBoxModel();
    private DefaultComboBoxModel dcbmKC = new DefaultComboBoxModel();
    private DefaultComboBoxModel dcbmctsp = new DefaultComboBoxModel();
    private DefaultComboBoxModel dcbmTenSP = new DefaultComboBoxModel();
    private DefaultComboBoxModel dcbmMaCTSP = new DefaultComboBoxModel();
    private DefaultComboBoxModel dcbmTT = new DefaultComboBoxModel();
    private DefaultComboBoxModel dcbmNSX = new DefaultComboBoxModel();

    private PhuKienService servicepk = new PhuKienService();
    private ChatLieuService clservice = new ChatLieuService();
    private MauSacService servicems = new MauSacService();
    private LoaiQuaiService servicelq = new LoaiQuaiService();
    private ThuonngHieuService serviceth = new ThuonngHieuService();
    private PhanLoaiService servicepl = new PhanLoaiService();
    private KichCoService servicekc = new KichCoService();
    private TinhTrangService servicett = new TinhTrangService();
    private NhaSanXuatService servicensx = new NhaSanXuatService();

    
    
    
    /**
     * Creates new form ThemCTSP
     */
    public ThemCTSP() {
        initComponents();
        list = service.getAll();
        list2 = service2.getAll();
        listctsp = servicectsp.getID();
        listTenSP = service2.getTenSP();

        listcl = clservice.getTenCL();
        listms = servicems.getTenMS();
        listpk = servicepk.getTenPK();
        listlq = servicelq.getTenLQ();
        listth = serviceth.getTenTH();
        listpl = servicepl.getTenPL();
        listkc = servicekc.getTenKC();
        listMaCTSP = servicectsp.getMaCTSP();
        listtt = servicett.getTenTT();
        listnsx = servicensx.getTenNSX();

        dcbm1 = (DefaultComboBoxModel) cbbChatLieu.getModel();
        dcbmMS = (DefaultComboBoxModel) cbbMauSac.getModel();
        dcbmPK = (DefaultComboBoxModel) cbbPhuKien.getModel();
        dcbmLQ = (DefaultComboBoxModel) cbbLoaiQuai.getModel();
        dcbmTH = (DefaultComboBoxModel) cbbThuongHieu.getModel();
        dcbmPL = (DefaultComboBoxModel) cbbPhanLoai.getModel();
        dcbmKC = (DefaultComboBoxModel) cbbKichCo.getModel();

        dcbmTenSP = (DefaultComboBoxModel) cbbTenSP.getModel();

        dcbmTT = (DefaultComboBoxModel) cbbTinhTrang.getModel();
        dcbmNSX = (DefaultComboBoxModel) cbbNSX.getModel();

        showCBBCL();
        showCBBMS();
        showCBBPK();
        showCBBLQ();
        showCBBTH();
        showCBBPL();
        showCBBKC();
        showCBBiD();
        showCBBtenSP();
        showCBBMaCTSP();
        showCBBTinhTrang();
        showCBBNSX();
        
        
   
    }

    public void showCBBCL() {
        dcbm1.removeAllElements();
        for (ChatLieu cl : listcl) {
            dcbm1.addElement(cl.getTenChatLieu());
        }
//        dcbm1.addElement(listcl);
    }

    public void showCBBMS() {
        dcbmMS.removeAllElements();
        for (MauSac ms : listms) {
            dcbmMS.addElement(ms.getTenMauSac());
        }
//        dcbmMS.addElement(listms);
    }

    public void showCBBPK() {
        dcbmPK.removeAllElements();
        for (PhuKien pk : listpk) {
            dcbmPK.addElement(pk.getTenPhuKien());
        }
    }

    public void showCBBLQ() {
        dcbmLQ.removeAllElements();
        for (LoaiQuai lq : listlq) {
            dcbmLQ.addElement(lq.getTenLoaiQuai());
        }
    }

    public void showCBBTH() {
        dcbmTH.removeAllElements();
        for (ThuongHieu th : listth) {
            dcbmTH.addElement(th.getTenThuongHieu());
        }
    }

    public void showCBBPL() {
        dcbmPL.removeAllElements();
        for (PhanLoai pl : listpl) {
            dcbmPL.addElement(pl.getTenPhanLoai());
        }
    }

    public void showCBBKC() {
        dcbmKC.removeAllElements();
        for (KichCo kc : listkc) {
            dcbmKC.addElement(kc.getTenKichCo());
        }
    }

    public void showCBBiD() {
        dcbmctsp.removeAllElements();
        for (ChiTietSanPham ctsp : listctsp) {
            dcbmctsp.addElement(ctsp.getmaSP());
        }
    }

    public void showCBBtenSP() {
        dcbmTenSP.removeAllElements();
        for (CTSPViewmodel ctsp : listTenSP) {
            dcbmTenSP.addElement(ctsp.getTenSP2());
        }
    }

    public void showCBBMaCTSP() {
        dcbmMaCTSP.removeAllElements();
        for (ChiTietSanPham cl : listMaCTSP) {
            dcbmMaCTSP.addElement(cl.getID());
        }

    }

    public void showCBBTinhTrang() {
        dcbmTT.removeAllElements();
        for (TinhTrang cl : listtt) {
            dcbmTT.addElement(cl.getTenTinhTrang());
        }
//        dcbm1.addElement(listcl);
    }

    public void showCBBNSX() {
        dcbmNSX.removeAllElements();
        for (NhaSanXuat cl : listnsx) {
            dcbmNSX.addElement(cl.getTenNhaSanXuat());
        }
//        dcbm1.addElement(listcl);
    }

    public void fillTable2(int row) {
        CTSPViewmodel sp2 = list2.get(row);
        txtMoTa2.setText(sp2.getMoTa2());
        txtSoLuong.setText(sp2.getSoLuong2() + "");
        txtGiaNhap.setText(sp2.getGiaNhap() + "");
        txtGiaBan.setText(sp2.getGiaBan() + "");
        cbbChatLieu.setSelectedItem(sp2.getChatLieu() + "");
        cbbPhuKien.setSelectedItem(sp2.getPhuKien() + "");
        cbbLoaiQuai.setSelectedItem(sp2.getLoaiQuai() + "");
        cbbThuongHieu.setSelectedItem(sp2.getThuongHieu() + "");
        cbbPhanLoai.setSelectedItem(sp2.getPhanLoai() + "");
        cbbKichCo.setSelectedItem(sp2.getKichCo() + "");

        cbbTinhTrang.setSelectedItem(sp2.getTinhTrang() + "");
        cbbTenSP.setSelectedItem(sp2.getTenSP2() + "");
        cbbNSX.setSelectedItem(sp2.getNhaSanXuat());

    }

    public bpurse.qlts.entity.ChiTietSanPham getFromDataCTSP() {

        String nhaSanXuat = cbbNSX.getSelectedItem() + "";
        String chatLieu = cbbChatLieu.getSelectedItem() + "";
        String mauSac = cbbMauSac.getSelectedItem() + "";
        String phuKien = cbbPhuKien.getSelectedItem() + "";
        String loaiQuai = cbbLoaiQuai.getSelectedItem() + "";
        String thuongHieu = cbbThuongHieu.getSelectedItem() + "";
        String phanLoai = cbbPhanLoai.getSelectedItem() + "";
        String kichCo = cbbKichCo.getSelectedItem() + "";
        String tinhTrang = cbbTinhTrang.getSelectedItem() + "";
        int soLuong = Integer.parseInt(txtSoLuong.getText().trim());
        String moTa = txtMoTa2.getText().trim();
        float giaNhap = Float.parseFloat(txtGiaNhap.getText().trim());
        float giaBan = Float.parseFloat(txtGiaBan.getText().trim());

        bpurse.qlts.entity.ChiTietSanPham ctsp = new ChiTietSanPham(nhaSanXuat, chatLieu, mauSac,
                phuKien, loaiQuai, thuongHieu, phanLoai, kichCo, tinhTrang, soLuong, moTa, giaNhap, giaBan);
        return ctsp;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AddCTSP = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbbTenSP = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtMoTa2 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        cbbTinhTrang = new javax.swing.JComboBox<>();
        txtGiaNhap = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        cbbChatLieu = new javax.swing.JComboBox<>();
        cbbMauSac = new javax.swing.JComboBox<>();
        cbbPhuKien = new javax.swing.JComboBox<>();
        cbbLoaiQuai = new javax.swing.JComboBox<>();
        cbbThuongHieu = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        cbbNSX = new javax.swing.JComboBox<>();
        cbbKichCo = new javax.swing.JComboBox<>();
        cbbPhanLoai = new javax.swing.JComboBox<>();
        btnThem1 = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();

        jLabel9.setText("Chất liệu");

        jLabel13.setText("Màu sắc");

        jLabel14.setText("Phụ kiện");

        jLabel16.setText("Loại quai");

        jLabel19.setText("Thương hiệu");

        jLabel7.setText("Tên sản phẩm");

        cbbTenSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Mô tả");

        txtMoTa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMoTa2ActionPerformed(evt);
            }
        });

        jLabel11.setText("Số lượng");

        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });

        jLabel23.setText("Tình trạng");

        cbbTinhTrang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbTinhTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTinhTrangActionPerformed(evt);
            }
        });

        jLabel12.setText("Giá nhập");

        jLabel22.setText("Giá bán");

        cbbChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMauSacActionPerformed(evt);
            }
        });

        cbbPhuKien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbLoaiQuai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbThuongHieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbThuongHieuActionPerformed(evt);
            }
        });

        jLabel20.setText("Phân loại");

        jLabel21.setText("Kích cỡ");

        jLabel25.setText("Nhà sản xuất");

        cbbNSX.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbKichCo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbPhanLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnThem1.setText("Thêm");
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addGap(33, 33, 33)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(3, 3, 3)
                                    .addComponent(jLabel12))
                                .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(36, 36, 36)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel22)
                                .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(cbbTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8)
                        .addComponent(txtMoTa2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbbTinhTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addGap(230, 230, 230)))
                .addGap(100, 100, 100)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel19)
                                    .addGap(44, 44, 44))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel16)
                                    .addGap(62, 62, 62)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel13))
                                .addGap(64, 64, 64)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbbThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbChatLieu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbLoaiQuai, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbMauSac, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(157, 157, 157)
                                        .addComponent(cbbNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(64, 64, 64)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel21)
                                            .addComponent(jLabel25)
                                            .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel20)
                                        .addGap(182, 182, 182))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(cbbPhuKien, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbbPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(100, 100, 100))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(jLabel9)
                                .addGap(30, 30, 30))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel7)
                                .addGap(27, 27, 27)
                                .addComponent(cbbTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20))
                                .addGap(135, 135, 135)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(cbbThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(txtMoTa2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel22))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbTinhTrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(cbbPhuKien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(cbbKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbLoaiQuai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        btnThoat.setText("THOÁT");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AddCTSPLayout = new javax.swing.GroupLayout(AddCTSP);
        AddCTSP.setLayout(AddCTSPLayout);
        AddCTSPLayout.setHorizontalGroup(
            AddCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddCTSPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AddCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        AddCTSPLayout.setVerticalGroup(
            AddCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddCTSPLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AddCTSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AddCTSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtMoTa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMoTa2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMoTa2ActionPerformed

    private void cbbTinhTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTinhTrangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbTinhTrangActionPerformed

    private void cbbMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMauSacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbMauSacActionPerformed

    private void cbbThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbThuongHieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbThuongHieuActionPerformed

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        if (txtMoTa2.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng ko để trống mô tả");
            return;
        }
        if (txtSoLuong.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng ko để trống SL ");
            return;
        }
        try {
            int soluong = Integer.parseInt(txtSoLuong.getText().trim());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "nhập số");
            return;
        }
        if (0 >= Integer.parseInt(txtSoLuong.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập > 0");
            return;
        }

        if (txtGiaNhap.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng ko để trống ");
            return;
        }

        try {
            float gianhap = Float.parseFloat(txtGiaNhap.getText().trim());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "nhập số");
            return;
        }

        if (0 >= Float.parseFloat(txtGiaNhap.getText())) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập > 0");
            return;
        }
        if (txtGiaBan.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng ko để trống");
            return;
        }
        try {
            float giaban = Float.parseFloat(txtGiaBan.getText().trim());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "nhập số");
            return;
        }

        if (0 >= Float.parseFloat(txtGiaBan.getText())) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập > 0");
            return;
        }
        int check = JOptionPane.showConfirmDialog(this, "thêm?");
        if (check == JOptionPane.YES_OPTION) {
            listctsp = servicectsp.getID();
            showCBBiD();
            servicectsp.addCTSP(getFromDataCTSP(), cbbTenSP.getSelectedItem() + "");
            SanPham sp = new SanPham();

            list = service.getAll();
            sp.showDataTable1(list);

            list2 = service2.getAll();
            sp.showDataTable2(service2.getAll());

            list2 = service2.getTenSP();
            showCBBtenSP();

            listMaCTSP = servicectsp.getMaCTSP();
            showCBBMaCTSP();

            listctsp = servicectsp.getID();
            showCBBiD();
            JOptionPane.showMessageDialog(this, "đã thêm mới");
        } else {
            JOptionPane.showMessageDialog(this, "đã thoát");
            return;
        }

    }//GEN-LAST:event_btnThem1ActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
          System.out.println("Button Quay lại được nhấn.");
        SanPham sanpham = new SanPham();
        JPanel paneSPCT = sanpham.getPanelSPCT();
        JPanel viewThemThuocTinhSP = AddCTSP;
        viewThemThuocTinhSP.removeAll();
        viewThemThuocTinhSP.add(paneSPCT);
        viewThemThuocTinhSP.setLayout(new BorderLayout());
        viewThemThuocTinhSP.add(paneSPCT, BorderLayout.CENTER);
        viewThemThuocTinhSP.revalidate();
        viewThemThuocTinhSP.repaint();
    }//GEN-LAST:event_btnThoatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AddCTSP;
    private javax.swing.JButton btnThem1;
    private javax.swing.JButton btnThoat;
    private javax.swing.JComboBox<String> cbbChatLieu;
    private javax.swing.JComboBox<String> cbbKichCo;
    private javax.swing.JComboBox<String> cbbLoaiQuai;
    private javax.swing.JComboBox<String> cbbMauSac;
    private javax.swing.JComboBox<String> cbbNSX;
    private javax.swing.JComboBox<String> cbbPhanLoai;
    private javax.swing.JComboBox<String> cbbPhuKien;
    private javax.swing.JComboBox<String> cbbTenSP;
    private javax.swing.JComboBox<String> cbbThuongHieu;
    private javax.swing.JComboBox<String> cbbTinhTrang;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtMoTa2;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
