package net.camtrunet.eventsguay.Dominio;

import java.util.Date;
/**
 * Created by ricardo on 28/08/16.
 */
public class Evento {

    private int eventoId;
    private String titulo;
    private String detalle;
    private Date fecha;
    private String ubicacion;
    private boolean estado;

    public Evento(){
        fecha=new Date();
    }

    public int getEventoId() {
        return eventoId;
    }

    public void setEventoId(int eventoId) {
        this.eventoId = eventoId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }


    @Override
    public String toString() {
        return "Evento{" +
                "eventoId=" + eventoId +
                ", titulo='" + titulo + '\'' +
                ", detalle='" + detalle + '\'' +
                ", fecha=" + fecha +
                ", ubicacion='" + ubicacion + '\'' +
                ", estado=" + estado +
                '}';
    }
}
