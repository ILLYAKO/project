package service;

import com.google.common.annotations.VisibleForTesting;
import domain.Advice;
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

    @VisibleForTesting
    void validate(Advice advice) {
//        if (advice.getYear() < MAX_YEAR_ALLOWED) {
//            throw new ValidationException("The advice cannot be older than year 2000");
//        }
//
//        if (isDuplicatedPlate(advice)) {
//            throw new ValidationException("There is another advice with the same plate, please, choose another one");
//        }
    }

    @VisibleForTesting
//    boolean isDuplicatedPlate(Advice advice) {
//        return adviceRepository
//                .findByCriteria("plate", advice.getPlate())
//                .filter(c -> !c.getId().equals(advice.getId()))
//                .isPresent();
//    }

    public void add(Advice advice) {
        //validate(advice);
        adviceRepository.add(advice);
    }

    public void modify(Advice advice) {
        adviceRepository
                .findById(advice.getAdviceId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Advice with id " + advice.getAdviceId() + " was not found!"));

        validate(advice);
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
}