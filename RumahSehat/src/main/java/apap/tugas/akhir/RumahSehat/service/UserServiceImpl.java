package apap.tugas.akhir.RumahSehat.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import apap.tugas.akhir.RumahSehat.model.UserModel;
import apap.tugas.akhir.RumahSehat.repository.UserDb;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDb userDb;

    @Override
    public UserModel addUser(UserModel user){
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userDb.save(user);
    }

    @Override
    public String encrypt(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public UserModel getUserByUsername(String username){
        return userDb.findByUsername(username);
    }

    @Override
    public List<UserModel> getListUser(){
        return userDb.findAll();
    }

    @Override
    public void deleteUser(UserModel user){
        userDb.delete(user);
    }

    @Override
    public boolean updatePassword(UserModel username, String oldPass, String newPass, String confirmPass){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        
        if(passwordEncoder.matches(oldPass, username.getPassword()) && newPass.equals(confirmPass)){
            String pass = encrypt(newPass);
            username.setPassword(pass);
            userDb.save(username);
            return true;
        }
        
        return false;
    }
}