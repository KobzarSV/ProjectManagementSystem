package ua.goit.project.model.converter;

public interface Convertor <T, E> {

    E toDto(T dao);
    T toDao(E dto);
}
