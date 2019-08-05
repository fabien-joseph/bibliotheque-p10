package com.bibliotheque.webapp.consumer;

import com.bibliotheque.webapp.model.FakeLivre;

import java.util.ArrayList;
import java.util.List;

public class FakeLivres {
    public List<FakeLivre> livres = new ArrayList<>();

    public FakeLivres() {
        FakeLivre harryPotter = new FakeLivre();

        harryPotter.setId(1L);
        harryPotter.setAuteur("J.K Rowlin");
        harryPotter.setNom("Harry Potter");
        harryPotter.setImgUrl("https://images-na.ssl-images-amazon.com/images/I/81OdLM-ZyuL.jpg");
        harryPotter.setQuantite(7);
        livres.add(harryPotter);

        FakeLivre leLabyrinthe = new FakeLivre();
        leLabyrinthe.setId(2L);
        leLabyrinthe.setAuteur("James Dashner");
        leLabyrinthe.setNom("Le Labyrinthe");
        leLabyrinthe.setImgUrl("https://images-na.ssl-images-amazon.com/images/I/91IJ2rIOEDL.jpg");
        leLabyrinthe.setQuantite(18);
        livres.add(leLabyrinthe);

        FakeLivre lesMiserables = new FakeLivre();
        lesMiserables.setId(3L);
        lesMiserables.setAuteur("Victor Hugo");
        lesMiserables.setNom("Les misérables");
        lesMiserables.setImgUrl("https://images-eu.ssl-images-amazon.com/images/I/510ypkdwIYL.jpg");
        lesMiserables.setQuantite(42);
        livres.add(lesMiserables);

        FakeLivre sherlock = new FakeLivre();
        sherlock.setId(4L);
        sherlock.setAuteur("Arthur Conan Doyle");
        sherlock.setNom("Sherlock Holmes");
        sherlock.setImgUrl("https://images-na.ssl-images-amazon.com/images/I/413FajzGBGL._SX327_BO1,204,203,200_.jpg");
        sherlock.setQuantite(8);
        livres.add(sherlock);

        FakeLivre orgueilEtPrejuges = new FakeLivre();
        orgueilEtPrejuges.setId(5L);
        orgueilEtPrejuges.setAuteur("Jane Austen");
        orgueilEtPrejuges.setNom("Orguil et préjugés");
        orgueilEtPrejuges.setImgUrl("http://ressources.bragelonne.fr/img/livres/2017-10/1710-Orgueiletprejugescollector_org.jpg");
        orgueilEtPrejuges.setQuantite(8);
        livres.add(orgueilEtPrejuges);

        FakeLivre hurlevent = new FakeLivre();
        hurlevent.setId(5L);
        hurlevent.setAuteur("Emilie Brontë");
        hurlevent.setNom("Les Hauts de Hurle-Vent");
        hurlevent.setImgUrl("https://images-na.ssl-images-amazon.com/images/I/51V6VR99Z0L._SX321_BO1,204,203,200_.jpg");
        hurlevent.setQuantite(8);
        livres.add(hurlevent);

        FakeLivre number1984 = new FakeLivre();
        number1984.setId(6L);
        number1984.setAuteur("George Orwell");
        number1984.setNom("1984");
        number1984.setImgUrl("https://ec56229aec51f1baff1d-185c3068e22352c56024573e929788ff.ssl.cf1.rackcdn.com/attachments/large/2/2/1/005479221.jpg");
        number1984.setQuantite(8);
        livres.add(number1984);

        FakeLivre etranger = new FakeLivre();
        etranger.setId(7L);
        etranger.setAuteur("Albert Camus");
        etranger.setNom("L'Étranger");
        etranger.setImgUrl("https://images-na.ssl-images-amazon.com/images/I/51MYDidib7L._SX366_BO1,204,203,200_.jpg");
        etranger.setQuantite(8);
        livres.add(etranger);

        FakeLivre odyssee = new FakeLivre();
        odyssee.setId(8L);
        odyssee.setAuteur("Homère");
        odyssee.setNom("L'Odyssée");
        odyssee.setImgUrl("https://images-na.ssl-images-amazon.com/images/I/41h2kjLuajL._SX328_BO1,204,203,200_.jpg");
        odyssee.setQuantite(8);
        livres.add(odyssee);

    }
}
