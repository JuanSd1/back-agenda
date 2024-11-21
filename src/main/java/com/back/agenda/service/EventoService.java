package com.back.agenda.service;

import com.back.agenda.model.Evento;
import java.util.List;
import java.util.Optional;

public interface EventoService {

    Evento crearEvento(Evento evento);
    Optional<Evento> obtenerEventoPorId(Long id);
    List<Evento> obtenerTodosLosEventos();
    Evento actualizarEvento(Long id, Evento evento);
    boolean eliminarEvento(Long id);
}
