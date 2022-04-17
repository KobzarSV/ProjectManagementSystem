package ua.goit.project.model.converter;

public interface Convertor <T, E> {

    E toDto(T type);
    T toDao(E type);
}
