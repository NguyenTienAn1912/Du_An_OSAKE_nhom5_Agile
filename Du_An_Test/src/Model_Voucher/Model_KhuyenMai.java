/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.sql.Date;

/**
 *
 * @author nguyenduybac
 */
public class Model_KhuyenMai {
    private int id;
    private String ma_voucher;
    private Date ngay_bat_dau;
    private Date ngay_het_han;
    private String ten_giam_gia;
    private double phan_tram_giam_gia;
    private double dieu_kien_ap_dung;
    private int so_lan_su_dung;
    private int trang_thai;
    private String mo_ta;
    private Date ngay_tao;
    private Date ngay_cap_nhat;

    public Model_KhuyenMai(){}

    public Model_KhuyenMai(int id, String ma_voucher, Date ngay_bat_dau, Date ngay_het_han, String ten_giam_gia, double phan_tram_giam_gia, double dieu_kien_ap_dung, int so_lan_su_dung, int trang_thai, String mo_ta, Date ngay_tao, Date ngay_cap_nhat) {
        this.id = id;
        this.ma_voucher = ma_voucher;
        this.ngay_bat_dau = ngay_bat_dau;
        this.ngay_het_han = ngay_het_han;
        this.ten_giam_gia = ten_giam_gia;
        this.phan_tram_giam_gia = phan_tram_giam_gia;
        this.dieu_kien_ap_dung = dieu_kien_ap_dung;
        this.so_lan_su_dung = so_lan_su_dung;
        this.trang_thai = trang_thai;
        this.mo_ta = mo_ta;
        this.ngay_tao = ngay_tao;
        this.ngay_cap_nhat = ngay_cap_nhat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMa_voucher() {
        return ma_voucher;
    }

    public void setMa_voucher(String ma_voucher) {
        this.ma_voucher = ma_voucher;
    }

    public Date getNgay_bat_dau() {
        return ngay_bat_dau;
    }

    public void setNgay_bat_dau(Date ngay_bat_dau) {
        this.ngay_bat_dau = ngay_bat_dau;
    }

    public Date getNgay_het_han() {
        return ngay_het_han;
    }

    public void setNgay_het_han(Date ngay_het_han) {
        this.ngay_het_han = ngay_het_han;
    }

    public String getTen_giam_gia() {
        return ten_giam_gia;
    }

    public void setTen_giam_gia(String ten_giam_gia) {
        this.ten_giam_gia = ten_giam_gia;
    }

    public double getPhan_tram_giam_gia() {
        return phan_tram_giam_gia;
    }

    public void setPhan_tram_giam_gia(double phan_tram_giam_gia) {
        this.phan_tram_giam_gia = phan_tram_giam_gia;
    }

    public double getDieu_kien_ap_dung() {
        return dieu_kien_ap_dung;
    }

    public void setDieu_kien_ap_dung(double dieu_kien_ap_dung) {
        this.dieu_kien_ap_dung = dieu_kien_ap_dung;
    }

    public int getSo_lan_su_dung() {
        return so_lan_su_dung;
    }

    public void setSo_lan_su_dung(int so_lan_su_dung) {
        this.so_lan_su_dung = so_lan_su_dung;
    }

    public int getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(int trang_thai) {
        this.trang_thai = trang_thai;
    }

    public String getMo_ta() {
        return mo_ta;
    }

    public void setMo_ta(String mo_ta) {
        this.mo_ta = mo_ta;
    }

    public Date getNgay_tao() {
        return ngay_tao;
    }

    public void setNgay_tao(Date ngay_tao) {
        this.ngay_tao = ngay_tao;
    }

    public Date getNgay_cap_nhat() {
        return ngay_cap_nhat;
    }

    public void setNgay_cap_nhat(Date ngay_cap_nhat) {
        this.ngay_cap_nhat = ngay_cap_nhat;
    }
    
    
    
}
