package apap.tugas.akhir.RumahSehat.repository;

import apap.tugas.akhir.RumahSehat.model.PasienModel;
import apap.tugas.akhir.RumahSehat.model.TagihanModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface TagihanDb extends JpaRepository<TagihanModel, String> {

    Optional<TagihanModel> findByKode(String kode);

    @Query(nativeQuery = true, value = "SELECT T FROM appointment A, pasien P, tagihan T WHERE P.id = :idPasien AND P.id = A.id_pasien AND A.kode = T.kode_appointment")
    Collection<TagihanModel> findTagihanByIdPasien(@Param("idPasien") String idPasien);

    @Query(nativeQuery = true, value = "SELECT T FROM appointment A, pasien P, tagihan T WHERE P.id = :idPasien AND A.kode = :kodeAppointment AND P.id = A.id_pasien AND A.kode = T.kode_appointment")
    Collection<TagihanModel> findTagihanByIdPasienAndKodeAppointment(@Param("idPasien") String idPasien, @Param("kodeAppointment") String kodeAppointment);

    
}
