package Controller;

import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;

public class Pool {
   
    public DataSource theDataSource;
    
    public Pool(){
        InitializeDataSource();
    }
    
    private void InitializeDataSource(){
        BasicDataSource theBasicDataSource = new BasicDataSource();
        theBasicDataSource.setDriverClassName("org.gjt.mm.mysql.Driver");
        theBasicDataSource.setUsername("root");
        theBasicDataSource.setPassword("root");
        theBasicDataSource.setUrl("jdbc:mysql://localhost:3306/cargosautomaticos");
        theBasicDataSource.setMaxActive(50);
        
        theDataSource = theBasicDataSource;
    }   
}
