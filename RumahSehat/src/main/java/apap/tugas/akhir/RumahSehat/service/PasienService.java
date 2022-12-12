package apap.tugas.akhir.RumahSehat.service;

import java.util.List;

import apap.tugas.akhir.RumahSehat.model.PasienModel;

public interface PasienService {

    void addPasien(PasienModel pasien);

    List<PasienModel> getListPasien();

    List<PasienModel> getAllPasien();

    PasienModel getPasienById(String id);

    PasienModel getPasienByKodeTagihan(String kodeTagihan);

    PasienModel updatePasien(PasienModel pasien);

    PasienModel deletePasien(PasienModel pasien);
}
