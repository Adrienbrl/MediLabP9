package com.medilabo.front.patient;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/patients")
public class PatientController {

    private final PatientClient patientClient;

    public PatientController(PatientClient patientClient) {
        this.patientClient = patientClient;
    }

    @GetMapping
    public String listPatients(Model model) {
        model.addAttribute("patients", patientClient.getPatients());
        return "patients/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("patientForm", new PatientForm());
        model.addAttribute("pageTitle", "Ajouter un patient");
        model.addAttribute("formAction", "/patients");
        return "patients/form";
    }

    @PostMapping
    public String createPatient(
            @Valid @ModelAttribute("patientForm") PatientForm patientForm,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", "Ajouter un patient");
            model.addAttribute("formAction", "/patients");
            return "patients/form";
        }

        patientClient.createPatient(patientForm);
        redirectAttributes.addFlashAttribute("successMessage", "Le patient a bien été ajouté.");
        return "redirect:/patients";
    }

    @GetMapping("/{id}")
    public String viewPatient(@PathVariable("id") Long id, Model model) {
        model.addAttribute("patient", patientClient.getPatient(id));
        return "patients/detail";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        PatientView patient = patientClient.getPatient(id);
        model.addAttribute("patientId", id);
        model.addAttribute("patientForm", PatientForm.from(patient));
        model.addAttribute("pageTitle", "Modifier un patient");
        model.addAttribute("formAction", "/patients/" + id);
        return "patients/form";
    }

    @PostMapping("/{id}")
    public String updatePatient(
            @PathVariable("id") Long id,
            @Valid @ModelAttribute("patientForm") PatientForm patientForm,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("patientId", id);
            model.addAttribute("pageTitle", "Modifier un patient");
            model.addAttribute("formAction", "/patients/" + id);
            return "patients/form";
        }

        patientClient.updatePatient(id, patientForm);
        redirectAttributes.addFlashAttribute("successMessage", "Le patient a bien été mis à jour.");
        return "redirect:/patients/" + id;
    }
}
