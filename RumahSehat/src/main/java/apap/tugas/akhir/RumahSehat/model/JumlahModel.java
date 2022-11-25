package apap.tugas.akhir.RumahSehat.model;

import java.time.LocalDateTime;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
@Table(name="jumlah")
public class JumlahModel implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    // @Size(max = 20)
    @Column(name = "id_jumlah", nullable = false)
    private Long idJumlah;
    
    @ManyToOne
    @JoinColumn(name = "obat", referencedColumnName = "id_obat", nullable = false)
    private ObatModel obat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resep", referencedColumnName = "id")
    private ResepModel resep;

    @NotNull
    @Column(name = "kuantitas", nullable = false)
    private Integer kuantitas;
}
