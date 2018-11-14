import com.centerflag982.metroSchedule.Stop;
import com.centerflag982.metroSchedule.dao.JDBCDao;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class JDBCDaoTest {

    @Test
    public void connects(){

        Connection testConnection = null;

        try {
            testConnection = JDBCDao.getConnection();
        } catch (SQLException e){
            System.out.println("SQL exception");
        }

        assertNotNull(testConnection);
    }

    @Test
    public void stationListRetrieved(){
        List<Stop> testList;
        JDBCDao testDao = new JDBCDao();

        testList = testDao.getStationList();

        assertNotNull(testList);

    }

}
