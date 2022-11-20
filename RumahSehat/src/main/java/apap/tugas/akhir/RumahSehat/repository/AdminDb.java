package apap.tugas.akhir.RumahSehat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugas.akhir.RumahSehat.model.AdminModel;

@Repository
public interface AdminDb extends JpaRepository<AdminModel, Long>{
    AdminModel findByUsername(String username);
}