package apap.tugas.akhir.RumahSehat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import apap.tugas.akhir.RumahSehat.model.DokterModel;
import apap.tugas.akhir.RumahSehat.repository.DokterDb;

@Service
public class DokterServiceImpl implements DokterService{
    @Autowired
    private DokterDb dokterDb;

    @Override
    public List<DokterModel> getListDokter(){
        return dokterDb.findAll();
    }

    @Override
    public String encrypt(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public DokterModel addDokter(DokterModel dokter){
        String pass = encrypt(dokter.getPassword());
        dokter.setPassword(pass);
        return dokterDb.save(dokter);
    }
}
