/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DBConnect.DBConnect_An;
import Model_HoaDon.Model_GioHang;
import Model_SanPham.Model_ChiTietSanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import main.response.HoaDonChiTietReponse;

/**
 *
 * @author ADMIN
 */
public class Repository_HDCT {
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    
    
    public ArrayList<Model_GioHang> getAll(Integer hoaDonID) {
        String sql = "SELECT\n" +
"    hd.id,\n" +
"    spct.ma_san_pham_chi_tiet,\n" +
"    sp.ten_san_pham,\n" +
"	hsx.ten_hang_san_xuat,\n" +
"    xs.xuat_su,\n" +
"    nd.nong_do,\n" +
"    pl.ten_phan_loai,\n" +
"    dt.Dung_tich,\n" +
"    ncc.ten_nha_cung_cap,\n" +
"    spct.gia_nhap,\n" +
"    spct.gia_ban,\n" +
"    hdct.so_luong,\n" +
"    spct.trang_thai,\n" +
"    hdct.so_luong * hdct.gia_ban AS total_price,\n" +
"    hdct.id_hoa_don,\n" +
"    spct.id AS id_san_pham_chi_tiet\n" +
"FROM\n" +
"    dbo.SanPhamChiTiet spct\n" +
"    INNER JOIN dbo.HoaDonChiTiet hdct ON spct.id = hdct.id_san_pham_chi_tiet\n" +
"    INNER JOIN\n" +
"    dbo.HangSanXuat hsx ON spct.id_hang_san_xuat = hsx.id\n" +
"INNER JOIN\n" +
"    dbo.XuatSu xs ON spct.id_xuat_su = xs.id\n" +
"INNER JOIN\n" +
"    dbo.NongDo nd ON spct.id_nong_do = nd.id\n" +
"INNER JOIN\n" +
"    dbo.NamSanXuat nsx ON spct.id_nam_san_xuat = nsx.id\n" +
"INNER JOIN\n" +
"    dbo.PhanLoai pl ON spct.id_phan_loai = pl.id\n" +
"INNER JOIN\n" +
"    dbo.DungTich dt ON spct.id_dung_tich = dt.id\n" +
"INNER JOIN\n" +
"    dbo.NhaCungCap ncc ON spct.id_nha_cung_cap = ncc.id\n" +
"INNER JOIN \n" +
"	dbo.HoaDon hd ON hdct.id_hoa_don = hd.id\n" +
"INNER JOIN \n" +
"	dbo.SanPham sp ON spct.id_san_pham = sp.id\n" +
"WHERE\n" +
"    hdct.id_hoa_don = ?;";
        ArrayList<Model_GioHang> lists = new ArrayList<>();
        try (Connection con = DBConnect_An.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, hoaDonID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new Model_GioHang(
                        rs.getInt(1), 
                        rs.getString(2),
                        rs.getString(3), 
                        rs.getString(4),
                        rs.getInt(5), 
                        rs.getString(6), 
                        rs.getString(7), 
                        rs.getString(8),
                        rs.getString(9), 
                        rs.getString(10), 
                        rs.getInt(11), 
                        rs.getInt(12),
                        rs.getInt(13), 
                        rs.getBoolean(14), 
                        rs.getInt(15), 
                        rs.getInt(16), 
                        rs.getInt(17)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }
    public int updateGioHang(String ma_hdct, HoaDonChiTietReponse response) throws SQLException{
        String sql = "update HoaDonChiTiet\n" +
"set so_luong = ? where ma_hoa_don_chi_tiet = ?";
        try(Connection con = DBConnect_An.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setObject(1, response.getSoLuong());
            ps.setObject(2, ma_hdct);
            
            return ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
