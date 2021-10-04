package service;


import domain.Complaint;
import domain.ComplaintPart;
import domain.ComplaintType;
import domain.User;
import exception.EntityNotFoundException;
import repository.ComplaintRepository;
import repository.Repository;

import java.util.List;
import java.util.UUID;

public class ComplaintService implements Service<Complaint> {

    private Repository<Complaint> complaintRepository;

    public ComplaintService() {
        complaintRepository = new ComplaintRepository();
    }

    public ComplaintService(Repository<Complaint> complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    public void add(Complaint complaint) {
        ComplaintType complaintType = new ComplaintType();
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
                        () -> new EntityNotFoundException("Complaint with id "
                                + complaint.getComplaintId() + " was not found!"));
        complaintRepository.modify(complaint);
    }

    public void remove(String id) {
        complaintRepository.remove(
                complaintRepository.findById(id)
                        .orElseThrow(
                                () -> new EntityNotFoundException("Complaint with id " + id + " was not found!"))
        );
    }

    public Complaint findById(String id) {
        return complaintRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Complaint with id " + id + " was not found!"));
    }

    public List<Complaint> findAll() {
        return complaintRepository.findAll();
    }

    public List<Complaint> findAllComplaintOfUser(User user) {
        return complaintRepository.findAllComplaintOfUser(user);
    }
}