package id.goldan.technobittest.Model;

/**
 * Created by ranug on 07/01/2017.
 */

public class Data {
    private int id;
    private String nama;
    private String alamat;
    private String jenisKelamin;
    private String tanggalLahir;

    public Data(int id, String nama, String alamat, String jenisKelamin, String tanggalLahir) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.jenisKelamin = jenisKelamin;
        this.tanggalLahir = tanggalLahir;
    }

    public Data(){

    }

    public Data(String nama, String alamat, String jenisKelamin, String tanggalLahir) {
        this.nama = nama;
        this.alamat = alamat;
        this.jenisKelamin = jenisKelamin;
        this.tanggalLahir = tanggalLahir;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }
}
