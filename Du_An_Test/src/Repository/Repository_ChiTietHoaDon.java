/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import java.sql.*;
import java.util.ArrayList;
import Model_Voucher.Model_ChiTietHoaDon;

import DBConnect.DBConnect_An;

/**
 *
 * @author Admin
 */
public class Repository_ChiTietHoaDon {
    public ArrayList<Model_ChiTietHoaDon> getAll(int id) {
        String sql = """
                     SELECT HDCT.id,SP.ma_san_pham,SP.ten_san_pham, HDCT.so_luong,gia_ban,HD.tong_tien,HD.trang_thai                                                                                
                     FROM HoaDonChiTiet HDCT
                     JOIN SanPham SP ON HDCT.id_san_pham_chi_tiet = SP.id
                     JOIN HoaDon HD ON HDCT.id_hoa_don = HD.id 
                     where HDCT.id_hoa_don = ?                     															  
                     """;
        ArrayList<Model_ChiTietHoaDon> al = new ArrayList<>();
        try(Connection con = DBConnect_An.getConnection(); PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Model_ChiTietHoaDon hdct = new Model_ChiTietHoaDon();
                hdct.setId(rs.getInt(1));
                hdct.setMaSP(rs.getString(2));
                hdct.setTenSP(rs.getString(3));
                hdct.setSoLuong(rs.getInt(4));
                hdct.setDonGia(rs.getDouble(5));
                hdct.setTongTien(rs.getDouble(6));
                hdct.setTrangThai(rs.getBoolean(7));
                al.add(hdct);
            }
                System.out.print(al);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return al;
    } 

}
