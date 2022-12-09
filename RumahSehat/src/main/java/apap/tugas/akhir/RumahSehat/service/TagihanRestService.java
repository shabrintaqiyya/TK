package apap.tugas.akhir.RumahSehat.service;

import apap.tugas.akhir.RumahSehat.model.PasienModel;
import apap.tugas.akhir.RumahSehat.model.TagihanModel;

import java.util.List;

public interface TagihanRestService {

    void addTagihan(TagihanModel tagihan);

    List<TagihanModel> getAllTagihan();

    TagihanModel getTagihanByKode(String kode);

    TagihanModel getTagihanByIdPasien(String idPasien);

    TagihanModel updateTagihan(TagihanModel tagihan);

    TagihanModel deleteTagihan(TagihanModel tagihan);

}
