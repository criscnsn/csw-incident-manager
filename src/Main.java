import models.Incidencia;
import repository.IIncidenciaRepository;
import repository.IncidenciaInMemory;
import services.IIncidenciaService;
import services.IncidenciaService;
import ui.IView;
import ui.JavaFxView;

public class Main {
    public static void main(String[] args) {
        IIncidenciaRepository<Incidencia> incidenciaRepository = new IncidenciaInMemory();
        IIncidenciaService incidenciaService = new IncidenciaService(incidenciaRepository);

        IView view = new JavaFxView(incidenciaService);
        view.show();
    }
}