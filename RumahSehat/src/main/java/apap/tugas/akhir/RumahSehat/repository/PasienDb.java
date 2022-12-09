package apap.tugas.akhir.RumahSehat.repository;

import apap.tugas.akhir.RumahSehat.model.PasienModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasienDb extends JpaRepository<PasienModel, String> {

    Optional<PasienModel> findById(String id);

}
