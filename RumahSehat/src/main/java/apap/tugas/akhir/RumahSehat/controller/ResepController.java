package apap.tugas.akhir.RumahSehat.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tugas.akhir.RumahSehat.model.ApotekerModel;
import apap.tugas.akhir.RumahSehat.model.AppointmentModel;
import apap.tugas.akhir.RumahSehat.model.JumlahModel;
import apap.tugas.akhir.RumahSehat.model.ObatModel;
import apap.tugas.akhir.RumahSehat.model.ResepModel;
import apap.tugas.akhir.RumahSehat.model.TagihanModel;
import apap.tugas.akhir.RumahSehat.service.ApotekerService;
import apap.tugas.akhir.RumahSehat.service.AppointmentService;
import apap.tugas.akhir.RumahSehat.service.TagihanService;
import apap.tugas.akhir.RumahSehat.service.ObatService;
import apap.tugas.akhir.RumahSehat.service.ResepService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/user")
public class ResepController {
    @Qualifier("resepServiceImpl")
    @Autowired
    private ResepService resepService;

    @Autowired
    private ObatService obatService;

    @Autowired
    private TagihanService tagihanService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private ApotekerService apotekerService;

    @GetMapping("/resep/add/{kodeAppointment}")
    public String addResepFormPage(Model model, @PathVariable String kodeAppointment) {
        ResepModel resep = new ResepModel();
        resep.setKodeAppointment(appointmentService.getAppointmentByKode(kodeAppointment));
        resep.setIsDone(false);
        resep.setConfirmerUuid(null);

        JumlahModel jumlah = new JumlahModel();
        jumlah.setObat(new ObatModel());

        List<ObatModel> listObat = obatService.getListObat();
        List<JumlahModel> listJumlahNew = new ArrayList<>();
        listJumlahNew.add(jumlah);

        resep.setListJumlah(listJumlahNew);

        model.addAttribute("resep", resep);
        model.addAttribute("listObat", listObat);
        model.addAttribute("kodeAppointment", kodeAppointment);

        log.info("Dokter membuat resep");
        return "form-add-resep";
    }

    @PostMapping("/resep/add/{kodeAppointment}")
    public String addResepSubmitPage(@ModelAttribute ResepModel resep, @PathVariable String kodeAppointment, Model model) {
        for (JumlahModel jml : resep.getListJumlah()) {
            ObatModel obat = jml.getObat();
            ObatModel obatDb = obatService.getObatByIdObat(obat.getIdObat());
            if (jml.getKuantitas() > obatDb.getStok()) {
                log.info("Resep gagal dibuat");
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
        }

        resep.setCreatedAt(LocalDateTime.now(ZoneId.of("Asia/Jakarta")));
        resep.setIsDone(false);
        resep.setKodeAppointment(resep.getKodeAppointment());
        resep.setConfirmerUuid(null);
        resepService.addResep(resep);

        model.addAttribute("idResep", resep.getId());
        log.info(String.format("Resep dengan id ", resep.getId(), "berhasil dibuat"));
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
        List<ObatModel> listObat = obatService.getListObat();

        model.addAttribute("resep", resep);
        model.addAttribute("listObat", listObat);

        log.info("User menambah row obat pada form resep");
        return "form-add-resep";
    }

    @PostMapping(value="/resep/add/{kodeAppointment}", params={"deleteRow"})
    private String deleteRowCourseMultiple(@ModelAttribute ResepModel resep, @PathVariable String kodeAppointment, @RequestParam("deleteRow") Integer row, Model model) {
        final Integer rowId = Integer.valueOf(row);
        resep.getListJumlah().remove(rowId.intValue());

        List<ObatModel> listObat = obatService.getListObat();

        model.addAttribute("resep", resep);
        model.addAttribute("listObat", listObat);

        log.info("User menghapus row obat pada form resep");
        return "form-add-resep";
    }

    @GetMapping("/resep/viewall")
    public String listCourse(Model model) {
        List<ResepModel> listResep = resepService.getListResep();
        model.addAttribute("listResep", listResep);
        log.info("User melihat list seluruh resep");
        return "viewall-resep";
    }

    @GetMapping("/resep/view")
    public String viewDetailResepPage(@RequestParam(value = "id") Long id, Model model){
        ResepModel resep = resepService.getResepById(id);
        List<JumlahModel> listJumlah = resep.getListJumlah();
        AppointmentModel appointment = resep.getKodeAppointment();
        model.addAttribute("listJumlah", listJumlah);
        model.addAttribute("resep", resep);
        model.addAttribute("appointment", appointment);
        log.info(String.format("User melihat detail resep dengan id ", resep.getId()));
        return "view-resep";
    }

    @PostMapping("/resep/konfirmasi")
    public String konfirmasiResepSubmitPage(@ModelAttribute ResepModel resep, Model model) {
        ResepModel resepEx = resepService.getResepById(resep.getId());
        AppointmentModel appointment = appointmentService.getAppointmentByKodeModel(resepEx.getKodeAppointment());
        resepEx.setIsDone(true);
        appointment.setIsDone(true);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = auth.getName();
        ApotekerModel apoteker = apotekerService.getApotekerByUsername(currentPrincipalName);
        resepEx.setConfirmerUuid(apoteker);

        resepService.addResep(resepEx);
        appointmentService.addAppointment(appointment);

        TagihanModel tagihan = new TagihanModel();
        tagihan.setTanggalTerbuat(LocalDateTime.now(ZoneId.of("Asia/Jakarta")));
        tagihan.setIsPaid(false);
        tagihan.setKodeAppointment(appointment);

        int price = appointment.getDokter().getTarif();
        for (JumlahModel jml : resepEx.getListJumlah()) {
            int hargaSatuan = jml.getObat().getHarga();
            int hargaTotal = hargaSatuan * jml.getKuantitas();
            price+=hargaTotal;
        }
        tagihan.setJumlahTagihan(price);
        tagihanService.addTagihan(tagihan);

        model.addAttribute("idResep", resepEx.getId());

        log.info(String.format("Apoteker memverifikasi resep dengan id ", resep.getId()));
        return "konfirmasi-resep";
    }
}
