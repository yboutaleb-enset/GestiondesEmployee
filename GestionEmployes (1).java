import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

// Classe représentant un employé
class Employe {
    private int id;
    private String nom;
    private String poste;
    private double salaire;

    // Constructeur par défaut
    public Employe() {}

    // Constructeur avec paramètres
    public Employe(int id, String nom, String poste, double salaire) {
        this.id = id;
        this.nom = nom;
        this.poste = poste;
        this.salaire = salaire;
    }

    // Getters et Setters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getPoste() { return poste; }
    public double getSalaire() { return salaire; }

    public void setNom(String nom) { this.nom = nom; }
    public void setPoste(String poste) { this.poste = poste; }
    public void setSalaire(double salaire) { this.salaire = salaire; }

    // Méthode pour afficher les informations de l'employé
    @Override
    public String toString() {
        return "ID: " + id + ", Nom: " + nom + ", Poste: " + poste + ", Salaire: " + salaire;
    }

    // Comparateur pour trier par salaire
    public static int compareParSalaire(Employe e1, Employe e2) {
        return Double.compare(e1.getSalaire(), e2.getSalaire());
    }
}

// Classe principale de gestion des employés
public class GestionEmployes {
    private static Employe[] employes = new Employe[50]; // Tableau d'employés (limité à 50)
    private static int count = 0; // Nombre d'employés enregistrés
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choix;
        do {
            printMenu();
            System.out.print("Entrez votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();
            traiterChoix(choix);
        } while (choix != 0);
    }

    // Affichage du menu principal
    public static void printMenu() {
        System.out.println("\n--- Menu Gestion des Employés ---");
        System.out.println("1. Ajouter un employé");
        System.out.println("2. Modifier un employé");
        System.out.println("3. Supprimer un employé");
        System.out.println("4. Afficher tous les employés");
        System.out.println("5. Rechercher un employé");
        System.out.println("6. Calculer la masse salariale");
        System.out.println("7. Trier les employés par salaire");
        System.out.println("0. Quitter");
    }

    // Traitement des choix utilisateur
    public static void traiterChoix(int choix) {
        switch (choix) {
            case 1:
                ajouterEmploye();
                break;
            case 2:
                modifierEmploye();
                break;
            case 3:
                supprimerEmploye();
                break;
            case 4:
                afficherEmployes();
                break;
            case 5:
                rechercherEmploye();
                break;
            case 6:
                System.out.println("Masse salariale totale : " + calculerMasseSalariale());
                break;
            case 7:
                trierEmployesParSalaire();
                break;
            case 0:
                System.out.println("Au revoir!");
                break;
            default:
                System.out.println("Choix invalide.");
        }
    }

    // Ajout d'un employé
    public static void ajouterEmploye() {
        if (count >= 50) {
            System.out.println("Nombre maximum d'employés atteint.");
            return;
        }
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nom: ");
        String nom = scanner.nextLine();
        System.out.print("Poste: ");
        String poste = scanner.nextLine();
        System.out.print("Salaire: ");
        double salaire = scanner.nextDouble();
        employes[count++] = new Employe(id, nom, poste, salaire);
        System.out.println("Employé ajouté.");
    }

    // Modification d'un employé
    public static void modifierEmploye() {
        System.out.print("ID de l'employé à modifier: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < count; i++) {
            if (employes[i].getId() == id) {
                System.out.print("Nouveau nom: ");
                employes[i].setNom(scanner.nextLine());
                System.out.print("Nouveau poste: ");
                employes[i].setPoste(scanner.nextLine());
                System.out.print("Nouveau salaire: ");
                employes[i].setSalaire(scanner.nextDouble());
                System.out.println("Employé modifié.");
                return;
            }
        }
        System.out.println("Employé non trouvé.");
    }

    // Suppression d'un employé
    public static void supprimerEmploye() {
        System.out.print("ID de l'employé à supprimer: ");
        int id = scanner.nextInt();
        for (int i = 0; i < count; i++) {
            if (employes[i].getId() == id) {
                employes[i] = employes[count - 1];
                employes[count - 1] = null;
                count--;
                System.out.println("Employé supprimé.");
                return;
            }
        }
        System.out.println("Employé non trouvé.");
    }

    // Affichage des employés
    public static void afficherEmployes() {
        for (int i = 0; i < count; i++) {
            System.out.println(employes[i]);
        }
    }

    // Recherche d'un employé
    public static void rechercherEmploye() {
        System.out.print("Nom ou poste : ");
        String critere = scanner.nextLine().toLowerCase();
        for (int i = 0; i < count; i++) {
            if (employes[i].getNom().toLowerCase().contains(critere) || employes[i].getPoste().toLowerCase().contains(critere)) {
                System.out.println(employes[i]);
            }
        }
    }

    // Calcul de la masse salariale
    public static double calculerMasseSalariale() {
        double total = 0;
        for (int i = 0; i < count; i++) {
            total += employes[i].getSalaire();
        }
        return total;
    }
}
