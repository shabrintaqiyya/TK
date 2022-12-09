package apap.tugas.akhir.RumahSehat.service;

import apap.tugas.akhir.RumahSehat.model.PasienModel;
import apap.tugas.akhir.RumahSehat.model.TagihanModel;
import apap.tugas.akhir.RumahSehat.repository.PasienDb;
import apap.tugas.akhir.RumahSehat.repository.TagihanDb;
import apap.tugas.akhir.RumahSehat.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

public class TagihanRestServiceImpl implements TagihanRestService{
    public final WebClient webClient;

    public TagihanRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.dokterUrl).build();
    }

    @Autowired
    TagihanDb tagihanDb;


    @Override
    public void addTagihan(TagihanModel tagihan) {
        tagihanDb.save(tagihan);
    }

    @Override
    public List<TagihanModel> getAllTagihan() {
        return tagihanDb.findAll();
    }

    @Override
    public TagihanModel getTagihanByKode(String kode) {
        Optional<TagihanModel> tagihan = tagihanDb.findByKode(kode);
        if (tagihan.isPresent()){
            return tagihan.get();
        } else return null;
    }

    @Override
    public TagihanModel getTagihanByIdPasien(String idPasien) {
        Optional<TagihanModel> tagihan = tagihanDb.findTagihanByIdPasien(idPasien);
        if (tagihan.isPresent()){
            return tagihan.get();
        } else return null;
    }

    @Override
    public TagihanModel updateTagihan(TagihanModel tagihan) {
        tagihanDb.save(tagihan);
        return tagihan;
    }

    @Override
    public TagihanModel deleteTagihan(TagihanModel tagihan) {
        tagihanDb.delete(tagihan);
        return tagihan;
    }
}
