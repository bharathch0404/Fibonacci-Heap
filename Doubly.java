class Dnode{
	Dnode p;
	Dlist child;
	int data,degree;
	boolean mark;
	public Dnode next,prev;
	Dnode(int data){
		this.data = data;
	}
}
class Dlist{
	public Dnode start=null;
	public Dnode end = null;
	
	int size = 0;
	
	public void insert(Dnode nptr)
	{
		
		
		if(start == null)
		{
			nptr.next= nptr;
			nptr.prev = nptr;
			start = nptr;
			end = start; 
		}
		else{
			Dnode x = start.next;
			 x.prev= nptr;
			 nptr.next=x;
			 nptr.prev=start;
			 start.next = nptr;
		}
		size ++;
	}
	
	public Dnode Delete(Dnode n){
		
		if(size==1 || size==0){
			start = null;
			end=null;
			size=0;
			return n;
		}
		Dnode x = n.next;
		Dnode y = n.prev;
		
		x.prev=y;
		y.next=x;
		
		start = x;
		
		size--;
		return n;
		
		
		
	}
	
	public void display()
	{
		//System.out.print("\nCircular Doubly Linked List = ");
        Dnode ptr = start;
        if (size == 0) 
        {
           // System.out.print("empty\n");
			System.out.print(" ~~~ ");
			return;
        }
        if (start.next == start) 
        {
            System.out.print(start.data+ "<->"+ptr.data+ "");
            return;
        }
        System.out.print(start.data+ "<->");
        ptr = start.next;
       	while(ptr.next != start)
        {
            System.out.print(ptr.data+ "<->");
            ptr = ptr.next;
        }
        System.out.print(ptr.data+ "<->");
		//System.out.print(ptr.data+ "\n");
        ptr = ptr.next;
        System.out.print(ptr.data);
        
        
	}
	
	
	
	
	
	
}
