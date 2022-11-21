package apap.tugas.akhir.RumahSehat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import apap.tugas.akhir.RumahSehat.model.ObatModel;
import apap.tugas.akhir.RumahSehat.repository.ObatDb;

import javax.transaction.Transactional;

@Service
@Transactional
public class ObatServiceImpl implements ObatService{

    @Autowired
    private ObatDb obatDb;

    @Override
    public List<ObatModel> getListObat(){
        return obatDb.findAll();
    }

    @Override
    public String encrypt(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public ObatModel addObat(ObatModel obat){
//        String pass = encrypt(obat.getPassword());
//        obat.setPassword(pass);
        return obatDb.save(obat);
    }

    @Override
    public ObatModel getObatByIdObat(String idObat) {
        Optional<ObatModel> obat = obatDb.findByIdObat(idObat);
        return obat.orElse(null);
    }

    @Override
    public ObatModel updateObat(ObatModel obat){
        obatDb.save(obat);
        return obat;
    };

}