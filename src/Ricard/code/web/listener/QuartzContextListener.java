package Ricard.code.web.listener;

import Ricard.code.util.JDBCUtils;
import com.mysql.jdbc.AbandonedConnectionCleanupThread;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@WebListener()
public class QuartzContextListener implements ServletContextListener {


    public static final List<String> MANUAL_DESTROY_THREAD_IDENTIFIERS = Arrays.asList("QuartzScheduler", "scheduler_Worker");

    /**
     * 监听ServletContext对象创建。ServletContextEvent对象服务器启动后自动创建
     *
     * @param sce
     */
    public void contextInitialized(ServletContextEvent sce) {
        JDBCUtils.init();
    }

    /**
     * 在服务器关闭后,ServletContext对象被销毁。当服务器正常关闭后该方法被调用
     *
     * @param sce
     */
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("web service stop");
        try {
            while(DriverManager.getDrivers().hasMoreElements()) {
                DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
            }
            System.out.println("jdbc Driver close");
        }  catch (SQLException e) {
            e.printStackTrace();
        }

        AbandonedConnectionCleanupThread.checkedShutdown();
    }

}
