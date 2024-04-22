package com.example.demo.Controller;

import com.example.demo.service.GestorClientes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
