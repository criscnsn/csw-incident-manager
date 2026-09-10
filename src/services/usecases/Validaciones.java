package services.usecases;

public class Validaciones {
    protected static boolean validarId(String id){
        return id.matches(  "^INC-\\d{3,}?$");
    }
}
