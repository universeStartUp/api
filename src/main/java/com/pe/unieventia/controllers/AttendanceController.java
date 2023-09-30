import com.pe.unieventia.AttendanceRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/asistencias")
    public void createAttendance(@RequestBody AttendanceRequest request) {
        attendanceService.insertAttendance(request.getAttendanceStateId(), request.getUserId(), request.getEventId());
    }
}