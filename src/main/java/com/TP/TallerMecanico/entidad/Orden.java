package com.TP.TallerMecanico.entidad;

import java.time.LocalDate;
import jakarta.persistence.*;
// import jakarta.validation.constraints.NotEmpty;
// import jakarta.validation.constraints.Pattern;
// import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

//import org.springframework.jmx.export.annotation.ManagedResource;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name="orden")
public class Orden implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrden;

    private LocalDate fechaRegistro;

    private LocalDate fechaDocumento;

    @NotEmpty(message = "El kilometraje no debe estar vacio")
    @Pattern(regexp = "^[0-9]{1,8}$", message = "Debe ser un número de 1 a 8 dígitos.")
    private String kilometros;

    @OneToMany(mappedBy = "orden")
    private List<DetalleOrden> detallesOrden;

    @OneToMany(mappedBy = "orden")
    private List<Imagen> imagenesOrden;

    @ManyToOne
    @JoinColumn(name="vehiculo_id")
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name="tecnico_id")
    private Tecnico tecnico;

    @Transient
    private String modo;
    private Boolean estado = true;

    public List<DetalleOrden> getDetallesOrden(){
        return detallesOrden;
    }

    public int calcularTotal(){
        int total = 0;
        for (DetalleOrden detalleOrden : detallesOrden) {
            if (detalleOrden.getEstado()) {
                total+=detalleOrden.getSubtotal();
            }
        }
        return total;
    }
}