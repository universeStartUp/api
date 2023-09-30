import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int attendanceStateId ;
    private int userId;
    private int eventId;

    // Getters
    public int getAttendanceStateId() {
        return attendanceStateId;
    }

    public int getUserId() {
        return userId;
    }

    public int getEventId() {
        return eventId;
    }

    // Setters
    public void setAttendanceStateId(int attendanceStateId) {
        this.attendanceStateId = attendanceStateId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
