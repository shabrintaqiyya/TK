package apap.tugas.akhir.RumahSehat.model;

import java.time.LocalDateTime;
import java.util.List;

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

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="resep")
public class ResepModel implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Size(max = 20)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "is_done", nullable = false)
    private Boolean isDone;

    @NotNull
    @Column(name = "created_at", nullable = false, columnDefinition = "TIME")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "confirmer_uuid", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)  
    private ApotekerModel confirmerUuid;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "kode_appointment", referencedColumnName = "kode", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)  
    private AppointmentModel kodeAppointment;

    @OneToMany(mappedBy = "resep", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<JumlahModel> listJumlah;
}
