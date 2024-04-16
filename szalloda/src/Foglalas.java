import java.time.LocalDate;

public class Foglalas {
    private Szoba szoba;
    private LocalDate datum;
    private String foglaloNev;

    public Foglalas(Szoba szoba, LocalDate datum, String foglaloNev) {
        this.szoba = szoba;
        this.datum = datum;
        this.foglaloNev = foglaloNev;
    }

    public Szoba getSzoba() {
        return szoba;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public String getFoglaloNev() {
        return foglaloNev;
    }

}
