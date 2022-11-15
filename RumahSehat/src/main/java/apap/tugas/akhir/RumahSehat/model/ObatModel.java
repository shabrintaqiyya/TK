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
import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="obat")
public class ObatModel implements Serializable{

    @Id
    // @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Size(max = 20)
    @Column(name = "id_obat", nullable = false)
    private String idObat;

    @NotNull
    @Size(max = 200)
    @Column(name = "nama_obat", nullable = false)
    private String namaObat;

    @NotNull
    @Column(name = "stok", nullable = false, columnDefinition = "integer default 100")
    private Integer stok;

    @NotNull
    @Column(name = "harga", nullable = false)
    private Integer harga;

    @OneToMany(mappedBy = "obat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<JumlahModel> listJumlah;
}
