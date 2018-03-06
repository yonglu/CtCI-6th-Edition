package FileAndDir;

import java.util.*;

//A class to represent a Directory (Inherits
//from Entry)
public class Directory extends Entry {
	protected Map<String, Entry> contents;

	public Directory(String n, Directory p) {
		super(n, p);
		contents = new HashMap<String, Entry>();
	}

	public int getSize() {
		int size = 0;
		for (Entry e : contents.values())
			size += e.getSize();

		return size;
	}

	public int numberOfFiles() {
		int count = 0;
		for (Entry e : contents.values()) {
			if (e instanceof Directory) {
				count++; // Directory counts as a file
				Directory d = (Directory) e;
				count += d.numberOfFiles(); 
			} else if (e instanceof File)
				count++;
		}
		return count;
	}

	public boolean deleteEntry(String name) {
		contents.remove(name);
		return true;
	}

	public void addEntry(String name, Entry entry) {
		contents.put(name, entry);
	}

	protected ArrayList<Entry> getContents() {
		return (ArrayList)contents.values();
	}
	
	public Entry getEntry(String name) {
		return contents.get(name);
	}

}