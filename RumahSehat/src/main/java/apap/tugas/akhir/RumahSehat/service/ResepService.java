package apap.tugas.akhir.RumahSehat.service;

import java.util.List;

import apap.tugas.akhir.RumahSehat.model.ResepModel;

public interface ResepService {
    void addResep(ResepModel resep);
    List<ResepModel> getListResep();
    ResepModel getResepById(Long id);
}
