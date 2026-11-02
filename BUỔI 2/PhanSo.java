import java.util.Objects;

/** Biểu diễn một phân số có tử số và mẫu số nguyên. */
public class PhanSo implements Comparable<PhanSo> {
    private int tuSo;
    private int mauSo;

    public PhanSo() {
        this(0, 1);
    }

    public PhanSo(int tuSo, int mauSo) {
        setMauSo(mauSo);
        this.tuSo = tuSo;
    }

    public int getTuSo() {
        return tuSo;
    }

    public void setTuSo(int tuSo) {
        this.tuSo = tuSo;
    }

    public int getMauSo() {
        return mauSo;
    }

    public void setMauSo(int mauSo) {
        if (mauSo == 0) {
            throw new IllegalArgumentException("Mẫu số phải khác 0.");
        }
        this.mauSo = mauSo;
    }

    public PhanSo cong(PhanSo other) {
        Objects.requireNonNull(other, "Phân số không được null.");
        return new PhanSo(
                Math.toIntExact((long) tuSo * other.mauSo + (long) other.tuSo * mauSo),
                Math.toIntExact((long) mauSo * other.mauSo));
    }

    public PhanSo tru(PhanSo other) {
        Objects.requireNonNull(other, "Phân số không được null.");
        return new PhanSo(
                Math.toIntExact((long) tuSo * other.mauSo - (long) other.tuSo * mauSo),
                Math.toIntExact((long) mauSo * other.mauSo));
    }

    public PhanSo nhan(PhanSo other) {
        Objects.requireNonNull(other, "Phân số không được null.");
        return new PhanSo(Math.toIntExact((long) tuSo * other.tuSo),
                Math.toIntExact((long) mauSo * other.mauSo));
    }

    public PhanSo chia(PhanSo other) {
        Objects.requireNonNull(other, "Phân số không được null.");
        if (other.tuSo == 0) {
            throw new ArithmeticException("Không thể chia cho phân số 0.");
        }
        return new PhanSo(Math.toIntExact((long) tuSo * other.mauSo),
                Math.toIntExact((long) mauSo * other.tuSo));
    }

    /** Trả về phân số tương đương đã rút gọn. */
    public PhanSo rutGon() {
        long tu = tuSo;
        long mau = mauSo;
        if (mau < 0) {
            tu = -tu;
            mau = -mau;
        }
        long ucln = ucln(Math.abs(tu), mau);
        return new PhanSo(Math.toIntExact(tu / ucln), Math.toIntExact(mau / ucln));
    }

    private static long ucln(long a, long b) {
        while (b != 0) {
            long tam = a % b;
            a = b;
            b = tam;
        }
        return a == 0 ? 1 : a;
    }

    /** Trả về số âm, 0 hoặc số dương khi phân số này nhỏ hơn, bằng hoặc lớn hơn other. */
    @Override
    public int compareTo(PhanSo other) {
        Objects.requireNonNull(other, "Phân số không được null.");
        long trai = (long) tuSo * other.mauSo;
        long phai = (long) other.tuSo * mauSo;
        // Điều chỉnh dấu mẫu để so sánh đúng cả khi mẫu âm.
        return mauSo * (long) other.mauSo >= 0
                ? Long.compare(trai, phai)
                : Long.compare(phai, trai);
    }

    @Override
    public String toString() {
        PhanSo toiGian = rutGon();
        return toiGian.mauSo == 1
                ? Integer.toString(toiGian.tuSo)
                : toiGian.tuSo + "/" + toiGian.mauSo;
    }
}
