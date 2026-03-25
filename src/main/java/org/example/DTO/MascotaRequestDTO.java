package org.example.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MascotaRequestDTO {
    // Solo los campos que el cliente puede enviar
    // No incluye id — lo genera la BD
    private String raza;
    private String especie;
    private String nombre;
}