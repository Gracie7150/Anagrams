import java.util.*;

public class Anagrams {

    private Set<String> dictionary;
    private Set<String> allWords;

    public Anagrams(Set<String> dictionary)  {
        // throws exceptions for null
        if (dictionary == null) {
            throw new IllegalArgumentException();
        }
        // sets the dictionary as a valid dictionary
        this.dictionary = dictionary;
        this.allWords = new HashSet<String>();
    }

    public Set<String> getWords(String phrase) {
        // throws exceptions for null
        if (phrase == null) {
            throw new IllegalArgumentException();
        }

        LetterInventory chosenPhrase = new LetterInventory(phrase);

        Iterator<String> iterator = dictionary.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();

            LetterInventory dictPhrase = new LetterInventory(element);

            if (chosenPhrase.contains(dictPhrase) && !dictPhrase.isEmpty()) {
                allWords.add(element);
            }
        }

        return allWords;
    }

    public void print(String phrase) {
        // throws exceptions for null
        if (phrase == null) {
            throw new IllegalArgumentException();
        }

        LetterInventory chosenPhrase = new LetterInventory(phrase);
        List<String> List = new ArrayList<String>();
        printHelper(chosenPhrase, List, 0);
    }


    public void print(String phrase, int max) {
        // throws exceptions for null/invalid max
        if (max < 0) {
            throw new IllegalArgumentException();
        }
        if (phrase == null) {
            throw new IllegalArgumentException();
        }
        
        LetterInventory chosenPhrase = new LetterInventory(phrase);
        List<String> List = new ArrayList<String>();
        printHelper(chosenPhrase, List, max);
    }

    private void printHelper(LetterInventory chosenPhrase, List<String> List, int max) {
        if (chosenPhrase.isEmpty() && max == 0) {
            System.out.println(List);
        } else if ((List.size() == max) && chosenPhrase.isEmpty()) {
            System.out.println(List);
        } else {
            for (String word: dictionary) {
                if (chosenPhrase.contains(word)) {
                    LetterInventory newInventory = new LetterInventory(chosenPhrase);
                    List<String> newList = new ArrayList<String>(List);
                    newList.add(word);
                    newInventory.subtract(word);
                    printHelper(newInventory, newList, max);
                }
            }
        }
    }
}