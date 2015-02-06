package com.thinklab.platform.mining.recommendations.collaborativeFiltering.experiment;

import java.io.File;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.common.Weighting;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.RandomUtils;

public class UserBasedRecommendEvalutor {
	
	public static void main(String[] args) {
		try {
			RandomUtils.useTestSeed();
			DataModel dm = new FileDataModel(new File("F:/collection-data/libimseti/ratings.dat"));
			RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
			RecommenderBuilder builder = new RecommenderBuilder() {
				public Recommender buildRecommender(DataModel dataModel)
						throws TasteException {
					UserSimilarity similarity = new EuclideanDistanceSimilarity(dataModel,Weighting.WEIGHTED);
					UserNeighborhood neighborhood = new NearestNUserNeighborhood(1, similarity, dataModel);
					return new GenericUserBasedRecommender(dataModel, neighborhood, similarity);
				}
			};
			double rating = evaluator.evaluate(builder, null, dm, 0.8, 0.1);
			System.out.println(rating);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
