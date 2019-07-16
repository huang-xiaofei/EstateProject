/**
 * 
 */
package com.estate.frontier.table.config;

//@Configuration
//@ComponentScan(basePackages = { "com.gitee.sunchenbin.mybatis.actable.manager.*" })
public class MybatisTableConfig {
//
//	@Value("${spring.datasource.driverClassName}")
//	private String driver;
//
//	@Value("${spring.datasource.url}")
//	private String url;
//
//	@Value("${spring.datasource.username}")
//	private String username;
//
//	@Value("${spring.datasource.password}")
//	private String password;
//
//	@Bean
//	public PropertiesFactoryBean configProperties() throws Exception {
//		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
//		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//		propertiesFactoryBean.setLocations(resolver.getResources("classpath*:createTableConfig.properties"));
//		return propertiesFactoryBean;
//	}
//
//	// 配置数据库连接池
//	@Bean
//	public DruidDataSource dataSource() {
//		DruidDataSource dataSource = new DruidDataSource();
//		dataSource.setDriverClassName(driver);
//		dataSource.setUrl(url);
//		dataSource.setUsername(username);
//		dataSource.setPassword(password);
//		dataSource.setMaxActive(30);
//		dataSource.setInitialSize(10);
//		dataSource.setValidationQuery("SELECT 1");
//		dataSource.setTestOnBorrow(true);
//		return dataSource;
//	}
//
//	@Bean
//	public DataSourceTransactionManager dataSourceTransactionManager() {
//		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
//		dataSourceTransactionManager.setDataSource(dataSource());
//		return dataSourceTransactionManager;
//	}
//
//	@Bean
//	public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
//		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//		sqlSessionFactoryBean.setDataSource(dataSource());
//		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//		sqlSessionFactoryBean.setMapperLocations(
//				resolver.getResources("classpath*:com/gitee/sunchenbin/mybatis/actable/mapping/*/*.xml"));
//		sqlSessionFactoryBean.setTypeAliasesPackage("com.estate.frontier.model.*");
//		return sqlSessionFactoryBean;
//	}
//
}