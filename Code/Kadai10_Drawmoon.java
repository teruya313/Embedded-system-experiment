
import hardware.*; //実験で実機を使用する場合にはコメントを外してこれを使用する
 //import stub.*; //予習などで実機を使用せずに動作を確認する場合には、これを使用する

import java.util.ArrayList;

public class Kadai10_Drawmoon
{
    private Hardware hardware = Hardware.getInstance(); //ハードウェアのインスタンスを取得する

    public void drawFigure() {
        System.out.println( "Kadai 9 Start!" );
        hardware.manipulator.sw2.addListener( () -> systemExit() );

        hardware.drawUnit.servoX.setMinMax(68 , 202); //X座標の最小値と最大値
        hardware.drawUnit.servoY.setMinMax(57 , 177); //Y座標の最小値と最大値

        ArrayList<int[]> drawPoints = new ArrayList<>();
        Bezier bezier;
        int offsetX = 50; //図形の中心座標 x
        int offsetY = 50; //図形の中心座標 Y

        bezier = new Bezier(); //1本目の曲線を生成する
        bezier.addPoint( offsetX - 0 , offsetY  -20); //始点の座標 (1)
        bezier.addPoint( offsetX +20 , offsetY - 0 ); //制御線の座標 (2)
        bezier.addPoint( offsetX +20 , offsetY + 20 ); //制御線の座標 (3)
        bezier.addPoint( offsetX - 0 , offsetY + 40 ); //終点の座標 (4)
        drawPoints.addAll( bezier.getPoints() ); //間した座標列を取得し描画データに追加
        
        bezier = new Bezier(); //2本目の曲線を生成する
        bezier.addPoint( offsetX + 0 , offsetY + 40 ); //始点の座標 (5)
        bezier.addPoint( offsetX + 40, offsetY + 20 ); //制御線の座標 (6)
        bezier.addPoint( offsetX + 40, offsetY + 0 ); //制御線の座標 )
        bezier.addPoint( offsetX + 0 , offsetY - 20 ); //終点の座標 (8)
        drawPoints.addAll( bezier.getPoints() ); //補間した座標列を取得して描画データに追加

        while ( true ) {
            for ( int[] point : drawPoints ) {
                hardware.drawUnit.servoX.setValue( convertX( point[0] ) );
                hardware.drawUnit.servoY.setValue( convertY( point[1] ) );
                if ( point[2] != 0 ) {
                    hardware.drawUnit.laser.on();
                } else {
                    hardware.drawUnit.laser.off();
                }
                UtilityTools.wait_ms( 10 );
            }
        }
    }

    private int convertX( int x ) {
        int xMin = hardware.drawUnit.servoX.getMin();
        int xMax = hardware.drawUnit.servoX.getMax();
        return ( x+xMin); //この部分に変換式を記述する
    }

    private int convertY( int y ) {
        int yMin = hardware.drawUnit.servoY.getMin();
        int yMax = hardware.drawUnit.servoY.getMax();
        return ( y+yMin); //この部分に変換式を記述する
    }

    private void systemExit() {
        hardware.reset();
        System.out.println( "Kadai 10 End." );
        System.exit( 0 );
    }
}
