package id.goldan.technobittest.Model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by ranug on 08/01/2017.
 */

@Root(name = "Response")
public class Response {
    @Attribute
    private String RespCode;

    @Attribute
    private String Nama;

    @Attribute
    private String Alamat;

    @Attribute
    private String JenisKelamin;

    @Attribute
    private String TanggalLahir;

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String alamat) {
        Alamat = alamat;
    }

    public String getJenisKelamin() {
        return JenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        JenisKelamin = jenisKelamin;
    }

    public String getTanggalLahir() {
        return TanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        TanggalLahir = tanggalLahir;
    }
}
