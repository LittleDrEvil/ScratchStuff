/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KarnMenuTest.Screens;

import com.badlogic.gdx.Gdx;
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
    
    
        CharClass HitTest(CharClass chara, Vector2 vBlock, float fDist){
        
        if (isHitV(chara.vChar, 30, 40, chara.vFloor, Gdx.graphics.getWidth(), 40)) {
            chara.dSpeed = 0;
            chara.nJum = 0;
            chara.dGravity = 0;
            chara.vChar.y = 40;
            chara.fDy = 0;
        } 
        
        
        if(isHitBlockT(chara.vChar.x, chara.vChar.y , chara.nH ,vBlock.x - fDist, vBlock.y , 30)){
            chara.dSpeed = 0;
            chara.nJum = 0;
            chara.dGravity = 0;
            chara.vChar.y = vBlock.y + 32;
            chara.fDy = 0;
            
        } 
        if(isHitBlockB(chara.vChar.x, chara.vChar.y , chara.nH ,vBlock.x - fDist, vBlock.y , 30)){
            chara.dSpeed = 0;
            chara.nJum = 0;
            chara.vChar.y = chara.fSY;
            chara.fDy*=-1;
            System.out.println("bottum");   
        }

        if(isHitBlockL(chara.vChar.x, chara.vChar.y, chara.nW, chara.nH, vBlock.x - fDist, vBlock.y, 30, 30)){
            chara.dSpeed = 0;
            chara.vChar.x = chara.fSX-1;
            System.out.println("left");
            chara.dCharSpeed = 0;
            chara.fSx = 0;
            chara.dGravity = 0;
        }
        if(isHitBlockR(chara.vChar.x, chara.vChar.y, chara.nW, chara.nH, vBlock.x - fDist, vBlock.y, 30, 30)){
            chara.dSpeed = 0;
            chara.vChar.x = chara.fSX+1;
            System.out.println("right");
            chara.dCharSpeed = 0;
            chara.fSx = 0;
            chara.dGravity = 0;
        }
        
        if (chara.vChar.x < 0 && fDist <= 125) {
            chara.vChar.x += chara.fSx;
            chara.vChar.x = 1;
        } else if (chara.vChar.x < 125 && fDist > 125){
            chara.vChar.x += chara.fSx;
            chara.vChar.x = 126;
        }
        return chara;
        //        if(isHitBlockLR(chara.vChar.x, chara.vChar.y, 30, vBlock.x - fDist, vBlock.y, 30)){
//            chara.dSpeed = 0;
////            chara.nJum = 0;
////            chara.vChar.x = chara.fSX- chara.fSx;
////            System.out.println("lr");
//            if(chara.vChar.x <= vBlock.x - fDist){ chara.vChar.x = chara.fSX-2; System.out.println("left");}
//            
//            if(chara.vChar.x >= vBlock.x - fDist){ chara.vChar.x = chara.fSX+2; System.out.println("right");}
//            
//            chara.fSx = 0;
//            chara.dGravity = 0;
////            System.out.println("side");
//        } 
    }
    boolean isHitSB(Sprite spr, BlockClass bBlock, float fDist) {
        if ((((spr.getX() <= bBlock.vBlock.x-fDist) && (spr.getX() + spr.getWidth() >= bBlock.vBlock.x-fDist))
                || ((spr.getX() >= bBlock.vBlock.x-fDist) && (spr.getX() <= bBlock.vBlock.x + bBlock.nWidth - fDist)))
                && (((spr.getY() <= bBlock.vBlock.y) && (spr.getY() + spr.getHeight() >= bBlock.vBlock.y))
                || ((spr.getY() >= bBlock.vBlock.y) && (spr.getY() <= bBlock.vBlock.y + bBlock.nWidth)))) {
            return true;
        } else {
            return (false);
        }
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
    boolean isHitV(Vector2 v1, float nW1, float nH1, Vector2 v2, float nW2, float nH2) {

        if ((((v1.x <= v2.x) && (v1.x + nW1 >= v2.x))
                || ((v1.x >= v2.x) && (v1.x <= v2.x + nW2)))
                && (((v1.y <= v2.y) && (v1.y + nH1 >= v2.y))
                || ((v1.y >= v2.y) && (v1.y <= v2.y + nH2)))) {
            return true;
        } else {
            return (false);
        }
    }
    boolean isHitBlockLR(float nX1, float nY1, float nW1, float nH1, float nX2, float nY2, float nW2, float nH2) {

        if ((((nX1 <= nX2) && (nX1 + nW1 >= nX2))
                || ((nX1 >= nX2) && (nX1 <= nX2 + nW2)))
                && (((nY1 <= nY2 - 3) && (nY1 + nH1 >= nY2 - 3))
                || ((nY1 >= nY2 - 3) && (nY1 <= nY2 + nH2 - 3)))) {
            return true;
        }
        return false;
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
