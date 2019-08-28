package com.bibliotheque.webapp.controller;

import io.swagger.client.api.LivreApi;
import io.swagger.client.api.UtilisateurApi;
import io.swagger.client.model.Livre;
import io.swagger.client.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class AccueilController {
    private Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:9090/fab24/bibliotheque-livres/1.0.0/")
            .addConverterFactory(GsonConverterFactory.create()).build();
    private LivreApi serviceLivre = retrofit.create(LivreApi.class);
    private UtilisateurApi serviceUtilisateur = retrofit.create(UtilisateurApi.class);

    @GetMapping("/")
    public String accueil(Model model, HttpSession session, HttpServletRequest request) {
        System.out.println(session.getAttribute("utilisateurId"));
        return "accueil";
    }

    @GetMapping("/livres")
    public String livres(Model model,
                         @RequestParam(value = "search", required = false, defaultValue = "") String search)
            throws IOException {
        model.addAttribute("livres", serviceLivre.findLivres(search).execute().body());
        return "livres";
    }

    @GetMapping("/livres/{id}")
    public String livre(Model model, @PathVariable Long id) throws IOException {
        Livre livre = serviceLivre.getLivreById(id).execute().body();
        model.addAttribute("livre", livre);
        return "livre";
    }
    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) throws IOException {
        List<Livre> livres = null;
        if (session.getAttribute("utilisateurId") != null) {
            String idToConvert = String.valueOf(session.getAttribute("utilisateurId"));
            Utilisateur utilisateur = serviceUtilisateur.getUtilisateurById(Long.parseLong(idToConvert)).execute().body();
            model.addAttribute("utilisateur", utilisateur);
            model.addAttribute("livres", livres);
            return "profile";
        }
        return "redirect:/connexion";
    }

    @GetMapping("/inscription")
    public String inscription() {
        return "inscription";
    }

    @GetMapping("/reservation")
    public String reservation() {
        return "reservation";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/connexion")
    public String connexion() {
        return "connexion";
    }

    @PostMapping("/connexionPost")
    public String connexionPost(HttpSession session,
                                @RequestParam(value = "mail", required = false, defaultValue = "") String mail,
                                @RequestParam(value = "password", required = false, defaultValue = "") String password)
            throws IOException {
        System.out.println("Entrée");

        if (serviceUtilisateur.connectUser(mail, password).execute().code() == 200) {
            Utilisateur utilisateur = serviceUtilisateur.findUtilisateursByMail(mail).execute().body();
            System.out.println("200 OK");
            if (utilisateur != null) {
                session.setAttribute("utilisateurId", utilisateur.getId());
                System.out.println("user trouvé");
                return "redirect:/";
            }
        }
        System.out.println("Fail");
        return "redirect:/connexion";
    }

    @GetMapping("/deconnexion")
    public String deconnexion(HttpSession session) {
        session.removeAttribute("utilisateurId");
        return "redirect:/";
    }
}
