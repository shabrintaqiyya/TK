package apap.tugas.akhir.RumahSehat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class PageController {
    @RequestMapping("/")
    private String home() {
        return "home";
    }

    @RequestMapping("/login")
    private String login(Model model) {
        // model.addAttribute("port", serverProperties.getPort());
        return "login";
    }
}
