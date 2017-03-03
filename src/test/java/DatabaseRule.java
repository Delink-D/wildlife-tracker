import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

	@Override 
	protected void before() {
		DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "delink", "0000");
	}

	@Override
	protected void after() {
		try(Connection con = DB.sql2o.open()){
			String sqlp = "DELETE FROM animals *;";
			// String sqlm = "DELETE FROM sighting *;";
			con.createQuery(sqlp).executeUpdate();
			// con.createQuery(sqlm).executeUpdate();
		}
	}
}