public abstract class  Szoba {
    protected int ar;
    protected int szobaszam;


    public Szoba(int szobaszam, int ar) {
        this.szobaszam = szobaszam;
        this.ar = ar;
    }

    public int getAr() {
        return ar;
    }

    public int getSzobaszam() {
        return szobaszam;
    }
}
