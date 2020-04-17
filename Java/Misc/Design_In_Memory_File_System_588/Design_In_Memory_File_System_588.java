package Design_In_Memory_File_System_588;

import java.util.*;

/* 588. Design In-Memory File System

Design an in-memory file system to simulate the following functions:

   ls: Given a path in string format. If it is a file path, return a list that only contains 
       this file's name. If it is a directory path, return the list of file and directory 
       names in this directory. Your output (file and directory names together) should in lexicographic order.

   mkdir: Given a directory path that does not exist, you should make a new directory according to the 
        path. If the middle directories in the path don't exist either, you should create them as well. 
        This function has void return type.

   addContentToFile: Given a file path and file content in string format. If the file doesn't exist, 
        you need to create that file containing given content. If the file already exists, you need to 
        append given content to original content. This function has void return type.

   readContentFromFile: Given a file path, return its content in string format.
 

Example:

Input: 
["FileSystem","ls","mkdir","addContentToFile","ls","readContentFromFile"]
[[],["/"],["/a/b/c"],["/a/b/c/d","hello"],["/"],["/a/b/c/d"]]

Output:
[null,[],null,null,["a"],"hello"]


Note:

    You can assume all file or directory paths are absolute paths which begin with / and do not end with / 
    except that the path is just "/".
    You can assume that all operations will be passed valid parameters and users will not attempt to retrieve 
    file content or list a directory or file that does not exist.
    You can assume that all directory names and file names only contain lower-case letters, and same names 
    won't exist in the same directory.

*/


/*
 *  Followup: 
 *  	- talk about trade off using TreeMap (O(logN) in put, O(1) in ls) vs HashMap (O(1) in put, O(NlogN) in ls)
 *  	- additional methods: rmdir, rm, mv, cp, etc...
 */
class File { 
	boolean isFile;
	Map<String, File> child;
	String content;
	
	public File() {
		isFile = false;
		// TreeMap is sorted by natural order of the key
//		child = new HashMap<String, File>();
		child = new TreeMap<String, File>();
		content = "";
	}
}	
  
class FileSystem { 
    File root;
  
    public FileSystem() 
    { 
    	root = new File();
    } 
  
    public List < String > ls(String path) {
    	List<String> nameList = new ArrayList<String>();
    	
    	File t = root;
    	
    	// If path == "/", then just t is root, no need to do anything
    	if (!path.equals("/")) {
    		// e.g. /a/b/c or /a/b/d.txt
    		String[] levels = path.split("/");
    		for (int i = 0; i < levels.length; i++) {
    			t = t.child.get(levels[i]);
    		}
    		if (t.isFile) {   	
    			nameList.add(levels[levels.length-1]);
    			return nameList;
    		}
    	}
    	
		Set<String> nameSet = t.child.keySet();
		nameList = new ArrayList<String> (nameSet);
		
		// Use TreeMap, no need to sort as TreeMap is sorted by natural order of the key.
//		Collections.sort(nameList);
		return nameList;    	
    }
    
    public void mkdir(String path) {
    	File t = root;
    	String[] levels = path.split("/");
		for (int i = 0; i < levels.length; i++) {
			if (!t.child.containsKey(levels[i])) {
				t.child.put(levels[i], new File());
			}
			
			t = t.child.get(levels[i]);
		}    	
    }
    
    public void addContentToFile(String filePath, String content) {
    	File t = root;
    	String[] levels = filePath.split("/");
    	// e.g. /a/b/d.txt
		for (int i = 0; i < levels.length - 1; i++) {
			t = t.child.get(levels[i]);
		}
		
		if (!t.child.containsKey(levels[levels.length-1])) {
			File newFile = new File();
			newFile.isFile = true;
			t.child.put(levels[levels.length-1], newFile);
		}
		
		File file = t.child.get(levels[levels.length-1]);
		file.content = file.content + content;
    	
    }
    
    public String readContentFromFile(String filePath) {
    	File t = root;
    	String[] levels = filePath.split("/");
    	// e.g. /a/b/d.txt
		for (int i = 0; i < levels.length - 1; i++) {
			t = t.child.get(levels[i]);
		}
		
		return t.child.get(levels[levels.length-1]).content;
    }
} 

public class Design_In_Memory_File_System_588 {
    
	public static void main(String[] args) {
		FileSystem fs = new FileSystem();
		System.out.println(fs.ls("/"));
		fs.mkdir("/a/z");
		fs.mkdir("/a/y");
		fs.mkdir("/a/b/c");
		fs.mkdir("/a/x");
		System.out.println(fs.ls("/a"));
		fs.addContentToFile("/a/b/c/d","hello");
		System.out.println(fs.readContentFromFile("/a/b/c/d"));
		
	}
}
