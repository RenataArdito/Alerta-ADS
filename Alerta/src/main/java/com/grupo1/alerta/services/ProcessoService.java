package com.grupo1.alerta.services;

import com.grupo1.alerta.models.Processo;
import com.grupo1.alerta.repositories.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProcessoService {

    @Autowired
    private ProcessoRepository processoRepository;

    public void salvarProcesso(Processo processo) {
        processoRepository.save(processo);
    }

    public List<Processo> listarProcessos() {
        return processoRepository.findAll();
    }
}
