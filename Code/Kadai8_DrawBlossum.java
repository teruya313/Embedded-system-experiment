/**
 * トロコイドによる円の描画
 *
 * @author (学籍番号 氏名)
 * @version (プログラムを作成した日付)
 */
import hardware.*; //実験で実機を使用する場合にはコメントを外してこれを使用する
// import stub.*; //予習などで実機を使用せずに動作を確認する場合には、これを使用する

public class Kadai8_DrawBlossum
{
    private Hardware hardware = Hardware.getInstance(); //ハードウェアのインスタンスを取得する

    public void drawFigure() {
        System.out.println( "Kadai 6 Start!" );
        hardware.manipulator.sw2.addListener( () -> systemExit() );

        hardware.drawUnit.servoX.setMinMax( 60, 215); //X座標の最小値と最大値を入れる
        hardware.drawUnit.servoY.setMinMax( 45, 205); //Y座標の最小値と最大値を入れる
        double rc = 60;
        double rm = 40;// 半径 rc
        double rd =60;
        double x0 = 50; // 中心座標 x0
        double y0 = 50; // 中心座標 y0
        hardware.drawUnit.laser.on();

        while ( true ) {
            for ( int th=0 ; th<360*3 ; th += 3) { //中心角を3°間隔で360°まわす。
                double radian = Math.toRadians( th );
               double x = (rc-rm)*Math.cos(radian) + rd * Math.cos((rc-rm)*radian/rm );
                double y = (rc-rm)*Math.sin(radian) - rd * Math.sin((rc-rm)*radian/rm );
                hardware.drawUnit.servoX.setValue( convertX( (int)x ) );
                hardware.drawUnit.servoY.setValue( convertY( (int)y ) );
                UtilityTools.wait_ms( 10 );
            }
        }
    }

    private int convertX( int x ) {
        int xMin = hardware.drawUnit.servoX.getMin();
        int xMax = hardware.drawUnit.servoX.getMax();
        return ( x+130); //この部分に変換式を記述する
    }

    private int convertY( int y ) {
        int yMin = hardware.drawUnit.servoY.getMin();
        int yMax = hardware.drawUnit.servoY.getMax();
        return (y+130); //この部分に変換式を記述する
    }

    private void systemExit() {
        hardware.reset();
        System.out.println( "Kadai 6 End." );
        System.exit( 0 );
    }
}