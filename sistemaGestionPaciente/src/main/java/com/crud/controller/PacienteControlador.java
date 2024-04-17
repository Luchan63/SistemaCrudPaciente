package com.crud.controller;

import com.crud.modelo.Paciente;
import com.crud.repository.DAOException;
import com.crud.service.MySQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
public class PacienteControlador {
    @Autowired
    private MySQLManager mySQLManager;

    @GetMapping(value = {"/","/inicio"})
    public String inicio(Model model) throws DAOException, SQLException {
        List<Paciente>  list = mySQLManager.getPacienteDAO().getALl();
        model.addAttribute("lista", list);

        return "./Paciente/index";
    }

    @GetMapping(value = "/nuevo")
    public String mostrarFormularios(Model model)
    {
        model.addAttribute("paciente", new Paciente());

        return "./Paciente/formulario_Paciente";
    }

    @PostMapping(value = "/guardar")
    public String guardar(Paciente paciente) throws DAOException, SQLException {
        if (paciente.getId() != null) {
            // Si el paciente ya tiene un ID, significa que es una edición, entonces actualizamos
            mySQLManager.getPacienteDAO().modificar(paciente);
        } else {
            // Si el paciente no tiene un ID, significa que es nuevo, entonces insertamos
            mySQLManager.getPacienteDAO().insertar(paciente);
        }
        return "redirect:./inicio";
    }

    @GetMapping(value = "/editar/{id}")
    public String mostrarFormularioDeModificarPaciente(@PathVariable("id") Long id, Model model) throws DAOException, SQLException {

        Paciente paciente =
                mySQLManager.getPacienteDAO().getById(id);
        model.addAttribute("paciente", paciente);
        return "./Paciente/formulario_Paciente";
    }

    @GetMapping(value ="/eliminar/{id}" )
    public String eliminarPaciente(@PathVariable("id") Long id) throws DAOException, SQLException {
       mySQLManager.getPacienteDAO().elimanar(id);

        return "redirect:/inicio";

    }



        @GetMapping("/buscar")
        public String searchByName(Model model, @RequestParam("palabraClave") String palabraClave) throws DAOException, SQLException {
            if (palabraClave != null && !palabraClave.isEmpty()) {
                List<Paciente> list = mySQLManager.getPacienteDAO().getALl(palabraClave);
                model.addAttribute("lista", list); // Cambia "listas" por "lista" para mantener consistencia
                model.addAttribute("palabraClave", palabraClave);
            }
            return "./Paciente/index"; // Devuelve el nombre de la vista a la que quieres redirigir después de la búsqueda
        }
    }


