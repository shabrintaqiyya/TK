package apap.tugas.akhir.RumahSehat.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DokterDetail {
    private String username_dokter;
    private String nama_dokter;
    private Integer tarif;
}
