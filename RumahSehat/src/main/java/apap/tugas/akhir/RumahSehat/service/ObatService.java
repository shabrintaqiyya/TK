package apap.tugas.akhir.RumahSehat.service;

import apap.tugas.akhir.RumahSehat.model.ObatModel;

import java.util.List;

public interface ObatService {
    List<ObatModel> getListObat();
    ObatModel addObat(ObatModel obat);
    public String encrypt(String password);
}
