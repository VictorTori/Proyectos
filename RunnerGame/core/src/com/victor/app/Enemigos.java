package com.victor.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Enemigos {

    Texture rino;
    Texture fire;

    Rectangle fireRec;
    Rectangle rinoRec;

    Vector2 posicionRino;
    Vector2 posicionFire;

    long velocidadRino;
    long velocidadFuego;

    public boolean fuego;
    int puntuacion = 0;

    public Enemigos(){
        posicionRino = new Vector2(Gdx.graphics.getWidth(),0);
        posicionFire = new Vector2(Gdx.graphics.getWidth(),0);
        rino = new Texture("enemigo.png");
        fire = new Texture("fire.png");
        rinoRec = new Rectangle(posicionRino.x, posicionRino.y,64,64);
        fireRec = new Rectangle(posicionFire.x,posicionFire.y,70,40);
        velocidadFuego = 8;
        velocidadRino = 3;
    }

    public void render(SpriteBatch batch){

        batch.draw(rino, posicionRino.x, posicionRino.y);
        batch.draw(fire, posicionFire.x, posicionFire.y);
    }

    public void moveRino(){
        posicionRino.x = posicionRino.x - velocidadRino;
        rinoRec.x = posicionRino.x;
        if(posicionRino.x < 0)
            posicionRino.x = Gdx.graphics.getWidth();
    }

    public void moveFire(){
        posicionFire.x = posicionFire.x -velocidadFuego;
        fireRec.x = posicionFire.x;
        if(posicionFire.x < -fire.getWidth()) {
            posicionFire.x = Gdx.graphics.getWidth();
            puntuacion = puntuacion +50;
            fuego = false;
        }
    }
}
