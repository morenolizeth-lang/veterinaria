package org.example.Service;

import org.example.DTO.MascotaRequestDTO;
import org.example.DTO.MascotaResponseDTO;

import java.util.List;
import java.util.Optional;

public interface MascotaService {
    List<MascotaResponseDTO> listar();
    Optional<MascotaResponseDTO> buscarPorId(Long id);
    MascotaResponseDTO crear(MascotaRequestDTO dto);
    Optional<MascotaResponseDTO> actualizar(Long id, MascotaRequestDTO dto);
    boolean eliminar(Long id);
}
