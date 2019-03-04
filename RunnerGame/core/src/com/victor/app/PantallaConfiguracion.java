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

public class PantallaConfiguracion implements Screen {

    Stage stage;
    int score;
    String correctoV;
    String correctoH;
    boolean volumen;
    boolean habilidadOn;
    final String volOn = "VOLUMEN ON";
    final String volOff = "VOLUMEN OFF";
    final String habOn = "HABILIDAD ON";
    final String habOff = "HABILIDAD OFF";

    PantallaConfiguracion(boolean volumen, boolean habilidadOn){
        this.volumen = volumen;
        this.habilidadOn = habilidadOn;
    }

    @Override
    public void show() {
        if(!VisUI.isLoaded()){
            VisUI.load();
        }

        nombreBotones();

        stage = new Stage();

        VisTable tabla = new VisTable(true);
        tabla.setFillParent(true);
        stage.addActor(tabla);

        final VisTextButton btVolumen = new VisTextButton(correctoV);
        btVolumen.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(volumen == true){
                    btVolumen.setText(volOff);
                    volumen = false;
                } else {
                    btVolumen.setText(volOn);
                    volumen = true;
                }
            }
        });

        final VisTextButton btHabilidad = new VisTextButton(correctoH);
        btHabilidad.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                if(habilidadOn == true){
                    btHabilidad.setText(habOff);
                    habilidadOn = false;
                } else {
                    btHabilidad.setText(habOn);
                    habilidadOn = true;
                }
            }
        });

        VisTextButton btAtras = new VisTextButton("Atras");
        btAtras.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new PantallaInicio(volumen,habilidadOn));
                dispose();
            }
        });

        tabla.row();
        tabla.add(btVolumen).center().width(200).height(100).pad(5);
        tabla.row();
        tabla.add(btHabilidad).left().width(200).height(100).pad(5);
        tabla.row();
        tabla.add(btAtras).center().width(200).height(100).pad(5);

        Gdx.input.setInputProcessor(stage);
    }

    private void nombreBotones() {
        if(volumen){
            correctoV = volOn;
        } else {
            correctoV = volOff;
        }

        if(habilidadOn){
            correctoH = habOn;
        } else {
            correctoH = habOff;
        }
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
