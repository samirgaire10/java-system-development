//▼ Javaによるテキストエディタ

//▼ 「java.awt」パッケージは、swingより前に登場したGUIライブラリ
//▼ 「javax.swing」パッケージは、クラス名の最初に「J」が付いている
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;


//▼ JFrameクラスを継承（拡張）し、MyEditorクラスを作成
class MyEditor extends JFrame{

    //▼ GUIコンポーネントを収める入れ物を宣言
    JTextArea text;
    JScrollPane scroll;
    JMenuBar bar;
    JMenu menu_file, menu_edit;
    JMenuItem mi_select,mi_new, mi_open, mi_save, mi_exit, mi_copy, mi_paste, mi_cut;

    //▼ MyEditorクラスのコンストラクタを定義
    MyEditor(String title) {

        //▼ 親クラス（JFrame）のコンストラクタを呼び出しJFrameインスタンス作成
        super(title);

        //▼▼ イベントリスナオブジェクトを生成　資料P.1のⅤ参照
        EditorEvent event = new EditorEvent(this);

        //▼ 生成したJFrameインスタンスにレイアウトをセット
        this.setLayout(new BorderLayout());

        //▼ 入力用テキストエリアを作成
        text = new JTextArea();

        //▼ 折り返しをサポート
        text.setLineWrap(true);

        //▼ スクロールペインを作成してテキストエリアをセット
        scroll = new JScrollPane(text);

        //▼ フレームにScrollPaneをセット
        add(scroll, BorderLayout.CENTER);


    //▼ メニューの作成と組み込み-----

        //▼ メニューバーの作成、資料P.2の①参照
        bar = new JMenuBar(); 

        //▼ 境界の設定
        bar.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        //▼ File Menuの作成と組み込み----------------
        menu_file = new JMenu("File");

        //▼ 「File」メニューにニーモニックキー「F」を設定、資料P.2の④参照
        menu_file.setMnemonic('F');

        //▼ New
        mi_new = new JMenuItem("New", new ImageIcon("new.gif"));
        //▼▼ イベントリスナオブジェクトをメニューアイテム「New」に組み込む　資料P.1のⅥ参照
        mi_new.addActionListener(event);
        menu_file.add(mi_new);

        //▼ Open
        mi_open = new JMenuItem("Open", new ImageIcon("open.gif"));
        //▼▼ イベントリスナオブジェクトをメニューアイテム「Open」に組み込む　資料P.1のⅥ参照

        menu_file.add(mi_open);

        //▼ Save
        mi_save = new JMenuItem("Save", new ImageIcon("save.gif"));
        //▼▼ イベントリスナオブジェクトをメニューアイテム「Save」に組み込む　資料P.1のⅥ参照
        mi_save.addActionListener(event);
        menu_file.add(mi_save);

        //▼ セパレータを挟む
        menu_file.addSeparator();

        //▼ Exit
        mi_exit = new JMenuItem("Exit", new ImageIcon("exit.gif"));
        //▼▼ イベントリスナオブジェクトをメニューアイテム「Exit」に組み込む　資料P.1のⅥ参照
        mi_exit.addActionListener(event);
        menu_file.add(mi_exit);

        //▼ メニューバーに追加
        bar.add(menu_file);

        //▼ Edit Menuの作成と組み込み----------------
        menu_edit = new JMenu("Edit");
        //▼ 「Edit」メニューにニーモニックキー「E」を設定、資料P.2の④参照
        menu_edit.setMnemonic('E');

        //▼ Copy
        mi_copy = new JMenuItem("Copy", new ImageIcon("copy.gif"));
        //▼▼ イベントリスナオブジェクトをメニューアイテム「Copy」に組み込む　資料P.1のⅥ参照
        mi_copy.addActionListener(event);
        menu_edit.add(mi_copy);

        //▼ Paste
        mi_paste = new JMenuItem("Paste", new ImageIcon("paste.gif"));
        //▼▼ イベントリスナオブジェクトをメニューアイテム「Paste」に組み込む　資料P.1のⅥ参照
        mi_paste.addActionListener(event);
        menu_edit.add(mi_paste);

        //▼ Cut
        mi_cut = new JMenuItem("Cut", new ImageIcon("cut.gif"));
        //▼▼ イベントリスナオブジェクトをメニューアイテム「Cut」に組み込む　
        mi_cut.addActionListener(event);
        menu_edit.add(mi_cut);

        //▼ Select
        mi_select = new JMenuItem("Select", new ImageIcon("Select.gif"));
        //▼▼ イベントリスナオブジェクトをメニューアイテム「Select」に組み込む　
        mi_select.addActionListener(event);
        menu_edit.add(mi_select);

        
        //▼ メニューバーに「Edit」メニュー追加
        bar.add(menu_edit);

        //▼ メニューバーをフレームにセット、資料P.2の①参照
        this.setJMenuBar(bar);


    //▲ メニューの作成と組み込みここまで-----


        //▼ フレーム（ウィンドウ）が閉じる際の処理を定義
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //▼ フレームのサイズを定義して表示
        this.setSize(500, 300);
        this.setVisible(true);
    }

    //▼ mainメソッド（MyEditorを起動）
    public static void main(String s[]) {
        //▼ MyEditorクラスの新規インスタンスを作成
        MyEditor myApp = new MyEditor("My Editor");
    }
}

//▼ イベントリスナクラスの定義
//▼ アクションリスナインターフェースを組み込んでの定義
class EditorEvent implements ActionListener {
    private MyEditor editor;
    private JFileChooser file_chooser;

    //▼ コンストラクタの定義
    EditorEvent (MyEditor editor) {
        this.editor = editor;
    }

    //▼ イベント処理用メソッド
    public void actionPerformed(ActionEvent e) {
        // イベントのもととなったメニューアイテムを調べる
        JMenuItem source = (JMenuItem)e.getSource();

        //▼ Newメニューアイテムが選択された時
        if (source == editor.mi_new) {
            //▼▼「New」の処理内容 　資料P.2のⅣ参照
            editor.text.setText("");
        }

        //▼  Openメニューアイテムが選択された時
        else if(source == editor.mi_open) {
            file_chooser = new JFileChooser();

            //▼  「開く」ダイアログをオープン
            int ret = file_chooser.showOpenDialog(editor);

            //▼  yesまたはokのボタンが押された場合
            if (ret == JFileChooser.APPROVE_OPTION) {
                // ファイルを開く処理を行なう
                try {
                    //▼  読み込み用Stringオブジェクト
                    String strLine;
                    //▼  FileChooserで選択されたファイルをFileオブジェクトに代入
                    File file = file_chooser.getSelectedFile();
                    //▼  選択されたファイルの絶対パスを指定してBufferedReaderオブジェクトを作成
                    BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
                    //▼  BufferedReader オブジェクトから一行分を読込み、TextAreaへ始めの一行を代入
                    editor.text.setText(reader.readLine());
                    //▼  二行目以降は改行コードを挟んでアペンド
                    while ((strLine = reader.readLine()) != null) {
                        editor.text.append("\n" + strLine);
                    }
                    //▼  BufferedReaderオブジェクトをクローズ
                    reader.close();
                } catch (IOException ie) { }
            }
        }

        //▼  Saveメニューアイテムが選択された時
        else if(source == editor.mi_save) {
            file_chooser = new JFileChooser();
            //▼  「保存」ダイアログをオープンする
            int intRet = file_chooser.showSaveDialog(editor);

            //▼ 「yes」または「ok」のボタンが押された場合
            if (intRet == JFileChooser.APPROVE_OPTION) {
                //▼  ファイルを保存する処理を行なう
                try {
                    //▼  FileChooserで選択されたファイルをFileオブジェクトに代入
                    File file = file_chooser.getSelectedFile();
                    //▼  getAbsolutePathメソッドで得られた絶対パスによるファイル名で、Filewriterオブジェクト、
                    //▼  BufferedWriterオブジェクト、PrintWriterオブジェクトを生成
                    PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath())));
                    //▼  テキストエリアから情報を取得し、PrintWriterオブジェクト writerを使ってバッファへ出力
                    writer.write(editor.text.getText());
                    //▼  バッファからファイルに出力する際にflush()を使うが、close()はflush()後にストリームを閉じる
                    writer.flush();
                    writer.close();
                } catch (IOException ie) { }
            }
        }


        //▼  Exitメニューアイテムが選択された時
        else if(source == editor.mi_exit) {
            //▼▼「Exit」の処理内容 　資料P.2のⅤ参照
            System.exit(0);
        }

        //▼  Copyメニューアイテムが選択された時
        else if(source == editor.mi_copy) {
            //▼▼「Copy」の処理内容 　資料P.2のⅠ参照
            editor.text.copy();

        }

        //▼  Pasteメニューアイテムが選択された時
        else if(source == editor.mi_paste) {
            //▼▼「Paste」の処理内容 　資料P.2のⅢ参照
            editor.text.paste();

        }

        //▼  Cutメニューアイテムが選択された時
        else if(source == editor.mi_cut) {
            //▼▼「Cut」の処理内容 　資料P.2のⅡ参照
            editor.text.cut();

        }
        else if(source == editor.mi_select) {
            //▼▼「Select」の処理内容 　「すべてを選択」するようにメソッドを選んで下さい
            editor.text.selectAll();
        }
    }

}
