package apap.tugas.akhir.RumahSehat.restcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import apap.tugas.akhir.RumahSehat.model.JumlahModel;
import apap.tugas.akhir.RumahSehat.model.ResepModel;
import apap.tugas.akhir.RumahSehat.rest.ResepDetail;
import apap.tugas.akhir.RumahSehat.service.ResepService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/resep")
public class ResepRestController {
    @Autowired
    private ResepService resepService;

    @GetMapping("/detail/{id}")
    public ResepDetail retrieveResep(@PathVariable("id") Long id) {
        try {
            ResepModel resep = resepService.getResepById(id);
            ResepDetail resepDetail = new ResepDetail();
            List<String> jumlah = new ArrayList<>();

            String status = "Belum selesai";
            String apoteker = "-";
            if (resep.getIsDone()) {
                status = "Selesai";
                apoteker = resep.getConfirmerUuid().getNama();
            }

            for (JumlahModel jml : resep.getListJumlah()){
                String listJmlObat = ""+ jml.getObat().getNamaObat()+ " (" + jml.getKuantitas().toString() + " buah)";
                jumlah.add(listJmlObat);
            }

            resepDetail.setId(String.valueOf(id));
            resepDetail.setNamaDokter(resep.getKodeAppointment().getDokter().getNama());
            resepDetail.setNamaPasien(resep.getKodeAppointment().getPasien().getNama());
            resepDetail.setIsDone(status);
            resepDetail.setNamaApoteker(apoteker);
            resepDetail.setListJumlah(jumlah);
            
            log.info(String.format("User melihat detail resep dengan id ", id));
            return resepDetail;
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Resep with id " + id + " not found"
            );
        }
    }
}
