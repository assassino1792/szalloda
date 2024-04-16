import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class SzallodaInterface {
    private Szalloda szalloda;
    private Scanner scanner;

    public SzallodaInterface() {
        this.szalloda = new Szalloda("Grand Tokaj", new ArrayList<>());
        this.szalloda.addSzoba(new EgyagyasSzoba(1, 20000));
        this.szalloda.addSzoba(new KetagyasSzoba(2, 30000));
        this.szalloda.addSzoba(new KetagyasSzoba(3, 40000));
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("Válassz egy opciót:");
            System.out.println("1. Foglalás");
            System.out.println("2. Lemondás");
            System.out.println("3. Listázás");
            System.out.println("4. Kilépés");
            System.out.print("Opció: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    handleReservation();
                    break;
                case 2:
                    handleCancellation();
                    break;
                case 3:
                    szalloda.listazFoglalasok();
                    break;
                case 4:
                    System.out.println("Kilépés...");
                    return;
                default:
                    System.out.println("Érvénytelen opció.");
            }
        }
    }

    private void handleReservation() {
        int roomNumber = 0;
        while (true) {
            System.out.print("Add meg a szobaszámot (1, 2, 3): ");
            roomNumber = scanner.nextInt();
            if (roomNumber >= 1 && roomNumber <= 3) {
                break;  
            } else {
                System.out.println("Nincs ilyen szobaszám. Válasszon a megadott szobák közül!");
            }
        }
        System.out.print("Add meg a dátumot (yyyy-mm-dd): ");
        String dateStr = scanner.next();
        LocalDate date = LocalDate.parse(dateStr);
        System.out.print("Add meg a foglaló nevét: ");
        String reserverName = scanner.next();

        if (date.isBefore(LocalDate.now())) {
            System.out.println("A dátum nem lehet a múltban!");
            return;
        }

        try {
            int price = szalloda.foglalSzoba(roomNumber, date, reserverName);
            System.out.println("Foglalás sikeres. Ár: " + price + " Ft");
        } catch (IllegalStateException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleCancellation() {
        System.out.print("Add meg a szobaszámot: ");
        int roomNumber = scanner.nextInt();
        System.out.print("Add meg a dátumot (yyyy-mm-dd): ");
        String dateStr = scanner.next();
        LocalDate date = LocalDate.parse(dateStr);
        if (szalloda.lemondFoglalas(roomNumber, date)) {
            System.out.println("Lemondás sikeres.");
        } else {
            System.out.println("Nincs ilyen foglalás.");
        }
    }

    public static void main(String[] args) {
        new SzallodaInterface().start();
    }
}
