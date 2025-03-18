import java.util.ArrayList;
import java.util.Scanner;

class JadwalKereta {
    int id;
    String namaKereta;
    String rute;
    String tanggal;
    String jamKeberangkatan;
    double harga;
    int ketersediaanKursi;

    public JadwalKereta(int id, String namaKereta, String rute, String tanggal, String jamKeberangkatan, double harga, int ketersediaanKursi) {
        this.id = id;
        this.namaKereta = namaKereta;
        this.rute = rute;
        this.tanggal = tanggal;
        this.jamKeberangkatan = jamKeberangkatan;
        this.harga = harga;
        this.ketersediaanKursi = ketersediaanKursi;
    }

    public void display() {
        System.out.println("ID: " + id + " | Kereta: " + namaKereta + " | Rute: " + rute + " | Tanggal: " + tanggal + " | Jam: " + jamKeberangkatan + " | Harga: " + harga + " | Kursi: " + ketersediaanKursi);
    }
}

public class KeretaApiCRUD {
    static ArrayList<JadwalKereta> jadwalList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static int idCounter = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Menu Kereta Api =====");
            System.out.println("1. Tambah Jadwal");
            System.out.println("2. Lihat Jadwal");
            System.out.println("3. Update Jadwal");
            System.out.println("4. Hapus Jadwal");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: tambahJadwal(); break;
                case 2: lihatJadwal(); break;
                case 3: updateJadwal(); break;
                case 4: hapusJadwal(); break;
                case 5: System.out.println("Keluar dari program..."); return;
                default: System.out.println("Pilihan tidak valid!");
            }
        }
    }

    static void tambahJadwal() {
        System.out.print("Nama Kereta: ");
        String namaKereta = scanner.nextLine();
        System.out.print("Rute: ");
        String rute = scanner.nextLine();
        System.out.print("Tanggal (yyyy-mm-dd): ");
        String tanggal = scanner.nextLine();
        System.out.print("Jam Keberangkatan (hh:mm): ");
        String jamKeberangkatan = scanner.nextLine();
        System.out.print("Harga: ");
        double harga = scanner.nextDouble();
        System.out.print("Ketersediaan Kursi: ");
        int kursi = scanner.nextInt();
        scanner.nextLine();

        jadwalList.add(new JadwalKereta(idCounter++, namaKereta, rute, tanggal, jamKeberangkatan, harga, kursi));
        System.out.println("Jadwal berhasil ditambahkan!");
    }

    static void lihatJadwal() {
        if (jadwalList.isEmpty()) {
            System.out.println("Tidak ada jadwal tersedia.");
        } else {
            System.out.println("\n===== Jadwal Kereta Api =====");
            for (JadwalKereta jadwal : jadwalList) {
                jadwal.display();
            }
        }
    }

    static void updateJadwal() {
        System.out.print("Masukkan ID Jadwal yang ingin diupdate: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        for (JadwalKereta jadwal : jadwalList) {
            if (jadwal.id == id) {
                System.out.print("Nama Kereta Baru: ");
                jadwal.namaKereta = scanner.nextLine();
                System.out.print("Rute Baru: ");
                jadwal.rute = scanner.nextLine();
                System.out.print("Tanggal Baru: ");
                jadwal.tanggal = scanner.nextLine();
                System.out.print("Jam Keberangkatan Baru: ");
                jadwal.jamKeberangkatan = scanner.nextLine();
                System.out.print("Harga Baru: ");
                jadwal.harga = scanner.nextDouble();
                System.out.print("Ketersediaan Kursi Baru: ");
                jadwal.ketersediaanKursi = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Jadwal berhasil diperbarui!");
                return;
            }
        }
        System.out.println("Jadwal tidak ditemukan!");
    }

    static void hapusJadwal() {
        System.out.print("Masukkan ID Jadwal yang ingin dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (JadwalKereta jadwal : jadwalList) {
            if (jadwal.id == id) {
                jadwalList.remove(jadwal);
                System.out.println("Jadwal berhasil dihapus!");
                return;
            }
        }
        System.out.println("Jadwal tidak ditemukan!");
    }
}
