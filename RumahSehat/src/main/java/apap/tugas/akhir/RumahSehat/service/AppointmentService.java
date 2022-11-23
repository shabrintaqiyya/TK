package apap.tugas.akhir.RumahSehat.service;

import java.util.List;

import apap.tugas.akhir.RumahSehat.model.AppointmentModel;

public interface AppointmentService {
    List<AppointmentModel> getListAppointment();
    AppointmentModel getAppointmentByKode(String kode);
    
}
