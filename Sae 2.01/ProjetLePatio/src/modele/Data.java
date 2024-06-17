package modele;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Data {
        public static ObservableList<Reservation> reservations = FXCollections.observableArrayList();

        public static void chargementDonnees() throws ParseException {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

                // Dates de réservation
                Date date1 = formatter.parse("07/07/2013");
                Date date2 = formatter.parse("05/11/2023");
                Date date3 = formatter.parse("06/04/2000");
                Date date4 = formatter.parse("10/12/2024");
                Date date5 = formatter.parse("15/08/2022");

                // Date d'événement
                Date dateEvenement1 = formatter.parse("09/10/2023");
                Date dateEvenement2 = formatter.parse("15/12/2024");

                // Création des artistes
                Artiste artiste1 = new Artiste("MichMich");
                Artiste artiste2 = new Artiste("Joe LaCrème");
                Artiste artiste3 = new Artiste("Laurel et Hardy");

                // Création des spectacles
                Spectacle spectacle1 = new Spectacle("Toullec Comedie Club", 3, 500, "Comique", artiste1);
                Spectacle spectacle2 = new Spectacle("hrst", 3, 500, "ahah", artiste1);
                Spectacle spectacle3 = new Spectacle("Le grand show de Joe", 2, 600, "Variété", artiste2);
                Spectacle spectacle4 = new Spectacle("Laurel et Hardy en action", 1, 450, "Comédie", artiste3);

                // Création des zones
                Zone zone1 = new Zone("test", spectacle1);
                Zone zone2 = new Zone("prout", spectacle2);
                Zone zone3 = new Zone("VIP", spectacle3);
                Zone zone4 = new Zone("Famille", spectacle4);

                // Création des représentations
                Representation representation1 = new Representation("mardi", "8h39", true, spectacle1);
                Representation representation2 = new Representation("jeudi", "20h00", true, spectacle2);
                Representation representation3 = new Representation("samedi", "14h30", true, spectacle3);
                Representation representation4 = new Representation("dimanche", "19h00", true, spectacle4);

                // Création des billets, tarifs et affectations
                Tarif t1 = new Tarif(60.00);
                Tarif t2 = new Tarif(90.00);
                Tarif t3 = new Tarif(120.00);
                Tarif t4 = new Tarif(80.00);
                t1.ajouterTarif(zone1, spectacle1);
                t2.ajouterTarif(zone2, spectacle2);
                t3.ajouterTarif(zone3, spectacle3);
                t4.ajouterTarif(zone4, spectacle4);

                Fauteuil fauteuil1 = new Fauteuil("A1", "normal", zone1);
                Fauteuil fauteuil2 = new Fauteuil("B2", "VIP", zone3);
                Fauteuil fauteuil3 = new Fauteuil("C3", "VIP", zone3);
                Fauteuil fauteuil4 = new Fauteuil("D4", "VIP", zone3);
                Fauteuil fauteuil5 = new Fauteuil("E5", "VIP", zone3);
                Fauteuil fauteuil6 = new Fauteuil("F6", "famille", zone4);
                Fauteuil fauteuil7 = new Fauteuil("G7", "famille", zone4);
                Fauteuil fauteuil8 = new Fauteuil("H8", "normal", zone2);
                Fauteuil fauteuil9 = new Fauteuil("I9", "normal", zone2);
                Fauteuil fauteuil10 = new Fauteuil("J10", "normal", zone2);
                Jeune jeune = new Jeune();
                Billet billet1 = new Billet("q78r", fauteuil1, jeune);
                Billet billet2 = new Billet("y56u", fauteuil2, jeune);
                Billet billet3 = new Billet("p54e", fauteuil3, jeune);
                Billet billet4 = new Billet("g2c4", fauteuil4, jeune);
                Billet billet5 = new Billet("f1d3", fauteuil5, jeune);
                Billet billet6 = new Billet("m5h7", fauteuil6, jeune);
                Billet billet7 = new Billet("l3g6", fauteuil7, jeune);
                Billet billet8 = new Billet("k6j8", fauteuil8, jeune);
                Billet billet9 = new Billet("z9h3", fauteuil9, jeune);
                Billet billet10 = new Billet("x4n6", fauteuil10, jeune);

                // Création des clients
                Client client1 = new Client("John", "Doe", "123 Main St, New York", "123-456-7890",
                                "john.doe@example.com",
                                "FG9J0V");
                Client client2 = new Client("Jane", "Doe", "456 Elm St, Los Angeles", "987-654-3210",
                                "jane.doe@example.com",
                                "LPP0A2");
                Client client3 = new Client("Jim", "Smith", "789 Pine St, Chicago", "456-789-0123",
                                "jim.smith@example.com",
                                "KJH0P9");
                Client client4 = new Client("Mike", "Smith", "101 Oak St, Miami", "654-321-0987",
                                "mike.smith@example.com",
                                "HJ0P9L");
                Client client5 = new Client("Charlie", "Chaplin", "456 Walnut St, San Francisco", "415-654-3210",
                                "charliec@ee.com", "CC98UH");
                Client client6 = new Client("Hugo", "Dujardin", "1 Avenue des Champs-Elysées, Paris", "0123456789",
                                "hugo.dujardin@example.com", "HD567A");

                // Création des réservations sans facture
                Reservation reservation1 = new Reservation("00000001", date2, dateEvenement1, billet1, representation1,
                                client1);
                Reservation reservation2 = new Reservation("00000002", date3, dateEvenement1, billet2, representation3,
                                client2);
                Reservation reservation3 = new Reservation("00000003", date1, dateEvenement1, billet3, representation3,
                                client3);
                Reservation reservation4 = new Reservation("00000004", date2, dateEvenement2, billet4, representation3,
                                client4);
                Reservation reservation5 = new Reservation("00000005", date3, dateEvenement2, billet5, representation3,
                                client5);
                Reservation reservation6 = new Reservation("00000006", date4, dateEvenement2, billet6, representation4,
                                client6);
                Reservation reservation7 = new Reservation("00000007", date5, dateEvenement2, billet7, representation4,
                                client1);
                Reservation reservation8 = new Reservation("00000008", date1, dateEvenement2, billet8, representation2,
                                client2);
                Reservation reservation9 = new Reservation("00000009", date2, dateEvenement2, billet9, representation2,
                                client3);
                Reservation reservation10 = new Reservation("00000010", date3, dateEvenement2, billet10,
                                representation2, client4);

                reservation1.affecterBillet(billet2);
                reservation1.affecterBillet(billet3);
                reservation1.affecterBillet(billet4);

                // Ajout des réservations à la liste observable
                reservations.addAll(reservation1, reservation2, reservation3, reservation4, reservation5,
                                reservation6, reservation7, reservation8, reservation9, reservation10);
        }
}