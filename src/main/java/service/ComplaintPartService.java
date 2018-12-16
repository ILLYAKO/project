package service;

import com.google.common.annotations.VisibleForTesting;
import domain.ComplaintPart;
import domain.User;
import exception.EntityNotFoundException;
import exception.ValidationException;
import repository.ComplaintPartRepository;
import repository.Repository;

import java.util.List;

public class ComplaintPartService implements Service<ComplaintPart> {

    private Repository<ComplaintPart> complaintPartRepository;

    public ComplaintPartService() {
        complaintPartRepository = new ComplaintPartRepository();
    }

    public ComplaintPartService(Repository<ComplaintPart> ComplaintPartRepository ) {
        this.complaintPartRepository = ComplaintPartRepository;
    }

    @VisibleForTesting
    void validate(ComplaintPart complaintPart) {
        if (isDuplicatedPart(complaintPart)) {
            throw new ValidationException("There is another complaint with the same complaintPart, please, choose another one");
        }
    }

    @VisibleForTesting
    boolean isDuplicatedPart(ComplaintPart complaintPart) {
        return complaintPartRepository
                .findByCriteria("problematicpart_name", complaintPart.getComplaintPartName())
                .filter(cp -> !cp.getComplaintPart_id().equals(complaintPart.getComplaintPart_id()))
                .isPresent();
    }

    public void add(ComplaintPart complaintPart) {
        complaintPartRepository.add(complaintPart);
    }

    public void modify(ComplaintPart complaintPart) {
        complaintPartRepository
                .findById(complaintPart.getComplaintPart_id())
                .orElseThrow(
                        () -> new EntityNotFoundException("Complaint with id " + complaintPart.getComplaintPart_id()
                                + " was not found!"));

        complaintPartRepository.modify(complaintPart);
    }

    public void remove(String id) {
        complaintPartRepository.remove(complaintPartRepository
                .findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Complaint with id " + id + " was not found!")
                )
        );
    }

    public ComplaintPart findById(String id) {
        return complaintPartRepository
                .findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Complaint with id " + id + " was not found!")
                );
    }
    public ComplaintPart findByCriteria(String field, String criteria) {
        return complaintPartRepository.findByCriteria(field, criteria)
                .orElseThrow(() ->
                new EntityNotFoundException("User with " + field + ": " + criteria + " was not found!"));
    }

    public List<ComplaintPart> findAll() {
        return complaintPartRepository.findAll();
    }

    @Override
    public List<ComplaintPart> findAllComplaintOfUser(User user) {
        return null;
    }
}