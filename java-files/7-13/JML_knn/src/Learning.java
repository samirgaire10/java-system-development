//package mnlearning;

import java.io.File;

import net.sf.javaml.classification.Classifier;
import net.sf.javaml.classification.KNearestNeighbors;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;
import net.sf.javaml.tools.data.FileHandler;

public class Learning {

    public static void main(String args[])throws Exception{
    	// 学習用データの読み込み
        Dataset data = FileHandler.loadDataset(new File("iris_t.data"), 4, ",");



        // 再近傍用のインスタンスの作成と学習
    	Classifier knn = new KNearestNeighbors(5);
    	knn.buildClassifier(data);

        // 検証用データの読み込み（本チュートリアルでは同じデータを使用）
    	Dataset dataForClassification = FileHandler.loadDataset(new File("iris_v.data"), 4, ",");


        //検証結果と検証用のデータの検査を行う
        //Instance inst : 検証用データが格納されている
        //Classifier knn : 学習した結果が格納されている
        // 正解と不正解の場合のカウント
        int correct = 0;
        int wrong = 0;
        // 分類と結果判定の実施
        for (Instance inst : dataForClassification) {
        	Object predictedClassValue = knn.classify(inst);
        	Object realClassValue = inst.classValue(); // 評価用のデータから実際の分類値を取り出す
            if (predictedClassValue.equals(realClassValue))
                correct++;
            else
                wrong++;
        }
        System.out.println("ｋ近傍法による機械学習");
        System.out.println("正しい分類数：" + correct);
        System.out.println("誤った分類数：" + wrong);
    }
}