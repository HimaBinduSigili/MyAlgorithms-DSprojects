package datastructures;
import java.lang.Math;
class BSNode{
	int data;
	int count;
	BSNode left,right;
	public BSNode(int data) {
		this.data = data;
		this.count = 1;
		this.left = this.right = null;
	}
}
class RBNode{
	int data;
	int count;
	int color;
	RBNode left,right;
	public RBNode(int data) {
		this.data = data;
		this.count = 1;
		this.color = 1;
		this.left = this.right = null;
	}
}
class BStree {
	BSNode ArrayToBst(int num[]) {
		BSNode root = new BSNode(num[0]);
		for(int i=1;i<num.length;i++) {
			BSNode next = new BSNode(num[i]);
			BSNode p = root;
			while(p!=next) {
			if(next.data<p.data) {
				if(p.left==null)
					p.left=next;
				else
					p = p.left;
			}else if(next.data==p.data){
				p.count=p.count+1;
				p=next;
			}else {
				if(p.right==null)
					p.right=next;
				else
					p = p.right;
			}
			}
		}
		return root;
	}
	public int height(BSNode rt) {
		return rt==null? 0: Math.max(height(rt.left), height(rt.right))+1;	
	}
	public int avgkeycom(BSNode rt,int depth) {
		if(rt==null) {
			return 0;
		}
		int left =  avgkeycom(rt.left,depth+1);
		int right =  avgkeycom(rt.right,depth+1);
		return (left+right+rt.count*depth);	
	}
}

class RBtree
{
	private RBNode root =null;
	static boolean isRed(RBNode n)
	{
		return n!=null && n.color == 1;
	}
	RBNode ArrayToRBT(int num[]) {
		for(int i=0;i<num.length-1;i++) {
				root=RBtree.insert(root, num[i]);	
				root.color=0;
		}
		return root;
	}
			
	static RBNode insert(RBNode r, int k) 
	{
		if (r==null) 
		{
			r = new RBNode(k);
		}
		else if(r.data == k)
		{
			r.count++;
		}
		else
		{
			if (isRed(r.left))
			{
				if (isRed(r.right))
				{
					r.color= 1;
					r.left.color = 0;
					r.right.color = 0;
				}
				else
				{
					if (isRed(r.left.left))
					{
						r = rotateRight(r);
					}
					else if (isRed(r.left.right))
					{
						r.left = rotateLeft(r.left);
						r = rotateRight(r);
					}
				}
			}
			if (isRed(r.right))
			{
				
					if (isRed(r.right.left))
					{
						r.right = rotateRight(r.right);
						r = rotateLeft(r);
					}
					else if (isRed(r.right.right))
					{
						r = rotateLeft(r);
					}
				
			}
			if(r.data < k)
				r.right = insert(r.right, k);
			else
				r.left = insert(r.left, k);						
		}
		return r;
	}
	static RBNode rotateLeft(RBNode r)
	{
		RBNode child = r.right;

		r.right = child.left;
		child.left = r;

		r.color= 1;
		child.color = 0;

		return child;
	}
	static RBNode rotateRight(RBNode r)
	{
		RBNode child = r.left;

		r.left = child.right;
		child.right = r;

		r.color= 1;
		child.color = 0;

		return child;
	}
	public int height(RBNode rt) {
			return rt==null? 0: Math.max(height(rt.left), height(rt.right))+1;	
		}
	public int avgkeycom(RBNode rt,int depth) {
			if(rt==null) {
				return 0;
			}
			int left =  avgkeycom(rt.left,depth+1);
			int right =  avgkeycom(rt.right,depth+1);
			return (left+right+rt.count*depth);	
		}
}
public class CompareBSTnRBT {
public static void main(String[] args) {
	BStree bst1 = new BStree();
	BStree bst2 = new BStree();
	int seqsize =500;
	int j=0;
	int numseq1[] = new int[seqsize];
	for(int i=0;i<seqsize;i++) {
		numseq1[i]= (int) (Math.random()*100+1);
	}
	int numseq2[] = new int[seqsize];
	for(int i=1;i<100;i=i+2) {
		numseq2[j]= i;
		j++;
	}
	for(int i=2;i<=100;i=i+2) {
		numseq2[j]= i;
		j++;
	}
	for(int i=0;i<400;i++) {
		numseq2[j]= (int) (Math.random()*100+1);
		j++;
	}
	RBtree rbt1 = new RBtree();
	RBtree rbt2 = new RBtree();
	
    BSNode root1 =bst1.ArrayToBst(numseq1);
    BSNode root2 =bst2.ArrayToBst(numseq2);
    RBNode header1 = rbt1.ArrayToRBT(numseq1);
    RBNode header2 = rbt2.ArrayToRBT(numseq2);

    System.out.println("Height of constructed BST1 "+bst1.height(root1));
    System.out.println("Height of constructed BST2 "+bst2.height(root2));
    System.out.println("AVG COMP of constructed BST1 "+(float)bst1.avgkeycom(root1, 0)/seqsize);
    System.out.println("AVG COMP of constructed BST2 "+(float)bst2.avgkeycom(root2, 0)/seqsize);
    
    System.out.println("Height of constructed RBT1 "+rbt1.height(header1));
    System.out.println("Height of constructed RBT2 "+rbt2.height(header2));
    System.out.println("AVG COMP of constructed RBT1 "+(float)rbt1.avgkeycom(header1, 0)/seqsize);
    System.out.println("AVG COMP of constructed RBT2 "+(float)rbt2.avgkeycom(header2, 0)/seqsize);
    
}
}

