import com.centerflag982.metroSchedule.UserInteraction;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UserInteractionTest {

    @Test
    public void getNextArrivalDoesntExplode(){
        List<String> testList = new ArrayList<>();
        testList.add("21:59:00");

        UserInteraction uiTest = new UserInteraction();
        uiTest.getNextArrival(testList);
    }
}
