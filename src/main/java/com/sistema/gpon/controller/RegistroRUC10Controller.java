package com.sistema.gpon.controller;

import com.sistema.gpon.DTO.RucDTO;
import com.sistema.gpon.model.*;
import com.sistema.gpon.service.*;
import com.sistema.gpon.service.impl.*;
import com.sistema.gpon.utils.Alert;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/registros")
public class RegistroRUC10Controller {

    @Autowired
    private RegistroRUC10Service _registroRUC10Service;

    @Autowired
    private UsuarioServiceImpl _usuarioService;

    @Autowired
    private PromocionServiceImpl _promocionService;

    @Autowired
    private PlanServiceImpl _planService;

    @Autowired
    private SectorServiceImpl _seSectorService;

    @Autowired
    private  ClienteServiceImpl _ClienteService;

    @Autowired
    private DistritoServiceImpl _disDistritoService;

    @Autowired
    private ContactoPrincipalService _ContactoPrincipalService;

    @Autowired
    private ContactoSecundarioService _ContactoSecundarioService;

    @Autowired
    private CronogramaService _CronogramaService;

    @Autowired
    private  RegistroRUC10ServiceImpl _RegistroRUC10Service;

    @GetMapping({"", "/"})
    public String index(HttpServletRequest request, Model model) {
        model.addAttribute("uri", request.getRequestURI());

        model.addAttribute("Consultor", _usuarioService.findByRol_Descripcion("Consultor"));
        model.addAttribute("Supervisor", _usuarioService.findByRol_Descripcion("Supervisor"));
        model.addAttribute("promocion", _promocionService.listarPromociones());
        model.addAttribute("plam", _planService.listarTodoPlanes());
        model.addAttribute("sector" , _seSectorService.listarSectores() );
        model.addAttribute("distrito" , _disDistritoService.listarDistritos());

        return "registros/index";
    }

    @PostMapping({"/create"})
    @Transactional
    public String create(HttpServletRequest request, Model model , @ModelAttribute RucDTO newRucDTO , RedirectAttributes flash) {
        model.addAttribute("uri", request.getRequestURI());

        model.addAttribute("Consultor", _usuarioService.findByRol_Descripcion("Consultor"));
        model.addAttribute("Supervisor", _usuarioService.findByRol_Descripcion("Supervisor"));
        model.addAttribute("promocion", _promocionService.listarPromociones());
        model.addAttribute("plam", _planService.listarTodoPlanes());
        model.addAttribute("sector" , _seSectorService.listarSectores() );
        model.addAttribute("distrito" , _disDistritoService.listarDistritos());
        model.addAttribute("ruc10DTO" , new RucDTO());

        try {
            ContactoPrincipal contactoPrincipal=new ContactoPrincipal();
            contactoPrincipal.setNombreContacto(newRucDTO.getNombreContacto());
            contactoPrincipal.setDni(newRucDTO.getDniContacto());
            contactoPrincipal.setCorreo(newRucDTO.getCorreoContacto());
            contactoPrincipal.setTelefono(newRucDTO.getTelefonoContacto());
            contactoPrincipal = _ContactoPrincipalService.crearContactoPrin(contactoPrincipal);

            ContactoSecundario contactoSecundario=new ContactoSecundario();
            contactoSecundario.setNombreContacto(newRucDTO.getNombreContactoSec());
            contactoSecundario.setDni(newRucDTO.getDniContactoSec());
            contactoSecundario.setCorreo(newRucDTO.getCorreoContactoSec());
            contactoSecundario.setTelefono(newRucDTO.getTelefonoContactoSec());
            contactoSecundario = _ContactoSecundarioService.creaContactoSec(contactoSecundario);

            Cronograma cronograma= new Cronograma();
            cronograma.setUbicacionInstalacion(newRucDTO.getNombreDistrito() + " - " + newRucDTO.getNombreSector() + " - " + newRucDTO.getReferencia());
            cronograma.setRangoInstalacion(newRucDTO.getRangoInstalacion());
            cronograma = _CronogramaService.crearCronograma(cronograma);

            Cliente cliente= new Cliente();
            cliente.setDniCliente(newRucDTO.getDniCliente());
            cliente.setRuc(newRucDTO.getRucCliente());
            cliente.setTelefono(newRucDTO.getTelefonoCliente());
            cliente.setNombre(newRucDTO.getNombreCliente());
            cliente.setApellido(newRucDTO.getApellidoCliente());
            /* cliente = _ClienteService.crearCliente(cliente); */

            RegistroRUC10 rucDTO= new RegistroRUC10();
            rucDTO.setUsuarioConsulto(_usuarioService.buscarPorId(newRucDTO.getIdUsuarioConsulto()));
            rucDTO.setUsuarioSupervisor(_usuarioService.buscarPorId(newRucDTO.getIdUsuarioSupervisor()));
            rucDTO.setCliente(cliente);
            rucDTO.setContactoPrincipal(contactoPrincipal);
            rucDTO.setContactoSecundario(contactoSecundario);
            rucDTO.setPlan(_planService.buscarPorId(newRucDTO.getIdPlan()));
            rucDTO.setPromocion(_promocionService.buscarPorId(newRucDTO.getIdPromocion()));
            rucDTO.setCronograma(cronograma);
            rucDTO.setObservacion(newRucDTO.getObservacion());
            _registroRUC10Service.crearRegistro(rucDTO);

            flash.addFlashAttribute("alert", Alert.sweetAlertSuccess("Se ingreso correctamente el registro"));

        }catch (Exception e){
            e.printStackTrace();
            flash.addFlashAttribute("alert", Alert.sweetAlert("ERROR" ,"Se produjo un error" ,"error"));
            model.addAttribute("error", "Ocurrió un error al registrar");
            return "/home/dashboard";

        }

        return "redirect:/home/dashboard";
    }

}