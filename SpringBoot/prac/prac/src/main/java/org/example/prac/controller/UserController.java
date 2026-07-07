import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public User getUser() {
        // ...
    }
}