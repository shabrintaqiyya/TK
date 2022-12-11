package apap.tugas.akhir.RumahSehat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugas.akhir.RumahSehat.model.ResepModel;

@Repository
public interface ResepDb extends JpaRepository<ResepModel, Long> {
    
}
