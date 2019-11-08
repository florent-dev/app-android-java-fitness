package iut2.tp;

public class Multiplication {

    public static int TABLE = 1;
    private int nombre;

    public Multiplication(int nombre) {
        this.nombre = nombre;
    }

    public static void setTable(int table) {
        TABLE = table;
    }

    public static Multiplication[] getMultiplications() {
        Multiplication[] m = new Multiplication[9];
        for (int i = 0; i < 9 ; i++) {
            m[i] = new Multiplication(i+1);
        }
        return m;
    }

    public int getOperande1() {
        return this.nombre;
    }

    public int getOperande2() {
        return TABLE;
    }

    public boolean checkResult(int result) {
        return (result == (this.nombre * TABLE));
    }
}
