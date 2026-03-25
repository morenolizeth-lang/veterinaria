package org.example.Controller;

import org.example.DTO.MascotaRequestDTO;
import org.example.DTO.MascotaResponseDTO;
import org.example.Service.MascotaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MascotaController {

    private final MascotaService mascotaService;

    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    // GET /api/listarMascotas
    // SQL: SELECT * FROM mascotas;
    @GetMapping("/listarMascotas")
    public ResponseEntity<List<MascotaResponseDTO>> listar() {
        return ResponseEntity.ok(mascotaService.listar());
    }

    // GET /api/buscarMascota/1
    // SQL: SELECT * FROM mascotas WHERE id = 1;
    @GetMapping("/buscarMascota/{id}")
    public ResponseEntity<MascotaResponseDTO> buscarPorId(@PathVariable Long id) {
        return mascotaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/registrarMascota
    // SQL: INSERT INTO mascotas (raza, especie, nombre) VALUES (?, ?, ?);
    @PostMapping("/registrarMascota")
    public ResponseEntity<?> crear(@RequestBody MascotaRequestDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(mascotaService.crear(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // PUT /api/actualizarMascota/1
    // SQL: UPDATE mascotas SET raza = ?, especie = ?, nombre = ? WHERE id = 1;
    @PutMapping("/actualizarMascota/{id}")
    public ResponseEntity<MascotaResponseDTO> actualizar(@PathVariable Long id,
                                                         @RequestBody MascotaRequestDTO dto) {
        return mascotaService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/eliminarMascota/1
    // SQL: DELETE FROM mascotas WHERE id = 1;
    @DeleteMapping("/eliminarMascota/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!mascotaService.eliminar(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}