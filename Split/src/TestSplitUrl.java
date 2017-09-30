import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
 

public class TestSplitUrl {

	public static final int SIZE_FULL_URL=5;
	public static final int SIZE_MINI_URL=3;
	public static final int SIZE_NO_PORT_URL=4;
	public static final int SIZE_NO_PARAMS_URL=4;
	
	public static final String FULL_URL="http://host:8090/path?params";
	public static final String MINI_URL="http://host/path";
	public static final String NO_PORT_URL="http://host/path?params";
	public static final String NO_PARAMS_URL="http://host:8090/path";
	
	public static final String SCHEME="http";
	public static final String DOMAIN="host";
	public static final String PORT="8090";
	public static final String PATH="path";
	public static final String QUERY="params";
 
	
	@Test
	public void testSplitFullUrlRegEx(){
 
		String[] arr = SplitUrl.splitRegex(FULL_URL);
		 
		assertNotNull(arr);
		assertEquals(SIZE_FULL_URL, arr.length);
		
		assertEquals(SCHEME,arr[0]);
		assertEquals(DOMAIN,arr[1]);
		assertEquals(PORT,  arr[2]);
		assertEquals(PATH,  arr[3]);
		assertEquals(QUERY, arr[4]);
	}
 
	@Test
	public void testSplitFullUrlStateMachine(){
 
		String[] arr = SplitUrl.splitStateMachine(FULL_URL);
		 
		assertNotNull(arr);
		assertEquals(SIZE_FULL_URL, arr.length);
		
		assertEquals(SCHEME,arr[0]);
		assertEquals(DOMAIN,arr[1]);
		assertEquals(PORT,  arr[2]);
		assertEquals(PATH,  arr[3]);
		assertEquals(QUERY, arr[4]);
	}
	
	@Test
	public void testSplitMiniUrlRegEx(){
 
		String[] arr = SplitUrl.splitRegex(MINI_URL);
		 
		assertNotNull(arr);
		assertEquals(SIZE_MINI_URL, arr.length);
		
		assertEquals(SCHEME,arr[0]);
		assertEquals(DOMAIN,arr[1]);
		assertEquals(PATH,  arr[2]);
	}
	
	@Test
	public void testSplitMiniUrlStateMachine(){
 
		String[] arr = SplitUrl.splitStateMachine(MINI_URL);
		 
		assertNotNull(arr);
		assertEquals(SIZE_MINI_URL, arr.length);
		
		assertEquals(SCHEME,arr[0]);
		assertEquals(DOMAIN,arr[1]);
		assertEquals(PATH,  arr[2]);
	}
	
	@Test
	public void testSplitNoPortUrlRegEx(){
 
		String[] arr = SplitUrl.splitRegex(NO_PORT_URL);
		 
		assertNotNull(arr);
		assertEquals(SIZE_NO_PORT_URL, arr.length);
		
		assertEquals(SCHEME,arr[0]);
		assertEquals(DOMAIN,arr[1]);
		assertEquals(PATH,  arr[2]);
		assertEquals(QUERY, arr[3]);
		
	}
	
	@Test
	public void testSplitNoPortUrlStateMachine(){
 
		String[] arr = SplitUrl.splitStateMachine(NO_PORT_URL);
		 
		assertNotNull(arr);
		assertEquals(SIZE_NO_PORT_URL, arr.length);
		
		assertEquals(SCHEME,arr[0]);
		assertEquals(DOMAIN,arr[1]);
		assertEquals(PATH,  arr[2]);
		assertEquals(QUERY, arr[3]);
		
	}
	
	@Test
	public void testSplitNoQueryUrlRegEx(){
 
		String[] arr = SplitUrl.splitRegex(NO_PARAMS_URL);
		 
		assertNotNull(arr);
		assertEquals(SIZE_NO_PARAMS_URL, arr.length);
		
		assertEquals(SCHEME,arr[0]);
		assertEquals(DOMAIN,arr[1]);
		assertEquals(PORT,  arr[2]);
		assertEquals(PATH,  arr[3]);
		
	}
	
	@Test
	public void testSplitNoQueryUrlStateMachine(){
 
		String[] arr = SplitUrl.splitStateMachine(NO_PARAMS_URL);
		 
		assertNotNull(arr);
		assertEquals(SIZE_NO_PARAMS_URL, arr.length);
		
		assertEquals(SCHEME,arr[0]);
		assertEquals(DOMAIN,arr[1]);
		assertEquals(PORT,  arr[2]);
		assertEquals(PATH,  arr[3]);
		
	}
	
}
