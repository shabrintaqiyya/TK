package apap.tugas.akhir.RumahSehat.repository;

import apap.tugas.akhir.RumahSehat.model.TagihanModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagihanDb extends JpaRepository<TagihanModel, Long> {
    
}
