package apap.tugas.akhir.RumahSehat.controller;

import apap.tugas.akhir.RumahSehat.model.AppointmentModel;
import apap.tugas.akhir.RumahSehat.model.DokterModel;
import apap.tugas.akhir.RumahSehat.model.PasienModel;
import apap.tugas.akhir.RumahSehat.model.TagihanModel;
import apap.tugas.akhir.RumahSehat.model.UserModel;
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

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @Qualifier("dokterServiceImpl")
    @Autowired
    private DokterService dokterService;

    @RequestMapping("/appointment/{username}")
    public String jadwalAppointmentAdmin(
        @PathVariable(value = "username") String username, 
        Model model
    ) {
        System.out.println("MULAII");
        UserModel user = userService.getUserByUsername(username);
        System.out.println("11111111");
        System.out.println(user.getUsername());
        if (user.getRole().equals("Dokter")) {
            DokterModel dokter = dokterService.getDokterByUsername(username);
            System.out.println("dokter");
            System.out.println("aaaaaa: "+dokter.getUsername());
            List<AppointmentModel> listAppointmentDokter = dokter.getListAppointment();
            model.addAttribute("listAppointmentDokter", listAppointmentDokter);
            return "viewall-appointment-dokter";
        }
        List<AppointmentModel> listAppointment = appointmentService.getListAppointment();
        model.addAttribute("listAppointment", listAppointment);
        return "viewall-appointment-admin";
    }

    @GetMapping("/appointment/view/{kode}")
    public String viewDetailAppointmentPage(@PathVariable String kode, Model model) {
        AppointmentModel appointment = appointmentService.getAppointmentByKode(kode);
        
        if (!appointment.getIsDone()) {
            model.addAttribute("appointment", appointment);
            return "view-appointment";
        }
        model.addAttribute("appointment", appointment);
        model.addAttribute("resep", appointment.getResep());
        model.addAttribute("resepDone", appointment.getResep().getIsDone());
        return "view-appointment-done";
    }

    @GetMapping("/appointment/done/{kode}")
    public String updateDoneAppointment(@PathVariable String kode, Model model) {
        AppointmentModel appointment = appointmentService.getAppointmentByKode(kode);
        if (appointment.getResep() == null || appointment.getResep().getIsDone()) {
            appointment.setIsDone(true);
            appointmentService.setDoneAppointment(appointment);
            TagihanModel tagihan = appointmentService.setTagihan(appointment);
            model.addAttribute("kode", appointment.getKode());
            model.addAttribute("tagihan", tagihan);
        }
        return "appointment-done";
    }
}
