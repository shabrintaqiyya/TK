package apap.tugas.akhir.RumahSehat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugas.akhir.RumahSehat.model.DokterModel;

@Repository
public interface DokterDb extends JpaRepository<DokterModel, Long>{
    DokterModel findByUsername(String username);
}