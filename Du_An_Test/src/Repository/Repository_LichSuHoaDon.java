/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;
import java.sql.*;
import java.util.ArrayList;
import Model_Voucher.Model_LichSuHoaDon;

import DBConnect.DBConnect_An;
/**
 *
 * @author Admin
 */
public class Repository_LichSuHoaDon {
    public ArrayList<Model_LichSuHoaDon> getAll() {
        String sql = """
                    select Hoadon.id,Hoadon.ma_hoa_don,NhanVien.ten_nhan_vien,KhachHang.ho_ten,Hoadon.tong_tien,Hoadon.ngay_tao,Hoadon.trang_thai 
                    from HoaDon 
                    join NhanVien on NhanVien.id = HoaDon.id_nhan_vien
                    join KhachHang on KhachHang.id = HoaDon.id_khach_hang
                     """;
        ArrayList<Model_LichSuHoaDon> ls = new ArrayList<>();
        try (Connection con = DBConnect_An.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {           
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Model_LichSuHoaDon lshd = new Model_LichSuHoaDon();
                lshd.setId(rs.getInt(1));
                lshd.setMaHD(rs.getString(2));                
                lshd.setTenNV(rs.getString(3));
                lshd.setTenKH(rs.getString(4));
                lshd.setTongTien(rs.getDouble(5));
                lshd.setNgayTao(rs.getString(6));
                lshd.setTrangThai(rs.getBoolean(7));
                ls.add(lshd);
            }
            System.out.print(ls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
    public ArrayList<Model_LichSuHoaDon> TimMa(String maCanTim) {
        String sql = """
                     select Hoadon.id,Hoadon.ma_hoa_don,NhanVien.ten_nhan_vien,KhachHang.ho_ten,Hoadon.tong_tien,Hoadon.ngay_tao,Hoadon.trang_thai 
                     from HoaDon 
                     join NhanVien on NhanVien.id = HoaDon.id_nhan_vien
                     join KhachHang on KhachHang.id = HoaDon.id_khach_hang
                     where Hoadon.ma_hoa_don like ?
                     """;
        ArrayList<Model_LichSuHoaDon> ls = new ArrayList<>();
        try (Connection con = DBConnect_An.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {  
            ps.setObject(1,"%"+maCanTim+"%");
            ResultSet rs = ps.executeQuery();          
            while (rs.next()) {
                Model_LichSuHoaDon lshd = new Model_LichSuHoaDon();
                lshd.setId(rs.getInt(1));
                lshd.setMaHD(rs.getString(2));                
                lshd.setTenNV(rs.getString(3));
                lshd.setTenKH(rs.getString(4));
                lshd.setTongTien(rs.getDouble(5));
                lshd.setNgayTao(rs.getString(6));
                lshd.setTrangThai(rs.getBoolean(7));
                ls.add(lshd);
            }
            System.out.print(ls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
}
