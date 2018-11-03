import com.centerflag982.metroSchedule.dao.JDBCDao;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

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
    public void somethingHappens(){
        //not sure what I meant to replace this with
    }

}
