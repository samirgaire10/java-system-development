//▼修正箇所が６箇所あります
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
//▼今回新しく利用するデータ構造は「java.util」パッケージに収められています
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

//▼▼ActionListenerインターフェースをMainControllerクラスに実装
public class MainController implements  ActionListener{
  //▼▼文字列を型のフィールド（定数）を用意し値を収める
  private final String CODE_ROCK =  "グー"  ;
  private final String CODE_SCISSORS =  "チョキ"   ;
  private final String CODE_PAPER =    "パー" ;
  //▼MainFrameオブジェクトを収めるためのフィールドを用意
  private MainFrame mainFrame = new MainFrame();

  //▼▼コンストラクタ
  public  MainController(){
    //▼グー、チョキ、パーボタンにリスナーを設定
    // mainFrame.getLabelpcimageRock().addActionListener(this);
    mainFrame.getButtonRock().addActionListener(this);
    mainFrame.getButtonScissors().addActionListener(this);
    mainFrame.getButtonPaper().addActionListener(this);
  }
  
  //▼▼勝ちパターンを文字列リストとしてvictoryListを定義しておく「自分の手:CPUの手」
  private List<String>  victoryList  ;
  {
    this.victoryList = new ArrayList<String>();
    this.victoryList.add(this.CODE_ROCK + ":" + this.CODE_SCISSORS);
    this.victoryList.add(this.CODE_SCISSORS + ":" + this.CODE_PAPER);
    this.victoryList.add(this.CODE_PAPER + ":" + this.CODE_ROCK);
  }
  
  //▼▼押されたボタンオブジェクトとじゃんけんの手（文字列）のマッピング　rpsMapを定義
  private Map< Object , String  > rpsMap; 
  {
    //▼オブジェクトの等価で判断したいので、IdentityHashMapを使う（詳細は資料参照）
    //▼たとえば、グーボタンがクリックされたら文字列"グー"に対応ということ
    this.rpsMap = new IdentityHashMap<Object, String>();
    this.rpsMap.put(this.mainFrame.getLabelpcimageRock(),this.CODE_ROCK);

    this.rpsMap.put(this.mainFrame.getButtonRock(),this.CODE_ROCK);
    this.rpsMap.put(this.mainFrame.getButtonScissors(),this.CODE_SCISSORS);
    this.rpsMap.put(this.mainFrame.getButtonPaper(),this.CODE_PAPER);
  }
  
  //▼アノテーションを使っているので記述ミスをしたらエラーを吐いてくれます
  @Override
  //▼▼じゃんけんボタン押下時のアクションリスナー　（間違った記述をしています）
  public void actionPerformed(ActionEvent e){
    //▼押したボタンから人間の手として値("グー" or "チョキ" or "パー")を取得
    String result = this.rpsMap.get(e.getSource());
    
    //▼コンピュータの選択をランダムに設定
    Random random = new Random();
    //▼文字列型配列に"グー","チョキ","パー"を収め、CPUの手として
    //▼ランダムに"グー"もしくは"チョキ"もしくは"パー"をcpuResultに代入
    String cpuResult = new String[]{this.CODE_ROCK,this.CODE_SCISSORS,this.CODE_PAPER}[random.nextInt(3)];
    
    //▼あいこ時
    if(result.equals(cpuResult)){
      this.mainFrame.setrisult("相手（ＰＣ）は" + cpuResult + "。あいこです。");
    //▼人間の勝ち
    //▼人間の勝ちパターンとして定義したvictoryListのいずれかの値である時
    }else if(this.victoryList.contains(result + ":" + cpuResult)){
      this.mainFrame.setrisult("相手（ＰＣ）は" + cpuResult + "。あなたの勝ちです。");
    //▼その他は人間の負け
    }else{
      this.mainFrame.setrisult("相手（ＰＣ）は" + cpuResult + "。あなたの負けです。");
    }
  }
}