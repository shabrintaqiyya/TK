package apap.tugas.akhir.RumahSehat.service;

import apap.tugas.akhir.RumahSehat.model.AppointmentModel;
import apap.tugas.akhir.RumahSehat.model.DokterModel;
import apap.tugas.akhir.RumahSehat.rest.DokterDetail;

import java.util.List;

public interface DokterRestService {
    DokterModel getDokterByUsername(String username);
    // List<AppointmentModel> getListAppointmentInDokter(DokterModel dokter);
    List<DokterModel> getAllDokter();
    List<DokterDetail> retrieveListDokter();
}
