package com.thinklab.platform.mining.recommendations.collaborativeFiltering.experiment.customer;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.IDRescorer;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class CustomerRecommender implements Recommender{
	
	private Recommender delegate;
	private DataModel dataModel;
	private FastIDSet men;
	private FastIDSet women;

	public CustomerRecommender(DataModel dataModel) throws TasteException, IOException{
		UserSimilarity similarity = new EuclideanDistanceSimilarity(dataModel);
		UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, dataModel);
		delegate = new GenericUserBasedRecommender(dataModel, neighborhood, similarity);
		this.dataModel = dataModel;
		FastIDSet[] ids = GenderRescorer.parseMenWomen(new File(""));
		men = ids[0];
		women = ids[1];
	}
	
	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		
	}

	public List<RecommendedItem> recommend(long userID, int howMany)
			throws TasteException {
		IDRescorer rescorer = new GenderRescorer(men, women,null,null,userID, dataModel);
		return delegate.recommend(userID, howMany, rescorer);
	}

	public List<RecommendedItem> recommend(long userID, int howMany,
			IDRescorer rescorer) throws TasteException {
		return  delegate.recommend(userID, howMany, rescorer);
	}

	public float estimatePreference(long userID, long itemID)
			throws TasteException {
		IDRescorer rescorer = new GenderRescorer(men, women, null, null, userID, dataModel);
		return (float) rescorer.rescore(userID, delegate.estimatePreference(userID, itemID));
	}

	public void setPreference(long userID, long itemID, float value)
			throws TasteException {
		delegate.setPreference(userID, itemID, value);
	}

	public void removePreference(long userID, long itemID)
			throws TasteException {
		delegate.removePreference(userID, itemID);
	}

	public DataModel getDataModel() {
		return delegate.getDataModel();
	}

}
