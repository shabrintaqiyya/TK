package apap.tugas.akhir.RumahSehat.service;

import java.util.List;

import apap.tugas.akhir.RumahSehat.model.ApotekerModel;

public interface ApotekerService {
    // UserModel addUser(UserModel user);
    // public UserModel getUserByUsername(String username);
    List<ApotekerModel> getListApoteker();
    ApotekerModel addApoteker(ApotekerModel apoteker);
    public String encrypt(String password);
    ApotekerModel getApotekerByUsername(String username);
    // void deleteUser(UserModel user);
    // boolean updatePassword(UserModel username, String oldPass, String newPass, String confirmPass);
}
