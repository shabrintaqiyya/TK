package apap.tugas.akhir.RumahSehat.restcontroller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import apap.tugas.akhir.RumahSehat.service.AppointmentRestService;
import apap.tugas.akhir.RumahSehat.service.UserRestService;
import apap.tugas.akhir.RumahSehat.service.DokterRestService;
import apap.tugas.akhir.RumahSehat.model.AppointmentModel;
import apap.tugas.akhir.RumahSehat.model.DokterModel;
import apap.tugas.akhir.RumahSehat.model.UserModel;

@RestController
@RequestMapping("/api/v1")
public class AppointmentRestController {
    @Autowired
    private AppointmentRestService appointmentRestService;

    @Autowired
    private UserRestService userRestService;

    @Autowired
    private DokterRestService dokterRestService;
    
    @PostMapping(value = "add/appointment")
    private AppointmentModel createAppointment(@Valid @RequestBody AppointmentModel appointment, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
            );
        }
        else {
            return appointmentRestService.createAppointment(appointment);
        }
    }


    @GetMapping(value = "appointment/{username}")
    public List<AppointmentModel> retrieveListAppointment(@PathVariable("username") String username) {
        UserModel user = userRestService.getUserByUsername(username);
        if (user.getRole().equals("Dokter")) {
            DokterModel dokter = dokterRestService.getDokterByUsername(username);
            return dokterRestService.getListAppointmentInDokter(dokter);
        }
        return appointmentRestService.retrieveListAppointment();
    }

    // Retrieve
    @GetMapping(value = "appointment/{kode}")
    private AppointmentModel retrieveApt(@PathVariable("kode") String kode) {
        try {
            return appointmentRestService.getAppointmentByKode(kode);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Appointment kode " + kode + " not found"
            );
        }
    }
}