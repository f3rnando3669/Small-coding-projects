package midterm;

import java.util.Arrays;

/**
 * Tester for student solutions to midterm
 */
public class Midterm {

    // Global DSHashMap and DSGraph for all problems to use
    static DSHashMap<String> hashmap;
    static DSGraph graph;
    static String hashMapAnswer;

    // Global grade
    static int grade = 0;

    public static void main(String[] args){

        DSArrayList<Integer> a1 = new DSArrayList<>();
        DSArrayList<Integer> a2 = new DSArrayList<>();
        a1.add(1);
        a1.add(2);
        System.out.println("Equal? " + a1.equals(a2));

        // First grade the three DSHashMap questions
        gradeLongestChain();
        gradeKeysForValue();
        gradeToString();

        // Now grade the two DSGraph questions
        gradeTriangleFree();
        gradeNumberOfComponents();
	gradeHasASquare();
    }

    private static void gradeLongestChain() {
        System.out.println("\nGrading the longestChain function");
        hashmap = new DSHashMap<String>();
        Integer len; // The length of the longest chain

        len = hashmap.longestChain();
        checkExpect(len, 0, "No entries", 2);

        hashmap.put("Love", "More");
        len = hashmap.longestChain();
        checkExpect(len, 1, "One entry", 2);
        
        hashmap.put("Peace", "More");
        len = hashmap.longestChain();
        checkExpect(len, 2, "Two colliding entries", 2);
        
        hashmap.put("Time", "More");
        len = hashmap.longestChain();
        checkExpect(len, 2, "Third entry, but in a new chain", 2);
        
        for(int i = 0; i < 80; i++) hashmap.put("x"+i, "");
        len = hashmap.longestChain();
        checkExpect(len, 4, "80 more entries", 2);
        
        for(int i = 0; i < 8000; i++) hashmap.put("x"+i, "");
        len = hashmap.longestChain();
        checkExpect(len, 9, "8000 more entries", 2);
        assert false;
    }

    private static void gradeKeysForValue() {
        System.out.println("\nGrading the keysForValue function");
        hashmap = new DSHashMap<String>();
        DSArrayList<String> sanswer;
        DSArrayList<String> tanswer = new DSArrayList<>();

        sanswer = hashmap.keysForValue("Bluff");
        checkExpect(sanswer, tanswer, "Empty hash map", 3);

        String searchvalue = "" + (((int)(Math.random()  * 10000)) % 7);
        hashMapAnswer = "";
        for(int i = 0; i < 100; i++){
            String key = "" + i;
            String value = "" + (((int)(Math.random()  * 10000)) % 7);
            hashmap.put(key, value);
            hashMapAnswer = hashMapAnswer + ", " + key + ":" + value;
            if(value.equals(searchvalue)){
                tanswer.add(key);
            }
        }
        sanswer = hashmap.keysForValue(searchvalue);
        checkExpect(sanswer, tanswer, "100-element hash map", 7);

        // Get setup for next problem
        hashMapAnswer = "{" + hashMapAnswer.substring(2) + "}";
    }

    private static void gradeToString() {
        System.out.println("\nGrading the toString function");
        String sanswer = hashmap.toString();
        checkExpectHashMapStrings(sanswer, hashMapAnswer, "The same 100-element hash map", 10);
    }

    private static void gradeTriangleFree() {
        System.out.println("\nGrading the triangleFree function");
        DSGraph g = new DSGraph();
        checkExpect(g.triangleFree(), true, "Empty graph", 1);

        // Make a square
        g.addEdge("a", "b");
        g.addEdge("a", "d");
        g.addEdge("c", "b");
        g.addEdge("c", "d");
        checkExpect(g.triangleFree(), true, "Square graph", 1);

        // Add a spurious edge
        g.addEdge("d", "x");
        checkExpect(g.triangleFree(), true, "Square graph with an extra edge", 2);

        // Add another edge, but still no triangle
        g.addEdge("b", "x");
        checkExpect(g.triangleFree(), true, "K(2, 3) graph (google it)", 2);

        // Add three completely new vertices to make a triangle
        g.addEdge("r", "t");
        g.addEdge("s", "r");
        g.addEdge("t", "s");
        checkExpect(g.triangleFree(), false, "Two components, one has a triangle", 2);

        // Add one edge to create another triangle in the original component
        g.addEdge("a", "c");
        checkExpect(g.triangleFree(), false, "Two components, one has a triangle", 2);

    }

    private static void gradeNumberOfComponents() {
        System.out.println("\nGrading the numberOfComponents function");
        DSGraph g = new DSGraph();
        checkExpect(g.numberOfComponents(), 0, "Empty graph", 1);

        // Make a square
        g.addEdge("a", "b");
        g.addEdge("a", "d");
        g.addEdge("c", "b");
        g.addEdge("c", "d");
        checkExpect(g.numberOfComponents(), 1, "Square graph", 1);

        // Add a spurious edge
        g.addEdge("d", "x");
        checkExpect(g.numberOfComponents(), 1, "Square graph with a pendant edge", 1);

        // Add an outlier vertex
        g.addVertex("q");
        checkExpect(g.numberOfComponents(), 2, "New spurious vertex", 1);

        // Add another outlier vertex
        g.addVertex("r");
        checkExpect(g.numberOfComponents(), 3, "New spurious vertex", 1);

        // And another
        g.addVertex("s");
        checkExpect(g.numberOfComponents(), 4, "New spurious vertex", 1);

        // Connect two spurious vertices
        g.addEdge("r", "s");
        checkExpect(g.numberOfComponents(), 3, "Combine two components", 1);

        // Connect two spurious vertices
        g.addEdge("r", "q");
        checkExpect(g.numberOfComponents(), 2, "Combine two components", 1);

        // Rejoin to a single component
        g.addEdge("r", "a");
        checkExpect(g.numberOfComponents(), 1, "Combine two components", 2);
    }


    private static void gradeHasASquare(){
        System.out.println("\nGrading the hasASquare function");
        DSGraph g = new DSGraph();
        checkExpect(g.hasASquare(), false, "Empty graph", 1);

	// Make a single edge
        g.addEdge("a", "b");
        checkExpect(g.hasASquare(), false, "Single edge", 1);

        // Two more edges - almost a square
        g.addEdge("a", "d");
        g.addEdge("c", "b");
        checkExpect(g.hasASquare(), false, "Almost a square", 1);

	// Now make it a square
        g.addEdge("c", "d");
        checkExpect(g.hasASquare(), true, "Just a square", 1);
	
	// Start over with an empty graph
	g = new DSGraph();
	// Make a dodecahedron
	g.addEdge("a", "b"); g.addEdge("b", "c"); g.addEdge("c", "d");
	g.addEdge("d", "e"); g.addEdge("e", "a");
	g.addEdge("1", "2"); g.addEdge("2", "3"); g.addEdge("3", "4");
	g.addEdge("4", "5"); g.addEdge("5", "6"); g.addEdge("6", "7");
	g.addEdge("7", "8"); g.addEdge("8", "9"); g.addEdge("9", "10");
	g.addEdge("10", "1");
	g.addEdge("v", "w"); g.addEdge("w", "x"); g.addEdge("x", "y");
	g.addEdge("y", "z"); g.addEdge("z", "v");
	g.addEdge("a", "1"); g.addEdge("b", "3"); g.addEdge("c", "5");
	g.addEdge("d", "7"); g.addEdge("e", "9");
	g.addEdge("v", "2"); g.addEdge("w", "4"); g.addEdge("x", "6");
	g.addEdge("y", "8"); g.addEdge("z", "10"); 
        checkExpect(g.hasASquare(), false, "Dodecahedron", 1);

	// Add an edge on to ab321 face
	g.addEdge("a", "3");
	checkExpect(g.hasASquare(), true, "Dodecahedron + one edge", 1);
    }


    /**
     * For grading
     */
    private static void checkExpect(Object studentAnswer, Object correctAnswer, String string, int value) {
        if(studentAnswer.equals(correctAnswer)){
            grade += value;
            System.out.printf("Correct answer %s for %s. %d/%d\n", String.valueOf(studentAnswer), string, value, value);
        } else {
            System.out.printf("Incorrect answer %s for %s. %d/%d\n", String.valueOf(studentAnswer), string, 0, value);
            System.out.printf("--- Correct answer was %s\n", String.valueOf(correctAnswer));
        }
    }

    /**
     * Test two hash map strings for equality, even if they're scrambled, hopefully.
     * @param s1
     * @param s2
     * @return
     */
    private static void checkExpectHashMapStrings(String studentAnswer, String correctAnswer, String string, int value){
        String[] m1 = studentAnswer.replaceAll("[{} ]", "").split(",");
        String[] m2 = correctAnswer.replaceAll("[{} ]", "").split(",");
        Arrays.sort(m1);
        Arrays.sort(m2);
        boolean correct = true;
        if(m1.length != m2.length) correct = false;
        for(int i = 0; i < m1.length; i++){
            if(!m1[i].equals(m2[i])) correct = false;
        }
        if(correct){
            grade += value;
            System.out.printf("Correct answer %s for %s. %d/%d\n", String.valueOf(studentAnswer), string, value, value);
        } else {
            System.out.printf("Incorrect answer %s for %s. %d/%d\n", String.valueOf(studentAnswer), string, 0, value);
            System.out.printf("--- Correct answer was %s\n", String.valueOf(correctAnswer));
        }

    }
    
}
