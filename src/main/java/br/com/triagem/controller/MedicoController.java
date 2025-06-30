package br.com.triagem.controller;

import br.com.triagem.model.Medico;
import br.com.triagem.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @PostMapping
    public ResponseEntity<Medico> cadastrarMedico(@RequestBody Medico medico) {
        Medico salvo = service.salvar(medico);
        return ResponseEntity.ok(salvo);
    }

    @PutMapping("/{id}/plantao")
    public ResponseEntity<Medico> atualizarPlantao(@PathVariable Long id, @RequestParam boolean emPlantao) {
        Medico atualizado = service.atualizarPlantao(id, emPlantao);
        if (atualizado != null) {
            return ResponseEntity.ok(atualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
