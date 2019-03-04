package com.victor.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class PantallaInicio implements Screen {

    Stage stage;
    int inicio = 0;
    boolean volumen;
    boolean habilidad;

    PantallaInicio(boolean volumen,boolean habilidad){
        this.volumen = volumen;
        this.habilidad = habilidad;
    }

    @Override
    public void show() {
        if(!VisUI.isLoaded()){
            VisUI.load();
        }

        stage = new Stage();

        VisTable tabla = new VisTable(true);
        tabla.setFillParent(true);
        stage.addActor(tabla);

        VisTextButton btJugar = new VisTextButton("Jugar");
        btJugar.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new PantallaJuego(volumen,habilidad));
                dispose();
            }
        });

        VisTextButton btSalir = new VisTextButton("Salir");
        btSalir.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                VisUI.dispose();
                Gdx.app.exit();

            }
        });

        VisTextButton btConfiguracion = new VisTextButton("Configuracion");
        btConfiguracion.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new PantallaConfiguracion(volumen,habilidad));
                dispose();
            }
        });


        VisLabel megaJump = new VisLabel("MEGAJUMP");
        megaJump.setFontScale(2,2);
        megaJump.setColor(Color.BLACK);

        tabla.row();
        tabla.add(megaJump).center().width(150).height(100).pad(5);
        tabla.row();
        tabla.add(btJugar).center().width(150).height(100).pad(5);
        tabla.row();
        tabla.add(btConfiguracion).center().width(150).height(100).pad(5);
        tabla.row();
        tabla.add(btSalir).left().width(150).height(100).pad(5);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float dt) {
        Gdx.gl.glClearColor(1, 2, 3, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(dt);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
