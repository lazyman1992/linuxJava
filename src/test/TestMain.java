package test;
import java.util.*;
public class TestMain {
	
	public static class Node{
		public int value;
		public Node left;
		public Node right;
		
		public Node(int data){
			value=data;
		}
	}
	
	public static void perOrder(Node head){
		if(head==null)
			return;
		System.out.print(head.value+" ");
		if(head.left!=null)
			perOrder(head.left);
		if(head.right!=null)
			perOrder(head.right);
	}
	
	public static int maxLength(int[] arr,int k){
		if(arr==null||arr.length==0)
			return 0;
		HashMap<Integer,Integer> map=new HashMap<Integer,Integer>();
		map.put(0, -1);
		int len=0;
		int sum=0;
		for(int i=0;i<arr.length;i++){
			sum+=arr[i];
			if(map.containsKey(sum-k)){
				len=Math.max(i-map.get(sum-k), len);
			}
			if(!map.containsKey(sum)){
				map.put(sum, i);
			}
		}
		
		return len;
		
	}
	
	public static int getMaxLength(Node head,int sum){
		HashMap<Integer,Integer> sumMap=new HashMap<Integer,Integer>();
		sumMap.put(0, 0);
		return preOrder(head,sum,0,1,0,sumMap);
		
		
	}
	
	public static int preOrder(Node head,int sum,int preSum,int level,int maxLen,HashMap<Integer,Integer> sumMap){
		if(head==null)
			return maxLen;
		int curSum=preSum+head.value;
		if(!sumMap.containsKey(curSum))
			sumMap.put(curSum, level);
		if(sumMap.containsKey(curSum-sum))
			maxLen=Math.max(level-sumMap.get(curSum-sum), maxLen);
		maxLen=preOrder(head.left,sum,curSum,level+1,maxLen,sumMap);
		maxLen=preOrder(head.right,sum,curSum,level+1,maxLen,sumMap);
		if(level==sumMap.get(curSum))
			sumMap.remove(curSum);
		return maxLen;
			

	}
	
	
	public static void main(String[] args){
		Node head=new Node(-3);
		head.left=new Node(3);
		head.right=new Node(-9);
		head.left.left=new Node(1);
		head.left.right=new Node(0);
		head.left.right.left=new Node(1);
		head.left.right.right=new Node(6);
		head.right=new Node(-9);
		head.right.left=new Node(2);
		head.right.right=new Node(1);
		
		
		perOrder(head);
		System.out.println();
		System.out.print(getMaxLength(head,6));
	}

}
