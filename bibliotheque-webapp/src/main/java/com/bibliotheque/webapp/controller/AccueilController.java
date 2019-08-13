package com.bibliotheque.webapp.controller;

import io.swagger.client.api.LivreApi;
import com.bibliotheque.webapp.consumer.FakeLivres;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

@Controller
public class AccueilController {
    private LivreApi livreApi;

    public AccueilController(LivreApi livreApi) {
        this.livreApi = livreApi;
    }

    private FakeLivres fakeLivres = new FakeLivres();

    private Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:9090/fab24/bibliotheque-livres/1.0.0/")
            .addConverterFactory(GsonConverterFactory.create()).build();

    private LivreApi service = retrofit.create(LivreApi.class);

    @GetMapping("/")
    public String accueil(Model model) {
        return "accueil";
    }

    @GetMapping("livres")
    public String livres(Model model) {
        model.addAttribute("livres", fakeLivres.livres);
        return "livres";
    }

    @GetMapping("livres/{id}")
    public String livre(Model model, @PathVariable Long id) throws IOException {

        model.addAttribute("livre", service.getLivreById(id).execute().body());
        return "livre";
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
