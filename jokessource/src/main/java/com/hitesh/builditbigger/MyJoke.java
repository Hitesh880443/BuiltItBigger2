package com.hitesh.builditbigger;

import java.util.Random;

public class MyJoke {

    private String[] jokes;
    private Random random_number;

    public MyJoke() {
        jokes = new String[7];
        jokes[0] = "Barack Obama Jokes About Using LinkedIn After White House Stint";
        jokes[1] = "Wife: \"In my dream, I saw you in a jewelry store and you bought me a diamond ring.\" \n" +
                "Husband: \"I had the same dream and I saw your dad paying the bill.\"\n" +
                "Today's Joke";
        jokes[2] = "Q: How do you count cows? \n" +
                "A: With a cowculator.";
        jokes[3] = "Teacher: \"What is the future tense of the statement: 'I had killed a thief'?\" \n" +
                "Student: \"You will go to jail.\"";

        jokes[4] = "Yo momma's so fat, when I said I wanted \"pigs in a blanket,\" she got back in bed.";
        jokes[5] = "Q: What do you call a noodle that commits identity theft? \n" +
                "A: An impasta!";
        jokes[6] = "Paddy and Murphy are havin' a pint in the pub, when some scuba divers come on the TV. Paddy says, \"Murphy, why is it them deep sea divers always sit on the side of the boat with them air tanks on their backs, and fall backwards out of the boat?\" Murphy thinks for a minute then says, \"That's easy. It's 'cos if they fell forwards, they'd still be in the friggin boat!\"";

        random_number = new Random();
    }

    public String[] getJokes() {
        return jokes;
    }

    public String getRandomJoke() {
        return jokes[random_number.nextInt(jokes.length)];
    }
}
