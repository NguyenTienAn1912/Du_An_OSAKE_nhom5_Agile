/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model_HoaDon;

/**
 *
 * @author ADMIN
 */
public class Model_DSSanPham {
    private int id;
    private String MaSP;
    private String TenSP;
    private String HangSX;
    private int NamSX;
    private int NongDo;
    private String DungTich;
    private String XuatSu;
    private int soLuong;
    private Double GiaBan;

    public Model_DSSanPham() {
    }

    public Model_DSSanPham(String MaSP, String TenSP, String HangSX, int NamSX, int NongDo, String DungTich, String XuatSu, int soLuong, Double GiaBan) {
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.HangSX = HangSX;
        this.NamSX = NamSX;
        this.NongDo = NongDo;
        this.DungTich = DungTich;
        this.XuatSu = XuatSu;
        this.soLuong = soLuong;
        this.GiaBan = GiaBan;
    }

    public Model_DSSanPham(int id, String MaSP, String TenSP, String HangSX, int NamSX, int NongDo, String DungTich, String XuatSu, int soLuong, Double GiaBan) {
        this.id = id;
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.HangSX = HangSX;
        this.NamSX = NamSX;
        this.NongDo = NongDo;
        this.DungTich = DungTich;
        this.XuatSu = XuatSu;
        this.soLuong = soLuong;
        this.GiaBan = GiaBan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public String getHangSX() {
        return HangSX;
    }

    public void setHangSX(String HangSX) {
        this.HangSX = HangSX;
    }

    public int getNamSX() {
        return NamSX;
    }

    public void setNamSX(int NamSX) {
        this.NamSX = NamSX;
    }

    public int getNongDo() {
        return NongDo;
    }

    public void setNongDo(int NongDo) {
        this.NongDo = NongDo;
    }

    public String getDungTich() {
        return DungTich;
    }

    public void setDungTich(String DungTich) {
        this.DungTich = DungTich;
    }

    public String getXuatSu() {
        return XuatSu;
    }

    public void setXuatSu(String XuatSu) {
        this.XuatSu = XuatSu;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Double getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(Double GiaBan) {
        this.GiaBan = GiaBan;
    }
    
    public Object[] toDSSProw(){
    return new Object[]{this.getId(),this.getMaSP(),this.getTenSP(),this.getHangSX(),this.getNamSX(),this.getNongDo(),this.getDungTich(),this.getXuatSu(),this.getSoLuong(),this.getGiaBan()};
}
}
