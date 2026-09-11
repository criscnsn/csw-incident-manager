package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.EstadoIncidencia;
import models.Incidencia;
import models.PrioridadIncidencia;
import services.IIncidenciaService;
import services.usecases.exceptions.DatosIncidenciaInvalidosException;
import services.usecases.exceptions.IncidenciaNoEncontradaException;
import services.usecases.exceptions.TransicionEstadoInvalidaException;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class IncidenciasController implements Initializable {

    @FXML
    private TextField campoEquipo;

    @FXML
    private TextField campoUbicacion;

    @FXML
    private TextArea campoDescripcion;

    @FXML
    private ComboBox<PrioridadIncidencia> comboPrioridad;

    @FXML
    private Button botonRegistrar;

    @FXML
    private TextField campoBuscarId;

    @FXML
    private Button botonBuscar;

    @FXML
    private Button botonVerTodas;

    @FXML
    private TableView<Incidencia> tablaIncidencias;

    @FXML
    private TableColumn<Incidencia, String> columnaId;

    @FXML
    private TableColumn<Incidencia, String> columnaEquipo;

    @FXML
    private TableColumn<Incidencia, String> columnaUbicacion;

    @FXML
    private TableColumn<Incidencia, String> columnaDescripcion;

    @FXML
    private TableColumn<Incidencia, PrioridadIncidencia> columnaPrioridad;

    @FXML
    private TableColumn<Incidencia, EstadoIncidencia> columnaEstado;

    @FXML
    private ComboBox<EstadoIncidencia> comboNuevoEstado;

    @FXML
    private Button botonCambiarEstado;

    @FXML
    private Button botonRecargar;

    private IIncidenciaService incidenciaService;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        configurarColumnas();
        inicializarCombos();
    }

    public void setIncidenciaService(final IIncidenciaService incidenciaService) {
        this.incidenciaService = incidenciaService;
        cargarIncidencias();
    }

    private void configurarColumnas() {
        this.tablaIncidencias.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        this.columnaId.setCellValueFactory(new PropertyValueFactory<>("identificador"));
        this.columnaEquipo.setCellValueFactory(new PropertyValueFactory<>("equipoAfectado"));
        this.columnaUbicacion.setCellValueFactory(new PropertyValueFactory<>("ubicacionArea"));
        this.columnaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcionFalla"));
        this.columnaPrioridad.setCellValueFactory(new PropertyValueFactory<>("prioridad"));
        this.columnaEstado.setCellValueFactory(new PropertyValueFactory<>("estadoActual"));
    }

    private void inicializarCombos() {
        this.comboPrioridad.getItems().setAll(PrioridadIncidencia.values());
        this.comboNuevoEstado.getItems().setAll(EstadoIncidencia.values());
    }

    @FXML
    public void handleRegistrar() {
        if (this.incidenciaService == null) {
            return;
        }

        final String equipo = this.campoEquipo.getText();
        final String ubicacion = this.campoUbicacion.getText();
        final String descripcion = this.campoDescripcion.getText();
        final PrioridadIncidencia prioridad = this.comboPrioridad.getValue();

        try {
            final Incidencia registrada = this.incidenciaService.registrarIncidencia(
                    equipo,
                    ubicacion,
                    descripcion,
                    prioridad
            );

            limpiarFormularioRegistro();
            cargarIncidencias();
            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Registro Exitoso",
                    "Incidencia registrada con el ID: " + registrada.getIdentificador()
            );
        } catch (final DatosIncidenciaInvalidosException excepcionDatosInvalidos) {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Datos Inválidos",
                    excepcionDatosInvalidos.getMessage()
            );
        }
    }

    @FXML
    public void handleBuscar() {
        if (this.incidenciaService == null) {
            return;
        }

        final String identificador = this.campoBuscarId.getText();
        if (identificador == null || identificador.isBlank()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Búsqueda", "Ingresa un identificador válido.");
            return;
        }

        try {
            final Incidencia encontrada = this.incidenciaService.buscarIncidenciaPorIdentificador(identificador.trim());
            if (encontrada != null) {
                this.tablaIncidencias.getItems().setAll(List.of(encontrada));
            }
        } catch (final IncidenciaNoEncontradaException excepcionNoEncontrada) {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "No Encontrada",
                    excepcionNoEncontrada.getMessage()
            );
        }
    }

    @FXML
    public void handleVerTodas() {
        this.campoBuscarId.clear();
        cargarIncidencias();
    }

    @FXML
    public void handleCambiarEstado() {
        if (this.incidenciaService == null) {
            return;
        }

        final Incidencia seleccionada = this.tablaIncidencias.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Atención",
                    "Por favor, selecciona una incidencia de la tabla para cambiar su estado."
            );
            return;
        }

        final EstadoIncidencia nuevoEstado = this.comboNuevoEstado.getValue();
        if (nuevoEstado == null) {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Atención",
                    "Selecciona el nuevo estado al que deseas transicionar."
            );
            return;
        }

        try {
            final Incidencia actualizada = this.incidenciaService.cambiarEstadoIncidencia(
                    seleccionada.getIdentificador(),
                    nuevoEstado
            );
            cargarIncidencias();
            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Estado Actualizado",
                    "La incidencia " + seleccionada.getIdentificador() + " ahora está: " + nuevoEstado
            );
        } catch (TransicionEstadoInvalidaException excepcionTransicion) {
            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Transición No Permitida",
                    excepcionTransicion.getMessage()
            );
        } catch (IncidenciaNoEncontradaException excepcionNoEncontrada) {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Error de Búsqueda",
                    excepcionNoEncontrada.getMessage()
            );
        }
    }

    public void cargarIncidencias() {
        if (this.incidenciaService == null || this.tablaIncidencias == null) {
            return;
        }

        final List<Incidencia> listaIncidencias = this.incidenciaService.listarIncidencias();
        if (listaIncidencias != null) {
            this.tablaIncidencias.getItems().setAll(listaIncidencias);
        }
    }

    private void limpiarFormularioRegistro() {
        this.campoEquipo.clear();
        this.campoUbicacion.clear();
        this.campoDescripcion.clear();
        this.comboPrioridad.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(
            final Alert.AlertType tipoAlerta,
            final String titulo,
            final String mensaje
    ) {
        final Alert alerta = new Alert(tipoAlerta);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
