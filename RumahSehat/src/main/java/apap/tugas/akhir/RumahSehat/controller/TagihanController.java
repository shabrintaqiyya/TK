package apap.tugas.akhir.RumahSehat.controller;

import apap.tugas.akhir.RumahSehat.model.PasienModel;
import apap.tugas.akhir.RumahSehat.model.TagihanModel;
import apap.tugas.akhir.RumahSehat.service.PasienService;
import apap.tugas.akhir.RumahSehat.service.TagihanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TagihanController {

    @Qualifier("tagihanServiceImpl")
    @Autowired
    private TagihanService tagihanService;

    @Qualifier("pasienServiceImpl")
    @Autowired
    private PasienService pasienService;

    @GetMapping("/pasien/{id}/{kode}")
    public String formBayarTagihan(@PathVariable String id, String kode, Model model){
        PasienModel pasien = pasienService.getPasienById(id);
        TagihanModel tagihan = tagihanService.getTagihanByKode(kode);
        model.addAttribute("pasien", pasien);
        model.addAttribute("tagihan", tagihan);
        return "form-bayar-tagihan";
    }

    @PostMapping("/pasien/{id}/{kode}")
    public String formBayarTagihanKonfirmasi(@ModelAttribute TagihanModel tagihan, Model model) {
        TagihanModel tempTagihan = tagihanService.getTagihanByKode(tagihan.getKode());
        PasienModel tempPasien = pasienService.getPasienByKodeTagihan(tagihan.getKode());

        model.addAttribute("pasien", tempPasien);
        model.addAttribute("tagihan", tempTagihan);
        //return "form-bayar-tagihan-konfirmasi";

        if (tempPasien.getSaldo() <= tagihan.getJumlahTagihan()) {
            model.addAttribute("message", "Saldo anda tidak cukup");
            return "bayar-tagihan";
        } else {
            tempTagihan.setIsPaid(true);
            tempPasien.setSaldo(tempPasien.getSaldo()-tempTagihan.getJumlahTagihan());
            TagihanModel updatedTagihan = tagihanService.updateTagihan(tempTagihan);
            model.addAttribute("message", "Tagihan berhasil dibayar");
            return "bayar-tagihan";
        }
    }

    @GetMapping("/chart")
    public String viewStatistics(Model model) {
        return "chart";
    }

    /*
    @GetMapping("/pasien/{id}/{kode}/konfirmasi}")
    public String formBayarTagihanSubmit(@ModelAttribute TagihanModel tagihan, PasienModel pasien, Model model) {
        model.addAttribute("pasien", pasien);
        model.addAttribute("tagihan", tagihan);

        if (pasien.getSaldo() <= tagihan.getJumlahTagihan()) {
            model.addAttribute("message", "Saldo anda tidak cukup");
            return "bayar-tagihan";
        } else {
            tagihan.setIsPaid(true);
            pasien.setSaldo(pasien.getSaldo()-tagihan.getJumlahTagihan());
            TagihanModel updatedTagihan = tagihanService.updateTagihan(tagihan);
            model.addAttribute("message", "Tagihan berhasil dibayar");
            return "bayar-tagihan";
        }
    }
    */

}
