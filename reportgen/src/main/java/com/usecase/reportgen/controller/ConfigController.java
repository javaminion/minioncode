package com.usecase.reportgen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.usecase.reportgen.document.ConfigMap;
import com.usecase.reportgen.service.AdminService;

import java.util.Map;

@Controller
public class ConfigController {

    @Autowired
    private AdminService service;
    
    @GetMapping("/admin")
    public String listDocumentsByGet(Model model) {
        model.addAttribute("documents", service.getAllDocuments());
        return "viewconfig";
    }

    @PostMapping("/admin")
    public String listDocuments(Model model) {
        model.addAttribute("documents", service.getAllDocuments());
        return "viewconfig";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        return "addconfig";
    }

    @PostMapping("/add")
    public String addDocument(@RequestParam String stringKey, @RequestParam Map<String, String> jsonValue) {
        service.createDocument(stringKey, jsonValue);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        ConfigMap document = service.getDocumentById(id);
        model.addAttribute("document", document);
        return "editconfig";
    }

    @PostMapping("/edit")
    public String updateDocument(@RequestParam String id, @RequestParam String stringKey, @RequestParam Map<String, String> jsonValue) {
        service.updateDocument(id, stringKey, jsonValue);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteDocument(@PathVariable String id) {
        service.deleteDocument(id);
        return "redirect:/admin";
    }
}

