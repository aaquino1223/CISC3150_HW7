import java.io.*;

//Alex Aquino
//Homework 7
//DirTreeCreator outputs a tree of all the
//directories on my computer to dir_tree.txt
public class DirTreeCreator {
	
	FileOutputStream fos;
	int stack;
	
	//Creates output file
	public DirTreeCreator() {
		try {
			fos = new FileOutputStream("dir_tree.txt");
		} catch(IOException e) {
			e.printStackTrace();
		}
		stack = -1;
	}
	
	//Sets the file to the first directory
	//Returns the file
	public File setToRoot(File file) {
		File parent = file.getAbsoluteFile().getParentFile();
		
		if(parent == null) {
			return new File(parent, file.toString());
		}
		
		return setToRoot(file.getAbsoluteFile().getParentFile());
	}
	
	//Prints out all directories to the file in fos
	public void createTree(File file) {
		
		stack++;
		if(file.isDirectory()) {
			try {
				File parent = file.getAbsoluteFile().getParentFile();
				
				if(!(parent == null)) {
					for(int i = 0; i < stack; i++) {
						fos.write("\t".getBytes());
					}
					fos.write((file.getName() + "\n").getBytes());
				}
				else {
					fos.write((file.toString() + "\n").getBytes());
				}
				
				if(!(file.listFiles() == null)) {
					for(int i = 0; i < file.listFiles().length; i++) {
						createTree(file.listFiles()[i]);
					}		
				}	
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		stack--;
	}
	
	public static void main(String[] args) {
		DirTreeCreator tree = new DirTreeCreator();
		File file = new File("DirTreeCreator.java");
		
		file = tree.setToRoot(file);
		
		tree.createTree(file);
	}
}