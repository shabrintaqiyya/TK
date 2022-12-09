package apap.tugas.akhir.RumahSehat.repository;

import apap.tugas.akhir.RumahSehat.model.PasienModel;
import apap.tugas.akhir.RumahSehat.model.TagihanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface PasienDb extends JpaRepository<PasienModel, String> {

    Optional<PasienModel> findById(String id);

    @Query(nativeQuery = true, value = "SELECT P FROM appointment A, pasien P, tagihan T WHERE T.kode = :kodeTagihan AND P.id = A.id_pasien AND A.kode = T.kode_appointment")
    Optional<PasienModel> findPasienByKodeTagihan(@Param("kodeTagihan") String kodeTagihan);

}
