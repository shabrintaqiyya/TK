package apap.tugas.akhir.RumahSehat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugas.akhir.RumahSehat.model.ObatModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface ObatDb extends JpaRepository<ObatModel, String>{
    Optional<ObatModel> findByIdObat(String idObat);
    List<ObatModel> findAll();
}