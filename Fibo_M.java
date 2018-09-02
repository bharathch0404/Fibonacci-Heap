import java.util.*;
class Fibo_M{
	
	static HashMap<String,Fibo_M> hmap = new HashMap<String,Fibo_M>();
	static HashMap<Fibo_M,HashMap<Integer,LinkedList<Dnode>>> occ=new HashMap<Fibo_M,HashMap<Integer,LinkedList<Dnode>>>();
	Dlist rootList;
	Dnode  min;
	int n = 0;
	
	Fibo_M(){
		rootList  = new Dlist();
	}
	
	public static void createHeap(String Name){
	
		if(hmap.get(Name)==null)
		{
			hmap.put(Name,new Fibo_M());
			occ.put(hmap.get(Name),null);
			System.out.println("Heap "+Name+" has been created!");
		}
		else
		{
			System.out.println("Heap "+Name+" already exists!");
		}
	
	}
	
	public void insert(String Name,int data){
		Fibo_M H = hmap.get(Name);
		HashMap<Integer,LinkedList<Dnode>> t;
		if(occ.get(H)!=null)
			t=occ.get(H);
		else
			t=new HashMap<Integer,LinkedList<Dnode>>();
		Dnode x = new Dnode(data);
		LinkedList<Dnode> l;
		if(t.get(data)!=null)
			l=t.get(x);
		else
			l=new LinkedList<Dnode>();
		if(H!=null)
		{
			x.degree = 0;
			x.p=null;
			x.child = null;
			x.mark=false;
		
			if(H.min==null){
				H.rootList.insert(x);
				H.min=x;
			}
			else{
			
				H.rootList.insert(x);
				if(x.data < H.min.data){
					H.min=x;
				}
			}
			H.n = H.n+1;
			if(l==null)
			{
				l=new LinkedList<Dnode>();
				l.add(x);
			}
			else
				l.add(x);
			t.put(data,l);
			occ.put(H,t);
		}
		else
			System.out.println("Please create heap "+Name+" first!");
	}
	
	public Dnode Emin(String Name){
		Fibo_M H = hmap.get(Name);
		if(H.n==0)
		{
			System.out.println("Heap "+Name+" has no elements!");
			return null;
		}
		if(H!=null)
		{
			Dnode z = H.min;
		
			if(z != null){
		
				if(z.child==null){
					
					H.rootList.Delete(z);
				}
				else{
					int o=z.child.size;
					for(int i=0;i<o ;i++ ){
					//System.out.println("Error");
					//H.rootList.display();
					
						H.rootList.insert(z.child.Delete(z.child.start));
					//z.child.start.p=null;
					//z.child.display();
					}
					H.rootList.Delete(z);
				}
			
				if(z==z.next){
					H.rootList.display();
					//System.out.println("null Consolidating " + z.data );
					H.min =null;
			
				}
				else{
					//System.out.println("Consolidating");
					consolidate(Name);
				}
				H.n=H.n-1;
				System.out.println(z.data);
				HashMap<Integer,LinkedList<Dnode>> t=occ.get(H);
				LinkedList<Dnode> l=t.get(z.data);
				l.remove();
			}
			return z;
		}
		else	
		{	
			System.out.println("Please create heap "+Name+" first!");
			System.out.println("Returning junk value!");
			return new Dnode(12345678);
		}
	}
	
	public void consolidate(String Name){
	
		Fibo_M H = hmap.get(Name);
		
		Dnode [] A = new Dnode[H.n];
		
		for(int i=0;i<A.length;i++){
			A[i] = null;
		}
		Dnode x,y;
		x=H.rootList.start;
		int t = H.rootList.size;
		for(int i = 0;i<t;i++){
			
			
			int d = x.degree;
			
			while(d < A.length && A[d] != null){
				y=A[d];
				if(x.data > y.data){
					Dnode tmp = x;
					x=y;
					y=tmp;
				}
				Link(Name,y,x);
				A[d]=null;
				d=d+1;
			}
			//System.out.println( "d is " + d + " X is " + x.data + " A len" + t);
			A[d] = x;
			x=x.next;
			
		}
		
		H.min = null;
		
		for(int i = 0;i<A.length;i++){
			if(A[i] != null){
				if(H.min == null){
					H.min=A[i];
				}
				else{
					
					if(A[i].data < H.min.data){
						H.min = A[i];
					}
				}
			}
		}
		
	}
	
	public void  Link(String Name,Dnode y,Dnode x){
		Fibo_M H = hmap.get(Name);
		//H.rootList.display();
		//System.out.println( H.rootList.start.data + " <= Start");
		Dnode l = H.rootList.Delete(y);
		//System.out.println( H.rootList.start.data + " <= Start");
		if(x.child == null)
			x.child = new Dlist();
		
		//System.out.println("X is small that is " + x.data + "but y is "+y.data);
		y.prev=null;
		y.next=null;
		x.child.insert(y);
		x.degree += 1;
		y.mark = false;
		y.p=x;
		//System.out.println("Child");
		//x.child.display();
		//System.out.println("Root");
		//H.rootList.display();
			
	}
	public Dnode find_min(String Name)
	{
		Fibo_M H = hmap.get(Name);
		return H.min;
	}
	public void decrease_key(String Name,int data,int delta)
	{
		Fibo_M H = hmap.get(Name);
		if(H!=null)
		{
			HashMap<Integer,LinkedList<Dnode>> t=occ.get(H);
			if(t.get(data)==null)
			{
				System.out.println("Key with value "+data+ "doesn't exist in the heap "+Name);
				return;
			}
			LinkedList<Dnode> l=t.get(data);
			Dnode x=l.remove();
			//Dnode x = find(H.rootList,data);	
			if(x==null)
			{
				System.out.println("Key doesn't exist in the heap "+Name);
				return;
			}
			else
			{
				if(delta>x.data)
				{
					System.out.println("New key is greater than current key!");
				}
				else
				{
					x.data=delta;
					Dnode y=x.p;
					if(y!=null&&x.data<y.data)
					{
						cut(H,x,y);
						cascading_cut(H,y);
					}
					if(x.data<H.min.data)
						H.min=x;
					if(t.get(delta)!=null)
					{
						l=t.get(delta);
						l.add(x);
						t.put(delta,l);
					}
					else
					{
						l=new LinkedList<Dnode>();
						l.add(x);
						t.put(delta,l);
					}
				}		
			}
		}
		else
			System.out.println("Please create heap "+Name+" first!");
	}
	public void cut(Fibo_M H,Dnode x,Dnode y)
	{
		x=(y.child).Delete(x);
		y.degree--;
		H.rootList.insert(x);
		x.p=null;
		x.mark=false;
	}
	public void cascading_cut(Fibo_M H,Dnode y)
	{
		Dnode z=y.p;
		if(z!=null)
		{
			if(y.mark==false)
				y.mark=true;
			else
			{
				cut(H,y,z);
				cascading_cut(H,z);
			}
		}
	}
	public void display2(String Name)
	{
		System.out.println("Displaying heap "+Name);
		String s="";
		Fibo_M H = hmap.get(Name);
		if(H.n==0)
		{
			System.out.println("Heap "+Name+" has no elements!");
			return;
		}
		if(H!=null)
		{
			Queue<KeyValue> next=new LinkedList<KeyValue>();
			int level=0;
			KeyValue t1=new KeyValue();
			t1.key=H.rootList;
			t1.value=0;
			next.add(t1);
			while(!next.isEmpty())
			{
				
				KeyValue t=next.remove();
				Dlist cur=t.key;
				Dnode temp=cur.start;
				System.out.println(temp.data);
				if(level!=t.value)
				{
					level=t.value;
					System.out.println();
				}
				else
				{
					System.out.print(" ");
				}
				if(temp.child!=null)
				{
						KeyValue z=new KeyValue();
						z.key=temp.child;
						z.value=t.value+1;
						next.add(z);
				}
				System.out.print(temp.data+",");
				temp=temp.next;
				while(temp!=cur.start)
				{
					if(temp.next!=cur.start)
						System.out.print(temp.data+",");
					else
					{
						if(temp.p!=null)
							System.out.print(temp.data+":"+temp.p.data);
					}
					if(temp.child!=null)
					{
						KeyValue z=new KeyValue();
						z.key=temp.child;
						z.value=t.value+1;
						next.add(z);
					}
					temp=temp.next;
				}
			}
		}
	}
	public void display(String Name)
	{
		System.out.println("Displaying heap "+Name);
		String s="";
		Fibo_M H = hmap.get(Name);
		if(H!=null&&H.n==0)
		{
			System.out.println("Heap "+Name+" has no elements!");
			return;
		}
		if(H!=null)
		{
			int count=H.n;
			/*Queue<KeyValue> next=new LinkedList<KeyValue>();
			int level=0;
			KeyValue t=new KeyValue();
			t.key=H.rootList;
			t.value=0;
			next.add(t);*/
			int level=0;
			Queue<KeyValue> next=new LinkedList<KeyValue>();
			next.add(new KeyValue(H.rootList,0));
			while(!next.isEmpty()&&count>0)
			{
				//System.out.print(" "+count+" ");
				KeyValue t=next.remove();
				Dlist cur=null;
				Dnode temp=null;
				int curlevel=-1;
				if(t.key!=null)
				{
					cur=t.key;
					//System.out.print(t.value);
					curlevel=t.value;
					//System.out.println(" "+curlevel+":"+level);
					if(curlevel!=level)
					{
						//System.out.print("1");
						System.out.println();
						//cur.display();
						//System.out.print("2");
						//System.out.print("\n");
						//System.out.print("3");
						//cur.display();
						//System.out.print("4");
						//System.out.print(" "+count+" ");
						level=curlevel;
					}
					cur.display();
					temp=cur.start;
					if(cur!=null)
					{
						//System.out.println(count);
						count-=cur.size;
						//System.out.println(count);
					}
				}
				else
				{
					new Dlist().display();
				}
				if(next.peek()!=null)
				{
					//System.out.println(next.peek().value);
				}
				if(temp!=null&&temp.child!=null)
				{
					next.add(new KeyValue(temp.child,level+1));
					//System.out.println(next.peek().key.start.data);
					temp=temp.next;
					//count--;
				}
				else
				{
					
					if(temp!=null)
					{
						//count--;
						next.add(new KeyValue(null,level+1));
						temp=temp.next;
					}
				}
				
				while(temp!=null&&temp!=cur.start)
				{
					if(temp.child!=null)
					{
						//count--;
						next.add(new KeyValue(temp.child,level+1));
					}
					else
					{
						next.add(new KeyValue(null,level+1));
					}
					temp=temp.next;
				}
			}
			System.out.println();
		}
		else
			System.out.println("Heap "+Name+" doesn't exist!");
		
				/*KeyValue t2=next.remove();
				Dlist cur=t2.key;
				if(level!=t2.value)
				{
					s+="\n";
					level=t2.value;
				}
				Dnode temp=cur.start;
				System.out.println(temp.next);
				System.out.println(cur.start);
				if(temp.next!=cur.start)
				{
					if(level==0)
						s=s+temp.data+",";
					else
						s+=(temp.data)+":"+temp.p.data+",";		
				}
				else
				{
					if(level==0)
						s=s+temp.data;
					else
						s+=(temp.data)+":"+temp.p.data;
					
				}
				if(temp.child!=null)
				{
					t.key=temp.child;
					t.value=level+1;
					next.add(t);
				}
				temp=temp.next;
				while(temp!=cur.start)
				{
					if(temp!=cur.start.prev)
					{
						if(level==0)
							s=s+temp.data+",";
						else
							s+=(temp.data)+":"+temp.p.data+",";
					}
					else
					{
						if(level==0)
							s=s+temp.data;
						else
							s+=(temp.data)+":"+temp.p.data;
					
					}
					if(temp.child!=null)
					{
						t.key=temp.child;
						t.value=level+1;
						next.add(t);
					}
					temp=temp.next;
				}
				s+="  ";
			}
			System.out.println(s);
		}*/
		//else
		//	System.out.println("Heap "+Name+" doesn't exist!");
		
	}
	public HashMap<Integer,LinkedList<Dnode>> addList(Dlist list,HashMap<Integer,LinkedList<Dnode>> t)
	{
		Queue<Dlist> next=new LinkedList<Dlist>();
		Dnode cur=list.start;
		if(cur.child!=null)
			next.add(cur.child);
		LinkedList<Dnode> l;
		if(t.get(cur.data)!=null)
		{
			l=t.get(cur.data);
			l.add(cur);
			t.put(cur.data,l);
		}
		else
		{
			l=new LinkedList<Dnode>();
			l.add(cur);
			t.put(cur.data,l);
		}
		cur=cur.next;
		while(cur!=list.start)
		{
			if(cur.child!=null)
				next.add(cur.child);
			if(t.get(cur.data)!=null)
			{
				l=t.get(cur.data);
				l.add(cur);
				t.put(cur.data,l);
			}
			else
			{
				l=new LinkedList<Dnode>();
				l.add(cur);
				t.put(cur.data,l);
			}
		}
		while(!next.isEmpty())
		{
			t= addList(next.remove(),t);
		}
		return t;
	}
	public Dnode find(Dlist list,int data)
	{
		Queue<Dlist> next=new LinkedList<Dlist>();
		Dnode cur=list.start;
		//System.out.println(cur.data+":"+data);
		if(cur.data==data)
		{
			return cur;
		}
		else
		{
			if(cur.child!=null&&data>cur.data)
				next.add(cur.child);
			cur=cur.next;
		}
		while(cur!=list.start)
		{
			//System.out.println(cur.data+":"+data);
			if(cur.data==data)
			{
				//System.out.println(cur.data);
				return cur;
			}
			else
			{
				if(cur.child!=null&&data>cur.data)
					next.add(cur.child);
				cur=cur.next;
			}
		}
		while(!next.isEmpty())
		{
			return find(next.remove(),data);
		}
		return null;//new Dnode(-101);
	}
	public void free_heap(String Name)
	{
		if(hmap.get(Name)==null)
		{
			System.out.println("Heap already freed!");
		}
		else
		{
			occ.remove(hmap.get(Name));
			hmap.remove(Name);
			System.out.println("Heap "+Name+" has been freed!");
		}
	}
	public void Merge(String Name1,String Name2,String Name3)
	{
		Fibo_M H1 = hmap.get(Name1);
		Fibo_M H2 = hmap.get(Name2);
		
		boolean exist=true;
		if(H1==null)
		{
			System.out.println("Heap "+Name1+" doesn't exist!");
			exist=false;
		}
		if(H2==null)
		{
			System.out.println("Heap "+Name2+" doesn't exist!");
			exist=false;
		}
		if(!exist)
		{
			return;
		}
		free_heap(Name1);
		HashMap<Integer,LinkedList<Dnode>> t=occ.get(H2);
		LinkedList<Dnode> l;
		int te=H1.n+H2.n;
		while(H1.rootList.start != null){
			Dnode x= H1.rootList.Delete(H1.rootList.start);
			if(x.child!=null)
			{
				//System.out.println("HI");
				t=addList(x.child,t);
				occ.put(H2,t);
			}
			H2.rootList.insert(x);
			if(t.get(x.data)!=null)
			{
				l=t.get(x.data);
				l.add(x);
				t.put(x.data,l);
				occ.put(H2,t);
				//H2.n=H2.n+1;
			}
			else
			{
				l=new LinkedList<Dnode>();
				l.add(x);
				t.put(x.data,l);
				occ.put(H2,t);
				//H2.n=H2.n+1;
			}
		}

		H2.n=te;
		hmap.put(Name3,H2);
		//occ.put(H2,t);
		hmap.remove(Name2);
		System.out.println("Heap "+Name2+" has been freed!");
	}
}
class KeyValue
{
	Dlist key;
	int value;
	KeyValue()
	{
		
	}
	KeyValue(Dlist key,int value)
	{
		this.key=key;
		this.value=value;
	}
}
