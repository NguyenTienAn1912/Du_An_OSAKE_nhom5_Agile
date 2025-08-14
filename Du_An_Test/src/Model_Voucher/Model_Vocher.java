/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model_Voucher;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Model_Vocher {
    private int id;
    private String maVC;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private String tenGiamGia;
    private int PhanTramGiam;
    private int DieuKienApDung;
    private int SoLanSuDung;
    private boolean TrangThai;
    private String MoTa;
    private Date ngayTao;
    private Date NgayCapNhat;

    public Model_Vocher() {
    }

    public Model_Vocher(String maVC, Date ngayBatDau, Date ngayKetThuc, String tenGiamGia, int PhanTramGiam, int DieuKienApDung, int SoLanSuDung, boolean TrangThai, String MoTa, Date ngayTao, Date NgayCapNhat) {
        this.maVC = maVC;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.tenGiamGia = tenGiamGia;
        this.PhanTramGiam = PhanTramGiam;
        this.DieuKienApDung = DieuKienApDung;
        this.SoLanSuDung = SoLanSuDung;
        this.TrangThai = TrangThai;
        this.MoTa = MoTa;
        this.ngayTao = ngayTao;
        this.NgayCapNhat = NgayCapNhat;
    }

    public Model_Vocher(int id, String maVC, Date ngayBatDau, Date ngayKetThuc, String tenGiamGia, int PhanTramGiam, int DieuKienApDung, int SoLanSuDung, boolean TrangThai, String MoTa, Date ngayTao, Date NgayCapNhat) {
        this.id = id;
        this.maVC = maVC;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.tenGiamGia = tenGiamGia;
        this.PhanTramGiam = PhanTramGiam;
        this.DieuKienApDung = DieuKienApDung;
        this.SoLanSuDung = SoLanSuDung;
        this.TrangThai = TrangThai;
        this.MoTa = MoTa;
        this.ngayTao = ngayTao;
        this.NgayCapNhat = NgayCapNhat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaVC() {
        return maVC;
    }

    public void setMaVC(String maVC) {
        this.maVC = maVC;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getTenGiamGia() {
        return tenGiamGia;
    }

    public void setTenGiamGia(String tenGiamGia) {
        this.tenGiamGia = tenGiamGia;
    }

    public int getPhanTramGiam() {
        return PhanTramGiam;
    }

    public void setPhanTramGiam(int PhanTramGiam) {
        this.PhanTramGiam = PhanTramGiam;
    }

    public int getDieuKienApDung() {
        return DieuKienApDung;
    }

    public void setDieuKienApDung(int DieuKienApDung) {
        this.DieuKienApDung = DieuKienApDung;
    }

    public int getSoLanSuDung() {
        return SoLanSuDung;
    }

    public void setSoLanSuDung(int SoLanSuDung) {
        this.SoLanSuDung = SoLanSuDung;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayCapNhat() {
        return NgayCapNhat;
    }

    public void setNgayCapNhat(Date NgayCapNhat) {
        this.NgayCapNhat = NgayCapNhat;
    }
    
    public Object[] toDataRow(){
    return new Object[]{this.getId(),this.getMaVC(),this.getMoTa(),this.getTenGiamGia(),this.getPhanTramGiam(),this.getSoLanSuDung(),this.getNgayBatDau(),this.getNgayKetThuc(),this.isTrangThai()?"Còn":"Hết",this.getNgayTao(),this.getNgayCapNhat()};
    }
}
