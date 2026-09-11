package ui;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.IIncidenciaService;

import java.io.IOException;

public class JavaFxView implements IView {

    private final IIncidenciaService incidenciaService;

    public JavaFxView(final IIncidenciaService incidenciaService) {
        this.incidenciaService = incidenciaService;
    }

    @Override
    public void show() {
        Platform.startup(() -> {
            try {
                final Stage stagePrincipal = new Stage();
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
            } catch (IOException e) {
                throw new IllegalStateException("Error al cargar la vista FXML", e);
            }
        });
    }

}
