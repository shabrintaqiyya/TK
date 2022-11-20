package apap.tugas.akhir.RumahSehat.service;

import java.util.List;

import apap.tugas.akhir.RumahSehat.model.AdminModel;

public interface AdminService {
    // UserModel addUser(UserModel user);
    public AdminModel getUserByUsername(String username);
    List<AdminModel> getListAdmin();
    AdminModel addAdmin(AdminModel admin);
    public String encrypt(String password);
    // void deleteUser(UserModel user);
    // boolean updatePassword(UserModel username, String oldPass, String newPass, String confirmPass);
}