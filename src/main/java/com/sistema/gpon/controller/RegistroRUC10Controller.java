package com.sistema.gpon.controller;

import com.sistema.gpon.dto.RegistroFilter;
import com.sistema.gpon.dto.RucDTOCrear;
import com.sistema.gpon.dto.RucDTOActualizar;
import com.sistema.gpon.model.*;
import com.sistema.gpon.service.*;
import com.sistema.gpon.utils.Alert;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/registros")
public class RegistroRUC10Controller {

    @Autowired
    private RegistroRUC10Service registroRUC10Service;

    @Autowired
    private ReportService reporteService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PromocionService promocionService;

    @Autowired
    private PlanService planService;

    @Autowired
    private SectorService sectorService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private DistritoService distritoService;

    @Autowired
    private ContactoPrincipalService contactoPrincipalService;

    @Autowired
    private ContactoSecundarioService contactoSecundarioService;

    @Autowired
    private CronogramaService cronogramaService;

    @Autowired
    private EstadoRegistroService estadoRegistroService;

    @GetMapping({ "", "/" })
    public String listado(HttpServletRequest request, Model model) {
        model.addAttribute("uri", request.getRequestURI());

        model.addAttribute("lstEstados", estadoRegistroService.listarEstados());
        model.addAttribute("filtro", new RegistroFilter());
        model.addAttribute("lstRegistros", registroRUC10Service.listarRegistros());

        HttpSession session = request.getSession();
        Integer idRol = (Integer) session.getAttribute("idRol");
        model.addAttribute("idRol", idRol);

        return "registros/index";
    }

    @GetMapping("/filtrado")
    public String filtrado(@ModelAttribute RegistroFilter filtro, Model model) {
        model.addAttribute("lstEstados", estadoRegistroService.listarEstados());
        model.addAttribute("filtro", filtro);
        model.addAttribute("lstRegistros", registroRUC10Service.listarFiltros(filtro));

        return "registros/index";
    }

    @GetMapping("/detalle/{id}")
    public String detalle(HttpServletRequest request, Model model, @PathVariable int id) {
        RegistroRUC10 registroRUC10 = registroRUC10Service.buscarPorId(id);

        model.addAttribute("registro", registroRUC10);
        model.addAttribute("cronograma", registroRUC10.getCronograma());
        model.addAttribute("cliente", registroRUC10.getCliente());
        model.addAttribute("contactoPrincipal", registroRUC10.getContactoPrincipal());
        model.addAttribute("contactoSecundario", registroRUC10.getContactoSecundario());
        model.addAttribute("consultor", registroRUC10.getUsuarioConsultor());
        model.addAttribute("supervisor", registroRUC10.getUsuarioSupervisor());
        model.addAttribute("plan", registroRUC10.getPlan());
        model.addAttribute("promocion", registroRUC10.getPromocion());

        return "registros/detalle";
    }

    private void cargarDatosSelects(Model model) {
        model.addAttribute("consultores", usuarioService.findByRolDescripcion("Consultor").stream().filter(usuario -> usuario.getActivo()==true));
        model.addAttribute("supervisores", usuarioService.findByRolDescripcion("Supervisor").stream().filter(usuario -> usuario.getActivo()==true));
        model.addAttribute("promociones", promocionService.listarPromociones().stream().filter(promocion -> promocion.getActivo()==true));
        model.addAttribute("planes", planService.listarPlanes().stream().filter(plan -> plan.getActivo()==true));
        model.addAttribute("sectores", sectorService.listarSectores());
        model.addAttribute("distritos", distritoService.listarDistritos());
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        cargarDatosSelects(model);
        model.addAttribute("ruc10DTO", new RucDTOCrear());

        return "registros/nuevo";
    }

    @PostMapping("/registrar")
    @Transactional
    public String registrar(@Validated @ModelAttribute("ruc10DTO") RucDTOCrear ruc10DTO, BindingResult bindingResult,
            Model model, RedirectAttributes flash) {
        cargarDatosSelects(model);

        if (bindingResult.hasErrors()) {
            return "registros/nuevo";
        }

        try {
            ContactoPrincipal contactoPrincipal = new ContactoPrincipal();
            contactoPrincipal.setNombreContacto(ruc10DTO.getNombreContacto());
            contactoPrincipal.setDni(ruc10DTO.getDniContacto());
            contactoPrincipal.setCorreo(ruc10DTO.getCorreoContacto());
            contactoPrincipal.setTelefono(ruc10DTO.getTelefonoContacto());
            contactoPrincipal = contactoPrincipalService.crearContactoPrin(contactoPrincipal);

            ContactoSecundario contactoSecundario = new ContactoSecundario();
            contactoSecundario.setNombreContacto(ruc10DTO.getNombreContactoSec());
            contactoSecundario.setDni(ruc10DTO.getDniContactoSec());
            contactoSecundario.setCorreo(ruc10DTO.getCorreoContactoSec());
            contactoSecundario.setTelefono(ruc10DTO.getTelefonoContactoSec());
            contactoSecundario = contactoSecundarioService.creaContactoSec(contactoSecundario);

            Cronograma cronograma = new Cronograma();
            cronograma.setUbicacionInstalacion(
                    "NRO." + ruc10DTO.getNumero() + " " +
                            "DPTO/INT " + ruc10DTO.getInterior() + " " +
                            ruc10DTO.getObservacion() + " " +
                            "(" +
                            "LIMA-" + ruc10DTO.getDepartamento() + "-" + ruc10DTO.getProvincia() +
                            ")");
            cronograma.setRangoInstalacion(ruc10DTO.getRangoInstalacion());
            cronograma = cronogramaService.crearCronograma(cronograma);

            Cliente cliente = new Cliente();
            cliente.setDniCliente(ruc10DTO.getDniCliente());
            cliente.setRuc(ruc10DTO.getRucCliente());
            cliente.setTelefono(ruc10DTO.getTelefonoCliente());
            cliente.setNombre(ruc10DTO.getNombreCliente());
            cliente.setApellido(ruc10DTO.getApellidoCliente());
            cliente.setActivo(true);
            cliente = clienteService.crearClientenew(cliente);

            RegistroRUC10 rucDTO = new RegistroRUC10();
            rucDTO.setUsuarioConsultor(usuarioService.buscarPorId(ruc10DTO.getIdUsuarioConsultor()));
            rucDTO.setUsuarioSupervisor(usuarioService.buscarPorId(ruc10DTO.getIdUsuarioSupervisor()));
            rucDTO.setContactoPrincipal(contactoPrincipal);
            rucDTO.setContactoSecundario(contactoSecundario);
            rucDTO.setPlan(planService.buscarPorId(ruc10DTO.getIdPlan()));

            if (ruc10DTO.getIdPromocion() != null && ruc10DTO.getIdPromocion() != -1) {
                rucDTO.setPromocion(promocionService.buscarPorId(ruc10DTO.getIdPromocion()));
            } else {
                rucDTO.setPromocion(null);
            }

            rucDTO.setCronograma(cronograma);
            rucDTO.setCliente(cliente);
            rucDTO.setEstado(estadoRegistroService.buscarPorId(1));
            rucDTO.setDistrito(distritoService.buscarPorId(ruc10DTO.getIdDistrito()));
            rucDTO.setObservacion(ruc10DTO.getObservacion());

            registroRUC10Service.crearRegistro(rucDTO);

            flash.addFlashAttribute("alert", Alert.sweetAlertSuccess("Se ingreso correctamente el registro"));

        } catch (Exception e) {
            e.printStackTrace();
            flash.addFlashAttribute("alert", Alert.sweetAlert("ERROR", "Se produjo un error", "error"));
            model.addAttribute("error", "Ocurrió un error al registrar");
            return "/registros/nuevo";
        }

        return "redirect:/registros";
    }

    @GetMapping("/edicion/{id}")
    public String edicion(@PathVariable int id, Model model) {
        cargarDatosSelects(model);

        RegistroRUC10 registroRUC10 = registroRUC10Service.buscarPorId(id);
        Cronograma cronograma = cronogramaService.buscarPorId(registroRUC10.getCronograma().getIdCronograma());
        Cliente cliente = clienteService.buscarPorId(registroRUC10.getCliente().getDniCliente());
        ContactoPrincipal contactoPrincipal = contactoPrincipalService
                .buscarPorId(registroRUC10.getContactoPrincipal().getIdContactoPrincipal());
        ContactoSecundario contactoSecundario = contactoSecundarioService
                .buscarPorId(registroRUC10.getContactoSecundario().getIdContactoSecundario());
        Usuario consultor = usuarioService.buscarPorId(registroRUC10.getUsuarioConsultor().getIdUsuario());
        Usuario supervisor = usuarioService.buscarPorId(registroRUC10.getUsuarioSupervisor().getIdUsuario());
        Plan plan = planService.buscarPorId(registroRUC10.getPlan().getIdPlan());

        Promocion promocion = null;
        if (registroRUC10.getPromocion() != null) {
            promocion = promocionService.buscarPorId(registroRUC10.getPromocion().getIdPromocion());
        }

        List<EstadoRegistro> estadosLista = estadoRegistroService.listarEstados();

        RucDTOActualizar ruc10DTO = new RucDTOActualizar(
                registroRUC10.getIdRegistro(),
                contactoPrincipal.getNombreContacto(), contactoPrincipal.getDni(),
                contactoPrincipal.getCorreo(), contactoPrincipal.getTelefono(),
                contactoSecundario.getNombreContacto(), contactoSecundario.getDni(),
                contactoSecundario.getCorreo(), contactoSecundario.getTelefono(),
                cronograma.getRangoInstalacion(), cronograma.getUbicacionInstalacion(),
                cronograma.getFechaInstalacion(),
                consultor.getIdUsuario(), supervisor.getIdUsuario(),
                plan.getIdPlan(),
                (promocion != null && promocion.getIdPromocion() != null) ? promocion.getIdPromocion() : -1,
                registroRUC10.getDistrito().getIdDistrito(),
                registroRUC10.getObservacion(), cliente.getDniCliente(),
                cliente.getRuc(), cliente.getNombre(), cliente.getApellido(),
                cliente.getTelefono(), registroRUC10.getEstado().getIdEstado(), registroRUC10.getIdSolicitud(),
                registroRUC10.getIdInstalacion(), registroRUC10.getIdCarrito());
        ruc10DTO.setFechaInstalacion(cronograma.getFechaInstalacion());

        model.addAttribute("ruc10DTO", ruc10DTO);
        model.addAttribute("estados", estadosLista);

        return "registros/edicion";
    }

    @PostMapping("/guardar")
    @Transactional
    public String guardar(@Validated @ModelAttribute("ruc10DTO") RucDTOActualizar ruc10DTO, BindingResult bindingResult,
            Model model, RedirectAttributes flash) {
        cargarDatosSelects(model);

        if (bindingResult.hasErrors()) {
            return "registros/edicion";
        }

        try {
            RegistroRUC10 registroRUC10 = registroRUC10Service.buscarPorId(ruc10DTO.getIdRegistro());

            ContactoPrincipal contactoPrincipal = new ContactoPrincipal();
            contactoPrincipal.setIdContactoPrincipal(registroRUC10.getContactoPrincipal().getIdContactoPrincipal());
            contactoPrincipal.setNombreContacto(ruc10DTO.getNombreContacto());
            contactoPrincipal.setDni(ruc10DTO.getDniContacto());
            contactoPrincipal.setCorreo(ruc10DTO.getCorreoContacto());
            contactoPrincipal.setTelefono(ruc10DTO.getTelefonoContacto());
            contactoPrincipal = contactoPrincipalService.crearContactoPrin(contactoPrincipal);

            ContactoSecundario contactoSecundario = new ContactoSecundario();
            contactoSecundario.setIdContactoSecundario(registroRUC10.getContactoSecundario().getIdContactoSecundario());
            contactoSecundario.setNombreContacto(ruc10DTO.getNombreContactoSec());
            contactoSecundario.setDni(ruc10DTO.getDniContactoSec());
            contactoSecundario.setCorreo(ruc10DTO.getCorreoContactoSec());
            contactoSecundario.setTelefono(ruc10DTO.getTelefonoContactoSec());
            contactoSecundario = contactoSecundarioService.creaContactoSec(contactoSecundario);

            Cronograma cronograma = new Cronograma();
            cronograma.setIdCronograma(registroRUC10.getCronograma().getIdCronograma());
            cronograma.setUbicacionInstalacion(ruc10DTO.getLugarInstalacion());
            cronograma.setRangoInstalacion(ruc10DTO.getRangoInstalacion());
            cronograma.setFechaInstalacion(ruc10DTO.getFechaInstalacion());
            cronograma = cronogramaService.crearCronograma(cronograma);

            Cliente cliente = new Cliente();
            cliente.setDniCliente(registroRUC10.getCliente().getDniCliente());
            cliente.setDniCliente(ruc10DTO.getDniCliente());
            cliente.setRuc(ruc10DTO.getRucCliente());
            cliente.setTelefono(ruc10DTO.getTelefonoCliente());
            cliente.setNombre(ruc10DTO.getNombreCliente());
            cliente.setApellido(ruc10DTO.getApellidoCliente());
            cliente.setActivo(true);
            cliente = clienteService.crearClientenew(cliente);

            RegistroRUC10 rucDTO = new RegistroRUC10();
            rucDTO.setIdRegistro(registroRUC10.getIdRegistro());
            rucDTO.setUsuarioConsultor(usuarioService.buscarPorId(ruc10DTO.getIdUsuarioConsultor()));
            rucDTO.setUsuarioSupervisor(usuarioService.buscarPorId(ruc10DTO.getIdUsuarioSupervisor()));
            rucDTO.setContactoPrincipal(contactoPrincipal);
            rucDTO.setContactoSecundario(contactoSecundario);
            rucDTO.setPlan(planService.buscarPorId(ruc10DTO.getIdPlan()));
            rucDTO.setPromocion(promocionService.buscarPorId(ruc10DTO.getIdPromocion()));
            rucDTO.setCronograma(cronograma);
            rucDTO.setCliente(cliente);
            rucDTO.setEstado(estadoRegistroService.buscarPorId(1));
            rucDTO.setObservacion(ruc10DTO.getObservacion());
            rucDTO.setIdCarrito(ruc10DTO.getIdCarrito());
            rucDTO.setIdRegistro(ruc10DTO.getIdRegistro());
            rucDTO.setIdSolicitud(registroRUC10.getIdSolicitud());
            rucDTO.setIdInstalacion(ruc10DTO.getIdInstalacion());
            rucDTO.setEstado(estadoRegistroService.buscarPorId(ruc10DTO.getIdEstado()));
            rucDTO.setDistrito(registroRUC10.getDistrito());

            registroRUC10Service.actualizarRegistro(rucDTO);

            flash.addFlashAttribute("alert", Alert.sweetAlertSuccess("Se actualizó correctamente la venta"));

        } catch (Exception e) {
            e.printStackTrace();
            flash.addFlashAttribute("alert", Alert.sweetAlert("ERROR", "Se produjo un error", "error"));
            model.addAttribute("error", "Ocurrió un error al registrar");
            return "/registros/edicion";

        }

        return "redirect:/registros";
    }

    @GetMapping("/report/{id}")
    public ResponseEntity<byte[]> generarReporte(@PathVariable Integer id) {
        try {
            System.out.println("id: " + id);
            byte[] reporte = reporteService.generarReportParametrs("registro_ruc10_general", id);

            if (reporte == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            String nombreArchivo = (id != null && id > 0)
                    ? "registro_ruc10_" + id + ".pdf"
                    : "registro_ruc10_todos.pdf";
            headers.add("Content-Disposition", "inline; filename=" + nombreArchivo);

            return new ResponseEntity<>(reporte, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/contrato/{id}")
    public ResponseEntity<byte[]> generarContrato(@PathVariable Integer id) {
        try {
            byte[] reporte = reporteService.generarReportParametrs("registro_ruc10_detalle", id);

            if (reporte == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            String nombreArchivo = "registro_ruc10_" + id + ".pdf";
            headers.add("Content-Disposition", "inline; filename=" + nombreArchivo);

            return new ResponseEntity<>(reporte, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
