public class Practice {
	
	private int sum;
	
	public int sum(int[] List) {
		for (int i = 0; i < List.length; i++) {
			this.sum = this.sum + List[i];
		}
		return this.sum;
	}
	
	public static void main(String[] args) {
		
	int x = (0/0) + 3;
	System.out.println("The output is: "+ x);
		
	}


}