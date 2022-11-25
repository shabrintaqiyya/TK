package apap.tugas.akhir.RumahSehat.controller;

import apap.tugas.akhir.RumahSehat.model.AppointmentModel;
import apap.tugas.akhir.RumahSehat.model.DokterModel;
import apap.tugas.akhir.RumahSehat.model.PasienModel;
import apap.tugas.akhir.RumahSehat.model.ResepModel;
import apap.tugas.akhir.RumahSehat.service.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class AppointmentController {
    @Qualifier("appointmentServiceImpl")
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/appointment")
    public String jadwalAppointment(Model model) {
        List<AppointmentModel> listAppointment = appointmentService.getListAppointment();
        // List<AppointmentModel> listAppointmentDokter = ;
        // model.addAttribute("jadwalAppointment", jadwalAppointment);
        model.addAttribute("listAppointment", listAppointment);
        return "viewall-appointment";
    }

    @GetMapping("/appointment/{kode}")
    public String viewDetailAppointmentPage(@PathVariable String kode, Model model) {
        AppointmentModel appointment = appointmentService.getAppointmentByKode(kode);
        for (ResepModel resep : appointment.getListResep()) {
            if (resep.getIsDone()) {
                
            }
        }
        if (!appointment.getIsDone()) {
            model.addAttribute("appointment", appointment);
            return "view-appointment";
        }
        model.addAttribute("appointment", appointment);
        return "view-appointment-done";
    }
}
