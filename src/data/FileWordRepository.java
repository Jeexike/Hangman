package data;

import model.CategoriesEnum;
import model.Difficulty;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;
import java.util.Iterator;

public class FileWordRepository implements WordRepository {
    private final Map<CategoriesEnum, List<String>> categoryMap;
    private final Random random = new Random();

    public FileWordRepository() {
        this.categoryMap = new EnumMap<>(CategoriesEnum.class);
        getAllWards(FILE_PATH);
    }

    @Override
    public void getAllWards(String filePath) {
        for (CategoriesEnum category : CategoriesEnum.values()) {
            categoryMap.put(category, new ArrayList<>());
        }

        CategoriesEnum currentCategory = null;

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
                        currentCategory = CategoriesEnum.valueOf(categoryName);
                        continue;
                    } catch (IllegalArgumentException e) {
                        System.err.println("Неизвестная категория: " + categoryName);
                        currentCategory = null;
                    }
                }

                if (currentCategory != null) {
                    String word = line.toLowerCase();
                    categoryMap.get(currentCategory).add(word);
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
    public List<String> getWordsByDifficulty(CategoriesEnum category, Difficulty difficulty) {
        List<String> wordsByDifficult = new ArrayList<>(categoryMap.get(category));
        Iterator<String> iterator = wordsByDifficult.iterator();
        while (iterator.hasNext()) {
            String word = iterator.next();
            if (!(word.length() >= difficulty.getMinWordLength() && word.length() <= difficulty.getMaxWordLength())) {
                iterator.remove();
            }
        }

        if (wordsByDifficult.isEmpty()) {
            return categoryMap.get(category);
        }

        return wordsByDifficult;
    }

    @Override
    public String getRandomWord(CategoriesEnum category, Difficulty difficulty) {
        List<String> wordsByDifficulty = getWordsByDifficulty(category, difficulty);
        int randomInt = random.nextInt(wordsByDifficulty.size());
        return wordsByDifficulty.get(randomInt);
    }
}
