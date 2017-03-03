import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.sql.Timestamp;
import java.text.DateFormat;

public class EndangeredAnimalTest {

	@Rule
	public DatabaseRule database = new DatabaseRule();

	@Test
	public void Endangered_interntiateCorrectly() {
		EndangeredAnimal testEndangered = new EndangeredAnimal("Lion", "young", "ill");
		assertEquals(true, testEndangered instanceof EndangeredAnimal);
	}

	@Test
	public void getName_animalInterntiateWithName_Lion() {
		EndangeredAnimal testEndangered = new EndangeredAnimal("Lion", "young", "ill");
		assertEquals("Lion", testEndangered.getName());
	}

	@Test
	public void equals_animalInterntiateWithEqualsMethod_True() {
		EndangeredAnimal testEndangered = new EndangeredAnimal("Lion", "young", "ill");
		EndangeredAnimal testEndangered2 = new EndangeredAnimal("Lion", "young", "ill");
		assertTrue(testEndangered.equals(testEndangered2));
	}

	@Test 
	public void save_animalAddsObjectAnimalToDb_true() {
		EndangeredAnimal testEndangered = new EndangeredAnimal("Lion", "young", "ill");
		testEndangered.save();
		EndangeredAnimal saveAnimal = EndangeredAnimal.getAllEndangered().get(0);
		assertTrue(testEndangered.equals(saveAnimal));
	}

	@Test 
	public void save_assignsObjectAnId_true() {
		EndangeredAnimal testEndangered = new EndangeredAnimal("Lion", "young", "ill");
		testEndangered.save();
		EndangeredAnimal saveAnimal = EndangeredAnimal.getAllEndangered().get(0);
		assertEquals(testEndangered.getId(), saveAnimal.getId());
	}

	@Test 
	public void find_returnsTheSameSearchedObject_true() {
		EndangeredAnimal testEndangered = new EndangeredAnimal("Lion", "young", "ill");
		testEndangered.save();
		EndangeredAnimal testEndangered2 = new EndangeredAnimal("Lion", "young", "ill");
		testEndangered2.save();
		EndangeredAnimal saveAnimal = EndangeredAnimal.find(testEndangered2.getId());
		assertTrue(testEndangered2.equals(saveAnimal));
	}

	@Test 
	public void update_returnsTheUpdatedName_true() {
		EndangeredAnimal testEndangered = new EndangeredAnimal("Lion", "young", "ill");
		testEndangered.save();
		testEndangered.update("King");
		assertEquals("King", EndangeredAnimal.find(testEndangered.getId()).getName());
	}

	@Test 
	public void delete_returnsNull_true() {
		EndangeredAnimal testEndangered = new EndangeredAnimal("Lion", "young", "ill");
		testEndangered.save();
		testEndangered.delete();
		assertEquals(null, EndangeredAnimal.find(testEndangered.getId()));
	}
}