package apap.tugas.akhir.RumahSehat.controller;

import apap.tugas.akhir.RumahSehat.model.TagihanModel;
import apap.tugas.akhir.RumahSehat.service.PasienService;
import apap.tugas.akhir.RumahSehat.model.PasienModel;
import apap.tugas.akhir.RumahSehat.service.TagihanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

public class PasienController {

    @Qualifier("pasienServiceImpl")
    @Autowired
    private PasienService pasienService;

    @Qualifier("tagihanServiceImpl")
    @Autowired
    private TagihanService tagihanService;

    @GetMapping("/pasien/{id}")
    public String viewProfilPasien(@PathVariable String id, Model model){
        PasienModel pasien = pasienService.getPasienById(id);
        List<TagihanModel> listTagihan = new ArrayList(tagihanService.getTagihanByIdPasien(id));
        model.addAttribute("listTagihan", listTagihan);
        model.addAttribute("pasien", pasien);
        return "view-pasien";
    }

    @GetMapping("/pasien/{id}/topup")
    public String topupSaldoPasiemForm(@PathVariable String id, Model model){
        PasienModel pasien = pasienService.getPasienById(id);
        model.addAttribute("pasien", pasien);

        return "form-topup-pasien";
    }

    @PostMapping("/pasien/{id}/topup")
    public String topupSaldoPasienFormSubmit(@ModelAttribute PasienModel pasien, Model model){
        // Hitung saldo baru
        int saldoTambahan = pasien.getSaldo();
        PasienModel tempPasien = pasienService.getPasienById(pasien.getId()); // Ambil objek pasien dari db
        int saldoSaatIni = tempPasien.getSaldo();
        int saldoBaru = saldoTambahan + saldoSaatIni;

        tempPasien.setSaldo(saldoBaru);
        PasienModel updatedPasien = pasienService.updatePasien(tempPasien); // Update pasien di db dengan pasien saldo baru
        model.addAttribute("pasien", updatedPasien);

        return "topup-pasien";
    }

}
