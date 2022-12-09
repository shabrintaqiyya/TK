package apap.tugas.akhir.RumahSehat.service;

import apap.tugas.akhir.RumahSehat.model.PasienModel;
import apap.tugas.akhir.RumahSehat.model.TagihanModel;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PasienRestService {

    void addPasien(PasienModel pasien);

    List<PasienModel> getAllPasien();

    PasienModel getPasienById(String id);

    PasienModel getPasienByKodeTagihan(String kodeTagihan);

    PasienModel updatePasien(PasienModel pasien);

    PasienModel deletePasien(PasienModel pasien);
}
