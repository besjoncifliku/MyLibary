package model;
import java.io.Serializable;

	
	public class BDate implements Serializable {
		private int day;
		private int month;
		private int year;
		public BDate(int d,int m,int y){
			day=d;
			month=m;
			year=y;
		}
		public BDate(String bdy){
			String[] st=bdy.split("-");
			day=Integer.parseInt(st[2]);
			month=Integer.parseInt(st[1]);
			year=Integer.parseInt(st[0]);
		}
                public BDate(int i,String bdy){
                    String[] st = bdy.split("/");
                    day = Integer.parseInt(st[2]);
                    month = Integer.parseInt(st[1]);
                    year = Integer.parseInt(st[0]);
                }
		public int getDay() {
			return day;
		}
		public void setDay(int day) {
			this.day = day;
		}
		public int getMonth() {
			return month;
		}
		public void setMonth(int month) {
			this.month = month;
		}
		public int getYear() {
			return year;
		}
		public void setYear(int year) {
			this.year = year;
		}
		@Override
		public String toString() {
			return year + "-" + month + "-" + day;
		}
	}

