package stockanalyzer.ui;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import stockanalyzer.ctrl.Controller;

public class UserInterface 
{

	private Controller ctrl = new Controller();

	public void getDataFromCtrl1(){
		ctrl.process("AAPL;AMZN;TSLA;GOOG");
	}

	public void getDataFromCtrl2(){ ctrl.process("OMV.VI;EBS.VI;DOC.VI;SBO.VI;RBI.VI;VIG.VI;TKA.VI");
	}

	public void getDataFromCtrl3(){ ctrl.process("VOE.VI;FACC.VI;ANDR.VI;VER.VI;WIE.VI;CAI.VI;BG.VI;");

	}
	public void getDataFromCtrl4(){ ctrl.process("POST.VI;LNZ.VI;UQA.VIM;SPI.VI;ATS.VI;IIA.VI");

	}
	
	public void getDataForCustomInput() {
		System.out.println("\nPlease enter assets' symbols. Max 5 assets!");
		Scanner scan = new Scanner(System.in);
		List<String> symbolsToSearch = new ArrayList<>();
		String symbolsString = "";
		for(int i = 0; i < 5; i++) {
			symbolsToSearch.add(scan.next());
			symbolsString = symbolsString.concat(symbolsToSearch.get(i) + ";");
		}
		ctrl.process(symbolsString);
	}


	public void start() {
		Menu<Runnable> menu = new Menu<>("User Interface");
		menu.setTitel("Wählen Sie aus:");
		menu.insert("a", "Choice 1", this::getDataFromCtrl1);
		menu.insert("b", "Choice 2", this::getDataFromCtrl2);
		menu.insert("c", "Choice 3", this::getDataFromCtrl3);
		menu.insert("d", "Choice 4:",this::getDataFromCtrl4);
		menu.insert("z", "Choice User Input:",this::getDataForCustomInput);
		menu.insert("q", "Quit", null);
		Runnable choice;
		while ((choice = menu.exec()) != null) {
			 choice.run();
		}
		ctrl.closeConnection();
		System.out.println("Program finished");
	}


	protected String readLine() 
	{
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
		} catch (IOException e) {
		}
		return value.trim();
	}

	protected Double readDouble(int lowerlimit, int upperlimit) 
	{
		Double number = null;
		while(number == null) {
			String str = this.readLine();
			try {
				number = Double.parseDouble(str);
			}catch(NumberFormatException e) {
				number=null;
				System.out.println("Please enter a valid number:");
				continue;
			}
			if(number<lowerlimit) {
				System.out.println("Please enter a higher number:");
				number=null;
			}else if(number>upperlimit) {
				System.out.println("Please enter a lower number:");
				number=null;
			}
		}
		return number;
	}
}
