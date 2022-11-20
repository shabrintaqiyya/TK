package apap.tugas.akhir.RumahSehat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import apap.tugas.akhir.RumahSehat.model.AdminModel;
import apap.tugas.akhir.RumahSehat.repository.AdminDb;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminDb adminDb;

    @Override
    public List<AdminModel> getListAdmin(){
        return adminDb.findAll();
    }

    @Override
    public String encrypt(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public AdminModel addAdmin(AdminModel admin){
        String pass = encrypt(admin.getPassword());
        admin.setPassword(pass);
        return adminDb.save(admin);
    }

    @Override
    public AdminModel getUserByUsername(String username){
        return adminDb.findByUsername(username);
    }
}