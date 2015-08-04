package miscellaneous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class StockData {
	
	public static String JsonOutput = new String();
	    int poolSize = 2;
	 
	    int maxPoolSize = 2;
	 
	    long keepAliveTime = 10;
	 
	    ThreadPoolExecutor threadPool = null;
	 
	    final ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(
	            5);
	 
	    public StockData()
	    {
	        threadPool = new ThreadPoolExecutor(poolSize, maxPoolSize,
	                keepAliveTime, TimeUnit.SECONDS, queue);
	 
	    }
	 
	    public void runTask(Runnable task)
	    {
	        // System.out.println("Task count.."+threadPool.getTaskCount() );
	        // System.out.println("Queue Size before assigning the
	        // task.."+queue.size() );
	        threadPool.execute(task);
	        // System.out.println("Queue Size after assigning the
	        // task.."+queue.size() );
	        // System.out.println("Pool Size after assigning the
	        // task.."+threadPool.getActiveCount() );
	        // System.out.println("Task count.."+threadPool.getTaskCount() );
	        System.out.println("Task count.." + queue.size());
	 
	    }
	 
	    public void shutDown()
	    {
	        threadPool.shutdown();
	    }
	 
	    public void getStockData(final String companyName)
	    {
	    	
	        StockData mtpe = new StockData();
	        // start first one
	        mtpe.runTask(new Runnable()
	        {
	            public void run()
	            {
	                
	                    try
	                    {
	                        System.out.println("First Task");
	                       // Thread.sleep(1000);
	                        
	                        URL url = null;
							try {
								url = new URL("http://d.yimg.com/autoc.finance.yahoo.com/autoc?query="+companyName+"&callback=YAHOO.Finance.SymbolSuggest.ssCallback");
							} catch (MalformedURLException e) {
								e.printStackTrace();
							}
	                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
	                        
	                        con.setRequestMethod("GET");
	                        if(con.getResponseCode() == HttpURLConnection.HTTP_OK)
	                        {
	                        	BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
	                        	String line = new String();
	                        	StringBuilder sb = new StringBuilder();
	                        	
	                        	while((line = rd.readLine()) != null)
	                        	{
	                        		sb.append(line);
	                        		
	                        	}
	                        	
	                        	//System.out.println(sb.toString());
	                        	JsonOutput = sb.toString().substring(40,sb.toString().length()-1);
	                        	System.out.println("JSON OUTPUT :" + JsonOutput);
	                        	
	                        	
	                        	
	                        }
	                        
	                        
	                    } catch (IOException e) {
							e.printStackTrace();
						}
	                }
	            
	        });
	        
	     
	
	    }

	
}
