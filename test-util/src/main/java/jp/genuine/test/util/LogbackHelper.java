package jp.genuine.test.util;

import ch.qos.logback.classic.Logger;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;

public class LogbackHelper
{
    private static Logger ROOT_LOGGER = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
    private static Logger TEST_CASE_LOGGER;
    private static int CASE_COUNT = 0;
   
    static {
        ROOT_LOGGER.setLevel( Level.OFF );
    }
    
    public static <T> void loggingTestStart( T target){
        TEST_CASE_LOGGER = (Logger) LoggerFactory.getLogger(target.getClass());
        TEST_CASE_LOGGER.setLevel( Level.INFO );
        TEST_CASE_LOGGER.info( "UNIT TEST START" );
        System.out.print("now testing");
    }
        
    public static void countTestCase(){
        CASE_COUNT++;
        System.out.print(".");
    }
    
    public static void loggingTestEnd(){
        System.out.println("finished(" + CASE_COUNT + "case.)");
        TEST_CASE_LOGGER.info( "UNIT TEST END" );
        CASE_COUNT = 0;
    }
}
