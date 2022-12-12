package apap.tugas.akhir.RumahSehat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import apap.tugas.akhir.RumahSehat.model.ApotekerModel;
import apap.tugas.akhir.RumahSehat.repository.ApotekerDb;

@Service
public class ApotekerServiceImpl implements ApotekerService{
    @Autowired
    private ApotekerDb apotekerDb;

    @Override
    public List<ApotekerModel> getListApoteker(){
        return apotekerDb.findAll();
    }

    @Override
    public String encrypt(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public ApotekerModel addApoteker(ApotekerModel apoteker){
        String pass = encrypt(apoteker.getPassword());
        apoteker.setPassword(pass);
        return apotekerDb.save(apoteker);
    }

    @Override
    public ApotekerModel getApotekerByUsername(String username) {
        return apotekerDb.findByUsername(username);
    }

}