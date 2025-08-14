/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DBConnect.DBConnect_An;
import Model_SanPham.Model_ChiTietSanPham;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import main.entity.SanPham;

/**
 *
 * @author ADMIN
 */
public class Repository_ChiTietSanPham {
     private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;
    
     public ArrayList<Model_SanPham.Model_ChiTietSanPham> getAll(){
         sql = "select \n" +
                    "SanPhamChiTiet.id, \n" +
                    "SanPhamChiTiet.ma_san_pham_chi_tiet,\n" +
                    "SanPham.ten_san_pham,\n" +
                    "PhanLoai.ten_phan_loai,\n" +
                    "XuatSu.xuat_su,\n" +
                    "NamSanXuat.nam_san_xuat,\n" +
                    "HangSanXuat.ten_hang_san_xuat,\n" +
                    "NhaCungCap.ten_nha_cung_cap,\n" +
                    "NongDo.nong_do,\n" +
                    "DungTich.Dung_tich,\n" +
                    "SanPhamChiTiet.so_luong_ton,\n" +
                    "SanPhamChiTiet.gia_nhap,\n" +
                    "SanPhamChiTiet.gia_ban\n" +
                    "\n" +
                    "from SanPhamChiTiet\n" +
                    "join SanPham on SanPham.id = SanPhamChiTiet.id_san_pham\n" +
                    "join PhanLoai on PhanLoai.id = SanPhamChiTiet.id_phan_loai\n" +
                    "join XuatSu on XuatSu.id = SanPhamChiTiet.id_xuat_su\n" +
                    "join NamSanXuat on NamSanXuat.id = SanPhamChiTiet.id_nam_san_xuat\n" +
                    "join HangSanXuat on HangSanXuat.id = SanPhamChiTiet.id_hang_san_xuat\n" +
                    "join NhaCungCap on NhaCungCap.id = SanPhamChiTiet.id_nha_cung_cap\n" +
                    "join NongDo on NongDo.id = SanPhamChiTiet.id_nong_do\n" +
                    "join DungTich on DungTich.id = SanPhamChiTiet.id_dung_tich\n";
         ArrayList<Model_SanPham.Model_ChiTietSanPham> list_CTSanPham = new ArrayList<>();
         try{
            con = DBConnect_An.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                int id;
                String MaSPCT;
                String TenSP;
                String LoaiSP;
                String XuatSu;
                int NamSX;
                String HangSX;
                int NongDo;
                String NCC;
                String DungTich;
                int SoLuong;
                int GiaNhap;
                int GiaBan;
                
                id = rs.getInt(1);
                MaSPCT = rs.getString(2);
                TenSP = rs.getString(3);
                LoaiSP = rs.getString(4);
                XuatSu = rs.getString(5);
                NamSX = rs.getInt(6);
                HangSX = rs.getString(7);
                NCC = rs.getString(8);
                NongDo = rs.getInt(9);
                DungTich = rs.getString(10);
                SoLuong = rs.getInt(11);
                GiaNhap = rs.getInt(12);
                GiaBan = rs.getInt(13);
                
                Model_SanPham.Model_ChiTietSanPham ml = new Model_ChiTietSanPham(id, MaSPCT, TenSP, HangSX, NongDo, HangSX, XuatSu, HangSX, DungTich, HangSX, GiaNhap, GiaBan, SoLuong, true, GiaNhap, GiaBan, id);
                list_CTSanPham.add(ml);
            }
            return list_CTSanPham;
         }catch(Exception e){
            e.printStackTrace();
            return null;
        }

            
     }
//     public int insertSanPhamChiTiet(Model_ChiTietSanPham ml) {
//    String sql = "INSERT INTO SanPhamChiTiet " +
//                 "(id_san_pham, id_hang_san_xuat, id_nong_do, id_nam_san_xuat, id_xuat_su, id_phan_loai, id_dung_tich, id_nha_cung_cap, ma_san_pham_chi_tiet, gia_nhap, gia_ban, so_luong_ton) " +
//                 "VALUES (" +
//                 "    (SELECT id FROM SanPham WHERE ma_san_pham = ?), " +
//                 "    (SELECT id FROM HangSanXuat WHERE ten_hang_san_xuat = ?), " +
//                 "    (SELECT id FROM NongDo WHERE nong_do = ?), " +
//                 "    (SELECT id FROM NamSanXuat WHERE nam_san_xuat = ?), " +
//                 "    (SELECT id FROM XuatSu WHERE xuat_su = ?), " +
//                 "    (SELECT id FROM PhanLoai WHERE ten_phan_loai = ?), " +
//                 "    (SELECT id FROM DungTich WHERE dung_tich = ?), " +
//                 "    (SELECT id FROM NhaCungCap WHERE ten_nha_cung_cap = ?), " +
//                 "    ?, ?, ?, ?" +
//                 ")";
//    try {
//        // Kết nối cơ sở dữ liệu
//        con = DBConnect_An.getConnection();
//        ps = con.prepareStatement(sql);
//
//        // Gán giá trị cho các tham số
//        
//        ps.setObject(2, ml.getHangSX());
//        ps.setObject(3, ml.getNongDo());
//        ps.setObject(4, ml.getNamSX());
//        ps.setObject(5, ml.getXuatSu());
//        ps.setObject(6, ml.getLoaiSP());
//        ps.setObject(7, ml.getDungTich());
//        ps.setObject(8, ml.getNCC());
//        ps.setObject(9, ml.getMaSPCT());
//        ps.setObject(10, ml.getGiaNhap());
//        ps.setObject(11, ml.getGiaBan());
//        ps.setObject(12, ml.getSoLuong());
//
//        // Thực thi câu lệnh
//        int rowsInserted = ps.executeUpdate();
//        System.out.println("Số dòng được thêm: " + rowsInserted);
//        return rowsInserted;
//    } catch (Exception e) {
//        e.printStackTrace();
//        return 0; // Trả về 0 nếu có lỗi
//    } finally {
//        try {
//            if (ps != null) ps.close(); // Đóng PreparedStatement
//            if (con != null) con.close(); // Đóng Connection
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}



    public int update(int id_sua, Model_ChiTietSanPham ml) {
    String sql = "update SanPhamChiTiet set so_luong_ton = ?, gia_nhap = ?, gia_ban =? where id = ?";
    try {
        // Kết nối tới cơ sở dữ liệu
        con = DBConnect_An.getConnection();
        ps = con.prepareStatement(sql);

        // Gán giá trị cho các tham số
        ps.setObject(1, ml.getSoLuong());  // Tham số 1: số lượng tồn
        ps.setObject(2, ml.getGiaNhap());  // Tham số 2: giá nhập
        ps.setObject(3, ml.getGiaBan());   // Tham số 3: giá bán
        ps.setObject(4, id_sua);           // Tham số 4: mã sản phẩm chi tiết (điều kiện WHERE)

        // Thực thi câu lệnh
        return ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
        return 0; // Trả về 0 nếu xảy ra lỗi
    } 
}

}
