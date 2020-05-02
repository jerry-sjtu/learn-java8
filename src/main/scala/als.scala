//import org.apache.spark.ml.evaluation.RegressionEvaluator
//import org.apache.spark.ml.recommendation.ALS
//
//case class Rating(userId: Int, movieId: Int, rating: Float, timestamp: Long)
//def parseRating(str: String): Rating = {
//  val fields = str.split(",")
//  assert(fields.size == 4)
//  Rating(fields(0).toInt, fields(1).toInt, fields(2).toFloat, 1)
//}
//
//// val ratings = spark.read.textFile("qiang.wang/data/rating.csv")
////   .filter(r => !(r contains "userId"))
////   .map(parseRating)
////   .toDF()
//
//def randomInt1to100 = scala.util.Random.nextInt(100000)+1
//def randomScore = if (scala.util.Random.nextFloat > 0.5) {-1.0f} else {1.0f}
//
//val df1 = sc.parallelize(
//  Seq.fill(10000){(randomInt1to100,randomInt1to100,randomScore)}
//).toDF("userId", "movieId", "rating")
//// df1.show()
//val ratings = df1.map(r =>  Rating(r.getAs[Int](0), r.getAs[Int](1), r.getAs[Float](2), 1)).toDF()
//
//val Array(training, test) = ratings.randomSplit(Array(0.8, 0.2))
//
//// Build the recommendation model using ALS on the training data
//val als = new ALS()
//  .setMaxIter(50)
//  .setRegParam(0.01)
//  .setUserCol("userId")
//  .setItemCol("movieId")
//  .setRatingCol("rating")
//val model = als.fit(training)
//
//// Evaluate the model by computing the RMSE on the test data
//// Note we set cold start strategy to 'drop' to ensure we don't get NaN evaluation metrics
//model.setColdStartStrategy("drop")
//val predictions = model.transform(test)
//
//val evaluator = new RegressionEvaluator()
//  .setMetricName("rmse")
//  .setLabelCol("rating")
//  .setPredictionCol("prediction")
//val rmse = evaluator.evaluate(predictions)
//println(s"Root-mean-square error = $rmse")
//
//// Generate top 10 movie recommendations for each user
//val userRecs = model.recommendForAllUsers(10)
//// Generate top 10 user recommendations for each movie
//val movieRecs = model.recommendForAllItems(10)
//
//// show the features
//val rdd1 = model.userFactors.map(row => row.getAs[Seq[Float]]("features").sum)
//rdd1.take(10)
//val rdd2 = rdd1.filter(row => row < 0.0001 && row > -0.0001)
//rdd2

import scala.collection.mutable.WrappedArray
import scala.collection.mutable.ArrayBuffer


//val rdd1 = model.userFactors.map(row => row.getAs[Seq[Float]]("features").sum)
//rdd1.take(10)