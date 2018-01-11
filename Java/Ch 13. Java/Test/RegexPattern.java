package Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class RegexPattern {
	public static String lem() {
		System.out.println("lem");
		return "return from lem";
	}
	
	public static String foo() {
		int x = 0;
		int y = 5;
		try
		{
			System.out.println("start try");
			int b = y / x;
			System.out.println("end try");
			return "returned from try";
		} catch (Exception ex) {
			System.out.println("catch");
			return lem() + " | returned from catch";
		} finally {
			System.out.println("finally");	
		}
	}
	
	public static void bar() {
	   String metricsToExportPattern = new String("TotalPostsFailed|^\\w{0,}PerMinute$|PostsFailedPerMinute|TotalPostsRejected|TotalPostsSuccessful|PostsSuccessfulPerMinute|Output_WriteCount|Output_WriteRate");
      Pattern emitMetricsPattern = Pattern.compile(metricsToExportPattern);
      Set<String> metricNames = new HashSet<String>();
      metricNames.add("TotalPostsSuccessful");
      metricNames.add("Posts2SuccessfulPerMinute");
      metricNames.add("Posts3SuccessfulPerMinute");
      for (String name : metricNames) {
         if (emitMetricsPattern.matcher(name).matches()) {
            System.out.println("Emit to influxdb: " + name);
         }
      }
	}
	
	public static void main(String[] args) {
		bar();
	}

}
