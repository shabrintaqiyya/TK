package apap.tugas.akhir.RumahSehat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugas.akhir.RumahSehat.model.AppointmentModel;

@Repository
public interface AppointmentDb extends JpaRepository<AppointmentModel, String> {
    AppointmentModel findByKode(String kode);

}
