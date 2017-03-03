import org.sql2o.*;
import java.util.*;

public class Animal extends Animals {

	public Animal(String name){
		this.animal_name 	= name;
		this.endangered 	= 0;
	}

	// method to add animals to the database
	public void save() {
		try(Connection con = DB.sql2o.open()) {
			String sql = "INSERT INTO animals(animal_name,endangered) VALUES(:name,:endangered)";
			this.id = (int) con.createQuery(sql, true)
			.addParameter("name", this.animal_name)
			.addParameter("endangered", this.endangered)
			.executeUpdate()
			.getKey();
		}
	}

	// method to return all animals from table
	public static List<Animal> getAllNonEndangered() {
		try(Connection con = DB.sql2o.open()) {
			String sql = "SELECT * FROM animals WHERE endangered = 0";
			return con.createQuery(sql)
			.executeAndFetch(Animal.class);
		}
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return animal_name;
	}

	// method to find a particular animal
	public static Animal find(int id) {
		try(Connection con = DB.sql2o.open()) {
			String sql = "SELECT * FROM animals WHERE id = :id";
			Animal animal = con.createQuery(sql)
			.addParameter("id", id)
			.throwOnMappingFailure(false)
			.executeAndFetchFirst(Animal.class);
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
		if (!(otherAnimal instanceof Animal)) {
			return false;
		}else {
			Animal newAnimal = (Animal) otherAnimal;
			return this.getName().equals(newAnimal.getName());
		}
	}
} 