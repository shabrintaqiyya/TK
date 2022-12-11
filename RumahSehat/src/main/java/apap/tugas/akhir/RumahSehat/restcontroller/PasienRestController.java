package apap.tugas.akhir.RumahSehat.restcontroller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import apap.tugas.akhir.RumahSehat.model.PasienModel;
import apap.tugas.akhir.RumahSehat.service.PasienRestService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/api/v1/")
public class PasienRestController {
    @Autowired
    private PasienRestService pasienRestService;

    //Add
    @PostMapping(value= "/pasien/add")
    private PasienModel createPasien(@Valid @RequestBody PasienModel pasien, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
            );
        } else {
            log.info("Melakukan registrasi pasien");
            return pasienRestService.createPasien(pasien);
        }
    }

    @GetMapping("/pasien")
	public String displayPasien() {
		return "Display home untuk pasien";
	}
}
