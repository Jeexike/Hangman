package data;

import model.Categories;
import model.Difficulty;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileWordRepository implements WordRepository{
    private final Map<Categories, List<String>> wordsByCategory;
    Random random = new Random();

    public FileWordRepository() {
        this.wordsByCategory = new EnumMap<>(Categories.class);
        String filePath = "resources/words";
        getAllWards(filePath);
    }

    @Override
    public void getAllWards(String filePath) {
        for (Categories category : Categories.values()) {
            wordsByCategory.put(category, new ArrayList<>());
        }

        Categories currentCategory = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                 if (line.isEmpty()) {
                     continue;
                 }

                 if (line.startsWith("#")) {
                     String categoryName = line.substring(1).trim().toUpperCase();
                     try {
                         currentCategory = Categories.valueOf(categoryName);
                         continue;
                     } catch (IllegalArgumentException e) {
                         System.err.println("Неизвестная категория: " + categoryName);
                         currentCategory = null;
                     }
                 }

                 if (currentCategory != null) {
                     String word = line.toLowerCase();
                     wordsByCategory.get(currentCategory).add(word);
                 }
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
    }

//    Test Func
//    public void printMap() {
//        for (Map.Entry<Categories, List<String>> entry : wordsByCategory.entrySet()) {
//            Categories categories = entry.getKey();
//            List<String> list = entry.getValue();
//            System.out.println(categories + " : " + list);
//        }
//
//        for (String word : getWordsByDifficulty(Categories.SPORT, Difficulty.EASY)){
//            System.out.println(word);
//        }
//        System.out.println();
//        System.out.println();
//        System.out.println(getRandomWord(Categories.SPORT, Difficulty.EASY));
//    }

    @Override
    public List<String> getWordsByDifficulty(Categories category, Difficulty difficulty) {
        List<String> wordsByDifficult = new ArrayList<>(wordsByCategory.get(category));
        Iterator<String> iterator = wordsByDifficult.iterator();
        while (iterator.hasNext()){
            String word = iterator.next();
            if (!(word.length() >= difficulty.getMinWordLength() && word.length() <= difficulty.getMaxWordLength())) {
                iterator.remove();
            }
        }

        if (wordsByDifficult.isEmpty()) {
            return wordsByCategory.get(category);
        }

        return wordsByDifficult;
    }

    @Override
    public String getRandomWord(Categories category, Difficulty difficulty) {
        List<String> wordsByDifficulty = getWordsByDifficulty(category, difficulty);
        int randomInt = random.nextInt(wordsByDifficulty.size());
        return wordsByDifficulty.get(randomInt);
    }
}
