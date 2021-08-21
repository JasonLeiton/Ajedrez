package scm.elem;

public class FileLinesChange {
		
		private int from = 0;
		private int to = 0;
		private int changedLines = 0;
		public static enum Type{INSERT, REMOVE,REPLACE, EMPTY};
		private Type type;
		
		public FileLinesChange(int from, int to, int changedLines, Type type) {
			this.changedLines = changedLines;
			this.from = from;
			this.to = to;
			this.type = type;
		}
		
		public Type getChangeType() {
			return this.type;
		}
		
		public int getChangedLines() {
			return this.changedLines;
		}
		
		public int getFrom() {
			return this.from;
		}
		public int getTo() {
			return this.to;
		}
		
}