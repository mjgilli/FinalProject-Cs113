package edu.miracosta.cs113;

public class FakeEMailMergeSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EMail[] emails = new EMail[10];
		emails[0] = new EMail("Matthew Gilli", "LAB01", new Date(1,22,2013), "yo");
		emails[1] = new EMail("Matthew Gilli", "LAB02", new Date(1,22,2013), "yo");
		emails[2] = new EMail("Nick Jones", "LAB03", new Date(3,22,2015), "yo");
		emails[3] = new EMail("Matthew Gilli", "LAB03", new Date(3,22,2015), "yo");
		emails[4] = new EMail("Sally Holden", "classes", new Date(11,30,2011), "yo");
		emails[5] = new EMail("Casey Richardson", "LAB01", new Date(5,27,2017), "yo");
		emails[6] = new EMail("Alex Nava", "elementary", new Date(4,20,2007), "yo");
		emails[7] = new EMail("Kyle Erwin", "boxing", new Date(4,12,2016), "yo");
		emails[8] = new EMail("Kyle Erwin", "boxing", new Date(1,25,2012), "yo");
		emails[9] = new EMail("Pheonix Alabu", "dictionary", new Date(2,19,2012), "yo");
		
		EMail.mergeSort(emails, 'D');
		
		for(int i = 0; i < emails.length; i++)
		{
			System.out.println(emails[i]);
		}
		

	}

}
