package com.thinklab.platform.mining.recommendations.test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.ItemBasedRecommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class UserBasedRecommendation {

	public FastByIDMap<FastIDSet> toDataMap(DataModel dataModel) throws TasteException {
	    FastByIDMap<FastIDSet> data = new FastByIDMap<FastIDSet>(dataModel.getNumUsers());
	    LongPrimitiveIterator it = dataModel.getUserIDs();
	    while (it.hasNext()) {
	      long userID = it.nextLong();
	      data.put(userID, dataModel.getItemIDsFromUser(userID));
	    }
	    return data;
	}
	
	public static void main(String[] args) {
		UserBasedRecommendation test = new UserBasedRecommendation();
		try {
			DataModel model = new FileDataModel(new File("F:/temp/201412/dataset.csv"));
			UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
			UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
			System.out.println("neighborhood:"+neighborhood.getUserNeighborhood(2));
			UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
//			ItemSimilarity itemSimilarity = new EuclideanDistanceSimilarity(model);
//			ItemBasedRecommender recommender = new GenericItemBasedRecommender(model,itemSimilarity);
			List<RecommendedItem> recommendations = recommender.recommend(2, 3);
			for (RecommendedItem recommendation : recommendations) {
			  System.out.println(recommendation);
			}
			/*FastByIDMap<FastIDSet> fastId = GenericBooleanPrefDataModel.toDataMap(model);
			DataModel booleanModel = new GenericBooleanPrefDataModel(fastId);
			System.out.println(booleanModel.getNumUsers());
			UserSimilarity similarity = new SpearmanCorrelationSimilarity(booleanModel);
			UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, 0.6, similarity, booleanModel);
			Recommender recommender = new GenericBooleanPrefUserBasedRecommender(booleanModel, neighborhood, similarity);
			List<RecommendedItem> recommendations = recommender.recommend(2, 4);
			for (RecommendedItem recommendation : recommendations) {
			  System.out.println(recommendation);
			}*/
			/*DataModel model = new GenericBooleanPrefDataModel(
			GenericBooleanPrefDataModel.toDataMap(
			new FileDataModel(new File("F:/temp/201412/sss.csv"))));
			//
			UserSimilarity similarity = new LogLikelihoodSimilarity(model);
			System.out.println(similarity.userSimilarity(1, 2));
			UserNeighborhood neighborhood = new NearestNUserNeighborhood(10, similarity, model);
			Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
			List<RecommendedItem> recommendations = recommender.recommend(2, 4);
			for (RecommendedItem recommendation : recommendations) {
			  System.out.println(recommendation);
			}*/
//			RecommenderEvaluator evaluator =
//			new AverageAbsoluteDifferenceRecommenderEvaluator();
			/*RecommenderIRStatsEvaluator evaluator = new GenericRecommenderIRStatsEvaluator();
			RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
				public Recommender buildRecommender(DataModel model)
					throws TasteException {
					UserSimilarity similarity = new LogLikelihoodSimilarity(model);
					UserNeighborhood neighborhood =
					new NearestNUserNeighborhood(10, similarity, model);
					return
					new GenericUserBasedRecommender(model, neighborhood, similarity);
				}
			};
			DataModelBuilder modelBuilder = new DataModelBuilder() {
				public DataModel buildDataModel(
					FastByIDMap<PreferenceArray> trainingData) {
					return new GenericBooleanPrefDataModel(
					GenericBooleanPrefDataModel.toDataMap(trainingData));
				}
			};
//			double score = evaluator.evaluate(
//			recommenderBuilder, modelBuilder, model, 0.9, 1.0);
			IRStatistics score = evaluator.evaluate(recommenderBuilder, modelBuilder, model, null, 5, GenericRecommenderIRStatsEvaluator.CHOOSE_THRESHOLD, 1.0);
			System.out.println(score.getPrecision());
			System.out.println(score.getRecall());*/
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TasteException e) {
			e.printStackTrace();
		}
	}
	
}
