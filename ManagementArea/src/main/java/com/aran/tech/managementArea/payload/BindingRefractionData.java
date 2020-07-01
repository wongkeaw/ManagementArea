package com.aran.tech.managementArea.payload;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author oawon
 */
public class BindingRefractionData {

	public static Object Binding(Object source , Object distination) {
		
    	Method[]  methods = source.getClass().getMethods() ;
    	for (Method method : methods) {
			if (method.getName().startsWith("get"))  {
				try {
					Object value  = method.invoke(source );
					System.out.println(value);
					if ( value != null ) {
						Method setter = distination.getClass().getMethod(method.getName().replace("get", "set"), value.getClass());
						setter.invoke(distination,value) ;
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
					
				}
			}
		}
    	return distination ;
	}
}
