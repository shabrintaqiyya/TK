package apap.tugas.akhir.RumahSehat.service;

import apap.tugas.akhir.RumahSehat.model.PasienModel;

import java.util.List;

public interface PasienService {

    void addPasien(PasienModel pasien);

    List<PasienModel> getAllPasien();

    PasienModel getPasienById(String id);

    PasienModel updatePasien(PasienModel pasien);

    PasienModel deletePasien(PasienModel pasien);
}
