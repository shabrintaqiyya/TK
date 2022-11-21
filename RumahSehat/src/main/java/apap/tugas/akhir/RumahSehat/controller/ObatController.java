package apap.tugas.akhir.RumahSehat.controller;

import apap.tugas.akhir.RumahSehat.model.ObatModel;
import apap.tugas.akhir.RumahSehat.service.DokterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import apap.tugas.akhir.RumahSehat.service.ObatService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class ObatController {
    @Autowired
    private ObatService obatService;

    @GetMapping("/viewall-obat")
    public String listObat(Model model){
        List<ObatModel> listObat = obatService.getListObat();
        model.addAttribute("listObat", listObat);
        return "viewall-obat";
    }

}
