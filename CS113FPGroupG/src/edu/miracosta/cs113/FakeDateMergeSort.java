package edu.miracosta.cs113;

public class FakeDateMergeSort 
{

	public static void main(String[] args) 
	{
		Date[] dates = new Date[10];
		dates[0] = new Date(1,22,2013);
		dates[1] = new Date(3,17,1995);
		dates[2] = new Date(11,21,2011);
		dates[3] = new Date(7,12,2012);
		dates[4] = new Date(9,30,2009);
		dates[5] = new Date(10,22,2003);
		dates[6] = new Date(4,3,2007);
		dates[7] = new Date(8,7,2005);
		dates[8] = new Date(5,25,2013);
		dates[9] = new Date(1,22,2011);
		
		Date.mergeSort(dates);
		
		for(int i = 0; i < dates.length; i++)
		{
			System.out.println(dates[i]);
		}
		
	}

}
