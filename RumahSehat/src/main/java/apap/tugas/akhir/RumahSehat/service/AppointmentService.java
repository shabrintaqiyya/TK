package apap.tugas.akhir.RumahSehat.service;

import java.util.List;

import apap.tugas.akhir.RumahSehat.model.AppointmentModel;
import apap.tugas.akhir.RumahSehat.model.TagihanModel;

public interface AppointmentService {
    List<AppointmentModel> getListAppointment();
    AppointmentModel getAppointmentByKode(String kode);
    AppointmentModel setDoneAppointment(AppointmentModel appointment);
    TagihanModel setTagihan(AppointmentModel appointment);
}
