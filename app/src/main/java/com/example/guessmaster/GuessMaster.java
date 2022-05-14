package com.example.guessmaster;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.*;
import java.util.Random;
import android.content.DialogInterface;

import android.os.Bundle;

public class GuessMaster extends AppCompatActivity {
    //view components
    private TextView entityName;
    private TextView ticketsum;
    private Button guessButton;
    private EditText userIn;
    private Button btnclearContent;
    private String user_input;
    private ImageView entityImage;
    String answer;

    //entity objects
    Country usa = new Country ("United States",
            new Date("July", 4, 1776), 0.1, "Washington DC");
    Person myCreator = new Person("My Creator", new Date("December", 19, 2001),
            1, "Male");
    Politician trudeau = new Politician("Justin Trudeau", new Date("December",
            25,1971),0.25, "Male", "Liberal");
    Singer dion = new Singer("Celine Dion", new Date("March", 30, 1961),
            0.5, "Female", "La voix du bon Dieu",
            new Date("November", 8, 1981));

    //total number of possible entities
    private int numOfEntities;
    //array of all possible entities
    private Entity[] entities;
    //total ticket count
    private int tickets = 0;
    //index of current entity being guessed in array
    private int currentEntityId;

    /**
     * add an entity to the array of entities
     * @param entity to be added to the array
     */
    public void addEntity(Entity entity) {
        entities[numOfEntities++] = entity.clone();
    }

    /**
     * welcome user each time a new entity is chosen
     * @param entity contains the welcome message to be displayed
     */
    private void welcomeMessage(Entity entity) {
        //configure welcome alert
        AlertDialog.Builder welcomealert = new AlertDialog.Builder(GuessMaster.this);
        welcomealert.setTitle("GuessMaster game v3");
        welcomealert.setMessage(entity.welcomeMessage());
        welcomealert.setCancelable(false);

        //wait until start game button in alert is clicked to start game
        welcomealert.setNegativeButton("START GAME", new DialogInterface.OnClickListener() {

            //once start game button is click display toast welcome notification
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                Toast.makeText(getBaseContext(), "Game is Starting....Enjoy"
                        , Toast.LENGTH_SHORT).show();
            }
        });

        //display alert
        AlertDialog dialog = welcomealert.create();
        dialog.show();
    }

    /**
     * check if user guess matches the entity's birthday
     * @param entity contains birthday to be compared to the user guess
     */
    private void playGame(Entity entity) {

        //display entity name in text view
        entityName.setText(entity.getName());

        //get user input from edit text
        answer = userIn.getText().toString();
        answer = answer.replace("\n", "").replace("\r", "");
        Date date = new Date(answer);

        //if guess was correct, increment total tickets, display winning alert, and change entity
        if(date.equals(entity.getBorn())) {
            //add tickets won to tickets total and update in textview
            incrementTickets(entity.getAwardedTicketNumber());

            //configure win alert
            AlertDialog.Builder winalert = new AlertDialog.Builder(GuessMaster.this);
            winalert.setTitle("GuessMaster game v3");
            winalert.setMessage("BINGO!\n" + entity.closingMessage());
            winalert.setCancelable(false);

            //wait until continue button in alert is clicked to change entity
            winalert.setNegativeButton("CONTINUE", new DialogInterface.OnClickListener() {

                //once continue is clicked, display toast notification with tickets
                //won, change entity, and display its type
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    Toast.makeText(getBaseContext(), "You won " + entity.getAwardedTicketNumber() + " tickets!"
                            , Toast.LENGTH_SHORT).show();
                    changeEntity();
                    Toast.makeText(getBaseContext(), entities[currentEntityId].entityType()
                            , Toast.LENGTH_SHORT).show();
                }
            });

            //display alert
            AlertDialog dialog = winalert.create();
            dialog.show();

        //if the guess was incorrect, display incorrect alert
        } else{

            //configure incorrect alert
            AlertDialog.Builder incorrectalert = new AlertDialog.Builder(GuessMaster.this);
            incorrectalert.setTitle("Incorrect");
            //check if the guess was too early or too late
            if (date.precedes(entity.getBorn())) {
                incorrectalert.setMessage("Incorrect. Try a later date");
            } else {
                incorrectalert.setMessage("Incorrect. Try an earlier date");
            }
            incorrectalert.setCancelable(false);

            //wait until ok is clicked to continue game
            incorrectalert.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                }
            });

            //display alert
            AlertDialog dialog = incorrectalert.create();
            dialog.show();
        }
    }

    /**
     * check if the current guess matches the birthdate of the current entity
     */
    private void playGame() {
        playGame(entities[currentEntityId]);
    }

    /**
     * get a valid entity index
     * @return int that corresponds to an index of an entity in entities array
     */
    private int genRandomEntityId() {
        Random randomNumber = new Random();
        return randomNumber.nextInt(numOfEntities);
    }

    /**
     * This method is called when app is started. Set up the app
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //chose activity_guess_activity as the layout
        setContentView(R.layout.activity_guess_activity);

        //add all pre defined entities to entities array
        entities = new Entity[100];
        addEntity(new Country(usa));
        addEntity(new Person(myCreator));
        addEntity(new Politician(trudeau));
        addEntity(new Singer(dion));
        numOfEntities = 4;

        //Get all views as objects
        //TextView for total tickets
        ticketsum = (TextView) findViewById(R.id.ticket);
        //image view to display image
        entityImage = (ImageView) findViewById(R.id.entityImage);
        //TextView for total tickets
        entityName = (TextView) findViewById(R.id.entityName);
        //EditText for user input
        userIn = (EditText) findViewById(R.id.guessinput);
        //Specify the button in the view
        guessButton = (Button) findViewById(R.id.btnGuess); //note R refers to button defined in xml
        //Specify the button in the view
        btnclearContent = (Button) findViewById(R.id.btnClear);

        //select and show entity for the first round of the game
        changeEntity();
        //display welcome message for this entity
        welcomeMessage(entities[currentEntityId]);

        //When Change Entity button is clicked, select and show a new entity and display its type
        btnclearContent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                changeEntity();
                Toast.makeText(getBaseContext(), entities[currentEntityId].entityType()
                        , Toast.LENGTH_SHORT).show();
            }
        });

        //When Submit Guess button is clicked, check if the user guess matches the entity's birthday
        guessButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                playGame();
            }
        });
    }

    /**
     * increment the total ticket count and update total tickets displayed
     * @param newTickets number to increment total tickets by
     */
    private void incrementTickets(int newTickets) {
        tickets += newTickets;
        ticketsum.setText("Total Tickets: " + tickets);
    }

    /**
     * set the image in the image view to that of the entity
     * @param entity determines which image to use
     */
    private void ImageSetter(Entity entity) {
        switch (entity.getName()) {
            case "United States":
                entityImage.setImageResource(R.drawable.usaflag);
                break;
            case "My Creator":
                entityImage.setImageResource(R.drawable.selfie);
                break;
            case "Justin Trudeau":
                entityImage.setImageResource(R.drawable.justint);
                break;
            case "Celine Dion":
                entityImage.setImageResource(R.drawable.celidion);
                break;
            default:
                entityImage.setImageResource(R.drawable.ic_error_outline_black_24dp);
        }
    }

    /**
     * select a new entity and change the image displayed
     */
    private void changeEntity() {
        //get new entity
        currentEntityId = genRandomEntityId();
        Entity entity = entities[currentEntityId];
        //update the image displayed
        ImageSetter(entity);
        //display welcome message for this entity
        //welcomeMessage(entity);
        //update the entity name displayed in the text view
        String entName = entity.getName();
        entityName.setText(entName);
        //clear previous guess in edit text
        userIn.getText().clear();
    }




}