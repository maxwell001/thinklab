package com.thinklab.platform.mining.recommendations.collaborativeFiltering.experiment;

import java.io.File;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.common.Weighting;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.RandomUtils;

public class UserBasedRecommendIRStatusEvalutor {
	
	public static void main(String[] args) {
		try {
			RandomUtils.useTestSeed();
			DataModel dm = new FileDataModel(new File("F:/collection-data/libimseti/ratings.dat"));
			RecommenderIRStatsEvaluator evaluator = new GenericRecommenderIRStatsEvaluator();
			RecommenderBuilder builder = new RecommenderBuilder() {
				public Recommender buildRecommender(DataModel dataModel)
						throws TasteException {
					UserSimilarity similarity = new EuclideanDistanceSimilarity(dataModel,Weighting.WEIGHTED);
					UserNeighborhood neighborhood = new NearestNUserNeighborhood(1, similarity, dataModel);
					return new GenericUserBasedRecommender(dataModel, neighborhood, similarity);
				}
			};
			IRStatistics rating = evaluator.evaluate(builder, null, dm, null, 2, GenericRecommenderIRStatsEvaluator.CHOOSE_THRESHOLD, 1.0);
			System.out.println(rating.getPrecision());
			System.out.println(rating.getRecall());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
