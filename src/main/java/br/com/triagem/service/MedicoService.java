package br.com.triagem.service;

import br.com.triagem.model.Medico;
import br.com.triagem.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;

    public Medico salvar(Medico medico) {
        return repository.save(medico);
    }

    public Medico atualizarPlantao(Long id, boolean emPlantao) {
        Medico medico = repository.findById(id).orElse(null);
        if (medico != null) {
            medico.setEmPlantao(emPlantao);
            return repository.save(medico);
        }
        return null;
    }

    public List<Medico> listarEmPlantao() {
        return repository.findAll().stream()
                .filter(Medico::isEmPlantao)
                .toList();
    }
}
