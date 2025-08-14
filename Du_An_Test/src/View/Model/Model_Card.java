/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Model;

import javax.swing.Icon;

/**
 *
 * @author ADMIN
 */
public class Model_Card {
    private Icon icon;
    private String title;
    private String values;
    private String moTa1;
    private String moTa2;
    private String ThoiGian;
    private String TongDoanhThu;
    private String TongSanPham;
    private String TongGiamGia;
    public Model_Card(){}
    public Model_Card(Icon icon, String title, String values, String moTa1, String moTa2, String ThoiGian, String TongDoanhThu, String TongSanPham, String TongGiamGia) {
        this.icon = icon;
        this.title = title;
        this.values = values;
        this.moTa1 = moTa1;
        this.moTa2 = moTa2;
        this.ThoiGian = ThoiGian;
        this.TongDoanhThu = TongDoanhThu;
        this.TongSanPham = TongSanPham;
        this.TongGiamGia = TongGiamGia;
    }

    public String getThoiGian() {
        return ThoiGian;
    }

    public void setThoiGian(String ThoiGian) {
        this.ThoiGian = ThoiGian;
    }

    public String getTongDoanhThu() {
        return TongDoanhThu;
    }

    public void setTongDoanhThu(String TongDoanhThu) {
        this.TongDoanhThu = TongDoanhThu;
    }

    public String getTongSanPham() {
        return TongSanPham;
    }

    public void setTongSanPham(String TongSanPham) {
        this.TongSanPham = TongSanPham;
    }

    public String getTongGiamGia() {
        return TongGiamGia;
    }

    public void setTongGiamGia(String TongGiamGia) {
        this.TongGiamGia = TongGiamGia;
    }
    
    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public String getMoTa1() {
        return moTa1;
    }

    public void setMoTa1(String moTa1) {
        this.moTa1 = moTa1;
    }

    public String getMoTa2() {
        return moTa2;
    }

    public void setMoTa2(String moTa2) {
        this.moTa2 = moTa2;
    }


    

    public Model_Card(Icon icon, String title, String values, String moTa1, String moTa2) {
        this.icon = icon;
        this.title = title;
        this.values = values;
        this.moTa1 = moTa1;
        this.moTa2 = moTa2;
    }

   
    
    
}
