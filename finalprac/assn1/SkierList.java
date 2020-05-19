/**
 * Progammer note: Use this space for the file header.
 */

/**
 * Progarmmer note: Use this space to describe the class SkierList.
 * You may use the specification description as a guide.
 */
public class SkierList {
// Programmer note: Do not alter the following instance variables.
	private Skier[] skiers; // array storage for skiers
	private int count; // the number of skiers in the list
	// the following is the initial size of the empty skiers array.
	private static final int INITIAL_CAP = 3;

/*
 * Programmer note: Each of the methods below are not complete and
 * need to be implemented by you.
 * Make sure you provide method header comments and provide
 * the implementation code.
 * Make sure you test regularly for compilation and errors.
 * It is recommended that you reference the completed main method,
 * where each of your methods is tested;
 * follow that ordering so you can monitor your progress.
 */
	public SkierList() {
		// Programmer note: Initialize the skiers array to hold INITIAL_CAP Skier objects.
		skiers = new Skier[INITIAL_CAP];
	}

	public int size() {
		// Programmer note: The following is placeholder.
		return count;
	}

	public Skier get(int index) {	
		// Programmer note: The following is a placeholder.
		/*if (index < 0 || index > skiers.length -1) {
			return null;
		}*/
		return skiers[index];
	}

	public void remove(int index) {
		/*if (index < 0 || index > skiers.length) {
			return;
		}*/
		Skier[] temp = skiers;
		temp[index] = null;
		for(int i = index; i < temp.length - 1; i++) {
			temp[i] = skiers[i+1];
		}
		skiers = temp;
		count--;
	}

	public void add(Skier skier) {
	/*
	 * Programmer note: If the skiers array is full, it needs to be
	 * replaced with a larger array. It has been proven by algorithm efficiency
	 * experts that the best resize process is to double the size of the existing
	 * array.
	 * In this assignment, you do not need to similarly reduce the size of the array
	 * when skiers are removed.
	 */
	 
		if(skiers.length == count) {
			doubleArray();
		}
		
		skiers[count] = skier;
		count++;
		
	 
	}
	
	private void doubleArray() {
		Skier[] newList = new Skier[skiers.length*2];
		for (int i = 0; i < skiers.length; i++) {
			skiers[i] = newList[i];
		}
		skiers = newList;
	}

	public int findSkier(Skier skier) {
		// Programmer note: The following is a placeholder.
		/*int i = 0;
		for (i = 0; i > skiers.length; i++) {

			if(skiers[i] == null) {
				System.out.println("yo");
				System.out.println(i);
				return -1;
			}
			if (skiers[i].equals(skier)) {
				System.out.println("success");
				break;
			}
		}
		return i;*/
				int i;
		for(i=0; i > skiers.length; i++) {
			if (skiers[i] == null) {
				return -1;
			}
			if(skiers[i].equals(skier)) {
				break;
			}
		}
		
		return i;
			
	}
	
	
	

	/**
	 * Used primarily as a test harness for the class.
	 * @param args Not used.
 	 */
	public static void main(String[] args) {
		System.out.println("Testing the SkierList class.");
		SkierList list = null;
		try {
			list = new SkierList();
		} catch (Exception e) {
			System.out.println("Constructor not working.");
			e.printStackTrace();
			return;
		}
		// Add some skiers.
		Skier s1 = new Skier("Daffy Duck", 0);
		list.add(s1);
		if (list.size() != 1) {
			System.out.println("Failed at test one.");
			return;
		}
		if (!list.get(0).equals(s1)) {
			System.out.println("Failed at test two.");
			System.out.println("The first skier in the list needs to be in index position 0");
			return;
		}
		if (list.findSkier(s1) == -1) {
			System.out.println("Failed at test three.");
			return;
		}
		Skier s2 = new Skier("Bugs Bunny", 4);
		list.add(s2);
		if (s2.getLevel() != 4) {
			System.out.println("Failed at test four.");
			return;
		}
		if (list.size() != 2) {
			System.out.println("Failed at test five.");
			return;
		}
		Skier tmp1 = list.get(0);
		Skier remaining;
		if (tmp1.equals(s1)) {
			remaining = s2;
		} else {
			remaining = s1;
		}
		list.remove(0);
		if (list.findSkier(remaining) == -1) {
			System.out.println("Failed at test six.");
			return;
		}
		if (list.size() != 1) {
			System.out.println("Failed at test seven.");
			return;
		}
		if (!list.get(0).equals(remaining)) {
			System.out.println("Failed at test eight.");
			return;
		}
		list.remove(0);
		if (list.size() != 0) {
			System.out.println("Failed at test nine.");
			return;
		}
		System.out.println("Testing for expansion.");
		// note that the array has to expand in this test.
		// Creating an initial array with a length >= max is a failure.
		String prefix = "Skier";
		int max = 1000;
		try {
			for (int i=0; i<max; i++) {
				list.add(new Skier(prefix+i));
			}
		} catch (Exception e) {
			System.out.println("Failed at test 10.");
			e.printStackTrace();
			return;
		}
		for (int i=max-1; i>=0; i--) {
			if (list.findSkier(new Skier(prefix+i)) == -1) {
				System.out.println("Failed at test 11.");
				System.out.println("Unable to find skier: "+(prefix+i));
				return;
			}
		}
		System.out.println("All tests passed");
	}
}