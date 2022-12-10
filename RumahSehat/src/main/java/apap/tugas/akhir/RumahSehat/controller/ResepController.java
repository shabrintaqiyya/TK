package apap.tugas.akhir.RumahSehat.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tugas.akhir.RumahSehat.model.JumlahModel;
import apap.tugas.akhir.RumahSehat.model.ObatModel;
import apap.tugas.akhir.RumahSehat.model.ResepModel;
import apap.tugas.akhir.RumahSehat.service.AppointmentService;
import apap.tugas.akhir.RumahSehat.service.ObatService;
import apap.tugas.akhir.RumahSehat.service.ResepService;

@Controller
@RequestMapping("/user")
public class ResepController {
    @Qualifier("resepServiceImpl")
    @Autowired
    private ResepService resepService;

    @Autowired
    private ObatService obatService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/resep/add/{kodeAppointment}")
    public String addResepFormPage(Model model, @PathVariable String kodeAppointment) {
        ResepModel resep = new ResepModel();
        resep.setKodeAppointment(appointmentService.getAppointmentByKode(kodeAppointment));
        resep.setIsDone(false);
        resep.setConfirmerUuid(null);

        JumlahModel jumlah = new JumlahModel();
        jumlah.setObat(new ObatModel());

        List<ObatModel> listObat = obatService.getListObat();
        // List<ObatModel> listObatNew = new ArrayList<>();
        // List<JumlahModel> listJumlah = jumlahService.getListJumlah();
        List<JumlahModel> listJumlahNew = new ArrayList<>();
        listJumlahNew.add(jumlah);

        // resep.setListObat(listObatNew);
        // resep.getListObat().add(new ObatModel());
        // resep.getListJumlah().add(jumlah);
        resep.setListJumlah(listJumlahNew);

        model.addAttribute("resep", resep);
        model.addAttribute("listObat", listObat);
        model.addAttribute("kodeAppointment", kodeAppointment);
        // model.addAttribute("listJumlah", listJumlah);
        return "form-add-resep";
    }

    @PostMapping("/resep/add/{kodeAppointment}")
    public String addResepSubmitPage(@ModelAttribute ResepModel resep, @PathVariable String kodeAppointment, Model model) {
        for (JumlahModel jml : resep.getListJumlah()) {
            ObatModel obat = jml.getObat();
            ObatModel obatDb = obatService.getObatByIdObat(obat.getIdObat());
            if (jml.getKuantitas() > obatDb.getStok()) {
                // model.addAttribute("resep", resep);
                return "add-resep-failed";
            } else {
                continue;
            }
        }

        for (JumlahModel jml : resep.getListJumlah()) {
            ObatModel obat = jml.getObat();
            ObatModel obatDb = obatService.getObatByIdObat(obat.getIdObat());
            obatDb.setStok(obatDb.getStok() - jml.getKuantitas());
            jml.setObat(obatDb);
            jml.setResep(resep);
            // jumlahService.addJumlah(jml);

            // ini harusnya disimpen dulu ke list baru biar kl ternyata ada obat yg gagal yg lainnya ga terlanjur diapa2in
            // jumlahService.addJumlah(jml);
            // obatService.updateStok(obat);
        }

        resep.setCreatedAt(LocalDateTime.now());
        resep.setIsDone(false);
        // resep.setKodeAppointment(appointmentService.getAppointmentByKode(resep.getKodeAppointment()));
        resep.setKodeAppointment(resep.getKodeAppointment()); //bnr gak ya
        resep.setConfirmerUuid(null);
        resepService.addResep(resep);

        model.addAttribute("idResep", resep.getId());
        return "add-resep";
    }

    @PostMapping(value="/resep/add/{kodeAppointment}", params={"addRow"})
    private String addRowResepMultiple(@ModelAttribute ResepModel resep, @PathVariable String kodeAppointment, Model model) {
        if (resep.getListJumlah() == null || resep.getListJumlah().size() == 0) {
            resep.setListJumlah((new ArrayList<>()));
        }
        JumlahModel jumlah = new JumlahModel();
        jumlah.setObat(new ObatModel());
        resep.getListJumlah().add(jumlah);
        // course.getListPenyelenggara().add(new PenyelenggaraModel());
        List<ObatModel> listObat = obatService.getListObat();

        model.addAttribute("resep", resep);
        model.addAttribute("listObat", listObat);

        return "form-add-resep";
    }

    @PostMapping(value="/resep/add/{kodeAppointment}", params={"deleteRow"})
    private String deleteRowCourseMultiple(@ModelAttribute ResepModel resep, @PathVariable String kodeAppointment, @RequestParam("deleteRow") Integer row, Model model) {
        final Integer rowId = Integer.valueOf(row);
        resep.getListJumlah().remove(rowId.intValue());

        List<ObatModel> listObat = obatService.getListObat();

        model.addAttribute("resep", resep);
        model.addAttribute("listObat", listObat);

        return "form-add-resep";
    }

    @GetMapping("/resep/viewall")
    public String listCourse(Model model) {
        List<ResepModel> listResep = resepService.getListResep();
        model.addAttribute("listResep", listResep);
        return "viewall-resep";
    }
}
