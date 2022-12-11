package apap.tugas.akhir.RumahSehat.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import apap.tugas.akhir.RumahSehat.model.AppointmentModel;
import apap.tugas.akhir.RumahSehat.repository.AppointmentDb;
import apap.tugas.akhir.RumahSehat.rest.Setting;

@Service
@Transactional
public class AppointmentRestServiceImpl implements AppointmentRestService {
    // public final WebClient webClient;
    
    // public AppointmentRestServiceImpl(WebClient.Builder webClientBuilder) {
    //     this.webClient = webClientBuilder.baseUrl(Setting.appointmentUrl).build();
    // }

    // @Autowired
    // private AppointmentDb appointmentDb;

    // @Override 
    // public AppointmentDTO createAppointment(AppointmentDTO appointment) {
    //     return appointmentDb.save(appointment);
    // }
    
    // @Override
    // public List<AppointmentDTO> retrieveListAppointment() {
    //     return appointmentDb.findAll();
    // }

    // @Override 
    // public AppointmentDTO getAppointmentByKode(String kode) {
    //     Optional<AppointmentDTO> appointment = appointmentDb.findByKode(kode);
    //     if (appointment.isPresent()) {
    //         return appointment.get();
    //     } else {
    //         throw new NoSuchElementException();
    //     }
    // }
}
