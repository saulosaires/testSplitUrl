import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SplitUrl {

	/**
	 * Regex Used to split the URL
	 * 
	 * We can have up to 5 groups here
	 * 
	 * the first one the the scheme we just need to match everything before ://
	 * 
	 * the second one is the domain tha will be everything before the : or /
	 * 
	 * the third is the port is optional but if present will be everything between : and /
	 * 
	 * the fourth is the path the could be the rest of the string or until the ? char
	 * 
	 * the fifth is the query string that will be the rest of the string
	 * 
	 * **/
	public static final String REGEX="(.*)://([^:^]*)([^\"]*)/([^?]*)([^\"]*)";
	
	public static final int NUM_INTERATIONS=10000;
	
	public static void main(String[] args) {
 
		if(args.length==0)return;

		String url= args[0];

		String [] groups=null;
		
		Date dateStartRegex= new Date();

		/**
		 * Interacting through the regEx Splitter N times to test its performance 
		 * **/
		for(int i=0;i<NUM_INTERATIONS;i++){
			groups= splitRegex(url);
		}		
		
		Date dateFinalRegex= new Date();
		
		long diffRegex=dateFinalRegex.getTime()-dateStartRegex.getTime();
		
		//Time in milliseconds between NUM_INTERATIONS Interactions
		System.out.println("Regex:"+diffRegex+"msec");
		
		
		//RegEx Results
		for(String str:groups){
			System.out.println(str);
		}
		
		System.out.println();
		
		
		
		Date dateStartStateMachine= new Date();
		
		/**
		 * Interacting through the State Machine Splitter N times to test its performance 
		 * **/
		for(int i=0;i<NUM_INTERATIONS;i++){
			groups=splitStateMachine(url);
		}
		
		Date dateFinalStateMachine= new Date();
		
		long diffStateMachine=dateFinalStateMachine.getTime()-dateStartStateMachine.getTime();
		
		//Time in milliseconds between NUM_INTERATIONS Interactions
		System.out.println("State:"+diffStateMachine+"msec");
		
		//State Machine Results
		for(String str:groups){
			System.out.println(str);
		}
 
	}

 
	
	public static String [] splitStateMachine(String url){
	 
		StringBuilder part = new StringBuilder("");
		List<String>groups= new ArrayList<String>();
		 
	 
		char[] arrUrl=url.toCharArray();
	
		for(int i=0;i<arrUrl.length;i++){
	 
			char c= arrUrl[i];
			switch(c){
			
				case ':': case '/': case '?':  {
					
					//If is a special chars we add the String to the list and reset the StringBuilder,
					//if we enter in a situaion that we can two special chars one after another like '://'
					//it will not after the result because we only handle if the string until now is not empty
					if(!part.toString().equals("")){
						
						groups.add(part.toString());
						 
						part= new StringBuilder("");
						
						
					}
					break;
					
				}
			 
				default:
				  part.append(c);
					
			}
 
		}
		
		//adding the remaining string to the list
		groups.add(part.toString());
	 
		String[] arr = new String[groups.size()];
		
		groups.toArray(arr);
		
		return arr;
		
	}
	
	public static String [] splitRegex(String url){
 
		Pattern pattern = Pattern.compile(REGEX);
		Matcher matcher = pattern.matcher(url);

		matcher.find();

		String protocol = matcher.group(1);            
		String domain   = matcher.group(2);
		String port     = matcher.group(3).replace(":", "");//If the port is present the result will be like ':8080', here we just replace the ':'
		String uri      = matcher.group(4);
		String param    = matcher.group(5).replace("?", "");//If the param is present the result will be like '?params', here we just replace the '?'
		
		List<String>groups= new ArrayList<String>();
		
		groups.add(protocol);
		groups.add(domain);
		
		//Not put empty values in the list
	 	if(!"".equals(port))			
	 		groups.add(port);
	 	
		groups.add(uri);	 	
	 		
		//Not put empty values in the list
	 	if(!"".equals(param))			
	 		groups.add(param);	
			
	 	
		String[] arr = new String[groups.size()];
		
		groups.toArray(arr);
		return arr;
		
	}
	
}
