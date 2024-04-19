
/**
 * クラス PreKadai_PotXandSw1 の注釈をここに書きます.
 * 
 * @author (照屋奥介)
 * @version ()
 */

//import hardware.*; 
 import stub .*;
public class PreKadai_PotXandSw1
{
       private Hardware hardware = Hardware.getInstance(); //ハードウェアのインスタンスを取得する
        
       public void disPotXandSw1(){
           while(true){
                int potXvalue = hardware.manipulator.potX.getValue();
                System.out.print("PotX=" + potXvalue);
                System.out.print( "        " ); //表示での適度なスペースをとる
                System.out.print( "SW1 = " );
                if ( hardware.manipulator.sw1.isPush() ) { //sw1が押されているか調べ
                    System.out.print( "Pushed" ); //押されていたら「Pushed」と表示す
                } else {
                    System.out.print( "Released" ); //押されてなければ「Released」と表示する
                }
                System.out.println(); //改行する
   
            }

       }
}   
   
 
