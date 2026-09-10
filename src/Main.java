import models.Incidencia;
import repository.IIncidenciaRepository;
import repository.IncidenciaInMemory;
import services.IIncidenciaService;
import services.IncidenciaService;
import services.usecases.BuscarIncidenciaPorId;
import services.usecases.CambiarEstadoIncidencia;
import services.usecases.ListarIncidencias;
import services.usecases.RegistrarIncidencia;
import ui.IView;

import javax.swing.text.View;

public class Main {
    public static void main(String[] args) {
        IIncidenciaRepository<Incidencia> incidenciaRepository = new IncidenciaInMemory();

        BuscarIncidenciaPorId buscarIncidenciaPorId = new BuscarIncidenciaPorId(incidenciaRepository);
        CambiarEstadoIncidencia cambiarEstadoIncidencia = new CambiarEstadoIncidencia(incidenciaRepository);
        ListarIncidencias listarIncidencias = new ListarIncidencias(incidenciaRepository);
        RegistrarIncidencia registrarIncidencia = new RegistrarIncidencia(incidenciaRepository);

        IIncidenciaService incidenciaService = new IncidenciaService(buscarIncidenciaPorId, cambiarEstadoIncidencia, listarIncidencias, registrarIncidencia);

        //Falta implementar la interfaz IView
//        IView view;
//        view.show();
    }
}