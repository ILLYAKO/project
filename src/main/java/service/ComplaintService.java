package service;

import com.google.common.annotations.VisibleForTesting;
import domain.Complaint;
import exception.EntityNotFoundException;
import exception.ValidationException;
import repository.ComplaintRepository;
import repository.Repository;

import java.util.List;

public class ComplaintService implements Service<Complaint> {

    private Repository<Complaint> complaintRepository;

    public ComplaintService() {
        complaintRepository = new ComplaintRepository();
    }

    public ComplaintService(Repository<Complaint> complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    @VisibleForTesting
    void validate(Complaint complaint) {
//        if (complaint.getYear() < MAX_YEAR_ALLOWED) {
//            throw new ValidationException("The complaint cannot be older than year 2000");
//        }
//
//        if (isDuplicatedPlate(complaint)) {
//            throw new ValidationException("There is another complaint with the same plate, please, choose another one");
//        }
    }

    @VisibleForTesting
//    boolean isDuplicatedPlate(Complaint complaint) {
//        return complaintRepository
//                .findByCriteria("plate", complaint.getPlate())
//                .filter(c -> !c.getId().equals(complaint.getId()))
//                .isPresent();
//    }

    public void add(Complaint complaint) {
        //validate(complaint);
        complaintRepository.add(complaint);
    }

    public void modify(Complaint complaint) {
        complaintRepository
                .findById(complaint.getComplaintId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Complaint with id " + complaint.getComplaintId() + " was not found!"));

        validate(complaint);
        complaintRepository.modify(complaint);
    }

    public void remove(String id) {
        complaintRepository.remove(complaintRepository
                .findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Complaint with id " + id + " was not found!"))
        );
    }

    public Complaint findById(String id) {
        return complaintRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Complaint with id " + id + " was not found!"));
    }

    public List<Complaint> findAll() {
        return complaintRepository.findAll();
    }
}