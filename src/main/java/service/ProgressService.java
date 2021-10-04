package service;

import domain.Progress;
import domain.User;
import exception.EntityNotFoundException;
import repository.ProgressRepository;
import repository.Repository;

import java.util.List;

public class ProgressService implements Service<Progress> {

    private Repository<Progress> progressRepository;

    public ProgressService() {
        progressRepository = new ProgressRepository();
    }

    public ProgressService(Repository<Progress> progressRepository) {
        this.progressRepository = progressRepository;
    }

    public void add(Progress progress) {
        progressRepository.add(progress);
    }

    public void modify(Progress progress) {
        progressRepository
                .findById(progress.getProgressId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Progress with id " + progress.getProgressId() + " was not found!"));

        progressRepository.modify(progress);
    }

    public void remove(String id) {
        progressRepository.remove(progressRepository
                .findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Progress with id " + id + " was not found!"))
        );
    }

    public Progress findById(String id) {
        return progressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Progress with id " + id + " was not found!"));
    }

    public List<Progress> findAll() {
        return progressRepository.findAll();
    }

    @Override
    public List<Progress> findAllComplaintOfUser(User user) {
        return null;
    }
}