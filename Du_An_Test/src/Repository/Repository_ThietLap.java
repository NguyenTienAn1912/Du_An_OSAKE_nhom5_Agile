/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;
import DBConnect.DBConnect_An;

import Model.Model_ThietLap;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Admin
 */
public class Repository_ThietLap {
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement ps = null;
    private String sql = null;
    public ArrayList<Model_ThietLap> getAll(){
         sql = "select id,ma_nhan_vien,ten_nhan_vien,email,mat_khau,vai_tro,trang_thai from NhanVien";
         ArrayList<Model_ThietLap> list_ThietLap = new ArrayList();
         try {
             con = DBConnect_An.getConnection();
             ps = con.prepareStatement(sql);
             rs = ps.executeQuery();
             while (rs.next()) {                 
                int id;
                String maNV;
                String tenNV;
                String tenDangNhap;
                String matKhau;
                boolean phanQuyen;
                boolean tinhTrang;
                
                id = rs.getInt(1);
                maNV = rs.getString(2);
                tenNV = rs.getString(3);
                tenDangNhap = rs.getString(4);
                matKhau = rs.getString(5);
                phanQuyen = rs.getBoolean(6);
                tinhTrang = rs.getBoolean(7);
                
                Model_ThietLap tl = new Model_ThietLap(id, maNV, tenNV, tenDangNhap, matKhau, phanQuyen, tinhTrang);
                list_ThietLap.add(tl);
            }
            return list_ThietLap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }    
    public int deleteThietLap(int id_Xoa) {
        sql = "delete from NhanVien where id=?";
        try {
            con = DBConnect_An.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id_Xoa);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int  addThietLap(Model_ThietLap tl) {
  sql="insert into NhanVien(ma_nhan_vien,ten_nhan_vien,email,mat_khau,vai_tro,trang_thai) values (?,?,?,?,?,?)";
    try {
        con=DBConnect_An.getConnection();
        ps=con.prepareStatement(sql);
        ps.setObject(1, tl.getMaNV());
        ps.setObject(2, tl.getTenNV());
        ps.setObject(3, tl.getTenDangNhap());
        ps.setObject(4, tl.getMatKhau());
        ps.setObject(5, tl.isPhanQuyen());
        ps.setObject(6, tl.isTrangThai());
        return ps.executeUpdate(); 
    } catch (Exception e) {
        e.printStackTrace();
        return 0;
    }
}  
      public ArrayList<Model_ThietLap> TimTen(String tenCanTim){
         sql = "select id,ma_nhan_vien,ten_nhan_vien,email,mat_khau,vai_tro,trang_thai from NhanVien where email like ? ";
         ArrayList<Model_ThietLap> list_ThietLap = new ArrayList();
         try {
             con = DBConnect_An.getConnection();
             ps = con.prepareStatement(sql);
             ps.setObject(1,"%"+tenCanTim+"%");
             rs = ps.executeQuery();
             while (rs.next()) {                 
                int id;
                String maNV;
                String tenNV;
                String tenDangNhap;
                String matKhau;
                boolean phanQuyen;
                boolean tinhTrang;
                
                id = rs.getInt(1);
                maNV = rs.getString(2);
                tenNV = rs.getString(3);
                tenDangNhap = rs.getString(4);
                matKhau = rs.getString(5);
                phanQuyen = rs.getBoolean(6);
                tinhTrang = rs.getBoolean(7);
                
                Model_ThietLap tl = new Model_ThietLap(id, maNV, tenNV, tenDangNhap, matKhau, phanQuyen, tinhTrang);
                list_ThietLap.add(tl);
            }
            return list_ThietLap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public int updateThietLap(int idCanSua, Model_ThietLap tl){
      sql="update NhanVien set ma_nhan_vien = ?,ten_nhan_vien=?,email=?,mat_khau=?,vai_tro=?,trang_thai=? where id = ?  ";
      try {
        con = DBConnect_An.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, tl.getMaNV());
            ps.setObject(2, tl.getTenNV());
            ps.setObject(3, tl.getTenDangNhap());
            ps.setObject(4, tl.getMatKhau());
            ps.setObject(5, tl.isPhanQuyen());
            ps.setObject(6, tl.isTrangThai());
            ps.setObject(7, idCanSua);
            return ps.executeUpdate();
        
    } catch (Exception e) {
        e.printStackTrace();
        return 0;
    }
  }
public Boolean CheckTenDangNhap(String tenDangNhap)
    {
        Boolean b = false;      
        sql = "select TOP (1) * from NhanVien where email = ?";
        
        try {
            con = DBConnect_An.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, tenDangNhap);
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
    public Boolean CheckMaNV(String maNV)
    {
        Boolean b = false;      
        sql = "select TOP (1) * from NhanVien where ma_nhan_vien = ?";
        
        try {
            con = DBConnect_An.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maNV);
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
}
