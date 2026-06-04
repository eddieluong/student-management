import controller.StudentController;
import repository.StudentRepository;
import service.StudentService;

public class App {
    public static void main(String[] args) throws Exception {
       StudentRepository repository = new StudentRepository();
       StudentService service = new StudentService(repository);
       StudentController controller = new StudentController(service);

       controller.start();
    }
}
