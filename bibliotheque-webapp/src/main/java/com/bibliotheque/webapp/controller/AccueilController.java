package com.bibliotheque.webapp.controller;

import io.swagger.client.api.LivreApi;
import com.bibliotheque.webapp.consumer.FakeLivres;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AccueilController {
    private LivreApi livreApi;

    public AccueilController(LivreApi livreApi) {
        this.livreApi = livreApi;
    }

    private FakeLivres fakeLivres = new FakeLivres();

    @GetMapping("/")
    public String accueil(Model model) {
        return "accueil";
    }

    @GetMapping("/test")
    public String test(Model model) {
        return "test";
    }

    @GetMapping("/livres")
    public String livres(Model model) {
        model.addAttribute("livres", fakeLivres.livres);
        return "livres";
    }

    @GetMapping("livres/{id}")
    public String livre(Model model, @PathVariable Long id) {
        model.addAttribute("livre", fakeLivres.livres.get((Math.toIntExact(id) - 1)));
        return "livre";
    }
}
