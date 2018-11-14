import com.centerflag982.metroSchedule.UserInteraction;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UserInteractionTest {

    @Test
    public void getNextArrivalDoesntExplode(){
        List<String> testList = new ArrayList<>();
        testList.add("19:28:00");

        UserInteraction uiTest = new UserInteraction();
        uiTest.getNextArrival(testList);
    }
}
