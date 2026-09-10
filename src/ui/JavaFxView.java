package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import models.Incidencia;
import services.IIncidenciaService;

import java.util.List;

public class JavaFxView extends Application implements IView {

    private static IIncidenciaService servicioCompartido;
    private final IIncidenciaService incidenciaService;
    private TableView<Incidencia> tablaIncidencias;

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
    public void start(final Stage stagePrincipal) {
        stagePrincipal.setTitle("Listado de Incidencias");

        this.tablaIncidencias = construirTablaIncidencias();
        recargarListaIncidencias();

        final StackPane layoutPrincipal = new StackPane(this.tablaIncidencias);
        final Scene escena = new Scene(layoutPrincipal, 800, 450);

        stagePrincipal.setScene(escena);
        stagePrincipal.show();
    }

    private TableView<Incidencia> construirTablaIncidencias() {
        final TableView<Incidencia> tabla = new TableView<>();
        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        final TableColumn<Incidencia, String> columnaId = new TableColumn<>("ID");
        columnaId.setCellValueFactory(new PropertyValueFactory<>("identificador"));

        final TableColumn<Incidencia, String> columnaEquipo = new TableColumn<>("Equipo");
        columnaEquipo.setCellValueFactory(new PropertyValueFactory<>("equipoAfectado"));

        final TableColumn<Incidencia, String> columnaUbicacion = new TableColumn<>("Ubicación");
        columnaUbicacion.setCellValueFactory(new PropertyValueFactory<>("ubicacionArea"));

        final TableColumn<Incidencia, String> columnaDescripcion = new TableColumn<>("Descripción");
        columnaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcionFalla"));

        final TableColumn<Incidencia, String> columnaPrioridad = new TableColumn<>("Prioridad");
        columnaPrioridad.setCellValueFactory(new PropertyValueFactory<>("prioridad"));

        final TableColumn<Incidencia, String> columnaEstado = new TableColumn<>("Estado");
        columnaEstado.setCellValueFactory(new PropertyValueFactory<>("estadoActual"));

        tabla.getColumns().addAll(
                columnaId,
                columnaEquipo,
                columnaUbicacion,
                columnaDescripcion,
                columnaPrioridad,
                columnaEstado
        );

        return tabla;
    }

    private void recargarListaIncidencias() {
        if (this.tablaIncidencias == null || this.incidenciaService == null) {
            return;
        }
        final List<Incidencia> listaIncidencias = this.incidenciaService.listarIncidencias();
        if (listaIncidencias != null) {
            this.tablaIncidencias.getItems().setAll(listaIncidencias);
        }
    }
}

