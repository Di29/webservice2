import controllers.UserController;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class MyApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> hs = new HashSet<>();
        hs.add(UserController.class);
        hs.add(MultiPartFeature.class);
        return hs;
    }
}
