package apap.tugas.akhir.RumahSehat.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;

import apap.tugas.akhir.RumahSehat.repository.StringPrefixedSequenceIdGenerator;

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
@Table(name="tagihan")
public class TagihanModel implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "BILL")
    @GenericGenerator(
        name = "BILL",
        strategy = "apap.tugas.akhir.RumahSehat.repository.StringPrefixedSequenceIdGenerator",
        parameters = {
            @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
            @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "BILL-")
        }
    )
    @NotNull
    @Size(max = 20)
    @Column(name = "kode", nullable = false)
    private String kode;

    @NotNull
    @Column(name = "tanggal_terbuat", nullable = false, columnDefinition = "TIME")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime tanggalTerbuat;

    @Column(name = "tanggal_bayar", columnDefinition = "TIME")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime tanggalBayar; 

    @NotNull
    @Column(name = "is_paid", nullable = false)
    private Boolean isPaid;

    @NotNull
    @Column(name = "jumlah_tagihan", nullable = false)
    private Integer jumlahTagihan;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "kode_appointment", referencedColumnName = "kode", nullable = false)
    private AppointmentModel kodeAppointment;
}
