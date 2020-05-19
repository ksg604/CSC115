public class NodeApp {

public static void main(String[] args) {
	Node<String> node = new Node<>("5",null);
	Node<String> head = new Node<>("4", node);
	
	System.out.println(head.getData() + head.link.getData());


}


}