/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;
import DBConnect.DBConnect_An;
import Model_HoaDon.Model_DSHoaDon;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import main.entity.SanPham;
import main.response.HoaDonChiTietReponse;
import main.response.HoaDonResponse1;
/**
 *
 * @author ADMIN
 */
public class Repository_HoaDonChiTiet {
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;
    
    public ArrayList<HoaDonChiTietReponse> getAll(Integer hoaDonID) {
        String sql = "SELECT\n" +
"    hd.id,\n" +
"    spct.ma_san_pham_chi_tiet,\n" +
"    sp.ten_san_pham,\n" +
"    hsx.ten_hang_san_xuat,\n" +
"    nsx.nam_san_xuat,\n" +
"    xs.xuat_su,\n" +
"    nd.nong_do,\n" +
"    pl.ten_phan_loai,\n" +
"    dt.Dung_tich,\n" +
"    ncc.ten_nha_cung_cap,\n" +
"    spct.gia_nhap,\n" +
"    spct.gia_ban,\n" +
"    hdct.so_luong,\n" +
"    spct.trang_thai,\n" +
"    hdct.so_luong * hdct.gia_ban AS total_price,\n" +
"    hdct.id_hoa_don,\n" +
"    spct.id AS id_san_pham_chi_tiet\n" +
"FROM\n" +
"    dbo.SanPhamChiTiet spct\n" +
"    INNER JOIN dbo.HoaDonChiTiet hdct ON spct.id = hdct.id_san_pham_chi_tiet\n" +
"    INNER JOIN dbo.HangSanXuat hsx ON spct.id_hang_san_xuat = hsx.id\n" +
"	\n" +
"    INNER JOIN dbo.XuatSu xs ON spct.id_xuat_su = xs.id\n" +
"    INNER JOIN dbo.NongDo nd ON spct.id_nong_do = nd.id\n" +
"    INNER JOIN dbo.NamSanXuat nsx ON spct.id_nam_san_xuat = nsx.id\n" +
"    INNER JOIN dbo.PhanLoai pl ON spct.id_phan_loai = pl.id\n" +
"    INNER JOIN dbo.DungTich dt ON spct.id_dung_tich = dt.id\n" +
"    INNER JOIN dbo.NhaCungCap ncc ON spct.id_nha_cung_cap = ncc.id\n" +
"    INNER JOIN dbo.HoaDon hd ON hdct.id_hoa_don = hd.id\n" +
"    INNER JOIN dbo.SanPham sp ON spct.id_san_pham = sp.id\n" +
"WHERE\n" +
"    hdct.id_hoa_don = ?;";
        ArrayList<HoaDonChiTietReponse> lists = new ArrayList<>();
        try (Connection con = DBConnect_An.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, hoaDonID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new HoaDonChiTietReponse(
                        rs.getInt(1), 
                        rs.getString(2),
                        rs.getString(3), 
                        rs.getString(4),
                        rs.getString(5), 
                        rs.getString(6), 
                        rs.getString(7), 
                        rs.getString(8),
                        rs.getString(9), 
                        rs.getString(10), 
                        rs.getDouble(11), 
                        rs.getDouble(12),
                        rs.getInt(13), 
                        rs.getBoolean(14), 
                        rs.getDouble(15), 
                        rs.getInt(16), 
                        rs.getInt(17)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }
    public boolean add(HoaDonChiTietReponse response) {
        int check = 0;

        String sql = "INSERT INTO HoaDonChiTiet\n"
                + "(id_hoa_don, id_san_pham_chi_tiet, so_luong, gia_ban)\n"
                + "VALUES(?,?,?,?)";
        Connection con = DBConnect_An.getConnection(); 
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            // Object la cha cua cac loai kieu du lieu 
            ps.setObject(1, response.getIdHoaDon());
            ps.setObject(2, response.getIdSpct()); // Nhan vien lay tu login
            ps.setObject(3, response.getSoLuong());
            ps.setObject(4, response.getGiaBan());

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return check > 0;
    }
    public int Update(int id_sua, HoaDonChiTietReponse response){
        String sql = "update HoaDonChiTiet set so_luong = ? where ma_hoa_don_chi_tiet = ?";
        Connection con = DBConnect_An.getConnection(); 
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setObject(1, response.getSoLuong());
            ps.setObject(2, id_sua);
            
            return ps.executeUpdate();        
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return 0;
        }
    }
    public int delete( int id_Xoa){
        sql = "delete from HoaDonChiTiet where id = id_hoa_don\n" +
"delete from HoaDon where id = ?";
        Connection con = DBConnect_An.getConnection(); 
          try {
              PreparedStatement ps = con.prepareStatement(sql);
              ps.setObject(1, id_Xoa);
              return ps.executeUpdate();
          }catch(Exception e){
              e.printStackTrace();
              return 0;
          }
    }
}
