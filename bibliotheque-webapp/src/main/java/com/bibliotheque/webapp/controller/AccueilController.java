package com.bibliotheque.webapp.controller;

import io.swagger.client.api.LivreApi;
import io.swagger.client.model.Livre;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

@Controller
public class AccueilController {

    private Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:9090/fab24/bibliotheque-livres/1.0.0/")
            .addConverterFactory(GsonConverterFactory.create()).build();
    private LivreApi service = retrofit.create(LivreApi.class);

    @GetMapping("/")
    public String accueil(Model model) {
        return "accueil";
    }

    @GetMapping("/livres")
    public String livres(Model model,
                         @RequestParam(value = "search", required = false, defaultValue = "") String search)
            throws IOException {
        model.addAttribute("livres", service.findLivres(search).execute().body());
        return "livres";
    }

    @GetMapping("/livres/{id}")
    public String livre(Model model, @PathVariable Long id) throws IOException {
        Livre livre = service.getLivreById(id).execute().body();
        model.addAttribute("livre", livre);
        return "livre";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        return "profile";
    }

    @GetMapping("inscription")
    public String inscription() {
        return "inscription";
    }

    @GetMapping("reservation")
    public String reservation() {
        return "reservation";
    }

    @GetMapping("contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("connexion")
    public String connexion() {
        return "connexion";
    }


}
