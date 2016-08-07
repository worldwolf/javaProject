package com.fid.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class CombinationUtil {
	Vector<String[]> v = new Vector<String[]>();
	 Vector<String> vv = new Vector<String>(); 
	 public List<String> getCombination(List<String> array, int x) {
	  List<String> result = new ArrayList<String>();
	  pro(array, 0, x);
	  for(int i=0;i<v.size();i++){
		  String[] r=v.get(i);
		  result.add(String.join(",", r));
	  }
	  return result;
	 } 
	 
	 
	 public void pro(List<String> a, int start, int n) {
	  if (n == 0) {
		  String[] r = new String[vv.size()];
	   for (int i = 0; i < r.length; i++) {
	    r[i] = vv.get(i);
	   }
	   v.add(r);
	   return;
	  }
	  if (start >= a.size()) {
	   return;
	  }
	  for (int i = start; i < a.size(); i++) {
	   vv.add(a.get(i));
	   pro(a, i + 1, n - 1);
	   vv.remove(vv.size()-1);
	  }
	 }
	 
	 public List<String> getAllCombinations(List<String> strs){
		 if(strs == null) return Collections.emptyList();
		 List<String> result = new ArrayList<String>();
		 for (int i = 0; i < strs.size(); i++) {
			result.addAll(this.getCombination(strs, i+1));
		}
		 return result;
		 
		 
	 }
}
