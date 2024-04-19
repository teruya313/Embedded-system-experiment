
import hardware.*; //実験で実機を使用する場合にはコメントを外してこれを使用する
//import stub.*; //予習などで実機を使用せずに動作を確認する場合には、これを使用する


import java.util.ArrayList;

public class Kadai10_DrawMtFuji
{
    private Hardware hardware = Hardware.getInstance(); //ハードウェアのインスタンスを取得する

    public void drawFigure() {
        System.out.println( "Kadai 10 Start!" );
        hardware.manipulator.sw2.addListener( () -> systemExit() );

        hardware.drawUnit.servoX.setMinMax(68 , 202); //X座標の最小値と最大値を入れる
         hardware.drawUnit.servoY.setMinMax(57 , 177); //Y座標の最小値と最大値を入れる

        ArrayList<int[]> drawPoints = new ArrayList<>();
        Bezier bezier;
        int offsetX = 50; //図形の中心座標 x
        int offsetY = 50; //図形の中心座標 Y

        bezier = new Bezier(); //1本目の曲線を生成する
        bezier.addPoint( offsetX - 60 , offsetY  -30); //始点の座標 (1)
        bezier.addPoint( offsetX -30 , offsetY - 10 ); //制御線の座標 (2)
        bezier.addPoint( offsetX - 25, offsetY +  10); //制御線の座標 (3)
        bezier.addPoint( offsetX -20 , offsetY + 30 ); //終点の座標 (4)
        drawPoints.addAll( bezier.getPoints() ); //間した座標列を取得し描画データに追加
        
        bezier = new Bezier(); //2本目の曲線を生成する
        bezier.addPoint( offsetX - 20 , offsetY + 30 ); //始点の座標 (5)
        bezier.addPoint( offsetX - 15, offsetY + 25 ); //制御線の座標 (6)
        bezier.addPoint( offsetX +15 , offsetY + 25 ); //制御線の座標 (7)
        bezier.addPoint( offsetX + 20 , offsetY +30 ); //終点の座標 (8)
        drawPoints.addAll( bezier.getPoints() ); //補間した座標列を取得して描画データに追加
        
        bezier = new Bezier(); //2本目の曲線を生成する
        bezier.addPoint( offsetX + 20 , offsetY + 30 ); //始点の座標 (9)
        bezier.addPoint( offsetX + 25, offsetY + 10 ); //制御線の座標 (10)
        bezier.addPoint( offsetX + 30 , offsetY - 10 ); //制御線の座標 (11)
        bezier.addPoint( offsetX + 60 , offsetY - 30  ); //終点の座標 (12)
        drawPoints.addAll( bezier.getPoints() ); //補間した座標列を取得して描画データに追加
        
        bezier = new Bezier(); //2本目の曲線を生成する
        bezier.addPoint( offsetX + 60 , offsetY - 30 ); //始点の座標 (13)
        bezier.addPoint( offsetX + 30, offsetY - 30 ); //制御線の座標 (14)
        bezier.addPoint( offsetX - 30 , offsetY - 30 ); //制御線の座標 (15)
        bezier.addPoint( offsetX - 60 , offsetY  -30 ); //終点の座標 (16)
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
