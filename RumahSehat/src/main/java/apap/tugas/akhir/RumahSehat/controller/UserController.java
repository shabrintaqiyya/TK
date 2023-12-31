package apap.tugas.akhir.RumahSehat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import apap.tugas.akhir.RumahSehat.model.ApotekerModel;
import apap.tugas.akhir.RumahSehat.model.DokterModel;
import apap.tugas.akhir.RumahSehat.model.PasienModel;
import apap.tugas.akhir.RumahSehat.service.DokterService;
import apap.tugas.akhir.RumahSehat.service.PasienService;
import apap.tugas.akhir.RumahSehat.service.ApotekerService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ApotekerService apotekerService;

    @Autowired
    private DokterService dokterService;

    @Autowired
    private PasienService pasienService;

    // add dokter
    @GetMapping("/add-dokter")
    private String addDokterFormPage(Model model) {
        DokterModel dokter = new DokterModel();
        model.addAttribute("dokter", dokter);
        // log.info("Menambahkan dokter");
        return "form-add-dokter";
    }

    @PostMapping("/add-dokter") 
    private String addDokterSubmit(@ModelAttribute DokterModel dokter, Model model) {
        dokter.setIsSso(false);

        dokterService.addDokter(dokter);

        model.addAttribute("dokter", dokter);
        log.info("Admin menambahkan dokter baru");
        return "redirect:/";
    }
    
    // add apoteker
    @GetMapping("/add-apoteker")
    private String addApotekerFormPage(Model model) {
        ApotekerModel apoteker = new ApotekerModel();
        model.addAttribute("apoteker", apoteker);
        // log.info("Menambahkan apoteker");
        return "form-add-apoteker";
    }

    @PostMapping("/add-apoteker") 
    private String addApotekerSubmit(@ModelAttribute ApotekerModel apoteker, Model model) {
        apoteker.setIsSso(false);

        apotekerService.addApoteker(apoteker);

        model.addAttribute("user", apoteker);
        log.info("Admin menambahkan apoteker");
        return "redirect:/";
    }

    @GetMapping("/add-pasien")
    private String addPasienFormPage(Model model) {
        PasienModel pasien = new PasienModel();
        model.addAttribute("pasien", pasien);
        // log.info("Menambahkan pasien");
        return "form-add-pasien";
    }

    @PostMapping("/add-pasien")
    private String addPasienSubmit(@ModelAttribute PasienModel pasien, Model model) {
        pasien.setIsSso(false);

        pasienService.addPasien(pasien);

        model.addAttribute("user", pasien);
        log.info("Admin menambahkan pasien");
        return "redirect:/";
    }

    @GetMapping("/viewall-dokter")
    public String listDokter(Model model){
        List<DokterModel> listDokter = dokterService.getListDokter();
        model.addAttribute("listDokter", listDokter);
        log.info("Admin melihat daftar dokter");
        return "viewall-dokter";
    }

    @GetMapping("/viewall-apoteker")
    public String listApoteker(Model model){
        List<ApotekerModel> listApoteker = apotekerService.getListApoteker();
        model.addAttribute("listApoteker", listApoteker);
        log.info("Admin melihat daftar apoteker");
        return "viewall-apoteker";
    }

    @GetMapping("/viewall-pasien")
    public String listPasien(Model model){
        List<PasienModel> listPasien = pasienService.getListPasien();
        model.addAttribute("listPasien", listPasien);
        log.info("Admin melihat daftar pasien");
        return "viewall-pasien";
    }

    @GetMapping("/update")
    public String updatePasswordFormPage(Model model){
        return "form-update-password";
    }
}
