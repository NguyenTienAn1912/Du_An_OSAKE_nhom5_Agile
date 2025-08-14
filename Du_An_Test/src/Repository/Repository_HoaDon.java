/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;
import DBConnect.DBConnect_An;
import Model_HoaDon.Model_HoaDon1;
import java.sql.*;
import javax.swing.JOptionPane;
import main.entity.HoaDon;
/**
 *
 * @author ADMIN
 */
public class Repository_HoaDon {
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public String taoHoaDon(Model_HoaDon1 hoaDon) {
        String maHoaDonMoi = null;
        try {
            sql = "INSERT INTO HoaDon(ma_hoa_don, ten_nhan_vien, ten_khach_hang, sdt, tong_tien, tong_tien_sau_khi_giam, ghi_chu, trang_thai) " +
                  "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            con = DBConnect_An.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, hoaDon.getMaHoaDon());
            ps.setString(2, hoaDon.getTenNhanVien());
            ps.setString(3, hoaDon.getTenKhachhang());
            ps.setString(4, hoaDon.getSDT());
            ps.setDouble(5, hoaDon.getTongTien());
            ps.setDouble(6, hoaDon.getTongTienSauKhiGiam());  // Tính tổng tiền sau khi giảm giá
            ps.setString(7, hoaDon.getGhiChu());
            ps.setBoolean(8, hoaDon.isTrangThai());
            ps.executeUpdate();

            // Lấy mã hóa đơn mới (nếu có auto increment trong bảng)
            sql = "SELECT ma_hoa_don FROM HoaDon WHERE ma_hoa_don = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, hoaDon.getMaHoaDon());
            rs = ps.executeQuery();
            if (rs.next()) {
                maHoaDonMoi = rs.getString("ma_hoa_don");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maHoaDonMoi;
    }
     public boolean updateTongTien(Double tongTien, Integer id) {
        int check = 0;
        String sql = "UPDATE HoaDon SET tong_tien=? WHERE id=?";
        try (Connection con = DBConnect_An.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            // Log các giá trị để kiểm tra
            System.out.println("Updating HoaDon with id: " + id + " and tong_tien: " + tongTien);

            ps.setObject(1, tongTien);
            ps.setObject(2, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        System.out.println("Update result: " + check); // Kiểm tra kết quả trả về
        return check > 0;
    }
    public boolean add(HoaDon hoaDon) {

        int check = 0;

        String sql = "INSERT INTO HoaDon\n"
                + "   (id_khach_hang, id_nhan_vien, ngay_tao, trang_thai,tong_tien)\n"
                + "    VALUES(?,?,?,?,?)";
        try (Connection con = DBConnect_An.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            // Object la cha cua cac loai kieu du lieu 
            ps.setObject(1, hoaDon.getKhachHangID());
            ps.setObject(2, hoaDon.getNhanVienID()); // Nhan vien lay tu login
            ps.setObject(3, new java.util.Date());
            ps.setObject(4, 0);
            ps.setObject(5, 0);

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return check > 0;
    }
}
