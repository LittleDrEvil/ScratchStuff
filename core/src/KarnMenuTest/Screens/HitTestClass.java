/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KarnMenuTest.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author karnh7634
 */
public class HitTestClass {
    
    public void HitTestClass(CharClass chara, float fDist){
//        if (isHitV(chara.vChar, 30, 40, chara.vFloor, Gdx.graphics.getWidth(), 40)) {
//            chara.dSpeed = 0;
//            chara.nJum = 0;
//            chara.dGravity = 0;
//            chara.vChar.y = 40;
//            chara.fDy = 0;
//        } 
//        
//        
//        if(isHitBlockT(chara.vChar.x, chara.vChar.y , chara.nH ,vBlock.x - fDist, vBlock.y , 30)){
//            chara.dSpeed = 0;
//            chara.nJum = 0;
//            chara.dGravity = 0;
//            chara.vChar.y = vBlock.y + 32;
//            chara.fDy = 0;
//            
//        } 
//        if(isHitBlockB(chara.vChar.x, chara.vChar.y , chara.nH ,vBlock.x - fDist, vBlock.y , 30)){
//            chara.dSpeed = 0;
//            chara.nJum = 0;
//            chara.vChar.y = chara.fSY;
//            chara.fDy*=-1;
//            System.out.println("bottum");   
//        }
//
//        if(isHitBlockL(chara.vChar.x, chara.vChar.y, chara.nW, chara.nH, vBlock.x - fDist, vBlock.y, 30, 30)){
//            chara.dSpeed = 0;
//            chara.vChar.x = chara.fSX-1;
//            System.out.println("left");
//            chara.dCharSpeed = 0;
//            chara.fSx = 0;
//            chara.dGravity = 0;
//        }
//        if(isHitBlockR(chara.vChar.x, chara.vChar.y, chara.nW, chara.nH, vBlock.x - fDist, vBlock.y, 30, 30)){
//            chara.dSpeed = 0;
//            chara.vChar.x = chara.fSX+1;
//            System.out.println("right");
//            chara.dCharSpeed = 0;
//            chara.fSx = 0;
//            chara.dGravity = 0;
//        }
//        
//        if (chara.vChar.x < 0 && fDist <= 125) {
//            chara.vChar.x += chara.fSx;
//            chara.vChar.x = 1;
//        } else if (chara.vChar.x < 125 && fDist > 125){
//            chara.vChar.x += chara.fSx;
//            chara.vChar.x = 126;
//        }
    }
        
        CharClass HitBlock(Sprite chara, Vector2 vBlock, float fDist, CharClass charac){
            
            
        
        if(isHit(chara.getX(), chara.getY() , chara.getWidth(), chara.getHeight() ,0, 0, Gdx.graphics.getWidth(), 40)){
            charac.dSpeed = 0;
            charac.nJum = 0;
            charac.dGravity = 0;
            charac.vChar.y = 40;
            charac.fDy = 0;
        } 
        
        
        if(isHitBlockT(chara.getX(), chara.getY() , chara.getHeight() ,vBlock.x - fDist, vBlock.y , 30)){
            charac.dSpeed = 0;
            charac.nJum = 0;
            charac.dGravity = 0;
            charac.vChar.y = vBlock.y + 32;
            charac.fDy = 0;
        } 
        if(isHitBlockB(chara.getX(), chara.getY() , chara.getHeight() ,vBlock.x - fDist, vBlock.y , 30)){
            charac.dSpeed = 0;
            charac.nJum = 0;
            charac.vChar.y = charac.fSY;
            charac.fDy*=-1;
            System.out.println("bottum");   
        }

        if(isHitBlockL(chara.getX(), chara.getY(), chara.getWidth(), chara.getHeight(), vBlock.x - fDist, vBlock.y, 30,30)){
            charac.fDist -= charac.dSpeed;
            charac.dSpeed = 0;
            charac.vChar.x = charac.fSX-1;
            System.out.println("left");
            charac.dCharSpeed = 0;
            
            charac.fSx = 0;
            charac.dGravity = 0;
            
        }
        if(isHitBlockR(chara.getX(), chara.getY(), chara.getWidth(), chara.getHeight(), vBlock.x - fDist, vBlock.y, 30,30)){
            charac.fDist += charac.dSpeed;
            charac.dSpeed = 0;
            charac.vChar.x = charac.fSX+1;
            System.out.println("right");
            charac.dCharSpeed = 0;
            
            charac.fSx = 0;
            charac.dGravity = 0;
        }
        
        if (chara.getX() < 0 && fDist <= 125) {
            charac.vChar.x = 1;
        } else if (chara.getX() < Gdx.graphics.getWidth()/8 && fDist > 125){
            charac.vChar.x = Gdx.graphics.getWidth()/8+1;
        }
        if (chara.getX() > Gdx.graphics.getWidth() - Gdx.graphics.getWidth()/8) {
            charac.vChar.x -= charac.fSx;
            charac.vChar.x = Gdx.graphics.getWidth() - Gdx.graphics.getWidth()/8 - 1;
        }
        
//        if (charac.nJum == 0) {
//                if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
//                    charac.fDy = 4;
//                    charac.nJum = 1;
//                    charac.dGravity = -0.01;
//                    System.out.println("up");
//                }
//        }
        return charac;
    }
        
    boolean isHit(float nX1, float nY1, float nW1, float nH1, float nX2, float nY2, float nW2, float nH2) {

        if ((((nX1 <= nX2+5) && (nX1 + nW1 + 5>= nX2))
                || ((nX1 >= nX2) && (nX1 <= nX2 + nW2)))
                && (((nY1 <= nY2) && (nY1 + nH1 >= nY2))
                || ((nY1 >= nY2) && (nY1 <= nY2 + nH2)))) {
            return true;
        } else {
            return (false);
        }
    }
    boolean isHitBlockR(float nX1, float nY1, float nW1, float nH1, float nX2, float nY2, float nW2, float nH2) {

        if ((((nX1 >= nX2) && (nX1 <= nX2 + nW2)))
                && (((nY1 <= nY2 - 3) && (nY1 + nH1 >= nY2 - 3))
                || ((nY1 >= nY2 - 3) && (nY1 <= nY2 + nH2 - 3)))) {
            return true;
        }
        return false;
    }
    boolean isHitBlockL(float nX1, float nY1, float nW1, float nH1, float nX2, float nY2, float nW2, float nH2) {

        if ((((nX1 <= nX2) && (nX1 + nW1 >= nX2)))
                && (((nY1 <= nY2 - 3) && (nY1 + nH1 >= nY2 - 3))
                || ((nY1 >= nY2 - 3) && (nY1 <= nY2 + nH2 - 3)))) {
            return true;
        }
        return false;
    }
    boolean isHitBlockT(float nX1, float nY1, float nS1, float nX2, float nY2, float nS2) {

        if ((((nX1 <= nX2+1) && (nX1 + nS1 >= nX2-1))
                || ((nX1 >= nX2-1) && (nX1 <= nX2 + nS2 + 1)))
                && ((nY1 >= nY2 + 5) && (nY1 <= nY2 + 5 + nS2))) {
            return true;
        }
        return false;
    }
    boolean isHitBlockB(float nX1, float nY1, float nS1, float nX2, float nY2, float nS2) {

        if ((((nX1 <= nX2+2) && (nX1 + nS1 >= nX2-2))
                || ((nX1 >= nX2-1) && (nX1 + 2 <= nX2 + nS2)))
                && (((nY1 <= nY2 - 3) && (nY1 + nS1 >= nY2 - 3)))) {
            return true;
        }
        return false;
    }
}
