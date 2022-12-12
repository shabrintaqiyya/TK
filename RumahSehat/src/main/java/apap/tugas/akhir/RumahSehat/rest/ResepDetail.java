package apap.tugas.akhir.RumahSehat.rest;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResepDetail {
    @JsonProperty("id")
    private String id;

    @JsonProperty("nama_dokter")
    private String namaDokter;

    @JsonProperty("nama_pasien")
    private String namaPasien;

    @JsonProperty("is_done")
    private String isDone;

    @JsonProperty("nama_apoteker")
    private String namaApoteker;
    
    @JsonProperty("list_jumlah")
    private List<String> listJumlah;
}
