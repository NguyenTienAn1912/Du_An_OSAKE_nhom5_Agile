/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DBConnect.DBConnect_An;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import main.entity.PhieuGiamGia1;

/**
 *
 * @author ADMIN
 */
public class Repository_GiamGia {
    
    public Double getGiaTriDonHangToiThieu(String ma) {
        String sql = "select gia_tri_don_hang_toi_thieu from Voucher where ma_voucher =?";
        Double phanTram = 0.0;

        try (Connection con = DBConnect_An.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, ma);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                phanTram = rs.getDouble("gia_tri_don_hang_toi_thieu");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return phanTram;
    }
    public ArrayList<PhieuGiamGia1> getAllGiaTriTotNhat() {
        String sql = "SELECT id, \n"
                + "       ma_voucher, \n"
                + "       ngay_bat_dau, \n"
                + "       ngay_het_han, \n"
                + "       ten_giam_gia, \n"
                + "       phan_tram_giam_gia, \n"
                + "       gia_tri_don_hang_toi_thieu, \n"
                + "       so_lan_su_dung_toi_da, \n"
                + "       trang_thai, \n"
                + "       mo_ta \n"
                + "FROM Voucher  \n"
                + "WHERE trang_thai = 1 \n"
                + "ORDER BY phan_tram_giam_gia DESC";
        try (Connection con = DBConnect_An.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            // table => ResultSet
            ResultSet rs = ps.executeQuery();
            // Doi vs cac cau SQL 
            // su dung excuteQuery => tra ve 1 bang(resultset)
            ArrayList<PhieuGiamGia1> list = new ArrayList<>();
            while (rs.next()) {
                PhieuGiamGia1 PGG = new PhieuGiamGia1(rs.getInt("id"), rs.getString("ma_voucher"), rs.getDate("ngay_bat_dau"), rs.getDate("ngay_het_han"), rs.getString("ten_giam_gia"), rs.getDouble("phan_tram_giam_gia"), rs.getDouble("gia_tri_don_hang_toi_thieu"), rs.getInt("so_lan_su_dung_toi_da"), rs.getInt("trang_thai"), rs.getString("mo_ta"));
                list.add(PGG);
            }
            return list;
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }
    public Double getGiamNhieuHon(double tongTien) {
        String sql = "SELECT gia_tri_don_hang_toi_thieu FROM Voucher WHERE gia_tri_don_hang_toi_thieu > ?";
        try (Connection con = DBConnect_An.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            // Thiết lập tham số
            ps.setDouble(1, tongTien);

            ResultSet rs = ps.executeQuery();

            // Nếu có kết quả, lấy giá trị
            if (rs.next()) {
                return rs.getDouble("gia_tri_don_hang_toi_thieu");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Trả về null nếu không có kết quả nào
        return null;
    }
    public Integer getIDByMaPhieuGiamGia(String ma) {
        String query = "SELECT id FROM Voucher WHERE ma_voucher = ?";
        try (Connection con = DBConnect_An.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            // Set gia tri cho dau hoi cham 
            ps.setObject(1, ma);
            ResultSet rs = ps.executeQuery(); // Lay ket qua

            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }
    public Double getGiaTriGiam(String ma) {
        String sql = "select phan_tram_giam_gia from Voucher where ma_voucher = ?";
        double phanTram = 0.00;

        try (Connection con = DBConnect_An.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, ma);
            ResultSet rs = ps.executeQuery();

            // Kiểm tra nếu có kết quả từ truy vấn
            if (rs.next()) {
                // Lấy giá trị phần trăm giảm giá từ ResultSet
                phanTram = rs.getDouble("phan_tram_giam_gia");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return phanTram;
    }
}
