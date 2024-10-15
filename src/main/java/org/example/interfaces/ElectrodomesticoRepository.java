package org.example;

import java.util.List;

public interface ElectrodomesticoRepository {
    void crear(Electrodomestico e);
    Electrodomestico leer(int id);
    List<Electrodomestico> listar();
    void actualizar(Electrodomestico e);
    void eliminar(int id);
}
