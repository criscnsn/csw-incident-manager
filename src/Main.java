import services.IIncidenciaService;
import services.IncidenciaService;
import storage.IIncidenciaStorage;
import storage.IncidenciaInMemoryStorage;
import ui.IView;
import ui.JavaFxView;

public class Main {
    public static void main(String[] args) {
        final IIncidenciaStorage incidenciaStorage = new IncidenciaInMemoryStorage();
        final IIncidenciaService incidenciaService = new IncidenciaService(incidenciaStorage);

        final IView view = new JavaFxView(incidenciaService);
        view.show();
    }
}