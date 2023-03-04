package Generic_Utilities;

import java.util.Random;

public class Java_Utility {

	public int getRanDomNum()
	{
		Random ran=new Random();
		int ranNum = ran.nextInt(1000);
		return ranNum;
	}
}
