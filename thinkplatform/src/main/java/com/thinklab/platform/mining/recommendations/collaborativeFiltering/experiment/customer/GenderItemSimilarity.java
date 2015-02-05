package com.thinklab.platform.mining.recommendations.collaborativeFiltering.experiment.customer;

import java.util.Collection;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

public class GenderItemSimilarity implements ItemSimilarity{
	
	private FastIDSet men;
	private FastIDSet women;

	public GenderItemSimilarity(FastIDSet men,FastIDSet women){
		this.men = men;
		this.women = women;
	}
	
	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		
	}

	public double itemSimilarity(long profileID1, long profileID2)
			throws TasteException {
		Boolean profileID1IsMan = isMan(profileID1);
		if(profileID1IsMan==null){
			return 0.0;
		}
		Boolean profileID2IsMan = isMan(profileID2);
		if(profileID2IsMan==null){
			return 0.0;
		}
		
		return profileID1IsMan==profileID2IsMan?1.0:-1.0;
	}

	public double[] itemSimilarities(long profileID1, long[] profileID2s)
			throws TasteException {
		double[] result = new double[profileID2s.length];
		for(int i=0;i<profileID2s.length;i++){
			result[i] = itemSimilarity(profileID1, profileID2s[i]);
		}
		return result;
	}

	public long[] allSimilarItemIDs(long itemID) throws TasteException {
		return null;
	}
	
	public Boolean isMan(long profileId){
		if(men.contains(profileId)){
			return Boolean.TRUE;
		}
		if(women.contains(profileId)){
			return Boolean.FALSE;
		}
		return null;
	}
	
}
