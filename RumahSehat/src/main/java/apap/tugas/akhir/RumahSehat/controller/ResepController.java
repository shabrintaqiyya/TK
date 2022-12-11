package apap.tugas.akhir.RumahSehat.controller;

import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tugas.akhir.RumahSehat.model.AppointmentModel;
import apap.tugas.akhir.RumahSehat.model.JumlahModel;
import apap.tugas.akhir.RumahSehat.model.ObatModel;
import apap.tugas.akhir.RumahSehat.model.ResepModel;
import apap.tugas.akhir.RumahSehat.model.TagihanModel;
import apap.tugas.akhir.RumahSehat.model.UserModel;
import apap.tugas.akhir.RumahSehat.service.AppointmentService;
import apap.tugas.akhir.RumahSehat.service.TagihanService;
import apap.tugas.akhir.RumahSehat.service.UserService;
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
    private UserService userService;

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
        // model.addAttribute("listJumlah", listJumlahNew);

        log.info("Dokter membuat resep");
        return "form-add-resep";
    }

    @PostMapping("/resep/add/{kodeAppointment}")
    public String addResepSubmitPage(@ModelAttribute ResepModel resep, @PathVariable String kodeAppointment, Model model) {
        for (JumlahModel jml : resep.getListJumlah()) {
            ObatModel obat = jml.getObat();
            ObatModel obatDb = obatService.getObatByIdObat(obat.getIdObat());
            if (jml.getKuantitas() > obatDb.getStok()) {
                // model.addAttribute("resep", resep);
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
        log.info("Resep berhasil dibuat");
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
        AppointmentModel appointment = appointmentService.getAppointmentByKodeModel(resep.getKodeAppointment());
        model.addAttribute("listJumlah", listJumlah);
        model.addAttribute("resep", resep);
        model.addAttribute("appointment", appointment);
        log.info("User melihat detail resep");
        return "view-resep";
    }

    @PostMapping("/resep/konfirmasi")
    public String konfirmasiResepSubmitPage(@ModelAttribute ResepModel resep, Model model) {
        ResepModel resepEx = resepService.getResepById(resep.getId());
        System.out.print(resepEx.getId());
        AppointmentModel appointment = appointmentService.getAppointmentByKodeModel(resepEx.getKodeAppointment());
        resepEx.setIsDone(true);

        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // String currentPrincipalName = auth.getName();
        // for (UserModel user : userService.getListUser()) {
        //     if (user.getNama().equals(currentPrincipalName)) {
        //         // confirmerUuid = user.getId();
        //         resepEx.setConfirmerUuid(user);
        //     }
        // }
        appointment.setIsDone(true);

        resepService.addResep(resepEx);
        appointmentService.addAppointment(appointment);

        TagihanModel tagihan = new TagihanModel();
        tagihan.setTanggalTerbuat(LocalDateTime.now());
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

        log.info("Apotek memverifikasi resep");
        return "konfirmasi-resep";
    }
}
