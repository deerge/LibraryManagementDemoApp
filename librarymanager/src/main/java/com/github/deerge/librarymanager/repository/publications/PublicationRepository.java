package com.github.deerge.librarymanager.repository.publications;

import com.github.deerge.librarymanager.model.publications.Publication;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;

@Repository
public interface PublicationRepository<T extends Publication> extends CrudRepository<T, Long> {
    default T getById(long id) {
        return findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("%s #%d not found", getClazz().getSimpleName(), id)));
    }

    @SuppressWarnings("unchecked")
    default Class<T> getClazz() {
        return (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), PublicationRepository.class);
    };
}
