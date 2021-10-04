package service;

import com.google.common.annotations.VisibleForTesting;
import domain.ComplaintType;
import domain.User;
import exception.EntityNotFoundException;
import exception.ValidationException;
import repository.ComplaintTypeRepository;
import repository.Repository;

import java.util.List;

public class ComplaintTypeService implements Service<ComplaintType> {

    private Repository<ComplaintType> complaintTypeRepository;

    public ComplaintTypeService() {
        complaintTypeRepository = new ComplaintTypeRepository();
    }

    public ComplaintTypeService(Repository<ComplaintType> complaintTypeRepository) {
        this.complaintTypeRepository = complaintTypeRepository;
    }

    public void add(ComplaintType complaintType) {
        if (isDuplicatedType(complaintType)){

        }else{
            complaintTypeRepository.add(complaintType);
        }
    }

    public void modify(ComplaintType complaintType) {
        complaintTypeRepository
                .findById(complaintType.getComplaintTypeID())
                .orElseThrow(
                        () -> new EntityNotFoundException("Complaint with id " + complaintType.getComplaintTypeID()
                                + " was not found!"));
        complaintTypeRepository.modify(complaintType);
    }

    public void remove(String id) {
        complaintTypeRepository.remove(complaintTypeRepository
                .findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Complaint with id " + id + " was not found!"))
        );
    }

    public ComplaintType findById(String id) {
        return complaintTypeRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Complaint with id " + id + " was not found!"));
    }

    @Override
    public List<ComplaintType> findAll() {
        return null;
    }

    public ComplaintType findByCriteria(String field, String criteria) {
        return complaintTypeRepository.findByCriteria(field, criteria).orElseThrow(() ->
                new EntityNotFoundException("ComplaintType with " + field + ": " + criteria + " was not found!"));
    }

    @Override
    public List<ComplaintType> findAllComplaintOfUser(User user) {
        return null;
    }
    @VisibleForTesting
    void validateType(ComplaintType complaintType) {
        //if (complaint.getYear() < MAX_YEAR_ALLOWED) {
//            throw new ValidationException("The complaint cannot be older than year 2000");
//        }
        if (!isDuplicatedType(complaintType)) {
            throw new ValidationException("There is no Complaint Type with the same type, please, choose correct one.");
        }
    }

    @VisibleForTesting
    public boolean isDuplicatedType(ComplaintType complaintType) {
        return complaintTypeRepository
                .findByCriteria("problemtype_shortname", complaintType.getComplaintTypeShortName())
                .filter(ctsn -> !ctsn.getComplaintTypeID().equals(complaintType.getComplaintTypeShortName()))
                .isPresent();
    }
}