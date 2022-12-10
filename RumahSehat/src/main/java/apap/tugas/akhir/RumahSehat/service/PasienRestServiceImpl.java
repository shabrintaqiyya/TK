package apap.tugas.akhir.RumahSehat.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import apap.tugas.akhir.RumahSehat.model.PasienModel;
import apap.tugas.akhir.RumahSehat.repository.PasienDb;

import apap.tugas.akhir.RumahSehat.rest.Setting;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PasienRestServiceImpl implements PasienRestService {
    public final WebClient webClient;

    public PasienRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.dokterUrl).build();
    }

    @Autowired
    PasienDb pasienDb;

    @Override
    public String encrypt(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }
    
    @Override
    public PasienModel createPasien(PasienModel pasien)  {
        String pass = encrypt(pasien.getPassword());
        pasien.setPassword(pass);
        pasien.setRole("Pasien");
        pasien.setSaldo(0);
        pasien.setIsSso(false);
        return pasienDb.save(pasien);
    }

    @Override
    public void addPasien(PasienModel pasien) {
        pasienDb.save(pasien);
    }

    @Override
    public List<PasienModel> getAllPasien() {
        return pasienDb.findAll();
    }

    @Override
    public PasienModel getPasienById(String id) {
        Optional<PasienModel> pasien = pasienDb.findById(id);
        if (pasien.isPresent()){
            return pasien.get();
        } else return null;
    }

    @Override
    public PasienModel getPasienByKodeTagihan(String kodeTagihan) {
        Optional<PasienModel> pasien = pasienDb.findPasienByKodeTagihan(kodeTagihan);
        if (pasien.isPresent()){
            return pasien.get();
        } else return null;
    }

    @Override
    public PasienModel updatePasien(PasienModel pasien) {
        pasienDb.save(pasien);
        return pasien;
    }

    @Override
    public PasienModel deletePasien(PasienModel pasien) {
        pasienDb.delete(pasien);
        return pasien;
    }
}