/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.p_uts_23090134_c_2025;
//import com.mongodb.MongoClient;
//import com.mongodb.MongoClientUR;
import com.mongodb.client.*;
import org.bson.Document;
import com.mongodb.client.model.Filters;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class CRUD_23090134_C_2025 {
    private static MongoCollection<Document> collection;
    
    // Koneksi ke MongoDB
    private static void connectToMongoDB() {
        MongoClient client = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = client.getDatabase("uts_23090134_C_2025");
        collection = database.getCollection("coll_23090134_C_2025");
    }
    
    // Fungsi Create: Menambahkan Data Baru dengan 3 Dimensi yang Berbeda
    private static void createData() {
        Scanner scanner = new Scanner(System.in);

        // Dimensi 1: Informasi Pengguna
        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine();

        System.out.print("Masukkan Email: ");
        String email = scanner.nextLine();

        System.out.print("Masukkan Usia: ");
        int usia = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        // Dimensi 2: Informasi Alamat
        System.out.print("Masukkan Alamat: ");
        String alamat = scanner.nextLine();

        System.out.print("Masukkan Kota: ");
        String kota = scanner.nextLine();

        System.out.print("Masukkan Negara: ");
        String negara = scanner.nextLine();

        // Dimensi 3: Informasi Pekerjaan atau Pendidikan
        System.out.print("Masukkan Pekerjaan: ");
        String pekerjaan = scanner.nextLine();

        System.out.print("Masukkan Perusahaan atau Sekolah: ");
        String perusahaanSekolah = scanner.nextLine();

        System.out.print("Masukkan Posisi atau Jurusan: ");
        String posisiJurusan = scanner.nextLine();

        // Membuat document baru dengan 3 dimensi data
        Document doc = new Document("nama", nama)
                        .append("email", email)
                        .append("usia", usia)
                        .append("alamat", new Document("alamat", alamat)
                                           .append("kota", kota)
                                           .append("negara", negara))
                        .append("pekerjaan", new Document("pekerjaan", pekerjaan)
                                             .append("perusahaan_sekolah", perusahaanSekolah)
                                             .append("posisi_jurusan", posisiJurusan));

        // Menambahkan data ke collection
        collection.insertOne(doc);
        System.out.println("Data berhasil ditambahkan!");
    }
    
    // Fungsi Read: Menampilkan Semua Data dengan Dimensi 3
    private static void readData() {
        System.out.println("Menampilkan Semua Data:");

        // Mengambil seluruh data
        FindIterable<Document> documents = collection.find();

        // Menampilkan data dalam bentuk JSON
        for (Document doc : documents) {
            System.out.println(doc.toJson());
        }
    }
    
    // Fungsi Update: Mengubah Data Berdasarkan ID
    private static void updateData() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan ID Data yang ingin diubah: ");
        String id = scanner.nextLine();  // ID yang ingin diubah

        // Dimensi 1: Update Nama dan Email
        System.out.print("Masukkan Nama Baru: ");
        String nama = scanner.nextLine();

        System.out.print("Masukkan Email Baru: ");
        String email = scanner.nextLine();

        System.out.print("Masukkan Usia Baru: ");
        int usia = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        // Dimensi 2: Update Alamat
        System.out.print("Masukkan Alamat Baru: ");
        String alamat = scanner.nextLine();

        System.out.print("Masukkan Kota Baru: ");
        String kota = scanner.nextLine();

        System.out.print("Masukkan Negara Baru: ");
        String negara = scanner.nextLine();

        // Dimensi 3: Update Pekerjaan
        System.out.print("Masukkan Pekerjaan Baru: ");
        String pekerjaan = scanner.nextLine();

        System.out.print("Masukkan Perusahaan atau Sekolah Baru: ");
        String perusahaanSekolah = scanner.nextLine();

        System.out.print("Masukkan Posisi atau Jurusan Baru: ");
        String posisiJurusan = scanner.nextLine();

        // Membuat document baru dengan data yang telah diperbarui
        Document updatedDoc = new Document("nama", nama)
                                .append("email", email)
                                .append("usia", usia)
                                .append("alamat", new Document("alamat", alamat)
                                                   .append("kota", kota)
                                                   .append("negara", negara))
                                .append("pekerjaan", new Document("pekerjaan", pekerjaan)
                                                     .append("perusahaan_sekolah", perusahaanSekolah)
                                                     .append("posisi_jurusan", posisiJurusan));

        // Update data berdasarkan ID
        collection.updateOne(Filters.eq("_id", id), new Document("$set", updatedDoc));
        System.out.println("Data berhasil diperbarui!");
    }
    
    // Fungsi Delete: Menghapus Data Berdasarkan ID
    private static void deleteData() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan ID Data yang ingin dihapus: ");
        String id = scanner.nextLine();  // ID yang ingin dihapus

        // Menghapus data berdasarkan ID
        collection.deleteOne(Filters.eq("_id", id));
        System.out.println("Data berhasil dihapus!");
    }

    // Fungsi untuk Menjalankan CRUD
    public static void main(String[] args) {
        connectToMongoDB();  // Menghubungkan ke MongoDB
        
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        do {
            System.out.println("\nMenu CRUD MongoDB:");
            System.out.println("1. Tambah Data");
            System.out.println("2. Tampilkan Semua Data");
            System.out.println("3. Update Data");
            System.out.println("4. Hapus Data");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu (1-5): ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            
            switch (choice) {
                case 1:
                    createData();  // Menambah data
                    break;
                case 2:
                    readData();    // Menampilkan data
                    break;
                case 3:
                    updateData();  // Mengubah data
                    break;
                case 4:
                    deleteData();  // Menghapus data
                    break;
                case 5:
                    System.out.println("Keluar dari program...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (choice != 5);  // Program terus berjalan sampai pengguna memilih untuk keluar
        
        scanner.close();  // Menutup scanner
    }


    
    

    
}
