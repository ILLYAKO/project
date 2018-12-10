package service;

import com.google.common.annotations.VisibleForTesting;
import domain.Complaint;
import domain.ComplaintPart;
import domain.ComplaintType;
import exception.EntityNotFoundException;
import exception.ValidationException;
import repository.ComplaintPartRepository;
import repository.ComplaintRepository;
import repository.ComplaintTypeRepository;
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

        System.out.println(complaintPart.getComplaintPart_id());
       // System.out.println(complaint.getComplaintPart());
        System.out.println(complaintPart.getComplaintType_id());
       // System.out.println(complaint.getComplaintDescription());
//        if (complaint.getYear() < MAX_YEAR_ALLOWED) {
//            throw new ValidationException("The complaint cannot be older than year 2000");
//        }

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
        //validate(complaintPart);
        //validatePart(complaint);

        complaintPartRepository.add(complaintPart);
    }

    public void modify(ComplaintPart complaintPart) {
        complaintPartRepository
                .findById(complaintPart.getComplaintPart_id())
                .orElseThrow(
                        () -> new EntityNotFoundException("Complaint with id " + complaintPart.getComplaintPart_id() + " was not found!"));

        //validate(complaint);
        complaintPartRepository.modify(complaintPart);
    }

    public void remove(String id) {
        complaintPartRepository.remove(complaintPartRepository
                .findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Complaint with id " + id + " was not found!"))
        );
    }

    public ComplaintPart findById(String id) {
        return complaintPartRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Complaint with id " + id + " was not found!"));
    }
    public ComplaintPart findByCriteria(String field, String criteria) {
        System.out.println("ComplaintPart.findByCriteria  field: " + field + " = " + criteria);
        return complaintPartRepository.findByCriteria(field, criteria).orElseThrow(() ->
                new EntityNotFoundException("User with " + field + ": " + criteria + " was not found!"));
    }

    public List<ComplaintPart> findAll() {
        return complaintPartRepository.findAll();
    }
}