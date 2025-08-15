/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.Product.form1;


import Helper.ZXingHelper;

import Jframe.ThemDungTich;
import Jframe.ThemHangSX;
import Jframe.ThemNamSX;

import Jframe.ThemNongDo;
import Jframe.ThemPhanLoai;
import Jframe.ThemSuatXu;

import Model_HoaDon.Model_DSHoaDon;
import Model_SanPham.Model_ChiTietSanPham;
import Repository.Repository_ChiTietSanPham;
import Repository.Repository_DSSP;
import Repository.Repository_DanhSachSanPham;
import View.swing.TenDN;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import main.entity.DungTich;
import main.entity.HangSX;
import main.entity.LoaiRuou;
import main.entity.NamSX;
import main.entity.NhaCungCap;
import main.entity.NongDo;


import main.entity.XuatSu;
import main.repository.DungTichRepository;
import main.repository.HangSXRepository;
import main.repository.NamSXRepository;
import main.repository.NongDoRepository;
import main.repository.PhanLoaiRepository;
import main.repository.TenSanPhamRepository;
import main.repository.XuatSuRepository;
import java.util.function.UnaryOperator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import javax.swing.plaf.TabbedPaneUI;
import main.entity.SanPham;
import main.entity.SanPhamChiTiet;
import main.entity.ThuocTinhSanPham;
import main.repository.NhaCungCapRepository;
import main.repository.SanPhamChiTietRepository;
import main.repository.SanPhamRepository;
import main.repository.ThuocTinhSanPhamRepository;
import main.response.SanPhamChiTietRespone;
import net.miginfocom.layout.AC;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author ADMIN
 */
public class Form_SanPham extends javax.swing.JPanel {
//
    public static String ma_sanpham;

    private String selectedProductCode = null;
    
    private DefaultComboBoxModel dcbmDungTich;
    private DungTichRepository DungTichRepo = new DungTichRepository();
    
    private DefaultComboBoxModel dcbmHangSX;
    private HangSXRepository HangSXRepo = new HangSXRepository();
    
    private DefaultComboBoxModel dcbmLoaiRuou;
    private PhanLoaiRepository LoaiRuouRepo = new PhanLoaiRepository();
    
    private DefaultComboBoxModel dcbmNamSX;
    private NamSXRepository NamSXRepo = new NamSXRepository();
    
    private DefaultComboBoxModel dcbmNongDo;
    private NongDoRepository NongDoRepo = new NongDoRepository();
    
    private DefaultComboBoxModel dcbmNCC;
    private NhaCungCapRepository NCCRepo = new NhaCungCapRepository();
    
    private DefaultComboBoxModel dcbmXuatSu;
    private XuatSuRepository XuatSuRepo = new XuatSuRepository();
    
    private DefaultTableModel dtmSanPhamChiTiet = new DefaultTableModel();
    private Repository_DSSP sanPhamChitietRepository = new Repository_DSSP();
    
    private DefaultTableModel dtmSanPham = new DefaultTableModel();
    private SanPhamRepository sanPhamRepository = new SanPhamRepository();
    
    private DefaultTableModel dtmThuocTinhSanPham;
    private ThuocTinhSanPhamRepository thuocTinhSanPhamRepository;
    
    
    public static String maSanPhamChiTiet;
    private String ma_spct = Menu.maSPCT;
    String resultQR;
    
    Form_SanPham(String ketqua) {
        resultQR = ketqua;
        System.out.println("ma sản phẩm tai jframe hoa don " + resultQR);
    }
    private Repository.Repository_DanhSachSanPham rp = new Repository_DanhSachSanPham();
    private Repository.Repository_ChiTietSanPham rp1 = new Repository_ChiTietSanPham();
    private DefaultTableModel mol = new DefaultTableModel();
    
    private String tenHangSX = "%";
    private String tenNongDo = "%";
    private String tenDungTich = "%";
    private boolean tenGiaBan = false;
    
    
    
    

    public Form_SanPham() {
        initComponents();
        setOpaque(false);
        showComboboxDungTich(DungTichRepo.getAll());
        showComboboxHangSX(HangSXRepo.getAll());
        showComboboxLoaiRuou(LoaiRuouRepo.getAll());
        showComboboxNCC(NCCRepo.getAll());
        showComboboxNamSX(NamSXRepo.getAll());
        showComboboxNongDo(NongDoRepo.getAll());
        showComboboxXuatSu(XuatSuRepo.getAll());
        showComboboxTenSanPham(sanPhamRepository.getAll());
        
        ShowComboboxLocHangSX(HangSXRepo.getAll());
        ShowComboboxLocNongDo(NongDoRepo.getAll());
        ShowComboboxLocDungTich(DungTichRepo.getAll());
        ShowComboboxLocGia();
        cbb_LocDungTich.setSelectedItem(-1);
        cbb_LocHang.setSelectedItem(-1);
        cbb_LocNongDo.setSelectedItem(-1);
        cbb_Locgia.setSelectedItem(-1);
        
        sanPhamChitietRepository = new Repository_DSSP();
        dtmSanPhamChiTiet = (DefaultTableModel)tbl_SanPhamChiTiet.getModel();
        ShowTableSanPhamChiTiet(sanPhamChitietRepository.getAllGiamDan());
        
        sanPhamRepository = new SanPhamRepository();
        dtmSanPham = (DefaultTableModel) tbl_sanPham.getModel();
        ShowTableSanPham(sanPhamRepository.getAllGiamDan());
        
        thuocTinhSanPhamRepository = new ThuocTinhSanPhamRepository();
        dtmThuocTinhSanPham = (DefaultTableModel) tbl_Thuoc_Tinh_San_Pham.getModel();
//        ShowTableThuocTinhSanPham(thuocTinhSanPhamRepository.getAll());
        
  
        
        
    }

    
    private void ShowTableSanPhamChiTiet(ArrayList<SanPhamChiTietRespone> lists){
        dtmSanPhamChiTiet.setRowCount(0);
        
        AtomicInteger index = new AtomicInteger(1);
        lists.forEach(s -> dtmSanPhamChiTiet.addRow(new Object[]{
            index.getAndIncrement(),
            s.getMaSPCT(),
            s.getTenSP(),
            s.getHangSX(),
            s.getNamSX(),
            s.getXuatXu(),
            s.getNongDo(),
            s.getPhanLoai(),
            s.getDungTich(),
            s.getNCC(),
            String.format("%,.3f VND", s.getGiaNhap()),
            String.format("%,.3f VND", s.getGiaBan()),
            s.getSoLuong(),
            s.isTrangThai()? "Đang bán" : "Ngừng bán"
        }));
    }
    
    private void ShowTableSanPhamChiTietByQR(SanPhamChiTietRespone spct){
        try{
            if(spct == null){
                throw new NullPointerException("Sản Phẩm Không Tồn Tại");
            }
            // Xóa các dòng hiện tại trong bảng
            DefaultTableModel model = (DefaultTableModel) tbl_SanPhamChiTiet.getModel();
            model.setRowCount(0);
            
            double giaBan = spct.getGiaBan();
            double giaNhap = spct.getGiaNhap();
             String formattedGiaBan = String.format("%.3f", giaBan);
             String formattedGiaNhap = String.format("%.3f", giaNhap);
             // Định dạng lại số tiền để sử dụng dấu phân cách hàng nghìn (nếu cần)
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setGroupingSeparator('.');
            symbols.setDecimalSeparator(',');
            DecimalFormat decimalFormat = new DecimalFormat("#,##0.000", symbols);
            formattedGiaBan = decimalFormat.format(Double.parseDouble(formattedGiaBan));
            formattedGiaNhap = decimalFormat.format(Double.parseDouble(formattedGiaNhap));
            
           
            // Thêm một dòng mới vào bảng với thông tin chi tiết sản phẩm
           
            model.addRow(new Object[]{
                spct.getID(),
                spct.getMaSPCT(),
                spct.getTenSP(),
                spct.getHangSX(),
                spct.getNamSX(),
                spct.getXuatXu(),
                spct.getNongDo(),
                spct.getPhanLoai(),
                spct.getDungTich(),
                spct.getNCC(),
                formattedGiaNhap + "VNĐ",
                formattedGiaBan + "VNĐ",
                spct.getSoLuong(),
                spct.isTrangThai()? "Hết Hàng" : "Còn Hàng"
            });
           
            
        }catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(this, "Không có sản phẩm", "Thông báo", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            // Xử lý các lỗi khác nếu cần
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra: " + ex.getMessage(), "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void ShowTableSanPham(ArrayList<SanPham> lists){
        dtmSanPham.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        lists.forEach(s -> dtmSanPham.addRow(new Object[]{
            index.getAndIncrement(),
            s.getMaSanPham(),
            s.getTenSanPham(),
            s.getMoTa(),
            s.getSoLuong(),
            s.isTrangThai() ? "Còn hàng" : "Hết hàng",
            s.getNgayTao()
        }));
    }
    
    private void ShowTableThuocTinhSanPham(ArrayList<ThuocTinhSanPham> lists){
        dtmThuocTinhSanPham.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        lists.forEach(s -> dtmThuocTinhSanPham.addRow(new Object[]{
            index.getAndIncrement(), s.getMaThuocTinhSanPham(), s.getTenThuocTinhSanPham(),}));
    }
    
    
    private void showComboboxDungTich(ArrayList<DungTich> list){
       DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_DungTichSPCT.getModel();
       for (DungTich dungTich: list) {
           comboBoxModel.addElement(dungTich);
           System.out.println(dungTich.getId());
       }
   }    
    private void showComboboxHangSX(ArrayList<HangSX> list){
       DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_HangSPCT.getModel();
       for (HangSX hangSX : list) {
           comboBoxModel.addElement(hangSX);
           System.out.println(hangSX.getId());
       }
   }    
    private void showComboboxNamSX(ArrayList<NamSX> list){
       DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_NamSPCT.getModel();
       for (NamSX namSX : list) {
           comboBoxModel.addElement(namSX);
           System.out.println(namSX.getId());
       }
   }    
    private void showComboboxLoaiRuou(ArrayList<LoaiRuou> list){
       DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_LoaiRuouSPCT.getModel();
       for (LoaiRuou loaiRuou : list) {
           comboBoxModel.addElement(loaiRuou);
           System.out.println(loaiRuou.getId());
       }
   }    
    private void showComboboxNongDo(ArrayList<NongDo> list){
       DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_NongDoSPCT.getModel();
       for (NongDo nongDo : list) {
           comboBoxModel.addElement(nongDo);
           System.out.println(nongDo.getId());
       }
   }    
    private void showComboboxXuatSu(ArrayList<XuatSu> list){
       DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_XuatxuSPCT.getModel();
       for (XuatSu xuatSu : list) {
           comboBoxModel.addElement(xuatSu);
           System.out.println(xuatSu.getId());
       }
   }    
    private void showComboboxNCC(ArrayList<NhaCungCap> list){
       DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_NCCSPCT.getModel();
       for (NhaCungCap nhaCungCap : list) {
           comboBoxModel.addElement(nhaCungCap);
           System.out.println(nhaCungCap.getId());
       }
   }
    private void showComboboxTenSanPham(ArrayList<SanPham> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_TenSPCT.getModel();
        for (SanPham cl : list) {
            comboBoxModel.addElement(cl.getTenSanPham());
        }
    }

    private void ShowComboboxLocGia(){
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_Locgia.getModel();
        comboBoxModel.removeAllElements();
        comboBoxModel.addElement("Giảm dần");
        comboBoxModel.addElement("Tăng dần");
    }
    private void ShowComboboxLocDungTich(ArrayList<main.entity.DungTich> list){
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_LocDungTich.getModel();
        comboBoxModel.removeAllElements();
        for (DungTich cl : list) {
            comboBoxModel.addElement(cl);
        }
    }
    private void ShowComboboxLocNongDo(ArrayList<main.entity.NongDo> list){
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_LocNongDo.getModel();
        comboBoxModel.removeAllElements();
        for (NongDo cl : list) {
            comboBoxModel.addElement(cl);
        }
    }
    private void ShowComboboxLocHangSX(ArrayList<main.entity.HangSX> list){
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_LocHang.getModel();
        comboBoxModel.removeAllElements();
        for (HangSX cl : list) {
            comboBoxModel.addElement(cl);
        }
    }
    
    private SanPham getFormData(){
        if (txt_tenSanPham.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Bạn Chưa Nhập Sản Phẩm", "Lỗi Nhập Liệu", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        SanPham sp = SanPham.builder()
                .maSanPham(lbl_maSP.getText())
                .tenSanPham(txt_tenSanPham.getText())
                .moTa(txt_mota.getText())
                .build();
        
        return sp;
    }
    
    private ThuocTinhSanPham getFormDataThuocTinhSP(){
        String tenThuocTinh = txtTenThuocTinh.getText().trim();
        tenThuocTinh = tenThuocTinh.split(" ")[0];
        if (txtTenThuocTinh.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập tên thuộc tính", "Lỗi Nhập Liệu", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (!tenThuocTinh.matches("^[\\p{L}\\s]+$")) {
        JOptionPane.showMessageDialog(null, "Tên thuộc tính không được chứa số hoặc ký tự đặc biệt", "Lỗi Nhập Liệu", JOptionPane.ERROR_MESSAGE);
        return null;
    }
        if (tenThuocTinh.length() > 50) {
            JOptionPane.showMessageDialog(null, "Tên thuộc tính không được vượt quá 50 ký tự", "Lỗi Nhập Liệu", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        ThuocTinhSanPham ttsp = ThuocTinhSanPham.builder()
                .maThuocTinhSanPham(lblMaThuocTinh.getText())
                .tenThuocTinhSanPham(txtTenThuocTinh.getText())
                .build();
        return ttsp;
    }
    
    private SanPhamChiTietRespone getFormDataSanPhamCT(){
        String tenSP = cbb_TenSPCT.getSelectedItem().toString().trim();
        String hangSX = cbb_HangSPCT.getSelectedItem().toString().trim();
        String NamSX = cbb_NamSPCT.getSelectedItem().toString().trim();
        String XuatXu = cbb_XuatxuSPCT.getSelectedItem().toString().trim();
        String LoaiRuou = cbb_LoaiRuouSPCT.getSelectedItem().toString().trim();
        String NongDo = cbb_NongDoSPCT.getSelectedItem().toString().trim();
        String DungTich = cbb_DungTichSPCT.getSelectedItem().toString().trim();
        String NhaCC = cbb_NCCSPCT.getSelectedItem().toString().trim();
        String giaNhapText = txt_GiaNhapSPCT.getText().trim();
        String giaBanText = txt_GiaBanSPCT1.getText().trim();
        String soLuongText = txt_SoLuongSPCT.getText().trim();
         // Kiểm tra các trường dữ liệu không để trống
        if (tenSP.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tên sản phẩm không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (hangSX.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Hãng Sản Xuất không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (NamSX.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Năm Sản Xuất không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (XuatXu.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Xuất Xứ không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (LoaiRuou.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Loại Rượu không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (NongDo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nồng Độ không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (DungTich.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Dung Tích không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (NhaCC.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nhà Cung Cấp không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (giaNhapText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Giá Nhập không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (giaBanText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Giá bán không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (soLuongText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Số lượng không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        // Kiểm tra giá nhập
        if (!giaNhapText.matches("\\d+(\\.\\d{1,2})?")) { // Chỉ cho phép số dương, có thể có 1 hoặc 2 chữ số thập phân
            JOptionPane.showMessageDialog(null, "Giá bán phải là số dương và không có ký tự khác", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (giaNhapText.length() > 12) { // Giới hạn 12 ký tự
            JOptionPane.showMessageDialog(null, "Giá bán không được vượt quá 12 ký tự", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        Double giaNhap;
        try {
            giaNhap = Double.parseDouble(giaNhapText);
            if (giaNhap <= 0) { // Giá bán phải lớn hơn 0
                JOptionPane.showMessageDialog(null, "Giá Nhập phải lớn hơn 0", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        } catch (NumberFormatException e) {
            // Xử lý lỗi khi chuyển đổi số
            JOptionPane.showMessageDialog(null, "Dữ liệu số cho giá nhập không hợp lệ: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        // Kiểm tra giá bán
        if (!giaBanText.matches("\\d+(\\.\\d{1,2})?")) { // Chỉ cho phép số dương, có thể có 1 hoặc 2 chữ số thập phân
            JOptionPane.showMessageDialog(null, "Giá bán phải là số dương và không có ký tự khác", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (giaBanText.length() > 12) { // Giới hạn 12 ký tự
            JOptionPane.showMessageDialog(null, "Giá bán không được vượt quá 12 ký tự", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        Double giaBan;
        try {
            giaBan = Double.parseDouble(giaBanText);
            if (giaBan <= 0) { // Giá bán phải lớn hơn 0
                JOptionPane.showMessageDialog(null, "Giá bán phải lớn hơn 0", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        } catch (NumberFormatException e) {
            // Xử lý lỗi khi chuyển đổi số
            JOptionPane.showMessageDialog(null, "Dữ liệu số cho giá bán không hợp lệ: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Kiểm tra số lượng
        if (!soLuongText.matches("\\d+")) { // Chỉ cho phép số nguyên dương
            JOptionPane.showMessageDialog(null, "Số lượng phải là số nguyên dương", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (soLuongText.length() > 12) { // Giới hạn 12 ký tự
            JOptionPane.showMessageDialog(null, "Số lượng không được vượt quá 12 ký tự", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        Integer soLuong;
        try {
            soLuong = Integer.parseInt(soLuongText);
            if (soLuong <= 0) { // Số lượng phải lớn hơn 0
                JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        } catch (NumberFormatException e) {
            // Xử lý lỗi khi chuyển đổi số
            JOptionPane.showMessageDialog(null, "Dữ liệu số cho số lượng không hợp lệ: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Tạo đối tượng respone
        return SanPhamChiTietRespone.builder()
                .tenSP(tenSP)
                .HangSX(hangSX)
                .NamSX(NamSX)
                .xuatXu(XuatXu)
                .NongDo(NongDo)
                .PhanLoai(LoaiRuou)
                .DungTich(DungTich)
                .NCC(NhaCC)
                .GiaNhap(giaNhap)
                .giaBan(giaBan)
                .soLuong(soLuong)
                .build();
    
    }
    
    // Phương thức convertResponeToEntity sử dụng convertToId
    private SanPhamChiTiet convertResponeToEntity(SanPhamChiTietRespone respone){
        ArrayList<SanPham> spList = sanPhamRepository.getSanPhamByTen(respone.getTenSP());
        // Lấy phần tử đầu tiên trong danh sách hoặc trả về null nếu danh sách rỗng
        SanPham sp = spList.stream().findFirst().orElse(null);
        // Lấy các thuộc tính khác
        HangSX hsx = HangSXRepo.getHangSXByTen(respone.getHangSX());
        NamSX nsx = NamSXRepo.getNamSXByTen(respone.getNamSX());
        XuatSu xs = XuatSuRepo.getXuatSuByTen(respone.getXuatXu());
        NongDo nd = NongDoRepo.getNongDoByTen(respone.getNongDo());
        LoaiRuou pl = LoaiRuouRepo.getPhanLoaiByTen(respone.getPhanLoai());
        DungTich dt = DungTichRepo.getDungTichByTen(respone.getDungTich());
        NhaCungCap ncc = NCCRepo.getNhaCungCapByTen(respone.getNCC());

        // Xử lý trường hợp không tìm thấy sản phẩm
        if(sp == null ){
            throw new RuntimeException("Sản phẩm không tìm thấy: " + respone.getTenSP());
        }
        return SanPhamChiTiet.builder()
                .maSanPhamChiTiet(respone.getMaSPCT())
                .sanPhamID(sp.getId())
                .hangSXID(hsx != null ? hsx.getId() : null)
                .NamSXID(nsx != null ? nsx.getId() : null)
                .XuatsuID(xs != null ? xs.getId() : null)
                .NongDoID(nd != null ? nd.getId() : null)
                .PhanLoaiID(pl != null ? pl.getId() : null)
                .DungTichID(dt != null ? dt.getId() : null)
                .NCCID(ncc != null ? ncc.getId() : null)
                .giaNhap(respone.getGiaNhap())
                .giaBan(respone.getGiaBan())
                .soLuongTon(respone.getSoLuong())
                .trangThai(respone.isTrangThai())
                .build();
    }
    
    private void showDataSanPham(int index){
        String MaSP,ten,mota;
        MaSP = tbl_sanPham.getValueAt(index, 1).toString();
        ten = tbl_sanPham.getValueAt(index, 2).toString();
        mota = tbl_sanPham.getValueAt(index, 3).toString();
        lbl_maSP.setText(MaSP);
        txt_tenSanPham.setText(ten);
        txt_mota.setText(mota);
    }

    
    private void detailThuocTinhSanPham(int index){
        // Lấy danh sách thuộc tính sản phẩm từ repository
        //        List<ThuocTinhSanPham> danhSachThuocTinh = thuocTinhSanPhamRepository.getAll();
        
        if(index < 0 || index > tbl_Thuoc_Tinh_San_Pham.getRowCount() -1){
             JOptionPane.showMessageDialog(null, "Chỉ số không hợp lệ.");
            return;
        }
        String maThuocTinh = (String) tbl_Thuoc_Tinh_San_Pham.getValueAt(index, 1);
        String tenThuocTinh = (String) tbl_Thuoc_Tinh_San_Pham.getValueAt(index, 2);
        
        lblMaThuocTinh.setText(maThuocTinh);
        txtTenThuocTinh.setText(tenThuocTinh);
    }
    
//    public JComboBox<String> getCbb_DungTich(){
//        return cbb_DungTich;
//    }
    
//    public JComboBox<String> getCbb_tenSanPham(){
//        return cbb_tenSPCT;
//    }
    
    private SanPhamChiTietRespone getSanPhamResponse(String resultQR){
        Repository_DSSP spRepo = new Repository_DSSP();
        System.out.println("Chức năng tìm kiếm sản phẩm: ");
        // Kiểm tra mã QR không null hoặc rỗng
        if (resultQR == null || resultQR.trim().isEmpty()) {
            System.out.println("Không có mã QR được cung cấp");
            System.out.println("kết quả: " + this.resultQR);
            return null; // Trả vềull nếu không có mã QR n
        } else {
            SanPhamChiTietRespone sp = spRepo.getSanPhamChiTietByMaSPCT(resultQR.trim());
            if (sp != null) {
                System.out.println("Sản Phẩm được tìm thấy: " + sp.getMaSPCT());
                return sp;
            } else {
                System.out.println("Không tìm thấy sản phẩm với mã QR: " + resultQR);
                return null; // Trả về null nếu không tìm thấy hóa đơn
            }
        }
    }
    
//    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        group_DungTich = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        SanPhamChiTietJPanel = new javax.swing.JTabbedPane();
        jpn_DanhSach = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_sanPham = new View.swing.Table();
        jPanel5 = new javax.swing.JPanel();
        btn_timkiem = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txt_TimMaSP = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_maSP = new javax.swing.JLabel();
        txt_tenSanPham = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txt_mota = new javax.swing.JTextArea();
        rdo_tatca = new javax.swing.JRadioButton();
        rdo_DangBan = new javax.swing.JRadioButton();
        rdo_ngungBan = new javax.swing.JRadioButton();
        btn_ThemMoiSP = new com.Product.swing.ButtonBadges();
        btn_XoaSP = new com.Product.swing.ButtonBadges();
        btn_lamMoi = new com.Product.swing.ButtonBadges();
        jpn_sanPhamChiTiet = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_SanPhamChiTiet = new View.swing.Table();
        jLabel8 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        btn_TimSPCT = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        txt_TimMaSPCT = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        cbb_LocHang = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbb_LocDungTich = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        cbb_LocNongDo = new javax.swing.JComboBox<>();
        cbb_Locgia = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        btn_TaiQR = new com.Product.swing.ButtonBadges();
        btn_Reset1 = new com.Product.swing.ButtonBadges();
        btn_XuatEXCEL1 = new com.Product.swing.ButtonBadges();
        jPanel9 = new javax.swing.JPanel();
        paneBorder3 = new View.swing.PaneBorder();
        jLabel20 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        txt_GiaBanSPCT1 = new javax.swing.JTextField();
        txt_SoLuongSPCT = new javax.swing.JTextField();
        txt_GiaNhapSPCT = new javax.swing.JTextField();
        btn_ThemSPCT = new com.Product.swing.ButtonBadges();
        cbb_TenSPCT = new javax.swing.JComboBox<>();
        cbb_HangSPCT = new javax.swing.JComboBox<>();
        cbb_NamSPCT = new javax.swing.JComboBox<>();
        cbb_XuatxuSPCT = new javax.swing.JComboBox<>();
        cbb_LoaiRuouSPCT = new javax.swing.JComboBox<>();
        cbb_NongDoSPCT = new javax.swing.JComboBox<>();
        cbb_DungTichSPCT = new javax.swing.JComboBox<>();
        cbb_NCCSPCT = new javax.swing.JComboBox<>();
        btn_ThemNhanhHangSX = new View.swing.ButtonGradient();
        btn_ThemNhanhNamSX = new View.swing.ButtonGradient();
        btn_ThemNhanhXuatXu = new View.swing.ButtonGradient();
        btn_ThemNhanhLoaiRuou = new View.swing.ButtonGradient();
        btn_ThemNhanhNongDo = new View.swing.ButtonGradient();
        btn_ThemNhanhDungTich = new View.swing.ButtonGradient();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        rdo_HangSX = new javax.swing.JRadioButton();
        rdo_namsx = new javax.swing.JRadioButton();
        rdo_loairuou = new javax.swing.JRadioButton();
        rdo_nongDo = new javax.swing.JRadioButton();
        rdo_DungTich = new javax.swing.JRadioButton();
        rdo_XuatSu = new javax.swing.JRadioButton();
        rdo_NCC = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblMaThuocTinh = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtTenThuocTinh = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_Thuoc_Tinh_San_Pham = new View.swing.Table();
        btn_ThemThuocTinhSanPham = new com.Product.swing.ButtonBadges();
        btn_LamMoiThuocTinhSanPham = new com.Product.swing.ButtonBadges();
        btn_sua = new com.Product.swing.ButtonBadges();
        btn_xoa = new com.Product.swing.ButtonBadges();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("SẢN PHẨM");

        SanPhamChiTietJPanel.setBackground(new java.awt.Color(153, 153, 255));
        SanPhamChiTietJPanel.setForeground(new java.awt.Color(255, 255, 255));
        SanPhamChiTietJPanel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jpn_DanhSach.setOpaque(false);

        tbl_sanPham.setBackground(new java.awt.Color(204, 204, 204));
        tbl_sanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Mô Tả", "Số Lượng", "Trạng Thái", "Ngày tạo"
            }
        ));
        tbl_sanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_sanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_sanPham);
        if (tbl_sanPham.getColumnModel().getColumnCount() > 0) {
            tbl_sanPham.getColumnModel().getColumn(4).setHeaderValue("Số Lượng Còn");
            tbl_sanPham.getColumnModel().getColumn(5).setHeaderValue("Trạng Thái");
            tbl_sanPham.getColumnModel().getColumn(6).setHeaderValue("Ngày nhập");
        }

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        btn_timkiem.setBackground(new java.awt.Color(102, 102, 255));
        btn_timkiem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_timkiem.setForeground(new java.awt.Color(255, 255, 255));
        btn_timkiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/Timkiem.png"))); // NOI18N
        btn_timkiem.setText("TÌM");
        btn_timkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timkiemActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Tìm kiếm sản phẩm:");

        txt_TimMaSP.setBackground(new java.awt.Color(255, 255, 255));
        txt_TimMaSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_TimMaSP.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_TimMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(btn_timkiem)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_timkiem)
                    .addComponent(jLabel2)
                    .addComponent(txt_TimMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Mã SP:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tên SP:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Mô Tả:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Trạng Thái:");

        lbl_maSP.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_maSP.setForeground(new java.awt.Color(0, 0, 0));

        txt_tenSanPham.setBackground(new java.awt.Color(255, 255, 255));
        txt_tenSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_tenSanPham.setForeground(new java.awt.Color(0, 0, 0));

        txt_mota.setBackground(new java.awt.Color(255, 255, 255));
        txt_mota.setColumns(20);
        txt_mota.setRows(5);
        jScrollPane3.setViewportView(txt_mota);

        buttonGroup1.add(rdo_tatca);
        rdo_tatca.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdo_tatca.setForeground(new java.awt.Color(0, 0, 0));
        rdo_tatca.setText("Tất cả");
        rdo_tatca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdo_tatcaMouseClicked(evt);
            }
        });
        rdo_tatca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_tatcaActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdo_DangBan);
        rdo_DangBan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdo_DangBan.setForeground(new java.awt.Color(0, 0, 0));
        rdo_DangBan.setText("Đang Bán");
        rdo_DangBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdo_DangBanMouseClicked(evt);
            }
        });
        rdo_DangBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_DangBanActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdo_ngungBan);
        rdo_ngungBan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdo_ngungBan.setForeground(new java.awt.Color(0, 0, 0));
        rdo_ngungBan.setText("Ngừng Bán");
        rdo_ngungBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdo_ngungBanMouseClicked(evt);
            }
        });
        rdo_ngungBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_ngungBanActionPerformed(evt);
            }
        });

        btn_ThemMoiSP.setBackground(new java.awt.Color(102, 102, 255));
        btn_ThemMoiSP.setForeground(new java.awt.Color(255, 255, 255));
        btn_ThemMoiSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/ADD.png"))); // NOI18N
        btn_ThemMoiSP.setText("THÊM MỚI");
        btn_ThemMoiSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_ThemMoiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemMoiSPActionPerformed(evt);
            }
        });

        btn_XoaSP.setBackground(new java.awt.Color(102, 102, 255));
        btn_XoaSP.setForeground(new java.awt.Color(255, 255, 255));
        btn_XoaSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/An.png"))); // NOI18N
        btn_XoaSP.setText("ẨN SẢN PHẨM");
        btn_XoaSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_XoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaSPActionPerformed(evt);
            }
        });

        btn_lamMoi.setBackground(new java.awt.Color(102, 102, 255));
        btn_lamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btn_lamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/RESET.png"))); // NOI18N
        btn_lamMoi.setText("LÀM MỚI");
        btn_lamMoi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_lamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpn_DanhSachLayout = new javax.swing.GroupLayout(jpn_DanhSach);
        jpn_DanhSach.setLayout(jpn_DanhSachLayout);
        jpn_DanhSachLayout.setHorizontalGroup(
            jpn_DanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpn_DanhSachLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpn_DanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpn_DanhSachLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(21, 21, 21)
                        .addComponent(rdo_tatca)
                        .addGap(27, 27, 27)
                        .addComponent(rdo_DangBan)
                        .addGap(18, 18, 18)
                        .addComponent(rdo_ngungBan)
                        .addGap(0, 750, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpn_DanhSachLayout.createSequentialGroup()
                        .addGroup(jpn_DanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpn_DanhSachLayout.createSequentialGroup()
                                .addGroup(jpn_DanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpn_DanhSachLayout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(30, 30, 30)
                                        .addComponent(lbl_maSP, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jpn_DanhSachLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(34, 34, 34)
                                        .addComponent(txt_tenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(122, 122, 122)
                                .addComponent(jLabel6)
                                .addGap(59, 59, 59)
                                .addComponent(jScrollPane3))
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(62, 62, 62))))
            .addGroup(jpn_DanhSachLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(btn_ThemMoiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addComponent(btn_XoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(btn_lamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpn_DanhSachLayout.setVerticalGroup(
            jpn_DanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpn_DanhSachLayout.createSequentialGroup()
                .addGroup(jpn_DanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpn_DanhSachLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jpn_DanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpn_DanhSachLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4))
                            .addGroup(jpn_DanhSachLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_maSP, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(96, 96, 96)
                        .addGroup(jpn_DanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_tenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(141, 141, 141))
                    .addGroup(jpn_DanhSachLayout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addGroup(jpn_DanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jpn_DanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(rdo_tatca)
                    .addComponent(rdo_DangBan)
                    .addComponent(rdo_ngungBan))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpn_DanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ThemMoiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_XoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_lamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        SanPhamChiTietJPanel.addTab("Danh Sách", jpn_DanhSach);

        jpn_sanPhamChiTiet.setOpaque(false);

        tbl_SanPhamChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Hãng SX", "Năm SX", "Xuất Sứ", "Nồng Độ", "Loại SP", "Dung Tích", "NhàCC", "Giá Nhập", "Giá Bán", "SL Còn", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_SanPhamChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_SanPhamChiTietMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_SanPhamChiTiet);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(127, 127, 127));
        jLabel8.setText("Chi Tiết Sản Phẩm");

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        btn_TimSPCT.setBackground(new java.awt.Color(102, 102, 255));
        btn_TimSPCT.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_TimSPCT.setForeground(new java.awt.Color(255, 255, 255));
        btn_TimSPCT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/Timkiem.png"))); // NOI18N
        btn_TimSPCT.setText("TÌM");
        btn_TimSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimSPCTActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Tìm kiếm sản phẩm:");

        txt_TimMaSPCT.setBackground(new java.awt.Color(255, 255, 255));
        txt_TimMaSPCT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_TimMaSPCT.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_TimMaSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(btn_TimSPCT)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_TimSPCT)
                    .addComponent(jLabel22)
                    .addComponent(txt_TimMaSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));
        jPanel8.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Lọc Sản Phẩm Theo:");

        cbb_LocHang.setBackground(new java.awt.Color(255, 255, 255));
        cbb_LocHang.setForeground(new java.awt.Color(0, 0, 0));
        cbb_LocHang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_LocHangItemStateChanged(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(127, 127, 127));
        jLabel27.setText("HÃNG SX");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(127, 127, 127));
        jLabel12.setText("Dung Tích");

        cbb_LocDungTich.setBackground(new java.awt.Color(255, 255, 255));
        cbb_LocDungTich.setForeground(new java.awt.Color(0, 0, 0));
        cbb_LocDungTich.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_LocDungTichItemStateChanged(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(127, 127, 127));
        jLabel28.setText("Nồng Độ");

        cbb_LocNongDo.setBackground(new java.awt.Color(255, 255, 255));
        cbb_LocNongDo.setForeground(new java.awt.Color(0, 0, 0));
        cbb_LocNongDo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_LocNongDoItemStateChanged(evt);
            }
        });

        cbb_Locgia.setBackground(new java.awt.Color(255, 255, 255));
        cbb_Locgia.setForeground(new java.awt.Color(0, 0, 0));
        cbb_Locgia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_LocgiaItemStateChanged(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(127, 127, 127));
        jLabel29.setText("Giá");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel26)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbb_LocHang, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbb_LocDungTich, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbb_LocNongDo, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbb_Locgia, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel26)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbb_Locgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel29))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbb_LocNongDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel28))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbb_LocDungTich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27)
                        .addComponent(cbb_LocHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 20, Short.MAX_VALUE))
        );

        btn_TaiQR.setBackground(new java.awt.Color(102, 102, 255));
        btn_TaiQR.setForeground(new java.awt.Color(255, 255, 255));
        btn_TaiQR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/ADD.png"))); // NOI18N
        btn_TaiQR.setText("TẢI QR");
        btn_TaiQR.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_TaiQR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TaiQRActionPerformed(evt);
            }
        });

        btn_Reset1.setBackground(new java.awt.Color(102, 102, 255));
        btn_Reset1.setForeground(new java.awt.Color(255, 255, 255));
        btn_Reset1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/RESET.png"))); // NOI18N
        btn_Reset1.setText("LÀM MỚI");
        btn_Reset1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Reset1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Reset1ActionPerformed(evt);
            }
        });

        btn_XuatEXCEL1.setBackground(new java.awt.Color(0, 153, 0));
        btn_XuatEXCEL1.setForeground(new java.awt.Color(255, 255, 255));
        btn_XuatEXCEL1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/excel.png"))); // NOI18N
        btn_XuatEXCEL1.setText("XUẤT EXCEL");
        btn_XuatEXCEL1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_XuatEXCEL1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XuatEXCEL1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpn_sanPhamChiTietLayout = new javax.swing.GroupLayout(jpn_sanPhamChiTiet);
        jpn_sanPhamChiTiet.setLayout(jpn_sanPhamChiTietLayout);
        jpn_sanPhamChiTietLayout.setHorizontalGroup(
            jpn_sanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpn_sanPhamChiTietLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpn_sanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpn_sanPhamChiTietLayout.createSequentialGroup()
                        .addGroup(jpn_sanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(jpn_sanPhamChiTietLayout.createSequentialGroup()
                                .addComponent(btn_TaiQR, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(199, 199, 199)
                                .addComponent(btn_Reset1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_XuatEXCEL1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 1088, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jpn_sanPhamChiTietLayout.setVerticalGroup(
            jpn_sanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpn_sanPhamChiTietLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 168, Short.MAX_VALUE)
                .addGroup(jpn_sanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_TaiQR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Reset1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_XuatEXCEL1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        SanPhamChiTietJPanel.addTab("Sản Phẩm Chi Tiết", jpn_sanPhamChiTiet);

        paneBorder3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("THÊM SẢN PHẨM CHI TIẾT");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Tên Sản Phẩm");

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Hãng Sản Xuất");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Năm Sản Xuất");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Nồng Độ");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Dung Tích");

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("Loại Rượu");

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("Xuất Xứ");

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Nhà Cung Cấp");

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("Số Lượng");

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Giá Nhập");

        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Giá Bán");

        txt_GiaBanSPCT1.setBackground(new java.awt.Color(255, 255, 255));
        txt_GiaBanSPCT1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_GiaBanSPCT1.setForeground(new java.awt.Color(0, 0, 0));
        txt_GiaBanSPCT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_GiaBanSPCT1txt_HangSX3ActionPerformed(evt);
            }
        });

        txt_SoLuongSPCT.setBackground(new java.awt.Color(255, 255, 255));
        txt_SoLuongSPCT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_SoLuongSPCT.setForeground(new java.awt.Color(0, 0, 0));

        txt_GiaNhapSPCT.setBackground(new java.awt.Color(255, 255, 255));
        txt_GiaNhapSPCT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_GiaNhapSPCT.setForeground(new java.awt.Color(0, 0, 0));
        txt_GiaNhapSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_GiaNhapSPCTtxt_tenSP3ActionPerformed(evt);
            }
        });

        btn_ThemSPCT.setBackground(new java.awt.Color(102, 102, 255));
        btn_ThemSPCT.setForeground(new java.awt.Color(255, 255, 255));
        btn_ThemSPCT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/ADD.png"))); // NOI18N
        btn_ThemSPCT.setText("THÊM MỚI");
        btn_ThemSPCT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_ThemSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemSPCTActionPerformed(evt);
            }
        });

        cbb_TenSPCT.setBackground(new java.awt.Color(255, 255, 255));
        cbb_TenSPCT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbb_TenSPCT.setForeground(new java.awt.Color(0, 0, 0));

        cbb_HangSPCT.setBackground(new java.awt.Color(255, 255, 255));
        cbb_HangSPCT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbb_HangSPCT.setForeground(new java.awt.Color(0, 0, 0));

        cbb_NamSPCT.setBackground(new java.awt.Color(255, 255, 255));
        cbb_NamSPCT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbb_NamSPCT.setForeground(new java.awt.Color(0, 0, 0));
        cbb_NamSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_NamSPCTActionPerformed(evt);
            }
        });

        cbb_XuatxuSPCT.setBackground(new java.awt.Color(255, 255, 255));
        cbb_XuatxuSPCT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbb_XuatxuSPCT.setForeground(new java.awt.Color(0, 0, 0));

        cbb_LoaiRuouSPCT.setBackground(new java.awt.Color(255, 255, 255));
        cbb_LoaiRuouSPCT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbb_LoaiRuouSPCT.setForeground(new java.awt.Color(0, 0, 0));

        cbb_NongDoSPCT.setBackground(new java.awt.Color(255, 255, 255));
        cbb_NongDoSPCT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbb_NongDoSPCT.setForeground(new java.awt.Color(0, 0, 0));

        cbb_DungTichSPCT.setBackground(new java.awt.Color(255, 255, 255));
        cbb_DungTichSPCT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbb_DungTichSPCT.setForeground(new java.awt.Color(0, 0, 0));

        cbb_NCCSPCT.setBackground(new java.awt.Color(255, 255, 255));
        cbb_NCCSPCT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbb_NCCSPCT.setForeground(new java.awt.Color(0, 0, 0));

        btn_ThemNhanhHangSX.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/ADD.png"))); // NOI18N
        btn_ThemNhanhHangSX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemNhanhHangSXActionPerformed(evt);
            }
        });

        btn_ThemNhanhNamSX.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/ADD.png"))); // NOI18N
        btn_ThemNhanhNamSX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemNhanhNamSXActionPerformed(evt);
            }
        });

        btn_ThemNhanhXuatXu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/ADD.png"))); // NOI18N
        btn_ThemNhanhXuatXu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemNhanhXuatXuActionPerformed(evt);
            }
        });

        btn_ThemNhanhLoaiRuou.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/ADD.png"))); // NOI18N
        btn_ThemNhanhLoaiRuou.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemNhanhLoaiRuouActionPerformed(evt);
            }
        });

        btn_ThemNhanhNongDo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/ADD.png"))); // NOI18N
        btn_ThemNhanhNongDo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemNhanhNongDoActionPerformed(evt);
            }
        });

        btn_ThemNhanhDungTich.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/ADD.png"))); // NOI18N
        btn_ThemNhanhDungTich.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemNhanhDungTichActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout paneBorder3Layout = new javax.swing.GroupLayout(paneBorder3);
        paneBorder3.setLayout(paneBorder3Layout);
        paneBorder3Layout.setHorizontalGroup(
            paneBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneBorder3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(paneBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel46)
                    .addComponent(txt_GiaNhapSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(paneBorder3Layout.createSequentialGroup()
                        .addGroup(paneBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(paneBorder3Layout.createSequentialGroup()
                                .addComponent(cbb_LoaiRuouSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_ThemNhanhLoaiRuou, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel42)
                            .addComponent(cbb_TenSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32))
                        .addGap(33, 33, 33)
                        .addGroup(paneBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(jLabel47)
                            .addComponent(txt_GiaBanSPCT1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(paneBorder3Layout.createSequentialGroup()
                                .addComponent(cbb_NongDoSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_ThemNhanhNongDo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(paneBorder3Layout.createSequentialGroup()
                                .addGroup(paneBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel33)
                                    .addComponent(cbb_HangSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_ThemNhanhHangSX, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(59, 59, 59)
                        .addGroup(paneBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_SoLuongSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45)
                            .addComponent(jLabel34)
                            .addGroup(paneBorder3Layout.createSequentialGroup()
                                .addComponent(cbb_NamSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_ThemNhanhNamSX, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(paneBorder3Layout.createSequentialGroup()
                                .addComponent(cbb_DungTichSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_ThemNhanhDungTich, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel36))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(paneBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbb_XuatxuSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44)
                    .addComponent(jLabel43)
                    .addComponent(cbb_NCCSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ThemSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_ThemNhanhXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(177, 177, 177))
            .addGroup(paneBorder3Layout.createSequentialGroup()
                .addGap(411, 411, 411)
                .addComponent(jLabel20)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        paneBorder3Layout.setVerticalGroup(
            paneBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneBorder3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel20)
                .addGap(18, 18, 18)
                .addGroup(paneBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(paneBorder3Layout.createSequentialGroup()
                        .addGroup(paneBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(paneBorder3Layout.createSequentialGroup()
                                .addGroup(paneBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(paneBorder3Layout.createSequentialGroup()
                                        .addComponent(jLabel34)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbb_NamSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btn_ThemNhanhNamSX, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
                            .addGroup(paneBorder3Layout.createSequentialGroup()
                                .addGroup(paneBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(paneBorder3Layout.createSequentialGroup()
                                        .addComponent(jLabel32)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbb_TenSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(paneBorder3Layout.createSequentialGroup()
                                        .addGroup(paneBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btn_ThemNhanhHangSX, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(paneBorder3Layout.createSequentialGroup()
                                                .addComponent(jLabel33)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cbb_HangSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(2, 2, 2)))
                                .addGap(16, 16, 16)))
                        .addGroup(paneBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(paneBorder3Layout.createSequentialGroup()
                                .addGroup(paneBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(paneBorder3Layout.createSequentialGroup()
                                        .addGap(57, 57, 57)
                                        .addComponent(jLabel35))
                                    .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(paneBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbb_NongDoSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbb_DungTichSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(paneBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn_ThemNhanhDungTich, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_ThemNhanhNongDo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(paneBorder3Layout.createSequentialGroup()
                                    .addGap(57, 57, 57)
                                    .addGroup(paneBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneBorder3Layout.createSequentialGroup()
                                            .addComponent(jLabel42)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(cbb_LoaiRuouSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(btn_ThemNhanhLoaiRuou, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(57, 57, 57)
                        .addGroup(paneBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(paneBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(paneBorder3Layout.createSequentialGroup()
                                    .addComponent(jLabel46)
                                    .addGap(18, 18, 18)
                                    .addComponent(txt_GiaNhapSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneBorder3Layout.createSequentialGroup()
                                    .addComponent(jLabel45)
                                    .addGap(18, 18, 18)
                                    .addComponent(txt_SoLuongSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(paneBorder3Layout.createSequentialGroup()
                                .addComponent(jLabel47)
                                .addGap(18, 18, 18)
                                .addComponent(txt_GiaBanSPCT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(paneBorder3Layout.createSequentialGroup()
                        .addGroup(paneBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(paneBorder3Layout.createSequentialGroup()
                                .addComponent(jLabel43)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbb_XuatxuSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_ThemNhanhXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(75, 75, 75)
                        .addGroup(paneBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneBorder3Layout.createSequentialGroup()
                                .addComponent(jLabel44)
                                .addGap(38, 38, 38))
                            .addGroup(paneBorder3Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(cbb_NCCSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_ThemSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(360, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paneBorder3, javax.swing.GroupLayout.PREFERRED_SIZE, 1136, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(paneBorder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        SanPhamChiTietJPanel.addTab("Thêm Sản Phẩm Chi Tiết", jPanel9);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        rdo_HangSX.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdo_HangSX);
        rdo_HangSX.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdo_HangSX.setForeground(new java.awt.Color(0, 0, 0));
        rdo_HangSX.setText("Hãng Sản Xuất");
        rdo_HangSX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_HangSXActionPerformed(evt);
            }
        });

        rdo_namsx.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdo_namsx);
        rdo_namsx.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdo_namsx.setForeground(new java.awt.Color(0, 0, 0));
        rdo_namsx.setText("Năm Sản Xuất");
        rdo_namsx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_namsxActionPerformed(evt);
            }
        });

        rdo_loairuou.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdo_loairuou);
        rdo_loairuou.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdo_loairuou.setForeground(new java.awt.Color(0, 0, 0));
        rdo_loairuou.setText("Loại rượu");
        rdo_loairuou.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_loairuouActionPerformed(evt);
            }
        });

        rdo_nongDo.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdo_nongDo);
        rdo_nongDo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdo_nongDo.setForeground(new java.awt.Color(0, 0, 0));
        rdo_nongDo.setText("Nồng Độ");
        rdo_nongDo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_nongDoActionPerformed(evt);
            }
        });

        rdo_DungTich.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdo_DungTich);
        rdo_DungTich.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdo_DungTich.setForeground(new java.awt.Color(0, 0, 0));
        rdo_DungTich.setText("Dung Tích");
        rdo_DungTich.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_DungTichActionPerformed(evt);
            }
        });

        rdo_XuatSu.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdo_XuatSu);
        rdo_XuatSu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdo_XuatSu.setForeground(new java.awt.Color(0, 0, 0));
        rdo_XuatSu.setText("Xuất Sứ");
        rdo_XuatSu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_XuatSuActionPerformed(evt);
            }
        });

        rdo_NCC.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdo_NCC);
        rdo_NCC.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdo_NCC.setForeground(new java.awt.Color(0, 0, 0));
        rdo_NCC.setText("Nhà Cung Cấp");
        rdo_NCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_NCCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdo_XuatSu)
                    .addComponent(rdo_DungTich))
                .addGap(72, 72, 72)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdo_HangSX)
                    .addComponent(rdo_NCC))
                .addGap(95, 95, 95)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdo_nongDo)
                    .addComponent(rdo_namsx))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                .addComponent(rdo_loairuou)
                .addGap(71, 71, 71))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(rdo_loairuou)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdo_HangSX)
                            .addComponent(rdo_namsx)
                            .addComponent(rdo_DungTich))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdo_XuatSu)
                            .addComponent(rdo_NCC)
                            .addComponent(rdo_nongDo))))
                .addContainerGap())
        );

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Danh Mục Thuộc Tính");

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Mã thuộc tính ");

        lblMaThuocTinh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMaThuocTinh.setForeground(new java.awt.Color(0, 0, 0));

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Tên thuộc tính");

        txtTenThuocTinh.setBackground(new java.awt.Color(255, 255, 255));
        txtTenThuocTinh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTenThuocTinh.setForeground(new java.awt.Color(0, 0, 0));

        tbl_Thuoc_Tinh_San_Pham.setBackground(new java.awt.Color(204, 204, 204));
        tbl_Thuoc_Tinh_San_Pham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã Thuộc Tính", "Tên Thuộc Tính"
            }
        ));
        tbl_Thuoc_Tinh_San_Pham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_Thuoc_Tinh_San_PhamMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_Thuoc_Tinh_San_Pham);

        btn_ThemThuocTinhSanPham.setBackground(new java.awt.Color(102, 102, 255));
        btn_ThemThuocTinhSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btn_ThemThuocTinhSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/ADD.png"))); // NOI18N
        btn_ThemThuocTinhSanPham.setText("THÊM MỚI");
        btn_ThemThuocTinhSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_ThemThuocTinhSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemThuocTinhSanPhamActionPerformed(evt);
            }
        });

        btn_LamMoiThuocTinhSanPham.setBackground(new java.awt.Color(102, 102, 255));
        btn_LamMoiThuocTinhSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btn_LamMoiThuocTinhSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/RESET.png"))); // NOI18N
        btn_LamMoiThuocTinhSanPham.setText("LÀM MỚI");
        btn_LamMoiThuocTinhSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_LamMoiThuocTinhSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiThuocTinhSanPhamActionPerformed(evt);
            }
        });

        btn_sua.setBackground(new java.awt.Color(102, 102, 255));
        btn_sua.setForeground(new java.awt.Color(255, 255, 255));
        btn_sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/SAVE.png"))); // NOI18N
        btn_sua.setText("SỬA");
        btn_sua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });

        btn_xoa.setBackground(new java.awt.Color(102, 102, 255));
        btn_xoa.setForeground(new java.awt.Color(255, 255, 255));
        btn_xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/DELETE.png"))); // NOI18N
        btn_xoa.setText("XÓA");
        btn_xoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1124, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(btn_ThemThuocTinhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(161, 161, 161)
                                .addComponent(btn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(164, 164, 164)
                                        .addComponent(btn_LamMoiThuocTinhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel15)
                                            .addComponent(jLabel17)
                                            .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(50, 50, 50)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel14))))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel15)
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(46, 46, 46)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ThemThuocTinhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_LamMoiThuocTinhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );

        SanPhamChiTietJPanel.addTab("Thuộc Tính Sản Phẩm", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(489, 489, 489)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(SanPhamChiTietJPanel)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SanPhamChiTietJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 762, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
        // TODO add your handling code here:
        int index = tbl_Thuoc_Tinh_San_Pham.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa");
            return;
        }

        try {
            ThuocTinhSanPham ttsp = null;
            String message = "Xóa Thành Công";
            List<ThuocTinhSanPham> danhSachThuocTinh = null;

            if (rdo_HangSX.isSelected()) {
                danhSachThuocTinh = thuocTinhSanPhamRepository.getHangSX();
            } else if (rdo_namsx.isSelected()) {
                danhSachThuocTinh = thuocTinhSanPhamRepository.getNamSX();
            } else if (rdo_loairuou.isSelected()) {
                danhSachThuocTinh = thuocTinhSanPhamRepository.getPhanLoai();
            } else if (rdo_XuatSu.isSelected()) {
                danhSachThuocTinh = thuocTinhSanPhamRepository.getXuatXu();
            } else if (rdo_NCC.isSelected()) {
                danhSachThuocTinh = thuocTinhSanPhamRepository.getNCC();
            } else if (rdo_nongDo.isSelected()) {
                danhSachThuocTinh = thuocTinhSanPhamRepository.getNongDo();
            } else if (rdo_DungTich.isSelected()) {
                danhSachThuocTinh = thuocTinhSanPhamRepository.getDungTich();
            }  else {
                message = "Vui lòng chọn thuộc tính để xóa!";
                JOptionPane.showMessageDialog(null, message);
                return;
            }

            if (danhSachThuocTinh != null && index >= 0 && index < danhSachThuocTinh.size()) {
                ttsp = danhSachThuocTinh.get(index);

                if (rdo_HangSX.isSelected()) {
                    thuocTinhSanPhamRepository.removeHangSX(ttsp.getId());
                    ShowTableThuocTinhSanPham(thuocTinhSanPhamRepository.getHangSX());
                    
                    List<HangSX> HangSX = HangSXRepo.getAll();
                    DefaultComboBoxModel<String> modelHangSX = new DefaultComboBoxModel<>();
                    for (HangSX g : HangSX) {
                        modelHangSX.addElement(g.getTen());
                    }
                    cbb_HangSPCT.setModel(modelHangSX);

                } else if (rdo_namsx.isSelected()) {
                    thuocTinhSanPhamRepository.removeNamSX(ttsp.getId());
                    ShowTableThuocTinhSanPham(thuocTinhSanPhamRepository.getNamSX());
                    
                    List<NamSX> namSXs = NamSXRepo.getAll();
                    DefaultComboBoxModel<String> modelNamSX = new DefaultComboBoxModel<>();
                    for (NamSX g : namSXs) {
                        modelNamSX.addElement(g.getTen());
                    }
                    cbb_NamSPCT.setModel(modelNamSX);

                } else if (rdo_loairuou.isSelected()) {
                    thuocTinhSanPhamRepository.removeLoaiRuou(ttsp.getId());
                    ShowTableThuocTinhSanPham(thuocTinhSanPhamRepository.getPhanLoai());
                    
                    List<LoaiRuou> loai = LoaiRuouRepo.getAll();
                    DefaultComboBoxModel<String> modelloai = new DefaultComboBoxModel<>();
                    for (LoaiRuou g : loai) {
                        modelloai.addElement(g.getTen());
                    }
                    cbb_LoaiRuouSPCT.setModel(modelloai);

                } else if (rdo_XuatSu.isSelected()) {
                    thuocTinhSanPhamRepository.removeXuatSu(ttsp.getId());
                    ShowTableThuocTinhSanPham(thuocTinhSanPhamRepository.getXuatXu());
                    
                    List<XuatSu> Xs = XuatSuRepo.getAll();
                    DefaultComboBoxModel<String> modelxuatsu = new DefaultComboBoxModel<>();
                    for (XuatSu g : Xs) {
                        modelxuatsu.addElement(g.getTen());
                    }
                    cbb_XuatxuSPCT.setModel(modelxuatsu);

                } else if (rdo_NCC.isSelected()) {
                    thuocTinhSanPhamRepository.removeNCC(ttsp.getId());
                    ShowTableThuocTinhSanPham(thuocTinhSanPhamRepository.getNCC());
                    
                    List<NhaCungCap> NCC = NCCRepo.getAll();
                    DefaultComboBoxModel<String> modelNCC= new DefaultComboBoxModel<>();
                    for (NhaCungCap g : NCC) {
                        modelNCC.addElement(g.getTen());
                    }
                    cbb_NCCSPCT.setModel(modelNCC);

                } else if (rdo_nongDo.isSelected()) {
                    thuocTinhSanPhamRepository.removeNongDo(ttsp.getId());
                    ShowTableThuocTinhSanPham(thuocTinhSanPhamRepository.getNongDo());
                   
                    List<NongDo> nongdo = NongDoRepo.getAll();
                    DefaultComboBoxModel<String> modelNongDo = new DefaultComboBoxModel<>();
                    for (NongDo g : nongdo) {
                        modelNongDo.addElement(g.getTen());
                    }
                    cbb_NongDoSPCT.setModel(modelNongDo);

                } else if (rdo_DungTich.isSelected()) {
                    thuocTinhSanPhamRepository.removeDungTich(ttsp.getId());
                    ShowTableThuocTinhSanPham(thuocTinhSanPhamRepository.getDungTich());
                    
                    List<DungTich> DungTich = DungTichRepo.getAll();
                    DefaultComboBoxModel<String> modelDungTich = new DefaultComboBoxModel<>();
                    for (DungTich g : DungTich) {
                        modelDungTich.addElement(g.getTen());
                    }
                    cbb_DungTichSPCT.setModel(modelDungTich);

                } 
                    JOptionPane.showMessageDialog(null, message);
                } else {
                JOptionPane.showMessageDialog(null, "Chỉ số không hợp lệ hoặc danh sách trống.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi xóa thuộc tính sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_xoaActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        // TODO add your handling code here:
        int index = tbl_Thuoc_Tinh_San_Pham.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để sửa");
            return;
        }
        try {
            String message = "Sửa Thành Công";
            ArrayList<ThuocTinhSanPham> updatedList = null;

            if (rdo_HangSX.isSelected()) {
                ThuocTinhSanPham ttsp = thuocTinhSanPhamRepository.getHangSX().get(index);
                thuocTinhSanPhamRepository.updateHangSX(getFormDataThuocTinhSP(), ttsp.getId());
                updatedList = thuocTinhSanPhamRepository.getHangSX();
                // Cập nhật ComboBox ChatLieu
                List<HangSX> HangSX = HangSXRepo.getAll();
                DefaultComboBoxModel<String> modelHangSX = new DefaultComboBoxModel<>();
                for (HangSX g : HangSX) {
                    modelHangSX.addElement(g.getTen());
                }
                cbb_HangSPCT.setModel(modelHangSX);

            } else if (rdo_namsx.isSelected()) {
                ThuocTinhSanPham ttsp = thuocTinhSanPhamRepository.getNamSX().get(index);
                thuocTinhSanPhamRepository.updateNamSX(getFormDataThuocTinhSP(), ttsp.getId());
                updatedList = thuocTinhSanPhamRepository.getNamSX();
                // Cập nhật ComboBox ThuongHieu
                List<NamSX> namSXs = NamSXRepo.getAll();
                DefaultComboBoxModel<String> modelnamsx = new DefaultComboBoxModel<>();
                for (NamSX g : namSXs) {
                    modelnamsx.addElement(g.getTen());
                }
                cbb_NamSPCT.setModel(modelnamsx);

            } else if (rdo_loairuou.isSelected()) {
                ThuocTinhSanPham ttsp = thuocTinhSanPhamRepository.getPhanLoai().get(index);
                thuocTinhSanPhamRepository.updateLoaiRuou(getFormDataThuocTinhSP(), ttsp.getId());
                updatedList = thuocTinhSanPhamRepository.getPhanLoai();
                // Cập nhật ComboBox KichThuoc
                List<LoaiRuou> loai = LoaiRuouRepo.getAll();
                DefaultComboBoxModel<String> modelloai = new DefaultComboBoxModel<>();
                for (LoaiRuou g : loai) {
                    modelloai.addElement(g.getTen());
                }
                cbb_LoaiRuouSPCT.setModel(modelloai);

            } else if (rdo_XuatSu.isSelected()) {
                ThuocTinhSanPham ttsp = thuocTinhSanPhamRepository.getXuatXu().get(index);
                thuocTinhSanPhamRepository.updateXuatSu(getFormDataThuocTinhSP(), ttsp.getId());
                updatedList = thuocTinhSanPhamRepository.getXuatXu();
                // Cập nhật ComboBox CoAo
                List<XuatSu> xuatSus = XuatSuRepo.getAll();
                DefaultComboBoxModel<String> modelxs = new DefaultComboBoxModel<>();
                for (XuatSu g : xuatSus) {
                    modelxs.addElement(g.getTen());
                }
                cbb_XuatxuSPCT.setModel(modelxs);

            } else if (rdo_NCC.isSelected()) {
                ThuocTinhSanPham ttsp = thuocTinhSanPhamRepository.getNCC().get(index);
                thuocTinhSanPhamRepository.updateNCC(getFormDataThuocTinhSP(), ttsp.getId());
                updatedList = thuocTinhSanPhamRepository.getNCC();
                // Cập nhật ComboBox MauSac
                List<NhaCungCap> ncc = NCCRepo.getAll();
                DefaultComboBoxModel<String> modelNCC = new DefaultComboBoxModel<>();
                for (NhaCungCap g : ncc) {
                    modelNCC.addElement(g.getTen());
                }
                cbb_NCCSPCT.setModel(modelNCC);

            } else if (rdo_nongDo.isSelected()) {
                ThuocTinhSanPham ttsp = thuocTinhSanPhamRepository.getNongDo().get(index);
                thuocTinhSanPhamRepository.updateNongDo(getFormDataThuocTinhSP(), ttsp.getId());
                updatedList = thuocTinhSanPhamRepository.getNongDo();
                // Cập nhật ComboBox DoDay
                List<NongDo> nongdo = NongDoRepo.getAll();
                DefaultComboBoxModel<String> modelnongdo = new DefaultComboBoxModel<>();
                for (NongDo g : nongdo) {
                    modelnongdo.addElement(g.getTen());
                }
                cbb_NongDoSPCT.setModel(modelnongdo);

            } else if (rdo_DungTich.isSelected()) {
                ThuocTinhSanPham ttsp = thuocTinhSanPhamRepository.getDungTich().get(index);
                thuocTinhSanPhamRepository.updateDungTich(getFormDataThuocTinhSP(), ttsp.getId());
                updatedList = thuocTinhSanPhamRepository.getDungTich();
                // Cập nhật ComboBox TinhLinhHoat
                List<DungTich> dungtich = DungTichRepo.getAll();
                DefaultComboBoxModel<String> modelDungTich = new DefaultComboBoxModel<>();
                for (DungTich g : dungtich) {
                    modelDungTich.addElement(g.getTen());
                }
                cbb_DungTichSPCT.setModel(modelDungTich);

            }else {
                message = "Vui lòng chọn thuộc tính để sửa!";
                JOptionPane.showMessageDialog(null, message);
                return;
            }

            if (updatedList != null) {
                ShowTableThuocTinhSanPham(updatedList);
            }

            JOptionPane.showMessageDialog(null, message);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi sửa thuộc tính sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btn_suaActionPerformed

    private void btn_LamMoiThuocTinhSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiThuocTinhSanPhamActionPerformed
        // TODO add your handling code here:
        lblMaThuocTinh.setText("");
        txtTenThuocTinh.setText("");
//        this.ShowTableThuocTinhSanPham(thuocTinhSanPhamRepository.);
    }//GEN-LAST:event_btn_LamMoiThuocTinhSanPhamActionPerformed

    private void btn_ThemThuocTinhSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemThuocTinhSanPhamActionPerformed
        // TODO add your handling code here:
        ThuocTinhSanPham ttsp = getFormDataThuocTinhSP();
        if(ttsp == null){
            return ;
        }
        try{
            if(rdo_HangSX.isSelected()){
                if(HangSXRepo.isHangSanXuatExist(ttsp.getTenThuocTinhSanPham())){
                    JOptionPane.showMessageDialog(null, "Tên thuộc tính đã tồn tại", "Lỗi Nhập Liệu", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            thuocTinhSanPhamRepository.insertHangSX(ttsp);
            JOptionPane.showMessageDialog(null, "Thêm thuộc tính sản phẩm thành công");
            ShowTableThuocTinhSanPham(thuocTinhSanPhamRepository.getHangSX());
            // Cập nhật ComboBox HSX
            List<HangSX> hangsx = HangSXRepo.getAll();
            DefaultComboBoxModel<String> modelHangSX = new DefaultComboBoxModel<>();
            for (HangSX g : hangsx) {
                modelHangSX.addElement(g.getTen());// Thêm tên thuộc tính vào ComboBox
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi thêm thuộc tính sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_ThemThuocTinhSanPhamActionPerformed

    private void tbl_Thuoc_Tinh_San_PhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_Thuoc_Tinh_San_PhamMouseClicked
        // TODO add your handling code here:
        int index = tbl_Thuoc_Tinh_San_Pham.getSelectedRow();
        this.detailThuocTinhSanPham(index);
    }//GEN-LAST:event_tbl_Thuoc_Tinh_San_PhamMouseClicked

    private void rdo_NCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_NCCActionPerformed
        // TODO add your handling code here:
        ShowTableThuocTinhSanPham(thuocTinhSanPhamRepository.getNCC());
    }//GEN-LAST:event_rdo_NCCActionPerformed

    private void rdo_XuatSuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_XuatSuActionPerformed
        // TODO add your handling code here:
        ShowTableThuocTinhSanPham(thuocTinhSanPhamRepository.getXuatXu());
    }//GEN-LAST:event_rdo_XuatSuActionPerformed

    private void rdo_DungTichActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_DungTichActionPerformed
        // TODO add your handling code here:
        ShowTableThuocTinhSanPham(thuocTinhSanPhamRepository.getDungTich());
    }//GEN-LAST:event_rdo_DungTichActionPerformed

    private void rdo_nongDoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_nongDoActionPerformed
        // TODO add your handling code here:
        ShowTableThuocTinhSanPham(thuocTinhSanPhamRepository.getNongDo());
    }//GEN-LAST:event_rdo_nongDoActionPerformed

    private void rdo_loairuouActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_loairuouActionPerformed
        // TODO add your handling code here:
        ShowTableThuocTinhSanPham(thuocTinhSanPhamRepository.getPhanLoai());
    }//GEN-LAST:event_rdo_loairuouActionPerformed

    private void rdo_namsxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_namsxActionPerformed
        // TODO add your handling code here:
        ShowTableThuocTinhSanPham(thuocTinhSanPhamRepository.getNamSX());
    }//GEN-LAST:event_rdo_namsxActionPerformed

    private void rdo_HangSXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_HangSXActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tbl_Thuoc_Tinh_San_Pham.getModel();
        model.setRowCount(0);
        List<ThuocTinhSanPham> thuongHieuList = thuocTinhSanPhamRepository.getHangSX();
        for (ThuocTinhSanPham ttsp: thuongHieuList) {
            model.addRow(new Object[]{
                ttsp.getId(),
                ttsp.getMaThuocTinhSanPham(),
                ttsp.getTenThuocTinhSanPham(),
                });
        }
        tbl_Thuoc_Tinh_San_Pham.revalidate();
        tbl_Thuoc_Tinh_San_Pham.repaint();
    }//GEN-LAST:event_rdo_HangSXActionPerformed

    private void btn_XuatEXCEL1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XuatEXCEL1ActionPerformed
        // TODO add your handling code here:
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet spreadsheet = workbook.createSheet("Sản Phẩm Chi Tiết");

            // Tạo phong cách cho tiêu đề
            XSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.ALT_BARS.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.DASHED.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);

            // Thêm tiêu đề
            XSSFRow row = spreadsheet.createRow(2);
            row.setHeight((short) 500);
            XSSFCell cell = row.createCell(0);
            cell.setCellValue("DANH SÁCH SẢN PHẨM CHI TIẾT");
            cell.setCellStyle(headerStyle); // Áp dụng phong cách tiêu đề

            // Thêm hàng tiêu đề
            row = spreadsheet.createRow(3);
            row.setHeight((short) 500);
            String[] headers = {"STT", "MÃ SPCT", "Tên SP", "Hãng Sản Xuất", "Năm Sản Xuất", "Xuất Xứ", "Nồng Độ", "Loại Rượu", "Dung Tích", "Nhà Cung Cấp", "Giá Nhập", "GIÁ BÁN", "SỐ LƯỢNG", "TRẠNG THÁI"};
            for (int i = 0; i < headers.length; i++) {
                cell = row.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle); // Áp dụng phong cách tiêu đề
            }

            // Lấy dữ liệu từ repository
            Repository_DSSP sanPhamChiTietRepository = new Repository_DSSP();
            ArrayList<SanPhamChiTietRespone> listItem = sanPhamChiTietRepository.getAll();

            // Thêm dữ liệu vào bảng
            for (int i = 0; i < listItem.size(); i++) {
                SanPhamChiTietRespone spct = listItem.get(i);
                row = spreadsheet.createRow(4 + i);
                row.setHeight((short) 400);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(spct.getMaSPCT());
                row.createCell(2).setCellValue(spct.getTenSP());
                row.createCell(3).setCellValue(spct.getHangSX());
                row.createCell(4).setCellValue(spct.getNamSX());
                row.createCell(5).setCellValue(spct.getXuatXu());
                row.createCell(6).setCellValue(spct.getNongDo());
                row.createCell(7).setCellValue(spct.getPhanLoai());
                row.createCell(8).setCellValue(spct.getDungTich());
                row.createCell(9).setCellValue(spct.getNCC());
                row.createCell(10).setCellValue(spct.getGiaNhap());
                row.createCell(11).setCellValue(spct.getGiaBan());
                row.createCell(12).setCellValue(spct.getSoLuong());
                row.createCell(13).setCellValue(spct.isTrangThai() ? "Còn hàng" : "Hết hàng");
            }

            // Lưu file
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn vị trí và tên file để lưu");
            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                String filePath = fileToSave.getAbsolutePath();
                if (!filePath.endsWith(".xlsx")) {
                    filePath += ".xlsx";
                }

                try (FileOutputStream out = new FileOutputStream(filePath)) {
                    workbook.write(out);
                    JOptionPane.showMessageDialog(null, "Xuất file Excel thành công vào: " + filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Có lỗi khi ghi file.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_XuatEXCEL1ActionPerformed

    private void btn_Reset1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Reset1ActionPerformed
        // TODO add your handling code here:
        cbb_LocHang.setSelectedIndex(-1);
        cbb_LocNongDo.setSelectedIndex(-1);
        cbb_LocDungTich.setSelectedIndex(-1);
        cbb_Locgia.setSelectedIndex(-1);
        tenDungTich = "%";
        tenNongDo = "%";
        tenHangSX = "%";
        tenGiaBan = false;
        
        ShowTableSanPhamChiTiet(sanPhamChitietRepository.getAll());
    }//GEN-LAST:event_btn_Reset1ActionPerformed

    private void btn_TaiQRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TaiQRActionPerformed
        // TODO add your handling code here:
        if (selectedProductCode == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn mã muốn tải về máy!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JFileChooser jfc = new JFileChooser("D:\\");
        jfc.setDialogTitle("Save QR");
        int noiLuu = jfc.showSaveDialog(null);
        if (noiLuu == JFileChooser.APPROVE_OPTION) {
            String filePath = jfc.getSelectedFile().getAbsolutePath() + "_" + selectedProductCode + ".png";
            byte[] qrCodeImage = ZXingHelper.getQRCodeImage(selectedProductCode, 300, 300);
            if (qrCodeImage != null) {
                try (FileOutputStream fos = new FileOutputStream(filePath)) {
                    fos.write(qrCodeImage);
                    JOptionPane.showMessageDialog(null, "Lưu mã thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Lưu mã thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Tạo mã QR thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_TaiQRActionPerformed
    
    private void cbb_LocgiaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_LocgiaItemStateChanged
        // TODO add your handling code here:
        if(cbb_Locgia.getSelectedIndex() == 0){
            tenGiaBan = false;
            locSpctBanHang();
        }else{
            tenGiaBan = true;
            locSpctBanHang();
        }
    }//GEN-LAST:event_cbb_LocgiaItemStateChanged

    private void cbb_LocNongDoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_LocNongDoItemStateChanged
        // TODO add your handling code here:
        if(cbb_LocNongDo.getSelectedIndex() < 0){
            tenNongDo = "%";
            locSpctBanHang();
        }
        if(evt.getStateChange() == java.awt.event.ItemEvent.SELECTED){
            tenNongDo = cbb_LocNongDo.getSelectedItem().toString();
            locSpctBanHang();
        }
    }//GEN-LAST:event_cbb_LocNongDoItemStateChanged

    private void cbb_LocDungTichItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_LocDungTichItemStateChanged
        // TODO add your handling code here:
        if(cbb_LocDungTich.getSelectedIndex() < 0){
            tenDungTich = "%";
            locSpctBanHang();
        }
        if(evt.getStateChange() == java.awt.event.ItemEvent.SELECTED){
            tenDungTich = cbb_LocDungTich.getSelectedItem().toString();
            locSpctBanHang();
        }
    }//GEN-LAST:event_cbb_LocDungTichItemStateChanged

    private void cbb_LocHangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_LocHangItemStateChanged
        // TODO add your handling code here:
        if(cbb_LocHang.getSelectedIndex() < 0){
            tenHangSX = "%";
            locSpctBanHang();
        }
        if(evt.getStateChange() == java.awt.event.ItemEvent.SELECTED){
            tenHangSX = cbb_LocHang.getSelectedItem().toString();
            locSpctBanHang();
        }
    }//GEN-LAST:event_cbb_LocHangItemStateChanged

    private void btn_TimSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimSPCTActionPerformed
        // TODO add your handling code here:
        String maSPCT = txt_TimMaSPCT.getText().trim();
        ArrayList<SanPhamChiTietRespone> searchResult = sanPhamChitietRepository.searchh(maSPCT);
        ShowTableSanPhamChiTiet(searchResult);
    }//GEN-LAST:event_btn_TimSPCTActionPerformed

    private void tbl_SanPhamChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_SanPhamChiTietMouseClicked
        // TODO add your handling code here:
        int row = tbl_SanPhamChiTiet.getSelectedRow();
        if (row != -1) {
            selectedProductCode = (String) tbl_SanPhamChiTiet.getValueAt(row, 1); // Giả sử cột mã sản phẩm là cột thứ 2 (index 1)
        }
    }//GEN-LAST:event_tbl_SanPhamChiTietMouseClicked

    private void btn_lamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lamMoiActionPerformed
        // TODO add your handling code here:
        lbl_maSP.setText("");
        txt_tenSanPham.setText("");
        txt_mota.setText("");
        
        ShowTableSanPham(sanPhamRepository.getAllGiamDan());
    }//GEN-LAST:event_btn_lamMoiActionPerformed

    private void btn_ThemMoiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemMoiSPActionPerformed
        SanPham sanPham = getFormData();
        
        
        if(sanPham == null){
            return ;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thêm sản phẩm này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if(confirm == JOptionPane.YES_OPTION){
            try{
                sanPhamRepository.add(sanPham);
                JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công");
                ShowTableSanPham(sanPhamRepository.getAllGiamDan());

                List<SanPham> sp = sanPhamRepository.getAllGiamDan();
                DefaultComboBoxModel<String> modelSanPham = new DefaultComboBoxModel<>();

                for (SanPham g : sp) {
                    modelSanPham.addElement(g.getTenSanPham());// Thêm tên thuộc tính vào ComboBox
                }
                cbb_TenSPCT.setModel(modelSanPham);// Cập nhật model của ComboBox
            }catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi thêm sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_btn_ThemMoiSPActionPerformed

    private void rdo_ngungBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdo_ngungBanMouseClicked
        // TODO add your handling code here:
        ShowTableSanPham(sanPhamRepository.getSanPhamNgungBan());
    }//GEN-LAST:event_rdo_ngungBanMouseClicked

    private void rdo_DangBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_DangBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_DangBanActionPerformed

    private void rdo_DangBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdo_DangBanMouseClicked
        // TODO add your handling code here:
        ShowTableSanPham(sanPhamRepository.getSanPhamDangBan());
    }//GEN-LAST:event_rdo_DangBanMouseClicked

    private void rdo_tatcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdo_tatcaMouseClicked
        // TODO add your handling code here:
        ShowTableSanPham(sanPhamRepository.getAllGiamDan());
    }//GEN-LAST:event_rdo_tatcaMouseClicked

    private void btn_timkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timkiemActionPerformed
        // TODO add your handling code here:
        String maSp = txt_TimMaSP.getText().trim();
        ArrayList<SanPham> searchResult = sanPhamRepository.search(maSp);
        ShowTableSanPham(searchResult);

    }//GEN-LAST:event_btn_timkiemActionPerformed

    private void txt_GiaBanSPCT1txt_HangSX3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_GiaBanSPCT1txt_HangSX3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_GiaBanSPCT1txt_HangSX3ActionPerformed

    private void txt_GiaNhapSPCTtxt_tenSP3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_GiaNhapSPCTtxt_tenSP3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_GiaNhapSPCTtxt_tenSP3ActionPerformed

    private void btn_ThemSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemSPCTActionPerformed
        SanPhamChiTietRespone spctrp = getFormDataSanPhamCT();
        if(spctrp == null){
            return ;
        }
        int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thêm sản phẩm này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                SanPhamChiTiet spct = convertResponeToEntity(spctrp);
                sanPhamChitietRepository.add(spct);
                JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công");
                ShowTableSanPhamChiTiet(sanPhamChitietRepository.getAllGiamDan());
                ShowTableSanPham(sanPhamRepository.getAllGiamDan());
                
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi thêm sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_ThemSPCTActionPerformed

    private void rdo_tatcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_tatcaActionPerformed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_rdo_tatcaActionPerformed

    private void btn_ThemNhanhHangSXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemNhanhHangSXActionPerformed
        // TODO add your handling code here:
        ThemHangSX v = new ThemHangSX();
        v.setVisible(true);
        v.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {

                List<HangSX> hangSX = HangSXRepo.getAll();
                DefaultComboBoxModel<String> modelHangSX = new DefaultComboBoxModel<>();
                for (HangSX g : hangSX) {
                    modelHangSX.addElement(g.getTen());
                }
                cbb_HangSPCT.setModel(modelHangSX); // Cập nhật model của ComboBox
                cbb_LocHang.setModel(modelHangSX);
            }
        });
    }//GEN-LAST:event_btn_ThemNhanhHangSXActionPerformed

    private void cbb_NamSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_NamSPCTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbb_NamSPCTActionPerformed

    private void btn_ThemNhanhNamSXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemNhanhNamSXActionPerformed
        // TODO add your handling code here:
        ThemNamSX v = new ThemNamSX();
        v.setVisible(true);
        v.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {

                List<NamSX> NamSX = NamSXRepo.getAll();
                DefaultComboBoxModel<String> modelNamSX = new DefaultComboBoxModel<>();
                for (NamSX g : NamSX) {
                    modelNamSX.addElement(g.getTen());
                }
                cbb_NamSPCT.setModel(modelNamSX); // Cập nhật model của ComboBox
            }
        });
    }//GEN-LAST:event_btn_ThemNhanhNamSXActionPerformed

    private void btn_ThemNhanhXuatXuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemNhanhXuatXuActionPerformed
        // TODO add your handling code here:
        ThemSuatXu v = new ThemSuatXu();
        v.setVisible(true);
        v.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {

                List<XuatSu> xuatSu = XuatSuRepo.getAll();
                DefaultComboBoxModel<String> modelXuatSu = new DefaultComboBoxModel<>();
                for (XuatSu g : xuatSu) {
                    modelXuatSu.addElement(g.getTen());
                }
                cbb_XuatxuSPCT.setModel(modelXuatSu); // Cập nhật model của ComboBox
            }
        });
    }//GEN-LAST:event_btn_ThemNhanhXuatXuActionPerformed

    private void btn_ThemNhanhLoaiRuouActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemNhanhLoaiRuouActionPerformed
        // TODO add your handling code here:
        ThemPhanLoai v = new ThemPhanLoai();
        v.setVisible(true);
        v.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {

                List<LoaiRuou> PhanLoai = LoaiRuouRepo.getAll();
                DefaultComboBoxModel<String> modelphanLoai = new DefaultComboBoxModel<>();
                for (LoaiRuou g : PhanLoai) {
                    modelphanLoai.addElement(g.getTen());
                }
                cbb_LoaiRuouSPCT.setModel(modelphanLoai); // Cập nhật model của ComboBox
            }
        });
    }//GEN-LAST:event_btn_ThemNhanhLoaiRuouActionPerformed

    private void btn_ThemNhanhNongDoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemNhanhNongDoActionPerformed
        // TODO add your handling code here:
        ThemNongDo v = new ThemNongDo();
        v.setVisible(true);
        v.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {

                List<NongDo> nongDo = NongDoRepo.getAll();
                DefaultComboBoxModel<String> modelnongdo = new DefaultComboBoxModel<>();
                for (NongDo g : nongDo) {
                     modelnongdo.addElement(g.getTen());
                }
                cbb_NongDoSPCT.setModel(modelnongdo); // Cập nhật model của ComboBox
                cbb_LocNongDo.setModel(modelnongdo);
            }
        });
    }//GEN-LAST:event_btn_ThemNhanhNongDoActionPerformed

    private void btn_ThemNhanhDungTichActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemNhanhDungTichActionPerformed
        // TODO add your handling code here:
        ThemDungTich v = new ThemDungTich();
        v.setVisible(true);
        v.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {

                List<DungTich> DungTich = DungTichRepo.getAll();
                DefaultComboBoxModel<String> modelDungTIch = new DefaultComboBoxModel<>();
                for (DungTich g : DungTich) {
                    modelDungTIch.addElement(g.getTen());
                }
                cbb_DungTichSPCT.setModel(modelDungTIch); // Cập nhật model của ComboBox
                cbb_LocDungTich.setModel(modelDungTIch);
            }
        });
    }//GEN-LAST:event_btn_ThemNhanhDungTichActionPerformed

    private void tbl_sanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_sanPhamMouseClicked
        // TODO add your handling code here:
        int row = tbl_sanPham.getSelectedRow();
        this.showDataSanPham(row);
    }//GEN-LAST:event_tbl_sanPhamMouseClicked

    private void rdo_ngungBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_ngungBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_ngungBanActionPerformed

    private void btn_XoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaSPActionPerformed
        // TODO add your handling code here:
        int index = tbl_sanPham.getSelectedRow();
        SanPhamChiTietRespone spctrp;

        // Kiểm tra nếu không có dòng nào được chọn
        if (index == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm để Ẩn.");
            return;
        }
        // Hiển thị hộp thoại xác nhận
        int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn ẩn sản phẩm này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // Lấy sản phẩm từ chỉ số dòng
                Integer id_sp = (Integer) tbl_sanPham.getValueAt(index, 0);
                SanPham sp = sanPhamRepository.getAllGiamDan().get(index);

                //                ArrayList<SanPhamChiTiet> spct = convertResponeToEntity1(spctrp);
                //
                // Xóa sản phẩm khỏi cơ sở dữ liệu
                sanPhamRepository.hide(id_sp);
                sanPhamChitietRepository.HideSanPhamChiTiet(id_sp);

                // Xóa dòng khỏi DefaultTableModel
                dtmSanPham.removeRow(index);

                // Cập nhật bảng với dữ liệu mới
                ShowTableSanPham(sanPhamRepository.getAllGiamDan());
                ShowTableSanPhamChiTiet(sanPhamChitietRepository.getAllGiamDan());

                JOptionPane.showMessageDialog(null, "Ẩn sản phẩm thành công.");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi ẩn sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ẩn sản phẩm đã bị hủy.");
        }
    }//GEN-LAST:event_btn_XoaSPActionPerformed
    
    private void locSpctBanHang() {
        ArrayList<SanPhamChiTietRespone> list = sanPhamChitietRepository.locTheoDieuKienBanHang(tenDungTich, tenHangSX, tenNongDo, tenGiaBan);
        ShowTableSanPhamChiTiet(list);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane SanPhamChiTietJPanel;
    private com.Product.swing.ButtonBadges btn_LamMoiThuocTinhSanPham;
    private com.Product.swing.ButtonBadges btn_Reset1;
    private com.Product.swing.ButtonBadges btn_TaiQR;
    private com.Product.swing.ButtonBadges btn_ThemMoiSP;
    private View.swing.ButtonGradient btn_ThemNhanhDungTich;
    private View.swing.ButtonGradient btn_ThemNhanhHangSX;
    private View.swing.ButtonGradient btn_ThemNhanhLoaiRuou;
    private View.swing.ButtonGradient btn_ThemNhanhNamSX;
    private View.swing.ButtonGradient btn_ThemNhanhNongDo;
    private View.swing.ButtonGradient btn_ThemNhanhXuatXu;
    private com.Product.swing.ButtonBadges btn_ThemSPCT;
    private com.Product.swing.ButtonBadges btn_ThemThuocTinhSanPham;
    private javax.swing.JButton btn_TimSPCT;
    private com.Product.swing.ButtonBadges btn_XoaSP;
    private com.Product.swing.ButtonBadges btn_XuatEXCEL1;
    private com.Product.swing.ButtonBadges btn_lamMoi;
    private com.Product.swing.ButtonBadges btn_sua;
    private javax.swing.JButton btn_timkiem;
    private com.Product.swing.ButtonBadges btn_xoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbb_DungTichSPCT;
    private javax.swing.JComboBox<String> cbb_HangSPCT;
    private javax.swing.JComboBox<String> cbb_LoaiRuouSPCT;
    private javax.swing.JComboBox<String> cbb_LocDungTich;
    private javax.swing.JComboBox<String> cbb_LocHang;
    private javax.swing.JComboBox<String> cbb_LocNongDo;
    private javax.swing.JComboBox<String> cbb_Locgia;
    private javax.swing.JComboBox<String> cbb_NCCSPCT;
    private javax.swing.JComboBox<String> cbb_NamSPCT;
    private javax.swing.JComboBox<String> cbb_NongDoSPCT;
    private javax.swing.JComboBox<String> cbb_TenSPCT;
    private javax.swing.JComboBox<String> cbb_XuatxuSPCT;
    private javax.swing.ButtonGroup group_DungTich;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel jpn_DanhSach;
    private javax.swing.JPanel jpn_sanPhamChiTiet;
    private javax.swing.JLabel lblMaThuocTinh;
    private javax.swing.JLabel lbl_maSP;
    private View.swing.PaneBorder paneBorder3;
    private javax.swing.JRadioButton rdo_DangBan;
    private javax.swing.JRadioButton rdo_DungTich;
    private javax.swing.JRadioButton rdo_HangSX;
    private javax.swing.JRadioButton rdo_NCC;
    private javax.swing.JRadioButton rdo_XuatSu;
    private javax.swing.JRadioButton rdo_loairuou;
    private javax.swing.JRadioButton rdo_namsx;
    private javax.swing.JRadioButton rdo_ngungBan;
    private javax.swing.JRadioButton rdo_nongDo;
    private javax.swing.JRadioButton rdo_tatca;
    private View.swing.Table tbl_SanPhamChiTiet;
    private View.swing.Table tbl_Thuoc_Tinh_San_Pham;
    private View.swing.Table tbl_sanPham;
    private javax.swing.JTextField txtTenThuocTinh;
    private javax.swing.JTextField txt_GiaBanSPCT1;
    private javax.swing.JTextField txt_GiaNhapSPCT;
    private javax.swing.JTextField txt_SoLuongSPCT;
    private javax.swing.JTextField txt_TimMaSP;
    private javax.swing.JTextField txt_TimMaSPCT;
    private javax.swing.JTextArea txt_mota;
    private javax.swing.JTextField txt_tenSanPham;
    // End of variables declaration//GEN-END:variables
}
