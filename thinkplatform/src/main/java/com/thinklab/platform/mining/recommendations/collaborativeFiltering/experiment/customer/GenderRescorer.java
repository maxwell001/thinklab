package com.thinklab.platform.mining.recommendations.collaborativeFiltering.experiment.customer;

import java.io.File;
import java.io.IOException;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.recommender.IDRescorer;
import org.apache.mahout.common.iterator.FileLineIterable;

/**
 * 对偏好数据操作（修改和过滤）
 */
public class GenderRescorer implements IDRescorer{
	
	private final FastIDSet men;
	private final FastIDSet women;
	private final FastIDSet usersRateMoreMen;
	private final FastIDSet usersRateLessMen;
	private final boolean filterMen;

	public GenderRescorer(FastIDSet men,FastIDSet women,FastIDSet usersRateMoreMen,
			FastIDSet usersRateLessMen,
			long userID, DataModel model)
			throws TasteException {
		this.men = men;
		this.women = women;
		this.usersRateMoreMen = usersRateMoreMen;
		this.usersRateLessMen = usersRateLessMen;
		this.filterMen = ratesMoreMen(userID, model);
	}
	
	public static FastIDSet[] parseMenWomen(File genderFile) throws IOException{
		FastIDSet men = new FastIDSet(50000);
		FastIDSet women = new FastIDSet(50000);
		for(String line: new FileLineIterable(genderFile)){
			int comma = line.indexOf(",");
			char gender = line.charAt(comma+1);
			if(gender=='U'){
				continue;
			}
			long profileId = Long.parseLong(line.substring(0, comma+1));
			if(gender=='M'){
				men.add(profileId);
			}else{
				women.add(profileId);
			}
		}
		return new FastIDSet[] {men,women};
	}
	
	public boolean ratesMoreMen(long userId,DataModel dm) throws TasteException{
		if(usersRateMoreMen.contains(userId)){
			return true;
		}
		if(usersRateLessMen.contains(userId)){
			return false;
		}
		PreferenceArray pa = dm.getPreferencesFromUser(userId);
		int menCount = 0;
		int womenCount = 0;
		for(int i=0;i<pa.length();i++){
			long profileId = pa.getItemID(i);
			if(men.contains(profileId)){
				menCount++;
			}else if(women.contains(profileId)){
				womenCount++;
			}
		}
		boolean ratesMoreMen = menCount>womenCount;
		if(ratesMoreMen){
			usersRateMoreMen.add(userId);
		}else{
			usersRateLessMen.add(userId);
		}
		return ratesMoreMen;
	}
	
	public double rescore(long id, double originalScore) {
		return isFiltered(id)?Double.NaN:originalScore;
	}

	public boolean isFiltered(long id) {
		return filterMen?men.contains(id):women.contains(id);
	}

}
