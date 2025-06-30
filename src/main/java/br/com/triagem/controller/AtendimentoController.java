package br.com.triagem.controller;

import br.com.triagem.model.Medico;
import br.com.triagem.model.Paciente;
import br.com.triagem.service.MedicoService;
import br.com.triagem.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atendimento")
public class AtendimentoController {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private MedicoService medicoService;

    @GetMapping("/proximo")
    public ResponseEntity<?> proximoPaciente() {
        List<Medico> emPlantao = medicoService.listarEmPlantao();

        Paciente paciente = pacienteService.buscarProximoPaciente(emPlantao);

        if (paciente == null) {
            return ResponseEntity.ok("Nenhum paciente disponível ou médicos fora de plantão.");
        }

        paciente.setAtendido(true);
        pacienteService.salvar(paciente);

        return ResponseEntity.ok(paciente);
    }
}
