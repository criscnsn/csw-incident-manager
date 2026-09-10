package ui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Incidencia;
import services.IIncidenciaService;

import java.util.List;

public class JavaFxView implements IView {

    private final IIncidenciaService incidenciaService;
    private TableView<Incidencia> tablaIncidencias;

    public JavaFxView(final IIncidenciaService incidenciaService) {
        this.incidenciaService = incidenciaService;
    }

    @Override
    public void show() {
        try {
            Platform.startup(this::inicializarVentanaPrincipal);
        } catch (final IllegalStateException excepcionPlataformaYaIniciada) {
            // El toolkit de JavaFX ya fue inicializado en la JVM, se reutiliza el hilo de UI
            Platform.runLater(this::inicializarVentanaPrincipal);
        }
    }

    private void inicializarVentanaPrincipal() {
        final Stage stagePrincipal = new Stage();
        stagePrincipal.setTitle("Sistema Gestor de Incidencias");

        final BorderPane layoutContenedor = new BorderPane();
        layoutContenedor.setPadding(new Insets(16));

        final VBox panelSuperior = crearPanelSuperior();
        final VBox panelCentral = crearPanelListado();

        layoutContenedor.setTop(panelSuperior);
        layoutContenedor.setCenter(panelCentral);

        final Scene escena = new Scene(layoutContenedor, 900, 550);
        stagePrincipal.setScene(escena);
        stagePrincipal.show();

        recargarListaIncidencias();
    }

    private VBox crearPanelSuperior() {
        final Label etiquetaTitulo = new Label("Gestor de Incidencias");
        etiquetaTitulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        final Label etiquetaSubtitulo = new Label("Panel de seguimiento y control de fallas en aulas y laboratorios");
        etiquetaSubtitulo.setStyle("-fx-font-size: 12px; -fx-text-fill: #666666;");

        final VBox contenedorEncabezado = new VBox(4, etiquetaTitulo, etiquetaSubtitulo);
        contenedorEncabezado.setPadding(new Insets(0, 0, 16, 0));
        return contenedorEncabezado;
    }

    private VBox crearPanelListado() {
        this.tablaIncidencias = construirTablaIncidencias();

        final Button botonRefrescar = new Button("Actualizar lista");
        botonRefrescar.setOnAction(evento -> recargarListaIncidencias());

        final HBox barraAcciones = new HBox(10, botonRefrescar);
        barraAcciones.setAlignment(Pos.CENTER_RIGHT);
        barraAcciones.setPadding(new Insets(8, 0, 0, 0));

        return new VBox(8, this.tablaIncidencias, barraAcciones);
    }

    private TableView<Incidencia> construirTablaIncidencias() {
        final TableView<Incidencia> tabla = new TableView<>();
        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        final TableColumn<Incidencia, String> columnaId = new TableColumn<>("ID");
        columnaId.setCellValueFactory(new PropertyValueFactory<>("identificador"));
        columnaId.setMinWidth(90);

        final TableColumn<Incidencia, String> columnaEquipo = new TableColumn<>("Equipo");
        columnaEquipo.setCellValueFactory(new PropertyValueFactory<>("equipoAfectado"));
        columnaEquipo.setMinWidth(140);

        final TableColumn<Incidencia, String> columnaUbicacion = new TableColumn<>("Ubicación");
        columnaUbicacion.setCellValueFactory(new PropertyValueFactory<>("ubicacionArea"));
        columnaUbicacion.setMinWidth(140);

        final TableColumn<Incidencia, String> columnaDescripcion = new TableColumn<>("Descripción");
        columnaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcionFalla"));
        columnaDescripcion.setMinWidth(220);

        final TableColumn<Incidencia, String> columnaPrioridad = new TableColumn<>("Prioridad");
        columnaPrioridad.setCellValueFactory(new PropertyValueFactory<>("prioridad"));
        columnaPrioridad.setMinWidth(90);

        final TableColumn<Incidencia, String> columnaEstado = new TableColumn<>("Estado");
        columnaEstado.setCellValueFactory(new PropertyValueFactory<>("estadoActual"));
        columnaEstado.setMinWidth(110);

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
        if (this.tablaIncidencias == null) {
            return;
        }
        final List<Incidencia> listaActualizada = this.incidenciaService.listarIncidencias();
        this.tablaIncidencias.getItems().setAll(listaActualizada);
    }
}
