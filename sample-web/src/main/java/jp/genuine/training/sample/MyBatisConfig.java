package jp.genuine.training.sample;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import jp.genuine.training.sample.base.datasource.infrastructure.mybatis.LocalDateHandler;
import jp.genuine.training.sample.base.datasource.infrastructure.mybatis.LocalDateTimeHandler;


public class MyBatisConfig {

	@Configuration
	@MapperScan(basePackages = {"jp.genuine.training.sample.datasource.sampledb"}, sqlSessionFactoryRef = "sampleSqlSessionFactory")
	static class SampleDatasourceConfig {

		@Bean
		@ConfigurationProperties(prefix = "sample.datasource.sampledb")
		public DataSource sampleDBDataSource() {
			return DataSourceBuilder.create().build();
		}

		@Bean
		public SqlSessionFactory sampleSqlSessionFactory() throws Exception {
			return sqlSessionFactory(sampleDBDataSource());
		}

	}

	private static SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setTypeHandlers(new TypeHandler[] {
				new LocalDateHandler(),
				new LocalDateTimeHandler()
			});
		sessionFactory.setConfigLocation(new ClassPathResource("mybatis.xml"));
		return sessionFactory.getObject();
	}
}
