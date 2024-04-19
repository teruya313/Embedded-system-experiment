
/**
 * クラス UtilityTools の注釈をここに書きます.
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
public class UtilityTools
{
    // インスタンス変数 - コードに合わせて説明を書き換えます.
  

    /**
     * UtilityTools クラスのインスタンスのためのコンストラクタ
     */
    
    
    public static void wait_ms( int waitTime_ms ) {
            try{
                Thread.sleep( waitTime_ms);
            } catch(Exception e){
                    // nothing to do （この課題の用途では処理することがない）
                
            }    
     }

    
    
}
