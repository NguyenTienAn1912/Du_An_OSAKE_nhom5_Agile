package Model_Nhan_Vien;

import java.util.Date;

public class Model_Nhan_Vien {
    private int id;
    private String MaNV;   // Mã nhân viên
    private String TenNV;  // Tên nhân viên
    private boolean GioiTinh; // Giới tính: true = Nam, false = Nữ
    private String Sdt;    // Số điện thoại
    private Date NgaySinh;
//    private String MatKhau;
    private String Email;  // Địa chỉ email
    private boolean VaiTro;  // Vai trò: true = Admin, false = User
    private boolean TinhTrang; // Tình trạng: true = Hoạt động, false = Không hoạt động

    public Model_Nhan_Vien() {
    }

    public Model_Nhan_Vien(String MaNV, String TenNV, boolean GioiTinh, String Sdt, Date NgaySinh, String Email, boolean VaiTro, boolean TinhTrang) {
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.GioiTinh = GioiTinh;
        this.Sdt = Sdt;
        this.NgaySinh = NgaySinh;
//        this.MatKhau = MatKhau;
        this.Email = Email;
        this.VaiTro = VaiTro;
        this.TinhTrang = TinhTrang;
    }

    public Model_Nhan_Vien(int id, String MaNV, String TenNV, boolean GioiTinh, String Sdt, Date NgaySinh, String Email, boolean VaiTro, boolean TinhTrang) {
        this.id = id;
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.GioiTinh = GioiTinh;
        this.Sdt = Sdt;
        this.NgaySinh = NgaySinh;
//        this.MatKhau = MatKhau;
        this.Email = Email;
        this.VaiTro = VaiTro;
        this.TinhTrang = TinhTrang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
    }

    public boolean isGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String Sdt) {
        this.Sdt = Sdt;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

//    public String getMatKhau() {
//        return MatKhau;
//    }
//
//    public void setMatKhau(String MatKhau) {
//        this.MatKhau = MatKhau;
//    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public boolean isVaiTro() {
        return VaiTro;
    }

    public void setVaiTro(boolean VaiTro) {
        this.VaiTro = VaiTro;
    }

    public boolean isTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(boolean TinhTrang) {
        this.TinhTrang = TinhTrang;
    }
    public Object[] toDataRow(){
        return new Object[]{this.getId(),this.getMaNV(),this.getTenNV(),this.isGioiTinh()?"Nam":"Nữ",this.getSdt(),this.getNgaySinh(),this.getEmail(),
            this.isVaiTro()?"Quản Lý":"Nhân Viên",this.isTinhTrang()?"Đang Làm":"Đã Nghỉ"};
     }

 

   

    
                
    

 
}

  

