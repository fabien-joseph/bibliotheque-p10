package com.bibliotheque.webapp.consumer;

import io.swagger.client.model.Livre;

import java.util.ArrayList;
import java.util.List;

public class FakeLivres {
    public List<Livre> livres = new ArrayList<>();

    public FakeLivres() {
        Livre harryPotter = new Livre();

        harryPotter.setId(1L);
        harryPotter.setAuteur("J.K Rowlin");
        harryPotter.setNom("Harry Potter et la chambre des secrets");
        harryPotter.setImgUrl("https://images-na.ssl-images-amazon.com/images/I/81OdLM-ZyuL.jpg");
        harryPotter.setQuantite(7);
        harryPotter.setAnnee(1998);
        harryPotter.setResume("Hinc ille commotus ut iniusta perferens et indigna praefecti custodiam protectoribus " +
                "mandaverat fidis. quo conperto Montius tunc quaestor acer quidem sed ad lenitatem propensior, consulens" +
                " in commune advocatos palatinarum primos scholarum adlocutus est mollius docens nec decere haec fieri " +
                "nec prodesse addensque vocis obiurgatorio sonu quod si id placeret, post statuas Constantii deiectas " +
                "super adimenda vita praefecto conveniet securius cogitari.");
        harryPotter.setGenre(Livre.GenreEnum.FANTASTIQUE);
        livres.add(harryPotter);

        Livre leLabyrinthe = new Livre();
        leLabyrinthe.setId(2L);
        leLabyrinthe.setAuteur("James Dashner");
        leLabyrinthe.setNom("Le Labyrinthe");
        leLabyrinthe.setImgUrl("https://images-na.ssl-images-amazon.com/images/I/91IJ2rIOEDL.jpg");
        leLabyrinthe.setQuantite(18);
        livres.add(leLabyrinthe);

        Livre lesMiserables = new Livre();
        lesMiserables.setId(3L);
        lesMiserables.setAuteur("Victor Hugo");
        lesMiserables.setNom("Les misérables");
        lesMiserables.setImgUrl("https://images-eu.ssl-images-amazon.com/images/I/510ypkdwIYL.jpg");
        lesMiserables.setQuantite(42);
        livres.add(lesMiserables);

        Livre sherlock = new Livre();
        sherlock.setId(4L);
        sherlock.setAuteur("Arthur Conan Doyle");
        sherlock.setNom("Sherlock Holmes");
        sherlock.setImgUrl("https://images-na.ssl-images-amazon.com/images/I/413FajzGBGL._SX327_BO1,204,203,200_.jpg");
        sherlock.setQuantite(8);
        livres.add(sherlock);

        Livre orgueilEtPrejuges = new Livre();
        orgueilEtPrejuges.setId(5L);
        orgueilEtPrejuges.setAuteur("Jane Austen");
        orgueilEtPrejuges.setNom("Orguil et préjugés");
        orgueilEtPrejuges.setImgUrl("http://ressources.bragelonne.fr/img/livres/2017-10/1710-Orgueiletprejugescollector_org.jpg");
        orgueilEtPrejuges.setQuantite(8);
        livres.add(orgueilEtPrejuges);

        Livre hurlevent = new Livre();
        hurlevent.setId(5L);
        hurlevent.setAuteur("Emilie Brontë");
        hurlevent.setNom("Les Hauts de Hurle-Vent");
        hurlevent.setImgUrl("https://images-na.ssl-images-amazon.com/images/I/51V6VR99Z0L._SX321_BO1,204,203,200_.jpg");
        hurlevent.setQuantite(8);
        livres.add(hurlevent);

        Livre number1984 = new Livre();
        number1984.setId(6L);
        number1984.setAuteur("George Orwell");
        number1984.setNom("1984");
        number1984.setImgUrl("https://ec56229aec51f1baff1d-185c3068e22352c56024573e929788ff.ssl.cf1.rackcdn.com/attachments/large/2/2/1/005479221.jpg");
        number1984.setQuantite(8);
        livres.add(number1984);

        Livre etranger = new Livre();
        etranger.setId(7L);
        etranger.setAuteur("Albert Camus");
        etranger.setNom("L'Étranger");
        etranger.setImgUrl("https://images-na.ssl-images-amazon.com/images/I/51MYDidib7L._SX366_BO1,204,203,200_.jpg");
        etranger.setQuantite(8);
        livres.add(etranger);

        Livre odyssee = new Livre();
        odyssee.setId(8L);
        odyssee.setAuteur("Homère");
        odyssee.setNom("L'Odyssée");
        odyssee.setImgUrl("https://images-na.ssl-images-amazon.com/images/I/41h2kjLuajL._SX328_BO1,204,203,200_.jpg");
        odyssee.setQuantite(8);
        livres.add(odyssee);

    }
}
