package com.myapp.service;

import com.myapp.domain.Job;
import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link Job}.
 */
public interface JobService {
    /**
     * Save a job.
     *
     * @param job the entity to save.
     * @return the persisted entity.
     */
    Mono<Job> save(Job job);

    /**
     * Partially updates a job.
     *
     * @param job the entity to update partially.
     * @return the persisted entity.
     */
    Mono<Job> partialUpdate(Job job);

    /**
     * Get all the jobs.
     *
     * @return the list of entities.
     */
    Flux<Job> findAll();

    /**
     * Returns the number of jobs available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" job.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<Job> findOne(Long id);

    /**
     * Delete the "id" job.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
