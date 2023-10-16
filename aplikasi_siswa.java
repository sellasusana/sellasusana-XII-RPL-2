import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class aplikasi_siswa {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String jdbcURL = "jdbc:mysql://localhost/koneksi";
        String username = "root";
        String password = "";
        int pilihan;

        try {
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            String nama, jurusan, newNama, newJurusan;
            int nis, newNis, idSiswa, idsiswa;

            do {
                System.out.println("Menu Aplikasi Manajemen Kontak:");
                System.out.println("1. Tambah Siswa");
                System.out.println("2. Tampilkan Siswa");
                System.out.println("3. Perbarui Siswa");
                System.out.println("4. Hapus Siswa");
                System.out.println("5. Pencarian Siswa");
                System.out.println("6. Keluar");

                System.out.print("Pilih menu: ");
                pilihan = scanner.nextInt();
                scanner.nextLine(); // Membuang newline

                switch (pilihan) {
                    case 1:
                        System.out.println("--------------");
                        System.out.println("CREATE SISWA |");
                        System.out.println("--------------");
                        System.out.print("Masukkan Nama : ");
                        nama = scanner.nextLine();
                        System.out.print("Masukkan Jurusan : ");
                        jurusan = scanner.nextLine();
                        System.out.print("Masukkan NIS : ");
                        nis = scanner.nextInt();

                        // Definisikan query INSERT
                        String sqlQuery2 = "INSERT INTO siswa (nama, nis, jurusan) VALUES (?, ?, ?)";

                        // Buat PreparedStatement
                        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery2);

                        // Setel parameter
                        preparedStatement.setString(1, nama);
                        preparedStatement.setInt(2, nis);
                        preparedStatement.setString(3, jurusan);

                        // Eksekusi pernyataan SQL
                        int rowsInserted = preparedStatement.executeUpdate();
                        if (rowsInserted > 0) {
                            System.out.println("--------------------------------");
                            System.out.println("Data berhasil ditambahkan.");
                            System.out.println("--------------------------------");
                        }
                        preparedStatement.close();
                        break;
                    case 2:
                        Statement statement = connection.createStatement();
                        String sqlQuery1 = "SELECT * FROM siswa";
                        
                        ResultSet resultSet = statement.executeQuery(sqlQuery1);
                        
                        // System.out.println("No" + " | " + "Nama" + " | " + "NIS" + "       | " + "Jurusan");
                        System.out.println("--------------------------------");

                        while (resultSet.next()) {
                            int kolom1 = resultSet.getInt("id");
                            String kolom2 = resultSet.getString("nama");
                            int kolom3 = resultSet.getInt("NIS");
                            String kolom4 = resultSet.getString("Jurusan");
                            
                            System.out.println(kolom1 + " | " + kolom2 + " | " + kolom3 + " | " + kolom4);
                        }

                        System.out.println("--------------------------------");
                        
                        resultSet.close();
                        statement.close();
                        break;
                    case 3:
                        System.out.println("--------------");
                        System.out.println("UPDATE SISWA |");
                        System.out.println("--------------");
                        System.out.print("Masukkan id siswa : ");
                        idSiswa = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Masukkan nama baru : ");
                        newNama = scanner.nextLine();
                        System.out.print("Masukkan jurusan baru : ");
                        newJurusan = scanner.nextLine();
                        System.out.print("Masukkan NIS baru : ");
                        newNis = scanner.nextInt();
                        
                        // Definisikan query UPDATE
                        String sqlQuery3 = "UPDATE siswa SET nama = ?,nis = ?, jurusan = ? WHERE id = ?";
                        
                        // Buat PreparedStatement
                        PreparedStatement preparedStatement3 = connection.prepareStatement(sqlQuery3);
                        
                        // Setel parameter
                        preparedStatement3.setString(1, newNama);
                        preparedStatement3.setInt(2, newNis);
                        preparedStatement3.setString(3, newJurusan);
                        preparedStatement3.setInt(4, idSiswa);
                        
                        
                        // Eksekusi pernyataan SQL
                        int rowsUpdated = preparedStatement3.executeUpdate();
                        if (rowsUpdated > 0) {
                            System.out.println("--------------------------------");
                            System.out.println("Data berhasil diperbarui.");
                            System.out.println("--------------------------------");
                        } else {
                            System.out.println("--------------------------------");
                            System.out.println("Tidak ada data yang diperbarui.");
                            System.out.println("--------------------------------");
                        }
                        
                        preparedStatement3.close();
                        break;
                    case 4:
                        System.out.println("--------------");
                        System.out.println("DELETE SISWA |");
                        System.out.println("--------------");
                        System.out.print("Masukkan id siswa : ");
                        idsiswa = scanner.nextInt();
                        
                        // Definisikan query DELETE
                        String sqlQuery4 = "DELETE FROM siswa WHERE id = ?";
                        
                        // Buat PreparedStatement
                        PreparedStatement preparedStatement4 = connection.prepareStatement(sqlQuery4);
                        
                        // Setel parameter
                        preparedStatement4.setInt(1, idsiswa);
                        
                        // Eksekusi pernyataan SQL
                        int rowsDeleted = preparedStatement4.executeUpdate();
                        if (rowsDeleted > 0) {
                            System.out.println("--------------------------------");
                            System.out.println("Data berhasil dihapus.");
                            System.out.println("--------------------------------");
                        } else {
                            System.out.println("--------------------------------");
                            System.out.println("Tidak ada data yang dihapus.");
                            System.out.println("--------------------------------");
                        }
                        
                        preparedStatement4.close();
                        break;
                    case 5:
                        System.out.println("--------------");
                        System.out.println("SEARCH SISWA |");
                        System.out.println("--------------");
                        // Mencari siswa berdasarkan nama
                        System.out.print("Masukkan Nama Siswa yang ingin dicari: ");
                        String searchName = scanner.nextLine();

                        String sqlQuery = "SELECT * FROM siswa WHERE nama LIKE ?";
                        PreparedStatement preparedStatement5 = connection.prepareStatement(sqlQuery);
                        preparedStatement5.setString(1, "%" + searchName + "%"); 
                        // Menggunakan LIKE untuk mencocokkan nama parsial

                        ResultSet resultSet5 = preparedStatement5.executeQuery();

                        if (resultSet5.isBeforeFirst()) {
                            System.out.println("Hasil Pencarian:");
                            while (resultSet5.next()) {
                                int kolom1 = resultSet5.getInt("id");
                                String kolom2 = resultSet5.getString("nama");
                                int kolom3 = resultSet5.getInt("NIS");
                                String kolom4 = resultSet5.getString("Jurusan");
                                System.out.println(kolom1 + " | " + kolom2 + " | " + kolom3 + " | " + kolom4);
                            }
                        } else {
                            System.out.println("Tidak ada siswa dengan nama tersebut.");
                        }

                        resultSet5.close();
                        preparedStatement5.close();
                        break;
                    case 6:
                        System.out.println("Terima kasih!");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                }
            } while (pilihan != 6);
            
            connection.close();
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

