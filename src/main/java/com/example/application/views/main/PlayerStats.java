package com.example.application.views.main;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.model.HorizontalAlign;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import entity.Player;

public class PlayerStats extends HorizontalLayout {
    TextField name = new TextField();
    TextField score = new TextField();
    TextField round = new TextField();
    IntegerField roundScore = new IntegerField();
    Button addRoundScore = new Button();

    private Player player;

    public PlayerStats(Player player) {

        this.player = player;

        this.name.setValue(player.getName());
        this.score.setValue(Integer.toString(player.getTotalScore()));
        this.round.setValue(Integer.toString(player.getCurrentTurn()));

        this.name.setLabel("Player name");
        this.score.setLabel("Total score");
        this.round.setLabel("Round number");
        this.roundScore.setLabel("Score to add");
        this.addRoundScore.setText("Add Score");

        setPadding(true);
        setAlignItems(Alignment.CENTER);

        buttonPressClick();


        add(
                this.name, this.score, this.round, this.roundScore, this.addRoundScore
        );

        setToReadOnly();
    }

    private void buttonPressClick() {
        this.addRoundScore.addClickListener(buttonClickEvent -> {
            this.player.newUserTurn(this.roundScore.getValue());

            this.score.setValue(Integer.toString(player.getTotalScore()));
            this.round.setValue(Integer.toString(player.getCurrentTurn()));
        });
    }

    private void setToReadOnly() {
        this.name.setReadOnly(true);
        this.score.setReadOnly(true);
        this.round.setReadOnly(true);
    }

}
