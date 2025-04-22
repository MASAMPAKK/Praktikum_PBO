
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

// Abstract Superclass
abstract class JadwalKereta {
    private final int id;
    private String namaKereta;
    private String rute;
    private String tanggal;
    private String jamKeberangkatan;
    private final double harga;
    private int ketersediaanKursi;

    public JadwalKereta(int id, String namaKereta, String rute, String tanggal, String jamKeberangkatan, double harga, int ketersediaanKursi) {
        this.id = id;
        this.namaKereta = namaKereta;
        this.rute = rute;
        this.tanggal = tanggal;
        this.jamKeberangkatan = jamKeberangkatan;
        this.harga = harga;
        this.ketersediaanKursi = ketersediaanKursi;
    }

    public int getId() { return id; }
    public String getNamaKereta() { return namaKereta; }
    public void setNamaKereta(String namaKereta) { this.namaKereta = namaKereta; }
    public String getRute() { return rute; }
    public void setRute(String rute) { this.rute = rute; }
    public String getTanggal() { return tanggal; }
    public void setTanggal(String tanggal) { this.tanggal = tanggal; }
    public String getJamKeberangkatan() { return jamKeberangkatan; }
    public void setJamKeberangkatan(String jamKeberangkatan) { this.jamKeberangkatan = jamKeberangkatan; }
    public double getHarga() { return harga; }
    public int getKetersediaanKursi() { return ketersediaanKursi; }
    public void setKetersediaanKursi(int ketersediaanKursi) { this.ketersediaanKursi = ketersediaanKursi; }

    public abstract void display(); // Abstract method
}

// Subclass 1
final class JadwalEkonomi extends JadwalKereta {
    public JadwalEkonomi(int id, String nama, String rute, String tanggal, String jam, double harga, int kursi) {
        super(id, nama, rute, tanggal, jam, harga, kursi);
    }

    @Override
    public final void display() {
        System.out.print("[EKONOMI] ");
        System.out.println("ID: " + getId() + " | Kereta: " + getNamaKereta() + " | Rute: " + getRute() +
                " | Tanggal: " + getTanggal() + " | Jam: " + getJamKeberangkatan() +
                " | Harga: " + getHarga() + " | Kursi: " + getKetersediaanKursi());
    }
}

// Subclass 2
class JadwalBisnis extends JadwalKereta {
    public JadwalBisnis(int id, String nama, String rute, String tanggal, String jam, double harga, int kursi) {
        super(id, nama, rute, tanggal, jam, harga, kursi);
    }

    @Override
    public void display() {
        System.out.print("[BISNIS] ");
        System.out.println("ID: " + getId() + " | Kereta: " + getNamaKereta() + " | Rute: " + getRute() +
                " | Tanggal: " + getTanggal() + " | Jam: " + getJamKeberangkatan() +
                " | Harga: " + getHarga() + " | Kursi: " + getKetersediaanKursi());
    }
}

// Subclass 3
class JadwalEksekutif extends JadwalKereta {
    public JadwalEksekutif(int id, String nama, String rute, String tanggal, String jam, double harga, int kursi) {
        super(id, nama, rute, tanggal, jam, harga, kursi);
    }

    @Override
    public void display() {
        System.out.print("[EKSEKUTIF] ");
        System.out.println("ID: " + getId() + " | Kereta: " + getNamaKereta() + " | Rute: " + getRute() +
                " | Tanggal: " + getTanggal() + " | Jam: " + getJamKeberangkatan() +
                " | Harga: " + getHarga() + " | Kursi: " + getKetersediaanKursi());
    }
}

// Main Class
public class KeretaApiCRUD {
    private static final ArrayList<JadwalKereta> jadwalList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static int idCounter = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Menu Kereta Api =====");
            System.out.println("1. Tambah Jadwal");
            System.out.println("2. Lihat Jadwal");
            System.out.println("3. Update Jadwal");
            System.out.println("4. Hapus Jadwal");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            int choice = getValidInt();

            switch (choice) {
                case 1 -> tambahJadwal();
                case 2 -> lihatJadwal();
                case 3 -> updateJadwal();
                case 4 -> hapusJadwal();
                case 5 -> {
                    System.out.println("Keluar dari program...");
                    return;
                }
                default -> System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private static void tambahJadwal() {
        tambahJadwal(100);
    }

    private static void tambahJadwal(int defaultKursi) {
        System.out.print("Nama Kereta: ");
        String namaKereta = scanner.nextLine();

        System.out.print("Rute: ");
        String rute = scanner.nextLine();

        String tanggal;
        do {
            System.out.print("Tanggal (yyyy-mm-dd): ");
            tanggal = scanner.nextLine();
        } while (!isValidDate(tanggal));

        String jam;
        do {
            System.out.print("Jam Keberangkatan (hh:mm): ");
            jam = scanner.nextLine();
        } while (!isValidTime(jam));

        System.out.print("Harga: ");
        double harga = getValidDouble();

        System.out.print("Ketersediaan Kursi (isi 0 jika ingin default 100): ");
        int kursi = getValidInt();
        if (kursi == 0) kursi = defaultKursi;

        System.out.print("Pilih Kelas [1.Ekonomi | 2.Bisnis | 3.Eksekutif]: ");
        int kelas = getValidInt();

        JadwalKereta jadwal;
        switch (kelas) {
            case 1 -> jadwal = new JadwalEkonomi(idCounter++, namaKereta, rute, tanggal, jam, harga, kursi);
            case 2 -> jadwal = new JadwalBisnis(idCounter++, namaKereta, rute, tanggal, jam, harga, kursi);
            case 3 -> jadwal = new JadwalEksekutif(idCounter++, namaKereta, rute, tanggal, jam, harga, kursi);
            default -> {
                System.out.println("Kelas tidak valid. Jadwal tidak ditambahkan.");
                return;
            }
        }

        jadwalList.add(jadwal);
        System.out.println("Jadwal berhasil ditambahkan!");
    }

    private static void lihatJadwal() {
        if (jadwalList.isEmpty()) {
            System.out.println("Tidak ada jadwal tersedia.");
        } else {
            System.out.println("\n===== Jadwal Kereta Api =====");
            for (JadwalKereta jadwal : jadwalList) {
                jadwal.display();
            }
        }
    }

    private static void updateJadwal() {
        System.out.print("Masukkan ID Jadwal yang ingin diupdate: ");
        int id = getValidInt();

        for (JadwalKereta jadwal : jadwalList) {
            if (jadwal.getId() == id) {
                System.out.print("Nama Kereta Baru: ");
                jadwal.setNamaKereta(scanner.nextLine());

                System.out.print("Rute Baru: ");
                jadwal.setRute(scanner.nextLine());

                String tanggal;
                do {
                    System.out.print("Tanggal Baru (yyyy-mm-dd): ");
                    tanggal = scanner.nextLine();
                } while (!isValidDate(tanggal));
                jadwal.setTanggal(tanggal);

                String jam;
                do {
                    System.out.print("Jam Keberangkatan Baru (hh:mm): ");
                    jam = scanner.nextLine();
                } while (!isValidTime(jam));
                jadwal.setJamKeberangkatan(jam);

                System.out.print("Ketersediaan Kursi Baru: ");
                jadwal.setKetersediaanKursi(getValidInt());

                System.out.println("Jadwal berhasil diperbarui!");
                return;
            }
        }
        System.out.println("Jadwal tidak ditemukan!");
    }

    private static void hapusJadwal() {
        System.out.print("Masukkan ID Jadwal yang ingin dihapus: ");
        int id = getValidInt();

        for (JadwalKereta jadwal : jadwalList) {
            if (jadwal.getId() == id) {
                jadwalList.remove(jadwal);
                System.out.println("Jadwal berhasil dihapus!");
                return;
            }
        }
        System.out.println("Jadwal tidak ditemukan!");
    }

    private static int getValidInt() {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input < 0) throw new NumberFormatException();
                return input;
            } catch (NumberFormatException e) {
                System.out.print("Masukkan angka yang valid: ");
            }
        }
    }

    private static double getValidDouble() {
        while (true) {
            try {
                double input = Double.parseDouble(scanner.nextLine());
                if (input < 0) throw new NumberFormatException();
                return input;
            } catch (NumberFormatException e) {
                System.out.print("Masukkan harga yang valid: ");
            }
        }
    }

    private static boolean isValidDate(String date) {
        return Pattern.matches("\\d{4}-\\d{2}-\\d{2}", date);
    }

    private static boolean isValidTime(String time) {
        return Pattern.matches("\\d{2}:\\d{2}", time);
    }
}
