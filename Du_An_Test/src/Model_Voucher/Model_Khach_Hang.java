/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.util.Date;
/**
 *
 * @author Hùng
 */


public class Model_Khach_Hang {
  private int id;
  private String MaKH;
  private String TenKH;
  private String NgaySinh;
  private boolean GioiTinh;
  private String Email;
  private String SDT;
  private String DiaChi;
  

public Model_Khach_Hang(){
}

    public Model_Khach_Hang(String MaKH, String TenKH, String NgaySinh, boolean GioiTinh, String Email, String SDT, String DiaChi) {
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.Email = Email;
        this.SDT = SDT;
        this.DiaChi = DiaChi;
      
    }

    public Model_Khach_Hang(int id, String MaKH, String TenKH, String NgaySinh, boolean GioiTinh, String Email, String SDT, String DiaChi) {
        this.id = id;
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.Email = Email;
        this.SDT = SDT;
        this.DiaChi = DiaChi;
        
    }

    public Model_Khach_Hang(String TenKH, String NgaySinh, boolean GioiTinh, String Email, String SDT, String DiaChi) {
        this.TenKH = TenKH;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.Email = Email;
        this.SDT = SDT;
        this.DiaChi = DiaChi;
        
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public boolean isGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    

    public Object[] toDataRow(){
    return new Object[]{this.getId(),this.getMaKH(),this.getTenKH(),this.getNgaySinh(),this.isGioiTinh()?"Nam":"Nữ",this.getEmail(),this.getSDT(),this.getDiaChi()
    };
 }
}
