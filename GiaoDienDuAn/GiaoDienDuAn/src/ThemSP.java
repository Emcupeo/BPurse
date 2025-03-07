/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
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
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author My PC
 */
public class ThemSP extends javax.swing.JFrame {

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
     * Creates new form ThemSP
     */
    public ThemSP() {
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
    //    dbo.ChiTietSanPham.ID,
//                    dbo.ChiTietSanPham.idSanPham, 
//                    dbo.SanPham.TenSanPham,
//                    dbo.ChiTietSanPham.MoTa, 
//                    dbo.ChiTietSanPham.SoLuong, 
//                    dbo.ChiTietSanPham.GiaNhap, 
//                    dbo.ChiTietSanPham.GiaBan, 
//                    dbo.ChatLieu.TenChatLieu,
//                    dbo.MauSac.TenMauSac, 
//                    dbo.PhuKien.LoaiPhuKien, 
//                    dbo.LoaiQuai.TenLoaiQuai, 
//                    dbo.ThuongHieu.ThuongHieu, 
//                    dbo.PhanLoai.PhanLoai,
//                    dbo.KichCo.TenKichCo

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

        jLabel8 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        txtMoTa2 = new javax.swing.JTextField();
        cbbTinhTrang = new javax.swing.JComboBox<>();
        btnThem1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        cbbTenSP = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        cbbNSX = new javax.swing.JComboBox<>();
        txtGiaNhap = new javax.swing.JTextField();
        cbbChatLieu = new javax.swing.JComboBox<>();
        cbbMauSac = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        cbbPhuKien = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        cbbLoaiQuai = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        cbbThuongHieu = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        cbbPhanLoai = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        cbbKichCo = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel8.setText("Mô tả");

        txtMoTa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMoTa2ActionPerformed(evt);
            }
        });

        cbbTinhTrang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnThem1.setText("Thêm");
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });

        jLabel9.setText("Chất liệu");

        jLabel23.setText("Tình trạng");

        jLabel10.setText("Số lượng");

        jLabel7.setText("Tên sản phẩm");

        cbbTenSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setText("Màu sắc");

        jLabel12.setText("Giá nhập");

        jLabel25.setText("Nhà sản xuất");

        cbbNSX.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMauSacActionPerformed(evt);
            }
        });

        jLabel13.setText("Phụ kiện");

        cbbPhuKien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel16.setText("Loại quai");

        cbbLoaiQuai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel19.setText("Thương hiệu");

        cbbThuongHieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbThuongHieuActionPerformed(evt);
            }
        });

        jLabel20.setText("Phân loại");

        cbbPhanLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel21.setText("Kích cỡ");

        cbbKichCo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel22.setText("Giá bán");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(25, 25, 25)
                .addComponent(cbbTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(630, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel23)
                            .addGap(73, 73, 73)
                            .addComponent(cbbTinhTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtMoTa2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel10)
                                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(34, 34, 34)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel12)
                                        .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(38, 38, 38)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel22))))
                            .addGap(20, 20, 20)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel19)
                                        .addComponent(jLabel16))
                                    .addGap(37, 37, 37)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cbbLoaiQuai, 0, 74, Short.MAX_VALUE)
                                        .addComponent(cbbThuongHieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addGap(57, 57, 57)
                                    .addComponent(cbbPhuKien, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addGap(57, 57, 57)
                                    .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addGap(58, 58, 58)
                                    .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(118, 118, 118)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel21)
                                .addComponent(jLabel20)
                                .addComponent(jLabel25))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cbbPhanLoai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbbKichCo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbbNSX, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(78, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
                .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(61, 61, 61)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(30, 30, 30)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(cbbPhuKien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(29, 29, 29)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(50, 50, 50)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel19)
                                        .addComponent(cbbThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel16)
                                .addComponent(cbbLoaiQuai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8)
                            .addGap(4, 4, 4)
                            .addComponent(txtMoTa2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(15, 15, 15)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(jLabel12)
                                .addComponent(jLabel22))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(64, 64, 64)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel21)
                                .addComponent(cbbKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(101, 101, 101)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbbNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel25))))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbbTinhTrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel23))
                    .addGap(17, 17, 17)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
          if ( 0 >= Integer.parseInt(txtSoLuong.getText().trim()) ) {
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
         
        if ( 0 >= Float.parseFloat(txtGiaNhap.getText()) ) {
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
        
        if ( 0 >= Float.parseFloat(txtGiaBan.getText()) ) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập > 0");
            return;
        }
        int check = JOptionPane.showConfirmDialog(this, "thêm?");
        if(check == JOptionPane.YES_OPTION){
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
        JOptionPane.showMessageDialog(this,"đã thêm mới");
        }
        else{
            JOptionPane.showMessageDialog(this,"đã thoát");
            return;
        }
        
    }//GEN-LAST:event_btnThem1ActionPerformed

    private void cbbMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMauSacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbMauSacActionPerformed

    private void cbbThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbThuongHieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbThuongHieuActionPerformed

    private void txtMoTa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMoTa2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMoTa2ActionPerformed

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
            java.util.logging.Logger.getLogger(ThemSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThemSP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThem1;
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtMoTa2;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
