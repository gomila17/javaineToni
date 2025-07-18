package com.example.common.repository;

import java.util.Optional;
import java.util.stream.Stream;

import com.example.common.EntityBase;
import com.example.common.NotFondException;

public interface Get<T extends EntityBase, ID> extends Data<T> {
    default T get(ID id) {
        Stream<T> filter = getData().stream()
                .filter(e -> e.getId().equals(id));
        return unwrap(filter.findFirst(),"No se encuentra la entidad");
    }
    default T get(ID id,String message) {
        Stream<T> filter = getData().stream()
                .filter(e -> e.getId().equals(id));
        return unwrap(filter.findFirst(),message);
    }

    static <T extends EntityBase> T unwrap(Optional<T> optional, String message) {
        return optional.orElseThrow(() -> {
            throw new NotFondException("No se encuentra la entidad");
        });
    }
}

