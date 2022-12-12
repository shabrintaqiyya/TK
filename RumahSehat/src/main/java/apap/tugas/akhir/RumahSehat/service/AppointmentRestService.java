package apap.tugas.akhir.RumahSehat.service;

import java.util.List;

import apap.tugas.akhir.RumahSehat.model.AppointmentModel;
import apap.tugas.akhir.RumahSehat.model.DokterModel;
import apap.tugas.akhir.RumahSehat.model.PasienModel;
import apap.tugas.akhir.RumahSehat.rest.AptDetail;

public interface AppointmentRestService {
    DokterModel makeDokter(String username);
    PasienModel makePasien(String username);
    AppointmentModel createAppointment(DokterModel dokter, PasienModel pasien, AptDetail aptDTO);
    Boolean cekJadwal(AppointmentModel currentApt);
    AppointmentModel saveAppointment(AppointmentModel apt);
    List<AptDetail> retrieveListApt(String usernamePasien);
    AptDetail getAppointmentByKode(String kode);
}