package apap.tugas.akhir.RumahSehat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugas.akhir.RumahSehat.model.ObatModel;

@Repository
public interface ObatDb extends JpaRepository<ObatModel, String>{
    ObatModel findByIdObat(String idObat);
}