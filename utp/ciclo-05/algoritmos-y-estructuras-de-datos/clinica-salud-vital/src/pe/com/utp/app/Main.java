package pe.com.utp.app;

import pe.com.utp.algoritmos.busqueda.BusquedaDoctor;
import pe.com.utp.algoritmos.ordenamiento.BubbleSortPacientes;
import pe.com.utp.algoritmos.ordenamiento.MergeSortPagos;
import pe.com.utp.algoritmos.ordenamiento.QuickSort;
import pe.com.utp.algoritmos.recursividad.ReporteRecursivo;
import pe.com.utp.estructuras.arboles.ArbolDoctoresBusqueda;
import pe.com.utp.estructuras.colas.ColaPacientes;
import pe.com.utp.estructuras.listas.ListaCitas;
import pe.com.utp.estructuras.listas.ListaPacientes;
import pe.com.utp.estructuras.matrices.MatrizHorarios;
import pe.com.utp.estructuras.pilas.PilaHistorialCitas;
import pe.com.utp.interfaces.Pagable;
import pe.com.utp.interfaces.Registrable;
import pe.com.utp.interfaces.Reportable;
import pe.com.utp.modelo.cita.Cita;
import pe.com.utp.modelo.cita.HistorialCita;
import pe.com.utp.modelo.enums.Especialidad;
import pe.com.utp.modelo.enums.EstadoCita;
import pe.com.utp.modelo.enums.TipoPago;
import pe.com.utp.modelo.enums.TurnoConsultaAmbulatoria;
import pe.com.utp.modelo.pago.Boleta;
import pe.com.utp.modelo.pago.Factura;
import pe.com.utp.modelo.pago.Pago;
import pe.com.utp.modelo.persona.Administrador;
import pe.com.utp.modelo.persona.Doctor;
import pe.com.utp.modelo.persona.Paciente;
import pe.com.utp.modelo.tratamiento.Receta;
import pe.com.utp.modelo.tratamiento.Tratamiento;
import pe.com.utp.persistencia.ArchivoCita;
import pe.com.utp.persistencia.ArchivoPaciente;
import pe.com.utp.persistencia.ArchivoPago;
import pe.com.utp.servicios.CitaService;
import pe.com.utp.servicios.PacienteService;
import pe.com.utp.servicios.PagoService;
import pe.com.utp.servicios.ReporteService;
import pe.com.utp.util.Consola;
import pe.com.utp.util.GeneradorCodigo;
import pe.com.utp.util.UtilidadesFecha;
import pe.com.utp.util.Validador;

import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

/*
 * Clase principal del sistema.
 * Menu de sustentacion: cada opcion muestra un proceso clinico y el tema AED/POO aplicado.
 */
public class Main {

    public static void main(String[] args) {
        DatosSistema datos = crearDatosSilenciosos();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        System.out.println("\nDatos base cargados correctamente.");
        do {
            mostrarMenu();
            opcion = leerOpcion(scanner);
            ejecutarOpcion(opcion, datos);
        } while (opcion != 0);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n========== CLINICA SALUD VITAL ==========");
        System.out.println("1. Presentacion del sistema y datos base");
        System.out.println("2. Gestion de pacientes");
        System.out.println("3. Gestion de citas y horarios");
        System.out.println("4. Sala de espera de pacientes");
        System.out.println("5. Historial reciente y seguimiento clinico");
        System.out.println("6. Busquedas y ordenamientos");
        System.out.println("7. Arbol binario de doctores");
        System.out.println("8. Tratamientos, recetas y pagos");
        System.out.println("9. Persistencia, reportes y utilidades");
        System.out.println("10. Demostracion completa AED");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    private static int leerOpcion(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void ejecutarOpcion(int opcion, DatosSistema datos) {
        switch (opcion) {
            case 1:
                presentarSistema(datos);
                break;
            case 2:
                mostrarPacientes(datos);
                demostrarBusquedaActualizacionEliminacion(datos);
                demostrarListasEnlazadas(datos);
                break;
            case 3:
                demostrarMatrizYCitas(datos);
                break;
            case 4:
                demostrarCola(datos);
                break;
            case 5:
                demostrarPila(datos);
                demostrarTratamientoReceta(datos);
                break;
            case 6:
                demostrarBusquedaDoctores(datos);
                demostrarBubbleSort(datos);
                demostrarQuickSort(datos);
                demostrarRecursividad(datos);
                break;
            case 7:
                demostrarArbol(datos);
                break;
            case 8:
                demostrarTratamientoReceta(datos);
                demostrarPagos(datos);
                break;
            case 9:
                demostrarPersistenciaYReportes(datos);
                demostrarUtilidades(datos);
                break;
            case 10:
                ejecutarDemostracionCompleta(datos);
                break;
            case 0:
                System.out.println("\nSistema finalizado correctamente.");
                break;
            default:
                System.out.println("\nOpcion no valida.");
                break;
        }
    }

    private static DatosSistema crearDatosSilenciosos() {
        PrintStream salidaOriginal = System.out;

        try {
            // Consola: se silencia la carga inicial para que el menu sea limpio al exponer.
            System.setOut(new PrintStream(OutputStream.nullOutputStream()));
            return new DatosSistema();
        } finally {
            System.setOut(salidaOriginal);
        }
    }

    private static void mostrarConcepto(String concepto) {
        System.out.println("Concepto aplicado: " + concepto);
    }

    private static void presentarSistema(DatosSistema datos) {
        datos.consola.mostrarTitulo("PRESENTACION DEL SISTEMA");
        mostrarConcepto("POO organiza el sistema en modelos, servicios, interfaces y estructuras AED.");
        System.out.println("Sistema academico: Clinica Salud Vital");
        System.out.println("Pacientes cargados: " + datos.pacienteService.getTotalPacientes());
        System.out.println("Citas cargadas: " + datos.citaService.getTotalCitas());
        System.out.println("Pagos cargados: " + datos.pagoService.getTotalPagos());
        System.out.println("La opcion 10 ejecuta la demostracion completa.");
    }

    private static void mostrarPacientes(DatosSistema datos) {
        datos.consola.mostrarTitulo("GESTION DE PACIENTES CON ARREGLO");
        mostrarConcepto("AED usa arreglo unidimensional para guardar pacientes en posiciones consecutivas.");
        datos.pacienteService.mostrarPacientes();
    }

    private static void demostrarBusquedaActualizacionEliminacion(DatosSistema datos) {
        datos.consola.mostrarTitulo("BUSQUEDA, ACTUALIZACION Y ELIMINACION");
        mostrarConcepto("Busqueda lineal recorre el arreglo hasta encontrar el DNI o llegar al final.");

        // AED: se usa una copia temporal para demostrar eliminacion sin afectar el estado base del menu.
        PacienteService servicioTemporal = crearServicioTemporalPacientes(datos);

        String dniABuscar = "00000001";
        Paciente pacienteEncontrado = servicioTemporal.buscarPacientePorDni(dniABuscar);

        if (pacienteEncontrado != null) {
            System.out.println("Paciente encontrado con exito");
            pacienteEncontrado.mostrarDatos();
            servicioTemporal.actualizarPaciente(dniABuscar, "900000099", "paciente.actualizado@chiletedevpath.test");
        } else {
            System.out.println("No se encontro ningun paciente con el DNI: " + dniABuscar);
        }

        servicioTemporal.eliminarPaciente(datos.paciente3.getDni());
        servicioTemporal.mostrarPacientes();
    }

    private static PacienteService crearServicioTemporalPacientes(DatosSistema datos) {
        PrintStream salidaOriginal = System.out;

        try {
            // Consola: se ocultan mensajes de carga porque aqui solo interesa la demostracion.
            System.setOut(new PrintStream(OutputStream.nullOutputStream()));
            PacienteService servicioTemporal = new PacienteService();
            // POO: se programa contra la interfaz Registrable para separar contrato e implementacion.
            Registrable<Paciente> registroTemporal = servicioTemporal;
            registroTemporal.registrar(copiarPaciente(datos.paciente1));
            registroTemporal.registrar(copiarPaciente(datos.paciente2));
            registroTemporal.registrar(copiarPaciente(datos.paciente3));
            return servicioTemporal;
        } finally {
            System.setOut(salidaOriginal);
        }
    }

    private static Paciente copiarPaciente(Paciente paciente) {
        /*
         * POO: se crea una copia para que la demo de actualizacion no cambie
         * los objetos base que tambien usan listas, citas, cola y reportes.
         */
        return new Paciente(
                paciente.getCodigo(),
                paciente.getDni(),
                paciente.getNombres(),
                paciente.getApellidos(),
                paciente.getTelefono(),
                paciente.getCorreo(),
                paciente.getEdad()
        );
    }

    private static void demostrarListasEnlazadas(DatosSistema datos) {
        datos.consola.mostrarTitulo("LISTAS ENLAZADAS");
        mostrarConcepto("AED usa nodos enlazados para insertar y recorrer datos sin depender de posiciones fijas.");

        System.out.println("\nLista enlazada de pacientes:");
        datos.listaPacientes.mostrarPacientes();

        System.out.println("\nTotal de pacientes en lista: " + datos.listaPacientes.contarPacientes());

        System.out.println("\nLista enlazada de citas:");
        datos.listaCitas.mostrarCitas();

        Cita citaEncontrada = datos.listaCitas.buscarCitaPorCodigo("CIT-003");
        if (citaEncontrada != null) {
            System.out.println("\nCita encontrada en lista enlazada:");
            citaEncontrada.mostrarDatos();
        }
    }

    private static void demostrarMatrizYCitas(DatosSistema datos) {
        datos.consola.mostrarTitulo("MATRIZ DE HORARIOS Y CITAS");
        mostrarConcepto("AED usa matriz para cruzar doctores por fila y dias de atencion por columna.");
        datos.citaService.mostrarCitas();
        datos.citaService.mostrarMatrizHorarios();
        datos.citaService.mostrarTotalCitasPorDoctor();
        datos.citaService.mostrarTotalCitasPorDia();
    }

    private static void demostrarBusquedaDoctores(DatosSistema datos) {
        datos.consola.mostrarTitulo("BUSQUEDA LINEAL DE DOCTORES");
        mostrarConcepto("Busqueda lineal compara codigos de doctor desde la primera hasta la ultima posicion.");
        BusquedaDoctor busquedaDoctor = new BusquedaDoctor();
        Doctor doctorEncontrado = busquedaDoctor.buscarPorCodigo(datos.doctores, datos.doctores.length, "DOC-002");

        if (doctorEncontrado != null) {
            System.out.println("Doctor encontrado con exito");
            doctorEncontrado.mostrarDatos();
        } else {
            System.out.println("Doctor no encontrado");
        }
    }

    private static void demostrarCola(DatosSistema datos) {
        datos.consola.mostrarTitulo("SALA DE ESPERA DE PACIENTES");
        mostrarConcepto("AED usa cola FIFO porque el primer paciente que llega debe ser atendido primero.");

        ColaPacientes colaTemporal = crearColaTemporalPacientes(datos);

        System.out.println("\nPacientes en espera:");
        colaTemporal.mostrarCola();

        System.out.println("\nPaciente ubicado al frente:");
        Paciente pacienteFrente = colaTemporal.verFrente();
        if (pacienteFrente != null) {
            pacienteFrente.mostrarDatos();
        }

        System.out.println("\nPaciente atendido mediante desencolar():");
        Paciente pacienteAtendido = colaTemporal.desencolar();
        if (pacienteAtendido != null) {
            pacienteAtendido.mostrarDatos();
        }

        System.out.println("\nCola despues de atender al primer paciente:");
        colaTemporal.mostrarCola();
    }

    private static void demostrarPila(DatosSistema datos) {
        datos.consola.mostrarTitulo("HISTORIAL RECIENTE DE CITAS");
        mostrarConcepto("AED usa pila LIFO para consultar primero la cita registrada mas recientemente.");

        PilaHistorialCitas pilaTemporal = crearPilaTemporalCitas(datos);

        System.out.println("\nCitas apiladas desde la mas reciente:");
        pilaTemporal.mostrarPila();

        System.out.println("\nCita ubicada en la cima:");
        Cita citaEnLaCima = pilaTemporal.verCima();
        if (citaEnLaCima != null) {
            citaEnLaCima.mostrarDatos();
        }

        System.out.println("\nCita retirada mediante desapilar():");
        Cita citaRetirada = pilaTemporal.desapilar();
        if (citaRetirada != null) {
            citaRetirada.mostrarDatos();
        }

        System.out.println("\nPila despues de retirar la cita mas reciente:");
        pilaTemporal.mostrarPila();
    }

    private static void demostrarBubbleSort(DatosSistema datos) {
        datos.consola.mostrarTitulo("ORDENAMIENTO POR APELLIDO - BUBBLE SORT");
        mostrarConcepto("Bubble Sort compara vecinos e intercambia pacientes hasta ordenar por apellido.");

        System.out.println("\nPacientes antes de ordenar:");
        datos.pacienteService.mostrarPacientes();

        BubbleSortPacientes ordenamiento = new BubbleSortPacientes();
        ordenamiento.ordenarPorApellido(datos.pacienteService.getPacientes(), datos.pacienteService.getTotalPacientes());

        System.out.println("\nPacientes despues de ordenar por apellido:");
        datos.pacienteService.mostrarPacientes();
    }

    private static void demostrarArbol(DatosSistema datos) {
        datos.consola.mostrarTitulo("ARBOL BINARIO DE BUSQUEDA DE DOCTORES");
        mostrarConcepto("AED usa arbol binario para reducir la busqueda comparando codigos hacia izquierda o derecha.");

        System.out.println("\nRecorrido preorden:");
        datos.arbolDoctores.recorrerPreOrden();

        System.out.println("\nRecorrido inorden:");
        datos.arbolDoctores.recorrerInOrden();

        System.out.println("\nRecorrido postorden:");
        datos.arbolDoctores.recorrerPostOrden();

        System.out.println("\nBusqueda en arbol por codigo:");
        Doctor doctorEncontrado = datos.arbolDoctores.buscarDoctorPorCodigo("DOC-002");
        if (doctorEncontrado != null) {
            doctorEncontrado.mostrarDatos();
        }

        System.out.println("\nEliminacion demostrativa en arbol temporal:");
        ArbolDoctoresBusqueda arbolTemporal = crearArbolTemporalDoctores(datos);
        arbolTemporal.eliminarDoctorPorCodigo("DOC-002");
        System.out.println("Recorrido inorden despues de eliminar DOC-002:");
        arbolTemporal.recorrerInOrden();
    }

    private static ColaPacientes crearColaTemporalPacientes(DatosSistema datos) {
        PrintStream salidaOriginal = System.out;

        try {
            // AED: la cola temporal permite demostrar salida FIFO sin alterar la sala base.
            System.setOut(new PrintStream(OutputStream.nullOutputStream()));
            ColaPacientes colaTemporal = new ColaPacientes();
            colaTemporal.encolar(datos.paciente2);
            colaTemporal.encolar(datos.paciente3);
            return colaTemporal;
        } finally {
            System.setOut(salidaOriginal);
        }
    }

    private static PilaHistorialCitas crearPilaTemporalCitas(DatosSistema datos) {
        PrintStream salidaOriginal = System.out;

        try {
            // AED: la pila temporal permite demostrar pop sin perder el historial base.
            System.setOut(new PrintStream(OutputStream.nullOutputStream()));
            PilaHistorialCitas pilaTemporal = new PilaHistorialCitas();
            pilaTemporal.apilar(datos.cita1);
            pilaTemporal.apilar(datos.cita2);
            pilaTemporal.apilar(datos.cita3);
            return pilaTemporal;
        } finally {
            System.setOut(salidaOriginal);
        }
    }

    private static ArbolDoctoresBusqueda crearArbolTemporalDoctores(DatosSistema datos) {
        PrintStream salidaOriginal = System.out;

        try {
            // AED: se usa otro arbol para explicar eliminacion sin modificar el arbol principal.
            System.setOut(new PrintStream(OutputStream.nullOutputStream()));
            ArbolDoctoresBusqueda arbolTemporal = new ArbolDoctoresBusqueda();
            arbolTemporal.insertarDoctor(datos.doctor2);
            arbolTemporal.insertarDoctor(datos.doctor1);
            arbolTemporal.insertarDoctor(datos.doctor3);
            return arbolTemporal;
        } finally {
            System.setOut(salidaOriginal);
        }
    }

    private static void demostrarRecursividad(DatosSistema datos) {
        datos.consola.mostrarTitulo("REPORTE RECURSIVO");
        mostrarConcepto("Recursividad divide el recorrido en caso base y llamada sobre la siguiente posicion.");

        ReporteRecursivo reporteRecursivo = new ReporteRecursivo();

        System.out.println("\nPacientes mostrados con recursion:");
        reporteRecursivo.mostrarPacientes(datos.pacienteService.getPacientes(), 0, datos.pacienteService.getTotalPacientes());

        System.out.println("\nCitas mostradas con recursion:");
        reporteRecursivo.mostrarCitas(datos.citaService.getCitas(), 0, datos.citaService.getTotalCitas());

        System.out.println("\nTotal de pacientes contado con recursion: "
                + reporteRecursivo.contarElementos(0, datos.pacienteService.getTotalPacientes()));
        System.out.println("Total de citas contado con recursion: "
                + reporteRecursivo.contarElementos(0, datos.citaService.getTotalCitas()));
    }

    private static void demostrarQuickSort(DatosSistema datos) {
        datos.consola.mostrarTitulo("ORDENAMIENTO DE CITAS - QUICKSORT");
        mostrarConcepto("QuickSort usa pivote y recursion; Comparator define que dato se compara.");

        Cita[] citasParaOrdenar = {
                datos.cita3,
                datos.cita1,
                datos.cita5,
                datos.cita2,
                datos.cita4
        };

        System.out.println("\nCitas antes de ordenar por codigo:");
        mostrarCodigosCitas(citasParaOrdenar);

        QuickSort quickSort = new QuickSort();
        // Java: Comparator evita modificar Cita; aqui se ordena por codigo usando una expresion lambda.
        quickSort.ordenar(
                citasParaOrdenar,
                0,
                citasParaOrdenar.length - 1,
                (citaActual, citaPivote) -> citaActual.getCodigo().compareToIgnoreCase(citaPivote.getCodigo())
        );

        System.out.println("\nCitas despues de ordenar por codigo:");
        mostrarCodigosCitas(citasParaOrdenar);
    }

    private static void mostrarCodigosCitas(Cita[] citas) {
        for (int i = 0; i < citas.length; i++) {
            System.out.println(citas[i].getCodigo());
        }
    }

    private static void demostrarTratamientoReceta(DatosSistema datos) {
        datos.consola.mostrarTitulo("TRATAMIENTO, RECETA E HISTORIAL CLINICO");
        mostrarConcepto("POO relaciona objetos clinicos: tratamiento, receta e historial comparten codigos de referencia.");
        datos.tratamiento1.mostrarDatos();
        System.out.println();
        datos.receta1.mostrarDatos();
        System.out.println();
        datos.historialCita1.mostrarDatos();
    }

    private static void demostrarPagos(DatosSistema datos) {
        datos.consola.mostrarTitulo("PAGOS, HERENCIA Y MERGESORT");
        mostrarConcepto("POO aplica polimorfismo: PagoService procesa Pago, Boleta y Factura con el mismo contrato.");

        System.out.println("\nPagos antes de ordenar por monto:");
        datos.pagoService.mostrarPagos();

        MergeSortPagos mergeSortPagos = new MergeSortPagos();
        // AED: MergeSort divide el arreglo de pagos y fusiona tramos ordenados por monto.
        mergeSortPagos.ordenarPorMonto(datos.pagoService.getPagos(), 0, datos.pagoService.getTotalPagos() - 1);

        System.out.println("\nPagos despues de ordenar por monto:");
        datos.pagoService.mostrarPagos();
    }

    private static void demostrarPersistenciaYReportes(DatosSistema datos) {
        datos.consola.mostrarTitulo("PERSISTENCIA CSV Y REPORTES");
        mostrarConcepto("Persistencia simple exporta arreglos a CSV; no recupera datos desde archivo.");

        ArchivoPaciente archivoPaciente = new ArchivoPaciente();
        ArchivoCita archivoCita = new ArchivoCita();
        ArchivoPago archivoPago = new ArchivoPago();

        archivoPaciente.guardarPacientes("out/pacientes.csv", datos.pacienteService.getPacientes(), datos.pacienteService.getTotalPacientes());
        archivoCita.guardarCitas("out/citas.csv", datos.citaService.getCitas(), datos.citaService.getTotalCitas());
        archivoPago.guardarPagos("out/pagos.csv", datos.pagoService.getPagos(), datos.pagoService.getTotalPagos());

        System.out.println("Archivos CSV generados en la carpeta out");

        ReporteService reporteService = new ReporteService();
        // POO: Reportable permite generar reportes mediante una interfaz comun.
        Reportable reporteAED = reporteService;
        reporteService.mostrarResumenGeneral(
                datos.pacienteService.getTotalPacientes(),
                datos.citaService.getTotalCitas(),
                datos.pagoService.getTotalPagos()
        );
        reporteAED.generarReporte();
    }

    private static void demostrarUtilidades(DatosSistema datos) {
        datos.consola.mostrarTitulo("UTILIDADES Y DATOS ADMINISTRATIVOS");
        mostrarConcepto("POO reutiliza clases auxiliares para validacion, codigos y fechas sin mezclar logica en Main.");

        Validador validador = new Validador();
        GeneradorCodigo generadorCodigo = new GeneradorCodigo();
        UtilidadesFecha utilidadesFecha = new UtilidadesFecha();

        System.out.println("DNI valido: " + validador.esDniValido(datos.paciente2.getDni()));
        System.out.println("Monto valido: " + validador.esMontoValido(datos.pago1.getMonto()));
        System.out.println("Texto valido: " + validador.esTextoValido(datos.paciente2.getNombres()));
        System.out.println("Codigo generado: " + generadorCodigo.generar("PAC", 6));
        System.out.println("Fecha formateada: " + utilidadesFecha.formatearFecha(LocalDate.now()));
        System.out.println();
        datos.administrador.mostrarDatos();
    }

    private static void ejecutarDemostracionCompleta(DatosSistema datos) {
        presentarSistema(datos);
        mostrarPacientes(datos);
        demostrarBusquedaActualizacionEliminacion(datos);
        demostrarListasEnlazadas(datos);
        demostrarMatrizYCitas(datos);
        demostrarBusquedaDoctores(datos);
        demostrarCola(datos);
        demostrarPila(datos);
        demostrarBubbleSort(datos);
        demostrarArbol(datos);
        demostrarRecursividad(datos);
        demostrarQuickSort(datos);
        demostrarTratamientoReceta(datos);
        demostrarPagos(datos);
        demostrarPersistenciaYReportes(datos);
        demostrarUtilidades(datos);
    }

    /*
     * Datos base del sistema.
     * Centraliza objetos y estructuras para que el menu trabaje sobre el mismo estado.
     */
    private static class DatosSistema {

        private final Consola consola = new Consola();

        private final Paciente paciente1 = new Paciente("PAC-001", "00000001", "Paciente", "DevPath Uno", "900000001", "paciente.uno@chiletedevpath.test", 28);
        private final Paciente paciente2 = new Paciente("PAC-002", "00000002", "Paciente", "DevPath Dos", "900000002", "paciente.dos@chiletedevpath.test", 20);
        private final Paciente paciente3 = new Paciente("PAC-003", "00000003", "Paciente", "DevPath Tres", "900000003", "paciente.tres@chiletedevpath.test", 17);
        private final Paciente paciente4 = new Paciente("PAC-004", "00000004", "Paciente", "DevPath Cuatro", "900000004", "paciente.cuatro@chiletedevpath.test", 21);
        private final Paciente paciente5 = new Paciente("PAC-005", "00000005", "Paciente", "DevPath Cinco", "900000005", "paciente.cinco@chiletedevpath.test", 35);

        private final Doctor doctor1 = new Doctor("DOC-001", "10000001", "Doctor", "DevPath Uno", "910000001", "000555", "doctor.uno@chiletedevpath.test", Especialidad.MEDICINA_GENERAL, TurnoConsultaAmbulatoria.MANANA);
        private final Doctor doctor2 = new Doctor("DOC-002", "10000002", "Doctor", "DevPath Dos", "910000002", "000556", "doctor.dos@chiletedevpath.test", Especialidad.DERMATOLOGIA, TurnoConsultaAmbulatoria.TARDE);
        private final Doctor doctor3 = new Doctor("DOC-003", "10000003", "Doctor", "DevPath Tres", "910000003", "000557", "doctor.tres@chiletedevpath.test", Especialidad.TRAUMATOLOGIA, TurnoConsultaAmbulatoria.MANANA);
        private final Doctor[] doctores = {doctor1, doctor2, doctor3};

        private final Administrador administrador = new Administrador("ADM-001", "20000001", "Administrador", "DevPath", "920000001", "admin@chiletedevpath.test", "Coordinadora de admision");

        private final Cita cita1 = new Cita("CIT-001", paciente2, doctor1, LocalDate.now(), LocalTime.now(), EstadoCita.PROGRAMADA, "Dolor de cuello");
        private final Cita cita2 = new Cita("CIT-002", paciente2, doctor2, LocalDate.now(), LocalTime.now(), EstadoCita.PROGRAMADA, "Presencia de fiebre");
        private final Cita cita3 = new Cita("CIT-003", paciente3, doctor3, LocalDate.now(), LocalTime.now(), EstadoCita.PROGRAMADA, "Control medico");
        private final Cita cita4 = new Cita("CIT-004", paciente4, doctor2, LocalDate.now(), LocalTime.now(), EstadoCita.PROGRAMADA, "Control medico");
        private final Cita cita5 = new Cita("CIT-005", paciente5, doctor1, LocalDate.now(), LocalTime.now(), EstadoCita.PROGRAMADA, "Control medico");

        private final Pago pago1 = new Boleta("PAG-001", cita1.getCodigo(), 120.00, TipoPago.EFECTIVO, LocalDate.now(), cita1.getPaciente().getDni());
        private final Pago pago2 = new Factura("PAG-002", cita2.getCodigo(), 85.50, TipoPago.TARJETA, LocalDate.now(), "20111111111");
        private final Pago pago3 = new Pago("PAG-003", cita3.getCodigo(), 160.00, TipoPago.TRANSFERENCIA, LocalDate.now());
        private final Pago pago4 = new Boleta("PAG-004", cita4.getCodigo(), 70.00, TipoPago.EFECTIVO, LocalDate.now(), paciente4.getDni());
        private final Pago pago5 = new Factura("PAG-005", cita5.getCodigo(), 140.00, TipoPago.TARJETA, LocalDate.now(), "20222222222");

        private final Tratamiento tratamiento1 = new Tratamiento("TRA-001", paciente2.getCodigo(), "Reposo y control por fiebre", 3);
        private final Receta receta1 = new Receta("REC-001", tratamiento1.getCodigo(), "Paracetamol 500 mg", "Tomar cada 8 horas por 3 dias");
        private final HistorialCita historialCita1 = new HistorialCita("HIS-001", paciente2.getCodigo(), "Paciente atendido por fiebre y derivado a tratamiento ambulatorio.");

        private final PacienteService pacienteService = new PacienteService();
        private final MatrizHorarios matrizHorarios = new MatrizHorarios(doctor1, doctor2, doctor3);
        private final CitaService citaService = new CitaService(matrizHorarios);
        private final PagoService pagoService = new PagoService();

        private final ListaPacientes listaPacientes = new ListaPacientes();
        private final ListaCitas listaCitas = new ListaCitas();
        private final ColaPacientes colaPacientes = new ColaPacientes();
        private final PilaHistorialCitas pilaHistorialCitas = new PilaHistorialCitas();
        private final ArbolDoctoresBusqueda arbolDoctores = new ArbolDoctoresBusqueda();

        private DatosSistema() {
            cargarPacientes();
            cargarCitas();
            cargarPagos();
            cargarEstructurasDinamicas();
        }

        private void cargarPacientes() {
            Registrable<Paciente> registroPacientes = pacienteService;
            registroPacientes.registrar(paciente1);
            registroPacientes.registrar(paciente2);
            registroPacientes.registrar(paciente3);
            registroPacientes.registrar(paciente4);
            registroPacientes.registrar(paciente5);
        }

        private void cargarCitas() {
            citaService.registrarCita(cita1, 1);
            citaService.registrarCita(cita2, 5);
            citaService.registrarCita(cita3, 1);
            citaService.registrarCita(cita4, 2);
            citaService.registrarCita(cita5, 3);
        }

        private void cargarPagos() {
            Pagable procesadorPago = pagoService;
            procesadorPago.procesarPago(pago1);
            procesadorPago.procesarPago(pago2);
            procesadorPago.procesarPago(pago3);
            procesadorPago.procesarPago(pago4);
            procesadorPago.procesarPago(pago5);
        }

        private void cargarEstructurasDinamicas() {
            listaPacientes.insertarPaciente(paciente1);
            listaPacientes.insertarPaciente(paciente2);
            listaPacientes.insertarPaciente(paciente3);
            listaPacientes.insertarPaciente(paciente4);
            listaPacientes.insertarPaciente(paciente5);

            listaCitas.insertarCita(cita1);
            listaCitas.insertarCita(cita2);
            listaCitas.insertarCita(cita3);
            listaCitas.insertarCita(cita4);
            listaCitas.insertarCita(cita5);

            colaPacientes.encolar(paciente2);
            colaPacientes.encolar(paciente3);

            pilaHistorialCitas.apilar(cita1);
            pilaHistorialCitas.apilar(cita2);
            pilaHistorialCitas.apilar(cita3);

            arbolDoctores.insertarDoctor(doctor2);
            arbolDoctores.insertarDoctor(doctor1);
            arbolDoctores.insertarDoctor(doctor3);
        }
    }
}
