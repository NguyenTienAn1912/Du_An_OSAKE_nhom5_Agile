/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author nguyenduybac
 */
public class Model_Thongke {
    private int thang;
    private int nam;
    private int tongSP;
    private double tongDTTGG;
    private double tongGG;
    private double tongDTSGG;
    
    public Model_Thongke(){}
     
    public Model_Thongke(int thang, int nam, int tongSP, double tongDTTGG, double tongGG, double tongDTSGG) {
        this.thang = thang;
        this.nam = nam;
        this.tongSP = tongSP;
        this.tongDTTGG = tongDTTGG;
        this.tongGG = tongGG;
        this.tongDTSGG = tongDTSGG;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public int getTongSP() {
        return tongSP;
    }

    public void setTongSP(int tongSP) {
        this.tongSP = tongSP;
    }

    public double getTongDTTGG() {
        return tongDTTGG;
    }

    public void setTongDTTGG(double tongDTTGG) {
        this.tongDTTGG = tongDTTGG;
    }

    public double getTongGG() {
        return tongGG;
    }

    public void setTongGG(double tongGG) {
        this.tongGG = tongGG;
    }

    public double getTongDTSGG() {
        return tongDTSGG;
    }

    public void setTongDTSGG(double tongDTSGG) {
        this.tongDTSGG = tongDTSGG;
    }
    
    
}
