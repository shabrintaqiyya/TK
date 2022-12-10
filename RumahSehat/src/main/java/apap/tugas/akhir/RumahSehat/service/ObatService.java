package apap.tugas.akhir.RumahSehat.service;

import apap.tugas.akhir.RumahSehat.model.ObatModel;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ObatService {
    List<ObatModel> getListObat();

    ObatModel addObat(ObatModel obat);

    ObatModel updateObat(ObatModel obat);

    ObatModel getObatByIdObat(String idObat);

    String encrypt(String password);
}
