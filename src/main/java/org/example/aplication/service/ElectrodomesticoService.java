package org.example;

import java.util.List;

public interface ElectrodomesticoService {
    void agregarElectrodomestico(Electrodomestico e);
    Electrodomestico obtenerElectrodomestico(int id);
    List<Electrodomestico> obtenerTodos();
    void actualizarElectrodomestico(Electrodomestico e);
    void eliminarElectrodomestico(int id);
}

