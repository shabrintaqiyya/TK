package apap.tugas.akhir.RumahSehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="appointment")
public class AppointmentModel implements Serializable {

    @Id
    // @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Size(max = 20)
    @Column(name = "kode", nullable = false)
    private String kode;

    @NotNull
    @Column(name = "waktu_awal", nullable = false, columnDefinition = "TIME")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime waktuAwal;

    @NotNull
    @Column(name = "is_done", nullable = false, columnDefinition = "boolean default false")
    private Boolean isDone;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_pasien", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)  
    private PasienModel pasien;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_dokter", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DokterModel dokter;

    @OneToOne(mappedBy = "kodeAppointment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    ResepModel resep;

    @OneToOne(mappedBy = "kodeAppointment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private TagihanModel tagihan;
}
