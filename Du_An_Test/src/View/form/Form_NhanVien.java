/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.form;

import Model_Nhan_Vien.Model_Nhan_Vien;
import java.text.SimpleDateFormat;
import Repository.Repository_Nhan_Vien;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class Form_NhanVien extends javax.swing.JPanel {

    /**
     * Creates new form Form_NhanVien
     */
    private  Repository_Nhan_Vien rp = new Repository_Nhan_Vien();
    private DefaultTableModel mol = new DefaultTableModel();
    private int i = 1;

    public Form_NhanVien() throws ParseException {
        initComponents();
        this.fillTable(rp.getAll());
        if (tbl_NhanVien.getRowCount() > 0) {
            this.ShowData(i);
        }
    }

    void fillTable(ArrayList<Model_Nhan_Vien> list) {
        mol = (DefaultTableModel) tbl_NhanVien.getModel();
        mol.setRowCount(0);
        for (Model_Nhan_Vien x : list) {
            mol.addRow(x.toDataRow());
        }
    }
    Model_Nhan_Vien ReadForm() {
    String manv, ten, email, sdt, TaiKhoan, MatKhau;
    boolean gioiTinh, VaiTro,Tinhtrang;
    String dateBD =  new SimpleDateFormat("yyyy-MM-dd").format(jNgaySinh.getDate());
            java.sql.Date NgaySinh = java.sql.Date.valueOf(dateBD);

    // Lấy giá trị từ các ô nhập liệu
    manv = txt_MaNV.getText();    
    
    ten = txt_HoTen.getText();

    email = txt_Email.getText();
    
    sdt = txt_SDT.getText();
//    TaiKhoan = txt_NgaySinh.getText();
   // MatKhau = txt_MatKhau.getText(); // Thêm lấy mật khẩu

    // Kiểm tra tên
    if (ten.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Tên không được để trống!");
        txt_HoTen.requestFocus();
        return null;
    }

    // Kiểm tra email
    if (email.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Email không được để trống!");
        txt_Email.requestFocus();
        return null;
    }

    // Kiểm tra số điện thoại
    if (sdt.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống!");
        txt_SDT.requestFocus();
        return null;
    }

 
//    if (MatKhau.isEmpty()) {
//        JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống!");
//        txt_MatKhau.requestFocus();
//        return null;
//    }

    // Kiểm tra ngày sinh
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    sdf.setLenient(false);
    try {
         // Thử parse ngày sinh
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ! Định dạng ngày phải là yyyy-MM-dd.");
        jNgaySinh.requestFocus();
        return null;
    }

    // Kiểm tra giới tính
    gioiTinh = rdo_Nam.isSelected(); // true nếu Nam, false nếu Nữ

    // Kiểm tra vai trò
    VaiTro = rdo_QuanLy.isSelected(); // true nếu Quản Lý, false nếu Nhân Viên
    
    // kiểm tra tình trạng
    Tinhtrang = rdo_Hoatdong.isSelected();
    // Trả về đối tượng Model_Nhan_Vien
        Model_Nhan_Vien nv = new Model_Nhan_Vien();
    if(rp.CheckSDT_NV(sdt.trim())){
        JOptionPane.showMessageDialog(null, "Số điện thoại đã tồn tại!");
        return null;
    }
    if(rp.CheckEMail(email.trim())){
        JOptionPane.showMessageDialog(null, "Email đã tồn tại!");
        return null;
    }
    nv.setMaNV(manv);
    nv.setTenNV(ten);
   // nv.setMatKhau(MatKhau);
    nv.setEmail(email);
    nv.setSdt(sdt);
    nv.setNgaySinh(NgaySinh);
    nv.setGioiTinh(gioiTinh);
    nv.setVaiTro(VaiTro);
    nv.setTinhTrang(Tinhtrang);
    return  nv;
}
    Model_Nhan_Vien ReadForm1() {
    String manv, ten, email, sdt, TaiKhoan, MatKhau;
    boolean gioiTinh, VaiTro,Tinhtrang;
    String dateBD =  new SimpleDateFormat("yyyy-MM-dd").format(jNgaySinh.getDate());
            java.sql.Date NgaySinh = java.sql.Date.valueOf(dateBD);

    // Lấy giá trị từ các ô nhập liệu
    manv = txt_MaNV.getText();    
    
    ten = txt_HoTen.getText();

    email = txt_Email.getText();
    
    sdt = txt_SDT.getText();
//    TaiKhoan = txt_NgaySinh.getText();
   // MatKhau = txt_MatKhau.getText(); // Thêm lấy mật khẩu

    // Kiểm tra tên
    if (ten.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Tên không được để trống!");
        txt_HoTen.requestFocus();
        return null;
    }

    // Kiểm tra email
    if (email.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Email không được để trống!");
        txt_Email.requestFocus();
        return null;
    }

    // Kiểm tra số điện thoại
    if (sdt.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống!");
        txt_SDT.requestFocus();
        return null;
    }

 
//    if (MatKhau.isEmpty()) {
//        JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống!");
//        txt_MatKhau.requestFocus();
//        return null;
//    }

    // Kiểm tra ngày sinh
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    sdf.setLenient(false);
    try {
         // Thử parse ngày sinh
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ! Định dạng ngày phải là yyyy-MM-dd.");
        jNgaySinh.requestFocus();
        return null;
    }

    // Kiểm tra giới tính
    gioiTinh = rdo_Nam.isSelected(); // true nếu Nam, false nếu Nữ

    // Kiểm tra vai trò
    VaiTro = rdo_QuanLy.isSelected(); // true nếu Quản Lý, false nếu Nhân Viên
    
    // kiểm tra tình trạng
    Tinhtrang = rdo_Hoatdong.isSelected();
    // Trả về đối tượng Model_Nhan_Vien
        Model_Nhan_Vien nv = new Model_Nhan_Vien();
//    if(rp.CheckSDT_NV(sdt.trim())){
//        JOptionPane.showMessageDialog(null, "Số điện thoại đã tồn tại!");
//        return null;
//    }
//    if(rp.CheckEMail(email.trim())){
//        JOptionPane.showMessageDialog(null, "Email đã tồn tại!");
//        return null;
//    }
    nv.setMaNV(manv);
    nv.setTenNV(ten);
   // nv.setMatKhau(MatKhau);
    nv.setEmail(email);
    nv.setSdt(sdt);
    nv.setNgaySinh(NgaySinh);
    nv.setGioiTinh(gioiTinh);
    nv.setVaiTro(VaiTro);
    nv.setTinhTrang(Tinhtrang);
    return  nv;
}

    void ShowData(int index) throws ParseException {
        String MaNV;
        String TenNV, SoDT , Email;
        String GioiTinh, VaiTro,MatKhau,TinhTrang,NgaySinh;
        
        
        
        MaNV = tbl_NhanVien.getValueAt(index, 1).toString();
        TenNV = tbl_NhanVien.getValueAt(index, 2).toString();
         GioiTinh = tbl_NhanVien.getValueAt(index, 3).toString();
        SoDT = tbl_NhanVien.getValueAt(index, 4).toString();
        NgaySinh = tbl_NhanVien.getValueAt(index, 5).toString();
        Email = tbl_NhanVien.getValueAt(index, 7).toString();
        MatKhau = tbl_NhanVien.getValueAt(index,6).toString();
        VaiTro = tbl_NhanVien.getValueAt(index, 8).toString();
//        TinhTrang= tbl_NhanVien.getValueAt(index, 9).toString();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        Date NgaySinh1 = dateFormat.parse(NgaySinh);
        
        txt_MaNV.setText(MaNV);
        txt_HoTen.setText(TenNV);
        jNgaySinh.setDate(NgaySinh1);
        txt_SDT.setText(SoDT);
        txt_Email.setText(Email);
//        txt_MatKhau.setText(MatKhau);

        if (GioiTinh.equalsIgnoreCase("nam")) {
            rdo_Nam.setSelected(true);
        } else {
            rdo_Nu.setSelected(true);
        }

        if (VaiTro.equalsIgnoreCase("Quản Lý")) {
            rdo_QuanLy .setSelected(true);
        } else {
            rdo_NhanVien.setSelected(false);
        
        }
//        if(TinhTrang.equalsIgnoreCase("Nghỉ")){
//            rdo_Nghi.setSelected(false);
//        }else{
//            rdo_Hoatdong.setSelected(true);
//            
//        }
        

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        group_GioiTinh = new javax.swing.ButtonGroup();
        group_VaiTro = new javax.swing.ButtonGroup();
        Group_trangthai = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rdo_Nu = new javax.swing.JRadioButton();
        txt_HoTen = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        rdo_Nam = new javax.swing.JRadioButton();
        txt_MaNV = new javax.swing.JTextField();
        txt_SDT = new javax.swing.JTextField();
        txt_Email = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        rdo_Nghi = new javax.swing.JRadioButton();
        rdo_NhanVien = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_NhanVien = new View.swing.Table();
        btn_Them = new javax.swing.JButton();
        btn_Sua = new javax.swing.JButton();
        btn_Xoa = new javax.swing.JButton();
        btn_Moi = new javax.swing.JButton();
        rdo_Hoatdong = new javax.swing.JRadioButton();
        rdo_QuanLy = new javax.swing.JRadioButton();
        jNgaySinh = new com.toedter.calendar.JDateChooser();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Nhân Viên");

        group_GioiTinh.add(rdo_Nu);
        rdo_Nu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        rdo_Nu.setForeground(new java.awt.Color(127, 127, 127));
        rdo_Nu.setText("NỮ");
        rdo_Nu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_NuActionPerformed(evt);
            }
        });

        txt_HoTen.setBackground(new java.awt.Color(127, 127, 127));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(127, 127, 127));
        jLabel3.setText("Họ và tên:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(127, 127, 127));
        jLabel4.setText("SĐT: ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(127, 127, 127));
        jLabel5.setText("Mã NV:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(127, 127, 127));
        jLabel6.setText("Vai Trò");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(127, 127, 127));
        jLabel7.setText("Ngày Sinh");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(127, 127, 127));
        jLabel18.setText("Giới Tính:");

        rdo_Nam.setBackground(new java.awt.Color(255, 255, 255));
        group_GioiTinh.add(rdo_Nam);
        rdo_Nam.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        rdo_Nam.setForeground(new java.awt.Color(127, 127, 127));
        rdo_Nam.setText("NAM");
        rdo_Nam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_NamActionPerformed(evt);
            }
        });

        txt_MaNV.setBackground(new java.awt.Color(127, 127, 127));
        txt_MaNV.setEnabled(false);
        txt_MaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_MaNVActionPerformed(evt);
            }
        });

        txt_SDT.setBackground(new java.awt.Color(127, 127, 127));
        txt_SDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SDTActionPerformed(evt);
            }
        });

        txt_Email.setBackground(new java.awt.Color(127, 127, 127));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(127, 127, 127));
        jLabel19.setText("Email:");

        rdo_Nghi.setBackground(new java.awt.Color(255, 255, 255));
        Group_trangthai.add(rdo_Nghi);
        rdo_Nghi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        rdo_Nghi.setForeground(new java.awt.Color(127, 127, 127));
        rdo_Nghi.setText("Nghỉ");
        rdo_Nghi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_NghiActionPerformed(evt);
            }
        });

        rdo_NhanVien.setBackground(new java.awt.Color(255, 255, 255));
        group_VaiTro.add(rdo_NhanVien);
        rdo_NhanVien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        rdo_NhanVien.setForeground(new java.awt.Color(127, 127, 127));
        rdo_NhanVien.setText("NHÂN VIÊN");

        tbl_NhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã NV", "Họ và Tên", "Giới Tính ", "SĐT", "Ngày Sinh", "Mail", "Vai Trò", "Tình Trạng"
            }
        ));
        tbl_NhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_NhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_NhanVien);

        btn_Them.setBackground(new java.awt.Color(102, 102, 255));
        btn_Them.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_Them.setForeground(new java.awt.Color(255, 255, 255));
        btn_Them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/ADD.png"))); // NOI18N
        btn_Them.setText("THÊM");
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });

        btn_Sua.setBackground(new java.awt.Color(102, 102, 255));
        btn_Sua.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_Sua.setForeground(new java.awt.Color(255, 255, 255));
        btn_Sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/SAVE.png"))); // NOI18N
        btn_Sua.setText("SỬA");
        btn_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaActionPerformed(evt);
            }
        });

        btn_Xoa.setBackground(new java.awt.Color(102, 102, 255));
        btn_Xoa.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_Xoa.setForeground(new java.awt.Color(255, 255, 255));
        btn_Xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/DELETE.png"))); // NOI18N
        btn_Xoa.setText("XÓA");
        btn_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaActionPerformed(evt);
            }
        });

        btn_Moi.setBackground(new java.awt.Color(102, 102, 255));
        btn_Moi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_Moi.setForeground(new java.awt.Color(255, 255, 255));
        btn_Moi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/RESET.png"))); // NOI18N
        btn_Moi.setText("LÀM MỚI");
        btn_Moi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MoiActionPerformed(evt);
            }
        });

        rdo_Hoatdong.setBackground(new java.awt.Color(255, 255, 255));
        Group_trangthai.add(rdo_Hoatdong);
        rdo_Hoatdong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        rdo_Hoatdong.setForeground(new java.awt.Color(127, 127, 127));
        rdo_Hoatdong.setText("Hoạt động");
        rdo_Hoatdong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_HoatdongActionPerformed(evt);
            }
        });

        rdo_QuanLy.setBackground(new java.awt.Color(255, 255, 255));
        group_VaiTro.add(rdo_QuanLy);
        rdo_QuanLy.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        rdo_QuanLy.setForeground(new java.awt.Color(127, 127, 127));
        rdo_QuanLy.setText("Quản lý");
        rdo_QuanLy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_QuanLyActionPerformed(evt);
            }
        });

        jNgaySinh.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_Them)
                                .addGap(55, 55, 55)
                                .addComponent(btn_Sua)
                                .addGap(64, 64, 64)
                                .addComponent(btn_Xoa)
                                .addGap(58, 58, 58)
                                .addComponent(btn_Moi)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel18))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(rdo_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(rdo_Nu, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txt_SDT, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                                                    .addComponent(jNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(67, 67, 67)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(26, 26, 26)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(rdo_QuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(69, 69, 69)
                                                .addComponent(rdo_NhanVien))
                                            .addComponent(txt_MaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel19)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(358, 358, 358))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(467, 467, 467)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(97, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(353, 353, 353)
                        .addComponent(rdo_Hoatdong)
                        .addGap(67, 67, 67)
                        .addComponent(rdo_Nghi, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 966, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(72, 72, 72)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txt_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txt_MaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19)
                            .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel6)
                    .addComponent(rdo_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdo_Nu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rdo_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdo_QuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdo_Hoatdong, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdo_Nghi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Them)
                    .addComponent(btn_Sua)
                    .addComponent(btn_Xoa)
                    .addComponent(btn_Moi))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rdo_NghiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_NghiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_NghiActionPerformed

    private void rdo_NamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_NamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_NamActionPerformed

    private void rdo_NuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_NuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_NuActionPerformed

    private void txt_MaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_MaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_MaNVActionPerformed

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
  //       TODO add your handling code here:
        if (ReadForm() != null) {
            int chon = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không?");
            if (chon == 0) {
                rp.Them(ReadForm());
                JOptionPane
                        .showMessageDialog(this, "Thêm thành công!");
                fillTable(rp.getAll());
                try {
                    ShowData(1);
                } catch (ParseException ex) {
                    Logger.getLogger(Form_NhanVien.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không thêm!");
            }
        }

        
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void txt_SDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SDTActionPerformed

    private void rdo_HoatdongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_HoatdongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_HoatdongActionPerformed

    private void rdo_QuanLyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_QuanLyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_QuanLyActionPerformed

    private void tbl_NhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_NhanVienMouseClicked
        // TODO add your handling code here:
         int i = tbl_NhanVien.getSelectedRow();
          
        try {
            ShowData(i);
        } catch (ParseException ex) {
            Logger.getLogger(Form_NhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tbl_NhanVienMouseClicked

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
        // TODO add your handling code here:
      i=tbl_NhanVien.getSelectedRow();// chọn dòng cần sửa
        if(i<0){
            JOptionPane.showMessageDialog(this, "bạn chưa chọn");
        }
        else{
            if(this.ReadForm1()!=null){// dl mới sửa ok
                int chon=JOptionPane.showConfirmDialog(this, "bạn chắc chắn sửa?");
                if(chon==0){
                    int id=Integer.parseInt(tbl_NhanVien.getValueAt(i, 0).toString());
                    rp.updateNhanVien(id, this.ReadForm1());
                    JOptionPane.showMessageDialog(this, "sửa thanh cong");
                    this.fillTable(rp.getAll());
                }
            }
        }
    }//GEN-LAST:event_btn_SuaActionPerformed

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
        // TODO add your handling code here:
               i=tbl_NhanVien.getSelectedRow();
      if(i<0){
          JOptionPane.showMessageDialog(this, "bạn chưa chọn dòng cần xóa");
      }
      else{// đã chọn dòng để xóa
          int chon=JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn xóa ?");
          if(chon==0){
              int id=Integer.parseInt(tbl_NhanVien.getValueAt(i, 0).toString());
              rp.XoaNhanVien(id);// xóa trong data
              JOptionPane.showMessageDialog(this, "Xóa Thành công!");
              this.fillTable(rp.getAll());// xóa trong bảng
          }
          else{
              JOptionPane.showMessageDialog(this, "Hủy!");
          }
    }    
    }//GEN-LAST:event_btn_XoaActionPerformed

    private void btn_MoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MoiActionPerformed
        // TODO add your handling code here:
        txt_HoTen.setText(null);
        txt_SDT.setText(null);
//        txt_MatKhau.setText(null);
        txt_Email.setText(null);
        jNgaySinh.setDate(null);
        txt_MaNV.setText(null);
        jNgaySinh.setDate(null);
        rdo_Nam.setSelected(true);
        rdo_Nghi.setSelected(true);
        rdo_NhanVien.setSelected(true);
    }//GEN-LAST:event_btn_MoiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Group_trangthai;
    private javax.swing.JButton btn_Moi;
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.ButtonGroup group_GioiTinh;
    private javax.swing.ButtonGroup group_VaiTro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private com.toedter.calendar.JDateChooser jNgaySinh;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdo_Hoatdong;
    private javax.swing.JRadioButton rdo_Nam;
    private javax.swing.JRadioButton rdo_Nghi;
    private javax.swing.JRadioButton rdo_NhanVien;
    private javax.swing.JRadioButton rdo_Nu;
    private javax.swing.JRadioButton rdo_QuanLy;
    private View.swing.Table tbl_NhanVien;
    private javax.swing.JTextField txt_Email;
    private javax.swing.JTextField txt_HoTen;
    private javax.swing.JTextField txt_MaNV;
    private javax.swing.JTextField txt_SDT;
    // End of variables declaration//GEN-END:variables
}
