package apap.tugas.akhir.RumahSehat.service;

import java.util.List;

import apap.tugas.akhir.RumahSehat.model.JumlahModel;
import apap.tugas.akhir.RumahSehat.model.ObatModel;

public interface JumlahService {
    void addJumlah(JumlahModel jumlah);
    List<JumlahModel> getListJumlah();
    String getNamaObat(ObatModel obat);
}
