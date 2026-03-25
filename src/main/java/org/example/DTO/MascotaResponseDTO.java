package org.example.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MascotaResponseDTO {
    // Solo los campos que quieres mostrar
    private Long id;
    private String raza;
    private String especie;
    private String nombre;
}