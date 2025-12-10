import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Bambino{
   private final String nome;
    private final String cognome;
    private final String codiceFiscale;
    private final int annoNascita;  
    

 public Bambino(String nome, String cognome, String codiceFiscale, int annoNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.annoNascita = annoNascita;
    }
    public String toCsv() {
        return String.join(";", codiceFiscale, nome, cognome, Integer.toString(annoNascita));
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Path csv = Path.of("bambino.csv");

        System.out.println("Inserisci bambino. Digita 'exit' per terminare.");
        while (true) {
            System.out.print("Nome (o 'exit' per uscire): ");
            String nome = sc.nextLine().trim();
            if (nome.equalsIgnoreCase("exit")) break;

            System.out.print("Cognome: ");
            String cognome = sc.nextLine().trim();

            System.out.print("Codicefiscale: ");
            String codiceFiscale = sc.nextLine().trim();

            System.out.print("Anno nascita: ");
            String annoInput = sc.nextLine().trim();
            int anno;
            try {
                anno = Integer.parseInt(annoInput);
            } catch (NumberFormatException e) {
                System.out.println("Anno non valido, riprova.\n");
                continue;
            }

            Bambino s = new Bambino(nome, cognome, codiceFiscale, anno);
            Files.writeString(
                csv,
                s.toCsv() + System.lineSeparator(),
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
            );
            System.out.println("Salvato: " + s.toCsv());

            System.out.print("Premi Invio per aggiungere un altro bambino o digita 'exit' per uscire: ");
            if (sc.nextLine().trim().equalsIgnoreCase("exit")) {
                break;
            }
        }
        System.out.println("Programma terminato.");
    }
}
