package apap.tugas.akhir.RumahSehat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugas.akhir.RumahSehat.model.ApotekerModel;
import apap.tugas.akhir.RumahSehat.model.PasienModel;

@Repository
public interface ApotekerDb extends JpaRepository<ApotekerModel, String>{
    ApotekerModel findByUsername(String username);
}
