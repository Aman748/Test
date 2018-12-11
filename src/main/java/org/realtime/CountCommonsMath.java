package org.realtime;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.moment.Variance;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CountCommonsMath implements CountCommonsMathInterface {
    private ArrayList<Future<ObjectPDF>> objectPDFArrayList;
    double[] mathArray;
    DescriptiveStatistics ds;

    CountCommonsMath(ArrayList<Future<ObjectPDF>> oobjectPDFArrayList) {
        this.objectPDFArrayList = oobjectPDFArrayList;

        mathArray = new double[objectPDFArrayList.size()];
        for (int j = 0; j < mathArray.length; j++) {
            try {
                mathArray[j] = objectPDFArrayList.get(j).get().getWordsNumber();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public double countMean() {
        Mean mean = new Mean();
        return mean.evaluate(mathArray);
    }

    @Override
    public double countVariance() {
        Variance variance = new Variance();
        return variance.evaluate(mathArray);
    }

    @Override
    public double countSD() {
        StandardDeviation standardDeviation = new StandardDeviation();
        return standardDeviation.evaluate(mathArray);
    }

    public double[] getMathArray(){
        return mathArray;
    }

//    @Override
//    public double countZS() {
//        Sum sum = new Sum();
//        sum = mean / standardDeviation;
//        return sum.evaluate(mathArray);
//    }

}
