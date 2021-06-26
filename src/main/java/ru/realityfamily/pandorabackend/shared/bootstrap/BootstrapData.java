package ru.realityfamily.pandorabackend.shared.bootstrap;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.realityfamily.pandorabackend.shared.models.*;
import ru.realityfamily.pandorabackend.shared.repository.*;

import java.io.File;
import java.util.*;

//@Component
public class BootstrapData implements ApplicationListener<ContextRefreshedEvent> {

    ItemRepository itemRepository;
    UserRepository userRepository;
    CategoryRepository categoryRepository;
    SubcategoryRepository subcategoryRepository;
    SubtagRepository subtagRepository;
    private User admin;
    private User testUser;
    private List<Item> debugItemList = new ArrayList<>();

    public BootstrapData(ItemRepository itemRepository, UserRepository userRepository, CategoryRepository categoryRepository, SubcategoryRepository subcategoryRepository, SubtagRepository subtagRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.subtagRepository = subtagRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadAllDebugUsers();
        loadItem();
        loadAllDebugCategoriesAndSubcategories();
    }

    private void loadItem(){
        Item item1 = new Item("Divan obivan", ModelAccessStrategy.Premium, admin);
        item1.setId("6073182a864dbb4531677d3f");
        debugItemList.add(itemRepository.save(item1));

        item1 = new Item("Stul Barnaul", ModelAccessStrategy.Premium, admin);
        item1.setId("6073182a864dbb4531677d40");
        debugItemList.add(itemRepository.save(item1));

        item1 = new Item("Lustra Balustra", ModelAccessStrategy.Premium, admin);
        item1.setId("6073182a864dbb4531677d41");
        debugItemList.add(itemRepository.save(item1));

        item1 = new Item("Shkaf gav", ModelAccessStrategy.Premium, admin);
        item1.setId("6073182a864dbb4531677d42");
        debugItemList.add(itemRepository.save(item1));

        item1 = new Item("Lol kek cheburek", ModelAccessStrategy.Premium, admin);
        saveIfNotExist(item1, itemRepository);
    }

    private void loadAllDebugUsers(){
        admin = new User("realityfamilyteam@yandex.ru","realityfamily", "$2a$10$OAOb9zOPZe6GKVQXARCot.ApdBr297OU0orKRlIiz1hKHjhVrBUaK", Arrays.asList(Role.Admin));
        admin.setId("607300ddbd25bf1b1101561b");
        saveIfNotExist(admin, userRepository);

        testUser = new User("test@gmail.com", "testUser", "$2a$10$OAOb9zOPZe6GKVQXARCot.ApdBr297OU0orKRlIiz1hKHjhVrBUaK", Arrays.asList(Role.User));
        testUser.setId("607300ddbd25bf1b1101561c");
        saveIfNotExist(testUser, userRepository);

    }


    private void loadAllDebugCategoriesAndSubcategories(){

        List<Subcategory> subcategoriesKuhnya = new ArrayList<>();
        subcategoriesKuhnya.add(new Subcategory("Еда,напитки","подкатегория еды и напитков"));
        subcategoriesKuhnya.add(new Subcategory("Кухонные гарнитуры","подкатегория кухонные гарнитуры"));
        subcategoriesKuhnya.add(new Subcategory("Техника крупная","подкатегория техника крупная"));
        subcategoriesKuhnya.add(new Subcategory("Декор","Подкатегория декор"));
        subcategoriesKuhnya.add(new Subcategory("Посуда","подкатегория посуда"));
        subcategoriesKuhnya = saveAllSubcategoryIfNotExist(subcategoriesKuhnya);
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Кухня", "Категория для добавления кухонных приборов и их принадлежностей", subcategoriesKuhnya ));

        List<Subcategory> subcategoriesDetskaya = new ArrayList<>();
        subcategoriesDetskaya.add(new Subcategory("Мебель корпусная","подкатегория Мебель корпусная"));
        subcategoriesDetskaya.add(new Subcategory("Кровати и диваны","подкатегория Кровати и диваны"));
        subcategoriesDetskaya.add(new Subcategory("Столы и стулья","подкатегория Столы и стулья"));
        subcategoriesDetskaya.add(new Subcategory("Декор","Подкатегория Декор"));
        subcategoriesDetskaya.add(new Subcategory("Игрушки","подкатегория Игрушки"));
        subcategoriesDetskaya = saveAllSubcategoryIfNotExist(subcategoriesDetskaya);
        categories.add(new Category("Детская", "Категория для добавления новых моделей детских", subcategoriesDetskaya));

        saveAllCategoryIfNotExist(categories);

        List<Subtag> mebelStolySubtag = new ArrayList<>();
        mebelStolySubtag.add(new Subtag("Журнальные", new ArrayList<Item>() {
            {
                for(int i = 1; i<11; i++)  add( saveItemIfNotExist(new Item("Item Number"+ i, "There is some description for item number "+ i, ModelAccessStrategy.Free, testUser)));
            }
        }));
        mebelStolySubtag = saveAllSubtagIfNotExist(mebelStolySubtag);


        List<Subcategory> subcategoriesMebel = new ArrayList<>();
        subcategoriesMebel.add(new Subcategory("Столы","подкатегория столов", mebelStolySubtag));
        subcategoriesMebel.add(new Subcategory("Стулья","подкатегория стульев"));
        subcategoriesMebel.add(new Subcategory("Шкафы","подкатегория шкафы "));
        subcategoriesMebel.add(new Subcategory("Стеллажи","Подкатегория стеллажи"));
        subcategoriesMebel.add(new Subcategory("Комоды","подкатегория Комоды"));
        subcategoriesMebel.add(new Subcategory("Полки","подкатегория Полки"));
        subcategoriesMebel.add(new Subcategory("Консоли","подкатегория Консоли"));
        subcategoriesMebel.add(new Subcategory("Мебель для медиа","подкатегория Мебель для медиа"));
        subcategoriesMebel.add(new Subcategory("Прихожие","подкатегория прихожие"));
        subcategoriesMebel.add(new Subcategory("Подставки","подкатегория Подставки"));
        subcategoriesMebel.add(new Subcategory("Тумбы","подкатегория Тумбы"));
        subcategoriesMebel.add(new Subcategory("Модульная","подкатегория Модульная"));
        subcategoriesMebel.add(new Subcategory("Комплекты","подкатегория Комплекты"));
        subcategoriesMebel.add(new Subcategory("Фурнитура","подкатегория Фурнитура"));
        subcategoriesMebel = saveAllSubcategoryIfNotExist(subcategoriesMebel);
        categories.add(new Category("Мебель", "Категория для добавления новых моделей мебели общего назначения", subcategoriesMebel));

        saveAllCategoryIfNotExist(categories);
    }

    public <T> List<T> loadObjectList(Class<T> type, String fileName) {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();
            File file = new ClassPathResource(fileName).getFile();
            MappingIterator<T> readValues =
                    mapper.reader(type).with(bootstrapSchema).readValues(file);
            return readValues.readAll();
        } catch (Exception e) {
            Logger logger = LoggerFactory.getLogger(BootstrapData.class);
            logger.error("Error occurred while loading object list from file " + fileName, e);
            return Collections.emptyList();
        }
    }


    private List<Item> saveAllItemIfNotExist(List<Item> item){
        List<Item> itemOutput = new ArrayList<>();
        for (Item i : item){
            itemOutput.add(saveItemIfNotExist(i));
        }
        return itemOutput;
    }

    private Item saveItemIfNotExist(Item item){
        boolean a = itemRepository.exists(Example.of(item));
        if(!a){
            return itemRepository.save(item);
        }
        else return itemRepository.findOne(Example.of(item)).get();
    }


    private List<Category> saveAllCategoryIfNotExist(List<Category> categories){
        List<Category> categoriesOutput = new ArrayList<>();
        for (Category c : categories){
            categoriesOutput.add(saveCategoryIfNotExist(c));
        }
        return categoriesOutput;
    }

    private Category saveCategoryIfNotExist(Category category){
        boolean a = categoryRepository.exists(Example.of(category));
        if(!a){
            return categoryRepository.save(category);
        }
        else return categoryRepository.findOne(Example.of(category)).get();
    }

    private Subcategory saveSubcategoryIfNotExist(Subcategory subcategory){
        if(!subcategoryRepository.exists(Example.of(subcategory))){
            return subcategoryRepository.save(subcategory);
        }
        else return subcategoryRepository.findOne(Example.of(subcategory)).get();
    }

    private List<Subcategory> saveAllSubcategoryIfNotExist(List<Subcategory> subcategories){
        List<Subcategory> subcategoryOutput = new ArrayList<>();
        for (Subcategory c : subcategories){
            subcategoryOutput.add(saveSubcategoryIfNotExist(c));
        }
        return subcategoryOutput;
    }

    private Subtag saveSubtagIfNotExist(Subtag subtag){
        if(!subtagRepository.exists(Example.of(subtag))){
            return subtagRepository.save(subtag);
        }
        else return subtagRepository.findOne(Example.of(subtag)).get();
    }

    private List<Subtag> saveAllSubtagIfNotExist(List<Subtag> subcategories){
        List<Subtag> subtagOutput = new ArrayList<>();
        for (Subtag c : subcategories){
            subtagOutput.add(saveSubtagIfNotExist(c));
        }
        return subtagOutput;
    }

    private <T extends BaseMongoTemplate, R extends MongoRepository> T saveIfNotExist(T object, R repository ){
        if(!repository.exists(Example.of(object))){
            return (T) repository.save(object);
        }
        else return (T) repository.findOne(Example.of(object)).get();

    }
}
