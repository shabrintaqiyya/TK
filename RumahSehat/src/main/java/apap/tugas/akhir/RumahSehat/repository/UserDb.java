package apap.tugas.akhir.RumahSehat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugas.akhir.RumahSehat.model.UserModel;

@Repository
public interface UserDb extends JpaRepository<UserModel, Long>{
    UserModel findByUsername(String username);
}
