package apap.tugas.akhir.RumahSehat.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apap.tugas.akhir.RumahSehat.model.DokterModel;
import apap.tugas.akhir.RumahSehat.rest.DokterDetail;
import apap.tugas.akhir.RumahSehat.service.DokterRestService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/")
public class DokterRestController {
    @Autowired
    private DokterRestService dokterRestService; 

    @GetMapping(value = "/dokter/list-dokter")
    private List<DokterModel> listPasien() {
        // System.out.println("PRINTTTTTTTTTT "+pasienRestService.getAllPasien());
        return dokterRestService.getAllDokter();
    }
    
    @GetMapping(value = "/list-dokter")
    private List<DokterDetail> retrieveListDokter() {
        return dokterRestService.retrieveListDokter();
    }
}
