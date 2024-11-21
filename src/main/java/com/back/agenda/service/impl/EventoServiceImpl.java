package com.back.agenda.service.impl;


import com.back.agenda.model.Evento;
import com.back.agenda.repository.EventoRepository;
import com.back.agenda.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public Evento crearEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    @Override
    public Optional<Evento> obtenerEventoPorId(Long id) {
        return eventoRepository.findById(id);
    }

    @Override
    public List<Evento> obtenerTodosLosEventos() {
        try {
            return eventoRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener eventos: " + e.getMessage());
        }
    }

    @Override
    public Evento actualizarEvento(Long id, Evento evento) {
        return eventoRepository.findById(id).map(eventoExistente -> {
            eventoExistente.setTipo(evento.getTipo());
            eventoExistente.setEncargado(evento.getEncargado());
            eventoExistente.setFechaHora(evento.getFechaHora());
            eventoExistente.setUbicacion(evento.getUbicacion());
            return eventoRepository.save(eventoExistente);
        }).orElseThrow(() -> new RuntimeException("Evento no encontrado"));
    }

    @Override
    public boolean eliminarEvento(Long id) {
        try {
            eventoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

