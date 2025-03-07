
import NhanVien.QRCode.WebCam;
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
import bpurse.qlts.service.ThuocTinhService;
import bpurse.qlts.service.ThuonngHieuService;
import bpurse.qlts.service.TinhTrangService;
import bpurse.qlts.viewmodel.SanPhamViewmodel1;
import bpurse.qlts.viewmodel.CTSPViewmodel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

public class SanPham extends javax.swing.JInternalFrame {

    private DecimalFormat format = new DecimalFormat("");
    private DecimalFormat format1 = new DecimalFormat("0");
    
    private List<SanPhamViewmodel1> list = new ArrayList<>();

    private List<CTSPViewmodel> list2 = new ArrayList<>();
    private List<ChiTietSanPham> listctsp = new ArrayList<>();
    private List<ChiTietSanPham> listMaCTSP = new ArrayList<>();

    private DefaultTableModel dtm1 = new DefaultTableModel();
    private DefaultTableModel dtm2 = new DefaultTableModel();
    private SanPhamService1 service = new SanPhamService1();
    private SanPhamService2 service2 = new SanPhamService2();
    private ChiTietSanPhamService servicectsp = new ChiTietSanPhamService();
    private ThuocTinhService ttservice = new ThuocTinhService();

    private List<ChatLieu> listcl = new ArrayList<>();
    private List<MauSac> listms = new ArrayList<>();
    private List<PhuKien> listpk = new ArrayList<>();
    private List<LoaiQuai> listlq = new ArrayList<>();
    private List<ThuongHieu> listth = new ArrayList<>();
    private List<PhanLoai> listpl = new ArrayList<>();
    private List<KichCo> listkc = new ArrayList<>();
    private List<TinhTrang> listtt = new ArrayList<>();
    private List<NhaSanXuat> listnsx = new ArrayList<>();
    //
    private DefaultTableModel dtmNSX = new DefaultTableModel();
    private DefaultTableModel dtmCL = new DefaultTableModel();
    private DefaultTableModel dtmMS = new DefaultTableModel();
    private DefaultTableModel dtmLQ = new DefaultTableModel();
    private DefaultTableModel dtmPL = new DefaultTableModel();
    private DefaultTableModel dtmTH = new DefaultTableModel();
    private DefaultTableModel dtmPK = new DefaultTableModel();
    private DefaultTableModel dtmKC = new DefaultTableModel();
    private DefaultTableModel dtmTT = new DefaultTableModel();
    //

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
     *
     * Creates new form Menu1
     */
    public SanPham() {

        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        ////////////////////////
        getContentPane().setBackground(new Color(255, 255, 255));
        ///////////////////////
        txtMaSP.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtTenSP.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtSearch.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtGiaBan.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        ///========
        txtMaThuocTinh.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtTenThuocTinh.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        //==================================CTSP
        txtSoLuong.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtGiaNhap.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        ///================================
        JTableHeader header1 = tblSanPham.getTableHeader();
        header1.setDefaultRenderer(new centerTable());

        tblSanPham.setDefaultEditor(Object.class, null);
        tblSanPham2.setDefaultEditor(Object.class, null);
        tblThuocTinhSP.setDefaultEditor(Object.class, null);
//        detailDongVat(1);

//==============================================
        JTableHeader header2 = tblThuocTinhSP.getTableHeader();
        header2.setDefaultRenderer(new centerTable());

        // LichSu
        for (int i = 0; i < tblSanPham2.getColumnCount(); i++) {
            tblSanPham2.getColumnModel().getColumn(i).setCellRenderer(new centerr());
        }

        // Set the custom header renderer
        JTableHeader header3 = tblSanPham2.getTableHeader();
        header3.setDefaultRenderer(new centerTablee());

        list = service.getAll();
        list2 = service2.getAll();
        listctsp = servicectsp.getID();
        dtm1 = (DefaultTableModel) tblSanPham.getModel();
        dtm2 = (DefaultTableModel) tblSanPham2.getModel();

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
        //
        dtmNSX = (DefaultTableModel) tblThuocTinhSP.getModel();
        dtmCL = (DefaultTableModel) tblThuocTinhSP.getModel();
        dtmMS = (DefaultTableModel) tblThuocTinhSP.getModel();
        dtmLQ = (DefaultTableModel) tblThuocTinhSP.getModel();
        dtmPL = (DefaultTableModel) tblThuocTinhSP.getModel();
        dtmTH = (DefaultTableModel) tblThuocTinhSP.getModel();
        dtmKC = (DefaultTableModel) tblThuocTinhSP.getModel();
        dtmTT = (DefaultTableModel) tblThuocTinhSP.getModel();
        dtmPK = (DefaultTableModel) tblThuocTinhSP.getModel();
        //

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

        showDataTable1(list);
        showDataTable2(list2);
        rdoNXS.isSelected();
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

        fillTable2(3);

        /////search sp////
        btnQuetQR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WebCam webCam = new WebCam();
                webCam.addIDListener(new WebCam.IDListener() {
                    @Override
                    public void onIDReceived(String idHD) {
                        // Xử lý ID ở đây
                        list2 = service2.getAll2(idHD);
                        showDataTable2(list2);
                        fillTable2(0);
                        tblSanPham2.setRowSelectionInterval(0, 0);

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

        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(txtSearch.getText().trim().isEmpty()){
                    list = service.getAll();
                    showDataTable1(list);
                    return;
                }
                service.search(txtSearch.getText());
                list = service.search(txtSearch.getText());
                showDataTable1(list);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(txtSearch.getText().trim().isEmpty()){
                    list = service.getAll();
                    showDataTable1(list);
                    return;
                }
                service.search(txtSearch.getText());
                list = service.search(txtSearch.getText());
                showDataTable1(list);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if(txtSearch.getText().trim().isEmpty()){
                    list = service.getAll();
                    showDataTable1(list);
                    return;
                }
                service.search(txtSearch.getText());
                list = service.search(txtSearch.getText());
                showDataTable1(list);
            }

        });
        //// search ctsp////
        txtSearch2.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                                
                service2.search2(txtSearch2.getText().trim());
                list2 = service2.search2(txtSearch2.getText().trim());
                showDataTable2(list2);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                service2.search2(txtSearch2.getText().trim());
                list2 = service2.search2(txtSearch2.getText().trim());
                showDataTable2(list2);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                service2.search2(txtSearch2.getText().trim());
                list2 = service2.search2(txtSearch2.getText().trim());
                showDataTable2(list2);
            }

        });

    }

    private void clear() {
        txtMaThuocTinh.setText("");
        txtTenThuocTinh.setText("");
    }

    public void showDataTable1(List<SanPhamViewmodel1> spm) {
        int stt = 0;
        dtm1.setRowCount(0);
        for (SanPhamViewmodel1 sanPhamViewmodel1 : spm) {
            stt++;
            dtm1.addRow(new Object[]{
                stt,
                sanPhamViewmodel1.getMaSP(),
                sanPhamViewmodel1.getTenSP(),
                sanPhamViewmodel1.getSoLuong(),
                sanPhamViewmodel1.getTrangThai()
            });
        }
    }

    public void showDataTable2(List<CTSPViewmodel> spm2) {
        int stt = 0;
        String hang = "";
        dtm2.setRowCount(0);
        for (CTSPViewmodel sp2 : spm2) {
            stt++;
            if (sp2.getSoLuong2() > 0) {
                hang = "Còn hàng";
            } else {
                hang = "Hết hàng";
            }

            dtm2.addRow(new Object[]{
                stt,
                sp2.getMaSP2(),
                sp2.getTenSP2(),
                sp2.getSoLuong2(),
                format.format(sp2.getGiaNhap()),
                format.format(sp2.getGiaBan()),
                sp2.getChatLieu(),
                sp2.getMauSac(),
                sp2.getPhuKien(),
                sp2.getLoaiQuai(),
                sp2.getThuongHieu(),
                sp2.getPhanLoai(),
                sp2.getKichCo(),
                sp2.getTinhTrang(),
                hang
            });
        }
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
        for (CTSPViewmodel ctsp : list2) {
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

    //
    public void showDataTableCL(List<ChatLieu> listCL) {
        int stt = 0;
        dtmTT.setRowCount(0);
        for (ChatLieu cl : listCL) {
            stt++;
            dtmTT.addRow(new Object[]{
                stt,
                cl.getMaCL(),
                cl.getTenChatLieu()
            });
        }
    }

    public void showDataTableNSX(List<NhaSanXuat> listnsx) {
        int stt = 0;
        dtmTT.setRowCount(0);
        for (NhaSanXuat cl : listnsx) {
            stt++;
            dtmTT.addRow(new Object[]{
                stt,
                cl.getMaNSX(),
                cl.getTenNhaSanXuat(),});
        }
    }

    public void showDataTableMS(List<MauSac> listms) {
        int stt = 0;
        dtmTT.setRowCount(0);
        for (MauSac cl : listms) {
            stt++;
            dtmTT.addRow(new Object[]{
                stt,
                cl.getMaMauSac(),
                cl.getTenMauSac()
            });
        }
    }

    public void showDataTableLQ(List<LoaiQuai> listLQ) {
        int stt = 0;
        dtmTT.setRowCount(0);
        for (LoaiQuai cl : listLQ) {
            stt++;
            dtmTT.addRow(new Object[]{
                stt,
                cl.getMaLoaiQuai(),
                cl.getTenLoaiQuai()
            });
        }
    }

    public void showDataTablePL(List<PhanLoai> listPL) {
        int stt = 0;
        dtmTT.setRowCount(0);
        for (PhanLoai cl : listPL) {
            stt++;
            dtmTT.addRow(new Object[]{
                stt,
                cl.getMaPhanLoai(),
                cl.getTenPhanLoai()
            });
        }
    }

    public void showDataTableTH(List<ThuongHieu> listTH) {
        int stt = 0;
        dtmTT.setRowCount(0);
        for (ThuongHieu cl : listTH) {
            stt++;
            dtmTT.addRow(new Object[]{
                stt,
                cl.getMaThuongHieu(),
                cl.getTenThuongHieu()
            });
        }
    }

    public void showDataTablePK(List<PhuKien> listPK) {
        int stt = 0;
        dtmTT.setRowCount(0);
        for (PhuKien cl : listPK) {
            stt++;
            dtmTT.addRow(new Object[]{
                stt,
                cl.getMaPhuKien(),
                cl.getTenPhuKien()
            });
        }
    }

    public void showDataTableKC(List<KichCo> listKC) {
        int stt = 0;
        dtmTT.setRowCount(0);
        for (KichCo cl : listKC) {
            stt++;
            dtmTT.addRow(new Object[]{
                stt,
                cl.getMaKichCo(),
                cl.getTenKichCo()
            });
        }
    }

    public void showDataTableTT(List<TinhTrang> listTT) {
        int stt = 0;
        dtmTT.setRowCount(0);
        for (TinhTrang cl : listTT) {
            stt++;
            dtmTT.addRow(new Object[]{
                stt,
                cl.getMaTinhTrang(),
                cl.getTenTinhTrang()
            });
        }
    }
    //

    public void fillTable1(int row) {
        SanPhamViewmodel1 sp1 = list.get(row);
        txtMaSP.setText(sp1.getMaSP());
        txtTenSP.setText(sp1.getTenSP());
//        String trangThai = sp1.getTrangThai().trim();
//        if(trangThai.equalsIgnoreCase( "Ngung ban")){
//            rdoNgungBan.setSelected(true);
//        }
//        else if(trangThai.equalsIgnoreCase( "Dang ban")){
//            rdoDangBan.setSelected(true);
//        }
//    }
    }

    public void fillTable2(int row) {
        CTSPViewmodel sp2 = list2.get(row);
        txtMoTa2.setText(sp2.getMoTa2());
        txtSoLuong.setText(sp2.getSoLuong2() + "");
        txtGiaNhap.setText(format1.format(sp2.getGiaNhap()) + "");
        txtGiaBan.setText(format1.format(sp2.getGiaBan()) + "");
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

    public bpurse.qlts.entity.SanPham getFromData() {

        String tenSP = txtTenSP.getText().trim();

        bpurse.qlts.entity.SanPham sp = new bpurse.qlts.entity.SanPham(tenSP);
        return sp;
    }

    //////////////// GETFORM DATA
    public NhaSanXuat getFormNhaSanXuat() {
        String ma = txtMaThuocTinh.getText();
        String ten = txtTenThuocTinh.getText();

        NhaSanXuat nsx = new NhaSanXuat(ten, ma);
        return nsx;
    }

    public PhanLoai getFormPhanLoai() {
        String ma = txtMaThuocTinh.getText();
        String ten = txtTenThuocTinh.getText();

        PhanLoai pl = new PhanLoai(ma, ten);
        return pl;
    }

    public ChatLieu getFormchaChatLieu() {
        String ma = txtMaThuocTinh.getText();
        String ten = txtTenThuocTinh.getText();

        ChatLieu cl = new ChatLieu(ma, ten);
        return cl;
    }

    public MauSac getFormMauSac() {
        String ma = txtMaThuocTinh.getText();
        String ten = txtTenThuocTinh.getText();

        MauSac ms = new MauSac(ma, ten);
        return ms;
    }

    public PhuKien getFormKien() {
        String ma = txtMaThuocTinh.getText();
        String ten = txtTenThuocTinh.getText();

        PhuKien pk = new PhuKien(ma, ten);
        return pk;
    }

    public LoaiQuai getFormLoaiQuai() {
        String ma = txtMaThuocTinh.getText();
        String ten = txtTenThuocTinh.getText();

        LoaiQuai lq = new LoaiQuai(ma, ten);
        return lq;
    }

    public ThuongHieu getFormThuongHieu() {
        String ma = txtMaThuocTinh.getText();
        String ten = txtTenThuocTinh.getText();

        ThuongHieu th = new ThuongHieu(ma, ten);
        return th;
    }

    public KichCo getFormKichCo() {
        String ma = txtMaThuocTinh.getText();
        String ten = txtTenThuocTinh.getText();

        KichCo kc = new KichCo(ma, ten);
        return kc;
    }

    public TinhTrang getFormTinhTrang() {
        String ma = txtMaThuocTinh.getText();
        String ten = txtTenThuocTinh.getText();

        TinhTrang tt = new TinhTrang(ma, ten);
        return tt;
    }

    ////////////////
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

// LichSu
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

    private static class centerTableee extends DefaultTableCellRenderer {

        public centerTableee() {
            setHorizontalAlignment(SwingConstants.CENTER);
        }
    }

    private static class centerTable extends DefaultTableCellRenderer {

        public centerTable() {
            setHorizontalAlignment(SwingConstants.CENTER);
        }

    }

    public JPanel getPanelSPCT() {
        return viewCTSP;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgThuocTinh = new javax.swing.ButtonGroup();
        btgTrangThai = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        viewSanPham = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        btnXoa = new RoundedButton();
        jLabel7 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        btnLamMoi = new RoundedButton();
        btnThem = new RoundedButton();
        btnUpdate = new RoundedButton();
        viewCTSP = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        roundedPanel1 = new RoundedPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cbbMauSac = new combobox.Combobox();
        cbbThuongHieu = new combobox.Combobox();
        cbbTenSP = new combobox.Combobox();
        cbbNSX = new combobox.Combobox();
        cbbLoaiQuai = new combobox.Combobox();
        cbbChatLieu = new combobox.Combobox();
        jLabel19 = new javax.swing.JLabel();
        cbbKichCo = new combobox.Combobox();
        jLabel20 = new javax.swing.JLabel();
        cbbPhuKien = new combobox.Combobox();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        txtGiaNhap = new javax.swing.JTextField();
        txtMoTa2 = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        cbbPhanLoai = new combobox.Combobox();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        cbbTinhTrang = new combobox.Combobox();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPham2 = new javax.swing.JTable();
        roundedButton8 = new RoundedButton();
        btnQuetQR = new RoundedButton();
        roundedButton11 = new RoundedButton();
        btnThem1 = new RoundedButton();
        btnXoa2 = new RoundedButton();
        btnLamMoi2 = new RoundedButton();
        btnUpdate2 = new RoundedButton();
        txtSearch2 = new javax.swing.JTextField();
        viewThuocTinh = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtMaThuocTinh = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTenThuocTinh = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblThuocTinhSP = new javax.swing.JTable();
        btnXoaThuocTinhSanPham = new RoundedButton();
        btnSuaThuocTinhSanPham = new RoundedButton();
        roundedButton4 = new RoundedButton();
        btnThemThuocTinhSanPham = new RoundedButton();
        rdoNXS = new javax.swing.JRadioButton();
        rdoCL = new javax.swing.JRadioButton();
        rdoMS = new javax.swing.JRadioButton();
        rdoPK = new javax.swing.JRadioButton();
        rdoLQ = new javax.swing.JRadioButton();
        rdoTH = new javax.swing.JRadioButton();
        rdoTT = new javax.swing.JRadioButton();
        rdoKC = new javax.swing.JRadioButton();
        rdoPL = new javax.swing.JRadioButton();

        setPreferredSize(new java.awt.Dimension(1230, 710));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("UVN Chim Bien", 1, 18)); // NOI18N
        jLabel1.setText("Sản Phẩm");

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1656, 939));

        viewSanPham.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel2.setText("Mã Sản Phẩm");

        txtMaSP.setEditable(false);
        txtMaSP.setOpaque(false);
        txtMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSPActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel3.setText("Tên Sản Phẩm");

        txtTenSP.setOpaque(false);
        txtTenSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSPActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(0, 0, 0));
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xóa");
        btnXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaMouseClicked(evt);
            }
        });
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-search-26.png"))); // NOI18N
        jLabel7.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel7MouseMoved(evt);
            }
        });
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        txtSearch.setOpaque(false);
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        btnLamMoi.setBackground(new java.awt.Color(0, 0, 0));
        btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setText("Làm Mới");
        btnLamMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLamMoiMouseClicked(evt);
            }
        });
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(0, 0, 0));
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm Mới");
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemMouseClicked(evt);
            }
        });
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(0, 0, 0));
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Sửa");
        btnUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpdateMouseClicked(evt);
            }
        });
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout viewSanPhamLayout = new javax.swing.GroupLayout(viewSanPham);
        viewSanPham.setLayout(viewSanPhamLayout);
        viewSanPhamLayout.setHorizontalGroup(
            viewSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewSanPhamLayout.createSequentialGroup()
                .addGroup(viewSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewSanPhamLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(viewSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(viewSanPhamLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSearch))
                            .addComponent(jLabel2)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(268, 268, 268)
                        .addGroup(viewSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(94, 94, 94)
                        .addGroup(viewSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(viewSanPhamLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1076, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(552, Short.MAX_VALUE))
        );
        viewSanPhamLayout.setVerticalGroup(
            viewSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(19, 19, 19)
                .addGroup(viewSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(viewSanPhamLayout.createSequentialGroup()
                        .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(13, 13, 13)
                        .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(viewSanPhamLayout.createSequentialGroup()
                        .addGroup(viewSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(viewSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(27, 27, 27)
                .addGroup(viewSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSearch))
                .addGap(135, 135, 135)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(378, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản Phẩm", viewSanPham);

        viewCTSP.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setBackground(new java.awt.Color(0, 0, 255));
        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 19)); // NOI18N
        jLabel10.setText("Bộ lọc");

        roundedPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Màu Sắc");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Thương Hiệu");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Kích cỡ");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Nhà Sản Xuất");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Loại quai");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Chất Liệu");

        cbbMauSac.setBackground(new java.awt.Color(204, 204, 204));
        cbbMauSac.setLabeText("");

        cbbThuongHieu.setBackground(new java.awt.Color(204, 204, 204));
        cbbThuongHieu.setLabeText("");

        cbbTenSP.setLabeText("");

        cbbNSX.setBackground(new java.awt.Color(204, 204, 204));
        cbbNSX.setLabeText("");
        cbbNSX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNSXActionPerformed(evt);
            }
        });

        cbbLoaiQuai.setBackground(new java.awt.Color(204, 204, 204));
        cbbLoaiQuai.setLabeText("");
        cbbLoaiQuai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLoaiQuaiActionPerformed(evt);
            }
        });

        cbbChatLieu.setBackground(new java.awt.Color(204, 204, 204));
        cbbChatLieu.setLabeText("");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Tên Sản Phẩm");

        cbbKichCo.setBackground(new java.awt.Color(204, 204, 204));
        cbbKichCo.setLabeText("");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Phụ kiện");

        cbbPhuKien.setBackground(new java.awt.Color(204, 204, 204));
        cbbPhuKien.setLabeText("");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Số Lượng");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Giá nhập");

        txtSoLuong.setBackground(new java.awt.Color(204, 204, 204));
        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });

        txtGiaNhap.setBackground(new java.awt.Color(204, 204, 204));
        txtGiaNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaNhapActionPerformed(evt);
            }
        });

        txtMoTa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMoTa2ActionPerformed(evt);
            }
        });

        txtGiaBan.setBackground(new java.awt.Color(204, 204, 204));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Giá bán");

        cbbPhanLoai.setBackground(new java.awt.Color(204, 204, 204));
        cbbPhanLoai.setLabeText("");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("Phân loại");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("Tình trạng");

        cbbTinhTrang.setBackground(new java.awt.Color(204, 204, 204));
        cbbTinhTrang.setLabeText("");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText("Mô tả");

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMoTa2)
                    .addComponent(cbbTenSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(jLabel19)
                            .addGroup(roundedPanel1Layout.createSequentialGroup()
                                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24))))
                        .addGap(0, 2, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 245, Short.MAX_VALUE)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbPhuKien, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addGap(50, 50, 50)
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbThuongHieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13)))
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(49, 49, 49)
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(cbbLoaiQuai, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(48, 48, 48)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbTinhTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel26))
                .addGap(64, 64, 64))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel11)
                    .addComponent(jLabel25)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbbNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel15)
                    .addComponent(jLabel18)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbLoaiQuai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbPhuKien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbTinhTrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addComponent(txtMoTa2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        tblSanPham2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SP", "Tên", "Số lượng", "Giá nhập", "Giá bán", "Chất liệu", "Màu", "Phụ kiện", "Loại quai", "Phân loại", "Kích cỡ", "Tình trạnh", "Trạng thái"
            }
        ));
        tblSanPham2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPham2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSanPham2);

        roundedButton8.setBackground(new java.awt.Color(0, 0, 0));
        roundedButton8.setForeground(new java.awt.Color(255, 255, 255));
        roundedButton8.setText("Xuất Excel");
        roundedButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedButton8ActionPerformed(evt);
            }
        });

        btnQuetQR.setBackground(new java.awt.Color(0, 0, 0));
        btnQuetQR.setForeground(new java.awt.Color(255, 255, 255));
        btnQuetQR.setText("Quẻt QR");
        btnQuetQR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuetQRActionPerformed(evt);
            }
        });

        roundedButton11.setBackground(new java.awt.Color(0, 0, 0));
        roundedButton11.setForeground(new java.awt.Color(255, 255, 255));
        roundedButton11.setText("Tải Mã QR");
        roundedButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedButton11ActionPerformed(evt);
            }
        });

        btnThem1.setBackground(new java.awt.Color(0, 0, 0));
        btnThem1.setForeground(new java.awt.Color(255, 255, 255));
        btnThem1.setText("Thêm");
        btnThem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThem1MouseClicked(evt);
            }
        });
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });

        btnXoa2.setBackground(new java.awt.Color(0, 0, 0));
        btnXoa2.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa2.setText("Xoá");
        btnXoa2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoa2MouseClicked(evt);
            }
        });
        btnXoa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa2ActionPerformed(evt);
            }
        });

        btnLamMoi2.setBackground(new java.awt.Color(0, 0, 0));
        btnLamMoi2.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi2.setText("Làm mới");
        btnLamMoi2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLamMoi2MouseClicked(evt);
            }
        });
        btnLamMoi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoi2ActionPerformed(evt);
            }
        });

        btnUpdate2.setBackground(new java.awt.Color(0, 0, 0));
        btnUpdate2.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate2.setText("Sửa");
        btnUpdate2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpdate2MouseClicked(evt);
            }
        });
        btnUpdate2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout viewCTSPLayout = new javax.swing.GroupLayout(viewCTSP);
        viewCTSP.setLayout(viewCTSPLayout);
        viewCTSPLayout.setHorizontalGroup(
            viewCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewCTSPLayout.createSequentialGroup()
                .addGroup(viewCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(viewCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(viewCTSPLayout.createSequentialGroup()
                            .addGap(60, 60, 60)
                            .addComponent(jLabel10)
                            .addGap(18, 18, 18)
                            .addComponent(txtSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(viewCTSPLayout.createSequentialGroup()
                            .addGap(49, 49, 49)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1061, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(viewCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(viewCTSPLayout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(roundedButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(17, 17, 17)
                            .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnUpdate2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(viewCTSPLayout.createSequentialGroup()
                            .addComponent(btnQuetQR, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(roundedButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnXoa2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnLamMoi2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(541, Short.MAX_VALUE))
        );
        viewCTSPLayout.setVerticalGroup(
            viewCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewCTSPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(viewCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(viewCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roundedButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(viewCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnQuetQR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundedButton11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(367, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Chi tiết sản phẩm", viewCTSP);

        viewThuocTinh.setBackground(new java.awt.Color(255, 255, 255));
        viewThuocTinh.setPreferredSize(new java.awt.Dimension(1656, 939));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel6.setText("Mã Thuộc Tính");

        txtMaThuocTinh.setEditable(false);
        txtMaThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaThuocTinhActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel8.setText("Tên Thuộc Tính");

        txtTenThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenThuocTinhActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel9.setText("Danh Sách Thuộc Tính");

        tblThuocTinhSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã Thuộc Tính", "Tên Thuộc Tính"
            }
        ));
        tblThuocTinhSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuocTinhSPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblThuocTinhSPMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(tblThuocTinhSP);

        btnXoaThuocTinhSanPham.setBackground(new java.awt.Color(0, 0, 0));
        btnXoaThuocTinhSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaThuocTinhSanPham.setText("Xóa");
        btnXoaThuocTinhSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaThuocTinhSanPhamMouseClicked(evt);
            }
        });
        btnXoaThuocTinhSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaThuocTinhSanPhamActionPerformed(evt);
            }
        });

        btnSuaThuocTinhSanPham.setBackground(new java.awt.Color(0, 0, 0));
        btnSuaThuocTinhSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaThuocTinhSanPham.setText("Sửa");
        btnSuaThuocTinhSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaThuocTinhSanPhamMouseClicked(evt);
            }
        });
        btnSuaThuocTinhSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaThuocTinhSanPhamActionPerformed(evt);
            }
        });

        roundedButton4.setBackground(new java.awt.Color(0, 0, 0));
        roundedButton4.setForeground(new java.awt.Color(255, 255, 255));
        roundedButton4.setText("Mới");
        roundedButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedButton4ActionPerformed(evt);
            }
        });

        btnThemThuocTinhSanPham.setBackground(new java.awt.Color(0, 0, 0));
        btnThemThuocTinhSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnThemThuocTinhSanPham.setText("Thêm");
        btnThemThuocTinhSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemThuocTinhSanPhamMouseClicked(evt);
            }
        });
        btnThemThuocTinhSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemThuocTinhSanPhamActionPerformed(evt);
            }
        });

        btgThuocTinh.add(rdoNXS);
        rdoNXS.setSelected(true);
        rdoNXS.setText("Nhà sản xuất");
        rdoNXS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNXSActionPerformed(evt);
            }
        });

        btgThuocTinh.add(rdoCL);
        rdoCL.setText("Chất liệu");
        rdoCL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoCLActionPerformed(evt);
            }
        });

        btgThuocTinh.add(rdoMS);
        rdoMS.setText("Màu sắc");
        rdoMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoMSActionPerformed(evt);
            }
        });

        btgThuocTinh.add(rdoPK);
        rdoPK.setText("Phụ kiện");
        rdoPK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoPKActionPerformed(evt);
            }
        });

        btgThuocTinh.add(rdoLQ);
        rdoLQ.setText("Loại quai");
        rdoLQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoLQActionPerformed(evt);
            }
        });

        btgThuocTinh.add(rdoTH);
        rdoTH.setText("Thương hiệu");
        rdoTH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTHActionPerformed(evt);
            }
        });

        btgThuocTinh.add(rdoTT);
        rdoTT.setText("Tình trạng");
        rdoTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTTActionPerformed(evt);
            }
        });

        btgThuocTinh.add(rdoKC);
        rdoKC.setText("Kích cỡ");
        rdoKC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoKCActionPerformed(evt);
            }
        });

        btgThuocTinh.add(rdoPL);
        rdoPL.setText("Phân loại");
        rdoPL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoPLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout viewThuocTinhLayout = new javax.swing.GroupLayout(viewThuocTinh);
        viewThuocTinh.setLayout(viewThuocTinhLayout);
        viewThuocTinhLayout.setHorizontalGroup(
            viewThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewThuocTinhLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(viewThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewThuocTinhLayout.createSequentialGroup()
                        .addGroup(viewThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(txtTenThuocTinh)
                            .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(76, 76, 76)
                        .addGroup(viewThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoPL)
                            .addComponent(rdoLQ)
                            .addComponent(rdoMS)
                            .addComponent(rdoNXS))
                        .addGap(35, 35, 35)
                        .addGroup(viewThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(viewThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(rdoKC)
                                .addComponent(rdoTH)
                                .addComponent(rdoPK))
                            .addGroup(viewThuocTinhLayout.createSequentialGroup()
                                .addComponent(rdoCL)
                                .addGap(20, 20, 20)))
                        .addGap(80, 80, 80)
                        .addGroup(viewThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(viewThuocTinhLayout.createSequentialGroup()
                                .addGroup(viewThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnThemThuocTinhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnXoaThuocTinhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(61, 61, 61)
                                .addComponent(btnSuaThuocTinhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(roundedButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(viewThuocTinhLayout.createSequentialGroup()
                        .addGap(285, 285, 285)
                        .addComponent(rdoTT))
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1078, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(539, Short.MAX_VALUE))
        );
        viewThuocTinhLayout.setVerticalGroup(
            viewThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewThuocTinhLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(viewThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewThuocTinhLayout.createSequentialGroup()
                        .addGroup(viewThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(viewThuocTinhLayout.createSequentialGroup()
                                .addGroup(viewThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnThemThuocTinhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSuaThuocTinhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rdoNXS)
                                    .addComponent(rdoCL))
                                .addGap(51, 51, 51)
                                .addGroup(viewThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnXoaThuocTinhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(roundedButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewThuocTinhLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel6)
                                .addGap(27, 27, 27)
                                .addGroup(viewThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(viewThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rdoMS)
                                        .addComponent(rdoPK))
                                    .addGroup(viewThuocTinhLayout.createSequentialGroup()
                                        .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel8)))
                                .addGap(11, 11, 11)))
                        .addGap(24, 24, 24)
                        .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(viewThuocTinhLayout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addGroup(viewThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(viewThuocTinhLayout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addGroup(viewThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rdoKC)
                                    .addComponent(rdoPL)))
                            .addGroup(viewThuocTinhLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(viewThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rdoLQ)
                                    .addComponent(rdoTH))))))
                .addGap(17, 17, 17)
                .addComponent(rdoTT)
                .addGap(130, 130, 130)
                .addComponent(jLabel9)
                .addGap(32, 32, 32)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(336, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thuộc Tính Sản Phẩm", viewThuocTinh);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(453, 453, 453)
                        .addComponent(jLabel1))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 976, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1160, 780));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSPActionPerformed

    private void txtTenSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSPActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTenSPActionPerformed

    private void btnXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseClicked
//        int check = JOptionPane.showConfirmDialog(this, "Bạn có muốn Xóa không?", "?", JOptionPane.YES_NO_CANCEL_OPTION);
//        if (check == JOptionPane.YES_OPTION) {
//            //
//            int row = tblStudent.getSelectedRow();
//            SanPham sp = list.get(row);
//            service.Xoa(sp.getIDSanPham());
//            list = service.getALLL();
//            showDataTable(list);
//            //
//            JOptionPane.showMessageDialog(this, " thành công!");
//            return;
//
//        }
//        if (check == JOptionPane.NO_OPTION) {
//            JOptionPane.showMessageDialog(this, "Bạn đã chọn NO");
//            return;
//        } else {
//            JOptionPane.showMessageDialog(this, "Bạn đã chọn CANCEL");
//            return;
//        }


    }//GEN-LAST:event_btnXoaMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed

        int row = tblSanPham.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "chon lai");
            return;
        }

        int check = JOptionPane.showConfirmDialog(this, "Xoá?");
        if (check == JOptionPane.YES_OPTION) {
            SanPhamViewmodel1 kh = list.get(row);
            service.xoaSP(kh.getMaSP());
            list = service.getAll();
            showDataTable1(list);
            JOptionPane.showMessageDialog(this, "đã xóa");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int row = tblSanPham.getSelectedRow();
        SanPhamViewmodel1 sp = list.get(row);
        fillTable1(row);

        if (evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1) { // Kiểm tra xem đã click 2 lần chưa
            System.out.println(sp.getIdSP());
            System.out.println("Đã click 2 lần");
            if (txtMaSP.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm");
                return;
            }

            if (sp.getSoLuong() <= 0) {
                
                JOptionPane.showMessageDialog(this, "Sản phẩm này chưa thêm thuộc tính");
                return;
            }
            jTabbedPane1.setSelectedIndex(1);

            list2 = service2.getAll3(sp.getIdSP());
            showDataTable2(list2);
            fillTable2(0);
            tblSanPham2.setRowSelectionInterval(0, 0);

        }


    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnLamMoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLamMoiMouseClicked
//        txtMaSP.setText("");
//        txtTenSP.setText("");
//        txtMota.setText("");
        //        cboBac.setSelectedItem(cboBac.getItemAt(0));
        //
        //        cboLoai.setSelectedItem(cboLoai.getItemAt(0));
    }//GEN-LAST:event_btnLamMoiMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        list = service.getAll();
        showDataTable1(list);
        list = service.getAll();

        listctsp = servicectsp.getID();
        showCBBiD();
        list2 = service2.getTenSP();
        showCBBtenSP();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseClicked
//        service.Them(getFormData());
//        list = service.getALLL();
//        showDataTable(list);
    }//GEN-LAST:event_btnThemMouseClicked

    private void txtTenThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenThuocTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenThuocTinhActionPerformed

    private void btnThemThuocTinhSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemThuocTinhSanPhamMouseClicked
//        service.Them(getFormData());
//            list = service.getAll();
//            showDataTable(list);
    }//GEN-LAST:event_btnThemThuocTinhSanPhamMouseClicked

    private void btnSuaThuocTinhSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaThuocTinhSanPhamMouseClicked
//       int row = tblStudent.getSelectedRow();
//        ThuongHieuChiTietSanPham gv = list.get(row);
//        service.Update(getFormData(), gv.getIDThuongHieu());
//        list = service.getAll();
//        showDataTable(list);
    }//GEN-LAST:event_btnSuaThuocTinhSanPhamMouseClicked

    private void btnXoaThuocTinhSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaThuocTinhSanPhamMouseClicked
//        int row = tblStudent1.getSelectedRow();
//        ThuongHieuChiTietSanPham ct = list.get(row);
//        service.Xoaa(ct.getIDThuongHieu());
//        list = service.getAll();
//        showDataTable(list);
    }//GEN-LAST:event_btnXoaThuocTinhSanPhamMouseClicked

    private void btnUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseClicked
//        int row = tblStudent.getSelectedRow();
//        SanPham gv = list.get(row);
//        service.Update(getFormData(), gv.getIDSanPham());
//        list = service.getALLL();
//        showDataTable(list);
    }//GEN-LAST:event_btnUpdateMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        int row = tblSanPham.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "chon lai");
            return;
        }

        int check = JOptionPane.showConfirmDialog(this, "Sửa?");
        if (check == JOptionPane.YES_OPTION) {
            SanPhamViewmodel1 kh = list.get(row);
            service.update(getFromData(), kh.getIdSP());
            list = service.getAll();
            showDataTable1(list);
            JOptionPane.showMessageDialog(this, "đã sửa");
        }


    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnThemThuocTinhSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemThuocTinhSanPhamActionPerformed
//        serviceCLieu.Them(getFormData());
//        listCLieu = serviceCLieu.getChatLieu();
//        showDataTableThuocTinhCLieu(listCLieu);
        if (rdoNXS.isSelected()) {
            int row = tblThuocTinhSP.getSelectedRow();
            NhaSanXuat nsx = listnsx.get(row);
            ttservice.addNSX(getFormNhaSanXuat());
            listnsx =ttservice.getNSX();
            showDataTableNSX(listnsx);
            JOptionPane.showMessageDialog(this, "đã thêm");
        } else if (rdoCL.isSelected()) {
            ttservice.addCL(getFormchaChatLieu());
            listcl =ttservice.getCL();
            showDataTableCL(listcl);
            JOptionPane.showMessageDialog(this, "đã thêm");
        } else if (rdoMS.isSelected()) {
            ttservice.addMS(getFormMauSac());
            listms =ttservice.getMS();
            showDataTableMS(listms);
            JOptionPane.showMessageDialog(this, "đã thêm");
        } else if (rdoPK.isSelected()) {
            ttservice.addPK(getFormKien());
            listpk =ttservice.getPK();
            showDataTablePK(listpk);
            JOptionPane.showMessageDialog(this, "đã thêm");
        } else if (rdoLQ.isSelected()) {
            ttservice.addLQ(getFormLoaiQuai());
            listlq =ttservice.getLQ();
            showDataTableLQ(listlq);
            JOptionPane.showMessageDialog(this, "đã thêm");
        } else if (rdoTH.isSelected()) {
            ttservice.addTH(getFormThuongHieu());
            listth =ttservice.getTH();
            showDataTableTH(ttservice.getTH());
            JOptionPane.showMessageDialog(this, "đã thêm");
        } else if (rdoPL.isSelected()) {
            ttservice.addPL(getFormPhanLoai());
            listpl =ttservice.getPL();
            showDataTablePL(ttservice.getPL());
            JOptionPane.showMessageDialog(this, "đã thêm");
        } else if (rdoKC.isSelected()) {
            ttservice.addKC(getFormKichCo());
            listkc =ttservice.getKC();
            showDataTableKC(ttservice.getKC());
            JOptionPane.showMessageDialog(this, "đã thêm");
        } else if (rdoTT.isSelected()) {
            ttservice.addTT(getFormTinhTrang());
            listtt =ttservice.getTT();
            showDataTableTT(ttservice.getTT());
            JOptionPane.showMessageDialog(this, "đã thêm");
        }


    }//GEN-LAST:event_btnThemThuocTinhSanPhamActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (txtTenSP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng ko để trống tên sản phẩm");
            return;
        }
        service.add(getFromData());
        list = service.getAll();
        showDataTable1(list);
        JOptionPane.showMessageDialog(this, "đã thêm");

        list2 = service2.getAll();
        showDataTable2(service2.getAll());

        listctsp = servicectsp.getID();
        showCBBiD();
    }//GEN-LAST:event_btnThemActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        service.search(txtSearch.getText() + "");
        list = service.search(txtSearch.getText() + "");
        showDataTable1(list);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_txtSearchActionPerformed

    private void rdoNXSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNXSActionPerformed
     listnsx =ttservice.getNSX();
        showDataTableNSX(listnsx);
    }//GEN-LAST:event_rdoNXSActionPerformed

    private void rdoCLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoCLActionPerformed
        listcl =ttservice.getCL();
        showDataTableCL(listcl);
    }//GEN-LAST:event_rdoCLActionPerformed

    private void rdoMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoMSActionPerformed
        listms =ttservice.getMS();
        showDataTableMS(listms);
    }//GEN-LAST:event_rdoMSActionPerformed

    private void rdoPKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoPKActionPerformed
        listpk =ttservice.getPK();
        showDataTablePK(listpk);
    }//GEN-LAST:event_rdoPKActionPerformed

    private void rdoLQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoLQActionPerformed
        listlq =ttservice.getLQ();
        showDataTableLQ(listlq);
    }//GEN-LAST:event_rdoLQActionPerformed

    private void rdoTHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTHActionPerformed
        listth =ttservice.getTH();
        showDataTableTH(listth);
    }//GEN-LAST:event_rdoTHActionPerformed

    private void rdoTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTTActionPerformed
        listtt =ttservice.getTT();
        showDataTableTT(listtt);
    }//GEN-LAST:event_rdoTTActionPerformed

    private void rdoKCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoKCActionPerformed
        listkc =ttservice.getKC();
        showDataTableKC(listkc);
    }//GEN-LAST:event_rdoKCActionPerformed

    private void rdoPLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoPLActionPerformed
       listpl =ttservice.getPL();
        showDataTablePL(listpl);
    }//GEN-LAST:event_rdoPLActionPerformed

    private void tblThuocTinhSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuocTinhSPMouseClicked
        // TODO add your handling code here:
        if (rdoNXS.isSelected()) {
            int index = tblThuocTinhSP.getSelectedRow();
            NhaSanXuat nsx = ttservice.getNSX().get(index);
            txtMaThuocTinh.setText(nsx.getMaNSX());
            txtTenThuocTinh.setText(nsx.getTenNhaSanXuat());
        } else if (rdoCL.isSelected()) {
            int index = tblThuocTinhSP.getSelectedRow();
            ChatLieu cl = ttservice.getCL().get(index);
            txtMaThuocTinh.setText(cl.getMaCL());
            txtTenThuocTinh.setText(cl.getTenChatLieu());
        } else if (rdoMS.isSelected()) {
            int index = tblThuocTinhSP.getSelectedRow();
            MauSac ms = ttservice.getMS().get(index);
            txtMaThuocTinh.setText(ms.getMaMauSac());
            txtTenThuocTinh.setText(ms.getTenMauSac());
        } else if (rdoPK.isSelected()) {
            int index = tblThuocTinhSP.getSelectedRow();
            PhuKien pk = ttservice.getPK().get(index);
            txtMaThuocTinh.setText(pk.getMaPhuKien());
            txtTenThuocTinh.setText(pk.getTenPhuKien());
        } else if (rdoLQ.isSelected()) {
            int index = tblThuocTinhSP.getSelectedRow();
            LoaiQuai lq = ttservice.getLQ().get(index);
            txtMaThuocTinh.setText(lq.getMaLoaiQuai());
            txtTenThuocTinh.setText(lq.getTenLoaiQuai());
        } else if (rdoTH.isSelected()) {
            int index = tblThuocTinhSP.getSelectedRow();
            ThuongHieu th = ttservice.getTH().get(index);
            txtMaThuocTinh.setText(th.getMaThuongHieu());
            txtTenThuocTinh.setText(th.getTenThuongHieu());
        } else if (rdoPL.isSelected()) {
            int index = tblThuocTinhSP.getSelectedRow();
            PhanLoai pl = ttservice.getPL().get(index);
            txtMaThuocTinh.setText(pl.getMaPhanLoai());
            txtTenThuocTinh.setText(pl.getTenPhanLoai());
        } else if (rdoKC.isSelected()) {
            int index = tblThuocTinhSP.getSelectedRow();
            KichCo kc = ttservice.getKC().get(index);
            txtMaThuocTinh.setText(kc.getMaKichCo());
            txtTenThuocTinh.setText(kc.getTenKichCo());
        } else if (rdoTT.isSelected()) {
            int index = tblThuocTinhSP.getSelectedRow();
            TinhTrang tt = ttservice.getTT().get(index);
            txtMaThuocTinh.setText(tt.getMaTinhTrang());
            txtTenThuocTinh.setText(tt.getTenTinhTrang());
        }


    }//GEN-LAST:event_tblThuocTinhSPMouseClicked

    private void tblThuocTinhSPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuocTinhSPMouseEntered
        // TODO add your handling code here:
        ////


    }//GEN-LAST:event_tblThuocTinhSPMouseEntered

    private void roundedButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedButton4ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dtmttsp = (DefaultTableModel) tblThuocTinhSP.getModel();
        dtmttsp.setRowCount(0); // Xóa hết các hàng khỏi bảng
        btgThuocTinh.clearSelection(); // Xóa lựa chọn trong buttonGroup1
        clear();
        JOptionPane.showMessageDialog(this, "đã làm mới");
    }//GEN-LAST:event_roundedButton4ActionPerformed

    private void txtMaThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaThuocTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaThuocTinhActionPerformed

    private void btnSuaThuocTinhSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaThuocTinhSanPhamActionPerformed
        // TODO add your handling code here:
        if (rdoNXS.isSelected()) {
            int index = tblThuocTinhSP.getSelectedRow();
            NhaSanXuat nsx = ttservice.getNSX().get(index);
            ttservice.updateNSX(getFormNhaSanXuat(), nsx.getID());
            showDataTableNSX(ttservice.getNSX());
            JOptionPane.showMessageDialog(this, "đã sửa");
        } else if (rdoCL.isSelected()) {
            int index = tblThuocTinhSP.getSelectedRow();
            ChatLieu cl = ttservice.getCL().get(index);
            ttservice.updateCL(getFormchaChatLieu(), cl.getID());
            showDataTableCL(ttservice.getCL());
            JOptionPane.showMessageDialog(this, "đã sửa");
        } else if (rdoMS.isSelected()) {
            int index = tblThuocTinhSP.getSelectedRow();
            MauSac ms = ttservice.getMS().get(index);
            ttservice.updateMS(getFormMauSac(), ms.getID());
            showDataTableMS(ttservice.getMS());
            JOptionPane.showMessageDialog(this, "đã sửa");
        } else if (rdoPK.isSelected()) {
            int index = tblThuocTinhSP.getSelectedRow();
            PhuKien pk = ttservice.getPK().get(index);
            ttservice.updatePK(getFormKien(), pk.getID());
            showDataTablePK(ttservice.getPK());
            JOptionPane.showMessageDialog(this, "đã sửa");
        } else if (rdoLQ.isSelected()) {
            int index = tblThuocTinhSP.getSelectedRow();
            LoaiQuai lq = ttservice.getLQ().get(index);
            ttservice.updateLQ(getFormLoaiQuai(), lq.getID());
            showDataTableLQ(ttservice.getLQ());
            JOptionPane.showMessageDialog(this, "đã sửa");
        } else if (rdoTH.isSelected()) {
            int index = tblThuocTinhSP.getSelectedRow();
            ThuongHieu th = ttservice.getTH().get(index);
            ttservice.updateTH(getFormThuongHieu(), th.getID());
            showDataTableTH(ttservice.getTH());
            JOptionPane.showMessageDialog(this, "đã sửa");
        } else if (rdoPL.isSelected()) {
            int index = tblThuocTinhSP.getSelectedRow();
            PhanLoai pl = ttservice.getPL().get(index);
            ttservice.updatePL(getFormPhanLoai(), pl.getID());
            showDataTablePL(ttservice.getPL());
            JOptionPane.showMessageDialog(this, "đã sửa");
        } else if (rdoKC.isSelected()) {
            int index = tblThuocTinhSP.getSelectedRow();
            KichCo kc = ttservice.getKC().get(index);
            ttservice.updateKC(getFormKichCo(), kc.getID());
            showDataTableKC(ttservice.getKC());
            JOptionPane.showMessageDialog(this, "đã sửa");
        } else if (rdoTT.isSelected()) {
            int index = tblThuocTinhSP.getSelectedRow();
            TinhTrang tt = ttservice.getTT().get(index);
            ttservice.updateTT(getFormTinhTrang(), tt.getID());
            showDataTableTT(ttservice.getTT());
            JOptionPane.showMessageDialog(this, "đã sửa");
        }

    }//GEN-LAST:event_btnSuaThuocTinhSanPhamActionPerformed

    private void btnXoaThuocTinhSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaThuocTinhSanPhamActionPerformed
        // TODO add your handling code here:
        int index = tblThuocTinhSP.getSelectedRow();
        if (index != -1) { // Ensure a row is selected
            if (rdoNXS.isSelected()) {
                NhaSanXuat nsx = ttservice.getNSX().get(index);
                ttservice.removeNSX(nsx.getID());
                showDataTableNSX(ttservice.getNSX());
                JOptionPane.showMessageDialog(this, "đã xóa");
            } else if (rdoCL.isSelected()) {
                ChatLieu cl = ttservice.getCL().get(index);
                ttservice.removeCL(cl.getID());
                showDataTableCL(ttservice.getCL());
                JOptionPane.showMessageDialog(this, "đã xóa");
            } else if (rdoMS.isSelected()) {
                MauSac ms = ttservice.getMS().get(index);
                ttservice.removeMS(ms.getID());
                showDataTableMS(ttservice.getMS());
                JOptionPane.showMessageDialog(this, "đã xóa");
            } else if (rdoPK.isSelected()) {
                PhuKien pk = ttservice.getPK().get(index);
                ttservice.removePK(pk.getID());
                showDataTablePK(ttservice.getPK());
                JOptionPane.showMessageDialog(this, "đã xóa");
            } else if (rdoLQ.isSelected()) {
                LoaiQuai lq = ttservice.getLQ().get(index);
                ttservice.removeLQ(lq.getID());
                showDataTableLQ(ttservice.getLQ());
                JOptionPane.showMessageDialog(this, "đã xóa");
            } else if (rdoTH.isSelected()) {
                ThuongHieu th = ttservice.getTH().get(index);
                ttservice.removeTH(th.getID());
                PhanLoai pl = ttservice.getPL().get(index);
                showDataTableTH(ttservice.getTH());
                JOptionPane.showMessageDialog(this, "đã xóa");
            } else if (rdoPL.isSelected()) {
                PhanLoai pl = ttservice.getPL().get(index);
                ttservice.removePL(pl.getID());
                showDataTablePL(ttservice.getPL());
                JOptionPane.showMessageDialog(this, "đã xóa");
            } else if (rdoKC.isSelected()) {
                KichCo kc = ttservice.getKC().get(index);
                ttservice.removeKC(kc.getID());
                showDataTableKC(ttservice.getKC());
                JOptionPane.showMessageDialog(this, "đã xóa");
            } else if (rdoTT.isSelected()) {
                TinhTrang tt = ttservice.getTT().get(index);
                ttservice.removeTT(tt.getID());
                showDataTableTT(ttservice.getTT());
                JOptionPane.showMessageDialog(this, "đã xóa");
            }
        }

    }//GEN-LAST:event_btnXoaThuocTinhSanPhamActionPerformed

    private void jLabel7MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel7MouseMoved

    private void btnUpdate2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate2ActionPerformed

        int row = tblSanPham2.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Hãy chọn 1 sản phẩm");
            return;
        }
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
        int check = JOptionPane.showConfirmDialog(this, "Sửa ?");
        if (check == JOptionPane.YES_OPTION) {
            CTSPViewmodel kh = list2.get(row);
            servicectsp.updateCTSP(getFromDataCTSP(), kh.getIdCTSP());
            SanPham sp = new SanPham();
            JOptionPane.showMessageDialog(this, "Đã sửa");
            list2 = service2.getAll();
            showDataTable2(list2);
            list2 = service2.getAll();
            JOptionPane.showMessageDialog(this, "Đã sửa");
        } else {
            JOptionPane.showMessageDialog(this, "đã thoát");
            return;
        }

    }//GEN-LAST:event_btnUpdate2ActionPerformed

    private void btnUpdate2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdate2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdate2MouseClicked

    private void btnLamMoi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoi2ActionPerformed
        list2 = service2.getAll();
        showDataTable2(list2);

        JOptionPane.showMessageDialog(this, "đã làm mới");
    }//GEN-LAST:event_btnLamMoi2ActionPerformed

    private void btnLamMoi2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLamMoi2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLamMoi2MouseClicked

    private void btnXoa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa2ActionPerformed
        int row = tblSanPham2.getSelectedRow();
        CTSPViewmodel kh = list2.get(row);

        servicectsp.xoaCTSP(kh.getIdCTSP());
        JOptionPane.showMessageDialog(this, "đã xóa");
        list2 = service2.getAll();
        showDataTable2(list2);
        list2 = service2.getAll();
    }//GEN-LAST:event_btnXoa2ActionPerformed

    private void btnXoa2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoa2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoa2MouseClicked

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        ThemCTSP themCTSP = new ThemCTSP();
        JPanel viewspct = viewCTSP;
        viewspct.removeAll();
        viewspct.add(themCTSP);

        viewspct.setLayout(new BorderLayout());

        // Thêm ThemChiTietSP vào viewsanpham với ràng buộc layout phù hợp
        viewspct.add(themCTSP, BorderLayout.CENTER);

        // Cập nhật giao diện
        viewspct.revalidate();
        viewspct.repaint();
    }//GEN-LAST:event_btnThem1ActionPerformed

    private void btnThem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThem1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThem1MouseClicked

    private void roundedButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedButton11ActionPerformed
        int row = tblSanPham2.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Hay chon 1 phan tu");
            return;
        }
        CTSPViewmodel nv = list2.get(row);

        try {
            ByteArrayOutputStream out = QRCode.from(nv.getIdCTSP()).to(ImageType.PNG).stream();
            String fName = nv.getIdCTSP();
            String pathName = ".\\qrcodeCTSP\\";
            FileOutputStream fout = new FileOutputStream(new File(pathName + (fName + ".PNG")));
            fout.write(out.toByteArray());
            fout.flush();
            JOptionPane.showMessageDialog(this, "Tạo QR thành công");
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_roundedButton11ActionPerformed

    private void btnQuetQRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuetQRActionPerformed

    }//GEN-LAST:event_btnQuetQRActionPerformed

    private void roundedButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedButton8ActionPerformed

        servicectsp.xuatHoaDon();
    }//GEN-LAST:event_roundedButton8ActionPerformed

    private void tblSanPham2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPham2MouseClicked
        int row = tblSanPham2.getSelectedRow();
        fillTable2(row);
    }//GEN-LAST:event_tblSanPham2MouseClicked

    private void txtMoTa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMoTa2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMoTa2ActionPerformed

    private void txtGiaNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaNhapActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void cbbLoaiQuaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLoaiQuaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbLoaiQuaiActionPerformed

    private void cbbNSXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNSXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbNSXActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgThuocTinh;
    private javax.swing.ButtonGroup btgTrangThai;
    private RoundedButton btnLamMoi;
    private RoundedButton btnLamMoi2;
    private RoundedButton btnQuetQR;
    private RoundedButton btnSuaThuocTinhSanPham;
    private RoundedButton btnThem;
    private RoundedButton btnThem1;
    private RoundedButton btnThemThuocTinhSanPham;
    private RoundedButton btnUpdate;
    private RoundedButton btnUpdate2;
    private RoundedButton btnXoa;
    private RoundedButton btnXoa2;
    private RoundedButton btnXoaThuocTinhSanPham;
    private combobox.Combobox cbbChatLieu;
    private combobox.Combobox cbbKichCo;
    private combobox.Combobox cbbLoaiQuai;
    private combobox.Combobox cbbMauSac;
    private combobox.Combobox cbbNSX;
    private combobox.Combobox cbbPhanLoai;
    private combobox.Combobox cbbPhuKien;
    private combobox.Combobox cbbTenSP;
    private combobox.Combobox cbbThuongHieu;
    private combobox.Combobox cbbTinhTrang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdoCL;
    private javax.swing.JRadioButton rdoKC;
    private javax.swing.JRadioButton rdoLQ;
    private javax.swing.JRadioButton rdoMS;
    private javax.swing.JRadioButton rdoNXS;
    private javax.swing.JRadioButton rdoPK;
    private javax.swing.JRadioButton rdoPL;
    private javax.swing.JRadioButton rdoTH;
    private javax.swing.JRadioButton rdoTT;
    private RoundedButton roundedButton11;
    private RoundedButton roundedButton4;
    private RoundedButton roundedButton8;
    private RoundedPanel roundedPanel1;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblSanPham2;
    private javax.swing.JTable tblThuocTinhSP;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMaThuocTinh;
    private javax.swing.JTextField txtMoTa2;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearch2;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTenThuocTinh;
    private javax.swing.JPanel viewCTSP;
    private javax.swing.JPanel viewSanPham;
    private javax.swing.JPanel viewThuocTinh;
    // End of variables declaration//GEN-END:variables

}
