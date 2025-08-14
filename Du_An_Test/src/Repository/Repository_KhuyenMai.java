/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;


import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;

/**
 *
 * @author nguyenduybac
 */
public class Repository_KhuyenMai {
    private Connection conn;

    public Repository_KhuyenMai() {
        conn = DBConnect.DBConnect_An.getConnection();
    }

    public ArrayList<Model.Model_KhuyenMai> getdata() throws SQLException {
        ArrayList<Model.Model_KhuyenMai> lst = new ArrayList<>();
        String query = "select *from Voucher";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(query);

        while (rs.next()) {
            Model.Model_KhuyenMai km = new Model.Model_KhuyenMai();
            km.setId(rs.getInt(1));
            km.setMa_voucher(rs.getString(2));
            km.setNgay_bat_dau(rs.getDate(3));
            km.setNgay_het_han(rs.getDate(4));
            km.setTen_giam_gia(rs.getString(5));
            km.setPhan_tram_giam_gia(rs.getDouble(6));
            km.setDieu_kien_ap_dung(rs.getDouble(7));
            km.setSo_lan_su_dung(rs.getInt(8));
            km.setTrang_thai(rs.getInt(9));
            km.setMo_ta(rs.getString(10));
            km.setNgay_tao(rs.getDate(11));
            km.setNgay_cap_nhat(rs.getDate(12));
            lst.add(km);
        }
        return lst;
    }
    
    public int update(Model.Model_KhuyenMai km) throws SQLException{
        String query = "UPDATE Voucher SET ma_voucher = ?, ngay_bat_dau = ?, ngay_het_han = ?,ten_giam_gia = ?,phan_tram_giam_gia = ?,gia_tri_don_hang_toi_thieu = ?, so_lan_su_dung_toi_da = ?,trang_thai = ?,mo_ta = ?,ngay_cap_nhat = CURRENT_TIMESTAMP WHERE id = ?;";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, km.getMa_voucher());
        ps.setDate(2, (Date) km.getNgay_bat_dau());
        ps.setDate(3,(Date)km.getNgay_het_han());
        ps.setString(4,km.getTen_giam_gia());
        ps.setDouble(5, km.getPhan_tram_giam_gia());
        ps.setDouble(6,km.getDieu_kien_ap_dung());
        ps.setInt(7, km.getSo_lan_su_dung());
        ps.setInt(8, km.getTrang_thai());
        ps.setString(9, km.getMo_ta());
        ps.setInt(10, km.getId());
        
        int result = ps.executeUpdate();
        return result;
    }
    
    public int insert(Model.Model_KhuyenMai km ) throws SQLException{
        String query = "INSERT INTO Voucher (ma_voucher,ngay_bat_dau,ngay_het_han,ten_giam_gia, phan_tram_giam_gia, gia_tri_don_hang_toi_thieu, so_lan_su_dung_toi_da, mo_ta ) VALUES ( ?,?,?,?,?,?,?,? );";
        
        
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, km.getMa_voucher());
        ps.setDate(2, (Date) km.getNgay_bat_dau());
        ps.setDate(3,(Date)km.getNgay_het_han());
        ps.setString(4,km.getTen_giam_gia());
        ps.setDouble(5, km.getPhan_tram_giam_gia());
        ps.setDouble(6,km.getDieu_kien_ap_dung());
        ps.setInt(7, km.getSo_lan_su_dung());
        ps.setString(8, km.getMo_ta());
        int result = ps.executeUpdate();
        return result;
    }
    
    
    
    public boolean check(String code) throws SQLException{
        String checkQuery = "SELECT COUNT(*) FROM Voucher where ma_voucher = ?;";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(checkQuery);
        if(rs.next()&& rs.getInt(1)>0){
            return true;
        }
        return false;
    }
    
     public ArrayList<Model.Model_KhuyenMai> timKiem(String maVoucher) throws SQLException {
        ArrayList<Model.Model_KhuyenMai> lst = new ArrayList<>();
        String query = "select * from Voucher where ma_voucher like ?;";
        try ( PreparedStatement ps = conn.prepareCall(query)){
            ps.setObject(1, "%" +maVoucher + "%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            Model.Model_KhuyenMai km = new Model.Model_KhuyenMai();
            km.setId(rs.getInt(1));
            km.setMa_voucher(rs.getString(2));
            km.setNgay_bat_dau(rs.getDate(3));
            km.setNgay_het_han(rs.getDate(4));
            km.setTen_giam_gia(rs.getString(5));
            km.setPhan_tram_giam_gia(rs.getDouble(6));
            km.setDieu_kien_ap_dung(rs.getDouble(7));
            km.setSo_lan_su_dung(rs.getInt(8));
            km.setTrang_thai(rs.getInt(9));
            km.setMo_ta(rs.getString(10));
            km.setNgay_tao(rs.getDate(11));
            km.setNgay_cap_nhat(rs.getDate(12));
            lst.add(km);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }
     
    public int delete(String maVoucher) throws SQLException {
        String query = "DELETE FROM GRADE WHERE MaSV = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, maVoucher);
        int result = ps.executeUpdate();
        return result;
    }
     
     
}
