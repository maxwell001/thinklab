package com.thinklab.platform.mining.recommendations.collaborativeFiltering.experiment.customer;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.recommender.IDRescorer;

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

	public FastIDSet[] 
	
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
		
		return 0;
	}

	public boolean isFiltered(long id) {
		
		return false;
	}

}
