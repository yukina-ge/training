package jp.genuine.training;

import jp.genuine.training.sample.datasource.CoreDataSourceTestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    CoreDataSourceTestSuite.class
})
public class SampleSpockAllTestSuite
{
}
