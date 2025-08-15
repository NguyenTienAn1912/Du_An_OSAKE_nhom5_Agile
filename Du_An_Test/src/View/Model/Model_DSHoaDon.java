/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model_HoaDon;
//import

/**
 *
 * @author ADMIN
 */
public class Model_DSHoaDon {
    private Integer id;  
    
    private Integer idKH;
    
    private Integer idNV;
    
    private Integer id_vouCher;
    
    private boolean trangThai;
    private String SDT;
    private int tongTien;
   
    private String maHoaDon;
    
    private String tenKhachHang;
    
    private String maNhanVien;

    private String hoTen;
    
    private String ngayTao;
    
    private String maKH;

   private int tienGiam;


    public Model_DSHoaDon() {
    }
//(STT, maHD, MaNV, TenKH, SDT, TongTien, NgayTao, TrangThai);

    public Model_DSHoaDon(Integer id, Integer idKH, Integer idNV, Integer id_vouCher, boolean trangThai, int tongTien, String maHoaDon, String tenKhachHang, String maNhanVien, String hoTen, String ngayTao, String maKH, int tienGiam) {
        this.id = id;
        this.idKH = idKH;
        this.idNV = idNV;
        this.id_vouCher = id_vouCher;
        this.trangThai = trangThai;
        this.tongTien = tongTien;
        this.maHoaDon = maHoaDon;
        this.tenKhachHang = tenKhachHang;
        this.maNhanVien = maNhanVien;
        this.hoTen = hoTen;
        this.ngayTao = ngayTao;
        this.maKH = maKH;
        this.tienGiam = tienGiam;
    }

    public Model_DSHoaDon(Integer idKH, Integer idNV, Integer id_vouCher, boolean trangThai, int tongTien, String maHoaDon, String tenKhachHang, String maNhanVien, String hoTen, String ngayTao, String maKH, int tienGiam) {
        this.idKH = idKH;
        this.idNV = idNV;
        this.id_vouCher = id_vouCher;
        this.trangThai = trangThai;
        this.tongTien = tongTien;
        this.maHoaDon = maHoaDon;
        this.tenKhachHang = tenKhachHang;
        this.maNhanVien = maNhanVien;
        this.hoTen = hoTen;
        this.ngayTao = ngayTao;
        this.maKH = maKH;
        this.tienGiam = tienGiam;
    }

    public Model_DSHoaDon(Integer id, boolean trangThai, String SDT, int tongTien, String maHoaDon, String maNhanVien, String hoTen, String ngayTao) {
        this.id = id;
        this.trangThai = trangThai;
        this.SDT = SDT;
        this.tongTien = tongTien;
        this.maHoaDon = maHoaDon;
        this.maNhanVien = maNhanVien;
        this.hoTen = hoTen;
        this.ngayTao = ngayTao;
    }

    public Model_DSHoaDon(Integer id, boolean trangThai, int tongTien, String maHoaDon, String maNhanVien, String maKH) {
        this.id = id;
        this.trangThai = trangThai;
        this.tongTien = tongTien;
        this.maHoaDon = maHoaDon;
        this.maNhanVien = maNhanVien;
        this.maKH = maKH;
    }

    public String getSDT() {
        return SDT;
    }

//    public Model_DSHoaDon(int id, String ma_HD, String ma_NV, String ma_KH, String SDT, int TongTien, String NgayTao, boolean TrangThai) {
//        this.id = id;
//        this.ma_HD = ma_HD;
//        this.ma_NV = ma_NV;
//        this.ma_KH = ma_KH;
//        this.SDT = SDT;
//        this.TongTien = TongTien;
//        this.NgayTao = NgayTao;
//        this.TrangThai = TrangThai;
//    }
    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdKH() {
        return idKH;
    }

    public void setIdKH(Integer idKH) {
        this.idKH = idKH;
    }

    public Integer getIdNV() {
        return idNV;
    }

    public void setIdNV(Integer idNV) {
        this.idNV = idNV;
    }

    public Integer getId_vouCher() {
        return id_vouCher;
    }

    public void setId_vouCher(Integer id_vouCher) {
        this.id_vouCher = id_vouCher;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public int getTienGiam() {
        return tienGiam;
    }

    public void setTienGiam(int tienGiam) {
        this.tienGiam = tienGiam;
    }

    
    public Object[] toDataHoaDon(){
        return new Object[]{this.getId(),this.getMaHoaDon(),this.getNgayTao(),this.getMaNhanVien(),this.getTongTien(),this.isTrangThai()?"Đã Thanh Toán":"Chờ Thanh Toán"};
    }
    
    public Object[] toDataRow(){
        return new Object[]{this.id,this.getMaHoaDon(),this.getMaNhanVien(),this.getMaKH(),this.getSDT(),this.getTongTien(),this.getTienGiam(),this.getNgayTao(),this.isTrangThai()?"Đã Thanh Toán":"Chưa Thanh Toán"};
    }
}
