package scm.elem;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

public class ChangedFile {

		private String filePathOld = "";
		private String filePath = "";
		private char changeType;

		private ArrayList<FileLinesChange> changes = new ArrayList<>();
		private ByteArrayOutputStream file;
		
		public ChangedFile(){}
		
		
		public ChangedFile(String filePathOld,String filePathNew) {
			// TODO Auto-generated constructor stub
			changes = new ArrayList<>();
			this.filePathOld = filePathOld;
			filePath = filePathNew;
		}
		
		public void setFile(ByteArrayOutputStream file) {
			this.file = file;
		}

		public ByteArrayOutputStream getFile() {
			return this.file;
		}
		
		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}
		
		public String getFilePath() {
			return filePath;
		}

		public void setChangeType(char changeType) {
			this.changeType = changeType;
		}
		
		public char getChangeType() {
			return changeType;
		}		
		
		public void setFilePathOld(String filePathOld) {
			 this.filePathOld = filePathOld;
		}
		
		public String getFilePathOld() {
			return filePathOld;
		}
	
		public boolean isTheSameFilePath() {
			return filePathOld.compareTo(filePath) == 0;
		}
		
		public boolean hasTheFilepathChanged() {
			return filePathOld.compareTo(filePath) != 0;
		}
		
		public ArrayList<FileLinesChange> getFileChanges(){
			return changes;
		}
		
		public FileLinesChange getChange(int i) {
			return changes.get(i);
		}
		
		public int CountChanges() {
			return changes.size();
		}
		
		public void addChange(int lineFrom, int lineTo,int linesChanged,FileLinesChange.Type type) {
			this.changes.add(new FileLinesChange(lineFrom, lineTo, linesChanged, type));
		}
}