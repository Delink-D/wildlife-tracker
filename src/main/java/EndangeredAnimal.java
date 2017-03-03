import org.sql2o.*;
import java.util.*;
import java.sql.Timestamp;
import java.util.Date;
import java.text.DateFormat;

public class EndangeredAnimal extends Animals {
	// private properties
	private String age;
	private String health;

	public EndangeredAnimal(String name, String age, String health) {
		this.animal_name 	= name;
		this.endangered 	= 1;
		this.age 			= age;
		this.health 		= health;
	}

	// method to add animals to the database
	public void save() {
		try(Connection con = DB.sql2o.open()) {
			String sql = "INSERT INTO animals(animal_name,endangered,age,health) VALUES(:name,:endangered,:age,:health)";
			this.id = (int) con.createQuery(sql, true)
			.addParameter("name", this.animal_name)
			.addParameter("endangered", this.endangered) 
			.addParameter("age", this.age)
			.addParameter("health", this.health)
			.executeUpdate()
			.getKey();
		}
	}

	public String getName() {
		return animal_name;
	}

	public int getId() {
		return id;
	}

	public String getAge() {
		return age;
	}

	public String getHealth() {
		return health;
	}

	// method to return all animals from table
	public static List<EndangeredAnimal> getAll() {
		try(Connection con = DB.sql2o.open()) {
			String sql = "SELECT * FROM animals";
			return con.createQuery(sql)
			.throwOnMappingFailure(false)
			.executeAndFetch(EndangeredAnimal.class);
		}
	}

	// method to return all animals from table
	public static List<EndangeredAnimal> getAllEndangered() {
		try(Connection con = DB.sql2o.open()) {
			String sql = "SELECT * FROM animals WHERE endangered = 1";
			return con.createQuery(sql)
			.executeAndFetch(EndangeredAnimal.class);
		}
	}

	// method to find a particular animal
	public static EndangeredAnimal find(int id) {
		try(Connection con = DB.sql2o.open()) {
			String sql = "SELECT * FROM animals WHERE id = :id";
			EndangeredAnimal animal = con.createQuery(sql)
			.addParameter("id", id)
			.throwOnMappingFailure(false)
			.executeAndFetchFirst(EndangeredAnimal.class);
			return animal;
		}
	}

	// Update method to make changes to the animal table
	public void update(String name) {
		try(Connection con = DB.sql2o.open()) {
			String sql = "UPDATE animals SET animal_name =:name WHERE id = :id";
			con.createQuery(sql)
			.addParameter("name", name)
			.addParameter("id", this.id)
			.executeUpdate();
		}
	}

	// a delete method that delets particular record of animal
	public void delete() {
		try(Connection con = DB.sql2o.open()) {
			String sql = "DELETE FROM animals WHERE id = :id";
			con.createQuery(sql)
			.addParameter("id", this.id)
			.executeUpdate();
		}
	}

	// overriding equls method
	@Override 
	public boolean equals(Object otherAnimal) {
		if (!(otherAnimal instanceof EndangeredAnimal)) {
			return false;
		}else {
			EndangeredAnimal newAnimal = (EndangeredAnimal) otherAnimal;
			return this.getName().equals(newAnimal.getName()) && this.getId() == newAnimal.getId();
		}
	}
}
