import java.util.*;
import java.util.regex.*;
public class Fibo{
	public static void main(String []args){
		Scanner sc=new Scanner(System.in);
		Fibo_M l = new Fibo_M();
		String name;
		int key;
		String op;
		do	
		{
			System.out.println("Options:\n1. Create Heap\n2. Insert\n3. Merge Heap\n"
		+"4. Find Min\n5. Extract Min\n"
		+"6. Decrease Key\n7. Free Heap\n");
			System.out.print("Choose your option: ");
			int c=sc.nextInt();
			System.out.println();
			switch(c)
			{	case 1:
					System.out.print("Enter the name of the heap: ");
					name=sc.next();
					System.out.println();
					l.createHeap(name);
					break;
				case 2:
					System.out.print("Enter the name of the heap: ");
					name=sc.next();
					System.out.println();
					System.out.print("Enter the value of key to be inserted: ");
					key=sc.nextInt();
					System.out.println();
					l.insert(name,key);
					l.display(name);
					break;
				case 3:
					System.out.print("Enter the name of the first heap: ");
					name=sc.next();
					System.out.println();
					System.out.print("Enter the name of the second heap: ");
					String name1=sc.next();
					System.out.println();
					System.out.print("Enter the name of the third heap: ");
					String name2=sc.next();
					System.out.println();
					l.Merge(name,name1,name2);
					l.display(name2);
					//l.display2(name2);
					//l.hmap.get(name2).rootList.start.next.child.display();
					break;
				case 4:
					System.out.print("Enter the name of the heap: ");
					name=sc.next();
					System.out.println();
					System.out.println("Value of the Minimum in the heap "+name+" is "+l.find_min(name).data);
					break;
				case 5:
					System.out.print("Enter the name of the heap: ");
					name=sc.next();
					System.out.println();
					System.out.println("Minimum value in the heap "+name+" is "+l.Emin(name).data+".");
					l.display(name);
					break;
				case 6:
					System.out.print("Enter the name of the heap: ");
					name=sc.next();
					System.out.println();
					System.out.print("Enter the value of key to be replaced: ");
					key=sc.nextInt();
					System.out.println();
					System.out.print("Enter the value of key to be replaced with: ");
					int delta=sc.nextInt();
					System.out.println();
					l.decrease_key(name,key,delta);
					l.display(name);
					//l.display2(name);
					break;
				case 7:
					System.out.print("Enter the name of the heap: ");
					name=sc.next();
					System.out.println();
					l.free_heap(name);
					break;
				default:
					System.out.println("Please choose a valid option");
					break;
			}
			System.out.println("Do you want to continue?");
			op=sc.next();
		}while(Pattern.matches("[Yy][eS]?[sS]?",op));
		/*Fibo_M l = new Fibo_M();
		l.createHeap("x");
		//l.hmap.get("x").rootList.display();
		l.insert("x",10);
		//l.hmap.get("x").rootList.display();
		l.insert("x",98);
		//l.hmap.get("x").rootList.display();
		l.insert("x",-1);
		l.insert("x",0);
		l.insert("x",-91);
		l.insert("x",-100);
		l.insert("x",-6);
		l.insert("x",85);
		l.insert("x",-9);
		l.insert("x",3);
		l.display("x");
		
		//l.hmap.get("x").rootList.display();
		System.out.println(l.Emin("x").data);
		//l.hmap.get("x").rootList.display();
		l.display("x");
		l.decrease_key("x",-1,-2);
		l.display("x");
		l.decrease_key("x",0,-92);
		l.display("x");
		//System.out.println(l.Emin("x").data);
		//l.hmap.get("x").rootList.display();
		//System.out.println(l.Emin("x").data);
		//System.out.println(l.Emin("x").data);
		//System.out.println(l.Emin("x").data);
		//System.out.println(l.Emin("x").data);
		//System.out.println(l.Emin("x").data);
		//System.out.println(l.Emin("x").data);
		//System.out.println(l.Emin("x").data);
		//System.out.println(l.Emin("x").data);
		//System.out.println(l.Emin("x").data);
		/*Dnode x = l.hmap.get("x").rootList.start;
		l.hmap.get("x").rootList.Delete(x);
		x = l.hmap.get("x").rootList.start;
		l.hmap.get("x").rootList.Delete(x);
		l.hmap.get("x").rootList.display();//
		
		l.free_heap("x");
		*/
		
	}
}
