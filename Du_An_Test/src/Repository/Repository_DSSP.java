/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DBConnect.DBConnect_An;
import Model_HoaDon.Model_DSSanPham;
import Model_SanPham.Model_DanhSachSanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import main.entity.SanPhamChiTiet;
import main.response.SanPhamChiTietRespone;

/**
 *
 * @author ADMIN
 */
public class Repository_DSSP {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

   public ArrayList<SanPhamChiTietRespone> getAll() {
        String sql = "SELECT \n" +
"    spct.id, \n" +
"    spct.ma_san_pham_chi_tiet, \n" +
"    sp.ten_san_pham, \n" +
"    hsx.ten_hang_san_xuat,\n" +
"	nsx.nam_san_xuat,\n" +
"    xs.xuat_su,\n" +
"    nd.nong_do,\n" +
"    pl.ten_phan_loai,\n" +
"    dt.Dung_tich,\n" +
"    ncc.ten_nha_cung_cap,\n" +
"    spct.gia_nhap,\n" +
"    spct.gia_ban, \n" +
"    spct.so_luong_ton, \n" +
"    CASE \n" +
"        WHEN spct.so_luong_ton > 0 THEN 1\n" +
"        ELSE 0\n" +
"    END AS trang_thai\n" +
"FROM \n" +
"    dbo.SanPhamChiTiet spct\n" +
"INNER JOIN \n" +
"    dbo.SanPham sp ON spct.id_san_pham = sp.id\n" +
"INNER JOIN dbo.HangSanXuat hsx ON spct.id_hang_san_xuat = hsx.id\n" +
"	\n" +
"INNER JOIN dbo.XuatSu xs ON spct.id_xuat_su = xs.id\n" +
"    INNER JOIN dbo.NongDo nd ON spct.id_nong_do = nd.id\n" +
"    INNER JOIN dbo.NamSanXuat nsx ON spct.id_nam_san_xuat = nsx.id\n" +
"    INNER JOIN dbo.PhanLoai pl ON spct.id_phan_loai = pl.id\n" +
"    INNER JOIN dbo.DungTich dt ON spct.id_dung_tich = dt.id\n" +
"    INNER JOIN dbo.NhaCungCap ncc ON spct.id_nha_cung_cap = ncc.id\n" +
"WHERE \n" +
"    spct.trang_thai_ban = 1;";

        ArrayList<SanPhamChiTietRespone> lists = new ArrayList<>();
        try (Connection con = DBConnect_An.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new SanPhamChiTietRespone(
                        rs.getInt(1), 
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getDouble(11),
                        rs.getDouble(12),
                        rs.getInt(13),
                        rs.getBoolean(14)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }
    public boolean updateSanPhamChiTiet(SanPhamChiTiet spct, String maSPCT) {
        int check = 0;
        String sql = "update SanPhamChiTiet\n" +
"                set id_san_pham=?,id_hang_san_xuat=?,id_nam_san_xuat=?,id_xuat_su=?,id_nong_do=?,id_phan_loai=?,id_dung_tich=?,id_nha_cung_cap=?,gia_nhap=?,gia_ban=?,so_luong_ton=?\n" +
"                where ma_san_pham_chi_tiet=?";
        try {
            Connection con = DBConnect_An.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
                    
            ps.setObject(1, spct.getSanPhamID());
            ps.setObject(2, spct.getHangSXID());
            ps.setObject(3, spct.getNamSXID());
            ps.setObject(4, spct.getXuatsuID());
            ps.setObject(5, spct.getNongDoID());
            ps.setObject(6, spct.getPhanLoaiID());
            ps.setObject(7, spct.getDungTichID());
            ps.setObject(8, spct.getNCCID());
            ps.setObject(9, spct.getGiaBan());
            ps.setObject(10, spct.getGiaNhap());
            ps.setObject(11, spct.getSoLuongTon());
            ps.setObject(12, maSPCT);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
    public boolean updateTrangThai(Integer id) {
        int check = 0;
        String sql = "update SanPhamChiTiet\n"
                + "set trang_thai_ban = 0\n"
                + "where id =?";
        try (Connection con = DBConnect_An.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
    public boolean updateSoLuong(SanPhamChiTietRespone sp) {
        int check = 0;
        String sql = "UPDATE SanPhamChiTiet\n"
                + "SET so_luong_ton = ?\n"
                + "WHERE id=?;";
        try (Connection con = DBConnect_An.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, sp.getSoLuong());
            ps.setObject(2, sp.getID());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;

    }
    public ArrayList<SanPhamChiTietRespone> locTheoDieuKienBanHang(String tenDungTich, String tenHangSX, String tenNongDo, boolean sapXepGiaBan) {

        String sql = "SELECT \n" +
"    spct.id, \n" +
"    spct.ma_san_pham_chi_tiet, \n" +
"    sp.ten_san_pham, \n" +
"    hsx.ten_hang_san_xuat,\n" +
"	nsx.nam_san_xuat,\n" +
"    xs.xuat_su,\n" +
"    nd.nong_do,\n" +
"    pl.ten_phan_loai,\n" +
"    dt.Dung_tich,\n" +
"    ncc.ten_nha_cung_cap,\n" +
"	spct.gia_nhap,\n" +
"    spct.gia_ban, \n" +
"    spct.so_luong_ton, \n" +
"    CASE \n" +
"        WHEN spct.so_luong_ton > 0 THEN 1\n" +
"        ELSE 0\n" +
"    END AS trang_thai\n" +
"FROM \n" +
"    dbo.SanPhamChiTiet spct\n" +
"INNER JOIN \n" +
"    dbo.SanPham sp ON spct.id_san_pham = sp.id\n" +
"INNER JOIN dbo.HangSanXuat hsx ON spct.id_hang_san_xuat = hsx.id\n" +
"	\n" +
"INNER JOIN dbo.XuatSu xs ON spct.id_xuat_su = xs.id\n" +
"    INNER JOIN dbo.NongDo nd ON spct.id_nong_do = nd.id\n" +
"    INNER JOIN dbo.NamSanXuat nsx ON spct.id_nam_san_xuat = nsx.id\n" +
"    INNER JOIN dbo.PhanLoai pl ON spct.id_phan_loai = pl.id\n" +
"    INNER JOIN dbo.DungTich dt ON spct.id_dung_tich = dt.id\n" +
"    INNER JOIN dbo.NhaCungCap ncc ON spct.id_nha_cung_cap = ncc.id\n" +
"WHERE \n" +
"       spct.trang_thai_ban = 1\n" +
"	AND hsx.ten_hang_san_xuat LIKE ?\n" +
"	AND dt.Dung_tich LIKE ?\n" +
"	AND nd.nong_do LIKE ?\n" +
"ORDER BY\n" +
"	CASE WHEN ? = 0 THEN spct.gia_ban END ASC,\n" +
"	CASE WHEN ? = 1 THEN spct.gia_ban END DESC;";

        ArrayList<SanPhamChiTietRespone> lists = new ArrayList<>();
        try (Connection con = DBConnect_An.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, tenHangSX);
            ps.setObject(2, tenDungTich);
            ps.setObject(3, tenNongDo);
            ps.setObject(4, sapXepGiaBan ? "0" : "1");
            ps.setObject(5, sapXepGiaBan ? "0" : "1");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new SanPhamChiTietRespone(
                        rs.getInt(1), 
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getDouble(11),
                        rs.getDouble(12),
                        rs.getInt(13),
                        rs.getBoolean(14)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }
       
    public SanPhamChiTietRespone getSanPhamChiTietByMaSPCT(String maSPCT) {
        String sql = "SELECT \n" +
"    spct.id, \n" +
"    spct.ma_san_pham_chi_tiet, \n" +
"    sp.ten_san_pham, \n" +
"    hsx.ten_hang_san_xuat,\n" +
"	nsx.nam_san_xuat,\n" +
"    xs.xuat_su,\n" +
"    nd.nong_do,\n" +
"    pl.ten_phan_loai,\n" +
"    dt.Dung_tich,\n" +
"    ncc.ten_nha_cung_cap,\n" +
"	spct.gia_nhap,\n" +
"    spct.gia_ban, \n" +
"    spct.so_luong_ton, \n" +
"    CASE \n" +
"        WHEN spct.so_luong_ton > 0 THEN 1\n" +
"        ELSE 0\n" +
"    END AS trang_thai\n" +
"FROM \n" +
"    dbo.SanPhamChiTiet spct\n" +
"INNER JOIN \n" +
"    dbo.SanPham sp ON spct.id_san_pham = sp.id\n" +
"INNER JOIN dbo.HangSanXuat hsx ON spct.id_hang_san_xuat = hsx.id\n" +
"	\n" +
"INNER JOIN dbo.XuatSu xs ON spct.id_xuat_su = xs.id\n" +
"    INNER JOIN dbo.NongDo nd ON spct.id_nong_do = nd.id\n" +
"    INNER JOIN dbo.NamSanXuat nsx ON spct.id_nam_san_xuat = nsx.id\n" +
"    INNER JOIN dbo.PhanLoai pl ON spct.id_phan_loai = pl.id\n" +
"    INNER JOIN dbo.DungTich dt ON spct.id_dung_tich = dt.id\n" +
"    INNER JOIN dbo.NhaCungCap ncc ON spct.id_nha_cung_cap = ncc.id\n" +
"WHERE \n" +
"       spct.trang_thai_ban = 1 \n"
                + "    AND spct.ma_san_pham_chi_tiet = ?;";

        SanPhamChiTietRespone spct = null;
        try (Connection con = DBConnect_An.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maSPCT);  // Sử dụng setString thay vì setObject vì maSPCT là String
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                spct = new SanPhamChiTietRespone(
                        rs.getInt("id"),
                        rs.getString("ma_san_pham_chi_tiet"),
                        rs.getString("ten_san_pham"),
                        rs.getString("ten_hang_san_xuat"),
                        rs.getString("nam_san_xuat"),
                        rs.getString("xuat_su"),
                        rs.getString("nong_do"),
                        rs.getString("ten_phan_loai"),
                        rs.getString("dung-tich"),
                        rs.getString("ten_nha_cung_cap"),
                        rs.getDouble("gia_nhap"),
                        rs.getDouble("gia_ban"),
                        rs.getInt("so_luong_ton"),
                        rs.getBoolean("trang_thai")
                );
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return spct;
    }
    public boolean HideSanPhamChiTiet(Integer id_san_pham) {
        int check = 0;
        String sql = "update SanPhamChiTiet\n"
                + "	set trang_thai_ban = 0\n"
                + "	where id_san_pham =?";
        try (Connection con = DBConnect_An.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id_san_pham);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
     public ArrayList<SanPhamChiTietRespone> getAllGiamDan() {
        String sql = "SELECT \n" +
"    spct.id, \n" +
"    spct.ma_san_pham_chi_tiet, \n" +
"    sp.ten_san_pham, \n" +
"    hsx.ten_hang_san_xuat,\n" +
"	nsx.nam_san_xuat,\n" +
"    xs.xuat_su,\n" +
"    nd.nong_do,\n" +
"    pl.ten_phan_loai,\n" +
"    dt.Dung_tich,\n" +
"    ncc.ten_nha_cung_cap,\n" +
"    spct.gia_nhap,\n" +
"    spct.gia_ban, \n" +
"    spct.so_luong_ton, \n" +
"    CASE \n" +
"        WHEN spct.so_luong_ton > 0 THEN 1\n" +
"        ELSE 0\n" +
"    END AS trang_thai\n" +
"FROM \n" +
"    dbo.SanPhamChiTiet spct\n" +
"INNER JOIN \n" +
"    dbo.SanPham sp ON spct.id_san_pham = sp.id\n" +
"INNER JOIN dbo.HangSanXuat hsx ON spct.id_hang_san_xuat = hsx.id\n" +
"	\n" +
"INNER JOIN dbo.XuatSu xs ON spct.id_xuat_su = xs.id\n" +
"    INNER JOIN dbo.NongDo nd ON spct.id_nong_do = nd.id\n" +
"    INNER JOIN dbo.NamSanXuat nsx ON spct.id_nam_san_xuat = nsx.id\n" +
"    INNER JOIN dbo.PhanLoai pl ON spct.id_phan_loai = pl.id\n" +
"    INNER JOIN dbo.DungTich dt ON spct.id_dung_tich = dt.id\n" +
"    INNER JOIN dbo.NhaCungCap ncc ON spct.id_nha_cung_cap = ncc.id\n" +
"WHERE \n" +
"    spct.trang_thai_ban = 1\n"
                + "ORDER BY \n"
                + "    sp.ngay_tao DESC;";

        ArrayList<SanPhamChiTietRespone> lists = new ArrayList<>();
        try (Connection con = DBConnect_An.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new SanPhamChiTietRespone(
                        rs.getInt(1), 
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getDouble(11),
                        rs.getDouble(12),
                        rs.getInt(13),
                        rs.getBoolean(14)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }
      public boolean add(SanPhamChiTiet spct) {
        int check = 0;

        String sql = "Insert into SanPhamChiTiet(id_san_pham,id_hang_san_xuat,id_nam_san_xuat,id_xuat_su,id_nong_do,id_phan_loai,id_dung_tich,id_nha_cung_cap,gia_nhap,gia_ban,so_luong_ton,trang_thai)\n" +
"Values(?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection con = DBConnect_An.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            // Kiểm tra giá trị không null
            if (spct.getSanPhamID() == null || spct.getHangSXID() == null
                    || spct.getNamSXID() == null || spct.getXuatsuID() == null
                    || spct.getNongDoID() == null || spct.getPhanLoaiID() == null
                    || spct.getDungTichID() == null || spct.getNCCID() == null
                    || spct.getGiaNhap() == null || spct.getGiaBan() == null
                    || spct.getSoLuongTon() == null) {
                throw new IllegalArgumentException("Các giá trị bắt buộc không thể null.");
            }

            ps.setObject(1, spct.getSanPhamID());
            ps.setObject(2, spct.getHangSXID());
            ps.setObject(3, spct.getNamSXID());
            ps.setObject(4, spct.getXuatsuID());
            ps.setObject(5, spct.getNongDoID());
            ps.setObject(6, spct.getPhanLoaiID());
            ps.setObject(7, spct.getDungTichID());
            ps.setObject(8, spct.getNCCID());
            ps.setObject(9, spct.getGiaNhap());
            ps.setObject(10, spct.getGiaBan());
            ps.setObject(11, spct.getSoLuongTon());
            ps.setObject(12, spct.isTrangThai());

            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi dữ liệu: " + e.getMessage());
        }

        return check > 0;
    }
      public ArrayList<SanPhamChiTietRespone> searchh(String maSP) {
        String sql = "SELECT \n" +
"    spct.id, \n" +
"    spct.ma_san_pham_chi_tiet, \n" +
"    sp.ten_san_pham, \n" +
"    hsx.ten_hang_san_xuat,\n" +
"    nsx.nam_san_xuat,\n" +
"    xs.xuat_su,\n" +
"    nd.nong_do,\n" +
"    pl.ten_phan_loai,\n" +
"    dt.Dung_tich,\n" +
"    ncc.ten_nha_cung_cap,\n" +
"    spct.gia_nhap, \n" +
"    spct.gia_ban, \n" +
"    spct.so_luong_ton, \n" +
"    CASE \n" +
"        WHEN spct.so_luong_ton > 0 THEN 1\n" +
"        ELSE 0\n" +
"    END AS trang_thai\n" +
"FROM \n" +
"    dbo.SanPhamChiTiet spct\n" +
"INNER JOIN \n" +
"    dbo.SanPham sp ON spct.id_san_pham = sp.id\n" +
"INNER JOIN dbo.HangSanXuat hsx ON spct.id_hang_san_xuat = hsx.id\n" +
"INNER JOIN dbo.XuatSu xs ON spct.id_xuat_su = xs.id\n" +
"INNER JOIN dbo.NongDo nd ON spct.id_nong_do = nd.id\n" +
"INNER JOIN dbo.NamSanXuat nsx ON spct.id_nam_san_xuat = nsx.id\n" +
"INNER JOIN dbo.PhanLoai pl ON spct.id_phan_loai = pl.id\n" +
"INNER JOIN dbo.DungTich dt ON spct.id_dung_tich = dt.id\n" +
"INNER JOIN dbo.NhaCungCap ncc ON spct.id_nha_cung_cap = ncc.id\n" +
"WHERE \n" +
"    spct.ma_san_pham_chi_tiet LIKE ?\n" +
"    OR sp.ten_san_pham LIKE ?\n" +
"    OR hsx.ten_hang_san_xuat LIKE ?\n" +
"    OR nsx.nam_san_xuat LIKE ?\n" +
"    OR xs.xuat_su LIKE ?\n" +
"    OR nd.nong_do LIKE ?\n" +
"    OR pl.ten_phan_loai LIKE ?\n" +
"    OR dt.Dung_tich LIKE ?\n" +
"    OR ncc.ten_nha_cung_cap LIKE ?\n" +
"    OR spct.gia_nhap LIKE ?\n" +
"    OR spct.gia_ban LIKE ?\n" +
"    OR spct.so_luong_ton LIKE ?\n" +
"    OR spct.trang_thai LIKE ?;";

        ArrayList<SanPhamChiTietRespone> lists = new ArrayList<>();
        try (Connection con = DBConnect_An.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            String searchString = "%" + maSP + "%"; // Thêm % vào để tạo thành mô phỏng tìm kiếm
            // Đặt giá trị cho các tham số trong câu truy vấn SQL
            for (int i = 1; i <= 13; i++) {
                ps.setString(i, searchString);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ma_san_pham_chi_tiet = rs.getString("ma_san_pham_chi_tiet");
                String ten_san_pham = rs.getString("ten_san_pham");
                String ten_hang_san_xuat = rs.getString("ten_hang_san_xuat");
                String nam_san_xuat = rs.getString("nam_san_xuat");
                String xuat_su = rs.getString("xuat_su");
                String nong_do = rs.getString("nong_do");
                String ten_phan_loai = rs.getString("ten_phan_loai");
                String dung_tich = rs.getString("Dung_tich");
                String ten_nha_cung_cap = rs.getString("ten_nha_cung_cap");
                Double giaNhap = rs.getDouble("gia_nhap");
                Double giaBan = rs.getDouble("gia_ban");
                Integer so_luong_ton = rs.getInt("so_luong_ton");
                Boolean trang_thai = rs.getBoolean("trang_thai");

                SanPhamChiTietRespone sanPham = new SanPhamChiTietRespone(id, ma_san_pham_chi_tiet, ten_san_pham,ten_hang_san_xuat, nam_san_xuat, xuat_su, nong_do, ten_phan_loai, dung_tich, ten_nha_cung_cap, giaNhap, giaBan, so_luong_ton, true);
                lists.add(sanPham);
            }
        } catch (Exception e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
            e.printStackTrace(); // In ra stack trace để xem chi tiết lỗi
        }
        return lists;
    }
      public ArrayList<SanPhamChiTietRespone> getListSanPhamChiTietByMaSPCT(String maSPCT) {
        String sql = "SELECT \n" +
"    spct.id, \n" +
"    spct.ma_san_pham_chi_tiet, \n" +
"    sp.ten_san_pham, \n" +
"    hsx.ten_hang_san_xuat,\n" +
"	nsx.nam_san_xuat,\n" +
"    xs.xuat_su,\n" +
"    nd.nong_do,\n" +
"    pl.ten_phan_loai,\n" +
"    dt.Dung_tich,\n" +
"    ncc.ten_nha_cung_cap,\n" +
"	spct.gia_nhap,\n" +
"    spct.gia_ban, \n" +
"    spct.so_luong_ton, \n" +
"    CASE \n" +
"        WHEN spct.so_luong_ton > 0 THEN 1\n" +
"        ELSE 0\n" +
"    END AS trang_thai\n" +
"FROM \n" +
"    dbo.SanPhamChiTiet spct\n" +
"INNER JOIN \n" +
"    dbo.SanPham sp ON spct.id_san_pham = sp.id\n" +
"INNER JOIN dbo.HangSanXuat hsx ON spct.id_hang_san_xuat = hsx.id\n" +
"	\n" +
"INNER JOIN dbo.XuatSu xs ON spct.id_xuat_su = xs.id\n" +
"    INNER JOIN dbo.NongDo nd ON spct.id_nong_do = nd.id\n" +
"    INNER JOIN dbo.NamSanXuat nsx ON spct.id_nam_san_xuat = nsx.id\n" +
"    INNER JOIN dbo.PhanLoai pl ON spct.id_phan_loai = pl.id\n" +
"    INNER JOIN dbo.DungTich dt ON spct.id_dung_tich = dt.id\n" +
"    INNER JOIN dbo.NhaCungCap ncc ON spct.id_nha_cung_cap = ncc.id\n" +
"WHERE \n" +
"       spct.trang_thai_ban = 1 \n"
                + "    AND spct.ma_san_pham_chi_tiet = ?;";

        ArrayList<SanPhamChiTietRespone> lists = new ArrayList<>();
        try (Connection con = DBConnect_An.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maSPCT);  // Sử dụng setString thay vì setObject vì maSPCT là String
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                lists.add(new SanPhamChiTietRespone(rs.getInt("id"), rs.getString("ma_san_pham_chi_tiet"), rs.getString("ten_san_pham"), rs.getString("ten_hang_san_xuat"), rs.getString("nam_san_xuat"), rs.getString("xuat_su"), rs.getString("nong_do"), rs.getString("ten_phan_loai"), rs.getString("Dung_tich"), rs.getString("ten_nha_cung_cap"), rs.getDouble("gia_nhap"), rs.getDouble("gia_ban"), rs.getInt("so_luong_ton"), rs.getBoolean("trang_thai")));
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return lists;
    }
    
      
}
