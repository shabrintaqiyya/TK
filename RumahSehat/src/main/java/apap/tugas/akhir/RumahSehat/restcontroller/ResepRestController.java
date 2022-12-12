package apap.tugas.akhir.RumahSehat.restcontroller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import apap.tugas.akhir.RumahSehat.model.ResepModel;
import apap.tugas.akhir.RumahSehat.service.ResepRestService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class ResepRestController {
    @Autowired
    private ResepRestService resepRestService;

    @GetMapping("/resep/detail/{id}")
    private ResepModel getData(@PathVariable("id") Long id) {
        try {
            return resepRestService.getResepById(id);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Resep with id " + id + " not found"
            );
        }
    }
}




// package apap.tugas.akhir.RumahSehat.rest;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import apap.tugas.akhir.RumahSehat.model.ResepModel;
// import apap.tugas.akhir.RumahSehat.service.ResepService;

// @RestController
// @RequestMapping("/api/resep")
// public class ResepDetail {
//     @Autowired
//     ResepService resepService;

//     @GetMapping(value = "/detailresep/{id}")
//     private ResepModel getData(@PathVariable Long id) {
//         ResepModel resep = resepService.getResepById(id);
//         return resep;
//     }
// }
