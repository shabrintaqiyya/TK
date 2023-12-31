package apap.tugas.akhir.RumahSehat.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import apap.tugas.akhir.RumahSehat.model.AppointmentModel;
import apap.tugas.akhir.RumahSehat.model.DokterModel;
import apap.tugas.akhir.RumahSehat.model.PasienModel;
import apap.tugas.akhir.RumahSehat.repository.AppointmentDb;
import apap.tugas.akhir.RumahSehat.repository.DokterDb;
import apap.tugas.akhir.RumahSehat.repository.PasienDb;
import apap.tugas.akhir.RumahSehat.rest.AptDetail;

@Service
@Transactional
public class AppointmentRestServiceImpl implements AppointmentRestService {
    @Autowired
    private AppointmentDb appointmentDb;

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
        System.out.println("kode apt yg baru dibuat: " + appointment.getKode());
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

    @Override
    public List<AptDetail> retrieveListApt(String usernamePasien) {
        PasienModel pasienModel = pasienDb.findByUsername(usernamePasien);
        List<AppointmentModel> listAptInPasienModel = pasienModel.getListAppointment();
        List<AptDetail> listAptInPasienDTO = new ArrayList<>();
        for (AppointmentModel aptModel : listAptInPasienModel) {
            AptDetail aptDtoTemp = new AptDetail();
            aptDtoTemp.setKode(aptModel.getKode());
            aptDtoTemp.setNamaDokter(aptModel.getDokter().getNama());
            aptDtoTemp.setUsernameDokter(aptModel.getDokter().getUsername());
            aptDtoTemp.setNamaPasien(aptModel.getPasien().getNama());
            aptDtoTemp.setUsernamePasien(usernamePasien);
            aptDtoTemp.setWaktuAwal(aptModel.getWaktuAwal());
            aptDtoTemp.setIsDone(aptModel.getIsDone());
            listAptInPasienDTO.add(aptDtoTemp);
        }
        return listAptInPasienDTO;
    }

    @Override
    public AptDetail getAppointmentByKode(AppointmentModel apt) {
        AptDetail aptDTO = new AptDetail();
        aptDTO.setKode(apt.getKode());
        aptDTO.setWaktuAwal(apt.getWaktuAwal());
        aptDTO.setNamaDokter(apt.getDokter().getNama());
        aptDTO.setUsernameDokter(apt.getDokter().getUsername());
        aptDTO.setNamaPasien(apt.getPasien().getNama());
        aptDTO.setUsernamePasien(apt.getPasien().getUsername());
        aptDTO.setIsDone(apt.getIsDone());
        if (apt.getResep() != null) {
            aptDTO.setResepId(apt.getResep().getId());
        }
        return aptDTO;
    }
}