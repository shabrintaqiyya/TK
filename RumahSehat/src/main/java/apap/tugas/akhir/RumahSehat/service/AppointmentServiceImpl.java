package apap.tugas.akhir.RumahSehat.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugas.akhir.RumahSehat.repository.AppointmentDb;
import apap.tugas.akhir.RumahSehat.repository.TagihanDb;
import apap.tugas.akhir.RumahSehat.model.AppointmentModel;
import apap.tugas.akhir.RumahSehat.model.TagihanModel;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    AppointmentDb appointmentDb;

    @Autowired
    TagihanDb tagihanDb;

    @Override
    public List<AppointmentModel> getListAppointment() {
        return appointmentDb.findAll();
    }

    @Override
    public AppointmentModel getAppointmentByKode(String kode) {
        AppointmentModel appointment = appointmentDb.findByKode(kode);
        return appointment;
    }
    @Override
    public AppointmentModel setDoneAppointment(AppointmentModel appointment) {
        appointment.setIsDone(true);
        setTagihan(appointment);
        appointmentDb.save(appointment);
        return appointment;
    }

    @Override
    public TagihanModel setTagihan(AppointmentModel appointment) {
        TagihanModel tagihan = new TagihanModel();
        tagihan.setTanggalTerbuat(LocalDateTime.now());
        tagihan.setIsPaid(false);
        tagihan.setJumlahTagihan(appointment.getDokter().getTarif());
        tagihan.setKodeAppointment(appointment);
        tagihanDb.save(tagihan);
        return tagihan;
    }

    @Override
    public AppointmentModel getAppointmentByKodeModel(AppointmentModel kode) {
        String kodenya = kode.getKode();
        AppointmentModel appointment = appointmentDb.findByKode(kodenya);
        return appointment;
    }

    @Override
    public void addAppointment(AppointmentModel appointment) {
        appointmentDb.save(appointment);
    }

    @Override
    public AppointmentModel createAppointment(AppointmentModel appointment) {
        return appointmentDb.save(appointment);
    }
}
