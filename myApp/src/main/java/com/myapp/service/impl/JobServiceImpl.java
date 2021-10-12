package com.myapp.service.impl;

import com.myapp.domain.Job;
import com.myapp.repository.JobRepository;
import com.myapp.service.JobService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link Job}.
 */
@Service
@Transactional
public class JobServiceImpl implements JobService {

    private final Logger log = LoggerFactory.getLogger(JobServiceImpl.class);

    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Mono<Job> save(Job job) {
        log.debug("Request to save Job : {}", job);
        return jobRepository.save(job);
    }

    @Override
    public Mono<Job> partialUpdate(Job job) {
        log.debug("Request to partially update Job : {}", job);

        return jobRepository
            .findById(job.getId())
            .map(existingJob -> {
                if (job.getJobTitle() != null) {
                    existingJob.setJobTitle(job.getJobTitle());
                }
                if (job.getMinSalary() != null) {
                    existingJob.setMinSalary(job.getMinSalary());
                }
                if (job.getMaxSalary() != null) {
                    existingJob.setMaxSalary(job.getMaxSalary());
                }

                return existingJob;
            })
            .flatMap(jobRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<Job> findAll() {
        log.debug("Request to get all Jobs");
        return jobRepository.findAll();
    }

    public Mono<Long> countAll() {
        return jobRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<Job> findOne(Long id) {
        log.debug("Request to get Job : {}", id);
        return jobRepository.findById(id);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete Job : {}", id);
        return jobRepository.deleteById(id);
    }
}
