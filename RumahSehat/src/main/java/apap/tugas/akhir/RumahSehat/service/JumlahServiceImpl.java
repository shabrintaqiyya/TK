package apap.tugas.akhir.RumahSehat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import apap.tugas.akhir.RumahSehat.model.JumlahModel;
import apap.tugas.akhir.RumahSehat.repository.JumlahDb;

@Service
@Transactional
public class JumlahServiceImpl implements JumlahService{
    @Autowired
    JumlahDb jumlahDb;

    @Override
    public void addJumlah(JumlahModel jumlah) {
        jumlahDb.save(jumlah);
    }

    @Override
    public List<JumlahModel> getListJumlah() {
        return jumlahDb.findAll();
    }
    
}
