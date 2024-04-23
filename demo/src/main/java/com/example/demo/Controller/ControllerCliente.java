package com.example.demo.Controller;

import com.example.demo.entity.Cliente;
import com.example.demo.service.GestorClientes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class ControllerCliente {
    GestorClientes ge = new GestorClientes ();

    @GetMapping("/cliente")
    public String crud(Model model){
        try {
            model.addAttribute("clientes", ge.listar());
            return "./cliente/listarcliente";
        } catch (Exception ex) {
            return "./error";
        }
    }
    @GetMapping("/cliente/alta")
    public String greetingForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "./cliente/altacliente";
    }
    @PostMapping("/cliente/alta")
    public String greetingSubmit(@ModelAttribute Cliente cliente, Model model) {
        String valorfinal="redirect:/cliente";
        try {
            ge.alta(cliente);
            try {
                model.addAttribute("clientes", ge.listar());
            } catch (SQLException ex) {
                Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
                valorfinal="error";
            }
        } catch (SQLException ex) {
            valorfinal="error";
        }
        return valorfinal;
    }
    @GetMapping("/cliente/eliminar")
    public String SubmitB (@RequestParam("codcliente") int id, Model model){
        String valorfinal="redirect:/cliente";
        try {
            ge.eliminar(id);
            model.addAttribute("clientes", ge.listar());
        } catch (SQLException ex) {
            valorfinal="error";
        }
        //return new ModelAndView(valorfinal, model.asMap());
        return valorfinal;
    }
}
