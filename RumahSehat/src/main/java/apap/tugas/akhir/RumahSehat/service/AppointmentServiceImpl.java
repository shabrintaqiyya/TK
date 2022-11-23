package apap.tugas.akhir.RumahSehat.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugas.akhir.RumahSehat.repository.AppointmentDb;
import apap.tugas.akhir.RumahSehat.model.AppointmentModel;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    AppointmentDb appointmentDb;

    @Override
    public List<AppointmentModel> getListAppointment() {
        return appointmentDb.findAll();
    }

    @Override
    public AppointmentModel getAppointmentByKode(String kode) {
        Optional<AppointmentModel> appointment = appointmentDb.findByKode(kode);
        if (appointment.isPresent()) {
            return appointment.get();
        } else return null;
    }
}
