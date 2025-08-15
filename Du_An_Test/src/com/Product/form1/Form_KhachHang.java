/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.Product.form1;

import Model.Model_Khach_Hang;
import Repository.Repository_KhachHang;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import main.entity.KhachHang;
import main.repository.ThemThongTinKhachHangRepository;

/**
 *
 * @author ADMIN
 */
public class Form_KhachHang extends javax.swing.JPanel {
    private ThemThongTinKhachHangRepository KHRP = new ThemThongTinKhachHangRepository();
     private DefaultTableModel dtm = new DefaultTableModel();
    /**
     * Creates new form Form_NhanVien
     */
    Repository_KhachHang rp = new Repository_KhachHang();
    private DefaultTableModel mol = new DefaultTableModel();
    private int i = 1;
    public Form_KhachHang() {
         initComponents();
        showDataTTKH(KHRP.getAll());
    }
    
    private void showDataTTKH(ArrayList<KhachHang> list_kh) {
        dtm = (DefaultTableModel) tbl_KhachHang.getModel();
        dtm.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        list_kh.forEach(kh -> dtm.addRow(new Object[]{
            index.getAndIncrement(),
            kh.getMaKhachHang(),
            kh.getHoTen(),
            kh.getNgaySinh(),
            kh.isGioiTinh() ? "Nam" : "Nữ", 
            kh.getEmail(), 
            kh.getSoDienThoai(),
            kh.getDiaChi(),
            
        }
        ));
    }
    
    void ShowData(int index) {
        String MaKH,IDKH;
        String TenKH, SoDT, NgaySinh, Email, DiaChi, GhiChu;
        String GioiTinh;
//        IDKH = tbl_KhachHang.getValueAt(index, 0).toString();
        MaKH = tbl_KhachHang.getValueAt(index, 1).toString();
        TenKH = tbl_KhachHang.getValueAt(index, 2).toString();
        SoDT = tbl_KhachHang.getValueAt(index, 6).toString();
        NgaySinh = tbl_KhachHang.getValueAt(index, 3).toString();
        Email = tbl_KhachHang.getValueAt(index, 5).toString();
        GioiTinh = tbl_KhachHang.getValueAt(index, 4).toString();
        DiaChi = tbl_KhachHang.getValueAt(index, 7).toString();
        
//        GhiChu = tbl_KhachHang.getValueAt(index, 8).toString();

        txt_Ma.setText(MaKH);
        txt_HoTen.setText(TenKH);
        txt_NgaySinh.setText(NgaySinh);
        txt_SDT.setText(SoDT);
        txt_Email.setText(Email);
        txt_DiaChiKH.setText(DiaChi);
        if (GioiTinh.equalsIgnoreCase("nam")) {
            rdo_Nam.setSelected(true);
        } else {
            rdo_Nu.setSelected(true);
        }
       
//        txt_GhiChu.setText(GhiChu);
        
    }
    
    

private KhachHang getFormDataKhachHang() {
    // Lấy dữ liệu từ các trường
    String hoTen = txt_HoTen.getText().trim();
    String diaChi = txt_DiaChiKH.getText().trim();
    String soDienThoai = txt_SDT.getText().trim();
    String email = txt_Email.getText().trim();
    String ngaySinhStr = txt_NgaySinh.getText().trim();

    // Kiểm tra xem tất cả các trường có bị bỏ trống không
    if (hoTen.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Họ tên không được bỏ trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return null;
    }

    if (diaChi.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Địa chỉ không được bỏ trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return null;
    }

    if (soDienThoai.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Số điện thoại không được bỏ trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return null;
    } else if (!soDienThoai.matches("\\d{10}")) {
        JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ. Vui lòng nhập lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return null;
    }

    if (email.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Email không được bỏ trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return null;
    }

    if (ngaySinhStr.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Ngày sinh không được bỏ trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        txt_NgaySinh.requestFocus();
        return null;
    }

    // Validate email
    String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    Pattern pattern = Pattern.compile(emailPattern);
    Matcher matcher = pattern.matcher(email);
    if (!matcher.matches()) {
        JOptionPane.showMessageDialog(this, "Email không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return null;
    }

    // Kiểm tra và chuyển đổi ngày sinh
    LocalDate ngaySinh;
    try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ngaySinh = LocalDate.parse(ngaySinhStr, formatter);
    } catch (DateTimeParseException e) {
        JOptionPane.showMessageDialog(this, "Ngày sinh không đúng định dạng (yyyy-MM-dd).", "Lỗi", JOptionPane.ERROR_MESSAGE);
        txt_NgaySinh.requestFocus();
        return null;
    }

    // Kiểm tra tuổi >= 18
    LocalDate now = LocalDate.now();
    Period tuoi = Period.between(ngaySinh, now);
    if (tuoi.getYears() < 18) {
        JOptionPane.showMessageDialog(this, "KHÔNG BÁN HÀNG CHO TRẺ EM DƯỚI 18 TUỔI!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return null;
    }

    // Chuyển giới tính từ radio button
    Boolean gioiTinh = rdo_Nam.isSelected(); // true cho "Nam", false cho "Nữ"

    // Tạo đối tượng KhachHang
    KhachHang kh = KhachHang.builder()
            .hoTen(hoTen)
            .diaChi(diaChi)
            .soDienThoai(soDienThoai)
            .email(email)
            .ngaySinh(ngaySinh.toString()) // Chuyển LocalDate sang String (ISO-8601)
            .gioiTinh(gioiTinh)
            .build();

    return kh;
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rdo_Nu = new javax.swing.JRadioButton();
        txt_HoTen = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        rdo_Nam = new javax.swing.JRadioButton();
        txt_NgaySinh = new javax.swing.JTextField();
        txt_SDT = new javax.swing.JTextField();
        txt_Email = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_KhachHang = new View.swing.Table();
        btn_Them = new javax.swing.JButton();
        btn_Sua = new javax.swing.JButton();
        btn_TimKiem = new javax.swing.JButton();
        btn_Reset = new javax.swing.JButton();
        txt_DiaChiKH = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_timSDT = new javax.swing.JTextField();
        txt_Ma = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("KHÁCH HÀNG");

        group_GioiTinh.add(rdo_Nu);
        rdo_Nu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        rdo_Nu.setForeground(new java.awt.Color(127, 127, 127));
        rdo_Nu.setText("NỮ");
        rdo_Nu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_NuActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(127, 127, 127));
        jLabel3.setText("Mã KH:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(127, 127, 127));
        jLabel4.setText("SĐT: ");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(127, 127, 127));
        jLabel7.setText("Ngày sinh:");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(127, 127, 127));
        jLabel18.setText("Giới Tính:");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(127, 127, 127));
        jLabel17.setText("Địa Chỉ:");

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

        txt_NgaySinh.setBackground(new java.awt.Color(127, 127, 127));

        txt_SDT.setBackground(new java.awt.Color(127, 127, 127));

        txt_Email.setBackground(new java.awt.Color(127, 127, 127));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(127, 127, 127));
        jLabel19.setText("Email:");

        tbl_KhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã KH", "Họ và Tên", "Ngày Sinh", "Giới Tính", "Email", "SĐT ", "Địa Chỉ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_KhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_KhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_KhachHang);

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

        btn_TimKiem.setBackground(new java.awt.Color(102, 102, 255));
        btn_TimKiem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_TimKiem.setForeground(new java.awt.Color(255, 255, 255));
        btn_TimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/Timkiem.png"))); // NOI18N
        btn_TimKiem.setText("TÌM KIẾM");
        btn_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemActionPerformed(evt);
            }
        });

        btn_Reset.setBackground(new java.awt.Color(102, 102, 255));
        btn_Reset.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_Reset.setForeground(new java.awt.Color(255, 255, 255));
        btn_Reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/RESET.png"))); // NOI18N
        btn_Reset.setText("LÀM MỚI");
        btn_Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ResetActionPerformed(evt);
            }
        });

        txt_DiaChiKH.setBackground(new java.awt.Color(127, 127, 127));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(127, 127, 127));
        jLabel8.setText("TÌM SĐT:");

        txt_timSDT.setBackground(new java.awt.Color(127, 127, 127));
        txt_timSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timSDTActionPerformed(evt);
            }
        });

        txt_Ma.setBackground(new java.awt.Color(127, 127, 127));
        txt_Ma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_MaActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(127, 127, 127));
        jLabel9.setText("Họ và Tên");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addComponent(jLabel4))
                                    .addComponent(jLabel9))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txt_NgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                                            .addComponent(txt_SDT)
                                            .addComponent(txt_Ma)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel19))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_DiaChiKH, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(35, 35, 35)
                                .addComponent(rdo_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addComponent(rdo_Nu, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_Them)
                                .addGap(55, 55, 55)
                                .addComponent(btn_Sua)
                                .addGap(58, 58, 58)
                                .addComponent(btn_Reset)
                                .addGap(509, 509, 509))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_timSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_TimKiem)))))
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(467, 467, 467)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1038, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel17)
                    .addComponent(txt_DiaChiKH, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Ma, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_NgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(rdo_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdo_Nu, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txt_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_TimKiem)
                        .addComponent(txt_timSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Them)
                    .addComponent(btn_Sua)
                    .addComponent(btn_Reset))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rdo_NamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_NamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_NamActionPerformed

    private void rdo_NuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_NuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_NuActionPerformed

    private void btn_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemActionPerformed
        // TODO add your handling code here:
        String sdtcantim = txt_timSDT.getText();
        if (sdtcantim.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập SĐT");
            txt_timSDT.requestFocus();
            
        } else {
            if (rp.timkiem(sdtcantim).isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không Tìm Thấy");
               
            } else {
                JOptionPane.showMessageDialog(this, "Xong!");
                showDataTTKH(KHRP.timSDT(sdtcantim));
            }
        }

    }//GEN-LAST:event_btn_TimKiemActionPerformed

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
        KhachHang kh = getFormDataKhachHang();
        List<KhachHang> existingKhachHangList = KHRP.getAll(); // Fetch all existing records
        KhachHang response = new KhachHang();
        i=tbl_KhachHang.getSelectedRow();// chọn dòng cần sửa
        if(i<0){
            JOptionPane.showMessageDialog(this, "bạn chưa chọn");
        }
        else{
            if (kh.getHoTen() == null || kh.getHoTen().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Họ tên không được để trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
            }
            if (kh.getSoDienThoai() == null || kh.getSoDienThoai().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (kh.getEmail() == null || kh.getEmail().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Email không được để trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(kh !=null){// dl mới sửa ok
                int chon=JOptionPane.showConfirmDialog(this, "bạn chắc chắn sửa khách hàng không ?");
                if(chon==0){
                    int id =Integer.parseInt(tbl_KhachHang.getValueAt(i, 0).toString());
                    KHRP.updateKhachHang(id, this.getFormDataKhachHang());
                    JOptionPane.showMessageDialog(this, "sửa thanh cong");
                    showDataTTKH(KHRP.getAll());
                    
                }else{
                    JOptionPane.showMessageDialog(this, "Bạn không sửa khách hàng.", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
                
            }
       
           
        
        }
    }//GEN-LAST:event_btn_SuaActionPerformed

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
         //TODO add your handling code here:
        KhachHang kh = getFormDataKhachHang();

        // Step 2: Perform validation
        List<KhachHang> existingKhachHangList = KHRP.getAll(); // Fetch all existing records

        // Check for empty fields
        if (kh.getHoTen() == null || kh.getHoTen().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Họ tên không được để trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (kh.getSoDienThoai() == null || kh.getSoDienThoai().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (kh.getEmail() == null || kh.getEmail().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email không được để trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check for duplicate email or phone number
        boolean emailExists = false;
        boolean phoneExists = false;

        for (KhachHang existingKh : existingKhachHangList) {
            if (existingKh.getEmail().equalsIgnoreCase(kh.getEmail())) {
                emailExists = true;
            }
            if (existingKh.getSoDienThoai().equals(kh.getSoDienThoai())) {
                phoneExists = true;
            }
        }

        if (emailExists) {
            JOptionPane.showMessageDialog(this, "Email đã tồn tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (phoneExists) {
            JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Step 3: If validation passes, add the new KhachHang
        boolean result = KHRP.add(kh);
        if (result) {
            // Success message
            JOptionPane.showMessageDialog(this, "Khách hàng đã được thêm thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            // Step 4: Update the displayed data
            showDataTTKH(KHRP.getAll());
        } else {
            // Error message
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm khách hàng.", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btn_ThemActionPerformed

    private void txt_timSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timSDTActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_txt_timSDTActionPerformed

    private void tbl_KhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_KhachHangMouseClicked
        // TODO add your handling code here:
        i = tbl_KhachHang.getSelectedRow();
        ShowData(i);
    }//GEN-LAST:event_tbl_KhachHangMouseClicked

    private void txt_MaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_MaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_MaActionPerformed

    private void btn_ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ResetActionPerformed
        // TODO add your handling code here:
        txt_HoTen.setText(null);
        txt_SDT.setText(null);
        txt_DiaChiKH.setText(null);
        txt_Email.setText(null);
        
        txt_Ma.setText(null);
        txt_NgaySinh.setText(null);
        rdo_Nam.setSelected(true);
        showDataTTKH(KHRP.getAll());
    }//GEN-LAST:event_btn_ResetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Reset;
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_TimKiem;
    private javax.swing.ButtonGroup group_GioiTinh;
    private javax.swing.ButtonGroup group_VaiTro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdo_Nam;
    private javax.swing.JRadioButton rdo_Nu;
    private View.swing.Table tbl_KhachHang;
    private javax.swing.JTextField txt_DiaChiKH;
    private javax.swing.JTextField txt_Email;
    private javax.swing.JTextField txt_HoTen;
    private javax.swing.JTextField txt_Ma;
    private javax.swing.JTextField txt_NgaySinh;
    private javax.swing.JTextField txt_SDT;
    private javax.swing.JTextField txt_timSDT;
    // End of variables declaration//GEN-END:variables
}
