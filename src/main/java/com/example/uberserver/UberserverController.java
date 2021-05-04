package com.example.uberserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@RestController
public class UberserverController {
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@GetMapping("v1/{now}")
    public Map<String,String> getNow(@PathVariable("now") String now){
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        final Timestamp ts = new Timestamp(System.currentTimeMillis());
        final String time = String.valueOf(sdf.format(ts));
        Map<String,String> map = new HashMap<String,String>();
        
        if(now.equals("now")){
        	map.put(now,time);
           
        }else {
        	map.put(now, "wrong key");
        }
        return map;
    }
	
	
	@GetMapping("v1/coords/{point-in-time}")
	public String getPointInTime(@PathVariable("point-in-time") int point_in_time) {
		String serviceHost = "uberserver_clusterf-proxy_1:8088";
		final String url = "http://" + serviceHost +"/v1/coords/" + point_in_time;
		RestTemplate rest = new RestTemplate();
		String result = rest.getForObject(url,String.class);
		System.out.println("result is "+result);
		return result;
	}
}
