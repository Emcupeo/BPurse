/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bpurse.qlts.repo;

import bpurse.qlts.entity.ChatLieu;
import bpurse.qlts.entity.KichCo;
import bpurse.qlts.entity.LoaiQuai;
import bpurse.qlts.entity.MauSac;
import bpurse.qlts.entity.NhaSanXuat;
import bpurse.qlts.entity.PhanLoai;
import bpurse.qlts.entity.PhuKien;
import bpurse.qlts.entity.ThuongHieu;
import bpurse.qlts.entity.TinhTrang;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ASUS
 */
public class ThuocTinhRepo {

    public List<NhaSanXuat> getNSX() {
        List<NhaSanXuat> lists = new ArrayList<>();
        String sql = """
                     SELECT        ID, MaNhaSanXuat, TenNhaSanXuat
                     FROM            dbo.NhaSanXuat
                     where Deleted = 1
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhaSanXuat nsx = new NhaSanXuat();
                nsx.setID(rs.getString(1));
                nsx.setMaNSX(rs.getString(2));
                nsx.setTenNhaSanXuat(rs.getString(3));
                lists.add(nsx);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }

    public List<ChatLieu> getCL() {
        List<ChatLieu> lists = new ArrayList<>();
        String sql = """
                     SELECT [ID]
                           ,[TenChatLieu]
                           ,[MaChatLieu]
                       FROM [BPURSE_SOF102_SD1702].[dbo].[ChatLieu]
                     where Deleted = 1
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChatLieu cl = new ChatLieu();
                cl.setID(rs.getString(1));
                cl.setTenChatLieu(rs.getString(2));
                cl.setMaCL(rs.getString(3));
                lists.add(cl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }

    public List<MauSac> getMS() {
        List<MauSac> lists = new ArrayList<>();
        String sql = """
                     SELECT [ID]
                           ,[TenMauSac]
                           ,[MaMauSac]
                       FROM [dbo].[MauSac]
                     where Deleted = 1
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MauSac ms = new MauSac();
                ms.setID(rs.getString(1));
                ms.setTenMauSac(rs.getString(2));
                ms.setMaMauSac(rs.getString(3));
                lists.add(ms);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }

    public List<PhuKien> getPK() {
        List<PhuKien> lists = new ArrayList<>();
        String sql = """
                     SELECT [ID]
                           ,[LoaiPhuKien]
                           ,[MaPhuKien]
                       FROM [dbo].[PhuKien]
                     where Deleted = 1
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PhuKien pk = new PhuKien();
                pk.setID(rs.getString(1));
                pk.setTenPhuKien(rs.getString(2));
                pk.setMaPhuKien(rs.getString(3));
                lists.add(pk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }

    public List<LoaiQuai> getLQ() {
        List<LoaiQuai> lists = new ArrayList<>();
        String sql = """
                        SELECT [ID]
                           ,[TenLoaiQuai]
                           ,[MaLoaiQuai]
                        FROM [dbo].[LoaiQuai]
                     where Deleted = 1
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LoaiQuai lq = new LoaiQuai();
                lq.setID(rs.getString(1));
                lq.setTenLoaiQuai(rs.getString(2));
               lq.setMaLoaiQuai(rs.getString(3));
                lists.add(lq);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }

    public List<ThuongHieu> getTH() {
        List<ThuongHieu> lists = new ArrayList<>();
        String sql = """
                        SELECT [ID]
                              ,[ThuongHieu]
                              ,[MaThuongHieu]
                        FROM [dbo].[ThuongHieu]
                     where Deleted = 1
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ThuongHieu th = new ThuongHieu();
                th.setID(rs.getString(1));
                th.setTenThuongHieu(rs.getString(2));
                th.setMaThuongHieu(rs.getString(3));
                lists.add(th);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }

    public List<PhanLoai> getPL() {
        List<PhanLoai> lists = new ArrayList<>();
        String sql = """
                        SELECT [ID]
                              ,[PhanLoai]
                              ,[MaPhanLoai]
                          FROM [dbo].[PhanLoai]
                     where Deleted = 1
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PhanLoai pl = new PhanLoai();
                pl.setID(rs.getString(1));
                pl.setTenPhanLoai(rs.getString(2));
                pl.setMaPhanLoai(rs.getString(3));
                lists.add(pl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }

    public List<KichCo> getKC() {
        List<KichCo> lists = new ArrayList<>();
        String sql = """
                        SELECT [ID]
                              ,[TenKichCo]
                              ,[MaKichCo]
                        FROM [dbo].[KichCo]
                     where Deleted = 1
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KichCo kc = new KichCo();
                kc.setID(rs.getString(1));
                kc.setTenKichCo(rs.getString(2));
                kc.setMaKichCo(rs.getString(3));
                lists.add(kc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }

    public List<TinhTrang> getTT() {
        List<TinhTrang> lists = new ArrayList<>();
        String sql = """
                        SELECT [ID]
                              ,[TinhTrang]
                              ,[MaTinhTrang]
                          FROM [dbo].[TinhTrang]
                     where Deleted = 1
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TinhTrang tt = new TinhTrang();
                tt.setID(rs.getString(1));
                tt.setTenTinhTrang(rs.getString(2));
                tt.setMaTinhTrang(rs.getString(3));
                lists.add(tt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }

    ////////////////////////////////////add
    public boolean addNSX(NhaSanXuat nsx) {
        String sql = """
                 INSERT INTO [dbo].[NhaSanXuat]
                            ([TenNhaSanXuat])
                      VALUES
                            (?)
                 """;
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, nsx.getTenNhaSanXuat());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean addCL(ChatLieu cl) {
        String sql = """
                 INSERT INTO [dbo].[ChatLieu]
                            ([TenChatLieu])
                      VALUES
                            (?)
                 """;
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, cl.getTenChatLieu());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean addMS(MauSac ms) {
        String sql = """
                 INSERT INTO [dbo].[MauSac]
                            ([TenMauSac])
                      VALUES
                            (?)
                 """;
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, ms.getTenMauSac());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean addPK(PhuKien pk) {
        String sql = """
                 INSERT INTO [dbo].[PhuKien]
                            ([LoaiPhuKien])
                      VALUES
                            (?)
                 """;
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, pk.getTenPhuKien());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean addLQ(LoaiQuai lq) {
        String sql = """
                INSERT INTO [dbo].[LoaiQuai]
                            ([TenLoaiQuai])
                      VALUES
                            (?)
                 """;
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, lq.getTenLoaiQuai());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean addTH(ThuongHieu th) {
        String sql = """
                INSERT INTO [dbo].[ThuongHieu]
                           ([ThuongHieu])
                     VALUES
                           (?)
                 """;
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, th.getTenThuongHieu());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean addPL(PhanLoai pl) {
        String sql = """
               INSERT INTO [dbo].[PhanLoai]
                           ([PhanLoai])
                     VALUES
                           (?)
                 """;
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, pl.getTenPhanLoai());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean addKC(KichCo kc) {
        String sql = """
               INSERT INTO [dbo].[KichCo]
                          ([TenKichCo])
                VALUES
                        (?)
                 """;
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, kc.getTenKichCo());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean addTT(TinhTrang tt) {
        String sql = """
               INSERT INTO [dbo].[TinhTrang]
                          ([TinhTrang])
                    VALUES
                          (?)
                 """;
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, tt.getTenTinhTrang());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
    /////////////update

    public boolean updateMS(MauSac ms, String id) {
        String sql = """
                 UPDATE [dbo].[MauSac]
                 SET [TenMauSac] = ?
                  WHERE ID = ?
                 """;
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, ms.getTenMauSac());
            ps.setObject(2, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean updateNSX(NhaSanXuat nsx, String id) {
        String sql = """
                UPDATE [dbo].[NhaSanXuat]
                SET [TenNhaSanXuat] = ?
                WHERE id = ?
                 """;
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, nsx.getTenNhaSanXuat());
            ps.setObject(2, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean updateCL(ChatLieu cl, String id) {
        String sql = """
                UPDATE [dbo].[ChatLieu]
                   SET [TenChatLieu] = ?
                 WHERE ID = ?
                 """;
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, cl.getTenChatLieu());
            ps.setObject(2, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean updatePK(PhuKien pk, String id) {
        String sql = """
                UPDATE [dbo].[PhuKien]
                   SET [LoaiPhuKien] = ?
                 WHERE ID =?
                 """;
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, pk.getTenPhuKien());
            ps.setObject(2, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean updateLQ(LoaiQuai lq, String id) {
        String sql = """
               UPDATE [dbo].[LoaiQuai]
                   SET [TenLoaiQuai] = ?
                 WHERE ID = ?
                 """;
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, lq.getTenLoaiQuai());
            ps.setObject(2, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean updateTH(ThuongHieu th, String id) {
        String sql = """
              UPDATE [dbo].[ThuongHieu]
                  SET [ThuongHieu] = ?
                WHERE ID = ?
                 """;
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, th.getTenThuongHieu());
            ps.setObject(2, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean updatePL(PhanLoai pl, String id) {
        String sql = """
              UPDATE [dbo].[PhanLoai]
                 SET [PhanLoai] = ?
               WHERE ID = ?
                 """;
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, pl.getTenPhanLoai());
            ps.setObject(2, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean updateKC(KichCo kc, String id) {
        String sql = """
              UPDATE [dbo].[KichCo]
                 SET [TenKichCo] = ?
               WHERE id = ?
                 """;
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, kc.getTenKichCo());
            ps.setObject(2, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean updateTT(TinhTrang tt, String id) {
        String sql = """
              UPDATE [dbo].[TinhTrang]
                 SET [TinhTrang] = ?
               WHERE ID = ?
                 """;
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, tt.getTenTinhTrang());
            ps.setObject(2, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
////////////////////delete
    public boolean removeNSX(String id) {
        String sql = """
                     UPDATE [dbo].[NhaSanXuat]
                    SET [Deleted] = 0
                  WHERE [dbo].[NhaSanXuat].[ID]  = ?
                 """;
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
    public boolean removeCL(String id) {
        String sql = """
                    DELETE FROM [dbo].[ChatLieu]
                          WHERE ID = ?
                 """;
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
    public boolean removeMS(String id) {
        String sql = """
                    DELETE FROM [dbo].[MauSac]
                    	 WHERE ID = ?
                 """;
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
    public boolean removeLQ(String id) {
        String sql = """
                        DELETE FROM [dbo].[LoaiQuai]
                          WHERE ID = ?
                 """;
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
    public boolean removeTH(String id) {
        String sql = """
                    DELETE FROM [dbo].[ThuongHieu]
                    	 WHERE ID = ?
                 """;
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
    public boolean removePL(String id) {
        String sql = """
                    DELETE FROM [dbo].[PhanLoai]
                          WHERE ID = ?
                 """;
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
    public boolean removeKC(String id) {
        String sql = """
                    DELETE FROM [dbo].[KichCo]
                          WHERE ID = ?
                 """;
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
    public boolean removeTT(String id) {
        String sql = """
                    DELETE FROM [dbo].[TinhTrang]
                          WHERE ID = ?
                 """;
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
    public boolean removePK(String id) {
        String sql = """
                    DELETE FROM [dbo].[PhuKien]
                          WHERE ID = ?
                 """;
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
    
}
