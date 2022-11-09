package com.lschaan.springquartz.service;

import com.lschaan.springquartz.errors.JobAlreadyExistsException;
import com.lschaan.springquartz.errors.JobNotFoundException;
import com.lschaan.springquartz.errors.SchedulingErrorException;
import com.lschaan.springquartz.repository.MessageJobRepository;
import com.lschaan.springquartz.repository.entity.MessageJob;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageJobSchedulerService {

    private static final Logger logger = LoggerFactory.getLogger(MessageJobSchedulerService.class);
    private final MessageJobRepository messageJobRepository;
    private final SchedulerFactoryBean schedulerFactoryBean;

    public MessageJobSchedulerService(MessageJobRepository messageJobRepository, SchedulerFactoryBean schedulerFactoryBean) {
        this.messageJobRepository = messageJobRepository;
        this.schedulerFactoryBean = schedulerFactoryBean;
        scheduleAllExistingJobs();
    }

    public MessageJob create(MessageJob job) {
        boolean jobAlreadyExists = messageJobRepository.existsByName(job.getName());
        if (jobAlreadyExists) {
            throw new JobAlreadyExistsException();
        }

        scheduleJob(job);
        return messageJobRepository.save(job);
    }

    public MessageJob findJobByName(String jobName) {
        return messageJobRepository.findByName(jobName).orElseThrow(JobNotFoundException::new);
    }

    public List<MessageJob> findAll() {
        return messageJobRepository.findAll();
    }

    public void delete(String jobName) {
        messageJobRepository.deleteByName(jobName);
        try {
            schedulerFactoryBean.getScheduler().deleteJob(JobKey.jobKey(jobName));
        } catch (SchedulerException schedulerException) {
            logger.warn("Unable to delete job {}", jobName, schedulerException);
        }
    }

    private void scheduleAllExistingJobs() {
        logger.info("Scheduling all jobs.");
        messageJobRepository.findAll().forEach(this::scheduleJob);
    }

    private void scheduleJob(MessageJob job) {
        JobDetail jobDetail = getJobDetail(job);
        Trigger trigger = getJobTrigger(job, jobDetail);
        scheduleJob(jobDetail, trigger);
    }

    private void scheduleJob(JobDetail jobDetail, Trigger trigger) {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        } catch (SchedulerException schedulerException) {
            logger.error("Error while scheduling the job {}", jobDetail.getKey().getName(), schedulerException);
            throw new SchedulingErrorException();
        }
    }

    private Trigger getJobTrigger(MessageJob job, JobDetail jobDetail) {
        return TriggerBuilder.newTrigger()
                .withIdentity(job.getName() + "-trigger")
                .forJob(jobDetail.getKey())
                .withSchedule(CronScheduleBuilder.cronSchedule(job.getCron()))
                .build();
    }

    private JobDetail getJobDetail(MessageJob job) {
        return JobBuilder.newJob()
                .withIdentity(job.getName())
                .ofType(MessageJobExecutor.class)
                .build();
    }
}
