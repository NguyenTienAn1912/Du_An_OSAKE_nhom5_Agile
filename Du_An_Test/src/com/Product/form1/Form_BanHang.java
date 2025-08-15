package com.Product.form1;

import DBConnect.DBConnect_An;
import Jframe.ThemThongTinKhachHangJframe;

import Model_HoaDon.Model_DSHoaDon;
import Model_HoaDon.Model_DSSanPham;
import Model_HoaDon.Model_GioHang;
import Model_HoaDon.Model_HoaDon1;
import Repository.Repository_DSHoaDon;
import Repository.Repository_DSSP;
import Repository.Repository_GiamGia;
import Repository.Repository_HDCT;
import Repository.Repository_HoaDon;
import Repository.Repository_HoaDonChiTiet;
import Repository.Repository_Voucher;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import lombok.Builder;
import main.entity.DungTich;
import main.entity.HangSX;
import main.entity.NongDo;
import main.entity.PhieuGiamGia1;
import main.repository.DungTichRepository;
import main.repository.HangSXRepository;
import main.repository.NongDoRepository;
import main.response.HoaDonChiTietReponse;
import net.miginfocom.layout.AC;
import org.apache.poi.hpsf.Decimal;
import org.jdesktop.swingx.renderer.StringValue;
import java.sql.*;
import java.text.NumberFormat;
import main.response.HoaDonResponse1;
import main.response.SanPhamChiTietRespone;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import main.entity.HoaDon;
import main.entity.KhachHang;
import main.repository.ThemThongTinKhachHangRepository;
import main.response.HoaDonResponse;
import org.w3c.dom.Text;

/**
 *
 * @author ADMIN
 */
public class Form_BanHang extends javax.swing.JPanel {
    
    private Integer id_kh;
    private Integer id_Voucher;
    private Integer indexHoaDonSelected;
    private String MaSPCT ;
    
    Double tongTien;
    Double TongTienSauGiam;
    
    private String maGiamGia;
    private String tenDungTich = "%";
    private String tenNongDo = "%";
    private String tenHangSX = "%";
    private boolean tenGiaBan = false;
    
    
    
    
    private DungTichRepository DungTichRP = new DungTichRepository();
    private NongDoRepository NongDoRP = new NongDoRepository();
    private HangSXRepository HangSXRP = new HangSXRepository();

    private DefaultTableModel model_HoaDon = new DefaultTableModel();
    private DefaultTableModel model_sanPham = new DefaultTableModel();
    private DefaultTableModel model_GioHang = new DefaultTableModel();
    
    private Repository_HoaDonChiTiet rpHDCT = new Repository_HoaDonChiTiet();
    private Repository_DSHoaDon rpHD = new Repository_DSHoaDon();
    private Repository.Repository_DSSP rpSP = new Repository_DSSP();
    private Repository.Repository_HDCT hdctRP = new Repository_HDCT();
    private Repository.Repository_Voucher VCRP = new Repository_Voucher();
    private Repository_HoaDon HoaDonRepo = new Repository_HoaDon();
    private Repository_GiamGia giamGiaRepository = new Repository_GiamGia();
    private ThemThongTinKhachHangRepository ThongTinKHRP = new ThemThongTinKhachHangRepository();

    private int i = -1;

    public Form_BanHang() {
        initComponents();
        fillHoaDon(rpHD.getAllByStatus());
        indexHoaDonSelected = tbl_DShoadon.getSelectedRow();
        fillDSSP(rpSP.getAll());
        
        ShowComboboxLocGia();
        cbb_gia.setSelectedIndex(-1);
        
        ShowComboboxDungTich(DungTichRP.getAll());
        cbb_DungTich.setSelectedIndex(-1);
        
        ShowComboboxLocNongDo(NongDoRP.getAll());
        cbb_NongDo.setSelectedIndex(-1);
        
        ShowComboboxLocHangSX(HangSXRP.getAll());
        cbb_Hang.setSelectedIndex(-1);
        
        

    }
    private void ShowComboboxLocGia(){
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_gia.getModel();
        comboBoxModel.removeAllElements();
        comboBoxModel.addElement("Giảm dần");
        comboBoxModel.addElement("Tăng dần");
    }
    private void ShowComboboxDungTich(ArrayList<main.entity.DungTich> list){
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_DungTich.getModel();
        comboBoxModel.removeAllElements();
        for (DungTich cl : list) {
            comboBoxModel.addElement(cl);
        }
    }
    private void ShowComboboxLocNongDo(ArrayList<main.entity.NongDo> list){
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_NongDo.getModel();
        comboBoxModel.removeAllElements();
        for (NongDo cl : list) {
            comboBoxModel.addElement(cl);
        }
    }
    private void ShowComboboxLocHangSX(ArrayList<main.entity.HangSX> list){
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_Hang.getModel();
        comboBoxModel.removeAllElements();
        for (HangSX cl : list) {
            comboBoxModel.addElement(cl);
        }
    }
    private void ShowComboboxPhieuGiamGia(ArrayList<PhieuGiamGia1> list){
        cbb_PhieuGiamGia.removeAllItems();
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_PhieuGiamGia.getModel();
        for (PhieuGiamGia1 cl : list) {
            comboBoxModel.addElement(cl.getMa_Voucher());
        }
    }
    
    private void ShowTableHoaDonChiTiet(ArrayList<HoaDonChiTietReponse> list  ){
        model_GioHang = (DefaultTableModel) tbl_GioHang.getModel();
        model_GioHang.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        
         // Map để lưu các sản phẩm đã được cộng dồn
         
         Map<String, HoaDonChiTietReponse> SanPhamMap = new HashMap<>();
         
         // Duyệt qua từng sản phẩm trong danh sách và cộng dồn số lượng nếu mã sản phẩm đã tồn tại
         
         for (HoaDonChiTietReponse s : list) {
            String maSPCT = s.getMaSPCT();
            
            if(SanPhamMap.containsKey(maSPCT)){
                // Cộng dồn số lượng và thành tiền nếu sản phẩm đã tồn tại trong Map
                HoaDonChiTietReponse existingSP = SanPhamMap.get(maSPCT);
                existingSP.setSoLuong(existingSP.getSoLuong() + s.getSoLuong());
                existingSP.setThanhTien(existingSP.getThanhTien() + s.getThanhTien());
            }else{
                 // Nếu sản phẩm chưa tồn tại, thêm sản phẩm vào Map
                 SanPhamMap.put(maSPCT, s);
            }
        }
         // Hiển thị các sản phẩm đã được cộng dồn vào bảng
         
         SanPhamMap.values().forEach(s -> model_GioHang.addRow(new Object[]{
             index.getAndIncrement(),
             s.getMaSPCT(),
             s.getTenSP(),
             s.getHangSX(),
             s.getNongdo(),
             s.getDungTich(),
             s.getSoLuong(),
             String.format("%,.3f₫", s.getGiaBan()),
             
             String.format("%,.3f₫", s.getThanhTien()),
         }));
         

 }
    
    
    private void calculateChange(){
        try {
            // Parse tổng tiền và tiền khách đưa, tiền khách CK
            String totalAmountStr = txt_tongTien1.getText().replace("₫", "").trim().replace(",", "").replace(".", "");
            String amountGivenStr = txt_tienKhachDua.getText().trim().replace(",", "").replace(".", "");
            

            // Đảm bảo tổng tiền không bị rỗng
            if (!totalAmountStr.isEmpty()) {
                double totalAmount = Double.parseDouble(totalAmountStr);
                double amountGiven = amountGivenStr.isEmpty() ? 0 : Double.parseDouble(amountGivenStr);
                

                // Tổng tiền khách đưa (tiền mặt + CK)
                double totalGiven = amountGiven ;

                // Chỉ tính toán khi tổng tiền khách đưa lớn hơn hoặc bằng tổng tiền
                if (totalGiven >= totalAmount) {
                    double change = totalGiven - totalAmount;
                    txt_tienThua.setText(String.format("%,.0f₫", change));
                } else {
                    txt_tienThua.setText("0₫");
                }
            }
        } catch (NumberFormatException e) {
            // Xử lý khi nhập liệu không hợp lệ
            txt_tienThua.setText("0₫");
        }
    }

private int countHoaDon(){
        int count = 0;
        String sql = "Select Count(*) FROM HoaDon where trang_thai = 0";
        try {
            Connection con = DBConnect_An.getConnection(); 
            PreparedStatement ps = con.prepareStatement(sql); 
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                count = rs.getInt(1);
            }
        }catch(Exception e){
            e.printStackTrace(System.out);
        }
        return count;
    }

    void fillHoaDon(ArrayList<HoaDonResponse1> lists) {

        model_HoaDon = (DefaultTableModel) tbl_DShoadon.getModel();
        model_HoaDon.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        lists.forEach(s -> model_HoaDon.addRow(new Object[]{
            index.getAndIncrement(),
            s.getMaHoaDon(),
            s.getNgayTao(),
            s.getMaNhanVien(),
            String.format("%,.3f₫", s.getTongTien()),
            s.getTrangThai() == 0 ? "Chưa thanh toán" : "Đã Thanh Toán",
            s.getTienGiam(),s.getId_vouCher()
        }));

    }

    void fillDSSP(ArrayList<SanPhamChiTietRespone> lists) {
        model_sanPham = (DefaultTableModel) tbl_DSSanPham.getModel();
        model_sanPham.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        lists.forEach(s -> model_sanPham.addRow(new Object[]{
            index.getAndIncrement(),
            s.getMaSPCT(),
            s.getTenSP(),
            s.getHangSX(),
            s.getNamSX(),
            s.getNongDo(),
            s.getPhanLoai(),
            s.getDungTich(),
            s.getXuatXu(),
            s.getSoLuong(),
            String.format("%,.3f₫", s.getGiaBan())
        }));
    }
private void updateTotalAmount() {
    double totalAmount = 0.0;

    // Duyệt qua tất cả các dòng trong bảng để tính tổng tiền
    for (int i = 0; i < model_GioHang.getRowCount(); i++) {
        double tongTien = (Double) model_GioHang.getValueAt(i, 8);  // Cột "Total" là cột 8
        totalAmount += tongTien;
    }

    // Hiển thị tổng tiền (hoặc có thể cập nhật một JLabel, JTextField)
    System.out.println("Total Amount: " + totalAmount);
}
     private double showTotalMoney(ArrayList<HoaDonChiTietReponse> lists) {
        tongTien = 0.0;
        for (HoaDonChiTietReponse hdct : lists) {
            tongTien += hdct.getThanhTien();
        }
        return tongTien;
    }
//    
    
 private void clearTableData() {
        DefaultTableModel model = (DefaultTableModel) tbl_GioHang.getModel(); // Lấy mô hình của JTable
        model.setRowCount(0); // Xóa tất cả các hàng, đưa bảng về trạng thái không có dữ liệu
    }
   



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        HoaDon_Panel = new View.swing.PaneBorder();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_DShoadon = new View.swing.Table();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_GioHang = new View.swing.Table();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_DSSanPham = new View.swing.Table();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_TaoHoaDon = new javax.swing.JButton();
        btn_QR = new javax.swing.JButton();
        btn_LamMoi1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btn_timKhachHang = new com.Product.swing.ButtonBadges();
        jLabel17 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txt_tenKhachHang1 = new javax.swing.JTextField();
        txt_maKhachHang = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txt_sdt = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_Huy = new com.Product.swing.ButtonBadges();
        btn_ThanhToan = new com.Product.swing.ButtonBadges();
        jLabel13 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txt_tongTien1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lb_Nofitications = new javax.swing.JLabel();
        txt_maHoaDon = new javax.swing.JTextField();
        txt_maNhanVien = new javax.swing.JTextField();
        txt_ngayTT = new javax.swing.JTextField();
        txt_tenKhachHang = new javax.swing.JTextField();
        txt_tongTien = new javax.swing.JTextField();
        txt_tienKhachDua = new javax.swing.JTextField();
        txt_tienThua = new javax.swing.JTextField();
        cbb_PhieuGiamGia = new javax.swing.JComboBox<>();
        cbb_Hang = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbb_DungTich = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        cbb_NongDo = new javax.swing.JComboBox<>();
        cbb_gia = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();

        HoaDon_Panel.setBackground(new java.awt.Color(255, 255, 255));
        HoaDon_Panel.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("BÁN HÀNG");

        tbl_DShoadon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã HĐ", "Ngày Tạo", "Mã NV", "Tổng Tiền", "Trạng Thái"
            }
        ));
        tbl_DShoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_DShoadonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_DShoadon);
        if (tbl_DShoadon.getColumnModel().getColumnCount() > 0) {
            tbl_DShoadon.getColumnModel().getColumn(0).setPreferredWidth(30);
            tbl_DShoadon.getColumnModel().getColumn(1).setPreferredWidth(30);
        }

        tbl_GioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Hãng SX", "Nồng Độ", "Dung Tích", "Số Lượng", "Giá Bán", "Thành Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_GioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbl_GioHangMouseEntered(evt);
            }
        });
        tbl_GioHang.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                tbl_GioHangInputMethodTextChanged(evt);
            }
        });
        tbl_GioHang.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tbl_GioHangPropertyChange(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_GioHang);

        tbl_DSSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Hãng SX", "Năm SX", "Nồng Độ", "Phân Loại", "Dung Tích", "Xuất Sứ", "Số Lượng", "Giá Bán"
            }
        ));
        tbl_DSSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_DSSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_DSSanPham);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(127, 127, 127));
        jLabel3.setText("Danh Sách Hóa Đơn");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(127, 127, 127));
        jLabel4.setText("Giỏ Hàng");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(127, 127, 127));
        jLabel5.setText("Sản Phẩm");

        btn_TaoHoaDon.setBackground(new java.awt.Color(102, 102, 255));
        btn_TaoHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_TaoHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btn_TaoHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/TaoHoaDon.png"))); // NOI18N
        btn_TaoHoaDon.setText("TẠO HÓA ĐƠN");
        btn_TaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TaoHoaDonActionPerformed(evt);
            }
        });

        btn_QR.setBackground(new java.awt.Color(102, 102, 255));
        btn_QR.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_QR.setForeground(new java.awt.Color(255, 255, 255));
        btn_QR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/QR.png"))); // NOI18N
        btn_QR.setText("QUÉT QR");
        btn_QR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_QRActionPerformed(evt);
            }
        });

        btn_LamMoi1.setBackground(new java.awt.Color(102, 102, 255));
        btn_LamMoi1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_LamMoi1.setForeground(new java.awt.Color(255, 255, 255));
        btn_LamMoi1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/RESET.png"))); // NOI18N
        btn_LamMoi1.setText("LÀM MỚI");
        btn_LamMoi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoi1ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(255, 0, 0));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 0, 255))); // NOI18N

        btn_timKhachHang.setBackground(new java.awt.Color(102, 102, 255));
        btn_timKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        btn_timKhachHang.setText("CHỌN");
        btn_timKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_timKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timKhachHangActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Tên KH");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Mã KH:");

        txt_tenKhachHang1.setBackground(new java.awt.Color(255, 255, 255));
        txt_tenKhachHang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tenKhachHang1ActionPerformed(evt);
            }
        });

        txt_maKhachHang.setBackground(new java.awt.Color(255, 255, 255));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("SĐT:");

        txt_sdt.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_maKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btn_timKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_tenKhachHang1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_tenKhachHang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_maKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_timKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(51, 51, 255))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Mã Hóa Đơn");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("Tổng");

        btn_Huy.setBackground(new java.awt.Color(102, 102, 255));
        btn_Huy.setForeground(new java.awt.Color(255, 255, 255));
        btn_Huy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/Huy.png"))); // NOI18N
        btn_Huy.setText("HỦY");
        btn_Huy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Huy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HuyActionPerformed(evt);
            }
        });

        btn_ThanhToan.setBackground(new java.awt.Color(102, 102, 255));
        btn_ThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btn_ThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/ThanhToan.png"))); // NOI18N
        btn_ThanhToan.setText("THANH TOÁN");
        btn_ThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_ThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThanhToanActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 255));
        jLabel13.setText("Ngày Tạo");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Mã Nhân Viên");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Tên Khách Hàng");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Voucher");
        jLabel9.setToolTipText("");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tiền Khách Đưa:");

        txt_tongTien1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_tongTien1.setForeground(new java.awt.Color(255, 51, 0));
        txt_tongTien1.setText("0 VND");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Tiền thừa");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 51, 51));
        jLabel7.setText("Tổng Tiền");

        lb_Nofitications.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lb_Nofitications.setForeground(new java.awt.Color(255, 0, 0));

        txt_maHoaDon.setBackground(new java.awt.Color(255, 255, 255));

        txt_maNhanVien.setBackground(new java.awt.Color(255, 255, 255));

        txt_ngayTT.setBackground(new java.awt.Color(255, 255, 255));

        txt_tenKhachHang.setBackground(new java.awt.Color(255, 255, 255));

        txt_tongTien.setBackground(new java.awt.Color(255, 255, 255));

        txt_tienKhachDua.setBackground(new java.awt.Color(255, 255, 255));

        txt_tienThua.setBackground(new java.awt.Color(255, 255, 255));

        cbb_PhieuGiamGia.setBackground(new java.awt.Color(255, 255, 255));
        cbb_PhieuGiamGia.setForeground(new java.awt.Color(0, 0, 0));
        cbb_PhieuGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_PhieuGiamGiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(105, 105, 105)
                                .addComponent(txt_tongTien1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lb_Nofitications, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_maHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_ThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Huy, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_ngayTT, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbb_PhieuGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_tenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel19)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_tienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(39, 39, 39)
                                    .addComponent(txt_tienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_tongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_maNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_maHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txt_ngayTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txt_maNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_tenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txt_tongTien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cbb_PhieuGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lb_Nofitications, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txt_tienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txt_tienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tongTien1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Huy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 298, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        cbb_Hang.setBackground(new java.awt.Color(255, 255, 255));
        cbb_Hang.setForeground(new java.awt.Color(0, 0, 0));
        cbb_Hang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_HangItemStateChanged(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(127, 127, 127));
        jLabel8.setText("HÃNG SX");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(127, 127, 127));
        jLabel12.setText("Dung Tích");

        cbb_DungTich.setBackground(new java.awt.Color(255, 255, 255));
        cbb_DungTich.setForeground(new java.awt.Color(0, 0, 0));
        cbb_DungTich.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_DungTichItemStateChanged(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(127, 127, 127));
        jLabel15.setText("Nồng Độ");

        cbb_NongDo.setBackground(new java.awt.Color(255, 255, 255));
        cbb_NongDo.setForeground(new java.awt.Color(0, 0, 0));
        cbb_NongDo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_NongDoItemStateChanged(evt);
            }
        });

        cbb_gia.setBackground(new java.awt.Color(255, 255, 255));
        cbb_gia.setForeground(new java.awt.Color(0, 0, 0));
        cbb_gia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_giaItemStateChanged(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(127, 127, 127));
        jLabel16.setText("Giá");

        javax.swing.GroupLayout HoaDon_PanelLayout = new javax.swing.GroupLayout(HoaDon_Panel);
        HoaDon_Panel.setLayout(HoaDon_PanelLayout);
        HoaDon_PanelLayout.setHorizontalGroup(
            HoaDon_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HoaDon_PanelLayout.createSequentialGroup()
                .addGroup(HoaDon_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HoaDon_PanelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(HoaDon_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(HoaDon_PanelLayout.createSequentialGroup()
                                .addGroup(HoaDon_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(HoaDon_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(HoaDon_PanelLayout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel8)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cbb_Hang, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel12)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cbb_DungTich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel15)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cbb_NongDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel16)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cbb_gia, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel4)
                                        .addComponent(jScrollPane2)
                                        .addComponent(jScrollPane1)
                                        .addComponent(jScrollPane3)
                                        .addGroup(HoaDon_PanelLayout.createSequentialGroup()
                                            .addComponent(btn_TaoHoaDon)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btn_LamMoi1)))
                                    .addComponent(btn_QR, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(HoaDon_PanelLayout.createSequentialGroup()
                        .addGap(491, 491, 491)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        HoaDon_PanelLayout.setVerticalGroup(
            HoaDon_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HoaDon_PanelLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(HoaDon_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HoaDon_PanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(HoaDon_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_TaoHoaDon)
                            .addComponent(btn_LamMoi1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(btn_QR)
                        .addGap(31, 31, 31)
                        .addGroup(HoaDon_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(HoaDon_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(cbb_Hang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))
                            .addGroup(HoaDon_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbb_DungTich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12)
                                .addComponent(cbb_NongDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15)
                                .addComponent(cbb_gia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))
                    .addGroup(HoaDon_PanelLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(HoaDon_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(HoaDon_Panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_QRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_QRActionPerformed
        // TODO add your handling code here:
        // Tạo một Menu để quét mã QR
        Menu menu = new Menu(3);
        
        menu.setVisible(true);

        menu.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                String maSpct = Menu.maSPCTBanHang;

                if (maSpct == null || maSpct.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm sau khi quét mã QR.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ArrayList<SanPhamChiTietRespone> listspct = rpSP.getListSanPhamChiTietByMaSPCT(maSpct);

                if (listspct.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm sau khi quét mã QR.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Chọn sản phẩm đầu tiên từ danh sách (nếu có nhiều sản phẩm, bạn có thể cho người dùng chọn sản phẩm cụ thể)
                SanPhamChiTietRespone spctr = listspct.get(0);

                // Kiểm tra xem hóa đơn đã được chọn chưa
                if (indexHoaDonSelected < 0 || indexHoaDonSelected >= rpHD.getAllByStatus().size()) {
                    JOptionPane.showMessageDialog(null, "Hóa đơn không hợp lệ hoặc không được chọn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return; // Exit if the index is invalid
                }

                // Hỏi người dùng nhập số lượng sản phẩm
                String soLuongStr = JOptionPane.showInputDialog("Số lượng là:", "0");

                // Validate the quantity input
                int soLuong;
                try {
                    soLuong = Integer.parseInt(soLuongStr);
                    if (soLuong <= 0) {
                        JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0.", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (soLuong > spctr.getSoLuong()) {
                        JOptionPane.showMessageDialog(null, "Số lượng nhập mua không được lớn hơn số lượng tồn kho.", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ.", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Get the selected HoaDonResponse1 object
                HoaDonResponse1 response1 = rpHD.getAllByStatus().get(indexHoaDonSelected);

                // Get the list of HoaDonChiTietReponse objects
                List<HoaDonChiTietReponse> hdctList = rpHDCT.getAll(response1.getId());

                // Kiểm tra xem sản phẩm đã tồn tại trong hóa đơn hay chưa
                boolean isExisting = false;
                for (HoaDonChiTietReponse hdct : hdctList) {
                    if (hdct.getIdSpct().equals(spctr.getID())) {
                        // Nếu sản phẩm đã tồn tại, cộng dồn số lượng và cập nhật lại thành tiền
                        int newSoLuong = hdct.getSoLuong() + soLuong;
                        hdct.setSoLuong(newSoLuong);
                        hdct.setThanhTien(newSoLuong * hdct.getGiaBan());
                        isExisting = true;
                        break;
                    }
                }

                // Nếu sản phẩm chưa tồn tại, thêm mới vào hóa đơn
                if (!isExisting) {
                    HoaDonChiTietReponse hoaDonChiTietResponse = HoaDonChiTietReponse.builder()
                            .idHoaDon(response1.getId())
                            .idSpct(spctr.getID())
                            .maSPCT(spctr.getMaSPCT())
                            .TenSP(spctr.getTenSP())
                            .hangSX(spctr.getHangSX())
                            .namSX(spctr.getNamSX())
                            .XuatSU(spctr.getXuatXu())
                            .nongdo(spctr.getNongDo())
                            .PhanLoai(spctr.getPhanLoai())
                            .dungTich(spctr.getDungTich())
                            .nhaCungCap(spctr.getNCC())
                            .giaNhap(spctr.getGiaNhap())
                            .giaBan(spctr.getGiaBan())
                            .soLuong(soLuong)
                            .trangThai(spctr.isTrangThai())
                            .thanhTien(soLuong * spctr.getGiaBan())
                            .build();

                    hdctList.add(hoaDonChiTietResponse);
                    rpHDCT.add(hoaDonChiTietResponse);
                }

                // Update the quantity of the selected product
                spctr.setSoLuong(spctr.getSoLuong() - soLuong);

                if (spctr.getSoLuong() == 0) {
                    rpSP.updateTrangThai(spctr.getID());
                    JOptionPane.showMessageDialog(null, "Sản phẩm đã hết hàng.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }

                // Update the product quantity in the database
                rpSP.updateSoLuong(spctr);

                // Reload tables
                fillDSSP(rpSP.getAll());
                ShowTableHoaDonChiTiet((ArrayList<HoaDonChiTietReponse>) hdctList);

                // Update total amount in the invoice
                double tongTien = showTotalMoney((ArrayList<HoaDonChiTietReponse>) hdctList);
                response1.setTongTien(tongTien);
                rpHD.updateTongTien(response1.getTongTien(), response1.getId());

                // Reload the invoice table
                fillHoaDon(rpHD.getAllByStatus());
            }
        });
    }//GEN-LAST:event_btn_QRActionPerformed

    private void btn_TaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TaoHoaDonActionPerformed
        final int MAX_INVOICES = 10;       
         if(countHoaDon() < MAX_INVOICES){
             // Lấy ID khách hàng đã chọn từ biến tĩnh hoặc mặc định là 1 nếu chưa chọn
             int khachHangID = ThemThongTinKhachHangJframe.id_tamKH != null ? ThemThongTinKhachHangJframe.id_tamKH : 1;
             
             //Tạo Đối tượng hóa đơn
             HoaDon hd = HoaDon.builder()
                     .khachHangID(khachHangID)
                     .nhanVienID(4)
                     .build();
             
             //Thêm Hóa Đơn Vào Cơ sở dữ liệu
             if(rpHD.add(hd)){
                 // Tải lại bảng hóa đơn
                 fillHoaDon(rpHD.getAllByStatus());
             }else{
                 JOptionPane.showMessageDialog(this, "Lỗi khi thêm hóa đơn.", "Thông báo", JOptionPane.ERROR_MESSAGE);
                 
             }
         }else{
             // Hiển thị thông báo lỗi khi đạt giới hạn số lượng hóa đơn
            JOptionPane.showMessageDialog(this, "Đã đạt giới hạn số lượng hóa đơn.", "Thông báo", JOptionPane.WARNING_MESSAGE);
         }
    }//GEN-LAST:event_btn_TaoHoaDonActionPerformed

    private void tbl_DSSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_DSSanPhamMouseClicked
        
        try{
            int row = tbl_DSSanPham.getSelectedRow();
            
            if(row < 0){
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm để tiếp tục.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            SanPhamChiTietRespone spctr = rpSP.getAll().get(row);
            
            if(indexHoaDonSelected < 0 || indexHoaDonSelected >= rpHD.getAllByStatus().size()){
                JOptionPane.showMessageDialog(null, "Hóa đơn không hợp lệ hoặc không được chọn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return; // Exit if the index is invalid
            }
            
            HoaDonResponse1 response1 = rpHD.getAllByStatus().get(indexHoaDonSelected);
            String soLuongStr = JOptionPane.showInputDialog("Nhập số lượng : " , "0");
            
            int soLuong ;
            try {
                soLuong = Integer.parseInt(soLuongStr);
                if (soLuong <= 0) {
                    JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0.", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (soLuong > spctr.getSoLuong()) {
                    JOptionPane.showMessageDialog(null, "Số lượng nhập mua không được lớn hơn số lượng tồn kho.", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ.", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            HoaDonChiTietReponse hoaDonChiTietResponse = HoaDonChiTietReponse.builder()
                    .idHoaDon(response1.getId())
                    .idSpct(spctr.getID())
                    .maSPCT(spctr.getMaSPCT())
                    .dungTich(spctr.getDungTich())
                    .XuatSU(spctr.getXuatXu())
                    .hangSX(spctr.getHangSX())
                    .namSX(spctr.getNamSX())
                    .nongdo(spctr.getNongDo())
                    .giaBan(spctr.getGiaBan())
                    .soLuong(soLuong)
                    .thanhTien(soLuong * spctr.getGiaBan())
                    .build();
            
            List<HoaDonChiTietReponse> hdctList = rpHDCT.getAll(response1.getId());
            
            boolean found = false;
            for (HoaDonChiTietReponse item : hdctList) {
                if(item.getIdSpct().equals(spctr.getID())){
                    item.setSoLuong(item.getSoLuong() + soLuong);
                    item.setThanhTien(item.getSoLuong() * item.getGiaBan());
                    found = true;
                    break;
                }
            }
            
            if(!found){
                hdctList.add(hoaDonChiTietResponse);
            }
            
            spctr.setSoLuong(spctr.getSoLuong() - soLuong);
            
            if(spctr.getSoLuong() == 0){
                rpSP.updateTrangThai(spctr.getID());
            }
            
            // Update số lượng
            rpSP.updateSoLuong(spctr);
            
            
            rpHDCT.add(hoaDonChiTietResponse);
            
            //Load lại table
            
            fillDSSP(rpSP.getAll());
            ShowTableHoaDonChiTiet((ArrayList<HoaDonChiTietReponse>) hdctList);
            // update tổng tiền
            
            tongTien = showTotalMoney((ArrayList<HoaDonChiTietReponse>) hdctList);
            response1.setTongTien(tongTien);
            HoaDonRepo.updateTongTien(response1.getTongTien(), response1.getId());
            String formattedTongTien = String.format("%,.3f₫",tongTien);
            txt_tongTien.setText(formattedTongTien);
            txt_tongTien1.setText(formattedTongTien);
            // load lại bảng hóa dơn
            
            fillHoaDon(rpHD.getAllByStatus());
            
            System.out.println("List: " + rpHD.getAllByStatus());
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi xử lý yêu cầu. Vui lòng thử lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_tbl_DSSanPhamMouseClicked

    private void tbl_DShoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_DShoadonMouseClicked
       indexHoaDonSelected = tbl_DShoadon.getSelectedRow();
       
       HoaDonResponse1 response1 = rpHD.getAllByStatus().get(indexHoaDonSelected);
        
        tongTien = response1.getTongTien();
        System.out.println(response1.getTongTien());
        
        String formattedTongTien = String.format("%,.3f₫",tongTien);
        
        txt_maHoaDon.setText(response1.getMaHoaDon());
        txt_maNhanVien.setText(response1.getMaNhanVien());
        txt_tenKhachHang1.setText(response1.getTenKhachHang());
        txt_maKhachHang.setText(response1.getMaKH());
        txt_tenKhachHang.setText(response1.getTenKhachHang());
        txt_tongTien.setText(formattedTongTien);
        txt_tongTien1.setText(formattedTongTien);
        txt_ngayTT.setText(response1.getNgayTao());
        
        
        
           // Tạm thời loại bỏ ActionListener để tránh áp dụng tự động phiếu giảm giá
           ActionListener[] listeners = cbb_PhieuGiamGia.getActionListeners();
        for (ActionListener listener : listeners) {
            cbb_PhieuGiamGia.removeActionListener(listener);
        }
        if (tongTien >= giamGiaRepository.getGiaTriDonHangToiThieu(response1.getMaNhanVien())) {

            ShowComboboxPhieuGiamGia(giamGiaRepository.getAllGiaTriTotNhat());
            cbb_PhieuGiamGia.setSelectedIndex(-1);
        }

        // Đặt lại ActionListener sau khi đã cập nhật xong ComboBox
        for (ActionListener listener : listeners) {
            cbb_PhieuGiamGia.addActionListener(listener);
        }

        ShowTableHoaDonChiTiet(rpHDCT.getAll(response1.getId()));
        System.out.println("abcxyz: " + tongTien);
        Double giamGia = giamGiaRepository.getGiamNhieuHon(tongTien);
        if (giamGia != null && giamGia > tongTien) {
            Double giam = giamGiaRepository.getGiaTriDonHangToiThieu(maGiamGia);

            System.out.println("hóa đơn tối thiểu: " + giam);
            Double soTienGiam = giam - tongTien;
            String FormatSoTienGiam = String.format("%,.3f₫", soTienGiam);
            TongTienSauGiam = tongTien - giam;
            
            lb_Nofitications.setText("Mua Thêm " + FormatSoTienGiam + " để nhận thêm ưu đãi");
        } else {
            lb_Nofitications.setText("");
        }
        
        // Thêm DocumentListener để tính tiền thừa khi nhập từ bàn phím
        txt_tienKhachDua.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                calculateChange();
            }

            public void removeUpdate(DocumentEvent e) {
                calculateChange();
            }

            public void insertUpdate(DocumentEvent e) {
                calculateChange();
            }
        });

        
    }//GEN-LAST:event_tbl_DShoadonMouseClicked

    private void btn_timKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timKhachHangActionPerformed
                ThemThongTinKhachHangJframe khachHangFrame = new ThemThongTinKhachHangJframe();
                khachHangFrame.setVisible(true);
        
                // Kiểm tra biến tĩnh sau khi đóng KhachHangFrame
                khachHangFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                                id_kh = ThemThongTinKhachHangJframe.id_tamKH;
                                KhachHang kh = ThongTinKHRP.getKhachHangById(id_kh);
                                System.out.println("id bán hàng " + id_kh);
                                if (id_kh != null && kh != null) {  // Kiểm tra id_kh và kh
                                        txt_maKhachHang.setText(kh.getMaKhachHang());
                                        txt_tenKhachHang.setText(kh.getHoTen());
                                        txt_tenKhachHang1.setText(kh.getHoTen());
                                        txt_sdt.setText(kh.getSoDienThoai());
                                    } else {
                                        // Xử lý trường hợp kh hoặc id_kh là null
                                        JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                                    }
                            }
                    });
    }//GEN-LAST:event_btn_timKhachHangActionPerformed

    private void btn_HuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HuyActionPerformed
//         Lấy đối tượng hóa đơn tại chỉ số được chọn
            HoaDonResponse1 hd = rpHD.getAllByStatus().get(indexHoaDonSelected);
            // Xác thực rằng đối tượng hóa đơn không null và có thông tin cần thiết
                if(hd != null && hd.getId() != null){
                        int option = JOptionPane.showConfirmDialog(
                                    null,
                                    "Bạn có chắc chắn muốn hủy hóa đơn này không?",
                                    "Xác nhận hủy",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE
                            );
            
                        //Kiểm tra sự lựa chọn của người dùng
                        if(option == JOptionPane.YES_OPTION){
                                boolean isCancelled = rpHD.huyHoaDon(hd); // Gọi phương thức hủy hóa đơn
                
                                // Kiểm tra xem việc hủy hóa đơn có thành công hay không
                                if(isCancelled){
                                       // Cập nhật lại bảng hiển thị hóa đơn
                                        fillHoaDon(rpHD.getAllByStatus());
                                        txt_ngayTT.setText("");
                                        txt_tenKhachHang.setText("");
                                        txt_maHoaDon.setText("");
                                        txt_tongTien.setText("");
                                        txt_tongTien1.setText("");
                                        txt_maNhanVien.setText("");
                                        txt_tienKhachDua.setText("");
                                        txt_tienThua.setText("");
                                        clearTableData();
                                        fillDSSP(rpSP.getAll());
                                        JOptionPane.showMessageDialog(null, "Hóa đơn đã được hủy thành công.", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    }else{
                                        JOptionPane.showMessageDialog(null, "Không thể hủy hóa đơn. Vui lòng thử lại.", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                            }else{
                                JOptionPane.showMessageDialog(null, "Hủy hóa đơn đã bị hủy bỏ.", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
                            }
                    }else{
                        JOptionPane.showMessageDialog(null, "Hóa đơn không hợp lệ hoặc không được chọn.", "Invalid Selection", JOptionPane.WARNING_MESSAGE);
                    }
    }//GEN-LAST:event_btn_HuyActionPerformed

    private void btn_ThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThanhToanActionPerformed
                if (indexHoaDonSelected < 0) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return; // Dừng xử lý nếu chưa chọn hóa đơn
                    }
        
                HoaDonResponse1 hd = rpHD.getAllByStatus().get(indexHoaDonSelected);
        
                if (hd == null) {
                        JOptionPane.showMessageDialog(null, "Hóa đơn không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return; // Dừng xử lý nếu hóa đơn không hợp lệ
                    }
                // Kiểm tra xem có chọn phiếu giảm giá không
        
                if(cbb_PhieuGiamGia.getSelectedIndex() == -1){
                        // Không chọn phiếu giảm giá, giữ nguyên tổng tiền cũ
                        hd.setTienGiam(null);
                        hd.setId_vouCher(null);
                    }else{
                        // Nếu có chọn phiếu giảm giá, tính tổng tiền sau khi giảm giá
                        if(TongTienSauGiam == null){
                                JOptionPane.showMessageDialog(null, "Giá trị giảm giá không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                                return; // Dừng xử lý nếu giá trị giảm giá không hợp lệ
                            }
                        hd.setTienGiam(TongTienSauGiam);
                        hd.setId_vouCher(id_Voucher);
                    }
                // Kiểm tra tổng số tiền
                double totalAmount = hd.getTongTien();
                if (totalAmount <= 0) {
                        JOptionPane.showMessageDialog(null, "Số tiền thanh toán không hợp lệ.", "Lỗi thanh toán", JOptionPane.ERROR_MESSAGE);
                        return; // Dừng xử lý nếu tổng số tiền không hợp lệ
                    }
                // Hỏi người dùng có muốn tiếp tục thanh toán không
        
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn thanh toán hay không?", "Xác nhận thanh toán", JOptionPane.YES_NO_OPTION);
                if(confirm == JOptionPane.YES_OPTION){
                        try{
                                 // Nếu người dùng chọn Yes, cập nhật thông tin vào cơ sở dữ liệu
                                 rpHD.updateThongTin(hd);
                
                                 // Làm mới bảng hóa đơn
                                 fillHoaDon(rpHD.getAllByStatus());
                
                                 // Xóa dữ liệu trong giỏ hàng sau khi thanh toán
                                 clearTableData();
                
                                 // Xóa Các trường văn bản
                                txt_ngayTT.setText("");
                                txt_tenKhachHang.setText("");
                                txt_maHoaDon.setText("");
                                txt_tongTien.setText("");
                                txt_tongTien1.setText("");
                                txt_maNhanVien.setText("");
                                txt_tienKhachDua.setText("");
                                txt_tienThua.setText("");
                
                                //Hiển thị thông báo thành công
                                JOptionPane.showMessageDialog(null, "Thanh toán thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                            }catch(Exception e){
                                e.printStackTrace();
                                JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi cập nhật thông tin hóa đơn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            }
                    }
    }//GEN-LAST:event_btn_ThanhToanActionPerformed

    private void cbb_HangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_HangItemStateChanged
        // TODO add your handling code here:
                if(cbb_Hang.getSelectedIndex() < 0){
                        tenHangSX = "%";
                        locSpctBanHang();
                    }
                if(evt.getStateChange() == java.awt.event.ItemEvent.SELECTED){
                        tenHangSX = cbb_Hang.getSelectedItem().toString();
                        locSpctBanHang();
                    }
    }//GEN-LAST:event_cbb_HangItemStateChanged

    private void cbb_DungTichItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_DungTichItemStateChanged
        // TODO add your handling code here:
                if(cbb_DungTich.getSelectedIndex() < 0){
                        tenDungTich = "%";
                        locSpctBanHang();
                    }
                if(evt.getStateChange() == java.awt.event.ItemEvent.SELECTED){
                        tenDungTich = cbb_DungTich.getSelectedItem().toString();
                        locSpctBanHang();
                    }
    }//GEN-LAST:event_cbb_DungTichItemStateChanged

    private void cbb_NongDoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_NongDoItemStateChanged
        // TODO add your handling code here:
                if(cbb_NongDo.getSelectedIndex() < 0){
                        tenNongDo = "%";
                        locSpctBanHang();
                    }
                if(evt.getStateChange() == java.awt.event.ItemEvent.SELECTED){
                        tenNongDo = cbb_NongDo.getSelectedItem().toString();
                        locSpctBanHang();
                    }
    }//GEN-LAST:event_cbb_NongDoItemStateChanged

    private void cbb_giaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_giaItemStateChanged
        // TODO add your handling code here:
                if(cbb_gia.getSelectedIndex() == 0){
                        tenGiaBan = false;
                        locSpctBanHang();
                    }else{
                        tenGiaBan = true;
                        locSpctBanHang();
                    }

    }//GEN-LAST:event_cbb_giaItemStateChanged

    private void txt_tenKhachHang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tenKhachHang1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tenKhachHang1ActionPerformed

    private void btn_LamMoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoi1ActionPerformed
        // TODO add your handling code here:
        txt_ngayTT.setText("");
        txt_tenKhachHang1.setText("");
        txt_maKhachHang.setText("");
        txt_tenKhachHang.setText("");
        txt_maHoaDon.setText("");
        txt_tongTien.setText("");
        txt_tongTien1.setText("");
        txt_maNhanVien.setText("");
        txt_tienKhachDua.setText("");
        txt_tienThua.setText("");
        lb_Nofitications.setText("");
        cbb_gia.setSelectedIndex(-1);
        cbb_DungTich.setSelectedIndex(-1);
        cbb_Hang.setSelectedIndex(-1);
        cbb_NongDo.setSelectedIndex(-1);
        tenDungTich = "%";
        tenNongDo = "%";
        tenHangSX = "%";
        tenGiaBan = false;
        fillDSSP(rpSP.getAll());
    }//GEN-LAST:event_btn_LamMoi1ActionPerformed

    private void cbb_PhieuGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_PhieuGiamGiaActionPerformed
        // TODO add your handling code here:
        maGiamGia = (String) cbb_PhieuGiamGia.getSelectedItem();
        System.out.println("giảm giá: " + maGiamGia);
        // Kiểm tra nếu giá trị giảm giá không null
        if (maGiamGia != null) {

            id_Voucher = giamGiaRepository.getIDByMaPhieuGiamGia(maGiamGia);

            try {

                double giamGia = giamGiaRepository.getGiaTriGiam(maGiamGia);
                System.out.println("tiền giảm: " + giamGia);

                if (tongTien != null && tongTien > 0) {
                    // Tạo định dạng tiền tệ
                    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                    System.out.println("Tổng Tiền " + tongTien);

                    // Kiểm tra nếu tổng tiền >= giá trị đơn hàng tối thiểu để áp dụng giảm giá
                    if (tongTien >= giamGiaRepository.getGiaTriDonHangToiThieu(maGiamGia)) {

                        System.out.println("Đơn hàng tối thiểu đây này: " + giamGiaRepository.getGiaTriDonHangToiThieu(maGiamGia));
                        System.out.println("Tiền giảm: " + giamGia);

                        // Tính số tiền giảm
                        double tienGiam = tongTien * (giamGia / 100.00);
                        
                        System.out.println("Tiền giảm 2: " + tienGiam);
                        lb_Nofitications.setText("Bạn giảm được " + String.format("%,.3f₫", tienGiam) + "VNĐ");
                        // Tính tổng tiền sau khi giảm giá
                        double tong = tongTien - tienGiam;
                        TongTienSauGiam = tong;
                        // Hiển thị tổng tiền đã giảm giá trên giao diện
                        txt_tongTien1.setText(String.format("%,.3f₫", tong));
                    } else {
                        System.out.println("sản phẩm không đủ điều kiện giảm!!!");
                        // Hiển thị thông báo cho người dùng
                        JOptionPane.showMessageDialog(null,
                                "Sản phẩm không đủ điều kiện dùng mã.\nVui lòng click lại hóa đơn để thanh toán lại.",
                                "Cảnh báo",
                                JOptionPane.WARNING_MESSAGE);
                        txt_tongTien1.setText(String.format("%,.3f₫", tongTien));
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("giá trị giảm giá không hợp lệ.");
                JOptionPane.showMessageDialog(null, "Giá trị giảm giá không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.out.println("giá trị giảm giá không hợp lệ.");
        }

    }//GEN-LAST:event_cbb_PhieuGiamGiaActionPerformed

    private void tbl_GioHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_GioHangMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_GioHangMouseEntered
    
    private void tbl_GioHangInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tbl_GioHangInputMethodTextChanged
        
        
    }//GEN-LAST:event_tbl_GioHangInputMethodTextChanged

    private void tbl_GioHangPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tbl_GioHangPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_GioHangPropertyChange
   

    private void locSpctBanHang() {
        ArrayList<SanPhamChiTietRespone> list = rpSP.locTheoDieuKienBanHang(tenDungTich, tenHangSX, tenNongDo, tenGiaBan);
        fillDSSP(list);
    }
    
    





    // Variables declaration - do not modify//GEN-BEGIN:variables
    private View.swing.PaneBorder HoaDon_Panel;
    private com.Product.swing.ButtonBadges btn_Huy;
    private javax.swing.JButton btn_LamMoi1;
    private javax.swing.JButton btn_QR;
    private javax.swing.JButton btn_TaoHoaDon;
    private com.Product.swing.ButtonBadges btn_ThanhToan;
    private com.Product.swing.ButtonBadges btn_timKhachHang;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbb_DungTich;
    private javax.swing.JComboBox<String> cbb_Hang;
    private javax.swing.JComboBox<String> cbb_NongDo;
    private javax.swing.JComboBox<String> cbb_PhieuGiamGia;
    private javax.swing.JComboBox<String> cbb_gia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lb_Nofitications;
    private View.swing.Table tbl_DSSanPham;
    private View.swing.Table tbl_DShoadon;
    private View.swing.Table tbl_GioHang;
    private javax.swing.JTextField txt_maHoaDon;
    private javax.swing.JTextField txt_maKhachHang;
    private javax.swing.JTextField txt_maNhanVien;
    private javax.swing.JTextField txt_ngayTT;
    private javax.swing.JTextField txt_sdt;
    private javax.swing.JTextField txt_tenKhachHang;
    private javax.swing.JTextField txt_tenKhachHang1;
    private javax.swing.JTextField txt_tienKhachDua;
    private javax.swing.JTextField txt_tienThua;
    private javax.swing.JTextField txt_tongTien;
    private javax.swing.JLabel txt_tongTien1;
    // End of variables declaration//GEN-END:variables
}
