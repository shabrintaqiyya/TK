package apap.tugas.akhir.RumahSehat.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import apap.tugas.akhir.RumahSehat.model.AppointmentModel;
import apap.tugas.akhir.RumahSehat.model.DokterModel;
import apap.tugas.akhir.RumahSehat.model.PasienModel;
import apap.tugas.akhir.RumahSehat.repository.AppointmentDb;
import apap.tugas.akhir.RumahSehat.repository.DokterDb;
import apap.tugas.akhir.RumahSehat.repository.PasienDb;
import apap.tugas.akhir.RumahSehat.rest.AptDetail;
import apap.tugas.akhir.RumahSehat.rest.DokterDetail;
import apap.tugas.akhir.RumahSehat.rest.PasienDetail;
import apap.tugas.akhir.RumahSehat.rest.Setting;

@Service
@Transactional
public class AppointmentRestServiceImpl implements AppointmentRestService {
    @Autowired
    private AppointmentDb appointmentDb;
    // public final WebClient webClient;
    
    // public AppointmentRestServiceImpl(WebClient.Builder webClientBuilder) {
    //     this.webClient = webClientBuilder.baseUrl(Setting.appointmentUrl).build();
    // }

    // @Autowired
    // private AppointmentDb appointmentDb;

    @Autowired
    private DokterDb dokterDb;

    @Autowired
    private PasienDb pasienDb;

    @Override
    public DokterModel makeDokter(String username) {
        DokterModel dokterModel = dokterDb.findByUsername(username);
        return dokterModel;
    }

    @Override
    public PasienModel makePasien(String username) {
        PasienModel pasienModel = pasienDb.findByUsername(username);
        return pasienModel;
    }

    @Override
    public AppointmentModel createAppointment(DokterModel dokter, PasienModel pasien, AptDetail aptDTO) {
        AppointmentModel appointment = new AppointmentModel();
        // System.out.println();
        appointment.setIsDone(false);
        appointment.setWaktuAwal(aptDTO.getWaktuAwal());
        appointment.setDokter(dokter);
        appointment.setPasien(pasien);
        return appointment;
    }

    @Override
    public Boolean cekJadwal(AppointmentModel currentApt) {
        List<AppointmentModel> listAppointmentInDokter = currentApt.getDokter().getListAppointment();
        LocalDateTime startCurrentApt = currentApt.getWaktuAwal();
        LocalDateTime endCurrentApt = startCurrentApt.plusHours(1);
        for (AppointmentModel jadwal : listAppointmentInDokter) {
            LocalDateTime waktuMulai = jadwal.getWaktuAwal();
            LocalDateTime waktuSelesai = waktuMulai.plusHours(1);
            Boolean bentrokDiAwal = startCurrentApt.isAfter(waktuMulai) && startCurrentApt.isBefore(waktuSelesai);
            Boolean bentrokDiAkhir = endCurrentApt.isAfter(waktuMulai) && endCurrentApt.isBefore(waktuSelesai);
            Boolean waktuSama = waktuMulai.equals(startCurrentApt) && waktuSelesai.equals(endCurrentApt);
            if (bentrokDiAwal || bentrokDiAkhir || waktuSama) {
                return false;
            }
        }
        return true;
    }

    @Override
    public AppointmentModel saveAppointment(AppointmentModel apt) {
        return appointmentDb.save(apt);
    }
}
