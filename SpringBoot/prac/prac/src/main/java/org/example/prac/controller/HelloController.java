import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public void getHello() {
        // ...
    }

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public void postHello() {
        // ...
    }

    @RequestMapping(value = "/hello", method = RequestMethod.PUT)
    public void putHello() {
        // ...
    }

    @RequestMapping(value = "/hello", method = RequestMethod.PATCH)
    public void patchHello() {
        // ...
    }

    @RequestMapping(value = "/hello", method = RequestMethod.DELETE)
    public void deleteHello() {
        // ...
    }
}