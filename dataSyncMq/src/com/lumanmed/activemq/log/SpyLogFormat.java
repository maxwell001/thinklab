package com.lumanmed.activemq.log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

public class SpyLogFormat implements MessageFormattingStrategy{

	@Override
	public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql) {
		String log = "";
		try{
			if(now!=null && !"".equals(now)){
				long nowl = Long.parseLong(now);
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date now1 = new Date(nowl);
				now = df.format(now1);
			}
			log = now + "|" + sql;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return log;
	}

}
