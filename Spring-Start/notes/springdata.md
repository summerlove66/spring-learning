
## spring data
- 1 .JDBC_URL = "jdbc:mysql://localhost:3306/test?useSSL=false&characterEncoding=utf8&serverTimezone=UTC"; 添加driver jar包

- 2 .步骤
	- 1.获取连接  Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD) 

	- 2.获取 PreparedStatement ps = PrepareStatement 对象 conn.prepareStatement("DELETE FROM students WHERE id=?") 并设置 参数值
	- 3.执行动作， 更新（增删改）  ps.executeUpdate(); 查询 ps.executeQuery()
	- 4.关闭资源  conn ,ps ,(ResultSet 对象 查重的情况下)

- 3 .代码
 
		public static void main(String[] args) throws SQLException {
		Connection connection = DriverManager.getConnection(url,databaseUser,password);
		try(PreparedStatement preparedStatement = connection.prepareStatement("UPDATE student SET NAME =? WHERE NAME =?")){
		preparedStatement.setObject(1,"YO BITCH");
		preparedStatement.setObject(2,"fucking good");
		preparedStatement.executeUpdate();
		//try(ResultSet resultSet = preparedStatement.executeQuery()){
		//while (resultSet.next()){
		//System.out.println(resultSet.getString("NAME"));
		//}
		//}
		
		}
		}
	   
    
- 4 . 数据源类型

		org.springframework.jdbc.datasource.DriverManagerDataSource
		org.apache.commons.dbcp2.BasicDataSource
		...


- 5 .sql 占位符表达法 ,Mapper

		public Person getDriverByCar(String carName) {
		    String driverIdSql = "SELECT driver_id FROM car WHERE car_name = ?";
		    try {
		        int driverId = jdbcTemplate.queryForObject(driverIdSql, new Object[]{carName}, Integer.class); //数组 与参数对应
		        String personSql = "SELECT * FROM person WHERE id =" + driverId;
		        return jdbcTemplate.queryForObject(personSql, new personMapper()); //也可以不传参
		    } catch (EmptyResultDataAccessException e) {
		        e.printStackTrace();
		        return null;
		    }
		}


- 6 . namedParameterJdbcTemplate

		 String sql = "INSERT INTO person(name,age) VALUES (:name,:age)";
		        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("name",person.getName())
		                                                    .addValue("age",person.getAge());
		
		        try {
		            namedParameterJdbcTemplate.update(sql,sqlParameterSource);
		            return true;
		        } catch (DataAccessException e) {
		            e.printStackTrace();
		            return false;
		        }





- 7 . Transactional （事务 )

- 8 . DaoImpl 可以继承JdbcDaoSupport 自动有jdbcTemplate