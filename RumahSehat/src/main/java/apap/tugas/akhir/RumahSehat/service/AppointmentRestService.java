package apap.tugas.akhir.RumahSehat.service;

import java.util.List;

import apap.tugas.akhir.RumahSehat.model.AppointmentModel;

public interface AppointmentRestService {
    AppointmentModel createAppointment(AppointmentModel appointment);
    List<AppointmentModel> retrieveListAppointment();
    AppointmentModel getAppointmentByKode(String kode);
}
