/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;



import Model.Model_Khach_Hang;
import DBConnect.DBConnect_An;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


/**
 *
 * @author Hùng
 */
public class Repository_KhachHang {
     private Connection con = null;
     private ResultSet  rs = null;
     private PreparedStatement ps = null;
     private String sql = null;
     
    public ArrayList<Model_Khach_Hang> getAll(){
         sql = "select id,ma_khach_hang,ho_ten,ngay_sinh,gioi_tinh,email,so_dien_thoai,dia_chi from KhachHang  ";
         ArrayList<Model_Khach_Hang> list_KhachHang = new ArrayList();
         try {
             con = DBConnect_An.getConnection();
             ps = con.prepareStatement(sql);
             rs = ps.executeQuery();
             while (rs.next()) {  
                 int id;
                String MaKH ;
                String TenKH;
                String NgaySinh;
                boolean GioiTinh;
                String Email;
                String SoDienThoai;
                String DiaChi;
                String GhiChu;
                id= rs.getInt(1);
                MaKH =  rs.getString(2);
                TenKH = rs.getString(3);
                SoDienThoai = rs.getString(7);
                NgaySinh = rs.getString(4);
                Email = rs.getString(6);
                GioiTinh = rs.getBoolean(5);
                DiaChi = rs.getString(8);
                
                 Model_Khach_Hang kh = new Model_Khach_Hang(id, MaKH, TenKH, NgaySinh, GioiTinh, Email, SoDienThoai, DiaChi);
                list_KhachHang.add(kh);
            }
            return list_KhachHang;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
         
    }
    public void them(Model_Khach_Hang kh) {
        sql = "INSERT INTO KhachHang (ma_khach_hang, ho_ten, ngay_sinh, gioi_tinh, email, so_dien_thoai, dia_chi,  ) VALUES\n"+"(?,?,?,?,?,?,?)";
        try {
            con = DBConnect_An.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, kh.getMaKH());
            ps.setObject(2, kh.getTenKH());
            ps.setObject(6, kh.getSDT());
            ps.setObject(3, kh.getNgaySinh());
            ps.setObject(5, kh.getEmail());
            ps.setObject(4, kh.isGioiTinh());
            ps.setObject(7, kh.getDiaChi());
            
                    
             ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
    }
    
    public Boolean CheckSDT_KH(String so_dien_thoai)
    {
        Boolean b = false;
        
//        sql = "select id,ma_khach_hang,ho_ten,ngay_sinh,gioi_tinh,email,so_dien_thoai,dia_chi,ghi_chu from KhachHang where So_Dien_Thoai like ?";
        sql = "select TOP (1) * from KhachHang where so_dien_thoai = ?";
        
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
    
    public ArrayList<Model_Khach_Hang> timkiem(String sdtcantim) {
        ArrayList<Model_Khach_Hang> list_KhachHang = new ArrayList<>();
        sql = "select id,ma_khach_hang,ho_ten,ngay_sinh,gioi_tinh,email,so_dien_thoai,dia_chi from KhachHang where So_Dien_Thoai like ?";
        try {
            con = DBConnect_An.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, "%"+sdtcantim+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                int id;
                String MaKH ;
                String TenKH;
                String NgaySinh;
                boolean GioiTinh;
                String Email;
                String SoDienThoai;
                String DiaChi;
                String GhiChu;
                id= rs.getInt(1);
                MaKH =  rs.getString(2);
                TenKH = rs.getString(3);
                SoDienThoai = rs.getString(7);
                NgaySinh = rs.getString(4);
                Email = rs.getString(6);
                GioiTinh = rs.getBoolean(5);
                DiaChi = rs.getString(8);
                ;
                 Model_Khach_Hang kh = new Model_Khach_Hang(id, MaKH, TenKH, NgaySinh, GioiTinh, Email,SoDienThoai, DiaChi);
                list_KhachHang.add(kh);
            }
            return list_KhachHang;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
     public int updateKhachHang(int id, Model_Khach_Hang kh){
      // cần biết id, đối tượng mới đọc từ form để sửa
       sql = "UPDATE KhachHang SET " +
                 "ma_khach_hang = ?, " +
                 "ho_ten = ?, " +
                 "ngay_sinh = ?, " +
                 "gioi_tinh = ?, " +
                 "email = ?, " +
                 "so_dien_thoai = ?, " +
                 "dia_chi = ?, " 
                 ;
      try {
        con= DBConnect_An.getConnection();
        ps=con.prepareStatement(sql);
        // nếu có ? trong sql thì bắt buộc phải set
        ps.setObject(1, id);
        return ps.executeUpdate();//thêm/sửa/xóa: executeUpdate();
    } catch (Exception e) {// k xóa được
        e.printStackTrace();
        return 0;
    }
   
     }
}
