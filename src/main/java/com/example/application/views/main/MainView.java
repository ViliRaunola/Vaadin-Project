package com.example.application.views.main;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import entity.Player;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Score manager")
@Route(value = "")
public class MainView extends VerticalLayout {
    TextField filterText = new TextField();
    PlayerForm playerForm = new PlayerForm();
    TextField newPlayerName = new TextField();
    Button addNewPlayer = new Button();
    VerticalLayout stats = new VerticalLayout();


    private List<Player> players = new ArrayList<>();

    public MainView() {
        addClassName("list-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);

        add(new H1("Score tracker"));
        add(new Paragraph("This is a MVP version of the score tracking. More features coming such as saving current game, viewing past games and removing players. Stay tuned!"));

        add(
                addNewPlayer(),
                getPlayerStats()
        );
    }


    private Component addNewPlayer() {
        newPlayerName.setLabel("New player name");
        addNewPlayer.setText("Add player to the game");

        addNewPlayer.addClickListener(buttonClickEvent -> {
            String newPlayerNameString = newPlayerName.getValue().strip();

            if(!newPlayerNameString.isEmpty()){
                this.players.add(new Player(newPlayerNameString));
                Player player = this.players.get(this.players.size() - 1);
                PlayerStats playerStats = new PlayerStats(player);
                stats.add(playerStats);
            } else {
                Notification notification = Notification
                        .show("Player name cannot be empty");
                notification.addThemeVariants(NotificationVariant.LUMO_WARNING);
            }
        });

        VerticalLayout content = new VerticalLayout();
        content.add(newPlayerName, addNewPlayer);
        content.setAlignItems(Alignment.CENTER);
        return content;
    }

    private Component getPlayerStats() {
        stats.setAlignItems(Alignment.CENTER);
        for (Player player : players) {
            PlayerStats playerStats = new PlayerStats(player);
            stats.add(playerStats);
        }

        return stats;
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(playerForm);
        content.setFlexGrow(1, playerForm);
        content.addClassName("content");

        content.setSizeFull();
        return content;
    }


    private Component getToolBar() {
        filterText.setPlaceholder("Filter by name");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button addPlayerButton = new Button("Add player");

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addPlayerButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

}
