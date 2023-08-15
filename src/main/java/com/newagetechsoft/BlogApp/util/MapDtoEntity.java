package com.newagetechsoft.BlogApp.util;

public interface MapDtoEntity<E,D> {

    E mapDtoToEntity(D dto);

    D mapEntityToDto(E entity);

}
