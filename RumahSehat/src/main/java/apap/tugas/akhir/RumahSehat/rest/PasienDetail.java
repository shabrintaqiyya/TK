package apap.tugas.akhir.RumahSehat.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PasienDetail {
    private String id_pasien;
    private String nama_pasien;
    
}
