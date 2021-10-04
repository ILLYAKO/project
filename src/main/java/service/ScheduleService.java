package service;

import domain.Schedule;
import domain.User;
import exception.EntityNotFoundException;
import repository.ScheduleRepository;
import repository.Repository;

import java.util.List;

public class ScheduleService implements Service<Schedule> {

    private Repository<Schedule> progressRepository;

    public ScheduleService() {
        progressRepository = new ScheduleRepository();
    }

    public ScheduleService(Repository<Schedule> progressRepository) {
        this.progressRepository = progressRepository;
    }

    public void add(Schedule progress) {
        progressRepository.add(progress);
    }

    public void modify(Schedule progress) {
        progressRepository
                .findById(progress.getScheduleId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Schedule with id " + progress.getScheduleId() + " was not found!"));

        progressRepository.modify(progress);
    }

    public void remove(String id) {
        progressRepository.remove(progressRepository
                .findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Schedule with id " + id + " was not found!"))
        );
    }

    public Schedule findById(String id) {
        return progressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Schedule with id " + id + " was not found!"));
    }

    public List<Schedule> findAll() {
        return progressRepository.findAll();
    }

    @Override
    public List<Schedule> findAllComplaintOfUser(User user) {
        return null;
    }
}