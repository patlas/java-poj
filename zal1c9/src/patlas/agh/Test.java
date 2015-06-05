package patlas.agh;

import patlas.agh.utils.TwoTypeList;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Preference x = new Preference();
		Preference y = new Preference("adsfadf", 2);
		Preference yf = new Preference("Joanna", 22);
		Preference yfx = new Preference("Patryk", 25);
		Preference.setTIMEOUT(20);
		x.setAddr("www.agbc.pl");
		//System.out.println(x.getAddr());
		
		Preferences.saveSettings();
		Preferences.loadSettings();
		
		for(Preference a : Preferences.prefList)
			System.out.println(a.getAddr());
		//System.out.println(Preferences.prefList.get(0).getAddr());
		//System.out.println(Preferences.prefList.size());
		//System.out.println(Preferences.prefList.get(Preferences.prefList.size()-2).getAddr());
		
		
	}

}
