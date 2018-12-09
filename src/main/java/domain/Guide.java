package domain;

import java.time.Instant;

public class Guide {
    private User user;
    private Complaint complaint;
    private Advice advice;
    private Instant complaintDate;

    public Guide() {
    }

    public Guide(User user, Complaint complaint, Advice advice, Instant complaintDate) {
        this.user = user;
        this.complaint = complaint;
        this.advice = advice;
        this.complaintDate = complaintDate;
    }
}
