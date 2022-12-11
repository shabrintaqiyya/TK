package apap.tugas.akhir.RumahSehat.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import apap.tugas.akhir.RumahSehat.model.AppointmentModel;
import apap.tugas.akhir.RumahSehat.model.DokterModel;
import apap.tugas.akhir.RumahSehat.repository.DokterDb;
import apap.tugas.akhir.RumahSehat.rest.Setting;

@Service
@Transactional
public class DokterRestServiceImpl implements DokterRestService {
    public final WebClient webClient;

    public DokterRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.userUrl).build();
    }

    @Autowired
    private DokterDb dokterDb;

    @Override 
    public DokterModel getDokterByUsername(String username) {
        DokterModel dokter = dokterDb.findByUsername(username);
        return dokter;
    }

    @Override
    public List<AppointmentModel> getListAppointmentInDokter(DokterModel dokter) {
        return dokter.getListAppointment();
    }
}
