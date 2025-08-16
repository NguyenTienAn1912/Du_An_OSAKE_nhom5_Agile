/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Model_ThietLap {
    private int id;
    private String maNV;
    private String tenNV;
    private String tenDangNhap;
    private String matKhau;
    private boolean phanQuyen;
    private boolean trangThai;

    public Model_ThietLap() {
    }

    public Model_ThietLap(String maNV, String tenNV, String tenDangNhap, String matKhau, boolean phanQuyen, boolean trangThai) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.phanQuyen = phanQuyen;
        this.trangThai = trangThai;
    }

    public Model_ThietLap(int id, String maNV, String tenNV, String tenDangNhap, String matKhau, boolean phanQuyen, boolean trangThai) {
        this.id = id;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.phanQuyen = phanQuyen;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public boolean isPhanQuyen() {
        return phanQuyen;
    }

    public void setPhanQuyen(boolean phanQuyen) {
        this.phanQuyen = phanQuyen;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
    public Object[] toDataRow(){
        return new Object[]{this.getId(),this.getMaNV(),this.getTenNV(),this.getTenDangNhap(),
        this.getMatKhau(),this.isPhanQuyen()?"QL":"NV",this.isTrangThai()?"HĐ":"NHĐ"};
    }
}
