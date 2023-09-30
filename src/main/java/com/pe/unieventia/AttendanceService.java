import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public void insertAttendance(int attendanceStateId, int userId, int eventId) {
        Attendance newAttendance = new Attendance();
        newAttendance.setAttendanceStateId(attendanceStateId);
        newAttendance.setUserId(userId);
        newAttendance.setEventId(eventId);
        attendanceRepository.save(newAttendance);
    }
}