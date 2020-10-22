package model;

public class Review {

	private String reviewer;
	private String descr;
	public Review(String reviewer, String descr) {
		super();
		this.reviewer = reviewer;
		this.descr = descr;
	}
	public String getReviewer() {
		return reviewer;
	}
	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	@Override
	public String toString() {
		return "Review [reviewer=" + reviewer + ", descr=" + descr + "]";
	}
	
	
}
