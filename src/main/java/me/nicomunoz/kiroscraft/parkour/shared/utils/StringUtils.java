package me.nicomunoz.kiroscraft.parkour.shared.utils;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

public class StringUtils {
	
	public static String replace(String value, String... replaces) {
		if(replaces != null)
			for(int j = 0; replaces.length > j; j+=2) {
				if(value.contains(replaces[j])) value = value.replace(replaces[j], replaces[j + 1]);
			}
		return value;
	}

	public static String[] replaces(String[] msg, String... replaces) {
		List<String> list = Lists.newArrayList();
    	if(replaces != null && replaces.length > 0)
    		for(String ss : msg) {
				for(int i = 0; replaces.length > i; i+=2) {
	    			if(ss.contains(replaces[i])) {
	    				ss = ss.replaceAll(replaces[i], replaces[i+1]);
	    			}
	    		}
    			list.add(ss);
    		}
    	else list = Arrays.asList(msg);
    	return list.toArray(new String[list.size()]);
	}

}
