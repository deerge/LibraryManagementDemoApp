package com.github.deerge.librarymanager.repository.publications;

import com.github.deerge.librarymanager.model.publications.Publication;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Repository
public interface PublicationRepository<T extends Publication> extends JpaRepository<T, Long> {
    default T getById(long id) {
        return findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("%s #%d not found", getClazz().getSimpleName(), id)));
    }

    default List<T> search(T entityParams) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
        Example<T> exampleQuery = Example.of(entityParams, matcher);
        return findAll(exampleQuery);
    }

    @SuppressWarnings("unchecked")
    default Class<T> getClazz() {
        return (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), PublicationRepository.class);
    };
}
