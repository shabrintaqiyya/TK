package apap.tugas.akhir.RumahSehat.controller;

import apap.tugas.akhir.RumahSehat.service.PasienService;
import apap.tugas.akhir.RumahSehat.model.PasienModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class PasienController {

    @Qualifier("pasienServiceImpl")
    @Autowired
    private PasienService pasienService;

    @GetMapping("/pasien/{id}")
    public String viewProfilPasien(@PathVariable String id, Model model){
        PasienModel pasien = pasienService.getPasienById(id);
        model.addAttribute("pasien", pasien);
        return "view-pasien";
    }

}
