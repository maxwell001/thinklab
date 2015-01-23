package com.thinklab.platform.mining.recommendations.collaborativeFiltering.experiment;

import java.io.File;
import java.io.IOException;
import org.apache.mahout.cf.taste.common.Weighting;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class UserBasedRecommendeExprement {
	
	public static void main(String[] args) {
		try {
			DataModel dataModel = new FileDataModel(new File("F:/collection-data/dataset.txt"));
			UserSimilarity similarity = new EuclideanDistanceSimilarity(dataModel, Weighting.UNWEIGHTED);
			UserNeighborhood neighborhood = new NearestNUserNeighborhood(1, 0, similarity, dataModel);
			Recommender recommender = new GenericUserBasedRecommender(dataModel, neighborhood, similarity);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
