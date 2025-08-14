///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package Repository;

//import Model.Model_TaiKhoan;
import DBConnect.DBConnect_An;
import Model_Voucher.Model_TaiKhoan;
import Model_Nhan_Vien.Model_Nhan_Vien;
import java.sql.*;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.util.Date;
/**
 *
 * @author Hùng
 */
public class Repository_Nhan_Vien {

    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement ps = null;
    private String sql = null;

    public ArrayList<Model_Nhan_Vien> getAll() {
        
        
                
        sql = "select id,ma_nhan_vien,ten_nhan_vien,gioi_tinh,so_dien_thoai,ngay_sinh,mat_khau,email,vai_tro,trang_thai from NhanVien";
        ArrayList<Model_Nhan_Vien> list_NhanVien = new ArrayList();
        try {
            con = DBConnect_An.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id;
                String MaNV;
                String TenNV;
                boolean GioiTinh;
                String SoDT;
                Date NgaySinh;
//                String NgaySinh;
                String MatKhau;
                String Email;
                boolean VaiTro;
                boolean TinhTrang;

                id = rs.getInt(1);
                MaNV = rs.getString(2);
                TenNV = rs.getString(3);
                GioiTinh = rs.getBoolean(4);
                SoDT = rs.getString(5);
                NgaySinh = rs.getDate(6);
//                MatKhau = rs.getString(7);
                Email = rs.getString(8);
                VaiTro = rs.getBoolean(9);
                TinhTrang = rs.getBoolean(10);
                
                
                Model_Nhan_Vien nv = new Model_Nhan_Vien(id, MaNV, TenNV, GioiTinh, SoDT, NgaySinh, Email, VaiTro, TinhTrang);
                list_NhanVien.add(nv);
            }
            return list_NhanVien;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void Them(Model_Nhan_Vien nv) {
        try {
            String sql = "insert into NhanVien(ma_nhan_vien,ten_nhan_vien,gioi_tinh,so_dien_thoai,ngay_sinh,mat_khau,email,vai_tro,trang_thai) Values\n"
                    + "(?,?,?,?,?,?,?,?,?)";
            con = DBConnect_An.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, nv.getMaNV());
            ps.setObject(2, nv.getTenNV());
            ps.setObject(3, nv.isGioiTinh());
            ps.setObject(4, nv.getSdt());
            ps.setObject(5, nv.getNgaySinh());
//            ps.setObject(6, nv.getMatKhau());
            ps.setObject(7, nv.getEmail());
            ps.setObject(8, nv.isVaiTro());
            ps.setObject(9, nv.isTinhTrang());

            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
    }
    public Boolean CheckSDT_NV(String so_dien_thoai)
    {
        Boolean b = false;
        
//        sql = "select id,ma_khach_hang,ho_ten,ngay_sinh,gioi_tinh,email,so_dien_thoai,dia_chi,ghi_chu from KhachHang where So_Dien_Thoai like ?";
        sql = "select TOP (1) * from NhanVien where so_dien_thoai = ?";
        
        try {
            con = DBConnect_An.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, so_dien_thoai);
            rs = ps.executeQuery();
            
            if (!rs.next()) {
                b = false;
            }
            else{
                b = true;
            }
            
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        return b;
    }    
    public Boolean CheckEMail(String so_dien_thoai)
    {
        Boolean b = false;
        
//        sql = "select id,ma_khach_hang,ho_ten,ngay_sinh,gioi_tinh,email,so_dien_thoai,dia_chi,ghi_chu from KhachHang where So_Dien_Thoai like ?";
        sql = "select TOP (1) * from NhanVien where email = ?";
        
        try {
            con = DBConnect_An.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, so_dien_thoai);
            rs = ps.executeQuery();
            
            if (!rs.next()) {
                b = false;
            }
            else{
                b = true;
            }
            
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        return b;
    }    
    public int XoaNhanVien(int idCanxoa) {
        sql = "delete from NhanVien where id = ?";
        try {// xóa ok
            con = DBConnect_An.getConnection();
            ps = con.prepareStatement(sql);
            // nếu có ? trong sql thì bắt buộc phải set
            ps.setObject(1, idCanxoa);
            return ps.executeUpdate();//thêm/sửa/xóa: executeUpdate();
        } catch (Exception e) {// k xóa được
            e.printStackTrace();
            return 0;
        }
    }

    public int updateNhanVien(int id, Model_Nhan_Vien nv) {
        // SQL với đầy đủ cột cần cập nhật và điều kiện WHERE
        sql = "UPDATE NhanVien SET ma_nhan_vien = ?, "
                + "ten_nhan_vien = ?, "
                + "gioi_tinh = ?, "
                + "so_dien_thoai = ?, "
                + "ngay_sinh = ?, "
                + "email = ?, "
                + "mat_khau = ?, "
                + "trang_thai = ?, "
                + "vai_tro = ? "
                + "WHERE id = ?";
   
        try (Connection con = DBConnect_An.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            String nvM = nv.getMaNV();
            // Gán giá trị cho các tham số trong câu lệnh SQL
            ps.setString(1, nvM);
            ps.setString(2, nv.getTenNV());
            ps.setBoolean(3, nv.isGioiTinh());
            ps.setString(4, nv.getSdt());
            
            java.util.Date utilDate = nv.getNgaySinh();
            java.sql.Date ngaySinh = new java.sql.Date(utilDate.getTime());
            ps.setDate(5, ngaySinh);
            
            //ps.setString(6, nv.getDiaChi());
            ps.setString(6, nv.getEmail());
//            ps.setString(7, nv.getMatKhau());
            ps.setBoolean(8, nv.isTinhTrang());
            ps.setBoolean(9, nv.isVaiTro()); // Đảm bảo giá trị này được thiết lập
            ps.setInt(10, id);

            // Thực thi lệnh SQL
            return ps.executeUpdate(); // Trả về số dòng bị ảnh hưởng
        } catch (Exception e) {
            e.printStackTrace();
           return 0; // Trả về 0 nếu có lỗi
        }
    }
    public ArrayList<Model_TaiKhoan> DangNhap() {
        ArrayList<Model_TaiKhoan> ds = new ArrayList<>();
        String sql = "select email,mat_khau ,vai_tro ,ma_nhan_vien from NhanVien";
        try (Connection con = DBConnect.DBConnect_An.getConnection()) {
            PreparedStatement ps = con.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String email = rs.getString("email");
                String matKhau = rs.getString("mat_khau");
                boolean vaiTro = rs.getBoolean("vai_tro");
                String maNV = rs.getString("ma_nhan_vien");
                Model_TaiKhoan ml = new Model_TaiKhoan(matKhau, maNV, email, vaiTro);
                ds.add(ml);
            }
            return ds;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }
  

}
