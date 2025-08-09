package com.mobilnet.controller;

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

import com.mobilnet.dto.RegistrationFilter;
import com.mobilnet.dto.RucDTOCreate;
import com.mobilnet.dto.RucDTOUpdate;
import com.mobilnet.model.*;
import com.mobilnet.service.*;
import com.mobilnet.utils.Alert;

import java.util.List;

@Controller
@RequestMapping("/registros")
public class Ruc10RecordController {

    @Autowired
    private Ruc10RecordService ruc10RecordService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private UserService userService;

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private PlanService planService;

    @Autowired
    private SectorService sectorService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private MainContactService mainContactService;

    @Autowired
    private SecondaryContactService secondaryContactService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private RecordStateService recordStateService;

    @GetMapping({ "", "/" })
    public String list(HttpServletRequest request, Model model) {
        model.addAttribute("uri", request.getRequestURI());

        model.addAttribute("lstEstados", recordStateService.list());
        model.addAttribute("filtro", new RegistrationFilter());
        model.addAttribute("lstRegistros", ruc10RecordService.list());

        HttpSession session = request.getSession();
        Integer roleId = (Integer) session.getAttribute("idRol");
        model.addAttribute("idRol", roleId);

        return "registros/index";
    }

    @GetMapping("/filtrado")
    public String filtered(@ModelAttribute RegistrationFilter filter, Model model) {
        model.addAttribute("lstEstados", recordStateService.list());
        model.addAttribute("filtro", filter);
        model.addAttribute("lstRegistros", ruc10RecordService.listWithFilters(filter));

        return "registros/index";
    }

    @GetMapping("/detalle/{id}")
    public String detail(HttpServletRequest request, Model model, @PathVariable int id) {
        Ruc10Record ruc10Record = ruc10RecordService.findById(id);

        model.addAttribute("registro", ruc10Record);
        model.addAttribute("cronograma", ruc10Record.getSchedule());
        model.addAttribute("cliente", ruc10Record.getClient());
        model.addAttribute("contactoPrincipal", ruc10Record.getMainContact());
        model.addAttribute("contactoSecundario", ruc10Record.getSecondaryContact());
        model.addAttribute("consultor", ruc10Record.getConsultantUser());
        model.addAttribute("supervisor", ruc10Record.getSupervisorUser());
        model.addAttribute("plan", ruc10Record.getPlan());
        model.addAttribute("promocion", ruc10Record.getPromotion());

        return "registros/detalle";
    }

    private void loadSelectData(Model model) {
        model.addAttribute("consultores", userService.findByRoleDescription("Consultor").stream()
                .filter(user -> user.getIsActive() == true));
        model.addAttribute("supervisores", userService.findByRoleDescription("Supervisor").stream()
                .filter(user -> user.getIsActive() == true));
        model.addAttribute("promociones",
                promotionService.list().stream().filter(promotion -> promotion.getIsActive() == true));
        model.addAttribute("planes", planService.list().stream().filter(plan -> plan.getIsActive() == true));
        model.addAttribute("sectores", sectorService.list());
        model.addAttribute("distritos", districtService.list());
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        loadSelectData(model);
        model.addAttribute("ruc10DTO", new RucDTOCreate());

        return "registros/nuevo";
    }

    @PostMapping("/registrar")
    @Transactional
    public String register(@Validated @ModelAttribute("ruc10DTO") RucDTOCreate ruc10DTO, BindingResult bindingResult,
            Model model, RedirectAttributes flash) {
        loadSelectData(model);

        if (bindingResult.hasErrors()) {
            return "registros/nuevo";
        }

        try {
            MainContact mainContact = new MainContact();
            mainContact.setName(ruc10DTO.getMainContactName());
            mainContact.setDni(ruc10DTO.getMainContactDni());
            mainContact.setEmail(ruc10DTO.getMainContactEmail());
            mainContact.setPhone(ruc10DTO.getMainContactPhone());
            mainContact = mainContactService.create(mainContact);

            SecondaryContact secondaryContact = new SecondaryContact();
            secondaryContact.setName(ruc10DTO.getSecondaryContactName());
            secondaryContact.setDni(ruc10DTO.getSecondaryContactDni());
            secondaryContact.setEmail(ruc10DTO.getSecondaryContactEmail());
            secondaryContact.setPhone(ruc10DTO.getSecondaryContactPhone());
            secondaryContact = secondaryContactService.create(secondaryContact);

            Schedule schedule = new Schedule();
            schedule.setInstallationLocation(
                    "NRO." + ruc10DTO.getNumber() + " " +
                            "DPTO/INT " + ruc10DTO.getInterior() + " " +
                            ruc10DTO.getObservation() + " " +
                            "(" +
                            "LIMA-" + ruc10DTO.getDepartment() + "-" + ruc10DTO.getProvince() +
                            ")");
            schedule.setInstallationRange(ruc10DTO.getInstallationRange());
            schedule = scheduleService.create(schedule);

            Client client = new Client();
            client.setDni(ruc10DTO.getClientDni());
            client.setRuc(ruc10DTO.getClientRuc());
            client.setPhone(ruc10DTO.getClientPhone());
            client.setFirstName(ruc10DTO.getClientFirstName());
            client.setLastName(ruc10DTO.getClientLastName());
            client.setIsActive(true);
            client = clientService.createNew(client);

            Ruc10Record rucRecord = new Ruc10Record();
            rucRecord.setConsultantUser(userService.findById(ruc10DTO.getConsultantUserId()));
            rucRecord.setSupervisorUser(userService.findById(ruc10DTO.getSupervisorUserId()));
            rucRecord.setMainContact(mainContact);
            rucRecord.setSecondaryContact(secondaryContact);
            rucRecord.setPlan(planService.findById(ruc10DTO.getPlanId()));

            if (ruc10DTO.getPromotionId() != null && ruc10DTO.getPromotionId() != -1) {
                rucRecord.setPromotion(promotionService.findById(ruc10DTO.getPromotionId()));
            } else {
                rucRecord.setPromotion(null);
            }

            rucRecord.setSchedule(schedule);
            rucRecord.setClient(client);
            rucRecord.setState(recordStateService.findById(1));
            rucRecord.setDistrict(districtService.findById(ruc10DTO.getDistrictId()));
            rucRecord.setObservation(ruc10DTO.getObservation());

            ruc10RecordService.create(rucRecord);

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
    public String edit(@PathVariable int id, Model model) {
        loadSelectData(model);

        Ruc10Record ruc10Record = ruc10RecordService.findById(id);
        Schedule schedule = scheduleService.findById(ruc10Record.getSchedule().getId());
        Client client = clientService.findById(ruc10Record.getClient().getDni());
        MainContact mainContact = mainContactService
                .findById(ruc10Record.getMainContact().getId());
        SecondaryContact secondaryContact = secondaryContactService
                .findById(ruc10Record.getSecondaryContact().getId());
        User consultant = userService.findById(ruc10Record.getConsultantUser().getId());
        User supervisor = userService.findById(ruc10Record.getSupervisorUser().getId());
        Plan plan = planService.findById(ruc10Record.getPlan().getId());

        Promotion promotion = null;
        if (ruc10Record.getPromotion() != null) {
            promotion = promotionService.findById(ruc10Record.getPromotion().getId());
        }

        List<RecordState> stateList = recordStateService.list();

        RucDTOUpdate ruc10DTO = new RucDTOUpdate(
                ruc10Record.getId(),
                mainContact.getName(), mainContact.getDni(),
                mainContact.getEmail(), mainContact.getPhone(),
                secondaryContact.getName(), secondaryContact.getDni(),
                secondaryContact.getEmail(), secondaryContact.getPhone(),
                schedule.getInstallationRange(), schedule.getInstallationLocation(),
                schedule.getInstallationDate(),
                consultant.getId(), supervisor.getId(),
                plan.getId(),
                (promotion != null && promotion.getId() != null) ? promotion.getId() : -1,
                ruc10Record.getDistrict().getId(),
                ruc10Record.getObservation(), client.getDni(),
                client.getRuc(), client.getFirstName(), client.getLastName(),
                client.getPhone(), ruc10Record.getState().getId(), ruc10Record.getRequestId(),
                ruc10Record.getInstallationId(), ruc10Record.getCartId());
        ruc10DTO.setInstallationDate(schedule.getInstallationDate());

        model.addAttribute("ruc10DTO", ruc10DTO);
        model.addAttribute("estados", stateList);

        return "registros/edicion";
    }

    @PostMapping("/guardar")
    @Transactional
    public String save(@Validated @ModelAttribute("ruc10DTO") RucDTOUpdate ruc10DTO, BindingResult bindingResult,
            Model model, RedirectAttributes flash) {
        loadSelectData(model);

        if (bindingResult.hasErrors()) {
            return "registros/edicion";
        }

        try {
            Ruc10Record ruc10Record = ruc10RecordService.findById(ruc10DTO.getRegistrationId());

            MainContact mainContact = new MainContact();
            mainContact.setId(ruc10Record.getMainContact().getId());
            mainContact.setName(ruc10DTO.getMainContactName());
            mainContact.setDni(ruc10DTO.getMainContactDni());
            mainContact.setEmail(ruc10DTO.getMainContactEmail());
            mainContact.setPhone(ruc10DTO.getMainContactPhone());
            mainContact = mainContactService.create(mainContact);

            SecondaryContact secondaryContact = new SecondaryContact();
            secondaryContact.setId(ruc10Record.getSecondaryContact().getId());
            secondaryContact.setName(ruc10DTO.getSecondaryContactName());
            secondaryContact.setDni(ruc10DTO.getSecondaryContactDni());
            secondaryContact.setEmail(ruc10DTO.getSecondaryContactEmail());
            secondaryContact.setPhone(ruc10DTO.getSecondaryContactPhone());
            secondaryContact = secondaryContactService.create(secondaryContact);

            Schedule schedule = new Schedule();
            schedule.setId(ruc10Record.getSchedule().getId());
            schedule.setInstallationLocation(ruc10DTO.getInstallationPlace());
            schedule.setInstallationRange(ruc10DTO.getInstallationRange());
            schedule.setInstallationDate(ruc10DTO.getInstallationDate());
            schedule = scheduleService.create(schedule);

            Client client = new Client();
            client.setDni(ruc10DTO.getClientDni());
            client.setRuc(ruc10DTO.getClientRuc());
            client.setPhone(ruc10DTO.getClientPhone());
            client.setFirstName(ruc10DTO.getClientFirstName());
            client.setLastName(ruc10DTO.getClientLastName());
            client.setIsActive(true);
            client = clientService.createNew(client);

            Ruc10Record rucRecord = new Ruc10Record();
            rucRecord.setId(ruc10Record.getId());
            rucRecord.setConsultantUser(userService.findById(ruc10DTO.getConsultantUserId()));
            rucRecord.setSupervisorUser(userService.findById(ruc10DTO.getSupervisorUserId()));
            rucRecord.setMainContact(mainContact);
            rucRecord.setSecondaryContact(secondaryContact);
            rucRecord.setPlan(planService.findById(ruc10DTO.getPlanId()));
            rucRecord.setPromotion(promotionService.findById(ruc10DTO.getPromotionId()));
            rucRecord.setSchedule(schedule);
            rucRecord.setClient(client);
            rucRecord.setState(recordStateService.findById(1));
            rucRecord.setObservation(ruc10DTO.getObservation());
            rucRecord.setCartId(ruc10DTO.getCartId());
            rucRecord.setId(ruc10DTO.getRegistrationId());
            rucRecord.setRequestId(ruc10Record.getRequestId());
            rucRecord.setInstallationId(ruc10DTO.getInstallationId());
            rucRecord.setState(recordStateService.findById(ruc10DTO.getStatusId()));
            rucRecord.setDistrict(ruc10Record.getDistrict());

            ruc10RecordService.update(rucRecord);

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
    public ResponseEntity<byte[]> generateReport(@PathVariable Integer id) {
        try {
            System.out.println("id: " + id);
            byte[] report = reportService.generateReportWithParameters("registro_ruc10_general", id);

            if (report == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            String fileName = (id != null && id > 0)
                    ? "registro_ruc10_" + id + ".pdf"
                    : "registro_ruc10_todos.pdf";
            headers.add("Content-Disposition", "inline; filename=" + fileName);

            return new ResponseEntity<>(report, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/contrato/{id}")
    public ResponseEntity<byte[]> generateContract(@PathVariable Integer id) {
        try {
            byte[] report = reportService.generateReportWithParameters("registro_ruc10_detalle", id);

            if (report == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            String fileName = "registro_ruc10_" + id + ".pdf";
            headers.add("Content-Disposition", "inline; filename=" + fileName);

            return new ResponseEntity<>(report, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}