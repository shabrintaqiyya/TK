package apap.tugas.akhir.RumahSehat.service;

import java.util.List;

import apap.tugas.akhir.RumahSehat.model.JumlahModel;

public interface JumlahService {
    void addJumlah(JumlahModel jumlah);
    List<JumlahModel> getListJumlah();
}
