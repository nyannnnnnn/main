package parser;

public class AddBreakRegion {
    
    private final String[] KEY_WORDS = {"on", "by", "from"};
    
    private String description;
    private String firstDateRegion;
    private String secondDateRegion;
    private String tagRegion;
    private String keyWord;
    
    public AddBreakRegion(String userInput) {
        description = "";
        firstDateRegion = "";
        secondDateRegion = "";
        tagRegion = "";
        keyWord = "";
        
        int keyWordPosition = getKeyWordPosition(userInput);
        int keyWordToPosition = 0;
        int tagPosition = 0;
        if (keyWordPosition > 0) {
            description = userInput.substring(0, keyWordPosition);
            keyWord = findKeyWord(userInput);
            
            String dateRegion;
            String afterKeyWord = userInput.substring(keyWordPosition + keyWord.length());
            tagPosition = getTagPosition(afterKeyWord);
            if (tagPosition >= 0) {
                dateRegion = afterKeyWord.substring(0, tagPosition);
                tagRegion = afterKeyWord.substring(tagPosition + 1);
            } else {
                dateRegion = afterKeyWord;
            }
            dateRegion = dateRegion.toLowerCase();
            
            if (keyWord.equals(KEY_WORDS[2])) {
                keyWordToPosition = dateRegion.indexOf(" to ");          
                if (keyWordToPosition > 0) {
                    firstDateRegion = dateRegion.substring(0, keyWordToPosition + 4);
                    secondDateRegion = dateRegion.substring(keyWordToPosition + 4);
                } else {
                    firstDateRegion = dateRegion;
                }
            } else {
                firstDateRegion = dateRegion;
            }
        } else {
            tagPosition = getTagPosition(userInput);
            if (tagPosition > 0) {
                description = userInput.substring(0, tagPosition);
                tagRegion = userInput.substring(tagPosition + 1);
            } else {
                description = userInput.trim();
            }
        }
        
        description = description.trim();
        keyWord = keyWord.trim();
        firstDateRegion = firstDateRegion.trim();
        secondDateRegion = secondDateRegion.trim();
        
        System.out.println();
        System.out.println("BreakRegion------------------------------------------");
        System.out.println("Description: |" + description + "|");
        System.out.println("startDateRegion: |" + firstDateRegion + "|");
        System.out.println("endDateRegion: |" + secondDateRegion + "|");
        System.out.println("TagRegion: |" + tagRegion + "|");
        System.out.println("KeyWord: |" + keyWord + "|");
        System.out.println("------------------------------------------BreakRegion");
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getFirstDateRegion() {
        return firstDateRegion;
    }
    
    public String getSecondDateRegion() {
        return secondDateRegion;
    }
    
    public String getTagRegion() {
        return tagRegion;
    }
    
    public String getKeyWord() {
        return keyWord;
    }
    
    private int getKeyWordPosition(String input) {
        input = input.toLowerCase();
        String keyWord = findKeyWord(input);
        if (!keyWord.isEmpty()) {
            return input.lastIndexOf(keyWord);
        } else {
            return -1;
        }
    }
    
    private int getTagPosition(String input) {
        if (input.contains(" #")) {
            return input.indexOf(" #");
        } else {
            return -1;
        }
    }
    
    private String findKeyWord(String input) {
        int position = 0;
        String keyWord = "";
        input = input.toLowerCase();
        
        if (input.contains(new String(" " + KEY_WORDS[0] + " ")) && input.lastIndexOf(KEY_WORDS[0]) > position) {
            position = input.lastIndexOf(KEY_WORDS[0]);
            keyWord = KEY_WORDS[0];
        }
        if (input.contains(new String(" " + KEY_WORDS[1] + " ")) && input.lastIndexOf(KEY_WORDS[1]) > position) {
            position = input.lastIndexOf(KEY_WORDS[1]);
            keyWord = KEY_WORDS[1];
        }
        if (input.contains(new String(" " + KEY_WORDS[2] + " ")) && input.lastIndexOf(KEY_WORDS[2]) > position) {
            position = input.lastIndexOf(KEY_WORDS[2]);
            keyWord = KEY_WORDS[2];
        }
        System.out.println("Keyword = " + keyWord);
        return keyWord;
    }
}
