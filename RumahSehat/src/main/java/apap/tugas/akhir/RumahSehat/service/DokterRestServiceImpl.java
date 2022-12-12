package apap.tugas.akhir.RumahSehat.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import apap.tugas.akhir.RumahSehat.model.AppointmentModel;
import apap.tugas.akhir.RumahSehat.model.DokterModel;
import apap.tugas.akhir.RumahSehat.repository.DokterDb;
import apap.tugas.akhir.RumahSehat.rest.DokterDetail;
import apap.tugas.akhir.RumahSehat.rest.Setting;

@Service
@Transactional
public class DokterRestServiceImpl implements DokterRestService {

    @Autowired
    private DokterDb dokterDb;

    @Override 
    public DokterModel getDokterByUsername(String username) {
        DokterModel dokter = dokterDb.findByUsername(username);
        return dokter;
    }

    @Override
    public List<DokterDetail> retrieveListDokter() {
        List<DokterModel> listDokterModel = dokterDb.findAll();
        List<DokterDetail> listDokterDTO = new ArrayList<>();
        for (DokterModel dokterModel : listDokterModel) {
            DokterDetail dokterTemp = new DokterDetail();
            dokterTemp.setUsername_dokter(dokterModel.getUsername());
            dokterTemp.setNama_dokter(dokterModel.getNama());
            dokterTemp.setTarif(dokterModel.getTarif());
            listDokterDTO.add(dokterTemp);
        }
        return listDokterDTO;
    }

    @Override
    public List<DokterModel> getAllDokter(){
        return dokterDb.findAll();
    }
}
