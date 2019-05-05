package com.springboot.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

public class DbConnectionTest {
	
//	public static void main(String[] args) {
//        //声明Connection对象
//        Connection con;
//        //驱动程序名
//        String driver = "com.mysql.jdbc.Driver";
//        //URL指向要访问的数据库名mydata
//        String url = "jdbc:mysql://localhost:3306/bigdata-server?useUnicode=true&amp;characterEncoding=UTF-8";
//        //MySQL配置时的用户名
//        String user = "root";
//        //MySQL配置时的密码
//        String password = "root";
//        //遍历查询结果集
//        try {
//            //加载驱动程序
//            Class.forName(driver);
//            //1.getConnection()方法，连接MySQL数据库！！
//            con = DriverManager.getConnection(url,user,password);
//            if(!con.isClosed())
//            {
//                System.out.println("Succeeded connecting to the Database!");
//                System.out.println("数据库数据链接成功！！");
//            }
//            //2.创建statement类对象，用来执行SQL语句！！
//            Statement statement = con.createStatement();
////            要执行的SQL语句
//            String sql = "select * from data_01";
////            3.ResultSet类，用来存放获取的结果集！！
//            ResultSet rs = statement.executeQuery(sql);
//            System.out.println("-----------------");
//            System.out.println("执行结果如下所示:");  
//            System.out.println("-----------------");  
//            System.out.println("姓名" + "\t" + "职称");  
//            System.out.println("-----------------");  
//             
//            String job = null;
//            String id = null;
//            while(rs.next()){
//                //获取stuname这列数据
//                job = rs.getString("d01_");
//                //获取stuid这列数据
//                id = rs.getString("id_");
//
//                //输出结果
//                System.out.println(id + "\t" + job);
//            }
//            rs.close();
//            con.close();
//        } catch(ClassNotFoundException e) {   
//            //数据库驱动类异常处理
//            System.out.println("Sorry,can`t find the Driver!");
//            e.printStackTrace();   
//        } catch(SQLException e) {
//            //数据库连接失败异常处理
//            e.printStackTrace();  
//        }catch (Exception e) {
//            e.printStackTrace();
//        }finally{
//            System.out.println("数据库数据抓取成功！！");
//        }
//    }

	/**
	 * @comment c3p0连接数据库 
	 * @param 	args
	 *
	 * @author 	zhouxin@cdtiansheng.com
	 * @date    2019年3月7日
	 */
//	public static void main(String[] args) {
//		//声明Connection对象
//		Connection con = null;
//		//驱动程序名
//		String driver = "com.mysql.jdbc.Driver";
//        //URL指向要访问的数据库名mydata
//        String url = "jdbc:mysql://localhost:3306/bigdata-server?useUnicode=true&amp;characterEncoding=UTF-8";
//        //MySQL配置时的用户名
//        String user = "root";
//        //MySQL配置时的密码
//        String password = "root";
//		ComboPooledDataSource c3p0 = new ComboPooledDataSource();
//		// 设置连接数据库需要的配置信息
//		try {
//			c3p0.setDriverClass(driver);
//			c3p0.setJdbcUrl(url);
//			c3p0.setUser(user);
//			c3p0.setPassword(password);
//			//2.设置连接池的参数
//			c3p0.setInitialPoolSize(5);
//			c3p0.setMaxPoolSize(15);
//			c3p0.setAutomaticTestTable("TEST");
//			//3.获取数据库连接对象
//			con = c3p0.getConnection();
//		}catch(SQLException e) {
//			//数据库连接失败异常处理
//			e.printStackTrace();  
//			System.out.println("连接失败");
//		}catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			System.out.println(con);
//		}
//	}
	/**
	 * @comment Druid数据库连接测试
	 * @param	 args
	 *
	 * @author 	zhouxin@cdtiansheng.com
	 * @date    2019年3月7日
	 */
	public static void main(String[] args) {
		DruidDataSource dataSourceSSO=null;
		Connection con = null;
		//驱动程序名
		String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://localhost:3306/bigdata-server1?useUnicode=true&amp;characterEncoding=UTF-8";
        //MySQL配置时的用户名
        String user = "root";
        //MySQL配置时的密码
        String password = "root";
		try{

            if(dataSourceSSO==null){

                dataSourceSSO = new DruidDataSource();
                //设置连接参数
                dataSourceSSO.setUrl(url);
                dataSourceSSO.setDriverClassName(driver);
                dataSourceSSO.setUsername(user);
                dataSourceSSO.setPassword(password);
                //配置初始化大小、最小、最大
                dataSourceSSO.setInitialSize(1);
                dataSourceSSO.setMinIdle(1);
                dataSourceSSO.setMaxActive(20);
                //连接泄漏监测
                dataSourceSSO.setRemoveAbandoned(true);
                dataSourceSSO.setRemoveAbandonedTimeout(30);
                //配置获取连接等待超时的时间
                dataSourceSSO.setMaxWait(20000);
                //配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
                dataSourceSSO.setTimeBetweenEvictionRunsMillis(20000);
                //防止过期
                dataSourceSSO.setValidationQuery("SELECT 'x'");
                dataSourceSSO.setTestWhileIdle(true);
                dataSourceSSO.setTestOnBorrow(true);  
                con = dataSourceSSO.getConnection();
            }
        }catch(SQLException e) {
			//数据库连接失败异常处理
			e.printStackTrace();  
			System.out.println("连接失败1");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("连接失败2");
		}finally {
			System.out.println(con);
			if(null != con)
			{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
