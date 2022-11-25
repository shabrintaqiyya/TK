package apap.tugas.akhir.RumahSehat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import apap.tugas.akhir.RumahSehat.model.JumlahModel;

public interface JumlahDb extends JpaRepository<JumlahModel, String> {
    
}
