package service;

import com.google.common.annotations.VisibleForTesting;
import domain.Complaint;
import domain.ComplaintPart;
import domain.ComplaintType;
import domain.User;
import exception.EntityNotFoundException;
import exception.ValidationException;
import repository.ComplaintPartRepository;
import repository.ComplaintRepository;
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

    @VisibleForTesting
    void validateType(ComplaintType complaintType) {

        System.out.println("complaintTypeShortName: " + complaintType.getComplaintTypeShortName());
       // System.out.println(complaint.getComplaintPart());

       // System.out.println(complaint.getComplaintDescription());
//        if (complaint.getYear() < MAX_YEAR_ALLOWED) {
//            throw new ValidationException("The complaint cannot be older than year 2000");
//        }
//
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

    public void add(ComplaintType complaintType) {
        if (isDuplicatedType(complaintType)){
            System.out.println("Exist complaintType: " + complaintType.getComplaintTypeShortName() );

        }else{
            complaintTypeRepository.add(complaintType);
        }
    }

    public void modify(ComplaintType complaintType) {
        complaintTypeRepository
                .findById(complaintType.getComplaintTypeID())
                .orElseThrow(
                        () -> new EntityNotFoundException("Complaint with id " + complaintType.getComplaintTypeID() + " was not found!"));

        //validate(complaint);
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
        return complaintTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Complaint with id " + id + " was not found!"));
    }

    public ComplaintType findByCriteria(String field, String criteria) {
        System.out.println("ComplaintType.findByCriteria  field: " + field + " = " + criteria);
        return complaintTypeRepository.findByCriteria(field, criteria).orElseThrow(() ->
                new EntityNotFoundException("User with " + field + ": " + criteria + " was not found!"));
    }

    public List<ComplaintType> findAll() {
        return complaintTypeRepository.findAll();
    }
}