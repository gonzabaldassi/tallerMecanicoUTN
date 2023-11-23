package com.TP.TallerMecanico.servicio;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TP.TallerMecanico.entidad.Orden;
import com.TP.TallerMecanico.interfaz.IOrdenDao;

@Service
public class OrdenFiltrador {
    @Autowired
    private IOrdenDao ordenDao;

    @Autowired
    private IOrdenService ordenService;

    @Autowired
    public OrdenFiltrador(IOrdenDao ordenDao, IOrdenService ordenService) {
        this.ordenDao = ordenDao;
        this.ordenService = ordenService;
    }

    public List<Orden> filtrarOrdenes(Long marcaId, Long modeloId, Long numero, LocalDate fechaDocumento) {
        if (marcaId != -1) {
            // Filtrar por marca
            return filtrarPorMarca(marcaId, modeloId, numero, fechaDocumento);
        } else if (modeloId != -1) {
            // Filtrar por modelo
            return filtrarPorModelo(modeloId, numero, fechaDocumento);
        } else if (numero != null) {
            // Filtrar por numero
            return filtrarPorNumero(numero, fechaDocumento);
        } else if (fechaDocumento != null) {
            // Filtrar por fecha
            return filtrarPorFecha(fechaDocumento);
        } else {
            // Listar todas las órdenes
            return listarOrdenes();
        }
    }

    
    private List<Orden> filtrarPorMarca(Long marcaId, Long modeloId, Long numero, LocalDate fechaDocumento) {
        if (modeloId != -1) {
            if (numero != null) {
                if (fechaDocumento != null) {
                    // Búsqueda para marca, modelo, número y fecha
                    return ordenDao.filtrarOrdenPorMarcaYModeloYNumeroYFechaDocumento(marcaId, modeloId, numero, fechaDocumento);                    
                }else{
                    // Búsqueda para marca, modelo y número
                    return ordenDao.filtrarOrdenPorMarcaYModeloYNumero(marcaId, modeloId, numero);
                }
            }else if (fechaDocumento != null) {
                // Búsqueda para marca, modelo y fecha
                return ordenDao.filtrarOrdenPorMarcaYModeloYFechaDocumento(marcaId, modeloId, fechaDocumento);
            }else{
                //Busqueda pra marca y modelo
                return ordenDao.filtrarOrdenPorMarcaYModelo(marcaId, modeloId);
            }
        } else if (numero != null) {
            if (fechaDocumento != null) {
                // Búsqueda para marca, número y fecha
                return ordenDao.filtrarOrdenPorMarcaYNumeroYFechaDocumento(marcaId, numero, fechaDocumento);
            } else {
                //Busqueda por marca y numero
                return ordenDao.filtrarOrdenPorMarcaYNumero(marcaId, numero);
            }

        } else if (fechaDocumento != null) {
            // Búsqueda para marca y fecha
            return ordenDao.filtrarOrdenPorMarcaYFechaDocumento(marcaId, fechaDocumento);
        } else {
            // Filtrar solo por marca
            return ordenDao.filtrarOrdenPorMarca(marcaId);
        }
    }

    private List<Orden> filtrarPorModelo(Long modeloId, Long numero, LocalDate fechaDocumento) {
        if (numero != null){
            if (fechaDocumento != null) {
                // Búsqueda para modelo, número y fecha
                return ordenDao.filtrarOrdenPorModeloYNumeroYFechaDocumento(modeloId, numero, fechaDocumento);
            } else{
                // Búsqueda para modelo y número
                return ordenDao.filtrarOrdenPorModeloYNumero(modeloId, numero);
            }
        }else if ( fechaDocumento != null) {
            // Búsqueda para modelo y fecha
            return ordenDao.filtrarOrdenPorModeloYFechaDocumento(modeloId, fechaDocumento);
        }else{
            // Filtrar solo por modelo
            return ordenDao.filtrarOrdenPorModelo(modeloId);     
        }
    }

    private List<Orden> filtrarPorNumero(Long numero, LocalDate fechaDocumento) {
        if (fechaDocumento != null) {
            // Búsqueda por número y fecha
            return ordenDao.filtrarOrdenPorNumeroYFechaDocumento(numero, fechaDocumento);
        }else{
            // Búsqueda por número
            return ordenDao.filtrarOrdenPorNumero(numero);     
        }
    }

    private List<Orden> filtrarPorFecha(LocalDate fechaDocumento) {
        // Filtrar por fecha
        return ordenDao.filtrarOrdenPorFecha(fechaDocumento);
    }

    private List<Orden> listarOrdenes() {
        return ordenService.listarOrdenes();
    }
}
