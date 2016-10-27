/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KarnMenuTest.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class BlockClass {
    Vector2 vBlock;
       void BlockClass(Vector2 vBlock_){
           vBlock = vBlock_;
       }
       boolean SideCheck(float fX, float fDist){
           if(fX < fDist || fX + 10 > fDist) return true;
           
        return false;
       }
}
