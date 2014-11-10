/**
* You are given a string, S, and a list of words, L, that are all of the same length. 
* Find all starting indices of substring(s) in S that is a concatenation of each word in L exactly once 
* and without any intervening characters. 
* @author  Yan Wang
*/

public class Solution {
    public List<Integer> findSubstring(String S, String[] L) {
        List<Integer> res = new ArrayList<Integer>();
        
        if (S == null || L == null || S.length() == 0 || L.length == 0) {
            return res;
        }
        
        int lens = S.length();
        int singleLen = L[0].length();
        int lenl = L.length;
        int totalLen = singleLen * lenl;
        
        if (totalLen > lens) {
            return res;
        }
        
        Map<String, Integer> map = new HashMap<String, Integer>();
        
        for (String str : L) {
            if (map.containsKey(str)) {
                map.put(str, map.get(str) + 1);
            }
            else {
                map.put(str, 1);
            }
        }
        
        for (int i = 0;i <= lens - totalLen;i++) {
            if (findSubstringFromStart(i, S, L, map)) {
                res.add(i);
            }
        }
        
        return res;
    }
    
    private boolean findSubstringFromStart(int start, String S, String[] L, Map<String, Integer> originalMap) {
        int i = start;
        int step = L[0].length();
        int end = start + L.length * step - 1;
        Map<String, Integer> map = new HashMap<String, Integer>(originalMap);
        
        while (i <= end) {
            
            String curStr = S.substring(i, i + step);
            
            if (map.containsKey(curStr) && map.get(curStr) > 0) {
                map.put(curStr, map.get(curStr) - 1);
                i += step;
            }
            else {
                return false;
            }
        }
        
        return true;
    }
}
