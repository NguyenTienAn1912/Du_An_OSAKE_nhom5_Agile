/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DBConnect.DBConnect_An;
import Model_SanPham.Model_ChiTietSanPham;
import Model_SanPham.Model_DanhSachSanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Repository_DanhSachSanPham {
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;
    
     public ArrayList<Model_SanPham.Model_DanhSachSanPham> getAll(){
         sql = "select SanPham.id,SanPham.ma_san_pham,SanPham.ten_san_pham,SanPhamChiTiet.so_luong_ton,SanPham.trang_thai,sanpham.ngay_tao from SanPham join SanPhamChiTiet on SanPhamChiTiet.id = SanPham.id";
         ArrayList<Model_SanPham.Model_DanhSachSanPham> list_DSSanPham = new ArrayList<>();
         try{
            con = DBConnect_An.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                int id;
                String MaSP;
                String TenSP;
                int SoLuongCon;
                boolean TrangThai;
                Date ngayNhap;
                
                id = rs.getInt(1);
                MaSP = rs.getString(2);
                TenSP = rs.getString(3);
                SoLuongCon = rs.getInt(4);
                TrangThai = rs.getBoolean(5);
                ngayNhap = rs.getDate(6);
                Model_SanPham.Model_DanhSachSanPham ml = new Model_DanhSachSanPham(id, MaSP, TenSP, SoLuongCon, TrangThai, ngayNhap);
                list_DSSanPham.add(ml);
            }
            return list_DSSanPham;
         }catch(Exception e){
            e.printStackTrace();
            return null;
        }

            
     }
    public int delete(int id_Xoa) {
        sql = "delete from SanPhamChiTiet where id = ?";
        try {// xóa dl ok
            con = DBConnect_An.getConnection();
            ps = con.prepareStatement(sql);
            // có ? thì phải set
            ps.setObject(1, id_Xoa);
            return ps.executeUpdate();// thêm/sửa/xóa executeUpdate()
        } catch (Exception e) {// k xóa được
            e.printStackTrace();
            return 0;
        }
    }
}
