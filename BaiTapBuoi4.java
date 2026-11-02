import java.util.ArrayList;
import java.util.List;

public class BaiTapBuoi4 {
    public static void main(String[] args) {
        List<Diem2D> danhSach = new ArrayList<>();

        danhSach.add(new Diem2D(1, 2));
        danhSach.add(new Diem3D(2, 3, 4));
        danhSach.add(new Diem2D(-3, 4));
        danhSach.add(new Diem3D(-1, 2, 5));

        // Thêm điểm đối xứng của từng điểm vào danh sách.
        int soDiemBanDau = danhSach.size();
        for (int i = 0; i < soDiemBanDau; i++) {
            danhSach.add(danhSach.get(i).doiXungQuaGocToaDo());
        }

        double tongKhoangCach2D = 0;
        double tongKhoangCach3D = 0;
        Diem2D diem2DTruoc = null;
        Diem3D diem3DTruoc = null;

        for (Diem2D diem : danhSach) {
            if (diem instanceof Diem3D) {
                Diem3D diem3D = (Diem3D) diem;
                if (diem3DTruoc != null) {
                    tongKhoangCach3D += diem3DTruoc.khoangCach(diem3D);
                }
                diem3DTruoc = diem3D;
            } else {
                if (diem2DTruoc != null) {
                    tongKhoangCach2D += diem2DTruoc.khoangCach(diem);
                }
                diem2DTruoc = diem;
            }
        }

        System.out.println("Danh sach cac diem (bao gom diem doi xung):");
        for (Diem2D diem : danhSach) {
            System.out.println("  " + diem);
        }
        System.out.printf("Tong khoang cach cac diem 2D: %.2f%n", tongKhoangCach2D);
        System.out.printf("Tong khoang cach cac diem 3D: %.2f%n", tongKhoangCach3D);
    }
}

class Diem2D {
    private double x;
    private double y;

    public Diem2D() {
        this(0, 0);
    }

    public Diem2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double khoangCach(Diem2D diemKhac) {
        if (diemKhac == null) {
            throw new IllegalArgumentException("Diem khac khong duoc null.");
        }
        return Math.hypot(x - diemKhac.x, y - diemKhac.y);
    }

    public Diem2D doiXungQuaGocToaDo() {
        return new Diem2D(-x, -y);
    }

    public void tinhTien(double deltaX, double deltaY) {
        x += deltaX;
        y += deltaY;
    }

    @Override
    public String toString() {
        return String.format("Diem2D(%.2f, %.2f)", x, y);
    }
}

class Diem3D extends Diem2D {
    private double z;

    public Diem3D() {
        this(0, 0, 0);
    }

    public Diem3D(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }

    public double getZ() {
        return z;
    }

    public double khoangCach(Diem3D diemKhac) {
        if (diemKhac == null) {
            throw new IllegalArgumentException("Diem khac khong duoc null.");
        }
        double dx = getX() - diemKhac.getX();
        double dy = getY() - diemKhac.getY();
        double dz = z - diemKhac.z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    @Override
    public Diem3D doiXungQuaGocToaDo() {
        return new Diem3D(-getX(), -getY(), -z);
    }

    public void tinhTien(double deltaX, double deltaY, double deltaZ) {
        super.tinhTien(deltaX, deltaY);
        z += deltaZ;
    }

    @Override
    public String toString() {
        return String.format("Diem3D(%.2f, %.2f, %.2f)", getX(), getY(), z);
    }
}
