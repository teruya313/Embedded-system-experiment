
import hardware.*; //実験で実機を使用する場合にはコメントを外してこれを使用する
//import stub.*; //予習などで実機を使用せずに動作を確認する場合には、これを使用する

public class Kadai8_DrawStar
{
    private Hardware hardware = Hardware.getInstance(); //ハードウェアのインスタンスを取得する

    public void drawFigure() {
        System.out.println( "Kadai 8 Start!" );
        hardware.manipulator.sw2.addListener( () -> systemExit() );

        hardware.drawUnit.servoX.setMinMax( 60, 215); //X座標の最小値と最大値を入れる
        hardware.drawUnit.servoY.setMinMax( 45, 205); //Y座標の最小値と最大値を入れる
        double rc = 50; // 定円の半径
        double rm =30; // 同円の半径
        double rd =50;  //描画点の半径
        double x0 = 50; // 中心座標 x0
        double y0 = 50; // 中心座標 y0
        hardware.drawUnit.laser.on();

        while ( true ) {
            for ( int th=0 ; th<360*3 ; th += 3) { //中心角を3°間隔で360°まわす。
                double radian = Math.toRadians( th );
                double x =x0+ (rc-rm)*Math.cos(radian) + rd * Math.cos((rc-rm)*radian/rm );
                double y =y0+ (rc-rm)*Math.sin(radian) - rd * Math.sin((rc-rm)*radian/rm );
                hardware.drawUnit.servoX.setValue( convertX( (int)x ) );
                hardware.drawUnit.servoY.setValue( convertY( (int)y ) );
                UtilityTools.wait_ms( 10 );
            }
        }
    }

    private int convertX( int x ) {
        int xMin = hardware.drawUnit.servoX.getMin();
        int xMax = hardware.drawUnit.servoX.getMax();
        return(((xMax-xMin)/100)*x+xMin); //この部分に変換式を記述する
    }

    private int convertY( int y ) {
        int yMin = hardware.drawUnit.servoY.getMin();
        int yMax = hardware.drawUnit.servoY.getMax();
        return (((yMax-yMin)/100)*y+yMin);//この部分に変換式を記述する
    }

    private void systemExit() {
        hardware.reset();
        System.out.println( "Kadai 8 End." );
        System.exit( 0 );
    }
}