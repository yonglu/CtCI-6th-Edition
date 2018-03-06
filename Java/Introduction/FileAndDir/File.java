package FileAndDir;

//A class to represent a File (Inherits
//from Entry)
public class File extends Entry {
	private String content;
	private int size;

	public File(String n, Directory p, int sz) {
		super(n, p);
		size = sz;
	}

	public int getSize() {
		return size;
	}

	public String getContents() {
		return content;
	}

	public void setContents(String c) {
		content = c;
	}
}