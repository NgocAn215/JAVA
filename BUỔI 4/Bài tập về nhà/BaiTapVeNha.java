import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class BaiTapVeNha {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        do {
            System.out.print("Nhap so nhan vien (n > 0): ");
            n = Integer.parseInt(scanner.nextLine().trim());
        } while (n <= 0);

        List<NhanVien> danhSach = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.println("\nNhap nhan vien thu " + (i + 1) + ":");
            NhanVien nhanVien = new NhanVien();
            nhanVien.nhap(scanner);
            danhSach.add(nhanVien);
        }

        NhanVien thamNienCaoNhat = danhSach.stream()
                .max(Comparator.comparingInt(NhanVien::getSoNamCongTac))
                .orElseThrow();

        System.out.println("\nNhan vien co so nam cong tac nhieu nhat:");
        thamNienCaoNhat.xuat();

        danhSach.sort(Comparator.comparingDouble(NhanVien::tinhTienLuong).reversed());
        System.out.println("\nDanh sach nhan vien theo luong tu cao den thap:");
        for (NhanVien nhanVien : danhSach) {
            nhanVien.xuat();
        }
    }
}

class Nguoi {
    private String hoTen;
    private int tuoi;

    public Nguoi() {
        this("", 0);
    }

    public Nguoi(String hoTen, int tuoi) {
        this.hoTen = hoTen;
        this.tuoi = tuoi;
    }

    public String getHoTen() {
        return hoTen;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void nhap(Scanner scanner) {
        System.out.print("Ho ten: ");
        hoTen = scanner.nextLine().trim();
        System.out.print("Tuoi: ");
        tuoi = Integer.parseInt(scanner.nextLine().trim());
    }

    public void xuat() {
        System.out.printf("Ho ten: %s | Tuoi: %d", hoTen, tuoi);
    }
}

class NhanVien extends Nguoi {
    private int soNamCongTac;
    private double heSoLuong;

    private static final double LUONG_CO_BAN = 1_500_000;
    private static final double PHU_CAP_MOI_NAM = 200_000;

    public NhanVien() {
        this("", 0, 0, 0);
    }

    public NhanVien(String hoTen, int tuoi, int soNamCongTac, double heSoLuong) {
        super(hoTen, tuoi);
        this.soNamCongTac = soNamCongTac;
        this.heSoLuong = heSoLuong;
    }

    public int getSoNamCongTac() {
        return soNamCongTac;
    }

    public double tinhTienLuong() {
        return LUONG_CO_BAN * heSoLuong + PHU_CAP_MOI_NAM * soNamCongTac;
    }

    @Override
    public void nhap(Scanner scanner) {
        super.nhap(scanner);
        System.out.print("So nam cong tac: ");
        soNamCongTac = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("He so luong: ");
        heSoLuong = Double.parseDouble(scanner.nextLine().trim());
    }

    @Override
    public void xuat() {
        super.xuat();
        System.out.printf(" | Nam cong tac: %d | He so luong: %.2f | Luong: %.0f dong%n",
                soNamCongTac, heSoLuong, tinhTienLuong());
    }
}