package com.back.agenda.controller;

import com.back.agenda.model.Evento;
import com.back.agenda.service.EventoService;
import com.back.agenda.service.NotificationService;
import com.back.agenda.service.impl.EventoServiceImpl;
import com.back.agenda.service.impl.NotificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/evento")
@CrossOrigin(origins = "http://localhost:5173")
public class EventoController {

    private final EventoService eventoService;
    private final NotificationService notificationService;

    public EventoController(EventoService eventoService, NotificationService notificationService) {
        this.eventoService = eventoService;
        this.notificationService = notificationService;
    }

    @GetMapping
    public ResponseEntity<List<Evento>> obtenerTodosLosEventos() {
        List<Evento> eventos = eventoService.obtenerTodosLosEventos();
        return ResponseEntity.ok(eventos);
    }

    @PostMapping
    public ResponseEntity<?> crearEvento(@RequestBody Evento evento) {
        try {
            Evento eventoCreado = eventoService.crearEvento(evento);
            return ResponseEntity.status(HttpStatus.CREATED).body(eventoCreado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el evento: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> obtenerEventoPorId(@PathVariable Long id) {
        return eventoService.obtenerEventoPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEvento(@PathVariable Long id, @RequestBody Evento evento) {
        try {
            Evento eventoActualizado = eventoService.actualizarEvento(id, evento);
            return ResponseEntity.ok(eventoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEvento(@PathVariable Long id) {
        if (eventoService.eliminarEvento(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/notificaciones")
    public ResponseEntity<?> enviarRecordatorioNotificacion(@PathVariable Long id) {
        Optional<Evento> eventoOptional = eventoService.obtenerEventoPorId(id);
        if (eventoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Evento evento = eventoOptional.get();
        String recipientEmail = "ortnn90@gmail.com";
        String subject = "Recordatorio de Evento Importante";
        String messageBody = "Estimado ciudadano, le recordamos el evento importante programado: " +
                "\nTipo de evento: " + evento.getTipo() +
                "\nEncargado: " + evento.getEncargado() +
                "\nFecha: " + evento.getFechaHora() +
                "\nUbicación: " + evento.getUbicacion();

        notificationService.sendNotification(recipientEmail, subject, messageBody);

        return ResponseEntity.ok("Notificación enviada correctamente");
    }
}
