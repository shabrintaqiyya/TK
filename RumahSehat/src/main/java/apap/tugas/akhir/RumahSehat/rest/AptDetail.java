package apap.tugas.akhir.RumahSehat.rest;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// @Getter @Setter
// @JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AptDetail {
    @JsonProperty("waktu_awal")
    private LocalDateTime waktuAwal;

    @JsonProperty("nama_dokter")
    private String namaDokter;

    @JsonProperty("username_dokter")
    private String usernameDokter;

    @JsonProperty("username_pasien")
    private String usernamePasien;
}