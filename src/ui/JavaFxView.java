package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.IIncidenciaService;

import java.io.IOException;

public class JavaFxView extends Application implements IView {

    private static IIncidenciaService servicioCompartido;
    private final IIncidenciaService incidenciaService;

    public JavaFxView() {
        this.incidenciaService = servicioCompartido;
    }

    public JavaFxView(final IIncidenciaService incidenciaService) {
        this.incidenciaService = incidenciaService;
        servicioCompartido = incidenciaService;
    }

    @Override
    public void show() {
        servicioCompartido = this.incidenciaService;
        Application.launch(JavaFxView.class);
    }

    @Override
    public void start(final Stage stagePrincipal) throws IOException {
        stagePrincipal.setTitle("Sistema Gestor de Incidencias");

        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/IncidenciasView.fxml"));
        final Parent vistaRaiz = loader.load();

        final IncidenciasController controlador = loader.getController();
        if (controlador != null) {
            controlador.setIncidenciaService(this.incidenciaService);
        }

        final Scene escena = new Scene(vistaRaiz, 1050, 640);
        stagePrincipal.setScene(escena);
        stagePrincipal.show();
    }
}
