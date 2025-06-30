package br.com.triagem.service;

import br.com.triagem.model.Paciente;
import br.com.triagem.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public Paciente salvar(Paciente paciente) {
        return repository.save(paciente);
    }

    public Paciente buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Paciente> listarNaoAtendidos() {
        return repository.findByAtendidoFalse();
    }

    public Paciente buscarProximoPaciente(List<?> medicosEmPlantao) {
        if (medicosEmPlantao == null || medicosEmPlantao.isEmpty()) return null;

        List<Paciente> pacientes = listarNaoAtendidos();

        return pacientes.stream()
                .sorted(Comparator
                        .comparingInt(this::prioridadeValor)
                        .thenComparingInt(this::gravidadeValor)
                        .reversed())
                .findFirst()
                .orElse(null);
    }

    private int prioridadeValor(Paciente p) {
        return switch (p.getPrioridade().toLowerCase()) {
            case "vermelha" -> 3;
            case "amarela" -> 2;
            case "verde" -> 1;
            default -> 0;
        };
    }

    private int gravidadeValor(Paciente p) {
        return switch (p.getGravidade().toLowerCase()) {
            case "grave" -> 3;
            case "moderada" -> 2;
            case "leve" -> 1;
            default -> 0;
        };
    }
}
