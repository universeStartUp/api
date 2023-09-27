import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private List<Task> tasks = new ArrayList<>();

    @GetMapping
    public List<Task> getAllTasks() {
        return tasks;
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        task.setId(tasks.size() + 1L);
        tasks.add(task);
        return task;
    }

    // Otros métodos como obtener una tarea por ID, actualizar, eliminar, etc.
}