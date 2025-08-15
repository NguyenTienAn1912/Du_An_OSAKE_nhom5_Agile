/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model_HoaDon;

/**
 *
 * @author ADMIN
 */


import java.math.BigDecimal;
import java.util.Date;

public class Model_HoaDonChiTiet {

    private int id; // id
    private int idHoaDon; // id_hoa_don
    private int idSanPhamChiTiet; // id_san_pham_chi_tiet
    private String maHoaDonChiTiet; // ma_hoa_don_chi_tiet
    private int giaBan; // gia_ban
    private int soLuong; // so_luong
    private Date ngayTao; // ngay_tao
    private Date ngayCapNhat; // ngay_cap_nhat

    // Constructor
    
    public Model_HoaDonChiTiet() {
    }

    public Model_HoaDonChiTiet(int id, int idHoaDon, int idSanPhamChiTiet, String maHoaDonChiTiet, int giaBan, int soLuong, Date ngayTao, Date ngayCapNhat) {
        this.id = id;
        this.idHoaDon = idHoaDon;
        this.idSanPhamChiTiet = idSanPhamChiTiet;
        this.maHoaDonChiTiet = maHoaDonChiTiet;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.ngayTao = ngayTao;
        this.ngayCapNhat = ngayCapNhat;
    }

    public Model_HoaDonChiTiet(int idHoaDon, int idSanPhamChiTiet, String maHoaDonChiTiet, int giaBan, int soLuong, Date ngayTao, Date ngayCapNhat) {
        this.idHoaDon = idHoaDon;
        this.idSanPhamChiTiet = idSanPhamChiTiet;
        this.maHoaDonChiTiet = maHoaDonChiTiet;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.ngayTao = ngayTao;
        this.ngayCapNhat = ngayCapNhat;
    }

    public Model_HoaDonChiTiet(int idHoaDon, int idSanPhamChiTiet, String maHoaDonChiTiet, int giaBan, int soLuong) {
        this.idHoaDon = idHoaDon;
        this.idSanPhamChiTiet = idSanPhamChiTiet;
        this.maHoaDonChiTiet = maHoaDonChiTiet;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
    }

    
    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public int getIdSanPhamChiTiet() {
        return idSanPhamChiTiet;
    }

    public void setIdSanPhamChiTiet(int idSanPhamChiTiet) {
        this.idSanPhamChiTiet = idSanPhamChiTiet;
    }

    public String getMaHoaDonChiTiet() {
        return maHoaDonChiTiet;
    }

    public void setMaHoaDonChiTiet(String maHoaDonChiTiet) {
        this.maHoaDonChiTiet = maHoaDonChiTiet;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(Date ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }
    
    
}
