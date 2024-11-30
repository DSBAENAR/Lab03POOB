package domain;

public class ReplicateException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String constructionMessageOpen = "Open option in construction...";
	public static final String constructionMessageSave = "Save option in construction...";
	public static final String constructionMessageImport = "Import option in construction...";
	public static final String constructionMessageExport = "Export option in construction...";
	
	public ReplicateException(String message) {
		super(message);
	}
}
