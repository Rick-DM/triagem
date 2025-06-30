package br.com.triagem.controller;

import br.com.triagem.model.Paciente;
import br.com.triagem.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/triagem")
public class TriagemController {

    @Autowired
    private PacienteService service;

    @PostMapping
    public ResponseEntity<Paciente> registrarTriagem(@RequestBody Paciente paciente) {
        Paciente salvo = service.salvar(paciente);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable Long id) {
        Paciente paciente = service.buscarPorId(id);
        if (paciente != null) {
            return ResponseEntity.ok(paciente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
