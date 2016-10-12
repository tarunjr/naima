package nalanda.com.naima.models;

import java.util.List;

/**
 * Created by ps1 on 10/11/16.
 */
public class Prescription {
    private String date;

    private String notes;

    private String review;

    private List<Medicine> medicines;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }
}
