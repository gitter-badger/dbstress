package eu.semberal.dbstress.util

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics

object ModelExtensions {

  implicit class StatisticalExtensions(l: List[Long]) {

    private lazy val ds = new DescriptiveStatistics(l.map(_.toDouble).toArray)

    private def doCalculate(f: DescriptiveStatistics => Double): Option[Double] = if(l.isEmpty) None else Some(f(ds))

    def median: Option[Double] = doCalculate(_.getPercentile(50))

    def mean: Option[Double] = doCalculate(_.getMean)

    def stddev: Option[Double] = doCalculate(_.getStandardDeviation)

    def minimum: Option[Long] = if(l.isEmpty) None else Some(l.min)

    def maximum: Option[Long] = if(l.isEmpty) None else Some(l.max)
  }

  implicit class StringifiedOption[T](o: Option[T]) {
    def getOrMissingString: String = o.map(_.toString).getOrElse("-")
  }
}
