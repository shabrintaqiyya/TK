package apap.tugas.akhir.RumahSehat.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import apap.tugas.akhir.RumahSehat.model.ResepModel;
import apap.tugas.akhir.RumahSehat.repository.ResepDb;

import apap.tugas.akhir.RumahSehat.rest.Setting;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Transactional
public class ResepRestServiceImpl implements ResepRestService {
    public final WebClient webClient;

    public ResepRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.resepUrl).build();
    }

    @Autowired
    ResepDb resepDb;

    @Override
    public ResepModel getResepById(Long id) {
        Optional<ResepModel> resep = resepDb.findById(id);
        if (resep.isPresent()) {
            return resep.get();
        }
        else return null;
    }
    
}
