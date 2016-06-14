package jp.genuine.training.sample.datasource.sampledb.sample;

import javax.sql.DataSource

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration

import jp.genuine.test.util.DBUnitHelper;
import jp.genuine.test.util.LogbackHelper
import jp.genuine.training.TestConfig;
import spock.lang.Specification
import spock.lang.Unroll;;

@ActiveProfiles("test")
@ContextConfiguration(classes = TestConfig.class, initializers = ConfigFileApplicationContextInitializer.class)
public class SampleDataSrouceSpec extends Specification
{
	@Autowired
    SampleDataSource sampleDataSource;

	@Autowired
    @Qualifier("sampleDBDataSource")
    DataSource sampleDBDataSource;
    
    private static IDatabaseConnection connection;
    
    def setupSpec(){
        LogbackHelper.loggingTestStart( this )
    }
    
    def setup(){
        LogbackHelper.countTestCase()
        connection = new DatabaseConnection( sampleDBDataSource.connection )
    }
    
    def cleanup(){
        connection.close()
    }
    
    def cleanupSpec(){
       LogbackHelper.loggingTestEnd()
    }

	@Unroll
    def "<#TEST_NAME>find:データの取得->#TITLE"(){   
        when:
            String resultValue = sampleDataSource.find()
            
        then:
            println resultValue
            resultValue == RESULT
            
        where:
            TEST_NAME = this.class.name  
            RESULT = "Sample"
    }
    
}