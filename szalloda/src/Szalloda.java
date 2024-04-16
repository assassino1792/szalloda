import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Szalloda {

    private String nev;
    private List<Szoba> szobak;
    private List<Foglalas> foglalasok = new ArrayList<>();

    public Szalloda(String nev, List<Szoba> szobák) {
        this.nev = nev;
        this.szobak = szobák;
    }

    public void addSzoba(Szoba szoba) {
        szobak.add(szoba);
    }



    public boolean lemondFoglalas(int szobaszam, LocalDate datum) {
        Iterator<Foglalas> iterator = foglalasok.iterator();
        while (iterator.hasNext()) {
            Foglalas foglalas = iterator.next();
            if (foglalas.getSzoba().getSzobaszam() == szobaszam && foglalas.getDatum().isEqual(datum)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public int foglalSzoba(int szobaszam, LocalDate datum, String foglaloNev){
        for (Foglalas foglalas : foglalasok ) {
            if (foglalas.getSzoba().getSzobaszam()==szobaszam && foglalas.getDatum().isEqual(datum) ){
                throw new IllegalStateException("A szoba ezen a napon már foglalt.");
            }
        }
        for (Szoba szoba : szobak ) {
            if (szoba.getSzobaszam() == szobaszam) {
                Foglalas ujFoglalas = new Foglalas(szoba, datum, foglaloNev);
                foglalasok.add(ujFoglalas);
                return szoba.getAr();
            }
        }
        throw new IllegalArgumentException( "Nincs ilyen számú szoba a szállodában." );
    }

    // Foglalások listázása
    public void listazFoglalasok() {
        if (foglalasok.isEmpty()) {
            System.out.println("Nincsenek jelenleg foglalások.");
            return;
        }
        System.out.println("Foglalások listája:");
        for (Foglalas foglalas : foglalasok) {
            Szoba szoba = foglalas.getSzoba();
            LocalDate datum = foglalas.getDatum();
            String foglaloNev = foglalas.getFoglaloNev();
            System.out.printf("Szobaszám: %d, Dátum: %s, Foglaló neve: %s, Ár: %d Ft\n",
                    szoba.getSzobaszam(), datum, foglaloNev, szoba.getAr());
        }
    }

}
