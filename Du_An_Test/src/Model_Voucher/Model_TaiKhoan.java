package Model_Voucher;

/**
 
 */
public class Model_TaiKhoan {
    private String taiKhoan;
    private String matKhau;
    private String ma_nhanVien;
    private String Email;
    private boolean Vaitro;
    
    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getma_nhanVien() {
        return ma_nhanVien;
    }

    public String getEmail() {
        return Email;
    }

    public void setma_nhanVien(String ma_nhanVien) {
        this.ma_nhanVien = ma_nhanVien;
    }

    public boolean isVaitro() {
        return Vaitro;
    }

    public void setVaitro(boolean Vaitro) {
        this.Vaitro = Vaitro;
    }

    public Model_TaiKhoan(String taiKhoan, String matKhau, String id_nhanVien) {
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.ma_nhanVien = id_nhanVien;
    }

    public Model_TaiKhoan(String matKhau, String Email , boolean Vaitro) {
        this.matKhau = matKhau;
        this.Email = Email;
        this.Vaitro = Vaitro;
    }

    public Model_TaiKhoan(String matKhau, String ma_nhanVien, String Email, boolean Vaitro) {
        this.matKhau = matKhau;
        this.ma_nhanVien = ma_nhanVien;
        this.Email = Email;
        this.Vaitro = Vaitro;
    }
    
    
    
}
