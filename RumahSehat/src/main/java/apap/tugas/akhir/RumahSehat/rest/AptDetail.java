package apap.tugas.akhir.RumahSehat.rest;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AptDetail {
    @JsonProperty("kode")
    private String kode;

    @JsonProperty("waktu_awal")
    private LocalDateTime waktuAwal;

    @JsonProperty("nama_dokter")
    private String namaDokter;

    @JsonProperty("username_dokter")
    private String usernameDokter;

    @JsonProperty("nama_pasien")
    private String namaPasien;

    @JsonProperty("username_pasien")
    private String usernamePasien;
    
    @JsonProperty("is_done")
    private Boolean isDone;

    @JsonProperty("resep")
    private Long resepId;
}