package apap.tugas.akhir.RumahSehat.model;


import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
