package apap.tugas.akhir.RumahSehat.service;

import java.util.List;

import apap.tugas.akhir.RumahSehat.model.DokterModel;

public interface DokterService {
    // UserModel addUser(UserModel user);
    // public String encrypt(String password);
    // public UserModel getUserByUsername(String username);
    List<DokterModel> getListDokter();
    DokterModel addDokter(DokterModel dokter);
    public String encrypt(String password);
    public DokterModel getDokterByUsername(String username);
    // void deleteUser(UserModel user);
    // boolean updatePassword(UserModel username, String oldPass, String newPass, String confirmPass);
}
