package com.catracker.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class Mytransformer implements IAnnotationTransformer{	
	
	static List<String> methodNames = new ArrayList();
		
static{
	Properties properties=new Properties();
	try {
	    //	FileInputStream file=new FileInputStream("C:\\Users\\yuvraj reddy\\Desktop\\Automation\\Little Caesars_WorkSpace\\VertivInc\\src\\Config.properties");
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\Config.properties");
		
		try {
			properties.load(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  	
	
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	
	
	String str=properties.getProperty("Range");
//	String str="TC5";
	   String[] temp = str.split(",");
	   
	   for(int i =0; i<temp.length;i++){
	    
	    if(temp[i].contains("-")){
	     
	     String[] values = temp[i].split("-");
	     int startIndex = Integer.valueOf(values[0].substring(2));
	     int endIndex = Integer.valueOf(values[1].substring(2));
	     for(;startIndex<=endIndex;startIndex++){
	      
	      methodNames.add("TC"+String.valueOf(startIndex));
	     }
	     
	    }
	    else
	    {
	     methodNames.add(temp[i]);
	    }
	}
}

	
		
		

	
//	@SuppressWarnings("rawtypes")
	public void transform(ITestAnnotation annotation, Class testClass,
			Constructor testConstructor, Method testMethod) {
		// TODO Auto-generated method stub
		
		if(methodNames.contains(testMethod.getName())){
			
			String methodName = testMethod.getName();
			int index = methodNames.indexOf(methodName);		
			annotation.setPriority(index);
			annotation.setEnabled(true);
		}
			
		else
			annotation.setEnabled(false);
		
		
		
		
		
	}

	
}



