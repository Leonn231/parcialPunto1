package org.example;

import java.util.List;

public class ElectrodomesticoServiceImpl implements ElectrodomesticoService {
    private ElectrodomesticoRepository repository;

    public ElectrodomesticoServiceImpl(ElectrodomesticoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void agregarElectrodomestico(Electrodomestico e) {
        repository.crear(e);
    }

    @Override
    public Electrodomestico obtenerElectrodomestico(int id) {
        return repository.leer(id);
    }

    @Override
    public List<Electrodomestico> obtenerTodos() {
        return repository.listar();
    }

    @Override
    public void actualizarElectrodomestico(Electrodomestico e) {
        repository.actualizar(e);
    }

    @Override
    public void eliminarElectrodomestico(int id) {
        repository.eliminar(id);
    }
}
