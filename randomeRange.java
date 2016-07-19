import com.cycling74.max.*;
import java.util.Random;
/*
Copyright 2016 Eddie Farr 
*/
public class randomRange extends MaxObject{
	//make some vars
	Random rnd = new Random();
	float num,min,max,result,tempMin,tempMax;
	//constructor class
	public randomRange(){
		//declare initial values
		num = 0;
		min = 0;
		tempMin = 0;
		max = 1;
		tempMax = 1;
		result = 0;		
		//inlet information
		declareInlets(new int[]{DataTypes.ALL, DataTypes.ALL, DataTypes.ALL});
		declareOutlets(new int[]{DataTypes.ALL});
		createInfoOutlet(false);
		setInletAssist(new String[]{"Bang for random number","Set minimum range, default is 0", "Set maximum range, default is 1"});
		setOutletAssist(new String[]{"Random number in range"});
	}
	//handle input from inlets 2 and 3
	public void inlet(float f){	
		//if inlet no 2 then assign to var min
		if(getInlet() == 1){
			tempMin = min;
			min = f;
			//check to make sure it's not too high			
			if(min >= max){
				error("input greater than max");
				min = tempMin;
			}				
		}
		//if inlet no 3 then assign to var max	
		if(getInlet() ==2){
			tempMax = max;	
			max = f;
			//check to make sure it's not too low	
			if(max <= min){
				error("input lower then min");
				max = tempMax;
			}	
		}
	}
	//handle bang on input 1
	public void bang(){
		int inlet_no;
		inlet_no = getInlet();
		//on bang output the next random num in the range
		if(getInlet() == 0){
			num = rnd.nextFloat();
			result = num * (max - min) + min;
			outlet(0, result);
		}
	}
    
}
