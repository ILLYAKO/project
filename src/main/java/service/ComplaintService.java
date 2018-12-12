package service;

import com.google.common.annotations.VisibleForTesting;
import domain.Complaint;
import domain.ComplaintPart;
import domain.ComplaintType;
import exception.EntityNotFoundException;
import exception.InfrastructureException;
import exception.ValidationException;
import repository.ComplaintPartRepository;
import repository.ComplaintRepository;
import repository.ComplaintTypeRepository;
import repository.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ComplaintService implements Service<Complaint> {

    private Repository<Complaint> complaintRepository;

    public ComplaintService() {
        complaintRepository = new ComplaintRepository();
    }

    public ComplaintService(Repository<Complaint> complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    @VisibleForTesting
    void validateType(Complaint complaint) {
        ComplaintTypeService complaintTypeService = new ComplaintTypeService();
        //complaintTypeService.validateType(complaint.getComplaintType());





        ;
        System.out.println(complaint.getComplaintId());
       // System.out.println(complaint.getComplaintPart());
        System.out.println(complaint.getComplaintType());
       // System.out.println(complaint.getComplaintDescription());
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

        System.out.println("--ComplaintService.add  complaint.getComplaintType(): "+complaint.getComplaintType());
        System.out.println("--ComplaintService.add  complaint.getComplaintDescription(): "+complaint.getComplaintDescription());
        System.out.println("--ComplaintService.add  complaint.getComplaintPart(): "+complaint.getComplaintPart());
        System.out.println("--ComplaintService.add  complaint.getComplaintPart(): "+complaint.getInformer().getUserFirstName());
        ComplaintType complaintType =new ComplaintType();
        complaintType.setComplaintTypeShortName(complaint.getComplaintTypeShortName());
        ComplaintTypeService complaintTypeService = new ComplaintTypeService();
        ComplaintType complaintTypeNew = complaintTypeService.findByCriteria("problemtype_shortname",
                complaintType.getComplaintTypeShortName());

        ComplaintPart complaintPart = new ComplaintPart();
        complaintPart.setComplaintPartName(complaint.getComplaintPartName());
        ComplaintPartService complaintPartService = new ComplaintPartService();
            complaintPart.setComplaintPart_id(UUID.randomUUID().toString());
            complaintPart.setComplaintType_id(complaintTypeNew.getComplaintTypeID());
            complaintPart.setComplaintPartDescription(complaint.getComplaintDescription());
            complaintPartService.add(complaintPart);
////////////////
        //A universally unique identifier (UUID) is a 128-bit number used to identify information in computer systems
        String complaintId = UUID.randomUUID().toString();
        Complaint complaintNew = new Complaint(
                complaintId,
                complaint.getInformer(),
                complaintTypeNew,
                complaintPart,
                complaint.getComplaintDescription()
        );
        complaintRepository.add(complaintNew);
    }

    public void modify(Complaint complaint) {
        complaintRepository
                .findById(complaint.getComplaintId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Complaint with id " + complaint.getComplaintId() + " was not found!"));

        //validate(complaint);
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