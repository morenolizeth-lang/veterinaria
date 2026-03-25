package org.example.Service;

import org.example.DTO.MascotaRequestDTO;
import org.example.DTO.MascotaResponseDTO;
import org.example.Model.Mascota;
import org.example.Repository.MascotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MascotaServiceImpl implements MascotaService {

    private final MascotaRepository mascotaRepository;

    public MascotaServiceImpl(MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    // Convierte Mascota → MascotaResponseDTO
    private MascotaResponseDTO toResponseDTO(Mascota mascota) {
        return new MascotaResponseDTO(
                mascota.getId(),
                mascota.getRaza(),
                mascota.getEspecie(),
                mascota.getNombre()
        );
    }

    // Convierte MascotaRequestDTO → Mascota
    private Mascota toEntity(MascotaRequestDTO dto) {
        Mascota mascota = new Mascota();
        mascota.setRaza(dto.getRaza());
        mascota.setEspecie(dto.getEspecie());
        mascota.setNombre(dto.getNombre());
        return mascota;
    }

    @Override
    public List<MascotaResponseDTO> listar() {
        // SQL: SELECT * FROM mascotas;
        return mascotaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MascotaResponseDTO> buscarPorId(Long id) {
        // SQL: SELECT * FROM mascotas WHERE id = 1;
        return mascotaRepository.findById(id)
                .map(this::toResponseDTO);
    }

    @Override
    public MascotaResponseDTO crear(MascotaRequestDTO dto) {
        // SQL: INSERT INTO mascotas (raza, especie, nombre) VALUES (?, ?, ?);
        Mascota mascota = toEntity(dto);
        return toResponseDTO(mascotaRepository.save(mascota));
    }

    @Override
    public Optional<MascotaResponseDTO> actualizar(Long id, MascotaRequestDTO dto) {
        // SQL: UPDATE mascotas SET raza = ?, especie = ?, nombre = ? WHERE id = ?;
        return mascotaRepository.findById(id).map(mascota -> {
            mascota.setRaza(dto.getRaza());
            mascota.setEspecie(dto.getEspecie());
            mascota.setNombre(dto.getNombre());
            return toResponseDTO(mascotaRepository.save(mascota));
        });
    }

    @Override
    public boolean eliminar(Long id) {
        // SQL: SELECT COUNT(*) FROM mascotas WHERE id = 1;
        if (!mascotaRepository.existsById(id)) return false;
        // SQL: DELETE FROM mascotas WHERE id = 1;
        mascotaRepository.deleteById(id);
        return true;
    }
}
