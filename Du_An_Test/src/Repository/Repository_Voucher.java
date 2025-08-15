/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DBConnect.DBConnect_An;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Repository_Voucher {
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;
    
    public ArrayList<Model_Voucher.Model_Vocher> getAll(){
        ArrayList<Model_Voucher.Model_Vocher> list = new ArrayList<>();
        sql = "select * from Voucher";
        
        try {
            con = DBConnect_An.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()){
                int id = rs.getInt(1);
                String ma = rs.getString(2);
                String Ten = rs.getString(3);
                String MoTa = rs.getString(4);
                int MucGiam = rs.getInt(5);
                int DieuKien = rs.getInt(6);
                int SoLanSuDung = rs.getInt(7);
                Date NgayBD = rs.getDate(8);
                Date NgayKT = rs.getDate(9);
                boolean TrangThai = rs.getBoolean(10);
                Date NgayTao = rs.getDate(11);
                Date NgayCapNhat = rs.getDate(12);
                
                Model_Voucher.Model_Vocher model = new Model_Voucher.Model_Vocher(id, ma, NgayTao, NgayKT, Ten, MucGiam, DieuKien, SoLanSuDung, TrangThai, MoTa, NgayTao, NgayCapNhat);
                list.add(model);
            }
            return list;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public ArrayList<String> cbo_MaVoucher_FormBH (int TongTien){
        ArrayList<String>   list = new ArrayList<>();
        sql = "select * from Voucher  where gia_tri_don_hang_toi_thieu < ?";
        try{
            con = DBConnect_An.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, TongTien);
            
            while(rs.next()){
                String moTa = rs.getString(1);
                list.add(moTa);
            }
            return list;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public String GetPhanTram(String MaVC) {
    String sql = "SELECT phan_tram_giam_gia FROM Voucher WHERE ma_voucher = ?";
    String phanTramGiam = null;

    try (Connection con = DBConnect_An.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setString(1, MaVC); // Gán giá trị mã voucher vào câu truy vấn
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                phanTramGiam = rs.getString(1); // Lấy giá trị phần trăm giảm
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return phanTramGiam; // Trả về phần trăm giảm (null nếu không tìm thấy)
}
    public ArrayList<String> getAllVoucher() {
        ArrayList<String> voucherList = new ArrayList<>();
        sql = "SELECT ten_giam_gia FROM Voucher WHERE trang_thai = 1";  // Chỉ lấy các voucher còn hiệu lực (trang_thai = 1)

        try {
            con = DBConnect_An.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String discountName = rs.getString("ten_giam_gia");
                voucherList.add(discountName); // Thêm tên giảm giá vào danh sách
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voucherList;
    }
    
    public double getDiscountPercentage(String voucherCode) {
        double discountPercentage = 0.0;
        String sql = "SELECT phan_tram_giam_gia FROM Voucher WHERE ten_giam_gia = ? AND trang_thai = 1"; // Truy vấn với mã voucher và trạng thái hoạt động

        try {
            con = DBConnect_An.getConnection(); // Kết nối đến cơ sở dữ liệu
            ps = con.prepareStatement(sql);
            ps.setString(1, voucherCode); // Gán mã voucher vào câu truy vấn

            rs = ps.executeQuery();

            if (rs.next()) {
                discountPercentage = rs.getDouble("phan_tram_giam_gia"); // Trả về tỷ lệ giảm giá (chuyển đổi thành số thập phân)
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return discountPercentage;
    }
    public double getVoucherMinOrderValue(String voucherCode) {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    double minOrderValue = 0.0;
    
    try {
        // Kết nối với cơ sở dữ liệu
        con = DBConnect_An.getConnection(); // Thay DBConnect bằng lớp kết nối cơ sở dữ liệu của bạn

        // Truy vấn lấy giá trị tối thiểu đơn hàng từ voucher
        String sql = "SELECT gia_tri_don_hang_toi_thieu FROM Voucher WHERE ma_voucher = ?";
        
        // Chuẩn bị truy vấn
        ps = con.prepareStatement(sql);
        ps.setString(1, voucherCode);
        
        // Thực thi truy vấn và lấy kết quả
        rs = ps.executeQuery();
        
        if (rs.next()) {
            minOrderValue = rs.getDouble("gia_tri_don_hang_toi_thieu");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Xử lý ngoại lệ nếu có lỗi kết nối hoặc truy vấn
    } finally {
        try {
            // Đảm bảo đóng các tài nguyên sau khi sử dụng
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    return minOrderValue; // Trả về giá trị tối thiểu đơn hàng
}
//    public String Voucher_TotNhat (int tongTien){
//        String VoucherTotNhat = null;
//        int giamGiaLonNhat = 0;
//        sql = "select ten_giam_gia, ma_voucher,phan_tram_giam_gia from Voucher where gia_tri_don_hang_toi_thieu < ?";
//        
//        try{
//            con = DBConnect.getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setObject(1, tongTien);
//            rs = ps.executeQuery();
//            
//            while(rs.next()){
//                String maVoucher = rs.getString("maVoucher");
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }
}
