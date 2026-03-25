package org.example.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //porque long es mas grande evita desbordamiento
    public long id;

    @Column(nullable = false, length = 40)

    public String raza;

    @Column(nullable = false, length = 40)

    public String especie;

    @Column(nullable = false, length = 40)

    public String nombre;


}
