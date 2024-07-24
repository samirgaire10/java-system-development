import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.Clip;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainController implements ActionListener {
    private final String CODE_ROCK = "グー";
    private final String CODE_SCISSORS = "チョキ";
    private final String CODE_PAPER = "パー";
    private MainFrame mainFrame = new MainFrame();

    private Clip clipTie;
    private Clip clipWin;
    private Clip clipLose;

    public MainController() {
        mainFrame.getButtonRock().addActionListener(this);
        mainFrame.getButtonScissors().addActionListener(this);
        mainFrame.getButtonPaper().addActionListener(this);
        
        try {
            clipTie = SoundPlayTest.createClip(new File("もう１回やる？.wav"));
            clipWin = SoundPlayTest.createClip(new File("あなたの勝ち.wav"));
            clipLose = SoundPlayTest.createClip(new File("あなたの負け.wav"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<String> victoryList = new ArrayList<String>() {
        {
            add(CODE_ROCK + ":" + CODE_SCISSORS);
            add(CODE_SCISSORS + ":" + CODE_PAPER);
            add(CODE_PAPER + ":" + CODE_ROCK);
        }
    };

    private Map<Object, String> rpsMap = new IdentityHashMap<Object, String>() {
        {
            put(mainFrame.getButtonRock(), CODE_ROCK);
            put(mainFrame.getButtonScissors(), CODE_SCISSORS);
            put(mainFrame.getButtonPaper(), CODE_PAPER);
        }
    };

    @Override
    public void actionPerformed(ActionEvent e) {
        String result = rpsMap.get(e.getSource());
        Random random = new Random();
        String cpuResult = new String[]{CODE_ROCK, CODE_SCISSORS, CODE_PAPER}[random.nextInt(3)];

        if (cpuResult.equals(CODE_ROCK)) {
            mainFrame.setimageRockPc();
        } else if (cpuResult.equals(CODE_SCISSORS)) {
            mainFrame.setimageScissorsPc();
        } else if (cpuResult.equals(CODE_PAPER)) {
            mainFrame.setimagePaperPc();
        }

        if (result.equals(cpuResult)) {
            mainFrame.setSubTitle("　　　相手（ＰＣ）は" + cpuResult + "。あいこです。");
            playClip(clipTie);
        } else if (victoryList.contains(result + ":" + cpuResult)) {
            mainFrame.setSubTitle("　　　相手（ＰＣ）は" + cpuResult + "。あなたの勝ちです。");
            playClip(clipWin);
        } else {
            mainFrame.setSubTitle("　　　相手（ＰＣ）は" + cpuResult + "。あなたの負けです。");
            playClip(clipLose);
        }
    }

    private void playClip(Clip clip) {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}