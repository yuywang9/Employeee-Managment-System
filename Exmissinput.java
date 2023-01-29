public class Exmissinput extends Exception {
    public Exmissinput(String message) {
        super("Missing input: Please give the name of the acting head for " + message);
    }
}
