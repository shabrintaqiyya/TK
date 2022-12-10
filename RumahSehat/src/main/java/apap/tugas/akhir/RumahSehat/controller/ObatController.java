package apap.tugas.akhir.RumahSehat.controller;

import apap.tugas.akhir.RumahSehat.model.ObatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import apap.tugas.akhir.RumahSehat.service.ObatService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/user")
public class ObatController {

    @Autowired
    private ObatService obatService;

    @PostMapping("/updateObat/idObat")
    public String updateObatSubmitPage(
            @ModelAttribute ObatModel obat, Model model,
            RedirectAttributes redirAttrs
    ) {
        if (obat.getStok() == null ){
            redirAttrs.addFlashAttribute("error", "Gagal mengupdate stok obat");
            return "redirect:/user/viewall-obat";
        }

        ObatModel updatedObat = obatService.updateObat(obat);
        if (updatedObat == null) {
            redirAttrs.addFlashAttribute("error", "Gagal mengupdate stok obat");
            return "redirect:/user/viewall-obat";
        }

//        model.addAttribute("idObat", updatedObat.getIdObat());
        redirAttrs.addFlashAttribute("success", "Berhasil mengupdate stok obat");
        return "redirect:/user/viewall-obat";
    }

    @GetMapping("/viewall-obat")
    public String listObat(Model model){
        List<ObatModel> listObat = obatService.getListObat();
        model.addAttribute("listObat", listObat);
        return "viewall-obat";
    }

    @RequestMapping("/update/{idObat}")
    public String updateObatFormPage(
            @PathVariable(value = "idObat") String idObat,
            Model model
    ) {
        ObatModel obat = obatService.getObatByIdObat(idObat);
        model.addAttribute("obat", obat);
        return "form-update-obat";
    }




}
