/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

/**
 *
 * @author nguyenduybac
 */
public class Repository_Thongke {
    private Connection conn;

    public Repository_Thongke() {
        conn = DBConnect.DBConnect_An.getConnection();
    }
    
    public ArrayList<Model.Model_Thongke> getdata() throws SQLException {
        ArrayList<Model.Model_Thongke> lst = new ArrayList<>();
        String query = "SELECT\n" +
"    MONTH(hd.ngay_tao) AS Thang,\n" +
"    YEAR(hd.ngay_tao) AS Nam,\n" +
"    SUM(hdc.so_luong) AS Tong_San_Pham,\n" +
"    SUM(hd.tong_tien) AS Tong_Doanh_Thu_Truoc_Giam,\n" +
"    SUM(hd.tong_tien - hd.tong_tien_sau_khi_giam) AS Tong_Giam_Gia,\n" +
"    SUM(hd.tong_tien_sau_khi_giam) AS Tong_Doanh_Thu_Sau_Giam\n" +
"FROM\n" +
"    HoaDon hd\n" +
"JOIN\n" +
"    HoaDonChiTiet hdc ON hd.id = hdc.id_hoa_don\n" +
"GROUP BY\n" +
"    MONTH(hd.ngay_tao), YEAR(hd.ngay_tao)\n" +
"ORDER BY\n" +
"    Nam, Thang;";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(query);

        while (rs.next()) {
            Model.Model_Thongke tk = new Model.Model_Thongke();
            tk.setThang(rs.getInt(1));
            tk.setNam(rs.getInt(2));
            tk.setTongSP(rs.getInt(3));
            tk.setTongDTTGG(rs.getDouble(4));
            tk.setTongGG(rs.getDouble(5));
            tk.setTongDTSGG(rs.getDouble(6));
            lst.add(tk);
        }
        return lst;
    }
    
    public ArrayList<Model.Model_Thongke> timKiem(Date ngayBatDau , Date ngayKetThuc) throws SQLException {
        ArrayList<Model.Model_Thongke> lst = new ArrayList<>();
       String query = "SELECT\n" +
"    SUM(hdc.so_luong) AS Tong_San_Pham,\n" +
"    SUM(hd.tong_tien - hd.tong_tien_sau_khi_giam) AS Tong_Giam_Gia,\n" +
"    SUM(hd.tong_tien) AS Tong_Doanh_Thu_Truoc_Giam,\n" +
"    SUM(hd.tong_tien_sau_khi_giam) AS Tong_Doanh_Thu_Sau_Giam\n" +
"FROM\n" +
"    HoaDon hd\n" +
"WHERE\n" +
"    hd.ngay_tao BETWEEN ? AND ?;";
        try ( PreparedStatement ps = conn.prepareCall(query)){
            ps.setDate(1,ngayBatDau );
            ps.setDate(2,ngayKetThuc);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            Model.Model_Thongke tk = new Model.Model_Thongke();
            
            tk.setTongSP(rs.getInt(1));
            tk.setTongDTTGG(rs.getDouble(2));
            tk.setTongGG(rs.getDouble(3));
            tk.setTongDTSGG(rs.getDouble(4));
            lst.add(tk);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }
    
    
}
