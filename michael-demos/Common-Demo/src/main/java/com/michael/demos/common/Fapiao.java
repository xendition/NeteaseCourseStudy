package com.michael.demos.common;

import java.util.*;

/**
 * 类功能描述:
 * <pre>
 *   xxxx
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/12/9 11:47
 */
public class Fapiao {


    public static void main(String[] args) {

        List<Double> prices = Arrays.asList(
                272.3,366d,229.95,83d
        );

        double target = 735;

        doCheck(prices, target);

    }

    private static void doCheck(List<Double> prices, double target) {

        Map<String,Double> resultMap = new HashMap<>();

        for (int i = 0; i < prices.size(); i++) {

            Double priceI = prices.get(i);
            for (int j = 0; j < prices.size(); j++) {

                Double priceJ = prices.get(j);
                Double total = priceI + priceJ;
                if ( i != j && total >= target) {
                    resultMap.put(priceI + " + " + priceJ,total);
                }

                for (int k = 0; k < prices.size(); k++) {

                    Double priceK = prices.get(k);
                    total = total + priceK;
                    if ( i != j && j != k && total >= target) {
                        resultMap.put(priceI + " + " + priceJ + " + " + priceK,total);
                    }

                    for (int l = 0; l < prices.size(); l++) {

                        Double priceL = prices.get(l);
                        total = total + priceL;
                        if ( i != j && j != k && k != l  && total >= target) {
                            resultMap.put(priceI + " + " + priceJ + " + " + priceK+ " + " + priceL,total);
                        }
                    }
                }
            }
        }
        List<Map.Entry<String, Double>> infoIds = new ArrayList<>(resultMap.entrySet());

        Collections.sort(infoIds, (o1, o2) -> (
                o2.getValue()>= o1.getValue() ? -1:1
        ));

        for (int i = 0; i < infoIds.size(); i++) {
            String id = infoIds.get(i).toString();
            System.out.println(id);
        }
    }
}
