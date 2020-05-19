/**
 * Programmer note: Use this space for the file header
 */

/**
 * Programmer note: Use this space to describe the class Lesson.
 * You may use the specification description as a guide.
 */
public class Lesson {

	/*
	 * Programmer note:
	 * the following static final array is a nice lookup array to
	 * match the level numbers to the level names.
	 * Note that the index of a name in the array is exactly the
	 * the level number for that name.
	 * Because this particular lookup array is immutable and
	 * there only needs to be one copy for any number of Lesson objects,
	 * we use the 'final' and 'static'.
	 */
	private static final String[] levelNames = 
		{"Beginner", "Novice", "Snowplower", "Intermediate", "Advanced"};
	// Programmer note: Do not alter the following instance variables.
	private int lessonLevel; // level of the Lesson, not necessarily all the skiers
	private String lessonName; // One of the names in levelNames array above.
	private SkierList students; // The list of skiers registered for this lesson.

/*
 * Programmer note: Each of the methods below are not complete and
 * need to be implemented by you.
 * Make sure you provide method header comments and provide
 * the implementation code.
 * Make sure you test regularly for compilation and errors.
 * It is recommended that you reference teh completed main method,
 * where each of your methods is tested;
 * follow that ordering so you can monitor your progress.
 */
 
   //creates a lesson for a given level
	public Lesson(int level) {
		students = new SkierList();
		if (level >= 0 && level <= 4) {
			lessonLevel = level;
			lessonName = levelNames[lessonLevel];
		} else {
			lessonLevel = 0;
			lessonName = levelNames[lessonLevel];
		}
	}
   
   //creates a lesson for a given level and populates that lesson with skiers
	public Lesson(int level, SkierList students) {
 		if (level >= 0 && level <= 4) {
			lessonLevel = level;
			this.students = students;
			lessonName = levelNames[lessonLevel];

		} else {
			lessonLevel = 0;
			this.students = students;
			lessonName = levelNames[lessonLevel];
		}
	}

   //sets the lesson name based on the number of students.
	public void setLessonLevel(int level) {
   
		if (level >= 0 && level <= 4){
			lessonName = levelNames[level];
			lessonLevel = level;
		}
		
	}

   //returns the group name of the lesson
	public String getName() {
		// Programmer note: The return statement is only a placeholder.
		return lessonName;
	}

	public int numStudents() {
		// Programmer note: The return statement is only a placeholder.
      
		return students.size();
	}

	public void addSkier(Skier skier) {
		students.add(skier);
		skier.setLevel(lessonLevel);
	}

	public void removeSkier(Skier skier) {
		if (students.findSkier(skier) != -1) {
			students.remove(students.findSkier(skier));
		}
	}

	public boolean isRegistered(Skier skier) {
		// Programmer note: The return statement is only a placeholder.
			if (students.findSkier(skier) != -1) {
			return true;
		} else {
			return false;
		}
	}

	public String toString() {
		// Programmer note: The return statement is only a placeholder.
		String a = "";
		for(int k=0;k<students.size();k++){
			a+= students.get(k).toString() +"\n";
		}
		return lessonName + " group:\n"+a ;
	}

	/**
	 * Used as a test harness for the class.
	 * @param args Not used.
	 */
	public static void main(String[] args) {
		System.out.println("Testing the Lesson class.");
		Lesson lesson = null;
		String[] group = {"Daffy Duck", "Bugs Bunny", "Betty Boop",
			"Roger Rabbit", "Han Solo", "Chewbacca"};
		try {
			lesson = new Lesson(2);
		} catch (Exception e) {
			System.out.println("Failed to construct a Lesson object.");
			e.printStackTrace();
			return;
		}
		if (!lesson.getName().equals("Snowplower")) {
			System.out.println("Failed at test one.");
			return;
		}
		if (lesson.numStudents() != 0) {
			System.out.println("Failed at test two.");
			return;
		}
		lesson.setLessonLevel(3);
		if (!lesson.getName().equals("Intermediate")) {
			System.out.println("Failed at test three.");
			return;
		}
		for (int i=0; i<group.length; i++) {
			lesson.addSkier(new Skier(group[i]));
		}
		if (lesson.numStudents() != 6) {
			System.out.println("Failed at test four.");
			return;
		}
		System.out.print("Checking the toString: Should see a list of ");
		System.out.println("6 skiers, all with level 3");
		System.out.println(lesson);
		
		System.out.println("Checking constructor that takes a list.");
		int[] levels = {0,3,2};
		int levelIndex = 0;
		SkierList list = new SkierList();
		for (int i=0; i<group.length; i++) {
			list.add(new Skier(group[i],levels[levelIndex]));
			levelIndex = (levelIndex+1)%levels.length;
		}
		try {
			lesson = new Lesson(4,list);
		} catch (Exception e) {
			System.out.println("Constructor not working.");
			e.printStackTrace();
			return;
		}
		if (lesson.numStudents() != 6) {
			System.out.println("Failed at test five.");
			return;
		}
		for (int i=0; i<list.size(); i++) {
			if (!lesson.isRegistered(list.get(i))) {
				System.out.println("Failed at test six.");
				return;
			}
		}
		Skier removed = list.get(3);
		lesson.removeSkier(removed);
		if (lesson.isRegistered(removed)) {
			System.out.println("Failed at test seven.");
			return;
		}
		if (lesson.numStudents() != 5) {
			System.out.println("Failed at test eight.");
			return;
		}
		System.out.print("The following printout should consist of 5 ");
		System.out.println("skiers with varying levels:");
		System.out.println(lesson);
		System.out.println("Testing completed.");
	}
}
