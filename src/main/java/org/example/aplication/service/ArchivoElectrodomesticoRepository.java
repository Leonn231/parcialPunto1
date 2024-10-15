package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArchivoElectrodomesticoRepository implements ElectrodomesticoRepository {
    private static final String FILE_NAME = "electrodomesticos.ser";
    private List<Electrodomestico> electrodomesticos;

    public ArchivoElectrodomesticoRepository() {
        electrodomesticos = leerArchivo();
    }

    @Override
    public void crear(Electrodomestico e) {
        electrodomesticos.add(e);
        guardarArchivo();
    }

    @Override
    public Electrodomestico leer(int id) {
        return electrodomesticos.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Electrodomestico> listar() {
        return electrodomesticos;
    }

    @Override
    public void actualizar(Electrodomestico e) {
        Electrodomestico existente = leer(e.getId());
        if (existente != null) {
            existente.setNombre(e.getNombre());
            existente.setMarca(e.getMarca());
            guardarArchivo();
        }
    }

    @Override
    public void eliminar(int id) {
        electrodomesticos.removeIf(e -> e.getId() == id);
        guardarArchivo();
    }

    private void guardarArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(electrodomesticos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Electrodomestico> leerArchivo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Electrodomestico>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}