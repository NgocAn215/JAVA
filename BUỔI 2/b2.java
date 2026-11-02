public class b2 {
    public static void main(String[] args) {
        HinhTron ht1 = new HinhTron(1, 2, 3.5);
        HinhTron ht2 = new HinhTron();

        System.out.println(ht1);
        System.out.println(ht2);

        if (ht1.dienTich() > ht2.dienTich()) {
            System.out.println("Diện tích hình tròn 1 lớn hơn hình tròn 2.");
        } else if (ht1.dienTich() < ht2.dienTich()) {
            System.out.println("Diện tích hình tròn 2 lớn hơn hình tròn 1.");
        } else {
            System.out.println("Diện tích hai hình tròn bằng nhau.");
        }
    }
}

class HinhTron {
    private double x;
    private double y;
    private double banKinh;

    public HinhTron() {
        this(0, 0, 0);
    }

    public HinhTron(double x, double y, double banKinh) {
        if (banKinh < 0) {
            throw new IllegalArgumentException("Bán kính không được âm.");
        }
        this.x = x;
        this.y = y;
        this.banKinh = banKinh;
    }

    public double dienTich() {
        return Math.PI * banKinh * banKinh;
    }

    @Override
    public String toString() {
        return String.format("Hình tròn tâm (%.2f, %.2f), bán kính %.2f, diện tích %.2f",
                x, y, banKinh, dienTich());
    }
}
