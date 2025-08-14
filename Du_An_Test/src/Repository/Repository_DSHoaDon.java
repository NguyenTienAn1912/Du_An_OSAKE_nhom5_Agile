/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;
import DBConnect.DBConnect_An;
import Model_HoaDon.Model_DSHoaDon;
import java.sql.*;
import java.util.ArrayList;
import lombok.Builder;
import main.entity.HoaDon;
import main.response.HoaDonResponse;
import main.response.HoaDonResponse1;
/**
 *
 * @author ADMIN
 */
public class Repository_DSHoaDon {
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;
    
    public ArrayList<HoaDonResponse> getAll() {
        String sql = "SELECT \n"
                + "    dbo.HoaDon.id, \n"
                + "    dbo.HoaDon.ma_hoa_don, \n"
                + "    dbo.HoaDon.ngay_tao, \n"
                + "    dbo.HoaDon.ngay_cap_nhat, \n"
                + "    dbo.HoaDon.tong_tien, \n" // Đổi tên cột để lấy tổng tiền gốc
                + "    dbo.HoaDon.tong_tien_sau_khi_giam, \n" // Thêm cột tổng tiền sau khi giảm
                + "    dbo.NhanVien.ma_nhan_vien, \n"
                + "    dbo.KhachHang.ho_ten, \n"
                + "    dbo.KhachHang.dia_chi, \n"
                + "    dbo.KhachHang.so_dien_thoai, \n"
                + "    dbo.HoaDon.trang_thai, \n"
                
                + "FROM \n"
                + "    dbo.HoaDon \n"
                + "INNER JOIN \n"
                + "    dbo.NhanVien \n"
                + "    ON dbo.HoaDon.id_nhan_vien = dbo.NhanVien.id \n"
                + "INNER JOIN \n"
                + "    dbo.KhachHang \n"
                + "    ON dbo.HoaDon.id_khach_hang = dbo.KhachHang.id\n"
                + "ORDER BY dbo.HoaDon.ngay_tao DESC";

        try (Connection con = DBConnect_An.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            ArrayList<HoaDonResponse> lists = new ArrayList<>();
            while (rs.next()) {
                double tongTien = rs.getDouble(5);
                Double tongTienSauKhiGiam = rs.getObject(6) != null ? rs.getDouble(6) : null;

                // Nếu tổng tiền sau khi giảm là null, sử dụng tổng tiền gốc
                if (tongTienSauKhiGiam == null) {
                    tongTienSauKhiGiam = tongTien;
                }

                HoaDonResponse response
                        = HoaDonResponse.builder()
                                .id(rs.getInt(1))
                                .maHoaDon(rs.getString(2))
                                .ngayTao(rs.getString(3))
                                .ngayCapNhap(rs.getString(4))
                                .tongTien(tongTienSauKhiGiam)
                                .maNhanVien(rs.getString(7))
                                .hoTen(rs.getString(8))
                                .diaChi(rs.getString(9))
                                .SDT(rs.getString(10))
                                .trangThai(rs.getInt(11))
                                .build();
                lists.add(response);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
    public ArrayList<HoaDonResponse1> getAllByStatus() {
        String sql = "SELECT \n"
                + "    hd.id,\n"
                + "    hd.id_khach_hang,\n"
                + "    hd.id_nhan_vien,\n"
                + "    hd.trang_thai,\n"
                + "    hd.tong_tien,\n"
                + "    hd.ma_hoa_don,\n"
                + "    kh.ho_ten,\n"
                + "    nv.ma_nhan_vien,\n"
                + "    nv.ten_nhan_vien,\n"
                + "    hd.ngay_tao,\n"
                + "	kh.ma_khach_hang\n"
                + "FROM HoaDon hd\n"
                + "JOIN KhachHang kh ON hd.id_khach_hang = kh.id\n"
                + "JOIN NhanVien nv ON hd.id_nhan_vien = nv.id\n"
                + "WHERE hd.trang_thai = 0;";
        ArrayList<HoaDonResponse1> lists = new ArrayList<>();
        try (Connection con = DBConnect_An.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonResponse1 response
                        = HoaDonResponse1.builder()
                                .id(rs.getInt(1))
                                .idKH(rs.getInt(2))
                                .idNV(rs.getInt(3))
                                .trangThai(rs.getInt(4))
                                .tongTien(rs.getDouble(5))
                                .maHoaDon(rs.getString(6))
                                .tenKhachHang(rs.getString(7))
                                .maNhanVien(rs.getString(8))
                                .hoTen(rs.getString(9))
                                .ngayTao(rs.getString(10))
                                .maKH(rs.getString(11))
                                .build();
                lists.add(response);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
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
    public boolean huyHoaDon(HoaDonResponse1 hoaDon) {
        int check = 0;

        String sql1 = "UPDATE spct "
                + "SET spct.so_luong_ton = spct.so_luong_ton + quantities.total_quantity "
                + "FROM SanPhamChiTiet spct "
                + "JOIN ( "
                + "    SELECT hdct.id_san_pham_chi_tiet, SUM(hdct.so_luong) as total_quantity "
                + "    FROM HoaDonChiTiet hdct "
                + "    WHERE hdct.id_hoa_don = ? "
                + "    GROUP BY hdct.id_san_pham_chi_tiet "
                + ") AS quantities ON spct.id = quantities.id_san_pham_chi_tiet;";

        String sql2 = "UPDATE HoaDonChiTiet "
                + "SET so_luong = 0 "
                + "WHERE id_hoa_don = ?;";

        String sql3 = "UPDATE HoaDon "
                + "SET trang_thai = 2 "
                + "WHERE id = ?;";

        // Query to get all relevant product IDs for the given HoaDon
        String sqlGetProductIds = "SELECT id_san_pham_chi_tiet "
                + "FROM HoaDonChiTiet "
                + "WHERE id_hoa_don = ?;";

        // The new SQL query with the condition
        String sql4 = "UPDATE SanPhamChiTiet "
                + "SET trang_thai_ban = 1 "
                + "WHERE id = ? AND trang_thai_ban = 0;";

        try (Connection con = DBConnect_An.getConnection()) {
            con.setAutoCommit(false); // Begin transaction

            // Update SanPhamChiTiet quantities
            try (PreparedStatement ps1 = con.prepareStatement(sql1)) {
                ps1.setObject(1, hoaDon.getId());
                check += ps1.executeUpdate();
            }

            // Update HoaDonChiTiet
            try (PreparedStatement ps2 = con.prepareStatement(sql2)) {
                ps2.setObject(1, hoaDon.getId());
                check += ps2.executeUpdate();
            }

            // Update HoaDon
            try (PreparedStatement ps3 = con.prepareStatement(sql3)) {
                ps3.setObject(1, hoaDon.getId());
                check += ps3.executeUpdate();
            }

            // Get all product IDs associated with the HoaDon
            try (PreparedStatement psGetIds = con.prepareStatement(sqlGetProductIds)) {
                psGetIds.setObject(1, hoaDon.getId());
                try (ResultSet rs = psGetIds.executeQuery()) {
                    while (rs.next()) {
                        int productId = rs.getInt("id_san_pham_chi_tiet");

                        // Conditionally update SanPhamChiTiet's trang_thai_ban for each product
                        try (PreparedStatement ps4 = con.prepareStatement(sql4)) {
                            ps4.setInt(1, productId);
                            check += ps4.executeUpdate();
                        }
                    }
                }
            }

            con.commit(); // Commit transaction if all updates succeed
        } catch (Exception e) {
            e.printStackTrace(System.out);
            try (Connection con = DBConnect_An.getConnection()) {
                con.rollback(); // Rollback transaction in case of failure
            } catch (Exception ex) {
                ex.printStackTrace(System.out);
            }
        }

        return check > 0;
    }
    public boolean updateThongTin(HoaDonResponse1 response) {
        int check = 0;

        // SQL statement to update trang_thai, hinh_thuc_thanh_toan, tong_tien and tien_giam
        String sql = "UPDATE HoaDon SET trang_thai = 1, tong_tien = ?, tong_tien_sau_khi_giam = ?, id_voucher=? WHERE id = ?";

        try (Connection con = DBConnect_An.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
             

            // Set tong_tien (total amount after discount)
            ps.setDouble(1, response.getTongTien());

            // Set tong_tien_sau_khi_giam (after discount), check for null
            if (response.getTienGiam() != null) {
                ps.setDouble(2, response.getTienGiam());
            } else {
                ps.setNull(2, java.sql.Types.DOUBLE);
            }

            // Set id_voucher, check for null
            if (response.getId_vouCher() != null) {
                ps.setInt(3, response.getId_vouCher());
            } else {
                ps.setNull(3, java.sql.Types.INTEGER);
            }

            ps.setInt(4, response.getId());

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return check > 0;
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
}
