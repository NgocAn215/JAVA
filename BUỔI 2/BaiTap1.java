import java.util.Arrays;
import java.util.List;

public class BaiTap1 {
    public static void main(String[] args) {
        List<PhanSo> cacPhanSo = Arrays.asList(
                new PhanSo(1, 2),
                new PhanSo(3, 4),
                new PhanSo(6, 8),
                new PhanSo(5, 2),
                new PhanSo(7, 3)
        );

        PhanSo lonNhat = cacPhanSo.get(0);
        PhanSo tong = new PhanSo();
        int soPhanSoKhongNguyen = 0;

        for (PhanSo phanSo : cacPhanSo) {
            if (phanSo.compareTo(lonNhat) > 0) {
                lonNhat = phanSo;
            }
            tong = tong.cong(phanSo);
            if (phanSo.getTuSo() % phanSo.getMauSo() != 0) {
                soPhanSoKhongNguyen++;
            }
        }

        System.out.println("Danh sách phân số: " + cacPhanSo);
        System.out.println("Phân số lớn nhất: " + lonNhat);
        System.out.println("Tổng các phân số: " + tong.rutGon());
        System.out.println("Số phân số không phải số nguyên: " + soPhanSoKhongNguyen);
    }
}
