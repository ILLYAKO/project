package service;

import domain.Advice;
import domain.User;
import exception.EntityNotFoundException;
import repository.AdviceRepository;
import repository.Repository;

import java.util.List;

public class AdviceService implements Service<Advice> {

    private Repository<Advice> adviceRepository;

    public AdviceService() {
        adviceRepository = new AdviceRepository();
    }

    public AdviceService(Repository<Advice> adviceRepository) {
        this.adviceRepository = adviceRepository;
    }

    public void add(Advice advice) {
        adviceRepository.add(advice);
    }

    public void modify(Advice advice) {
        adviceRepository
                .findById(advice.getAdviceId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Advice with id " + advice.getAdviceId() + " was not found!"));

        adviceRepository.modify(advice);
    }

    public void remove(String id) {
        adviceRepository.remove(adviceRepository
                .findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Advice with id " + id + " was not found!"))
        );
    }

    public Advice findById(String id) {
        return adviceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Advice with id " + id + " was not found!"));
    }

    public List<Advice> findAll() {
        return adviceRepository.findAll();
    }

    @Override
    public List<Advice> findAllComplaintOfUser(User user) {
        return null;
    }
}