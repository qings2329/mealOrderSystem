package practise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class JDKTest {
	
	
	
	public static void main(String args[]){
		
		
		HashMap<String, String> hmap = new HashMap<String, String>(10);
		
		Hashtable<String, String> ht = new Hashtable<String, String>(10);
		
		HashSet<String> hset = new HashSet<String>();
		
		ConcurrentHashMap<String, String> cmap = new ConcurrentHashMap<String, String>();
		
		
		//字符对象
		Character charObject = new Character('阿');
		
		
		List<Object> alist = new ArrayList<Object>();
		
		//Map中不能用基本类型?
		//Map<String, int> x = new HashMap<String, int>();
		
		//数组的初始化
		int[] x阿 = new int[]{0};
		int[] xb = new int[5];
		
		
		
		
		//Integer 对象不能修改?
		Integer x = new Integer(1);
		
		
	}
	
	
}
