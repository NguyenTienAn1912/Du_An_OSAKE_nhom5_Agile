/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;


import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author nguyenduybac
 */
public class Repository_NhaCC {
    private Connection conn ;
    
    public Repository_NhaCC(){
        conn = DBConnect.DBConnect_An.getConnection();
    }
    
    public ArrayList<Model.Model_NhaCC> getdata() throws SQLException {
        ArrayList<Model.Model_NhaCC> lst = new ArrayList<>();
        String query = "select * from NhaCungCap";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(query);

        while (rs.next()) {
            Model.Model_NhaCC ncc = new Model.Model_NhaCC();
            ncc.setId(rs.getInt(1));
            ncc.setMaNCC(rs.getString(2));
            ncc.setTenNCC(rs.getString(3));
            ncc.setDiaChi(rs.getString(4));
            ncc.setSoDienThoai(rs.getString(5));
            ncc.setTrangThai(rs.getBoolean(6));
            lst.add(ncc);
        }
        return lst;
    }
    
      public int update(Model.Model_NhaCC ncc) throws SQLException{
        String query = "UPDATE NhaCungCap SET ma_nha_cung_cap = ? ,ten_nha_cung_cap = ? ,dia_chi = ? ,so_dien_thoai = ? ,trang_thai = ? where id = ? ;";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, ncc.getMaNCC());
        ps.setString(2, ncc.getTenNCC());
        ps.setString(3, ncc.getDiaChi());
        ps.setString(4, ncc.getSoDienThoai());
        ps.setBoolean(5, ncc.isTrangThai());
        ps.setInt(6, ncc.getId());
        int result = ps.executeUpdate();
        return result;
    }
      
    public int insert(Model.Model_NhaCC ncc ) throws SQLException{
        String query = "INSERT INTO NhaCungCap(ma_nha_cung_cap, ten_nha_cung_cap, dia_chi, so_dien_thoai,trang_thai) VALUES (?,?,?,?,?);";
        
        
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, ncc.getMaNCC());
        ps.setString(2, ncc.getTenNCC());
        ps.setString(3, ncc.getDiaChi());
        ps.setString(4, ncc.getSoDienThoai());
        ps.setBoolean(5, ncc.isTrangThai());
        int result = ps.executeUpdate();
        return result;
    }  
    
    public ArrayList<Model.Model_NhaCC> timKiem(String maNCC) throws SQLException {
        ArrayList<Model.Model_NhaCC> lst = new ArrayList<>();
        String query = "select * from NhaCungCap where ma_nha_cung_cap like ?;";
        try ( PreparedStatement ps = conn.prepareCall(query)){
            ps.setObject(1, "%" +maNCC + "%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            Model.Model_NhaCC ncc = new Model.Model_NhaCC();
            ncc.setId(rs.getInt(1));
            ncc.setMaNCC(rs.getString(2));
            ncc.setTenNCC(rs.getString(3));
            ncc.setDiaChi(rs.getString(4));
            ncc.setSoDienThoai(rs.getString(5));
            ncc.setTrangThai(rs.getBoolean(6));
            lst.add(ncc);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }
    
    public int delete(String maNCC ) throws SQLException {
        String query = "DELETE  FROM NhaCungCap WHERE id = ? ;";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, maNCC);
        int result = ps.executeUpdate();
        return result;
    }
}
