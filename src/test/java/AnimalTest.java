import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.sql.Timestamp;
import java.text.DateFormat;

public class AnimalTest {

	@Rule
	public DatabaseRule database = new DatabaseRule();

	@Test
	public void animal_interntiateCorrectly() {
		Animal testAnimal = new Animal("Lion");
		assertEquals(true, testAnimal instanceof Animal);
	}	

	@Test
	public void getName_animalInterntiateWithName_Lion() {
		Animal testAnimal = new Animal("Lion");
		assertEquals("Lion", testAnimal.getName());
	}

	@Test
	public void equals_animalInterntiateWithEqualsMethod_True() {
		Animal testAnimal1 = new Animal("Lion");
		Animal testAnimal2 = new Animal("Lion");
		assertTrue(testAnimal1.equals(testAnimal2));
	}

	@Test 
	public void save_animalAddsObjectAnimalToDb_true() {
		Animal testAnimal = new Animal("Lion");
		testAnimal.save();
		Animal saveAnimal = Animal.getAllNonEndangered().get(0);
		assertTrue(testAnimal.equals(saveAnimal));
	}

	@Test 
	public void save_assignsObjectAnId_true() {
		Animal testAnimal = new Animal("Lion");
		testAnimal.save();
		Animal saveAnimal = Animal.getAllNonEndangered().get(0);
		assertEquals(testAnimal.getId(), saveAnimal.getId());
	}

	@Test 
	public void find_returnsTheSameSearchedObject_true() {
		Animal testAnimal1 = new Animal("Lion");
		testAnimal1.save();
		Animal testAnimal2 = new Animal("Elephant");
		testAnimal2.save();
		Animal saveAnimal = Animal.find(testAnimal2.getId());
		assertTrue(testAnimal2.equals(saveAnimal));
	}

	@Test 
	public void update_returnsTheUpdatedName_true() {
		Animal testAnimal = new Animal("Lion");
		testAnimal.save();
		testAnimal.update("King");
		assertEquals("King", Animal.find(testAnimal.getId()).getName());
	}

	@Test 
	public void delete_returnsNull_true() {
		Animal testAnimal = new Animal("Lion");
		testAnimal.save();
		testAnimal.delete();
		assertEquals(null, Animal.find(testAnimal.getId()));
	}
}