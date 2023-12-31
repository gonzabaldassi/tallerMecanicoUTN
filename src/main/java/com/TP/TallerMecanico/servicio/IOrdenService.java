package com.TP.TallerMecanico.servicio;

import com.TP.TallerMecanico.entidad.Orden;
import com.TP.TallerMecanico.entidad.Tecnico;
import java.time.LocalDate;
import java.util.List;
public interface IOrdenService {

    public List<Orden> listarOrdenes();
    public List<Orden> listarOrdenesFecha(LocalDate fechaDesde, LocalDate fechaHasta);
    public void guardar(Orden orden);
    public void actualizar(Orden orden);
    public void actualizarKilometraje(Orden orden);
    public void eliminar(Orden orden);
    public Orden buscarOrden(Long idOrden);
    public void activarOrden(Orden orden) ;
    //public List<Orden> filtrarOrdenes(Long marcaId, Long modeloId, Long numero, LocalDate fechaDocumento);
    public List<Orden> listarOrdenesTecnico(Tecnico tecnico);
}