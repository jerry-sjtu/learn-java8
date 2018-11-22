import ml.dmlc.xgboost4j.scala.spark.XGBoostClassifier
//val xgbParam = Map("eta" -> 0.1f,
//  "max_depth" -> 2,
//  "objective" -> "multi:softprob",
//  "num_class" -> 3,
//  "num_round" -> 100,
//  "num_workers" -> 2)


//val xgbClassifier = new XGBoostClassifier().
//  setFeaturesCol("features").
//  setLabelCol("classIndex")
//xgbClassifier.setMaxDepth(2);
//
//val xgbClassificationModel = xgbClassifier.fit(xgbInput);